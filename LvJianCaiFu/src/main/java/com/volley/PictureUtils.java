package com.volley;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Path;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;

import com.ryan.slidefragment.base.BaseApplication;
public class PictureUtils {
	private static final String LOGTAG = "PictureUtils";
	public static final int LEFT = 0;
	public static final int RIGHT = 1;
	public static final int TOP = 3;  
	public static final int BOTTOM = 4;
	public static final int CENTER = 5;
	public static final Config BITMAP_CONFIG = Config.ARGB_4444;
	
	public static Bitmap toRoundBitmap(Bitmap bitmap){
		int width = bitmap.getWidth();
		int height = bitmap.getHeight();
		float roundPx;
		float left,top,right,bottom,dst_left,dst_top,dst_right,dst_bottom;
		if (width<=height) {
			roundPx = width/2;
			top = 0;
			bottom = width;
			left = 0;
			right = width;
			height = width;
			dst_left = 0;
			dst_top = 0;
			dst_right = width;
			dst_bottom = width;
		} else{
			roundPx = height/2;
			float clip = (width - height)/2;
			left = clip;
            right = width - clip;
            top = 0;
            bottom = height;
            width = height;
            dst_left = 0;
            dst_top = 0;
            dst_right = height;
            dst_bottom = height;
		}
		Bitmap output = Bitmap.createBitmap(width,height,Config.ARGB_8888);
		Canvas canvas = new Canvas(output);
		
		final int color = 0xff424242;
		final Paint paint = new Paint();
		final Rect src = new Rect(( int)left, ( int )top, ( int)right, (int )bottom);
		final Rect dst = new Rect(( int)dst_left, ( int )dst_top, (int )dst_right, ( int)dst_bottom);
		final RectF rectF = new RectF(dst);
		
		paint.setAntiAlias( true );
		
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);
		canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
		
		paint.setXfermode( new PorterDuffXfermode(Mode. SRC_IN));
		canvas.drawBitmap(bitmap, src, dst, paint);
		return output;
	}
	
	private static int sampleSize(int width,int target){
		int result = 1;
		for(int i = 0;i<10;i++){
			if(width<target*2){
				break;
			}
			width = width/2;
			result = result*2;
		}
		return result;
	}
	public static Bitmap reducePic(String path,int width,int height){
		if (!FileUtil.isFileExist(path))
			return null;
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		Bitmap bitmap = BitmapFactory.decodeFile(path, options); // ��ʱ����bmΪ��
		int widthRatio = (int) Math.ceil(options.outWidth / width);
		int heightRatio = (int) Math.ceil(options.outHeight / height);
		if (widthRatio > 1 || heightRatio > 1) {
			if (widthRatio > heightRatio) {
				options.inSampleSize = widthRatio;
			} else {
				options.inSampleSize = heightRatio;
			}
		}
		options.inJustDecodeBounds = false;
		bitmap = BitmapFactory.decodeFile(path, options);
		return bitmap;
	}
	
	/**
	 * ͼƬ���ųɹ̶���С
	 * 
	 * @param path
	 *            ͼƬ·��
	 * @param newWidth
	 *            ͼƬ���ſ��
	 * @param newHeight
	 *            ͼƬ���Ÿ߶�
	 * @return
	 */
	public static Bitmap reducePicSize(String path, int newWidth, int newHeight) {
		if (!FileUtil.isFileExist(path))
			return null;
		return reducePicSize(BitmapFactory.decodeFile(path), newWidth,
				newHeight);
	}

	/**
	 * ͼƬ���ųɹ̶���С
	 * 
	 * @param path
	 *            ͼƬ·��
	 * @param newWidth
	 *            ͼƬ���ſ��
	 * @param newHeight
	 *            ͼƬ���Ÿ߶�
	 * @return
	 */
	public static Bitmap reducePicSize(Bitmap bitmap, int newWidth,
			int newHeight) {
		if (bitmap == null)
			return null;
		// ��ȡ���ͼƬ�Ŀ�͸�
		int width = bitmap.getWidth();
		int height = bitmap.getHeight();
		// ���������ʣ��³ߴ��ԭʼ�ߴ�
		float scaleWidth = ((float) newWidth) / width;
		float scaleHeight = ((float) newHeight) / height;
		return toScale(bitmap, scaleWidth, scaleHeight);
	}

	/** ��ͼƬ�浽SD���� */
	public static void savePictrueToSDCard(String path, Bitmap bitmap) {
		saveReduceQuality(path, bitmap, 100);
	}

	/**
	 * ��ͼƬ�����������浽SD����
	 * 
	 * @param path
	 *            ����·��
	 * @param bitmap
	 *            ԭͼƬ
	 * @param quality
	 *            �������� 0-100��0��ͣ�100���
	 */
	public static void saveReduceQuality(String path, Bitmap bitmap, int quality) {
		if (bitmap == null || StringUtils.isEmpty(path)) {
			return;
		}
		FileUtil.creatDirs(path);
		BufferedOutputStream bos = null;
		try {
			bos = new BufferedOutputStream(new FileOutputStream(path));
			// ����ѹ��ת������
			bitmap.compress(Bitmap.CompressFormat.JPEG, quality, bos);
			// ����flush()����������BufferStream
			bos.flush();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// ����OutputStream
			if (bos != null)
				try {
					bos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
 /**
  * ��ȡ���ļ�
 * @param inStream
 * @return
 * @throws Exception
 */
public static byte[] readStream(InputStream inStream) throws Exception {
       byte[] buffer = new byte[1024];
       int len = -1;
       ByteArrayOutputStream outStream = new ByteArrayOutputStream();
       while ((len = inStream.read(buffer)) != -1) {
                outStream.write(buffer, 0, len);
       }
       byte[] data = outStream.toByteArray();
       outStream.close();
       inStream.close();
       return data;

  } 
/** ͼƬ��ΪԲ�� */
public static Bitmap getRoundedCornerBitmap(Bitmap bitmap) {
	if (bitmap == null)
		return null;
	int width = bitmap.getWidth();
	int height = bitmap.getHeight();
	int radius = width >= height ? height : width;
	return getRoundedCornerBitmap(bitmap, radius);
}

/**
 * ͼƬ��ΪԲ��
 * 
 * @param bitmap
 * @param radius
 *            ֱ��
 * @return
 */
public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, int radius) {
	if (bitmap == null)
		return null;
	int width = bitmap.getWidth();
	int height = bitmap.getHeight();
	int x = 0;
	int y = 0;
	// ��ȡ�м䲿��
	if (width > radius)
		x = (width - radius) / 2;
	if (height > radius)
		y = (height - radius) / 2;

	Bitmap output = Bitmap.createBitmap(width, height, BITMAP_CONFIG);
	Canvas canvas = new Canvas(output);

	final int color = 0xff424242;
	final Paint paint = new Paint();
	final Rect rect = new Rect(x, y, radius + x, radius + y);
	final RectF rectF = new RectF(rect);
	final float roundPx = radius / 2;

	paint.setAntiAlias(true);
	canvas.drawARGB(0, 0, 0, 0);
	paint.setColor(color);
	canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

	paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
	canvas.drawBitmap(bitmap, rect, rect, paint);
	return output;
}

/**
 * ͼƬ��ת
 * 
 * @param path
 * @param angle
 *            ��ת�Ƕ�
 * @return
 */
public static Bitmap toRotate(String path, int angle) {
	return toRotate(BitmapFactory.decodeFile(path), angle);
}

/**
 * ͼƬ��ת
 * 
 * @param path
 * @param angle
 *            ��ת�Ƕ�
 * @return
 */
public static Bitmap toRotate(Bitmap bitmapOrg, int angle) {
	if (bitmapOrg == null)
		return null;
	// ��ȡ���ͼƬ�Ŀ�͸�
	int width = bitmapOrg.getWidth();
	int height = bitmapOrg.getHeight();
	// ��������ͼƬ�õ�matrix����
	Matrix matrix = new Matrix();
	// ��תͼƬ ����
	matrix.postRotate(angle);
	// �����µ�ͼƬ
	Bitmap resizedBitmap = Bitmap.createBitmap(bitmapOrg, 0, 0, width,
			height, matrix, true);
	return resizedBitmap;
}

/**
 * ͼƬ����
 * 
 * @param path
 *            �����ͼƬ·��
 * @return ȥɫ���ͼƬ
 */
public static Bitmap toGrayscale(String path) {
	return toGrayscale(BitmapFactory.decodeFile(path));
}

/**
 * ͼƬ����
 * 
 * @param bmpOriginal
 *            �����ͼƬ
 * @return ȥɫ���ͼƬ
 */
public static Bitmap toGrayscale(Bitmap bmpOriginal) {
	if (bmpOriginal == null)
		return null;
	int width, height;
	height = bmpOriginal.getHeight();
	width = bmpOriginal.getWidth();
	Bitmap bmpGrayscale = Bitmap.createBitmap(width, height,
			Config.RGB_565);
	Canvas c = new Canvas(bmpGrayscale);
	Paint paint = new Paint();
	ColorMatrix cm = new ColorMatrix();
	cm.setSaturation(0);
	ColorMatrixColorFilter f = new ColorMatrixColorFilter(cm);
	paint.setColorFilter(f);
	c.drawBitmap(bmpOriginal, 0, 0, paint);
	return bmpGrayscale;
}

/**
 * ȥɫͬʱ��Բ��
 * 
 * @param bmpOriginal
 *            ԭͼ
 * @param pixels
 *            Բ�ǻ���
 * @return �޸ĺ��ͼƬ
 */
public static Bitmap toGrayscale(Bitmap bmpOriginal, int pixels) {
	return toRoundCorner(toGrayscale(bmpOriginal), pixels);
}

/**
 * ��ͼƬ���Բ��
 * 
 * @param bitmap
 *            ��Ҫ�޸ĵ�ͼƬ
 * @param pixels
 *            Բ�ǵĻ���
 * @return Բ��ͼƬ
 */
public static Bitmap toRoundCorner(Bitmap bitmap, int pixels) {
	if (bitmap == null)
		return null;
	Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
			bitmap.getHeight(), BITMAP_CONFIG);
	Canvas canvas = new Canvas(output);
	final int color = 0xff424242;
	final Paint paint = new Paint();
	final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
	final RectF rectF = new RectF(rect);
	final float roundPx = pixels;
	paint.setAntiAlias(true);
	canvas.drawARGB(0, 0, 0, 0);
	paint.setColor(color);
	canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
	paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
	canvas.drawBitmap(bitmap, rect, rect, paint);
	return output;
}

/**
 * ʹԲ�ǹ���֧��BitampDrawable
 * 
 * @param bitmapDrawable
 * @param pixels
 * @return
 */
public static BitmapDrawable toRoundCorner(BitmapDrawable bitmapDrawable,
		int pixels) {
	Bitmap bitmap = bitmapDrawable.getBitmap();
	bitmapDrawable = new BitmapDrawable(toRoundCorner(bitmap, pixels));
	return bitmapDrawable;
}

/**
 * ˮӡ
 * 
 * @param bitmap
 * @return
 */
public static Bitmap createBitmapForWatermark(Bitmap src, Bitmap watermark) {
	if (src == null) {
		return null;
	}
	int w = src.getWidth();
	int h = src.getHeight();
	int ww = watermark.getWidth();
	int wh = watermark.getHeight();
	// create the new blank bitmap
	Bitmap newb = Bitmap.createBitmap(w, h, BITMAP_CONFIG);// ����һ���µĺ�SRC���ȿ��һ���λͼ
	Canvas cv = new Canvas(newb);
	// draw src into
	cv.drawBitmap(src, 0, 0, null);// �� 0��0��꿪ʼ����src
	// draw watermark into
	cv.drawBitmap(watermark, w - ww + 5, h - wh + 5, null);// ��src�����½ǻ���ˮӡ
	// save all clip
	cv.save(Canvas.ALL_SAVE_FLAG);// ����
	// store
	cv.restore();// �洢
	return newb;
}

/**
 * ͼƬ�ϳ�
 * 
 * @return
 */
public static Bitmap potoMix(int direction, Bitmap... bitmaps) {
	if (bitmaps.length <= 0) {
		return null;
	}
	if (bitmaps.length == 1) {
		return bitmaps[0];
	}
	Bitmap newBitmap = bitmaps[0];
	// newBitmap = createBitmapForFotoMix(bitmaps[0],bitmaps[1],direction);
	for (int i = 1; i < bitmaps.length; i++) {
		newBitmap = createBitmapForFotoMix(newBitmap, bitmaps[i], direction);
	}
	return newBitmap;
}

private static Bitmap createBitmapForFotoMix(Bitmap first, Bitmap second,
		int direction) {
	if (first == null) {
		return null;
	}
	if (second == null) {
		return first;
	}
	int fw = first.getWidth();
	int fh = first.getHeight();
	int sw = second.getWidth();
	int sh = second.getHeight();
	Bitmap newBitmap = null;
	if (direction == LEFT) {
		newBitmap = Bitmap.createBitmap(fw + sw, fh > sh ? fh : sh,
				BITMAP_CONFIG);
		Canvas canvas = new Canvas(newBitmap);
		canvas.drawBitmap(first, sw, 0, null);
		canvas.drawBitmap(second, 0, 0, null);
	} else if (direction == RIGHT) {
		newBitmap = Bitmap.createBitmap(fw + sw, fh > sh ? fh : sh,
				BITMAP_CONFIG);
		Canvas canvas = new Canvas(newBitmap);
		canvas.drawBitmap(first, 0, 0, null);
		canvas.drawBitmap(second, fw, 0, null);
	} else if (direction == TOP) {
		newBitmap = Bitmap.createBitmap(sw > fw ? sw : fw, fh + sh,
				BITMAP_CONFIG);
		Canvas canvas = new Canvas(newBitmap);
		canvas.drawBitmap(first, 0, sh, null);
		canvas.drawBitmap(second, 0, 0, null);
	} else if (direction == BOTTOM) {
		newBitmap = Bitmap.createBitmap(sw > fw ? sw : fw, fh + sh,
				BITMAP_CONFIG);
		Canvas canvas = new Canvas(newBitmap);
		canvas.drawBitmap(first, 0, 0, null);
		canvas.drawBitmap(second, 0, fh, null);
	}
	return newBitmap;
}

/**
 * Drawable ת Bitmap
 * 
 * @param drawable
 * @return
 */
public static Bitmap drawableToBitmapByBD(Drawable drawable) {
	if (drawable == null)
		return null;
	BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
	return bitmapDrawable.getBitmap();
}

/**
 * Bitmap ת Drawable
 * 
 * @param bitmap
 * @return
 */
public static Drawable bitmapToDrawableByBD(Bitmap bitmap) {
	if (bitmap == null)
		return null;
	Drawable drawable = new BitmapDrawable(bitmap);
	return drawable;
}

/**
 * byte[] ת bitmap
 * 
 * @param b
 * @return
 */
public static Bitmap bytesToBimap(byte[] b) {
	if (b != null && b.length != 0) {
		return BitmapFactory.decodeByteArray(b, 0, b.length);
	} else {
		return null;
	}
}

/**
 * bitmap ת byte[]
 * 
 * @param bm
 * @return
 */
public static byte[] bitmapToBytes(Bitmap bm) {
	if (bm == null)
		return null;
	ByteArrayOutputStream baos = new ByteArrayOutputStream();
	bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
	return baos.toByteArray();
}

// /** �ļ��Ƿ���� */
// private static boolean fileExists(String path){
// if(StringUtils.isEmpty(path))
// return false;
// File file = new File(path);
// return file.exists();
// }

/** ��ȡ����ͼƬ�� */
public static String getNameFromUrl(String url) {
	if (StringUtils.isEmpty(url))
		return null;
	return url.substring(url.lastIndexOf(File.separator), url.length());
}

/**
 * ��ȡ��ɫԲ�Ǳ���ͼ
 * 
 * @param color
 *            ��ɫ
 * @param pixels
 *            Բ�ǰ뾶
 * @param width
 *            ���
 * @param hight
 *            �߶�
 * @return
 */
public synchronized static Bitmap getRoundedCornerBitmap(int color,
		int pixels, int width, int hight) {
	try {
		final Bitmap output = Bitmap.createBitmap(width, hight,
				BITMAP_CONFIG);
		Canvas canvas = new Canvas(output);
		final Paint paint = new Paint();
		final Rect rect = new Rect(0, 0, width, hight);
		final RectF rectF = new RectF(rect);
		final float roundPx = pixels;
		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);
		canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
		return output;
	} catch (OutOfMemoryError e) {
		// TODO: handle exception
		return null;
	}
}

/**
 * ����������ѹ��ͼƬ
 * 
 * @param sourceBitmap
 *            ԭʼͼƬ
 * @param toWidth
 *            ���ͼƬ�Ŀ��
 * @param toHeigh
 *            ���ͼƬ�ĸ߶�
 * @return
 */
public synchronized static Bitmap curBitmap(final Bitmap sourceBitmap,
		int toWidth, int toHeigh) {
	if (sourceBitmap == null)
		return null;
	int targetWidth = sourceBitmap.getWidth();
	int targetHeight = sourceBitmap.getHeight();
	Bitmap targetBitmap = Bitmap.createBitmap(targetWidth, targetHeight,
			BITMAP_CONFIG);

	Canvas canvas = new Canvas(targetBitmap);
	canvas.setDrawFilter(new PaintFlagsDrawFilter(Paint.ANTI_ALIAS_FLAG,
			Paint.FILTER_BITMAP_FLAG));
	Path path = new Path();

	path.addCircle(targetWidth, targetHeight / 2, 34 * toWidth
			/ (2 * targetWidth), Path.Direction.CCW);
	canvas.clipPath(path, Region.Op.DIFFERENCE);
	Paint paint = new Paint();
	paint.setAntiAlias(true);
	// ������λͼ�����˲�����
	paint.setFilterBitmap(true);
	Rect rec = new Rect(0, 0, targetWidth, targetHeight);
	canvas.drawBitmap(sourceBitmap, rec, rec, paint);
	return targetBitmap;
}

/**
 * ���ָ����dp��ݴ���ͼƬ
 * 
 * @param bm
 * @param toWidth
 * @param toHeight
 * @param context
 * @return
 */
public synchronized static Bitmap getBitmapByPix(final Bitmap bm,
		int toWidth, int toHeight) {
	if (bm == null)
		return null;
	// ���ͼƬ�Ŀ��
	try {
		int width = bm.getWidth();
		int height = bm.getHeight();
		Log.e("bm.getWidth()", bm.getWidth() + "");
		// ������Ҫ�Ĵ�С
		int newWidth = dip2px(toWidth);
		int newHeight = dip2px(toHeight);// 66dp���Լ���Ҫ�Ĵ�С���������
		// �������ű���
		float scaleWidth = ((float) newWidth) / width;
		float scaleHeight = ((float) newHeight) / height;
		// ȡ����Ҫ���ŵ�matrix����
		final Matrix matrix = new Matrix();
		matrix.postScale(scaleWidth, scaleHeight);
		// �õ��µ�ͼƬ
		return Bitmap.createBitmap(bm, 0, 0, width, height, matrix, true);
	} catch (OutOfMemoryError e) {
		// TODO: handle exception
		return null;
	}

}

/**
 * ����ֻ�ķֱ��ʴ� dp �ĵ�λ ת��Ϊ px(����)
 */
public static int dip2px(float dpValue) {
	final float scale = BaseApplication.getApplication().getResources().getDisplayMetrics().density;

	return (int) (dpValue * scale + 0.5f);
}

/**
 * ����ֻ�ķֱ��ʴ� px(����) �ĵ�λ ת��Ϊ dp
 */
public static int px2dip(float pxValue) {
	final float scale = BaseApplication.getApplication().getResources().getDisplayMetrics().density;
	return (int) (pxValue / scale + 0.5f);
}

/**
 * ͼƬ�Ŵ�
 * 
 * @param bmp
 * @param scaleX
 *            �Ŵ�Ŀ�ȱ���
 * @param scaleY
 *            �Ŵ�ĸ߶ȱ���
 * @return
 */
public synchronized static Bitmap toScale(final Bitmap bmp, float scaleX,
		float scaleY) {
	if (bmp == null)
		return null;
	try {
		int bmpWidth = bmp.getWidth();
		int bmpHeight = bmp.getHeight();
		/* ����ͼƬ�Ŵ�ı��� */
		/* �������Ҫ�Ŵ�ı��� */
		/* ����reSize���Bitmap���� */
		Matrix matrix = new Matrix();
		matrix.postScale(scaleX, scaleY);
		Bitmap resizeBmp = Bitmap.createBitmap(bmp, 0, 0, bmpWidth,
				bmpHeight, matrix, true);
		return resizeBmp;

	} catch (OutOfMemoryError e) {
		// TODO: handle exception
		return null;
	}
	// bmp.recycle();
}

/**
 * ͼƬ����
 * 
 * @param mBitmap
 * @param r
 *            ���е�����
 * @param config
 * @return
 */
public synchronized static Bitmap cutBitmap(final Bitmap mBitmap, Rect r,
		Config config) {
	if (mBitmap == null)
		return null;
	try {
		int width = r.width();
		int height = r.height();
		final Bitmap croppedImage = Bitmap.createBitmap(width, height,
				config);
		Canvas cvs = new Canvas(croppedImage);
		Rect dr = new Rect(0, 0, width, height);
		cvs.drawBitmap(mBitmap, r, dr, null);
		return croppedImage;
	} catch (OutOfMemoryError e) {
		// TODO: handle exception
		return null;
	}
}

/**
 * ��ȡר������ҳ�汳��ͼ
 * 
 * @param mBitmap
 * @param toWidth
 *            ͼƬ��ʾ���
 * @param toHigth
 *            ͼƬ�߶�
 * @return
 */
public synchronized static Bitmap getDetailBg(Bitmap mBitmap, int toWidth,
		int toHigth) {
	if (mBitmap == null)
		return null;
	try {
		int h = mBitmap.getHeight();
		int w = mBitmap.getWidth();
		float scale = ((float) toWidth) / (float) w;
		Matrix matrix = new Matrix();
		matrix.postScale(scale, scale);
		Bitmap bmp = Bitmap.createBitmap(mBitmap, 0, 0, w, h, matrix, true);
		int scaleH = bmp.getHeight();
		int scaleW = bmp.getWidth();
		Bitmap cutBmp = cutBitmap(bmp, new Rect((scaleW - toWidth) / 2,
				(scaleH - toHigth) / 2, (scaleW + toWidth) / 2,
				(scaleH + toHigth) / 2), BITMAP_CONFIG);
		Bitmap result = BoxBlurFilter(cutBmp, 12);
		recycleBitmap(bmp);
		recycleBitmap(cutBmp);
		return result;
	} catch (OutOfMemoryError e) {
		// TODO: handle exception
		return null;
	}

}

/**
 * ��ȡ���������ֱ���
 * 
 * @param mBitmap
 * @param toWidth
 *            ͼƬ���
 * @param toHigth
 *            ͼƬ�߶�
 * @return
 */
public synchronized static Bitmap getMusicBg(Bitmap mBitmap, int toWidth,
		int toHigth) {
	if (mBitmap == null)
		return null;
	try {
		int h = mBitmap.getHeight();
		int w = mBitmap.getWidth();
		float scale = ((toWidth / w) > (toHigth / h) ? (toWidth / w)
				: (toHigth / h)) + 1;

		Bitmap bmp = toScale(mBitmap, scale, scale);
		if (bmp == null)
			return null;
		h = bmp.getWidth();
		w = bmp.getWidth();
		Bitmap cutBmp = cutBitmap(bmp, new Rect((w - toWidth) / 2,
				(w - toHigth) / 2, (w + toWidth) / 2, (w + toHigth) / 2),
				BITMAP_CONFIG);
		if (cutBmp == null)
			return null;
		Bitmap result = BoxBlurFilter(cutBmp, 12);
		recycleBitmap(cutBmp);
		recycleBitmap(bmp);
		return result;
	} catch (OutOfMemoryError e) {
		// TODO: handle exception
		return null;
	}
}

/** ˮƽ����ģ��� */
private static float hRadius = 10;
/** ��ֱ����ģ��� */
private static float vRadius = 10;

/** ģ����� */
public synchronized static Bitmap BoxBlurFilter(final Bitmap bmp,
		int iterations) {
	if (bmp == null)
		return null;
	if (!FileUtil.getAvailaleDisk())
		return null;
	try {
		int width = bmp.getWidth();

		int height = bmp.getHeight();

		int[] inPixels = new int[width * height];

		int[] outPixels = new int[width * height];

		final Bitmap bitmap = Bitmap.createBitmap(width, height,
				BITMAP_CONFIG);

		bmp.getPixels(inPixels, 0, width, 0, 0, width, height);

		for (int i = 0; i < iterations; i++) {

			blur(inPixels, outPixels, width, height, hRadius);

			blur(outPixels, inPixels, height, width, vRadius);

		}

		blurFractional(inPixels, outPixels, width, height, hRadius);

		blurFractional(outPixels, inPixels, height, width, vRadius);

		bitmap.setPixels(inPixels, 0, width, 0, 0, width, height);
		return bitmap;
	} catch (OutOfMemoryError e) {
		// TODO: handle exception
		return null;
	}

}

public static void blur(int[] in, int[] out, int width, int height,

float radius) {

	int widthMinus1 = width - 1;

	int r = (int) radius;

	int tableSize = 2 * r + 1;

	int divide[] = new int[256 * tableSize];

	for (int i = 0; i < 256 * tableSize; i++)

		divide[i] = i / tableSize;

	int inIndex = 0;

	for (int y = 0; y < height; y++) {

		int outIndex = y;

		int ta = 0, tr = 0, tg = 0, tb = 0;

		for (int i = -r; i <= r; i++) {

			int rgb = in[inIndex + clamp(i, 0, width - 1)];

			ta += (rgb >> 24) & 0xff;

			tr += (rgb >> 16) & 0xff;

			tg += (rgb >> 8) & 0xff;

			tb += rgb & 0xff;

		}

		for (int x = 0; x < width; x++) {

			out[outIndex] = (divide[ta] << 24) | (divide[tr] << 16)

			| (divide[tg] << 8) | divide[tb];

			int i1 = x + r + 1;

			if (i1 > widthMinus1)

				i1 = widthMinus1;

			int i2 = x - r;

			if (i2 < 0)

				i2 = 0;

			int rgb1 = in[inIndex + i1];

			int rgb2 = in[inIndex + i2];

			ta += ((rgb1 >> 24) & 0xff) - ((rgb2 >> 24) & 0xff);

			tr += ((rgb1 & 0xff0000) - (rgb2 & 0xff0000)) >> 16;

			tg += ((rgb1 & 0xff00) - (rgb2 & 0xff00)) >> 8;

			tb += (rgb1 & 0xff) - (rgb2 & 0xff);

			outIndex += height;

		}

		inIndex += width;

	}

}

public static void blurFractional(int[] in, int[] out, int width,

int height, float radius) {

	radius -= (int) radius;

	float f = 1.0f / (1 + 2 * radius);

	int inIndex = 0;

	for (int y = 0; y < height; y++) {

		int outIndex = y;

		out[outIndex] = in[0];

		outIndex += height;

		for (int x = 1; x < width - 1; x++) {

			int i = inIndex + x;

			int rgb1 = in[i - 1];

			int rgb2 = in[i];

			int rgb3 = in[i + 1];

			int a1 = (rgb1 >> 24) & 0xff;

			int r1 = (rgb1 >> 16) & 0xff;

			int g1 = (rgb1 >> 8) & 0xff;

			int b1 = rgb1 & 0xff;

			int a2 = (rgb2 >> 24) & 0xff;

			int r2 = (rgb2 >> 16) & 0xff;

			int g2 = (rgb2 >> 8) & 0xff;

			int b2 = rgb2 & 0xff;

			int a3 = (rgb3 >> 24) & 0xff;

			int r3 = (rgb3 >> 16) & 0xff;

			int g3 = (rgb3 >> 8) & 0xff;

			int b3 = rgb3 & 0xff;

			a1 = a2 + (int) ((a1 + a3) * radius);

			r1 = r2 + (int) ((r1 + r3) * radius);

			g1 = g2 + (int) ((g1 + g3) * radius);

			b1 = b2 + (int) ((b1 + b3) * radius);

			a1 *= f;

			r1 *= f;

			g1 *= f;

			b1 *= f;

			out[outIndex] = (a1 << 24) | (r1 << 16) | (g1 << 8) | b1;

			outIndex += height;

		}

		out[outIndex] = in[width - 1];

		inIndex += width;

	}

}

public static int clamp(int x, int a, int b) {

	return (x < a) ? a : (x > b) ? b : x;

}

/**
 * ר�����鱳��ͼƬ
 * 
 * @param mBitmap
 * @param toWidth
 *            ͼƬ���
 * @param toHigth
 *            ͼƬ�߶�
 * @return
 */
public synchronized static Bitmap getSpecialDetailImage(
		final Bitmap mBitmap, int toWidth, int toHigth) {

	if (!FileUtil.getAvailaleDisk())
		return null;

	if (mBitmap == null)
		return null;

	try { // ���ģ���
		int h = mBitmap.getHeight();
		int w = mBitmap.getWidth();
		// �߶Ȳ����Ŵ󣬸߶ȹ�����
		Bitmap above;
		if (h < w) {
			float scale = (float) toHigth / (float) h;
			above = toScale(mBitmap, scale, scale);
			if (above != null) {
				w = above.getWidth();
				above = cutBitmap(above, new Rect((w - toHigth) / 2, 0,
						(w + toHigth) / 2, toHigth), BITMAP_CONFIG);
			}
		} else {
			float scale = (float) toHigth / (float) w;
			above = toScale(mBitmap, scale, scale);
			if (above != null) {
				h = above.getHeight();
				above = cutBitmap(above, new Rect(0, (h - toHigth) / 2,
						toHigth, (h + toHigth) / 2), BITMAP_CONFIG);
			}
		}

		Bitmap below = getDetailBg(mBitmap, toWidth, toHigth);
		above = sideRenderBitmap(above, 50);
		final Bitmap result = overlying(below, above);
		recycleBitmap(above);
		recycleBitmap(below);
		return result;
	} catch (OutOfMemoryError e) {
		// TODO: handle exception
		return null;
	}

}

/**
 * ��
 * 
 * @param bitmap
 * @return
 */
public synchronized static Bitmap renderBitmap(final Bitmap bitmap) {
	if (!FileUtil.getAvailaleDisk())
		return null;
	float mSize = 0.5f;
	if (bitmap == null || bitmap.isRecycled())
		return null;

	final int SIZE = 32768;
	int width = bitmap.getWidth();
	int height = bitmap.getHeight();
	int ratio = width > height ? height * SIZE / width : width * SIZE
			/ height;// �����ж���*2^15 ���ڷŴ���ʣ�֮��ı���ʹ��ʱ��Ҫ����15λ������/2^15.

	int cx = width >> 1;
	int cy = height >> 1;
	int max = cx * cx + cy * cy;
	int min = (int) (max * (1 - mSize));
	int diff = max - min;// ===>> int diff = (int)(max * mSize);

	int[] pixels = new int[width * height];
	bitmap.getPixels(pixels, 0, width, 0, 0, width, height);
	for (int i = 0; i < height; i++) {
		for (int j = 0; j < width; j++) {
			int pixel = pixels[i * width + j];
			int r = (pixel & 0x00ff0000) >> 16;
			int g = (pixel & 0x0000ff00) >> 8;
			int b = (pixel & 0x000000ff);

			int dx = cx - j;
			int dy = cy - i;

			if (width > height) {
				dx = (dx * ratio) >> 15;
			} else {
				dy = (dy * ratio) >> 15;
			}

			int dstSq = dx * dx + dy * dy;
			float v = ((float) dstSq / diff) * 255;
			r = (int) (r + v);
			g = (int) (g + v);
			b = (int) (b + v);
			r = (r > 255 ? 255 : (r < 0 ? 0 : r));
			g = (g > 255 ? 255 : (g < 0 ? 0 : g));
			b = (b > 255 ? 255 : (b < 0 ? 0 : b));
			pixels[i * width + j] = (pixel & 0xff000000) + (r << 16)
					+ (g << 8) + b;
		}
	}

	return Bitmap.createBitmap(pixels, width, height, BITMAP_CONFIG);
}

/**
 * ����ģ��
 * 
 * @param bitmap
 * @return
 */
public synchronized static Bitmap sideRenderBitmap(final Bitmap bitmap,
		int sideWidth) {
	if (!FileUtil.getAvailaleDisk())
		return null;
	if (bitmap == null)
		return null;
	int width = bitmap.getWidth();
	int height = bitmap.getHeight();

	int[] pixels = new int[width * height];
	bitmap.getPixels(pixels, 0, width, 0, 0, width, height);
	for (int i = 0; i < height; i++) {
		for (int j = 0; j < sideWidth; j++) {
			int pixel = pixels[i * width + j];
			int alp = 0;
			int r = (pixel & 0x00ff0000) >> 16;
			int g = (pixel & 0x0000ff00) >> 8;
			int b = (pixel & 0x000000ff);

			if (j < sideWidth) {
				alp = 255 * j / sideWidth;
			}
			alp = (alp > 255 ? 255 : (alp < 0 ? 0 : alp));
			pixels[i * width + j] = Color.argb(alp, r, g, b);

		}
		for (int j = width - sideWidth; j < width; j++) {
			int pixel = pixels[i * width + j];
			int alp = 0;
			int r = (pixel & 0x00ff0000) >> 16;
			int g = (pixel & 0x0000ff00) >> 8;
			int b = (pixel & 0x000000ff);
			alp = 255 * (width - j) / sideWidth;
			alp = (alp > 255 ? 255 : (alp < 0 ? 0 : alp));
			pixels[i * width + j] = Color.argb(alp, r, g, b);

		}
	}
	return Bitmap.createBitmap(pixels, width, height, BITMAP_CONFIG);
}

/**
 * ���ͼƬ
 * 
 *            ͿѻͼƬ
 * @return
 */
public synchronized static Bitmap overlying(final Bitmap below,
		final Bitmap above) {

	if (!FileUtil.getAvailaleDisk())
		return null;
	if (below == null) {
		return above;
	} else if (above == null) {
		return below;
	}
	// ���ⴴ��һ��ͼƬ
	final Bitmap newb = Bitmap.createBitmap(below.getWidth(),
			below.getHeight() + 1, BITMAP_CONFIG);// ����һ���µĺ�SRC���ȿ��һ���λͼ
	Canvas canvas = new Canvas(newb);
	canvas.drawBitmap(below, 0, 0, null);// �� 0��0��꿪ʼ����ԭͼƬsrc
	canvas.drawBitmap(above, (below.getWidth() - above.getWidth()) / 2,
			(below.getHeight() - above.getHeight()) / 2, null); // ͿѻͼƬ����ԭͼƬ�м�λ��
	canvas.save(Canvas.ALL_SAVE_FLAG);
	canvas.restore();
	// below.recycle();
	return newb;
}

/**
 * ��ȡres��ԴͼƬ
 * 
 * @param context
 * @param id
 *            ��Դid
 * @return
 */
public synchronized static Bitmap getResBitmap(Context context, int id) {
	return BitmapFactory.decodeResource(context.getResources(), id);
}

/**
 * bitmap���մ���
 * 
 * @param bitmap
 */
public static void recycleBitmap(Bitmap bitmap) {
	if (bitmap == null)
		return;
	if (!bitmap.isRecycled()) {
		bitmap.recycle(); // ����ͼƬ��ռ���ڴ�
		System.gc();
	}
	bitmap = null;
}

/**
 * convert Bitmap to byte array
 * 
 * @param b
 * @return
 */
public static byte[] bitmapToByte(Bitmap b) {
	if (b == null) {
		return null;
	}

	ByteArrayOutputStream o = new ByteArrayOutputStream();
	b.compress(Bitmap.CompressFormat.PNG, 100, o);
	return o.toByteArray();
}

/**
 * convert byte array to Bitmap
 * 
 * @param b
 * @return
 */
public static Bitmap byteToBitmap(byte[] b) {
	return (b == null || b.length == 0) ? null : BitmapFactory
			.decodeByteArray(b, 0, b.length);
}

/**
 * convert Drawable to Bitmap
 * 
 * @param d
 * @return
 */
public static Bitmap drawableToBitmap(Drawable d) {
	return d == null ? null : ((BitmapDrawable) d).getBitmap();
}

/**
 * convert Bitmap to Drawable
 * 
 * @param b
 * @return
 */
public static Drawable bitmapToDrawable(Bitmap b) {
	return b == null ? null : new BitmapDrawable(b);
}

/**
 * convert Drawable to byte array
 * 
 * @param d
 * @return
 */
public static byte[] drawableToByte(Drawable d) {
	return bitmapToByte(drawableToBitmap(d));
}

/**
 * convert byte array to Drawable
 * 
 * @param b
 * @return
 */
public static Drawable byteToDrawable(byte[] b) {
	return bitmapToDrawable(byteToBitmap(b));
}
/**
 * @param path ·��
 * @param displayWidth ��Ҫ��ʾ�Ŀ��
 * @param displayHeight ��Ҫ��ʾ�ĸ߶�
 * @return Bitmap
 */
public static Bitmap decodeBitmap_Loction(String path, int displayWidth, int displayHeight) {
        BitmapFactory.Options op = new BitmapFactory.Options();
        op.inJustDecodeBounds = true;
        // op.inJustDecodeBounds = true;��ʾ����ֻ��ȡBitmap�Ŀ�ߵ���Ϣ������ȡ���ء�
        Bitmap bmp = BitmapFactory.decodeFile(path, op); // ��ȡ�ߴ���Ϣ
        // op.outWidth��ʾ����ͼ����ʵ�Ŀ��
        // op.inSamplySize ��ʾ������С�ı���
        // op.inSamplySize = 4,��ʾ��С1/4�Ŀ�͸ߣ�1/16�����أ�android��Ϊ����Ϊ2�����ġ�
        // ��ȡ�����С
        int wRatio = (int) Math.ceil(op.outWidth / (float) displayWidth);
        int hRatio = (int) Math.ceil(op.outHeight / (float) displayHeight);
        // ����ָ����С������С��Ӧ�ı���
        if (wRatio > 1 && hRatio > 1) {
                if (wRatio > hRatio) {
                        // ���̫�?���Ǿ���С��ȵ���Ҫ�Ĵ�С��ע�⣬�߶Ⱦͻ��ø�ӵ�С��
                        op.inSampleSize = wRatio;
                } else {
                        op.inSampleSize = hRatio;
                }
        }
        op.inJustDecodeBounds = false;
        bmp = BitmapFactory.decodeFile(path, op);
        // ��ԭBitmap����һ�����ߵ�Bitmap
        return Bitmap.createScaledBitmap(bmp, displayWidth, displayHeight, true);
} 
/**
 * ����������ͼƬ����bitmap
 * @param url
 * @return
 */
public static Bitmap getBitmapByUrl(String url){
	Bitmap bitmap = null;
	
	BitmapFactory.Options options = new BitmapFactory.Options();  
	try {
		URL url2=new URL(url);
		HttpURLConnection httpURLConnection=(HttpURLConnection) url2.openConnection();
	    httpURLConnection.setReadTimeout(5000);
	    int code=httpURLConnection.getResponseCode();
	    if(code==200){
	    	bitmap=BitmapFactory.decodeStream(httpURLConnection.getInputStream());
	    }
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	return bitmap;
}
/**
 * @param path ·��
 * @param displayWidth ��Ҫ��ʾ�Ŀ��
 * @param displayHeight ��Ҫ��ʾ�ĸ߶�
 * @return Bitmap
 */
public static Bitmap decodeBitmap_src(Context context ,int path, int displayWidth, int displayHeight) {
	BitmapFactory.Options op = new BitmapFactory.Options();
	op.inJustDecodeBounds = true;
	// op.inJustDecodeBounds = true;��ʾ����ֻ��ȡBitmap�Ŀ�ߵ���Ϣ������ȡ���ء�
	Bitmap bmp = BitmapFactory.decodeResource(context.getResources(), path, op); // ��ȡ�ߴ���Ϣ
	// op.outWidth��ʾ����ͼ����ʵ�Ŀ��
	// op.inSamplySize ��ʾ������С�ı���
	// op.inSamplySize = 4,��ʾ��С1/4�Ŀ�͸ߣ�1/16�����أ�android��Ϊ����Ϊ2�����ġ�
	// ��ȡ�����С
	int wRatio = (int) Math.ceil(op.outWidth / (float) displayWidth);
	int hRatio = (int) Math.ceil(op.outHeight / (float) displayHeight);
	// ����ָ����С������С��Ӧ�ı���
	if (wRatio > 1 && hRatio > 1) {
		if (wRatio > hRatio) {
			// ���̫�?���Ǿ���С��ȵ���Ҫ�Ĵ�С��ע�⣬�߶Ⱦͻ��ø�ӵ�С��
			op.inSampleSize = wRatio;
		} else {
			op.inSampleSize = hRatio;
		}
	}
	op.inJustDecodeBounds = false;
	//bmp = BitmapFactory.decodeResource(res, id, opts)
	bmp = BitmapFactory.decodeResource(context.getResources(), path, op); // ��ȡ�ߴ���Ϣ
	// ��ԭBitmap����һ�����ߵ�Bitmap
	return Bitmap.createScaledBitmap(bmp, displayWidth, displayHeight, true);
} 
// /**
// * get input stream from network by imageurl, you need to close
// inputStream yourself
// *
// * @param imageUrl
// * @param readTimeOutMillis
// * @return
// * @see ImageUtils#getInputStreamFromUrl(String, int, boolean)
// */
// public static InputStream getInputStreamFromUrl(String imageUrl, int
// readTimeOutMillis) {
// return getInputStreamFromUrl(imageUrl, readTimeOutMillis, null);
// }

// /**
// * get input stream from network by imageurl, you need to close
// inputStream yourself
// *
// * @param imageUrl
// * @param readTimeOutMillis read time out, if less than 0, not set, in
// mills
// * @param requestProperties http request properties
// * @return
// * @throws MalformedURLException
// * @throws IOException
// */
// public static InputStream getInputStreamFromUrl(String imageUrl, int
// readTimeOutMillis,
// Map<String, String> requestProperties) {
// InputStream stream = null;
// try {
// URL url = new URL(imageUrl);
// // Log.e("ImageUtils", "getInputStreamFromUrl()--imageUrl:"+imageUrl);
// HttpURLConnection con = (HttpURLConnection)url.openConnection();
// HttpUtils.setURLConnection(requestProperties, con);
// if (readTimeOutMillis > 0) {
// con.setReadTimeout(readTimeOutMillis);
// }
// stream = con.getInputStream();
// } catch (MalformedURLException e) {
// closeInputStream(stream);
// throw new RuntimeException("MalformedURLException occurred. ", e);
// } catch (IOException e) {
// closeInputStream(stream);
// throw new RuntimeException("IOException occurred. ", e);
// }
// return stream;
// }

// /**
// * get drawable by imageUrl
// *
// * @param imageUrl
// * @param readTimeOutMillis
// * @return
// * @see ImageUtils#getDrawableFromUrl(String, int, boolean)
// */
// public static Drawable getDrawableFromUrl(String imageUrl, int
// readTimeOutMillis) {
// return getDrawableFromUrl(imageUrl, readTimeOutMillis, null);
// }

// /**
// * get drawable by imageUrl
// *
// * @param imageUrl
// * @param readTimeOutMillis read time out, if less than 0, not set, in
// mills
// * @param requestProperties http request properties
// * @return
// */
// public static Drawable getDrawableFromUrl(String imageUrl, int
// readTimeOutMillis,
// Map<String, String> requestProperties) {
// InputStream stream = getInputStreamFromUrl(imageUrl, readTimeOutMillis,
// requestProperties);
// Drawable d = Drawable.createFromStream(stream, "src");
// closeInputStream(stream);
// return d;
// }

// /**
// * get Bitmap by imageUrl
// *
// * @param imageUrl
// * @param readTimeOut
// * @return
// * @see ImageUtils#getBitmapFromUrl(String, int, boolean)
// */
// public static Bitmap getBitmapFromUrl(String imageUrl, int readTimeOut) {
// return getBitmapFromUrl(imageUrl, readTimeOut, null);
// }

// /**
// * get Bitmap by imageUrl
// *
// * @param imageUrl
// * @param requestProperties http request properties
// * @return
// */
// public static Bitmap getBitmapFromUrl(String imageUrl, int readTimeOut,
// Map<String, String> requestProperties) {
// InputStream stream = getInputStreamFromUrl(imageUrl, readTimeOut,
// requestProperties);
// Bitmap b = BitmapFactory.decodeStream(stream);
// closeInputStream(stream);
// return b;
// }

/**
 * scale image
 * 
 * @param org
 * @param newWidth
 * @param newHeight
 * @return
 */
public static Bitmap scaleImageTo(Bitmap org, int newWidth, int newHeight) {
	return scaleImage(org, (float) newWidth / org.getWidth(),
			(float) newHeight / org.getHeight());
}

/**
 * scale image
 * 
 * @param org
 * @param scaleWidth
 *            sacle of width
 * @param scaleHeight
 *            scale of height
 * @return
 */
public static Bitmap scaleImage(Bitmap org, float scaleWidth,
		float scaleHeight) {
	if (org == null) {
		return null;
	}

	Matrix matrix = new Matrix();
	matrix.postScale(scaleWidth, scaleHeight);
	if (FileUtil.getAvailaleDisk())
		return Bitmap.createBitmap(org, 0, 0, org.getWidth(),
				org.getHeight(), matrix, true);
	else
		return null;
}

/**
 * close inputStream
 * 
 * @param s
 */
private static void closeInputStream(InputStream s) {
	if (s == null) {
		return;
	}

	try {
		s.close();
	} catch (IOException e) {
		throw new RuntimeException("IOException occurred. ", e);
	}
}

/**
 * �����Դid��ȡbitmap
 * 
 * @param res
 * @param id
 * @return
 */
public static Bitmap getBitmap(Resources res, int id) {
	BitmapFactory.Options options = new BitmapFactory.Options();
	options.inSampleSize = 1;
	options.inPurgeable = true;
	// ��inPurgeable һ��ʹ��
	options.inInputShareable = true;
	// 3. ���ٶ�Aphla ͨ��
	options.inPreferredConfig = Config.RGB_565;
	try {
		// 4. inNativeAlloc ��������Ϊtrue�����Բ���ʹ�õ��ڴ��㵽VM��
		BitmapFactory.Options.class.getField("inNativeAlloc").setBoolean(
				options, true);
	} catch (IllegalArgumentException e) {
		e.printStackTrace();
	} catch (SecurityException e) {
		e.printStackTrace();
	} catch (IllegalAccessException e) {
		e.printStackTrace();
	} catch (NoSuchFieldException e) {
		e.printStackTrace();
	}
	InputStream is = res.openRawResource(id);

	return BitmapFactory.decodeStream(is, null, options);
}

/** ���uri��ȡbitmap */
public static Bitmap decodeUriAsBitmap(Uri uri) {

	Bitmap bitmap = null;
	// if(!FileUtils.getAvailaleDisk())
	// return bitmap;
	File file = null;
	try {
		BitmapFactory.Options bfOptions = new BitmapFactory.Options();
		bfOptions.inDither = false;
		bfOptions.inPurgeable = true;
		bfOptions.inInputShareable = true;
		bfOptions.inTempStorage = new byte[32 * 1024];
		file = new File(uri.getPath());
		FileInputStream fs = null;
		if (file != null)
			fs = new FileInputStream(file);
		if (fs != null)
			bitmap = BitmapFactory.decodeFileDescriptor(fs.getFD(), null,
					bfOptions);
	} catch (FileNotFoundException e) {
		e.printStackTrace();
		return null;
	} catch (IOException e) {
		e.printStackTrace();
		return null;
	}
	return bitmap;
}
	
	
}

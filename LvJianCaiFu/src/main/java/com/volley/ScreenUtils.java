package com.volley;

import java.lang.reflect.Field;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.ryan.slidefragment.base.BaseApplication;

public class ScreenUtils {
	
	private static WindowManager wm;

	public static int getWindowswidth(){
		wm = (WindowManager) BaseApplication.getApplication().getSystemService(Context.WINDOW_SERVICE);
		int width = wm.getDefaultDisplay().getWidth();
		return width;
	}
	public static int getWindowsHight(){
		wm = (WindowManager) BaseApplication.getApplication().getSystemService(Context.WINDOW_SERVICE);
		int hight = wm.getDefaultDisplay().getHeight();
		return hight;
	}
	public static Bitmap takeScreenShot(Activity activity) {
        // View������Ҫ��ͼ��View
        View view = activity.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap b1 = view.getDrawingCache();

        // ��ȡ״̬���߶�
        Rect frame = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        int statusBarHeight = frame.top;
        Log.i("TAG", "" + statusBarHeight);

        // ��ȡ��Ļ���͸�
        int width = activity.getWindowManager().getDefaultDisplay().getWidth();
        int height = activity.getWindowManager().getDefaultDisplay()
                .getHeight();
        // ȥ��������
        // Bitmap b = Bitmap.createBitmap(b1, 0, 25, 320, 455);
        Bitmap b = Bitmap.createBitmap(b1, 0, statusBarHeight, width, height
                - statusBarHeight);
        view.destroyDrawingCache();
        return b;
    }
	/**
	 * �ɹ���Toast
	 * @param context
	 * @param text
	 */
	public static void showtoast_OK(Context context,String text){
		
		Toast  toast = Toast.makeText(context,
				text, Toast.LENGTH_SHORT);
		
			   toast.setGravity(Gravity.CENTER, 0, 0);
			   
			/**
			 * Toast����ͼƬ��ʽ success_toast
			 */
			  // LinearLayout toastView = (LinearLayout) toast.getView();
			  // ImageView imageCodeProject = new ImageView(BaseApplication.getApplication());
			  // imageCodeProject.setImageResource(R.drawable.success_toast);
			  // toastView.addView(imageCodeProject, 0);
			   toast.show();
	}
	public static void showtoast_ERROR(Context context,String text){
		Toast  toast = Toast.makeText(context,
				text, Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.CENTER, 0, 0);
		 
//		LinearLayout toastView = (LinearLayout) toast.getView();
//		ImageView imageCodeProject = new ImageView(BaseApplication.getApplication());
	//	imageCodeProject.setImageResource(R.drawable.error_toast);
//		toastView.addView(imageCodeProject, 0);
		toast.show();
	}
	/**
	 * ��ȡ֪ͨ���߶�
	 */
	public static int getNotificationHigh(){
		Class<?> c = null;
		Object obj = null;
		Field field = null;
		int x = 0, sbar = 0;
		try {
		    c = Class.forName("com.android.internal.R$dimen");
		    obj = c.newInstance();
		     field = c.getField("status_bar_height");
		    x = Integer.parseInt(field.get(obj).toString());
		    sbar = BaseApplication.getApplication().getResources().getDimensionPixelSize(x);
		} catch(Exception e1) {
		    e1.printStackTrace();
		}
		return sbar;
	}



	
}


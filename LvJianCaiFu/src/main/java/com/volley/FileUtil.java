package com.volley;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.protocol.HTTP;

import android.graphics.Bitmap;
import android.os.Environment;
import android.os.StatFs;
import android.util.Log;

/**
 * <p>
 * desc: �ļ�������
 * <p>
 * Copyright: Copyright(c)Hisun 2013
 * </p>
 * 
 * @author zhanghp
 * @data 2013-6-7
 * @time ����10:49:51
 */

public class FileUtil {

	public final static String FILE_EXTENSION_SEPARATOR = ".";
	private static final String TAG = "FileUtils";

	/**
	 * �ж�SD���Ƿ����
	 * 
	 * @return
	 */
	public static boolean checkSDCard() {
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			return true;
		}
		return false;
	}

	/**
	 * ��ȡSD��·��
	 * 
	 * @return
	 */
	public static File getSDPath() {
		if (checkSDCard()) {
			File sdDir = Environment.getExternalStorageDirectory();
			return sdDir;
		}
		return new File("/sdcard");
	}

	/**
	 * �ж��ļ��Ƿ����
	 * 
	 * @param path
	 * @return
	 */
	public static boolean isExists(String path) {
		File file = new File(path);
		return isExists(file);
	}

	/**
	 * ���·����ȡ�ļ�
	 * 
	 * @param path
	 * @return
	 */
	public static File getFile(String path) {
		File file = null;
		if (path == null) {
			return null;
		} else {
			file = new File(path);
			if (!file.exists()) {
				return null;
			}
			return new File(path);
		}
	}

	/**
	 * �ж��ļ��Ƿ����
	 * 
	 * @param file
	 * @return
	 */
	public static boolean isExists(File file) {
		return file.exists();
	}

	/**
	 * ��SD��Ŀ¼�´����ļ�
	 * 
	 * @param path
	 */
	public static File createFile(String path) {
		if (!checkSDCard()) {
			return null;
		} else {
			File file = new File(getSDPath(), path);
			if (!file.exists()) {
				file.mkdir();
			} else {
				file.delete();
			}
			return file;
		}
	}

	/**
	 * �����ļ�
	 * 
	 * @param parentFile
	 * @param path
	 * @return
	 */
	public static File createFile(File parentFile, String path) {
		if (parentFile == null) {
			return createFile(path);
		} else {
			File file = new File(parentFile, path);
			if (!file.exists()) {
				file.mkdir();
			}
			return file;
		}
	}

	/**
	 * ��SD��Ŀ¼�´����ļ���
	 * 
	 * @param fileName
	 */
	public static File createDir(String fileName) {
		if (!checkSDCard()) {
			return null;
		} else {
			File file = new File(getSDPath(), fileName);
			if (!file.exists()) {
				file.mkdirs();
			}
			return file;
		}
	}

	/**
	 * �����ļ���
	 * 
	 * @param parentFile
	 * @param fileName
	 * @return
	 */
	public static File createDir(File parentFile, String fileName) {
		if (parentFile == null) {
			return createDir(fileName);
		} else {
			File file = new File(getSDPath(), fileName);
			if (!file.exists()) {
				file.mkdirs();
			}
			return file;
		}
	}

	/**
	 * �ݹ鷽ʽɾ���ļ���/�ļ���
	 * 
	 * @param file
	 */
	public static void delete(File file) {
		if (file.isFile()) {
			file.delete();
			return;
		}
		if (file.isDirectory()) {
			File[] childFiles = file.listFiles();
			if (childFiles == null || childFiles.length == 0) {
				file.delete();
				return;
			}
			for (int i = 0; i < childFiles.length; i++) {
				delete(childFiles[i]);
			}
			file.delete();
		}
	}

	/**
	 * ��ȡ�ļ���
	 * 
	 * @param filePath
	 * @return
	 */
	public static String getFileName(String filePath) {
		int start = filePath.lastIndexOf("/");
		int end = filePath.lastIndexOf(".");
		if (start != -1 && end != -1) {
			return filePath.substring(start + 1, end);
		} else {
			return null;
		}
	}

	/**
	 * ��ȡ�ļ�������� ���׺
	 * 
	 * @param path
	 * @return
	 */
	public static String getFileFullName(String path) {
		int start = path.lastIndexOf("/");
		int end = path.length();
		if (start != -1 && end != -1) {
			return path.substring(start + 1, end);
		} else {
			return null;
		}
	}

	/**
	 * �����ļ�
	 * 
	 * @param srcPath
	 * @param tarPath
	 */
	public static void photoCopy(String srcPath, String tarPath) {
		try {
			FileInputStream fi = new FileInputStream(srcPath);
			BufferedInputStream in = new BufferedInputStream(fi);
			FileOutputStream fo = new FileOutputStream(new File(tarPath));
			BufferedOutputStream out = new BufferedOutputStream(fo);

			byte[] buf = new byte[1024];
			int len = in.read(buf);// ���ļ��������������ݷ��뵽buf�����У����ص��Ƕ����ĳ���
			while (len != -1) {
				out.write(buf, 0, len);
				len = in.read(buf);
			}
			out.close();
			fo.close();
			in.close();
			fi.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * ����ֵת��Ϊ�ļ��ı�׼��ʾ
	 * 
	 * @param size
	 * @return
	 */
	public static String caculateSize(int size) {
		String result = "";
		DecimalFormat dFormat = new DecimalFormat("#0.00");
		if (size / 1024 / 1024 > 0) {
			result += dFormat.format(size / (1024 * 1024.0)) + "M";
		} else {
			result += size / 1024 + "K";
		}
		return result;
	}

	/**
	 * �ж��ļ������Ƿ�ΪͼƬ
	 * 
	 * @param filePath
	 * @return
	 */
	public static boolean checkWheatherPhoto(String filePath) {
		try {
			FileInputStream inputStream = new FileInputStream(filePath);// ��SDCARD�¶�ȡһ���ļ�
			byte[] buffer = new byte[2];
			String filecode = "";// �ļ����ʹ���
			@SuppressWarnings("unused")
			String fileType = "";// �ļ�����
			if (inputStream.read(buffer) != -1) {// ͨ���ȡ������ǰ�����ֽ����ж��ļ�����
				for (int i = 0; i < buffer.length; i++) {
					// ��ȡÿ���ֽ���0xFF��������������ȡ��λ�������ȡ��������ݲ��ǳ��ָ���
					// ��ת�����ַ�
					filecode += Integer.toString((buffer[i] & 0xFF));
				}
				switch (Integer.parseInt(filecode)) {// ���ַ���ת����Integer���������ж�
				case 7790:
					fileType = "exe";
					filePath = null;
					break;
				case 7784:
					fileType = "midi";
					filePath = null;
					break;
				case 8297:
					fileType = "rar";
					filePath = null;
					break;
				case 8075:
					fileType = "zip";
					filePath = null;
					break;
				case 255216:
					fileType = "jpg";
					break;
				case 7173:
					fileType = "gif";
					filePath = null;
					break;
				case 6677:
					fileType = "bmp";
					break;
				case 13780:
					fileType = "png";
					break;
				default:
					filePath = null;
					fileType = "unknown type: " + filecode;
				}
			}
		} catch (FileNotFoundException e) {
			filePath = null;
			e.printStackTrace();
		} catch (IOException e) {
			filePath = null;
			e.printStackTrace();
		}
		return filePath == null ? false : true;
	}

	/**
	 * �ж��ļ��Ƿ��ĵ�
	 * 
	 * @param filePath
	 * @return
	 */
	public static boolean checkWheatherDocumnet(String filePath) {
		if ((filePath.endsWith("doc") || filePath.endsWith("docx")
				|| filePath.endsWith("xls") || filePath.endsWith("xlsx")
				|| filePath.endsWith("ppt") || filePath.endsWith("pptx") || filePath
					.endsWith("pdf"))) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * InputStreamת��Ϊbyte[]
	 * 
	 * @param is
	 * @return
	 * @throws IOException
	 */
	public static byte[] getByte(InputStream is) {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			byte[] b = new byte[1024];
			int len = 0;
			while ((len = is.read(b, 0, 1024)) != -1) {
				baos.write(b, 0, len);
				baos.flush();
			}
			byte[] bytes = baos.toByteArray();
			return bytes;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * �����ļ�
	 * 
	 * @param urlStr
	 * @param path
	 * @param fileName
	 * @return
	 */
	public static int downFile(String urlStr, String path, String fileName) {
		InputStream inputStream = null;

		try {
			URL url = new URL(urlStr);// ����һ��URL����
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.connect();
			inputStream = conn.getInputStream();
			File file = createFile(path + fileName);
			byte[] data = new byte[inputStream.available()];
			FileOutputStream fos = new FileOutputStream(file);
			int length = 0;
			while ((length = inputStream.read(data)) != -1) {
				fos.write(data, 0, length);
			}
			fos.flush();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
			return -1;
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

	/*
	 * 
	 * ɾ���ļ��������ǵ����ļ����ļ���
	 * 
	 * @param fileName ��ɾ����ļ���
	 * 
	 * @return �ļ�ɾ��ɹ�����true,���򷵻�false
	 */
	public static boolean delete(String fileName) {

		File file = new File(fileName);

		if (!file.exists()) {

			System.out.println("ɾ���ļ�ʧ�ܣ�" + fileName + "�ļ�������");
			return false;

		} else {

			if (file.isFile()) {

				return deleteFile(fileName);

			} else {

				return deleteDirectory(fileName);
			}
		}
	}

	/**
	 * 
	 * ɾ��Ŀ¼���ļ��У��Լ�Ŀ¼�µ��ļ�
	 * 
	 * @param dir
	 *            ��ɾ��Ŀ¼���ļ�·��
	 * 
	 * @return Ŀ¼ɾ��ɹ�����true,���򷵻�false
	 */

	public static boolean deleteDirectory(String dir) {

		// ���dir�����ļ��ָ����β���Զ�����ļ��ָ���

		if (!dir.endsWith(File.separator)) {
			dir = dir + File.separator;
		}

		File dirFile = new File(dir);

		// ���dir��Ӧ���ļ������ڣ����߲���һ��Ŀ¼�����˳�
		if (!dirFile.exists() || !dirFile.isDirectory()) {

			System.out.println("ɾ��Ŀ¼ʧ��" + dir + "Ŀ¼�����ڣ�");
			return false;
		}

		boolean flag = true;

		// ɾ���ļ����µ������ļ�(������Ŀ¼)

		File[] files = dirFile.listFiles();
		for (int i = 0; i < files.length; i++) {

			// ɾ�����ļ�
			if (files[i].isFile()) {
				flag = deleteFile(files[i].getAbsolutePath());
				if (!flag) {
					break;
				}
			}

			// ɾ����Ŀ¼

			else {
				flag = deleteDirectory(files[i].getAbsolutePath());
				if (!flag) {
					break;
				}
			}
		}

		if (!flag) {

			System.out.println("ɾ��Ŀ¼ʧ��");
			return false;
		}
		// ɾ��ǰĿ¼
		if (dirFile.delete()) {
			System.out.println("ɾ��Ŀ¼" + dir + "�ɹ���");
			return true;
		} else {

			System.out.println("ɾ��Ŀ¼" + dir + "ʧ�ܣ�");
			return false;
		}
	}

	/**
	 * 
	 * ɾ����ļ�
	 * 
	 * @param fileName
	 *            ��ɾ���ļ����ļ���
	 * 
	 * @return �����ļ�ɾ��ɹ�����true,���򷵻�false
	 */

	public static boolean deleteFile(String fileName) {
		File file = new File(fileName);
		if (file.isFile() && file.exists()) {
			file.delete();
			System.out.println("ɾ����ļ�" + fileName + "�ɹ���");
			return true;

		} else {
			System.out.println("ɾ����ļ�" + fileName + "ʧ�ܣ�");
			return false;

		}
	}

	/**
	 * ��SD���ϴ����ļ�
	 * 
	 * @throws IOException
	 */
	public File creatSDFile(String fileName) throws IOException {
		File file = new File(fileName);
		file.createNewFile();
		return file;
	}

	/**
	 * ��SD���ϴ���Ŀ¼
	 * 
	 * @param dirName
	 */
	public File creatSDDir(String dirName) {
		File dir = new File(dirName);
		dir.mkdir();
		return dir;
	}

	public static void copyFile(File sourceFile, File targetFile)
			throws IOException {
		BufferedInputStream inBuff = null;
		BufferedOutputStream outBuff = null;
		try {
			// �½��ļ���������������л���
			inBuff = new BufferedInputStream(new FileInputStream(sourceFile));

			// �½��ļ��������������л���
			outBuff = new BufferedOutputStream(new FileOutputStream(targetFile));

			// ��������
			byte[] b = new byte[1024 * 5];
			int len;
			while ((len = inBuff.read(b)) != -1) {
				outBuff.write(b, 0, len);
			}
			// ˢ�´˻���������
			outBuff.flush();
		} finally {
			// �ر���
			if (inBuff != null)
				inBuff.close();
			if (outBuff != null)
				outBuff.close();
		}
	}

	// ɾ��ĳĿ¼�ȵ������ļ�
	public static void delAllFileOfPath(String path) {
		File file = new File(path);
		if (!file.exists()) {
			return;
		}
		if (!file.isDirectory()) {
			return;
		}
		String[] tempList = file.list();
		File temp = null;
		for (int i = 0; i < tempList.length; i++) {
			if (path.endsWith(File.separator)) {
				temp = new File(path + tempList[i]);
			} else {
				temp = new File(path + File.separator + tempList[i]);
			}
			if (temp.isFile()) {
				temp.delete();
			}
			if (temp.isDirectory()) {
				delAllFileOfPath(path + "/" + tempList[i]);
			}
		}
	}

	public static StringBuilder readFile(String filePath, String charsetName) {
		File file = new File(filePath);
		StringBuilder fileContent = new StringBuilder("");
		if (file == null || !file.isFile()) {
			return null;
		}

		BufferedReader reader = null;
		try {
			InputStreamReader is = new InputStreamReader(new FileInputStream(
					file), charsetName);
			reader = new BufferedReader(is);
			String line = null;
			while ((line = reader.readLine()) != null) {
				if (!fileContent.toString().equals("")) {
					fileContent.append("\r\n");
				}
				fileContent.append(line);
			}
			reader.close();
			return fileContent;
		} catch (IOException e) {
			throw new RuntimeException("IOException occurred. ", e);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					throw new RuntimeException("IOException occurred. ", e);
				}
			}
		}
	}

	public static boolean writeFile(String filePath, String content,
			boolean append) {
		FileWriter fileWriter = null;
		try {
			creatDirs(filePath);
			fileWriter = new FileWriter(filePath, append);
			fileWriter.write(content);
			fileWriter.close();
			return true;
		} catch (IOException e) {
			throw new RuntimeException("IOException occurred. ", e);
		} finally {
			if (fileWriter != null) {
				try {
					fileWriter.close();
				} catch (IOException e) {
					throw new RuntimeException("IOException occurred. ", e);
				}
			}
		}
	}

	public static boolean writeFile(String filePath, InputStream stream) {
		return writeFile(filePath, stream, false);
	}

	public static boolean writeFile(String filePath, InputStream stream,
			boolean append) {
		return writeFile(filePath != null ? new File(filePath) : null, stream,
				append);
	}

	public static boolean writeFile(File file, InputStream stream) {
		return writeFile(file, stream, false);
	}

	public static boolean writeFile(File file, InputStream stream,
			boolean append) {
		OutputStream o = null;
		try {
			creatDirs(file.getAbsolutePath());
			o = new FileOutputStream(file, append);
			byte data[] = new byte[1024];
			int length = -1;
			while ((length = stream.read(data)) != -1) {
				o.write(data, 0, length);
			}
			o.flush();
			return true;
		} catch (FileNotFoundException e) {
			throw new RuntimeException("FileNotFoundException occurred. ", e);
		} catch (IOException e) {
			throw new RuntimeException("IOException occurred. ", e);
		} finally {
			if (o != null) {
				try {
					o.close();
					stream.close();
				} catch (IOException e) {
					throw new RuntimeException("IOException occurred. ", e);
				}
			}
		}
	}

	/**
	 * ��һ��InputStream��������д�뵽SD����
	 */
	public static String write2SDFromInput(String path, String fileName,
			InputStream input) {
		File file = null;
		OutputStream output = null;
		try {
			creatDirs(path + "buffer_" + fileName);
			file = new File(path + "buffer_" + fileName);
			output = new FileOutputStream(file);
			byte buffer[] = new byte[4 * 1024];
			while ((input.read(buffer)) != -1) {

				output.write(buffer);
			}
			output.flush();
			transferFile(path + "buffer_" + fileName, path + fileName);
			file.delete();
			return path + fileName;
		} catch (Exception e) {
			return null;
		} finally {
			try {
				output.close();
			} catch (Exception e) {
			}
		}

	}

	/**
	 * ���ļ��е����ת��Ϊutf��8�ĸ�ʽ
	 * 
	 * @param srcFileName
	 * @param destFileName
	 * @throws IOException
	 */
	public static void transferFile(String srcFileName, String destFileName)
			throws IOException {
		String line_separator = System.getProperty("line.separator");
		FileInputStream fis = new FileInputStream(srcFileName);
		StringBuffer content = new StringBuffer();
		DataInputStream in = new DataInputStream(fis);
		BufferedReader d = new BufferedReader(new InputStreamReader(in, "GBK"));// ,
		// "UTF-8"
		String line = null;
		while ((line = d.readLine()) != null)
			content.append(line + line_separator);
		d.close();
		in.close();
		fis.close();
		Writer ow = new OutputStreamWriter(new FileOutputStream(destFileName),
				HTTP.UTF_8);
		ow.write(content.toString());
		ow.close();
	}

	/**
	 * copy file
	 * 
	 * @param sourceFilePath
	 * @param destFilePath
	 * @return
	 * @throws RuntimeException
	 *             if an error occurs while operator FileOutputStream
	 */
	public static boolean copyFile(String sourceFilePath, String destFilePath) {
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(sourceFilePath);
		} catch (FileNotFoundException e) {
			throw new RuntimeException("FileNotFoundException occurred. ", e);
		}
		return writeFile(destFilePath, inputStream);
	}

	/**
	 * read file to string list, a element of list is a line
	 * 
	 * @param filePath
	 * @param charsetName
	 *            The name of a supported {@link java.nio.charset.Charset
	 *            </code>charset<code>}
	 * @return if file not exist, return null, else return content of file
	 * @throws RuntimeException
	 *             if an error occurs while operator BufferedReader
	 */
	public static List<String> readFileToList(String filePath,
			String charsetName) {
		File file = new File(filePath);
		List<String> fileContent = new ArrayList<String>();
		if (file == null || !file.isFile()) {
			return null;
		}

		BufferedReader reader = null;
		try {
			InputStreamReader is = new InputStreamReader(new FileInputStream(
					file), charsetName);
			reader = new BufferedReader(is);
			String line = null;
			while ((line = reader.readLine()) != null) {
				fileContent.add(line);
			}
			reader.close();
			return fileContent;
		} catch (IOException e) {
			throw new RuntimeException("IOException occurred. ", e);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					throw new RuntimeException("IOException occurred. ", e);
				}
			}
		}
	}

	public static String getFileNameWithoutExtension(String filePath) {
		if (StringUtils.isEmpty(filePath)) {
			return filePath;
		}

		int extenPosi = filePath.lastIndexOf(FILE_EXTENSION_SEPARATOR);
		int filePosi = filePath.lastIndexOf(File.separator);
		if (filePosi == -1) {
			return (extenPosi == -1 ? filePath : filePath.substring(0,
					extenPosi));
		}
		if (extenPosi == -1) {
			return filePath.substring(filePosi + 1);
		}
		return (filePosi < extenPosi ? filePath.substring(filePosi + 1,
				extenPosi) : filePath.substring(filePosi + 1));
	}

	public static String getFileName2(String filePath) {
		if (StringUtils.isEmpty(filePath)) {
			return filePath;
		}

		int filePosi = filePath.lastIndexOf(File.separator);
		return (filePosi == -1) ? filePath : filePath.substring(filePosi + 1);
	}

	public static String getFolderName(String filePath) {

		if (StringUtils.isEmpty(filePath)) {
			return filePath;
		}
		int filePosi = filePath.lastIndexOf(File.separator);
		if (filePosi == -1)
			return "";// ��·��

		if (filePath.substring(filePosi, filePath.length()).indexOf(".") == -1) {
			// filePathΪ�ļ���·��
			File file = new File(filePath);
			return file.getAbsolutePath();
		} else {
			// filePathΪ�ļ�·��
			return filePath.substring(0, filePosi);
		}

		// File file = new File(filePath);
		// if(file!=null)
		// return file.getAbsolutePath();
		// else return "";

		// return (filePosi == -1) ? "" : filePath.substring(0, filePosi);
	}

	public static String getFileExtension(String filePath) {
		if (StringUtils.isBlank(filePath)) {
			return filePath;
		}

		int extenPosi = filePath.lastIndexOf(FILE_EXTENSION_SEPARATOR);
		int filePosi = filePath.lastIndexOf(File.separator);
		if (extenPosi == -1) {
			return "";
		}
		return (filePosi >= extenPosi) ? "" : filePath.substring(extenPosi + 1);
	}

	public static boolean creatDirs(String filePath) {
		boolean bret;
		String folderName = getFolderName(filePath);
		if (StringUtils.isEmpty(folderName)) {
			return false;
		}
		File folder = new File(folderName);
		if (folder.exists() && folder.isDirectory()) {
			Log.e("FileUtils", "creatDirs  folder.exists():" + "filePath:"
					+ filePath + " folderName:" + folderName);
			return true;
		} else {
			bret = folder.mkdirs();
			Log.e("FileUtils", "creatDirs:" + bret + "filePath:" + filePath);
		}
		return bret;

	}

	public static boolean createFile2(String path) {
		if (path == null || path.equals(""))
			return false;
		creatDirs(path);
		File file = new File(path);
		try {
			return file.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	// /**
	// * @param filePath
	// * @return
	// * @see #makeDirs(String)
	// */
	// public static boolean creatFolders(String filePath) {
	// return creatDirs(filePath);
	// }

	/**
	 * Indicates if this file represents a file on the underlying file system.
	 * 
	 * @param filePath
	 * @return
	 */
	public static boolean isFileExist(String filePath) {
		if (StringUtils.isBlank(filePath)) {
			return false;
		}

		File file = new File(filePath);
		return (file.exists() && file.isFile());
	}

	/**
	 * Indicates if this file represents a directory on the underlying file
	 * system.
	 * 
	 * @param directoryPath
	 * @return
	 */
	public static boolean isFolderExist(String directoryPath) {
		if (StringUtils.isBlank(directoryPath)) {
			return false;
		}

		File dire = new File(directoryPath);
		return (dire.exists() && dire.isDirectory());
	}

	/**
	 * delete file or directory
	 * <ul>
	 * <li>if path is null or empty, return true</li>
	 * <li>if path not exist, return true</li>
	 * <li>if path exist, delete recursion. return true</li>
	 * <ul>
	 * 
	 * @param path
	 * @return
	 */
	public static boolean deleteFile2(String path) {
		if (StringUtils.isBlank(path)) {
			return true;
		}

		File file = new File(path);
		if (!file.exists()) {
			return true;
		}
		if (file.isFile()) {
			return file.delete();
		}
		if (!file.isDirectory()) {
			return false;
		}
		for (File f : file.listFiles()) {
			if (f.isFile()) {
				f.delete();
			} else if (f.isDirectory()) {
				deleteFile(f.getAbsolutePath());
			}
		}
		return file.delete();
	}

	/**
	 * get file size
	 * <ul>
	 * <li>if path is null or empty, return -1</li>
	 * <li>if path exist and it is a file, return file size, else return -1</li>
	 * <ul>
	 * 
	 * @param path
	 * @return returns the length of this file in bytes. returns -1 if the file
	 *         does not exist.
	 */
	public static long getFileSize(String path) {
		if (StringUtils.isBlank(path)) {
			return -1;
		}

		File file = new File(path);
		return (file.exists() && file.isFile() ? file.length() : -1);
	}

	/**
	 * ɾ���Ӧ�ļ��������ļ��� // * // * @param dirName //
	 */
	public static void creatDir(String dirName) {
		if (isFileExist(dirName))
			deleteFile(dirName);
		if (!isFolderExist(dirName)) {
			creatDirs(dirName);
		}
	}

	/**
	 * �ж�sd���Ƿ���ʣ��ռ�
	 * 
	 * @return true����ʣ��ռ� false��ʣ��ռ䲻��
	 */
	public static boolean getAvailaleDisk() {

		File path = Environment.getExternalStorageDirectory(); // 取得sdcard文件路径
		StatFs stat = new StatFs(path.getPath());
		long blockSize = stat.getBlockSize();
		long availableBlocks = stat.getAvailableBlocks();
		long size = (availableBlocks * blockSize) / 1024 / 1024;// MIB单位

		return size > 20;
	}

	/**
	 * �ж�sd���Ƿ����
	 * 
	 * @return true������ false��������
	 */
	public static boolean isHaveSDCard() {
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			return true;
		} else {
			return false;
		}
	}

	public static String getLocalFileSize(int size) {
		String result = "";
		int m = size / 1024 / 1024;
		int k = size / 1024;
		if (m > 0) {
			result = m + "M";
		} else if (k > 0) {
			result = k + "K";
		} else {
			result = size + "";
		}
		return result;
	}

	/**
	 * ��ͼƬ�浽SD����
	 * 
	 * @param path
	 * @param bitmap
	 * @param bCover
	 *            �Ƿ񸲸��Ѵ��ڵ�
	 * @return
	 */
	public static String savePictrueToSDCard(String path, Bitmap bitmap,
			boolean bCover) {

		if (bitmap == null) {
			Log.e("FileUtils", "savePictrueToSDCard path:" + path
					+ "bitmap == null");
			return "";
		}
		File file = new File(path);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			if (!bCover)// �����ǣ�����·��
				return path;
		}
		BufferedOutputStream bos;
		try {
			bos = new BufferedOutputStream(new FileOutputStream(path));
			if (bos == null)
				return null;
			// ����ѹ��ת������
			bitmap.compress(Bitmap.CompressFormat.JPEG, 80, bos);
			// ����flush()����������BufferStream
			bos.flush();
			// ����OutputStream
			bos.close();
			return path;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	
	
	//////ɾ���ļ�
	
	
	

}


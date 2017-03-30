package com.volley;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.reflect.Field;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import org.json.JSONObject;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;

public class DeviceUtil {

	private static String TAG = DeviceUtil.class.getName();

	public static String getOSVersion() {
		return "android_" + Build.VERSION.RELEASE;
	}

	public static String getDevice() {
		return Build.MODEL;
	}

	// 获取系统imei
	public static String getIMEI(Context context) {
		TelephonyManager telemgr = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);

		return telemgr.getDeviceId();
	}

	// 获取系统imsi
	public static String getIMSI(Context context) {
		try {
			TelephonyManager telemgr = (TelephonyManager) context
					.getSystemService(Context.TELEPHONY_SERVICE);
			return telemgr.getSubscriberId();
		} catch (SecurityException e) {
			// TODO: handle exception
			// LogUtil.print(5,TAG,"getIMSI"+e.getMessage());
			return null;
		}
	}

	/**
	 * 判断是否存在sim卡
	 * 
	 * @return
	 */
	public static boolean isHaveSim(Context context) {
		String sim = getIMSI(context);
		return sim != null && sim.length() > 1;
	}

	public static int getSIMState(Context context) {
		TelephonyManager telemgr = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		return telemgr.getSimState();
	}

	/**
	 * 获取版本号
	 * 
	 * @return
	 */
	public static String getAppVersionName(Context context) {
		String version = "";
		try {
			PackageInfo packageInfo = context.getPackageManager()
					.getPackageInfo(context.getPackageName(), 0);
			version = packageInfo.versionName;
		} catch (NameNotFoundException e) {
			Log.e(TAG, e.getMessage());
		}
		return version;
	}

	public static int getAppVersionCode(Context context) {
		int code = 1;
		try {
			PackageInfo packageInfo = context.getPackageManager()
					.getPackageInfo(context.getPackageName(), 0);
			code = packageInfo.versionCode;
		} catch (NameNotFoundException e) {
			Log.e(TAG, e.getMessage());
		}
		return code;
	}

	public static String getManufacturer() {
		return Build.MANUFACTURER;
	}

	/**
	 * 检查sd卡是否存在
	 * 
	 * @return
	 */
	public static boolean checkSDCard() {
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED))
			return true;
		return false;
	}

	// 获取sd卡可用空间 MIB单位
	public static long getAvailaleSize() {

		File path = Environment.getExternalStorageDirectory(); // 取得sdcard文件路径
		StatFs stat = new StatFs(path.getPath());
		long blockSize = stat.getBlockSize();
		long availableBlocks = stat.getAvailableBlocks();
		// return availableBlocks * blockSize;
		// (availableBlocks * blockSize)/1024 KIB 单位
		long size = (availableBlocks * blockSize) / 1024 / 1024;// MIB单位
		Log.e(TAG, "getAvailaleSize:" + size);
		return size;
	}

	// sd卡总空间
	public static long getAllSize() {

		File path = Environment.getExternalStorageDirectory();
		StatFs stat = new StatFs(path.getPath());
		long blockSize = stat.getBlockSize();
		long availableBlocks = stat.getBlockCount();
		// return availableBlocks * blockSize;
		return (availableBlocks * blockSize) / 1024 / 1024;// MIB单位
	}

	public static String getScreenPixel(Context context) {
		DisplayMetrics dm = new DisplayMetrics();
		WindowManager windowMgr = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		windowMgr.getDefaultDisplay().getMetrics(dm);
		return dm.widthPixels + "x" + dm.heightPixels;
	}

	public static int getScreenWidth(Context context) {
		WindowManager wm = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		android.view.Display display = wm.getDefaultDisplay();
		return display.getWidth();
	}

	public static int getScreenHeight(Context context) {
		WindowManager wm = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		android.view.Display display = wm.getDefaultDisplay();
		return display.getHeight();
	}

	public static int dp2px(Context context, int dp) {

		return (int) (context.getResources().getDisplayMetrics().density * dp + 0.5f);

	}

	public static int px2dp(Context context, int px) {

		return (int) (px/context.getResources().getDisplayMetrics().density + 0.5f);

	}
	
	public static String getMobileInfo() { 
	    //StringBuffer sb = new StringBuffer(); 
	    JSONObject mbInfo = new JSONObject(); 
	        
	    //通过反射获取用户硬件信息 
	    try { 
	    
	        Field[] fields = Build.class.getDeclaredFields(); 
	        for (Field field : fields) { 
	            // 暴力反射,获取私有信息 
	            field.setAccessible(true); 
	            String name = field.getName(); 
	            String value = field.get(null).toString(); 
	            //sb.append(name + "=" + value); 
	            //sb.append("n"); 
	            mbInfo.put(name, value); 
	        } 
	    } catch (Exception e) { 
	        e.printStackTrace(); 
	    } 
	        
	    //return sb.toString(); 
	    return mbInfo.toString(); 
	} 
	    
	    
	static public String getCpuString(){ 
	    if(Build.CPU_ABI.equalsIgnoreCase("x86")){ 
	        return "Intel"; 
	    } 
	        
	    String strInfo = ""; 
	    try
	    { 
	        byte[] bs = new byte[1024]; 
	        RandomAccessFile reader = new RandomAccessFile("/proc/cpuinfo", "r"); 
	        reader.read(bs); 
	        String ret = new String(bs); 
	        int index = ret.indexOf(0); 
	        if(index != -1) { 
	            strInfo = ret.substring(0, index); 
	        } else { 
	            strInfo = ret; 
	        } 
	    } 
	    catch (IOException ex){ 
	        ex.printStackTrace(); 
	    } 
	        
	    return strInfo; 
	} 
	    
	static public String getCpuType(){ 
	    String strInfo = getCpuString(); 
	    String strType = null; 
	        
	    if (strInfo.contains("ARMv5")) { 
	        strType = "armv5"; 
	    } else if (strInfo.contains("ARMv6")) { 
	        strType = "armv6"; 
	    } else if (strInfo.contains("ARMv7")) { 
	        strType = "armv7"; 
	    } else if (strInfo.contains("Intel")){ 
	        strType = "x86"; 
	    }else{ 
	        strType = "unknown"; 
	        return strType; 
	    } 
	        
	    if (strInfo.contains("neon")) { 
	        strType += "_neon"; 
	    }else if (strInfo.contains("vfpv3")) { 
	        strType += "_vfpv3"; 
	    }else if (strInfo.contains(" vfp")) { 
	        strType += "_vfp"; 
	    }else{ 
	        strType += "_none"; 
	    } 
	        
	    return strType; 
	} 
	    
	    

	
	    
	    
	private final static String kCpuInfoMaxFreqFilePath = "/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq"; 
	private static int getMaxCpuFreq() { 
	    int result = 0; 
	    FileReader fr = null; 
	    BufferedReader br = null; 
	    try { 
	        fr = new FileReader(kCpuInfoMaxFreqFilePath); 
	        br = new BufferedReader(fr); 
	        String text = br.readLine(); 
	        if (text != null) { 
	            result = Integer.parseInt(text.trim()); 
	        } 
	    } catch (FileNotFoundException e) { 
	        e.printStackTrace(); 
	    } catch (IOException e) { 
	        e.printStackTrace(); 
	    } finally { 
	        if (fr != null) 
	            try { 
	                fr.close(); 
	            } catch (IOException e) { 
	                // TODO Auto-generated catch block 
	                e.printStackTrace(); 
	            } 
	        if (br != null) 
	            try { 
	                br.close(); 
	            } catch (IOException e) { 
	                // TODO Auto-generated catch block 
	                e.printStackTrace(); 
	            } 
	    } 
	    
	    return result; 
	} 
	        
	public static class CPUInfo{ 
	    public CPUInfo(){ 
	            
	    } 
	        
	    public static final int CPU_TYPE_UNKNOWN            =   0x00000000; 
	    public static final int CPU_TYPE_ARMV5TE            =   0x00000001; 
	    public static final int CPU_TYPE_ARMV6              =   0x00000010; 
	    public static final int CPU_TYPE_ARMV7              =   0x00000100; 
	        
	    public static final int CPU_FEATURE_UNKNOWS         =   0x00000000; 
	    public static final int CPU_FEATURE_VFP             =   0x00000001; 
	    public static final int CPU_FEATURE_VFPV3           =   0x00000010; 
	    public static final int CPU_FEATURE_NEON            =   0x00000100; 
	        
	    public int mCPUType; 
	    public int mCPUCount; 
	    public int mCPUFeature;      
	    public double mBogoMips; 
	    public long mCPUMaxFreq; 
	} 
	    
	/**
	 * 
	 * @param cpuInfo
	 * @return
	 * @hide
	 */
	private static CPUInfo parseCPUInfo(String cpuInfo) { 
	    if (cpuInfo == null || "".equals(cpuInfo)) { 
	        return null; 
	    } 
	    
	    CPUInfo ci = new CPUInfo(); 
	    ci.mCPUType = CPUInfo.CPU_TYPE_UNKNOWN; 
	    ci.mCPUFeature = CPUInfo.CPU_FEATURE_UNKNOWS; 
	    ci.mCPUCount = 1; 
	    ci.mBogoMips = 0; 
	    
	    if (cpuInfo.contains("ARMv5")) { 
	        ci.mCPUType = CPUInfo.CPU_TYPE_ARMV5TE; 
	    } else if (cpuInfo.contains("ARMv6")) { 
	        ci.mCPUType = CPUInfo.CPU_TYPE_ARMV6; 
	    } else if (cpuInfo.contains("ARMv7")) { 
	        ci.mCPUType = CPUInfo.CPU_TYPE_ARMV7; 
	    } 
	    
	    if (cpuInfo.contains("neon")) { 
	        ci.mCPUFeature |= CPUInfo.CPU_FEATURE_NEON; 
	    } 
	    
	    if (cpuInfo.contains("vfpv3")) { 
	        ci.mCPUFeature |= CPUInfo.CPU_FEATURE_VFPV3; 
	    } 
	    
	    if (cpuInfo.contains(" vfp")) { 
	        ci.mCPUFeature |= CPUInfo.CPU_FEATURE_VFP; 
	    } 
	    
	    String[] items = cpuInfo.split("n"); 
	    
	    for (String item : items) { 
	        if (item.contains("CPU variant")) { 
	            int index = item.indexOf(": "); 
	            if (index >= 0) { 
	                String value = item.substring(index + 2); 
	                try { 
	                    ci.mCPUCount = Integer.decode(value); 
	                    ci.mCPUCount = ci.mCPUCount == 0 ? 1 : ci.mCPUCount; 
	                } catch (NumberFormatException e) { 
	                    ci.mCPUCount = 1; 
	                } 
	            } 
	        } else if (item.contains("BogoMIPS")) { 
	            int index = item.indexOf(": "); 
	            if (index >= 0) { 
	                String value = item.substring(index + 2); 
	            } 
	        } 
	    } 
	        
	    return ci; 
	} 
	    
	    
	    
	/**
	 * 获取设备内存大小值
	 * @return 内存大小,单位MB
	 */
//	public static long getTotalMemory() {  
//	    String str1 = "/proc/meminfo"; 
//	    String str2;         
//	    String[] arrayOfString; 
//	    long initial_memory = 0; 
//	    try { 
//	        FileReader localFileReader = new FileReader(str1); 
//	        BufferedReader localBufferedReader = new BufferedReader(localFileReader, 8192); 
//	        str2 = localBufferedReader.readLine(); 
//	        if (str2 != null) { 
//	            arrayOfString = str2.split("\s\+"); 
//	            initial_memory = Integer.valueOf(arrayOfString[1]).intValue()/1024; 
//	        } 
//	        localBufferedReader.close(); 
//	        return initial_memory; 
//	    }  
//	    catch (IOException e)  
//	    {        
//	        return -1; 
//	    } 
//	} 
//	    

	
	
	public static CPUInfo getCPUInfo() { 
	    String strInfo = null; 
	    try
	    { 
	        byte[] bs = new byte[1024]; 
	        RandomAccessFile reader = new RandomAccessFile("/proc/cpuinfo", "r"); 
	        reader.read(bs); 
	        String ret = new String(bs); 
	        int index = ret.indexOf(0); 
	        if(index != -1) { 
	            strInfo = ret.substring(0, index); 
	        } else { 
	            strInfo = ret; 
	        } 
	    } 
	    catch (IOException ex) 
	    { 
	        strInfo = ""; 
	        ex.printStackTrace(); 
	    } 
	        
	    CPUInfo info = parseCPUInfo(strInfo); 
	    info.mCPUMaxFreq = getMaxCpuFreq(); 
	                
	    return info; 
	} 
	    
	/**
	 * 获取android CPU类型
	 * 
	 * @return String CPU类型
	 */
	public static String getCpuModel(){ 
	    String cpu_model = ""; 
	    
	    CPUInfo in = getCPUInfo(); 
	              
	    if ((in.mCPUType & CPUInfo.CPU_TYPE_ARMV5TE) == CPUInfo.CPU_TYPE_ARMV5TE) 
	        cpu_model="armv5"; 
	    else if ((in.mCPUType & CPUInfo.CPU_TYPE_ARMV6) == CPUInfo.CPU_TYPE_ARMV6) 
	        cpu_model="armv6"; 
	    else if ((in.mCPUType & CPUInfo.CPU_TYPE_ARMV7) == CPUInfo.CPU_TYPE_ARMV7) 
	        cpu_model="armv7"; 
	    else
	        cpu_model="unknown"; 
	    return cpu_model; 
	} 
	    
	/**
	 * 获取android CPU特性
	 * 
	 * @return String CPU特性
	 */
	public static String getCpuFeature(){ 
	    String cpu_feature = ""; 
	            
	    CPUInfo in = getCPUInfo(); 
	                
	    if ((in.mCPUFeature & CPUInfo.CPU_FEATURE_NEON ) == CPUInfo.CPU_FEATURE_NEON) 
	        cpu_feature="neon"; 
	    else if ((in.mCPUFeature & CPUInfo.CPU_FEATURE_VFP ) == CPUInfo.CPU_FEATURE_VFP) 
	        cpu_feature="vfp"; 
	    else if ((in.mCPUFeature & CPUInfo.CPU_FEATURE_VFPV3 ) == CPUInfo.CPU_FEATURE_VFPV3) 
	        cpu_feature="vfpv3"; 
	    else
	        cpu_feature="unknown";  
	    return cpu_feature; 
	} 
	    
	/**
	 * 获取ip地址
	 * 
	 * @param mContext  Context
	 * @return ip地址字符串
	 */
	public static String getIpAddress(Context mContext) { 
	    String ipAddress = null; 
	    try { 
	        for (Enumeration<NetworkInterface> en = NetworkInterface 
	                .getNetworkInterfaces(); en.hasMoreElements();) { 
	            NetworkInterface intf = en.nextElement(); 
	            for (Enumeration<InetAddress> enumIpAddr = intf 
	                    .getInetAddresses(); enumIpAddr.hasMoreElements();) { 
	                InetAddress inetAddress = enumIpAddr.nextElement(); 
	                if (!inetAddress.isLoopbackAddress()) { 
	                    ipAddress = inetAddress.getHostAddress().toString();  
	                } 
	            } 
	        } 
	    } catch (SocketException ex) { 
	        return null; 
	    } 
	    
	    return ipAddress; 
	}
	/**
     * 获取手机mac地址
     * 错误返回12个0
     */
    public static String getMacAddress(Context context) {
        // 获取mac地址：
        String macAddress = "000000000000";
        try {
            WifiManager wifiMgr = (WifiManager) context
                    .getSystemService(Context.WIFI_SERVICE);
            WifiInfo info = (null == wifiMgr ? null : wifiMgr
                    .getConnectionInfo());
            if (null != info) {
                if (!TextUtils.isEmpty(info.getMacAddress()))
                    macAddress = info.getMacAddress().replace(":", "");
                else
                    return macAddress;
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return macAddress;
        }
        return macAddress;
    }
}

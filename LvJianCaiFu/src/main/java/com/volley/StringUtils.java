package com.volley;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.protocol.HTTP;

public class StringUtils {

    public static boolean isBlank(String str) {
        return (str == null || str.trim().length() == 0);
    }

    public static boolean isEmpty(String str) {
        return (str == null || str.length() == 0);
    }
    public static String nullStrToEmpty(String str) {
        return (str == null ? "" : str);
    }

    public static String capitalizeFirstLetter(String str) {
        if (isEmpty(str)) {
            return str;
        }

        char c = str.charAt(0);
        return (!Character.isLetter(c) || Character.isUpperCase(c)) ? str
				: new StringBuilder(str.length())
						.append(Character.toUpperCase(c))
						.append(str.substring(1)).toString();
    }

    public static String utf8Encode(String str) {
        if (!isEmpty(str) && str.getBytes().length != str.length()) {
            try {
                return URLEncoder.encode(str,  HTTP.UTF_8);
            } catch (UnsupportedEncodingException e) {
				throw new RuntimeException(
						"UnsupportedEncodingException occurred. ", e);
            }
        }
        return str;
    }

    public static String utf8Encode(String str, String defultReturn) {
        if (!isEmpty(str) && str.getBytes().length != str.length()) {
            try {
                return URLEncoder.encode(str,  HTTP.UTF_8);
            } catch (UnsupportedEncodingException e) {
                return defultReturn;
            }
        }
        return str;
    }

    public static String getHrefInnerHtml(String href) {
        if (isEmpty(href)) {
            return "";
        }

        String hrefReg = ".*<[\\s]*a[\\s]*.*>(.+?)<[\\s]*/a[\\s]*>.*";
		Pattern hrefPattern = Pattern
				.compile(hrefReg, Pattern.CASE_INSENSITIVE);
        Matcher hrefMatcher = hrefPattern.matcher(href);
        if (hrefMatcher.matches()) {
            return hrefMatcher.group(1);
        }
        return href;
    }

    public static String htmlEscapeCharsToString(String source) {
		return StringUtils.isEmpty(source) ? source : source
				.replaceAll("&lt;", "<").replaceAll("&gt;", ">")
                                                            .replaceAll("&amp;", "&").replaceAll("&quot;", "\"");
    }

    public static String fullWidthToHalfWidth(String s) {
        if (isEmpty(s)) {
            return s;
        }

        char[] source = s.toCharArray();
        for (int i = 0; i < source.length; i++) {
            if (source[i] == 12288) {
                source[i] = ' ';
                // } else if (source[i] == 12290) {
                // source[i] = '.';
            } else if (source[i] >= 65281 && source[i] <= 65374) {
                source[i] = (char)(source[i] - 65248);
            } else {
                source[i] = source[i];
            }
        }
        return new String(source);
    }

    public static String halfWidthToFullWidth(String s) {
        if (isEmpty(s)) {
            return s;
        }

        char[] source = s.toCharArray();
        for (int i = 0; i < source.length; i++) {
            if (source[i] == ' ') {
                source[i] = (char)12288;
                // } else if (source[i] == '.') {
                // source[i] = (char)12290;
            } else if (source[i] >= 33 && source[i] <= 126) {
                source[i] = (char)(source[i] + 65248);
            } else {
                source[i] = source[i];
            }
        }
        return new String(source);
    }
    
    public static boolean isNumeric(String str){
    	Pattern pattern = Pattern.compile("[0-9]*");
    	Matcher isNum = pattern.matcher(str);
    	if(!isNum.matches()){
    		return false;
    	}
    	return true;
    }
    
	public static int formatStrToInt(String str) {
		if (str != null && !"".equals(str)) {
			if (isNumeric(str)) {
				return Integer.valueOf(str);
			} else {
				return 0;
			}
		} else {
			return 0;
		}
	}

	public static boolean isMobile(String str) {
		Pattern p = null;
		Matcher m = null;
		boolean isMobile = false;
		p = Pattern.compile("^[1][3,4,5,8][0-9]{9}$"); // 验证手机号
		m = p.matcher(str);
		isMobile = m.matches();
		return isMobile;
	}

	public static boolean isPhone(String str) {
		Pattern p1 = null, p2 = null;
		Matcher m = null;
		boolean isPhone = false;
		p1 = Pattern.compile("^[0][1-9]{2,3}-[0-9]{5,10}$"); // 验证带区号的
		p2 = Pattern.compile("^[1-9]{1}[0-9]{5,8}$"); // 验证没有区号的
		if (str.length() > 9) {
			m = p1.matcher(str);
			isPhone = m.matches();
		} else {
			m = p2.matcher(str);
			isPhone = m.matches();
		}
		return isPhone;
	}
}

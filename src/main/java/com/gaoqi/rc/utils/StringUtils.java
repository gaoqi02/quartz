package com.gaoqi.rc.utils;

/**
 * Created by qigao212074 on 2016/9/14.
 */
public class StringUtils {

    /**
     * Trim
     *
     * @param str
     * @return String
     */
    public static String trimToNull(String str){
        if(str == null){
            return null;
        }
        str = str.trim();
        if("".equals(str)){
            return null;
        }
        return str;
    }

    /**
     * 判断是否为空
     *
     * @param str
     * @return boolean
     */
    public static boolean trimIsNull(String str){
        if(trimToNull(str) == null){
            return true;
        }
        return false;
    }

}

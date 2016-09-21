package com.gaoqi.rc.utils;

import java.lang.reflect.Method;

/**
 * Created by qigao212074 on 2016/9/12.
 */
public class ReflectUtil {

    /**
     * 检查某个对象下是否有该方法
     * @param target
     * @param methodName
     * @return
     */
    public static boolean checkExist(Object target, String methodName) {
        if (target == null || methodName.equals("")) {
            return false;
        }
        return checkExist(target.getClass(), methodName);
    }

    public static boolean checkExist(Class<?> clazz, String methodName){
        if (clazz == null || methodName.equals("")) {
            return false;
        }
        boolean isExist = false;
        Method[] methodArray = clazz.getDeclaredMethods();
        for (Method method : methodArray) {
            if (method.getName().equals(methodName)) {
                isExist = true;
                //发现即完成
                break;
            }
        }
        return isExist;
    }

}

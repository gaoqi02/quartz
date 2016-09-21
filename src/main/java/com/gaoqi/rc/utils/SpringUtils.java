package com.gaoqi.rc.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;


/**
 * Created by qigao212074 on 2016/9/6.
 */
public class SpringUtils implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    @SuppressWarnings("static-access")
    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        this.applicationContext = applicationContext;

    }

    public static Object getBean(String name) throws BeansException {
        if (containsBean(name)) {
            return applicationContext.getBean(name);
        }
        return null;
    }

    /**
     * 是否包含Bean
     *
     * @param name
     * @return boolean
     */
    public static boolean containsBean(String name) {
        if (applicationContext.containsBean(name)) {
            return true;
        }
        return false;
    }
}
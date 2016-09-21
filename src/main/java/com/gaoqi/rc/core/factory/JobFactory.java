package com.gaoqi.rc.core.factory;

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.scheduling.quartz.AdaptableJobFactory;

/**
 * job 是由 quartz启动时 instance的
 * service dao 是由spring 启动时instance的
 * 两者并不属于同一个容器，所以使用这个Factory 去继承AdaptableJobFactory，让job中可以调用service
 *
 * Created by qigao212074 on 2016/9/9.
 */
public class JobFactory extends AdaptableJobFactory {

    @Autowired
    private AutowireCapableBeanFactory capableBeanFactory;

    @Override
    protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
        //调用父类的方法
        Object jobInstance = super.createJobInstance(bundle);
        capableBeanFactory.autowireBean(jobInstance);
        return jobInstance;
    }
}

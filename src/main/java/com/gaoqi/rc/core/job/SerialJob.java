package com.gaoqi.rc.core.job;

import org.quartz.DisallowConcurrentExecution;

/**
 * 串行Job extends BaseJob
 *
 * Created by qigao212074 on 2016/9/9.
 */
@DisallowConcurrentExecution
public class SerialJob extends BaseJob {

}

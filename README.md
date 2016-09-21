# quartz

quartz 2.1+  +  spring  的组合
在其中主要代码位于core和utils中的TriggerUtils等

listener定义了任务触发的监听器。用于判断任务是否需要执行，以及执行过程中的日志
job  BaseJob  可串行job 用于getSpringBean 反射执行执行文件
     SerialJob 非可串行job 
trigger  目前使用SimpleTrigger  用于定义某个时间开始执行  循环次数 循环间隔 结束时间等
也定义了crontrigger

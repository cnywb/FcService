package com.fc.listener;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

public class QuartzManager {

	private static SchedulerFactory gSchedulerFactory = new StdSchedulerFactory();  
    private static String JOB_GROUP_NAME = "EXTJWEB_JOBGROUP_NAME";  
    private static String TRIGGER_GROUP_NAME = "EXTJWEB_TRIGGERGROUP_NAME";  
    
    /** 
    * 添加一个定时任务，使用默认的任务组名，触发器名，触发器组名 
    * 
    * @param jobName 
    *            任务名 
    * @param jobClass 
    *            任务 
    * @param time 
    *            时间设置，参考quartz说明文档 
    * @throws SchedulerException 
    * @throws ParseException 
    */  
   public static void addJob(String jobName, String jobClass, String time) {  
       try {  
           Scheduler sched = gSchedulerFactory.getScheduler();  
           JobDetail jobDetail = new JobDetail(jobName, JOB_GROUP_NAME, Class.forName(jobClass));// 任务名，任务组，任务执行类  
           // 触发器  
           CronTrigger trigger = new CronTrigger(jobName, TRIGGER_GROUP_NAME);// 触发器名,触发器组  
           trigger.setCronExpression(time);// 触发器时间设定  
           sched.scheduleJob(jobDetail, trigger);  
           // 启动  
           if (!sched.isShutdown()){  
               sched.start();  
           }  
       } catch (Exception e) {  
           e.printStackTrace();  
           throw new RuntimeException(e);  
       }  
   }  
   
}

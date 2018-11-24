package com.fc.ss.listener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.fc.bean.DmsToLogBean;
import com.fc.service.SubmitCallCenterService;
import com.fc.ss.bean.FcBeforeOBBean;
import com.fc.ss.bean.FcCampaignChannelBean;
import com.fc.ss.bean.FcCampaignMasterBean;
import com.fc.ss.service.BeforeOBService;
import com.fc.ss.service.CampaignChannelService;
import com.fc.ss.service.CampaignMasterService;
import com.fc.ss.service.SMSService;

/**
 * soundsun
 * @author 
 * 定时任务工作类
 */
public class SAPUploadTask implements Job{
	private static Logger log = Logger.getLogger(SAPUploadTask.class);
	
	public static Map<String, DmsToLogBean> toLogBeanMap = new HashMap<String, DmsToLogBean>();
	SubmitCallCenterService subService = new SubmitCallCenterService();
	BeforeOBService beforeOBService = new BeforeOBService();
	CampaignMasterService campaignMasterService = new CampaignMasterService();
	CampaignChannelService campaignChannelService = new CampaignChannelService();
	SMSService smsService = new SMSService();
	public static final DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:");
	
	public void execute(JobExecutionContext arg0)
			throws JobExecutionException {
				//不再通过时间来判断需要生成文件的数据		通过数据库表中的特定标识符来生成新的数据
				Calendar calendar = Calendar.getInstance();
				calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY));
				String startDate = format.format(calendar.getTime())+"00:00";
				String endDate=format.format(calendar.getTime())+"59:59";
				try {
					beforeOBService.GenerateText(startDate, endDate);
					List<FcCampaignChannelBean> fcCampaignChannelList=campaignChannelService.GenerateText(startDate, endDate);
					List<FcCampaignMasterBean> fcCampaignMasterList=campaignMasterService.GenerateText(startDate, endDate);
//					smsService.GenerateText(startDate, endDate);
					
					//文件全部生成后再进行update操作 以防止表被占用导致update不成功 后续文件无法生存
					campaignMasterService.updateBean(fcCampaignMasterList);
					campaignChannelService.updateBean(fcCampaignChannelList);
					//因FC_SS_BEFOREOB表经常被锁，所以不用这个方法  新增一张FC_SS_IDS表 存放FC_SS_BEFOREOB的ID 用以做对比。
//					beforeOBService.updateBean(fcBeforeObList);
				} catch (Exception e) {
				    e.printStackTrace();
				}
			}
	
	/**
	 * 获取DMS数据相关日志表实例化相关类
	 * 
	 * @return
	 */
	public static DmsToLogBean createDmsToLogBean(String readType,
			String readDate) {
		DmsToLogBean logBean = new DmsToLogBean();
		logBean.setReadType(readType);
		logBean.setReadDate(readDate);
		return logBean;
	}
	
	public static void main(String[] args) {
	    Calendar calendar = Calendar.getInstance();
	    calendar.set(11, calendar.get(11));
	    String startDate = format.format(calendar.getTime()) + "00:00";
	    String endDate = format.format(calendar.getTime()) + "59:59";
	    System.out.println(startDate);
	    System.out.println(endDate);
	  }
}

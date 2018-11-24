package com.fc.ss.listener;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.fc.ss.dao.db.AemDataDao;
import com.fc.ss.util.CommonFunction;

public class AEMTask implements Job{
	private static Logger log = Logger.getLogger(AEMTask.class);
	
	private static CommonFunction cf = new CommonFunction();
	public static final DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH");
	
	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		log.info("AEM监控任务开启!");
		//是否开启Debug模式
		boolean bool=Boolean.parseBoolean(cf.getSapMailPropertiesValue("aem.mail.debug"));
		//从配置文件中获取两个时间段
		String am=cf.getSapMailPropertiesValue("aem.mail.am");
		String pm=cf.getSapMailPropertiesValue("aem.mail.pm");
		//从配置文件中获取收件人以及抄送人
		String to=cf.getSapMailPropertiesValue("sap.mail.to_address");
		String cc=cf.getSapMailPropertiesValue("sap.mail.cc_address");
		//发送异常情况联系人以及抄送人
		String exceptionTo=cf.getSapMailPropertiesValue("sap.mail.exception_to_address");
		String exceptionCc=cf.getSapMailPropertiesValue("sap.mail.exception_cc_address");
		//获取邮件主题/内容
		String subject=cf.getSapMailPropertiesValue("aem.data.subject");
		String txt=cf.getSapMailPropertiesValue("aem.data.txt");
		//从配置文件中获取两个时间段预期的数据量
		int amData=Integer.parseInt(cf.getSapMailPropertiesValue("aem.data.am"));
		int pmData=Integer.parseInt(cf.getSapMailPropertiesValue("aem.data.pm"));
		//获取昨天的日期
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -1);
		
		AemDataDao aemDao = new AemDataDao();
		String date=format.format(new Date());
		//设置两个查询的时间段
		String startTime=date;
		String endTime;
		//数据量记录字段
		int i=0;
		try {
			if(date.substring(11,13).equals(am)){
				String message1="邮件触发日期："+date.substring(0,11)+"<br/>"+"数据时间：前一日18：00 P.M. 到 当日10:00 A.M. （考核值：数据量不得少于"+amData+"条）"+"<br/>"+"实际数据量："+i+"条";
				String endDate = format.format(calendar.getTime());
				endTime=endDate.substring(0, 11)+"18";
				i=aemDao.getM2FUserData(endTime, startTime);
				log.info("上一个时间段内的数据量为："+i);
				if(i<amData){
					log.info("上一个时间段内的数据量不符合预期,准备发送邮件通知!");
					sendEmail("AEM数据监控",message1,to,cc,bool);
				}
				throw new SQLException();
			}else if(date.substring(11,13).equals(pm)){
				String message2="邮件触发日期："+date.substring(0,11)+"<br/>"+"数据时间：当日10：00 A.M. 到 当日18:00 P.M. （考核值：数据量不得少于"+pmData+"条）"+"<br/>"+"实际数据量："+i+"条";
				endTime=date.substring(0, 11)+"10";
				i=aemDao.getM2FUserData(endTime, startTime);
				log.info("上一个时间段内的数据量为："+i);
				if(i<pmData){
					log.info("上一个时间段内的数据量不符合预期,准备发送邮件通知!");
					sendEmail("AEM数据监控",message2,to,cc,bool);
				}
			}else{
				log.error("AEM监控任务在错误的时间执行!当前时间："+new Date());
				sendEmail("AEM数据监控异常","AEM监控任务在错误的时间执行!当前时间："+new Date(),exceptionTo,exceptionCc,true);
			}
		} catch (Exception e) {
			log.error("AEM监控程序执行出现异常!请检查："+e);
			sendEmail("AEM数据监控异常","AEM监控出现异常!请检查："+e,exceptionTo,exceptionCc,bool);
		}
	}
	
	public void sendEmail(String subject,String txt,String to,String cc,boolean bool){
		try{
			log.info("正在从ss.sap.mail.properties中获取邮箱配置...");
			String from=cf.getSapMailPropertiesValue("sap.mail.from");
			String password=cf.getSapMailPropertiesValue("sap.mail.password");
			String host=cf.getSapMailPropertiesValue("sap.mail.smtp.host");
			String port=cf.getSapMailPropertiesValue("sap.mail.smtp.port");
			
	        Properties props = new Properties();
	        props.setProperty("mail.transport.protocol", "smtp");
	        props.setProperty("mail.smtp.auth", "true");
	        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	        props.put("mail.smtp.socketFactory.port", port);
	        props.setProperty("mail.smtp.port", port);
	        props.setProperty("mail.host", host);
	        props.put("mail.smtp.ssl.checkserveridentity", "false");
	        
	        log.info("邮箱服务器配置中...");
	        
	        Session session = Session.getInstance(props);
	        session.setDebug(bool);
	        
	        Multipart multipart = new MimeMultipart();
			// 添加邮件正文
	        BodyPart contentPart = new MimeBodyPart();
	        contentPart.setContent(txt, "text/html;charset=UTF-8");
	        multipart.addBodyPart(contentPart);
	        
	        Message msg = new MimeMessage(session);
	        msg.setSubject(subject);
	        msg.setText(txt);
	        msg.setContent(multipart);
	        msg.setFrom(new InternetAddress(from));
	        
	        log.info("正在校验收件人...");
	        
	        //检查是否多个收件人或抄送人
	  		String [] ccAddressStr=cc.split(",");
	  		String [] toAddressStr=to.split(",");
	  		if(toAddressStr.length>1){
	  			Address [] toAddress=new Address[toAddressStr.length];
	  			for(int i=0;i<toAddress.length;i++){
	  				toAddress[i]=new InternetAddress(toAddressStr[i]);
	  			}
	  			msg.addRecipients(Message.RecipientType.TO,toAddress);
	  		}else{
	  			msg.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
	  		}
	  		if(ccAddressStr.length>1){
	  			Address [] ccAddress=new Address[ccAddressStr.length];
	  			for(int i=0;i<ccAddress.length;i++){
	  				ccAddress[i]=new InternetAddress(ccAddressStr[i]);
	  			}
	  			msg.addRecipients(Message.RecipientType.CC,ccAddress);
	  		}else{
	  			msg.addRecipient(Message.RecipientType.CC,new InternetAddress(cc));
	  		}
	  		
	        Transport transport = session.getTransport();
	        
	        log.info("正在建立连接..."+from+"||"+password);
	        
	        transport.connect(from, password);
	        
	        log.info("连接已建立,正在发送邮件!");
	        
	        transport.sendMessage(msg,msg.getAllRecipients());
	  
	        
	        log.info("邮件发送成功...");
	        transport.close();
		}catch(Exception e){
			e.printStackTrace();
		}
    }
	
}

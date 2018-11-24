package com.fc.test;

import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;
import org.quartz.JobExecutionException;
import org.springframework.util.FileCopyUtils;

import com.fc.listener.CallCenterTask;
import com.fc.listener.DmsTask;
import com.fc.service.DmsPreSellService;
import com.fc.service.GetCallCenterService;
import com.fc.service.SubmitCallCenterService;
import com.fc.webService.impl.OdpToCcDataImpl;

public class test {
	private static GetCallCenterService getCal = new GetCallCenterService();
	private static OdpToCcDataImpl odpTocc=new OdpToCcDataImpl();
	private static DmsPreSellService dsS=new DmsPreSellService();
	private static SubmitCallCenterService scS=new SubmitCallCenterService();
	private static String at;
	static DateFormat formata = new SimpleDateFormat("yyyyMMdd");
	static DateFormat formatb = new SimpleDateFormat("yyyy-MM-dd");
	
	public final static String MD5(String s) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			byte[] strTemp = s.getBytes();
			// 使用MD5创建MessageDigest对象
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);
			byte[] md = mdTemp.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte b = md[i];
				// System.out.println((int)b);
				// 将没个数(int)b进行双字节加密
				str[k++] = hexDigits[b >> 4 & 0xf];
				str[k++] = hexDigits[b & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			return null;
		}
	}
	
	public static boolean isPhoneNO(String mobiles) {
		boolean flag = false;
		try {
			Pattern p = Pattern
					.compile("^(17951)?((\\+86)|(86))?((13[0-9])|(14[7])|(15[0-3,5-9])|(17[0,0-9]))\\d{8}$");
			Matcher m = p.matcher(mobiles);
			flag = m.matches();
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}
	//DmsTask 接收Dms数据并且创建发送至CC的xml
	@Test
	public void testDmsTask() throws JobExecutionException{
	    DmsTask task=new DmsTask();
	    //JobExecutionContext arg0=new JobExecutionContext(null, null, task);
	    task.execute(null);
	}
	//CallCenterTask 接收CC的拨打结果，并且保存数据至库
	@Test 
	public void testCallCenterTask() throws JobExecutionException{
	    CallCenterTask ccTask=new CallCenterTask();
	    ccTask.execute(null);
	}
	public static void main(String[] args) {
	    System.out.print("11-9_AAA号304室".replaceAll(
				"[^a-zA-Z0-9-_\u4e00-\u9fa5]", ""));
	//System.out.println(5/500);
	    
	/*       try {
		scS.createSubCCErrorXML2();
	    } catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }*/
	    
            /*  DateFormat format = new SimpleDateFormat("yyyyMMddHHmm");
              String fileName=format.format(new Date());
	    File from = new File("D:/PYSYSTEM/subPreSellCallCenter/20150818/1439860368574.xml");
	    File to1 = new File("D:/PYSYSTEM/subPreSellCallCenter/20150818Bf");
	    if(!to1.exists()){
	      to1.mkdirs();
	     }
	     File to = new File("D:/PYSYSTEM/subPreSellCallCenter/20150818Bf/1439860368574.xml");
	    try {
		FileCopyUtils.copy(from, to);
	    } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }	*/
		//System.out.println("售前数据："+odpTocc.isPreSellDataExist(""));
		System.out.println("售前数据xml："+odpTocc.downPreSellDataFile(""));
		//dsS.createDmsPreSellXml("");
		//System.out.println(format.format(new Date()));
		
//		XmlSaveUtil xmlutil=new XmlSaveUtil();
//		DateFormat format = new SimpleDateFormat("yyyyMMdd");
//		String date=format.format(new Date());
//		String d1=date.substring(0,6);
//		d1=d1+xmlutil.LOCAL_SUBCC_STOP_DAY;
//		System.out.println(d1);
		
//		System.out.println(isPhoneNO("17630158974"));
//		try {
//			XmlSaveUtil u=new XmlSaveUtil();
//			File f=new File("D:\\Z1397052014273.xml");
//			List a=u.parseAndPrint(f,DmsObToData.class);
//			System.out.println(a.size());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}
	
	/**
	 * 返回前一天的日期
	 */
	private static String get1Date(String importDate)throws Exception{
		Date dc=formata.parse(importDate);
		Calendar c = Calendar.getInstance();
		c.set(dc.getYear()+1900,dc.getMonth(),dc.getDate());
		c.add(Calendar.DATE, -1);
		Date oneDate=c.getTime();
		return formatb.format(oneDate);
	}

	/**
	 * 验证手机号码
	 * 
	 * @param mobiles
	 * @return
	 */
	public static boolean isMobileNO(String mobiles) {
		boolean flag = false;
		try {
			if(mobiles.length()<11){
				return flag;
			}
			if(mobiles.length()>11){
				int sIndex1=mobiles.indexOf("13");
				int sIndex2=mobiles.indexOf("15");
				int sIndex3=mobiles.indexOf("18");
				int sIndex4=mobiles.indexOf("14");
				if((mobiles.length()-sIndex1)>=8 && sIndex1>-1 ){
					mobiles=mobiles.substring(sIndex1,sIndex1+11);
				}else if((mobiles.length()-sIndex2)>=8 && sIndex2>-1 ){
					mobiles=mobiles.substring(sIndex2,sIndex2+11);
				}else if((mobiles.length()-sIndex3)>=8 && sIndex3>-1 ){
					mobiles=mobiles.substring(sIndex3,sIndex3+11);
				}else if((mobiles.length()-sIndex4)>=8 && sIndex4>-1 ){
					mobiles=mobiles.substring(sIndex4,sIndex4+11);
				}
			}
			Pattern p = Pattern
					.compile("^(17951)?((\\+86)|(86))?((13[0-9])|(14[0-9])|(15[0-9])|(18[0-9]))\\d{8}$");
			Matcher m = p.matcher(mobiles);
			flag = m.matches();
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	/**
	 * 验证手机号码
	 * 
	 * @param mobiles
	 * @return
	 */
	public static boolean isPhoneNO2(String Phone) {
		boolean flag = false;
		try {
			if(Phone.indexOf("-")>-1){
				Phone=Phone.split("-")[1];
			}
			if(Phone.length()>=7){
				Pattern p = Pattern.compile("^[0-9]*$");
				Matcher m = p.matcher(Phone);
				flag = m.matches();
			}
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

}

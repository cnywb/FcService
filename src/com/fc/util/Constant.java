package com.fc.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 常用变量
 * @author lq
 *
 */
public class Constant implements java.io.Serializable {
	private static final long serialVersionUID = 3473412935252620814L;
	/**
	 * 手机号码为空
	 */
	public static final  String  K_01="K_01";
	/**
	 * 手机号码错误
	 */
	public static final  String  K_02="K_02";
	/**
	 * 车主姓名包含'4S'
	 */
	public static final  String  K_03="K_03";
	/**
	 * 车主姓名包含'试驾'
	 */
	public static final  String  K_04="K_04";
	/**
	 * 经销商代码为空
	 */
	public static final  String  K_05="K_05";
	/**
	 * 经销商代码为B99999
	 */
	public static final  String  K_06="K_06";
	/**
	 * 经销商代码为A11800
	 */
	public static final  String  K_07="K_07";
	/**
	 * 手机数据存在已拨打的空错号历史
	 */
	public static final  String  K_08="K_08";
	/**
	 * 数据存在空错号历史并已拨打2次
	 */
	public static final  String  K_09="K_09";
	/**
	 * 正确数据存在已拨打历史，不可再次拨打
	 */
	public static final  String  K_10="K_10";
	/**
	 * 数据存在空错号历史，并已达到拨打截至日期
	 */
	public static final  String  K_11="K_11";
	/**
	 * 车辆销售时间为空
	 */
	public static final  String  K_12="K_12";
	/**
	 * 车辆销售时间格式错误
	 */
	public static final  String  K_13="K_13";
	/**
	 * 车辆销售时间不符合新车主判定时间
	 */
	public static final  String  K_14="K_14";
	/**
	 * 大客户数据，数据中包含以下信息则不允许发送至CallCenter 拨打
	 * 国安局、公安部门、法院、检察院、租赁
	 */
	public static final  String  K_15="K_15";
	
	/**
	 * 不参与拨打的大客户数据，该数据有管理员在CRM管理界面进行维护
	 */
	public static final  String  K_16="K_16";
	/**
	 * 存在OB历史的数据
	 */
	public static final  String  K_17="K_17";
	
	public static final  String  K_20="K_20";
	
	//ping.qi add 2014-07-28
	/**
	 * 车主姓名包含'直销车'
	 * */
	public static final String K_21="K_21";
	/**
	 * 车主姓名包含'邹志祥'
	 * */
	public static final String K_22="K_22";
	/**
	 * 车主手机号包含“13510944747”
	 * */
	public static final String K_23="K_23";
	
	/**
	 * 自身车主手机重复（同批数据）
	 * */
	public static final String K_24="K_24";
	/**
	 * 自身联系人手机重复（同批数据）
	 * */
	public static final String K_25="K_25";
	
	/**
	 * 自身车主/联系人手机重复（同批数据）
	 * */
	public static final String K_26="K_26";
	
	/**
	 * 历史车主手机重复（历史公司FC_ODP_CSI_OB_RAWDATA车主对比）
	 * */
	public static final String K_27="K_27";
	
	/**
	 * 历史车主手机重复（历史公司FC_DMS_OB_TO_DATA车主对比） 
	 * */
	public static final String K_28="K_28";
	
	/**
	 * 历史联系人手机重复(历史公司FC_DMS_OB_TO_DATA联系人对比  )
	 * */
	public static final String K_29="K_29";
	
	/**
	 * 历史联系人手机重复(历史公司FC_ODP_CSI_OB_RAWDATA联系人对比 )
	 * */
	public static final String K_30="K_30";
	
	/**
	 *历史车主手机重复 (历史个人FC_ODP_CSI_OB_RAWDATA车主对比 )   
	 * */
	public static final String K_31="K_31";
	
	/**
	 * 历史车主手机重复(历史个人FC_DMS_OB_TO_DATA车主对比 )
	 * */
	public static final String K_32="K_32";
	
	/**
	 * 历史联系人手机重复(历史个人FC_ODP_CSI_OB_RAWDATA联系人对比  )
	 * */
	public static final String K_33="K_33";
	
	/**
	 * 历史联系人手机重复(历史个人FC_DMS_OB_TO_DATA联系人对比)
	 * */
	
	public static final String K_34="K_34";
	
	/**
	 * 历史VIN重复(历史FC_ODP_CSI_OB_RAWDATA)
	 * */
	
	public static final String K_40="K_40";
	
	/**
	 * 历史VIN重复(历史FC_DMS_OB_TO_DATA)
	 * */
	
	public static final String K_41="K_41";
	
	/**
	 * 生成XML限定的数据条数
	 */
	public static final int XML_DATA_COUNT=100;
	
	public static final String JOBDETAIL_01="jobDetail-s1";
	public static final String JOBDETAIL_02="jobDetail-s2";
	public static final String JOBDETAIL_03="jobDetail-s3";
	public static final String JOBDETAIL_04="jobDetail-s4";
	public static final String JOBDETAIL_05="jobDetail-s5";
	public static final String JOBDETAIL_06="jobDetail-s6";
	public static final String JOBDETAIL_07="jobDetail-s7";
	public static final String JOBDETAIL_08="jobDetail-s8";
	/**
	 * soundsun start
	 */
	public static final String JOBDETAIL_09="jobDetail-s9";
	public static final String JOBDETAIL_10="jobDetail-s10";
	public static final String JOBDETAIL_11="jobDetail-s11";
	public static final String JOBDETAIL_12="jobDetail-s12";
	public static final String JOBDETAIL_13="jobDetail-s13";
	
	/**
	 * soundsun end
	 */
	private static Map<String,String> LISTENER_MAP=new HashMap<String,String>();
	public static String LISTENER_MAP(String KEY){
		if(!(LISTENER_MAP!=null && LISTENER_MAP.size()>0) ){
			LISTENER_MAP.put(JOBDETAIL_01,"com.fc.service.UploadDmsXmlService");
			LISTENER_MAP.put(JOBDETAIL_02,"com.fc.listener.DmsTask");
			LISTENER_MAP.put(JOBDETAIL_03,"com.fc.listener.CallCenterTask");
			//LISTENER_MAP.put(JOBDETAIL_04,"com.fc.listener.PreSellCallCenterTask");
			LISTENER_MAP.put(JOBDETAIL_05,"com.fc.listener.SubDmsPreSellTask");
			LISTENER_MAP.put(JOBDETAIL_06,"com.fc.listener.GetDmsPreSellTask");
			LISTENER_MAP.put(JOBDETAIL_07,"com.fc.listener.DmsPreSellTask");//发送给dms的售前数据task，创建xml,上传至ftp
			LISTENER_MAP.put(JOBDETAIL_08,"com.fc.listener.PreSellCallCenterNewTask");//get cc 售前数据
			/**
			 * soundsun start
			 */
			
			LISTENER_MAP.put(JOBDETAIL_09,"com.fc.ss.listener.CleanDataTask");//上传售前数据至DMS
			LISTENER_MAP.put(JOBDETAIL_10,"com.fc.ss.listener.SAPUploadTask");
			LISTENER_MAP.put(JOBDETAIL_11,"com.fc.ss.listener.SAPDownloadTask");
			LISTENER_MAP.put(JOBDETAIL_12, "com.fc.ss.listener.SAPToFcTask");
			LISTENER_MAP.put(JOBDETAIL_13, "com.fc.ss.listener.AEMTask");
			/**
			 * soundsun end
			 */
		}
		return LISTENER_MAP.get(KEY);
	}
}

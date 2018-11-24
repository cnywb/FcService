package com.fc.util.Interface;

/**
 * Properties文件属性
 * @author lq
 *
 */
public	abstract class IProperties {
	 public  String DMS_TO_DATE_PATH;//DMS数据存放至本机目录路径
	 public  String DMS_TO_BACK_PATH;//其它系统访问InfoXLive后落地至本机Back目录
	 public  String SUB_CALLCENTER_SH_PATH;//发送至CallCenter售后数据存放目录
	 public  String GET_CALLCENTER_SH_PATH;//接收CallCenter售后反馈数据存放目录
	 public  String SUB_CALLCENTER_PRE_PATH;//发送至CallCenter售前数据存放目录
	 public  String GET_CALLCENTER_PRE_PATH;//接收CallCenter售前数据存放目录
	 public  String GET_DMS_PRE_PATH;//接收DMS售前数据反馈，存放地址
	 public  String GET_DMS_PRE_TIME;//接收DMS售前数据定时任务时间。
	 public  String LOG_ZIP_PATH;//日志ZIP文件存放目录
	 public  String VISIT_DMS_TO_DATA_TIME;//访问存放至本机Back目录时间
	 public  String GET_CALLCENTER_OB_TIME;//访问CallCenterWebService 售后数据设定时间
	 public  String UPAD_DMS_ERROR_TIME;//上传空错号数据至FTP目录设定时间
	 public  String GET_CALLcENTER_PRE_TIME;//访问CallCenterWebService 售前数据设定时间
	 public  String READ_TYPES;//读取本机BACK目录的各个不同的读取类别
	 public  String UPLOAD_FTP_PATH;//上传FTP目录路径
	 public  String SET_WD;
	 public  String WEB_SERVICE_URL;//webService地址
	 public  String MANUAL_READ_SWITCH;
	 public  String LOCAL_SUBCC_LAST_MONTH;//前2月日期
	 public  String LOCAL_SUBCC_THIS_MONTH;//前1月日期
	 public  String LOCAL_SUBCC_STOP_DAY;//当前截止日
	 public  String LOCAL_SUBCC_MONTH_DIRECTORY;//发送DMS反馈数据文件至Call Center存储目录
	 public  String MANUAL_READCC_SWITCH;//手动执行接收CallCenter数据日期配置
	 public  String DMSPRESELL_READ_SWITCH;//手动执行发送DMS售前数据日期配置
	 public  String DMSPRESELL_DATA_TIME;//fc发送至dms任务时间配置
	 public  String MANUAL_SS_READ_SWITCH;
	 
	 
	 public  String GET_CLEAN_DATA_TIME;
	 public  String GET_DATA_ROW_TIME;
	 public  String GET_SAP_UPLOAD_TIME;
	 public  String GET_SAP_DOWNLOAD_TIME;
	 public  String LISTENER_AEM_INTERFACE;
	 
	 public  String MOVE_SAP_TO_FC_TIME;
}

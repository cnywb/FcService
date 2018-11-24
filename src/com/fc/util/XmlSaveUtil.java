package com.fc.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.fc.util.Interface.IProperties;

/**
 * 保存XML文件到本地工具类
 * 
 * @author lq
 * 
 */
public class XmlSaveUtil extends IProperties {
	private static Logger log = Logger.getLogger(XmlSaveUtil.class);
	public static final DateFormat formaty = new SimpleDateFormat("yyyyMMdd");
	private PropUtils p = new PropUtils();
	public static String importDate;

	public static String tday = formaty.format(new Date());
	private static XMLString xml = new XMLString();
	//记录写入文件个数
	public Map<String,Integer>  map=new HashMap<String,Integer>();
	public XmlSaveUtil() {
		initProperties();
	}
	
	//初始化Properties文件
	private void initProperties(){
		try {
			p.loadFile("ftpConn.properties");
			DMS_TO_DATE_PATH=getFilePath("local.Directory");//DMS数据存放至本机目录路径
			DMS_TO_BACK_PATH=getFilePath("local.Directory.FtpDirectory");//其它系统访问InfoXLive后落地至本机Back目录
			SUB_CALLCENTER_SH_PATH=getFilePath("local.xml.CallCenter.Directory");//发送至CallCenter售后数据存放目录
			GET_CALLCENTER_SH_PATH=getFilePath("local.xml.getCallCenter.Directory");//接收CallCenter售后反馈数据存放目录
			SUB_CALLCENTER_PRE_PATH=getFilePath("local.xml.PreSell.Directory");//发送至CallCenter售前数据存放目录
			GET_CALLCENTER_PRE_PATH=getFilePath("local.xml.getPreSell.Directory");//接收CallCenter售前数据存放目录
			GET_DMS_PRE_PATH=getFilePath("local.XML.GET_DMS_TO_PRESELL");//接收DMS反馈的售前数据存放目录
			GET_DMS_PRE_TIME=getFilePath("local.getDmsPreSellData.Timing");//接收DMS售前反馈数据的时间
			LOG_ZIP_PATH=getFilePath("local.xml.zip.Directory");//日志ZIP文件存放目录
			VISIT_DMS_TO_DATA_TIME=getFilePath("local.Timing");//访问存放至本机Back目录时间
			GET_CALLCENTER_OB_TIME=getFilePath("local.getCallCenter.Timing");//访问CallCenterWebService 售后数据设定时间
			UPAD_DMS_ERROR_TIME=getFilePath("local.upftpTime");//上传空错号数据至FTP目录设定时间
			GET_CALLcENTER_PRE_TIME=getFilePath("local.getPreSellCC.Timing");//访问CallCenterWebService 售前数据设定时间
			READ_TYPES=getFilePath("local.readType");//读取本机BACK目录的各个不同的读取类别
			UPLOAD_FTP_PATH=getFilePath("local.ftp");//上传FTP目录路径
			SET_WD=getFilePath("local.WD");
			WEB_SERVICE_URL=getFilePath("local.WebService.URL");
			MANUAL_READ_SWITCH=getFilePath("manual.read.switch");//手动读取数据开关
			LOCAL_SUBCC_LAST_MONTH=getFilePath("local.subCC.Last.month");//前2月日期
			LOCAL_SUBCC_THIS_MONTH=getFilePath("local.subCC.This.month");//前1月日期
			LOCAL_SUBCC_STOP_DAY=getFilePath("local.subCC.Stop.Day");//当前截止日
			LOCAL_SUBCC_MONTH_DIRECTORY=getFilePath("local.subCC.month.Directory");//发送DMS反馈数据文件至Call Center存储目录
			MANUAL_READCC_SWITCH=getFilePath("manual.readcc.switch");//手动读取数据开关
			DMSPRESELL_READ_SWITCH=getFilePath("manual.dmspresell.read.switch");//手动执行上传dms数据时间
			DMSPRESELL_DATA_TIME=getFilePath("local.dmsPrereSellSata.Timing");//上传dms数据时间
			MANUAL_SS_READ_SWITCH=getFilePath("manual.ss.read.switch");//生成excel开关
			
			
			GET_CLEAN_DATA_TIME=getFilePath("local.getCleanData.Timing");//访问sms生成EXCEL文件的触发时间
			GET_DATA_ROW_TIME=getFilePath("local.getDataRaw.Timing");//访问DataRawTemp临时表的触发时间
			GET_SAP_UPLOAD_TIME=getFilePath("local.getSAPUpload.Timing");//访问生成xml文件并上传到ftp的触发时间
			GET_SAP_DOWNLOAD_TIME=getFilePath("local.sap.download.Timing");//从SAP FTP服务器获取文件
			MOVE_SAP_TO_FC_TIME=getFilePath("local.moveSapToFc.Timing");//sap回传失败的文件移动
			LISTENER_AEM_INTERFACE=getFilePath("local.listenerAem.Timing");//Aem监控 每天10点以及18点执行一次任务
		} catch (Exception e) {
			log.error("读取ftpConn.properties异常", e);
		}
	}
	
	
	
	/**
	 * 根据读取的XML文件内容，返回相应的文件夹路径
	 * 
	 * @param pathType
	 * @return
	 */
	private String getFilePath(String pathType) {
		String path = p.getValue(pathType);
		return path;
	}


	/**
	 * 根据XML文件路径和映射类获取该表的列表信息并返回list
	 * 
	 * @param uri
	 * @param className
	 * @return
	 * @throws Exception
	 */
	public List parseAndPrint(File file, Class<?> cls) throws Exception {
		return xml.parseXml2List(file, cls);
	}

	/**
	 * 拷贝文件夹到另一目录
	 * @param src
	 * @param dest
	 * @throws IOException
	 */
	public   void copyFolder(File src,String filePath) throws IOException {
		if (src.isDirectory()) {
			File f=new File(filePath);
			if(!f.exists()){
				f.mkdirs();
				if(!this.map.containsKey(f.getName())){
					log.info("[检测到目录未创建，创建目录并注入数量]"+f.getAbsolutePath());
					this.map.put(f.getName(),0);
				}
			}else{
				if(!this.map.containsKey(f.getName())){
					log.info("[检测到目录已创建，注入数量]"+f.getAbsolutePath());
					this.map.put(f.getName(),0);
				}
			}
			
			File[] files = src.listFiles();
			for (File file : files) {
				// 递归复制
				copyFolder(file, f.getAbsolutePath()+"\\"+file.getName());
			}
		}else {
			File f=new File(filePath);
			InputStream in = new FileInputStream(src);
			OutputStream out = new FileOutputStream(f);
			byte[] buffer = new byte[1024];
			int length;
			while ((length = in.read(buffer)) > 0) {
				out.write(buffer, 0, length);
			}
			in.close();
			out.close();
			String parentName=f.getParentFile().getName();
			if(this.map.containsKey(parentName)){
				 int  number=this.map.get(parentName);
				 number=number+1;
				 this.map.put(parentName, number);
			}
		}
	}

	/**
	 * 根据字符转换为时间
	 * 
	 * @param date
	 * @return
	 */
	public static Date formatDate(String date) {
		Date d = null;
		try {
			DateFormat formatw = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			d = formatw.parse(date);
		} catch (Exception e) {
			log.info("时间转换异常", e);
		}
		return d;
	}

	public static String formatDate(Date date, int style) {
		if (date == null) {
			return "";
		}
		switch (style) {
		case 6:
			return formath.format(date);
		case 5:
			return formatyc.format(date);
		case 4:
			return formats.format(date);
		case 3:
			return formatm.format(date);
		case 2:
			return format.format(date);
		default:
			return formatw.format(date);
		}
	}

	public static final DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	public static final DateFormat formatw = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static final DateFormat formatm = new SimpleDateFormat("MM-dd HH:mm");
	public static final DateFormat formats = new SimpleDateFormat("MM-dd");
	public static final DateFormat formatyc = new SimpleDateFormat("yyyyMMdd");
	public static final DateFormat formath = new SimpleDateFormat("HH:mm");

}

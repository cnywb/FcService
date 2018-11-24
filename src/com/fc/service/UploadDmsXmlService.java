package com.fc.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import sun.net.TelnetOutputStream;
import sun.net.ftp.FtpClient;

import com.fc.bean.UploadDmsDataErrorBean;
import com.fc.dao.db.SaveDmsDataDao;
import com.fc.util.PropUtils;
import com.fc.util.XmlSaveUtil;

/**
 * 上传空错号数据到FTP
 * 
 * @author WangJian
 * 
 *         2013-11-28
 */
public class UploadDmsXmlService implements Job{

	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		// TODO Auto-generated method stub
		
	}
	/*	private static Logger log = Logger.getLogger(UploadDmsXmlService.class);
		public static XmlSaveUtil xmlBean;
		public static final DateFormat formaty = new SimpleDateFormat("yyyyMMddHHmmss");
		public static final DateFormat format = new SimpleDateFormat("yyyyMMdd");
		private static SaveDmsDataDao saveDmsBean = new SaveDmsDataDao();
		private String ip = "";
		private String username = "";
		private String password = "";
		private int port = -1;
		private String path = "";
		private int count=0;
		FtpClient ftpClient = null;
		OutputStream os = null;
		FileInputStream is = null;

		public  UploadDmsXmlService(String serverIP, String username, String password) {
			this.ip = serverIP;
			this.username = username;
			this.password = password;
		}
		
		public  UploadDmsXmlService(String serverIP, int port, String username, String password) {
			this.ip = serverIP;
			this.username = username;
			this.password = password;
			this.port = port;
		}
		public  UploadDmsXmlService() {
			
		}
		

		*//**
		 * 连接ftp服务器
		 * 
		 * @throws IOException
		 *//*
		private boolean connectServer(){
			log.info("开始连接FTP！");
			ftpClient = new FtpClient();
			try {
				if(this.port != -1){
						ftpClient.openServer(this.ip,this.port);
				}else{
					ftpClient.openServer(this.ip);
				}
				ftpClient.login(this.username, this.password);
				if (this.path.length() != 0){
					ftpClient.cd(this.path);// path是ftp服务下主目录的子目录			
				}
				ftpClient.binary();// 用2进制上传、下载
				log.info("连接成功,已登录到\"" + ftpClient.pwd() + "\"目录");
				return true;
			}catch (IOException e){
				e.printStackTrace();
				return false;
			}
		}
		
		*//**
		 * 断开与ftp服务器连接
		 * 
		 * @throws IOException
		 *//*
		private boolean closeServer(){
			try{
				if (is != null) {
					is.close();
				}
				if (os != null) {
					os.close();
				}
				if (ftpClient != null) {
					ftpClient.closeServer();
				}
				log.info("已从服务器断开");
				return true;
			}catch(IOException e){
				e.printStackTrace();
				return false;
			}
		}
		
		*//**
		 * 检查文件夹在当前目录下是否存在
		 * @param dir
		 * @return
		 *//*
		 private boolean isDirExist(String dir){
			 String pwd = "";
			 try {
				 pwd = ftpClient.pwd();
				 ftpClient.cd(dir);
				 ftpClient.cd(pwd);
			 }catch(Exception e){
				 return false;
			 }
			 return true; 
		 }
		
		*//**
		 * 在当前目录下创建文件夹
		 * @param dir
		 * @return
		 * @throws Exception
		 *//*
		 private boolean createDir(String dir){
			 try{
				ftpClient.ascii();
				StringTokenizer s = new StringTokenizer(dir, "/"); //sign
				s.countTokens();
				String pathName = ftpClient.pwd();
				while(s.hasMoreElements()){
					pathName = pathName + "/" + (String) s.nextElement();
					try {
						ftpClient.sendServer("MKD " + pathName + "\r\n");
					} catch (Exception e) {
						e = null;
						return false;
					}
					ftpClient.readServerResponse();
				}
				ftpClient.binary();
				return true;
			}catch (IOException e1){
				e1.printStackTrace();
				return false;
			}
		 }
		 
		 *//**
		  * ftp上传
		  * 如果服务器段已存在名为filename的文件夹，该文件夹中与要上传的文件夹中同名的文件将被替换
		  * 
		  * @param filename 要上传的文件（或文件夹）名
		  * @return
		  * @throws Exception
		  *//*
		 private boolean upload(String filename){
			String newname = "";
			if(filename.indexOf("/") > -1){
				newname = filename.substring(filename.lastIndexOf("/") + 1);
			}else{
				newname = filename;
			}
			return upload(filename, newname);
		}
		 
		 *//**
		  * ftp上传
		  * 如果服务器段已存在名为newName的文件夹，该文件夹中与要上传的文件夹中同名的文件将被替换
		  * 
		  * @param fileName 要上传的文件（或文件夹）名
		  * @param newName 服务器段要生成的文件（或文件夹）名
		  * @return
		  *//*
		private boolean upload(String fileName, String newName){
			 try{
				 String savefilename = new String(fileName.getBytes("ISO-8859-1"), "UTF-8"); 
				 File file_in = new File(savefilename);//打开本地待长传的文件
				 if(!file_in.exists()){
					 throw new Exception("此文件或文件夹[" + file_in.getName() + "]有误或不存在!");
				 }
				 
				 String createDate = format.format(new Date());
				 if(!isDirExist(newName)){
					 createDir(newName);
					 log.info("文件夹不存在，创建文件夹！");
				 }
				 createDir(newName+"/"+createDate);
				 if(file_in.isDirectory()){
					 upload(file_in.getPath(),newName,ftpClient.pwd());
				 }else{
					 uploadFile(file_in.getPath(),newName+"/"+createDate+"/"+file_in.getName());
				 }
				 
				 if(is != null){
					 is.close();
				 }
				 if(os != null){
					 os.close();
				 }
				 return true;
			 }catch(Exception e){ 
			  		e.printStackTrace(); 
			  		System.err.println("Exception e in Ftp upload(): " + e.toString()); 
			  		return false;
			 }finally{
				 try{
					 if(is != null){
						 is.close();
					 }
					 if(os != null){ 
						 os.close(); 
					 }
				 }catch(IOException e){
					 e.printStackTrace();
			  	} 
			 }
		 }
		 
		 *//**
		  * 真正用于上传的方法
		  * @param fileName
		  * @param newName
		  * @param path
		  * @throws Exception
		  *//*
		 private void upload(String fileName, String newName,String path) throws Exception{
				 String savefilename = new String(fileName.getBytes("ISO-8859-1"), "GBK"); 
				 File file_in = new File(savefilename);//打开本地待长传的文件
				 if(!file_in.exists()){
					 throw new Exception("此文件或文件夹[" + file_in.getName() + "]有误或不存在!");
				 }
				 if(file_in.isDirectory()){
					 if(!isDirExist(newName)){
						 createDir(newName);
					 }
					 ftpClient.cd(newName);
					 File sourceFile[] = file_in.listFiles();
					 for(int i = 0; i < sourceFile.length; i++){
					     if(!sourceFile[i].exists()){
					    	 continue;
					     }
					     if(sourceFile[i].isDirectory()){
					    	 this.upload(sourceFile[i].getPath(),sourceFile[i].getName(),path+"/"+newName);
					     }else{
					    	 this.uploadFile(sourceFile[i].getPath(),sourceFile[i].getName());
					      }
					    }
				 }else{
					 uploadFile(file_in.getPath(),newName);
				 }
				 ftpClient.cd(path);
		 }

		*//**
		 *  upload 上传文件
		 * 
		 * @param filename 要上传的文件名
		 * @param newname 上传后的新文件名
		 * @return -1 文件不存在 >=0 成功上传，返回文件的大小
		 * @throws Exception
		 *//*
		private long uploadFile(String filename, String newname) throws Exception{
			long result = 0;
			TelnetOutputStream os = null;
			FileInputStream is = null;
			try {
				java.io.File file_in = new java.io.File(filename);
				if(!file_in.exists())
					return -1;
				os = ftpClient.put(newname);
				result = file_in.length();
				is = new FileInputStream(file_in);
				byte[] bytes = new byte[1024];
				int c;
				while((c = is.read(bytes)) != -1){
					os.write(bytes, 0, c);
				}
			}finally{
				if(is != null){
					is.close();
				}
				if(os != null){
					os.close();
				}
			}
			return result;
		}
		

		
		*//**
		 * 通过KEY得到对应的值
		 * @param getValue
		 * @return
		 *//*
		private static String getPath(String getValue){
			PropUtils p = new PropUtils();
            try {
            	p.loadFile("ftpConn.properties");
			} catch (Exception e) {
				log.error("读取ftpConn.properties异常", e);
			}
            String path = p.getValue(getValue);
            return path;
		}

		public void execute(JobExecutionContext arg0)
		throws JobExecutionException {
		try {
				String dir = getPath("local.ftp");
				String dir2 = dir + "/" + format.format(new Date());
				File file = new File(dir2);
				if (file.exists()) {
					if (file.listFiles().length > 0) {
						File[] file1 = file.listFiles();
						for (File f : file1) {
							f.delete();
						}
					}
				}
				
				DateFormat formaty = new SimpleDateFormat("yyyyMMdd");
				Calendar calendar = new GregorianCalendar();
				Date date = new Date();
				calendar.setTime(date);
				calendar.add(calendar.DATE,-9);//把日期往前推9天.
				date=calendar.getTime(); 
				String createDate = formaty.format(date);
				
				log.info("调用获取数据方法!");
				List<UploadDmsDataErrorBean> lista = saveDmsBean.getCallErrorData();
				//List<UploadDmsDataErrorBean> listb = saveDmsBean.getErrorData(createDate); xuwei_20150722 空错号不发送给dms
				List<UploadDmsDataErrorBean> upList=new ArrayList<UploadDmsDataErrorBean>();
				if(lista!=null &&  lista.size()>0){
					upList.addAll(lista);
				}
				if(listb!=null &&  listb.size()>0){
					upList.addAll(listb);
				}
				
				log.info("查询空错号数据成功，总条数为：" + upList.size());
				if (upList != null && upList.size() > 0) {
					try {
						log.info("开始生成XML！");
						DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
						createXmlDocument(UploadDmsDataErrorBean.class, upList,"GBK", file.getAbsolutePath(), createDate, "ODP"+format.format(new Date()));
						
						//createXml(list);
					} catch (Exception e) {
						e.printStackTrace();
						log.info("生成XML失败！");
					}
				} else {
					log.info("空错号数据为空，生成XML失败");
				}
				if (upList != null) {
					upList.clear();
				}
		} catch (Exception e1) {
				e1.printStackTrace();
				log.error("获取数据失败！",e1);
			}
		}
		
		private static boolean isNumeric(String str){ 
		    Pattern pattern = Pattern.compile("[0-9]*"); 
		    return pattern.matcher(str).matches();    
		 }
		public static void createXmlDocument(Class<?> obj, List<?> entitys,
				String Encode, String XMLPath, String createDate, String XmlName) {
			log.info("动态生成XML文件开始");
			try {
				XMLWriter writer = null;// 声明写XML的对象
				OutputFormat format = OutputFormat.createPrettyPrint();
				format.setEncoding(Encode); // 设置XML文件的编码格式 UTF-8

				File file = new File(XMLPath);// 获得文件
				if (!file.exists()) {
					file.mkdirs();
				}
				// 创建xml文件
				Document document = DocumentHelper.createDocument();
				Element root = document.addElement("data");// 添加根节点
				root.addElement("time_stamp").setText(formaty.format(new Date()));
				Element zroot = root.addElement("beans");// 添加根节点
				Field[] properties = obj.getDeclaredFields();// 获得实体类的所有属性

				for (Object t : entitys) { // 递归实体
					Element element = zroot.addElement("bean"); // 二级节点
					for (int i = 0; i < properties.length; i++) {
						if (!"serialVersionUID".equals(properties[i].getName())) {
							// 反射get方法
							Method meth = t.getClass().getMethod(
									"get"
											+ (properties[i].getName().substring(0,
													1)).toUpperCase()
											+ properties[i].getName().substring(1));
							// 为二级节点添加属性，属性值为对应属性的值
							Object invo = meth.invoke(t);
							element.addElement(properties[i].getName()).setText(
									(invo != null ? invo.toString() : ""));
						}
					}
				}
				File fil = new File(XMLPath + "\\" + XmlName + ".xml");
				writer = new XMLWriter(new FileOutputStream(fil), format);
				writer.write(document);
				writer.close();
			} catch (Exception e) {
				log.error("动态生成XML失败", e);
			}
		}*/
}

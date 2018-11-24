package com.fc.ss.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;

import java.util.List;

import java.util.StringTokenizer;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.record.CFHeaderRecord;

import sun.net.TelnetInputStream;
import sun.net.TelnetOutputStream;
import sun.net.ftp.FtpClient;
import sun.net.ftp.FtpDirEntry;
import sun.net.ftp.FtpProtocolException;

import com.fc.util.XmlSaveUtil;

/**
 * 上传文件到远程FTP服务器
 * 
 * @author Soundsun
 * 
 *         2016-6-6
 */
public class RemoteFTP {
		private static Logger log = Logger.getLogger(RemoteFTP.class);
		public static XmlSaveUtil xmlBean;
		public static final DateFormat formaty = new SimpleDateFormat("yyyyMMddHHmmss");
		public static final DateFormat format = new SimpleDateFormat("yyyyMMdd");
		private String ip = "";
		private String username = "";
		private String password = "";
		private int port = -1;
		private String path = "";
		FtpClient ftpClient = null;
		OutputStream os = null;
		FileInputStream is = null;
		private CommonFunction cf = new CommonFunction();

		public  RemoteFTP(String serverIP, String username, String password) {
			this.ip = serverIP;
			this.username = username;
			this.password = password;
		}
		
		public  RemoteFTP(String serverIP, int port, String username, String password) {
			this.ip = serverIP;
			this.username = username;
			this.password = password;
			this.port = port;
		}
		
		public  RemoteFTP() {
			
		}
		
		/**
		 * 连接ftp服务器
		 * @throws FtpProtocolException 
		 * 
		 * @throws IOException
		 */
		private boolean connectServer() throws FtpProtocolException{
			log.info("开始连接FTP！");
			try {
				
				ftpClient = FtpClient.create();
				ftpClient.connect(new InetSocketAddress(this.ip, port));
				ftpClient.login(this.username, this.password.toCharArray());
				
				if (this.path.length() != 0){
					ftpClient.changeDirectory(this.path);// path是ftp服务下主目录的子目录			
				}
				ftpClient.setBinaryType();// 用2进制上传、下载
				log.info("连接成功");
				return true;
				
			}catch (IOException e){
				log.info("连接失败");
				e.printStackTrace();
				return false;
			}
			
		}
		
		/**
		 * 断开与ftp服务器连接
		 * 
		 * @throws IOException
		 */
		private boolean closeServer(){
			try{
				if (is != null) {
					is.close();
				}
				if (os != null) {
					os.close();
				}
				if (ftpClient != null) {
					ftpClient.close();
				}
				log.info("已从服务器断开");
				return true;
			}catch(IOException e){
				e.printStackTrace();
				return false;
			}
		}
		
		/**
		 * 检查文件夹在当前目录下是否存在
		 * @param dir
		 * @return
		 */
		 private boolean isDirExist(String dir){
			 try {
	            ftpClient.changeDirectory(dir);
	        } catch (Exception e) {
	            return false;
	        }
	        return true;
		 }
		
		/**
		 * 在当前目录下创建文件夹
		 * @param dir
		 * @return
		 * @throws Exception
		 */
		 private void createDir(String dir) throws Exception {
		        ftpClient.setAsciiType();
		        StringTokenizer s = new StringTokenizer(dir, "/"); // sign
		        s.countTokens();
		        String pathName = "";
		        while (s.hasMoreElements()) {
		            pathName = pathName + "/" + (String) s.nextElement();
		            try {
		                ftpClient.makeDirectory(pathName);
		            } catch (Exception e) {
		                e = null;
		            }
		        }
		        ftpClient.setBinaryType();
		    }
	
		 /**
		  * 真正用于上传的方法
		  * @param fileName
		  * @param newName
		  * @param path
		  * @throws Exception
		  */
		 private void upload(String fileName, String newName, String path) throws Exception{
			 
			 log.info("上传文件至FTP开始");
			 try{
				 String savefilename = new String(fileName.getBytes("ISO-8859-1"), "utf-8"); 
				 
				 File file_in = new File(savefilename);//打开本地待长传的文件
				 if(!file_in.exists()){
					 throw new Exception("此文件或文件夹[" + file_in.getName() + "]有误或不存在!");
				 }
				 if(path != null && path != ""){
					 if(!isDirExist(path)){
						 createDir(path);
					 }
					 ftpClient.changeDirectory(path);
				 }
				 
				 uploadFile(file_in.getPath(),newName);
			 }catch(Exception e){
				 e.printStackTrace();
				 throw e;
			 }
		 }

		/**
		 *  upload 上传文件
		 * 
		 * @param filename 要上传的文件名
		 * @param newname 上传后的新文件名
		 * @return -1 文件不存在 >=0 成功上传，返回文件的大小
		 * @throws Exception
		 */
		private long uploadFile(String filename, String newname) throws Exception{
			long result = 0;
			TelnetOutputStream os = null;
			FileInputStream is = null;
			try {
				java.io.File file_in = new java.io.File(filename);
				if(!file_in.exists())
					return -1;
				os = (TelnetOutputStream) ftpClient.putFileStream(newname);
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

		public boolean UploadFileToRemoteFTP(String localFileFullPath, String remoteFileName, String remotePath) throws Exception{
			
			try {
				connectServer();
				upload(localFileFullPath, remoteFileName, remotePath);
				closeServer();
				
				return true;
			} catch (Exception e) {
				// TODO: handle exception
				return false;
			}
		}
		
		public boolean MoveFileInRemoteFTP(String remoteFileName, String remoteSourcePath, String remoteTargetPath, Boolean needConnectServer) throws Exception{
			
			try {
				if(needConnectServer){
					connectServer();
				}
				copyFile(remoteFileName, remoteSourcePath, remoteTargetPath);
				deleteFile(remoteSourcePath, remoteFileName);
				
				return true;
			} catch (Exception e) {
				// TODO: handle exception
				return false;
			}
			finally{
				if(needConnectServer){
					closeServer();
				}
			}
		}
		
		public boolean DeleteFileFromRemoteFTP(String remotePath, String remoteFileName) throws Exception{
			
			try {
				connectServer();
				deleteFile(remotePath, remoteFileName);
				closeServer();
				
				return true;
			} catch (Exception e) {
				// TODO: handle exception
				return false;
			}
		}
		
		public List<String> DownloadFileFromRemoteFTP(String remotePath, String localPath) throws Exception{
			
			List<String> localFiles = new ArrayList<String>();
			
			try {
				connectServer();
				
				List<String> remoteFiles = getFtpFileList(remotePath);
				
				String backupPath = cf.GetPropertiesValue("sap.ftp.download.remote.backup.path");
				String errorPath = cf.GetPropertiesValue("ssap.ftp.download.remote.error.path");
				for (String remoteFile : remoteFiles) 
	           {
					try  
		            {  
						log.error("远程文件名：" + remoteFile);
		                String localFile = download(remotePath, localPath, remoteFile);
		                localFiles.add(localFile);
		                MoveFileInRemoteFTP(remoteFile, remotePath, backupPath, false);
		            } catch (FtpProtocolException e)  
		            {  
		            	MoveFileInRemoteFTP(remoteFile, remotePath, errorPath, false);
		            	log.error("下载远程文件错误", e);
		                e.printStackTrace();
		            }                 
	           }
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				return new ArrayList<String>();
			}
			finally{
				closeServer();
			}
			
			return localFiles;
		}
		
		 /*
		      * FTP getFileList
		      * @param filenames       保存遍历的文件名
		      * @param path    遍历目录的路径
		      * */
		     private List<String> getFtpFileList(String path){
		    	 List<String> filenames = new ArrayList<String>();
		         BufferedReader ds = null;
		         try {
		        	 
		        	 Iterator<FtpDirEntry> files = ftpClient.listFiles(path);
		        	 
		        	 while (files.hasNext()) {
		                 FtpDirEntry ftpFile = files.next();
		                 if(ftpFile.getType() == FtpDirEntry.Type.FILE){
		                	 filenames.add(ftpFile.getName());
		                	 log.info("遍历远程FTP文件名：" + ftpFile.getName());
		                 }
		             }
		        	 
//		             ds = new BufferedReader(new InputStreamReader(ftpClient.nameList(path), "ISO-8859-1"));
//		             String line = "";
//		             while((line = ds.readLine())!= null){
//		                 line = new String(line.getBytes("ISO-8859-1"),"GBK");
//		                 String name[] = line.split("/");
//		                 filenames.add(name[name.length - 1]);
//		                 log.info("遍历远程FTP文件名：" + name[name.length - 1]);
//		             }
		         } catch (FtpProtocolException e) {
		             // TODO Auto-generated catch block
		             e.printStackTrace();
		         } catch (IOException e) {
		             // TODO Auto-generated catch block
		             e.printStackTrace();
		         }
		         
		         return filenames;
		     }
		
		/** 
	     *  
	     * 下载文件 
	     *  
	     * @param remoteFile 
	     *            远程文件路径(服务器端) 
	     * @param localFile 
	     *            本地文件路径(客户端) 
	     * @throws FtpProtocolException 
	     *  
	     */  
	    private String download(String remotePath, String localPath, String remoteFile) throws FtpProtocolException  
	    {  
	    	  FileInputStream is = null;
	    	  FileOutputStream os = null;
	    	  
	    	  try {
	    		  is =  (FileInputStream) ftpClient.getFileStream(remotePath + remoteFile);
	    		  
	    		  File newFilePath = new File(localPath);// 获得文件
		  		    if (!newFilePath.exists()) {
		  		    	newFilePath.mkdirs();
		  		    }
		  		    
		  		  String localFile = newFilePath + "\\" + File.separator + remoteFile;
		  		    
	    		  os = new FileOutputStream(localFile);
	    		  byte[] buffer = new byte[1024];
	    		  int c;
		    	  while((c = is.read(buffer)) != -1)
		    	  {
		    		  os.write(buffer,0,c);
		    	  }
		    	  log.info("Download Success: " + localFile);
		    	  return localFile;
	    	  } catch (FtpProtocolException e) {
	    	  // TODO Auto-generated catch block
	    		  e.printStackTrace();
	    		  log.error("Download Fail: " + remoteFile, e);
	    		  return "";
	    	  } catch (IOException e) {
	    	  // TODO Auto-generated catch block
	    		  e.printStackTrace();
	    		  log.error("Download Fail: " + remoteFile, e);
	    		  return "";
	    	  }catch (Exception e) {
	    		  // TODO: handle exception
	    		  log.error("Download Fail: " + remoteFile, e);
	    		  e.printStackTrace();
	    		  return "";
	    	  }finally  
	          {  
	              try  
	              {  
	                  if (is != null)  
	                  {  
	                      is.close();  
	                  }  
	              } catch (IOException e)  
	              {  
	            	  log.error("Download Fail: " + remoteFile, e);
	                  e.printStackTrace();  
	              } finally  
	              {  
	                  try  
	                  {  
	                      if (os != null)  
	                      {  
	                          os.close();  
	                      }  
	                  } catch (IOException e)  
	                  {  
	                	  log.error("Download Fail: " + remoteFile, e);
	                      e.printStackTrace();  
	                  }  
	              }  
	          }  
	    }
	    
	    public boolean deleteFile(String remotePath, String fileName){  
	        try{
	            
	            ftpClient.changeDirectory(remotePath.replace("\\", "/"));
	            ftpClient.deleteFile(fileName);
	            
	            return true;
	        }catch (Exception e) {
	            e.printStackTrace();
	            return false;
	        }
	    }
	    
	    public void copyFile(String sourceFileName, String sourceDir, String targetDir) throws Exception {  
	    	InputStream is = null;  
	        try {  
	        	 if(!isDirExist(sourceDir)){
					 createDir(sourceDir);
				 }
				 ftpClient.changeDirectory(sourceDir);

	            // 设置以二进制流的方式传输  
	            ftpClient.setBinaryType();  
	            is = ftpClient.getFileStream(new String(sourceFileName.getBytes("GBK"), "iso-8859-1"));  
	  
	            if (is != null) {  
	            	if(!isDirExist(targetDir)){
						 createDir(targetDir);
					 }
					ftpClient.changeDirectory(targetDir);
	                ftpClient.putFile(new String(sourceFileName.getBytes("GBK"), "iso-8859-1"), is);
	                
	                ftpClient.changeDirectory(sourceDir);
	            }  
	  
	        } finally {  
	            // 关闭流  
	            if (is != null) {  
	                is.close();  
	            }  
	        }  
	    }  
	    	 
}

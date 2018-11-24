package com.fc.ss.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.fc.dao.conn.DBConnection;
import com.fc.util.PropUtils;

public class CommonFunction {
	private static Logger log = Logger.getLogger(CommonFunction.class);
	  private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	  public static final DateFormat formaty = new SimpleDateFormat("yyyyMMddHHmmss");

	  public Date GetDate(String dateStr)
	  {
	    Date date = null;

	    if ((dateStr != null) && (dateStr != "")) {
	      try {
	        date = formaty.parse(dateStr);
	      } catch (Exception e) {
	        date = null;
	      }
	    }

	    return date;
	  }

	  public String GetDateString(String dateStr) {
	    String dateString = null;
	    Date date = GetDate(dateStr);

	    if (date != null) {
	      try {
	        dateString = this.sdf.format(date);
	      } catch (Exception e) {
	        dateString = null;
	      }
	    }

	    return dateString;
	  }

	  public BufferedReader ReadTextFile(String filePath) throws IOException
	  {
	    BufferedReader bufferedReader = null;
	    InputStreamReader read = null;
	    try
	    {
	      String encoding = "GBK";
	      File file = new File(filePath);
	      if ((file.isFile()) && (file.exists())) {
	        read = new InputStreamReader(
	          new FileInputStream(file), encoding);
	        bufferedReader = new BufferedReader(read);
	        String lineTxt = null;
	        while ((lineTxt = bufferedReader.readLine()) != null) {
	          System.out.println(lineTxt);
	        }
	        read.close();
	      } else {
	        System.out.println("找不到指定的文件");
	      }
	    } catch (Exception e) {
	      System.out.println("读取文件内容出错");
	      e.printStackTrace();
	    }
	    finally {
	      if (read != null) {
	        read.close();
	      }
	      if (bufferedReader != null) {
	        bufferedReader.close();
	      }
	    }

	    return bufferedReader;
	  }

	  public String GetPropertiesValue(String fileName, String key) {
	    PropUtils p = new PropUtils();
	    try {
	      p.loadFile(fileName);
	    } catch (Exception e) {
	      log.error("读取" + fileName + "异常", e);
	    }
	    String value = p.getValue(key);
	    return value;
	  }
	  
	  public String getSapMailPropertiesValue(String key){
		  PropUtils p=new PropUtils();
		  try {
			p.loadFile("ss.sap.mail.properties");
		} catch (IOException e) {
			log.error("读取ss.sap.mail.properties异常"+e);
			e.printStackTrace();
		}
		  String value=p.getValue(key);
		  return value;
	  }

	  public String GetPropertiesValue(String key)
	  {
	    PropUtils p = new PropUtils();
	    try {
	      p.loadFile("ss.sap.properties");
	    } catch (Exception e) {
	      log.error("读取ss.sap.properties异常", e);
	    }
	    String value = p.getValue(key);
	    return value;
	  }

	  public String GetSapFileNumberFormPropertiesFile(String key) {
	    PropUtils p = new PropUtils();
	    try {
	      p.loadFile("ss.sap.file.number.properties");
	    } catch (Exception e) {
	      log.error("读取ss.sap.file.number.properties异常", e);
	    }
	    String value = p.getValue(key);
	    return value;
	  }
	  
	  public String GetSapToFcErrorFileMove(String key){
		  PropUtils p=new PropUtils();
		  try{
			  p.loadFile("ss.sap.properties");
		  }catch(Exception e){
			  log.error("读取ss.sap.properties异常",e);
		  }
		  String value = p.getValue(key);
		  return value;
	  }

	  public void SetSapFileNumberPropertiesValue(String key, String value) {
	    PropUtils p = new PropUtils();
	    try {
	      p.storeValue("ss.sap.file.number.properties", key, value);
	    } catch (Exception e) {
	      log.error("设置ss.sap.file.number.properties值异常", e);
	    }
	  }

	  public String GetTxtCtrlNumbString(int ctrlNumbLength, int ctrlNumb)
	  {
	    String ctrlNumbFormat = "";

	    for (int i = 0; i < ctrlNumbLength; i++) {
	      ctrlNumbFormat = ctrlNumbFormat + "0";
	    }

	    DecimalFormat df = new DecimalFormat(ctrlNumbFormat);
	    String ctrlNumbeString = df.format(ctrlNumb);

	    return ctrlNumbeString;
	  }

	  public void MoveFile(String resourceFullPath, String targetPath)
	  {
	    try {
	      File file = new File(resourceFullPath);
	      File moveToFile = new File(targetPath + file.getName());

	      if (file.renameTo(moveToFile))
	        System.out.println("File is moved successful!");
	      else
	        System.out.println("File is failed to move!");
	    }
	    catch (Exception e) {
	      e.printStackTrace();
	    }
	  }

	  public List<File> GetFileList(String localPath, Boolean recursionFlag, String fileExt) {
	    File dir = new File(localPath);

	    List<File> fileList = new ArrayList<File>();

	    if (dir.exists()) {
	      File[] files = dir.listFiles();
	      if (files != null) {
	        for (int i = 0; i < files.length; i++) {
	          String fileName = files[i].getName();
	          if ((files[i].isDirectory()) && (recursionFlag.booleanValue())) {
	            GetFileList(files[i].getAbsolutePath(), recursionFlag, fileExt);
	          }
	          else if ((fileExt != null) && (fileExt != "")) {
	            if (fileName.endsWith(fileExt)) {
	              String strFileName = files[i].getAbsolutePath();
	              System.out.println("---" + strFileName);
	              fileList.add(files[i]);
	            }
	          }
	          else
	          {
	            String strFileName = files[i].getAbsolutePath();
	            System.out.println("---" + strFileName);
	            fileList.add(files[i]);
	          }
	        }
	      }

	    }

	    return fileList;
	  }
	  
	  public String lockedTable(String sql) throws Exception{
			StringBuffer s = new StringBuffer("");
			PreparedStatement stmt=null;
			Connection conn=null;
			conn = DBConnection.getConnection();
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sql);
			ResultSet res=stmt.executeQuery();
			while(res.next()){
				s.append(res.getString(1)+",");
			}
			if(!s.toString().equals("")){
				return s.toString().substring(0,s.length()-1);
			}
			return s.toString();
		}
}

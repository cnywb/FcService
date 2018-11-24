package com.fc.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.fc.ss.util.CommonFunction;

import sun.util.logging.resources.logging;

/**
 * 读取properties 文件工具类
 * @author lq
 *
 */
public class PropUtils {

	private Properties props;
	private static Logger log = Logger.getLogger(PropUtils.class);

	public PropUtils() {
		props = new Properties();
	}
	public void loadFile(String fname) throws IOException {
		props.load(PropUtils.class.getResourceAsStream("/" + fname));
	}
	public String getValue(String key) {
		if (null == key || "".equals(key))
			return "";
		return (props.getProperty(key));
	}
	
	public void storeValue(String fname, String key, String value) throws IOException {
		if (null != key && !"".equals(key)) {
			
			String path = PropUtils.class.getResource("/").getPath()+"ftpConn.properties"; 
			log.info(path);
			InputStream fis = null;  
	        OutputStream fos = null;  
			
			File file = new File(path);  
            if (!file.exists())  
                file.createNewFile();  
            fis = new FileInputStream(file);  
            props.load(fis);  
            fis.close();
            fos = new FileOutputStream(file);  
         
            props.setProperty(key, value); //设值-保存
            props.store(fos, "Update '" + key + "'+ '"+value);  
			
		}
	}

	public void setValue(String key, String value) {
		if (null != key && !"".equals(key)) {
			props.setProperty(key, value);
		}
	}
	public Properties getProps() {
		return (props);
	}
}

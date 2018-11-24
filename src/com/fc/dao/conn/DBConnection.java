package com.fc.dao.conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;

import com.fc.dao.db.SaveDmsDataDao;
import com.fc.util.Encrypt;
import com.fc.util.PropUtils;

public class DBConnection {
    private static Logger log = Logger.getLogger(DBConnection.class);
    private static Connection conn;
    static{
	init();
    }
    /**
     * 获得数据库连接
     * 
     * @return
     */
    public static synchronized Connection getConnections() {
	init();
	if (conn == null) {
	    log.info("[获取数据库连接出现异常]未能得到数据库连接，请检查数据库是否能连通!");
	}
	return conn;
    }

    /**
     * 初始化数据库连接
     */
    private static void init() {
	try {
	    PropUtils p = new PropUtils();
	    p.loadFile("jdbc.properties");
//	    Class.forName(p.getValue("db.driver"));
//	    conn = DriverManager.getConnection(
//		    p.getValue("db.url"),
//		    p.getValue("db.user"),
//		    Encrypt.decryptString(Encrypt.ENCRY_STYLE_DES,
//			    p.getValue("db.password")));
	    initDataSource( p.getValue("db.url"), p.getValue("db.user"), Encrypt.decryptString(Encrypt.ENCRY_STYLE_DES,
		    p.getValue("db.password")), p.getValue("db.driver"), p.getValue("db.minimumConnectionCount"), p.getValue("db.maximumConnectionCount"), 
		    p.getValue("db.maximumConnectionCount"), p.getValue("db.minimumConnectionCount"));
	} catch (Exception e) {
	    e.printStackTrace();
	    log.error("[连接数据库出现异常]", e);
	}
    }

    /**
     * 关闭数据库链接
     */
    public static synchronized void closeConnection() {
	if (conn != null) {
	    try {
		conn.close();
	    } catch (SQLException e) {
		e.printStackTrace();
	    }
	}
    }

    // 持有一个静态的数据库连接池对象
    private static DataSource DS;

    // 使用DBCP提供的BasicDataSource实现DataSource接口
    private static void initDataSource(String connectURI, String username,
	    String password, String driverClass, String initialSize,
	    String maxActive, String maxIdle, String maxWait) {
	BasicDataSource ds = new BasicDataSource();
	ds.setDriverClassName(driverClass);
	ds.setUsername(username);
	ds.setPassword(password);
	ds.setUrl(connectURI);
	ds.setInitialSize(Integer.parseInt(initialSize));
	ds.setMaxActive(Integer.parseInt(maxActive));
	ds.setMaxIdle(Integer.parseInt(maxIdle));
	ds.setMaxWait(Integer.parseInt(maxWait));
	DS = ds;
    }
    
    

    // 获得一个数据库连接
    public static synchronized Connection getConnection() {
	if (DS != null) {
	    try {
		conn = DS.getConnection();
	    } catch (Exception e) {
		System.out.println(e.getMessage());
	    }
	    // 将数据库连接的事物设置为不默认为自动Commit
//	    try {
//		conn.setAutoCommit(false);
//	    } catch (SQLException e) {
//		System.out.println(e.getMessage());
//	    }
	    return conn;
	}
	// 回收数据库连接时，直接使用con.close()即可
	return conn;

    }

    // 回收数据库连接
    protected static void shutdownDataSource() throws SQLException {
	BasicDataSource bds = (BasicDataSource) DS;
	bds.close();
    }

}

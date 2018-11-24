package com.fc.ss.dao.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.fc.dao.conn.DBConnection;

public class AemDataDao {
	private static Logger log = Logger.getLogger(AemDataDao.class);
	
	public Integer getM2FUserData(String startTime,String endTime) throws SQLException{
		log.info("查询"+startTime+" - "+endTime+"时间段内的数据量!");
		PreparedStatement dmsToDataStmt = null;
		Connection conn=null;
		Integer resNumber = 0;
		try {
			conn = DBConnection.getConnection();
			conn.setAutoCommit(false);
			String sql ="SELECT COUNT(*) FROM M2F_USERDATA WHERE CREATEDATE BETWEEN TO_DATE('"+startTime+"','yyyy-MM-dd hh24:mi:ss') AND TO_DATE('"+endTime+"','yyyy-MM-dd hh24:mi:ss')";
			dmsToDataStmt = conn.prepareStatement(sql);
			ResultSet res = dmsToDataStmt.executeQuery();
			if (res != null) {
				while (res.next()) {
					resNumber = res.getInt(1);
				}
			}
			conn.commit();
		} catch (Exception e) {
			log.error("查询出现异常!"+e);
			conn.rollback();
		}
		return resNumber;
	}
}

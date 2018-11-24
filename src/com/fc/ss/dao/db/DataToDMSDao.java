package com.fc.ss.dao.db;

import com.fc.dao.conn.DBConnection;
import com.fc.ss.bean.FcDataToDMSBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.apache.log4j.Logger;

public class DataToDMSDao
{
  private static Logger log = Logger.getLogger(DataToDMSDao.class);
  
  public Boolean CheckDataExistsByLocalToDealerId(String localToDealerId)
    throws SQLException
  {
    PreparedStatement stmt = null;
    Connection conn = null;
    Integer resNumber = Integer.valueOf(0);
    try
    {
      conn = DBConnection.getConnection();
      conn.setAutoCommit(false);
      //String sql = "select count(1) from FC_SS_SAP_DATA_TO_DMS g where g.SELL_ID = '" + localToDealerId + "'";
     
        String sql = "select count(1) from FC_SS_SAP_DATA_TO_DMS g where g.SELL_ID=?";
		
		stmt = conn.prepareStatement(sql);
		stmt.setString(1, localToDealerId);
      ResultSet res = stmt.executeQuery();
      if (res != null) {
        while (res.next()) {
          resNumber = Integer.valueOf(res.getInt(1));
        }
      }
      conn.commit();
    }
    catch (Exception e)
    {
      conn.rollback();
      if (stmt != null) {
        stmt.close();
      }
      if (conn != null) {
        try
        {
          conn.close();
        }
        catch (Exception e2)
        {
          e2.printStackTrace();
        }
      }
    }
    finally
    {
      if (stmt != null) {
        stmt.close();
      }
      if (conn != null) {
        try
        {
          conn.close();
        }
        catch (SQLException e)
        {
          e.printStackTrace();
        }
      }
    }
    if (resNumber.intValue() > 0) {
      return Boolean.valueOf(true);
    }
    return Boolean.valueOf(false);
  }
  
  public void InsertData(List<FcDataToDMSBean> mList)
    throws Exception
  {
    PreparedStatement dataStmt = null;
    Connection conn = null;
    
    log.info("[插入数据]开始");
    try
    {
      conn = DBConnection.getConnection();
      conn.setAutoCommit(false);
      if ((mList != null) && (mList.size() > 0))
      {
        String sql = "INSERT INTO FC_SS_SAP_DATA_TO_DMS(DATA_DIST_DATE,DATA_PRIORITY,NAME,SEX,MOBILE,MOBILE2,P_TEL,PHONE,EMAIL,PROVINCE,CITY,CLIENT_SOURCE,LIKE_MODEL,JH_CAR_TIME,CLIENT_DEMAND,MANAME,DEALER_CODE,DEALER_REGION,DEALER_COMMUNITY,DEALER_NAME,SELL_ID,IMPORT_CODE,SAP_CRM_ID) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        dataStmt = conn.prepareStatement(sql);
        for (FcDataToDMSBean m : mList)
        {
          dataStmt.setString(1, m.getDataDistDate());
          dataStmt.setString(2, m.getDataPriority());
          dataStmt.setString(3, m.getName());
          dataStmt.setString(4, m.getSex());
          dataStmt.setString(5, m.getMobile());
          dataStmt.setString(6, m.getMobile2());
          dataStmt.setString(7, m.getpTel());
          dataStmt.setString(8, m.getPhone());
          dataStmt.setString(9, m.getEmail());
          dataStmt.setString(10, m.getProvince());
          dataStmt.setString(11, m.getCity());
          dataStmt.setString(12, m.getClientSource());
          dataStmt.setString(13, m.getLikeModel());
          dataStmt.setString(14, m.getJhCarTime());
          dataStmt.setString(15, m.getClientDemand());
          dataStmt.setString(16, m.getMname());
          dataStmt.setString(17, m.getDealerCode());
          dataStmt.setString(18, m.getDealerRegion());
          dataStmt.setString(19, m.getDealerCommunity());
          dataStmt.setString(20, m.getDealerName());
          dataStmt.setString(21, m.getSellId());
          dataStmt.setString(22, m.getImportCode());
          dataStmt.setString(23, m.getSapCrmId());
          dataStmt.addBatch();
        }
        dataStmt.executeBatch();
        conn.commit();
      }
    }
    catch (Exception e)
    {
      log.error("[插入FC_SS_SAP_DATA_TO_DMS数据]异常", e);
      conn.rollback();
      

      throw e;
    }
    finally
    {
      if (dataStmt != null) {
        dataStmt.close();
      }
      if (conn != null) {
        try
        {
          conn.close();
        }
        catch (SQLException e)
        {
          e.printStackTrace();
        }
      }
    }
  }
}

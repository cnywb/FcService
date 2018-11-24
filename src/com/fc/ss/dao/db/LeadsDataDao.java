package com.fc.ss.dao.db;

import com.fc.dao.conn.DBConnection;
import com.fc.ss.bean.FcLeadsDataBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.apache.log4j.Logger;

public class LeadsDataDao
{
  private static Logger log = Logger.getLogger(LeadsDataDao.class);
  
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
     // String sql = "select count(1) from FC_SS_SAP_LEADS_DATA g where g.SELL_NO = '" + localToDealerId + "'";
      String sql = "select count(1) from FC_SS_SAP_LEADS_DATA g where g.SELL_NO=?";
		
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
        catch (SQLException e1)
        {
          e1.printStackTrace();
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
  
  public void InsertData(List<FcLeadsDataBean> mList)
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
        String sql = "INSERT INTO FC_SS_SAP_LEADS_DATA(SELL_CREATE_DATE,SELL_DEALER_TIME,SELL_NO,SELL_STATUS,PHONE_STATUS,INTENT_LEVEL,INTENT_MODELS,IS_DIVE_OFF,NO_DIVE_REASON,DIST_SELL_TIME,SELL_CONSULTANT,SELL_FOLLOW_TIME,IMP_DATE,IMP_NUMBER,WILLING_TO_SHOP,WILLING_TO_DRIVING) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        dataStmt = conn.prepareStatement(sql);
        for (FcLeadsDataBean m : mList)
        {
          dataStmt.setString(1, m.getSellCreateDate());
          dataStmt.setString(2, m.getSellDealerTime());
          dataStmt.setString(3, m.getSellNo());
          dataStmt.setString(4, m.getSellStatus());
          dataStmt.setString(5, m.getPhoneStatus());
          dataStmt.setString(6, m.getIntentLevel());
          dataStmt.setString(7, m.getIntentModels());
          dataStmt.setString(8, m.getIsDiveOff());
          dataStmt.setString(9, m.getNoDiveReason());
          dataStmt.setString(10, m.getDistSellTime());
          dataStmt.setString(11, m.getSellConsultant());
          dataStmt.setString(12, m.getSellFollowTime());
          dataStmt.setString(13, m.getImpDate());
          dataStmt.setString(14, m.getImpNumber());
          dataStmt.setString(15, m.getWillingToShop());
          dataStmt.setString(16, m.getWillingToDriving());
          dataStmt.addBatch();
        }
        dataStmt.executeBatch();
        conn.commit();
      }
    }
    catch (Exception e)
    {
      log.error("[插入FC_SS_SAP_LEADS_DATA数据]异常", e);
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

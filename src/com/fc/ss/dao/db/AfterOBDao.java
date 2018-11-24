package com.fc.ss.dao.db;

import com.fc.dao.conn.DBConnection;
import com.fc.ss.bean.FcAfterOBBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.apache.log4j.Logger;

public class AfterOBDao
{
  private static Logger log = Logger.getLogger(AfterOBDao.class);
  
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
      String sql = "select count(1) from fc_ss_sap_afterob g where g.local_to_dealer_id=?";
      //String sql = "select count(1) from fc_ss_sap_afterob g where g.local_to_dealer_id = '" + localToDealerId + "'";
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
        catch (Exception e1)
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
  
  public void InsertData(List<FcAfterOBBean> mList)
    throws Exception
  {
    PreparedStatement dataStmt = null;
    PreparedStatement dataStmt1 = null;
    Connection conn = null;
    
    log.info("[插入数据]开始");
    try
    {
      conn = DBConnection.getConnection();
      conn.setAutoCommit(false);
      if ((mList != null) && (mList.size() > 0))
      {
        String sql = "INSERT INTO FC_SS_SAP_AFTEROB(JOB_TITLE,NAME,MOBILE,MOBILE_2,HOMEPHONE,TELEPHONE,EMAIL,DATEOFBIRTH,EDUCATIONLEVEL,GENDER,MARITALSTATUS,PROVINCE,CITY,ADDRESS,MONTHLYHOUSEHOLDINCOME,OCCUPATION,PRIMARYCOMMUNICATION,SECONDARYCOMMUNICATION,ESTIMATE_BUYCARTIME_BEFOREOB,INTERESTED_CAR_MODEL_BEFOREOB,INTERESTED_CAR_SERIES_BEFOREOB,ESTIMATE_BUYCARTIME_AFTEROB,INTERESTED_CAR_MODEL_AFTEROB,INTERESTED_CAR_SERIES_AFTEROB,START_OB_DATE,MEDIASOURCE,CALL_ATTEMPT,VERIFY_STATUS,VERIFY_MODE,LOCAL_TO_DEALER_ID,IMPORT_DATE,MEDIA_CODEID,MEMO1,MEMO2,MEMO3,MEMO4,MEMO5,SYMBOL,CAMPAIGNNAME_CHI,CAMPAIGNNAME_ENG,CREATE_DATE,IS_DOWNLOAD,ASSIGN_DEALER_NAME,ASSIGN_DEALER_CODE,EXECUTION_DATE,RECEIVE_DATE,LEADS_TYPE,IS_HOT_LEADS,BUDGET_RANGE) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        dataStmt = conn.prepareStatement(sql);
        for (FcAfterOBBean m : mList)
        {
          dataStmt.setString(1, m.getJobTitle());
          dataStmt.setString(2, m.getName());
          dataStmt.setString(3, m.getMobile());
          dataStmt.setString(4, m.getMobile2());
          dataStmt.setString(5, m.getHomePhone());
          dataStmt.setString(6, m.getTelephone());
          dataStmt.setString(7, m.getEmail());
          if (m.getDateOfBirth() == null) {
            dataStmt.setDate(8, null);
          } else {
            dataStmt.setDate(8, new java.sql.Date(m.getDateOfBirth().getTime()));
          }
          dataStmt.setString(9, m.getEducationLevel());
          dataStmt.setString(10, m.getGender());
          dataStmt.setString(11, m.getMaritalStatus());
          dataStmt.setString(12, m.getProvince());
          dataStmt.setString(13, m.getCity());
          dataStmt.setString(14, m.getAddress());
          dataStmt.setString(15, m.getMonthlyHouseholdIncome());
          dataStmt.setString(16, m.getOccupation());
          dataStmt.setString(17, m.getPrimaryCommunication());
          dataStmt.setString(18, m.getSecondaryCommunication());
          dataStmt.setString(19, m.getEstimateBuycartimeBeforeOB());
          dataStmt.setString(20, m.getInterestedCarModelBeforeOB());
          dataStmt.setString(21, m.getInterestedCarSeriesBeforeOB());
          dataStmt.setString(22, m.getEstimateBuycartimeAfterOB());
          dataStmt.setString(23, m.getInterestedCarCodelAfterOB());
          dataStmt.setString(24, m.getInterestedCarEeriesAfterOB());
          dataStmt.setString(25, m.getStartOBDate());
          dataStmt.setString(26, m.getMediaSource());
          dataStmt.setString(27, m.getCallAttempt());
          dataStmt.setString(28, m.getVerifyStatus());
          dataStmt.setString(29, m.getVerifyMode());
          dataStmt.setString(30, m.getLocalToDealerId());
          dataStmt.setDate(31, new java.sql.Date(new java.util.Date().getTime()));
          dataStmt.setString(32, m.getMediaCodeId());
          dataStmt.setString(33, m.getMemo1());
          dataStmt.setString(34, m.getMemo2());
          dataStmt.setString(35, m.getMemo3());
          dataStmt.setString(36, m.getMemo4());
          dataStmt.setString(37, m.getMemo5());
          dataStmt.setString(38, m.getSymbol());
          dataStmt.setString(39, m.getCampaignnameChi());
          dataStmt.setString(40, m.getCampaignnameEng());
          dataStmt.setString(41, m.getCreateDate());
          dataStmt.setString(42, m.getIsDownload());
          dataStmt.setString(43, m.getAssignDealerName());
          dataStmt.setString(44, m.getAssignDealerCode());
          if (m.getExecutionDate() == null) {
            dataStmt.setDate(45, null);
          } else {
            dataStmt.setDate(45, new java.sql.Date(m.getExecutionDate().getTime()));
          }
          dataStmt.setString(46, m.getReceiveDate());
          dataStmt.setString(47, m.getLeadsType());
          dataStmt.setString(48, m.getIsHotLeads());
          dataStmt.setString(49, m.getBudgetRange());
          dataStmt.addBatch();
        }
        dataStmt.executeBatch();
        conn.commit();
      }
    }
    catch (Exception e)
    {
      log.error("[插入FC_SS_SAP_AFTEROB数据]异常", e);
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

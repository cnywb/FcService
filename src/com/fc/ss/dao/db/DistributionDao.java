package com.fc.ss.dao.db;

import com.fc.dao.conn.DBConnection;
import com.fc.ss.bean.FcDistributionBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.apache.log4j.Logger;

public class DistributionDao
{
  private static Logger log = Logger.getLogger(DistributionDao.class);
  
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
     // String sql = "select count(1) from fc_ss_sap_distribution g where g.local_to_dealer_id = '" + localToDealerId + "'";
      String sql = "select count(1) from fc_ss_sap_distribution g where g.local_to_dealer_id=?";

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
  
  public void InsertData(List<FcDistributionBean> mList)
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
        String sql = "INSERT INTO FC_SS_SAP_DISTRIBUTION(EXECUTION_DATE,NAME,GENDER,LEADS_LEVEL,MOBILE,MOBILE_2,TELEPHONE,HOMEPHONE,PROVINCE,CITY,INTERESTED_CAR_MODEL,ESTIMATE_BUYCARTIME,TYPE_OF_DEMAND,ASSIGN_DEALER_CODE,DEALER_ZONE,ASSIGN_DEALER_NAME,LOCAL_TO_DEALER_ID,MEDIA_SOURCE_TYPE,CUSTOMER_SOURCE,DEALER_FEEDBACK,DEALER_FEEDBACK_TIME,IS_LEAD_PROFILING,SYSTEM_TIME,MEDIA_CODEID,DMS_DISTRIBUTE_TO_DEALER_TIME,DISTRIBUTION_STATUS,MEMO1,MEMO2,MEMO3,MEMO4,MEMO5,CAMPAIGNNAME_CHI,CAMPAIGNNAME_ENG,IS_DOWNLOAD,CREATE_DATE,MEDIASOURCE,IMPORT_DATE,WILLING_TO_SHOP,WILLING_TO_DRIVING,IS_FIRST_SHOWROOM,FIRST_SHOWROOM_TIME,IS_SECOND_SHOWROOM,SECOND_SHOWROOM_TIME,IS_TEST_DRIVE,TEST_DRIVE_TIME,OB_CALL_STATUS,IS_FOLLOW_UP,DEALER_CODE,DEALER_NAME,CUS_RECORD_DATE,CUS_CREATE_DATE,RECEIVE_DATE) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        dataStmt = conn.prepareStatement(sql);
        for (FcDistributionBean m : mList)
        {
          dataStmt.setString(1, m.getExecutionDate());
          dataStmt.setString(2, m.getName());
          dataStmt.setString(3, m.getGender());
          dataStmt.setString(4, m.getLeadsLevel());
          dataStmt.setString(5, m.getMobile());
          dataStmt.setString(6, m.getSecondaryMobilePhone());
          dataStmt.setString(7, m.getWorkPhone());
          dataStmt.setString(8, m.getHomePhone());
          dataStmt.setString(9, m.getProvince());
          dataStmt.setString(10, m.getCity());
          dataStmt.setString(11, m.getVechileOfInterests());
          dataStmt.setString(12, m.getIntendedPurchaseTime());
          dataStmt.setString(13, m.getTypeOfDemand());
          dataStmt.setString(14, m.getAssignDealerCode());
          dataStmt.setString(15, m.getDealerZone());
          dataStmt.setString(16, m.getAssignDealerName());
          dataStmt.setString(17, m.getLocalToDealerId());
          dataStmt.setString(18, m.getMediaSourceType());
          dataStmt.setString(19, m.getCustomerSource());
          dataStmt.setString(20, m.getDealerFeedBack());
          if (m.getDealerFeedBackTime() == null) {
            dataStmt.setDate(21, null);
          } else {
            dataStmt.setDate(21, new java.sql.Date(m.getDealerFeedBackTime().getTime()));
          }
          dataStmt.setString(22, m.getSalesProfileCreatedFlag());
          dataStmt.setDate(23, new java.sql.Date(new java.util.Date().getTime()));
          dataStmt.setString(24, m.getChannel());
          if (m.getDistributionTimeOfData() == null) {
            dataStmt.setDate(25, null);
          } else {
            dataStmt.setDate(25, new java.sql.Date(m.getDistributionTimeOfData().getTime()));
          }
          dataStmt.setString(26, m.getDistributionStatus());
          dataStmt.setString(27, m.getMemo1());
          dataStmt.setString(28, m.getMemo2());
          dataStmt.setString(29, m.getMemo3());
          dataStmt.setString(30, m.getMemo4());
          dataStmt.setString(31, m.getMemo5());
          dataStmt.setString(32, m.getCampaignNameChi());
          dataStmt.setString(33, m.getCampaignNameEng());
          dataStmt.setString(34, "否");
          dataStmt.setString(35, m.getCreateDate());
          dataStmt.setString(36, m.getMediasource());
          dataStmt.setDate(37, new java.sql.Date(new java.util.Date().getTime()));
          dataStmt.setString(38, m.getWillingToShop());
          dataStmt.setString(39, m.getWillingToDriving());
          dataStmt.setString(40, m.getIsFirstShowroom());
          dataStmt.setString(41, m.getFirstShowroomTime());
          dataStmt.setString(42, m.getIsSecondShowroom());
          dataStmt.setString(43, m.getSecondShowroomTime());
          dataStmt.setString(44, m.getIsTestDrive());
          dataStmt.setString(45, m.getTestDriveTime());
          dataStmt.setString(46, m.getObCallStatus());
          dataStmt.setString(47, m.getIsFollowUp());
          dataStmt.setString(48, m.getDealerCode());
          dataStmt.setString(49, m.getDealerName());
          dataStmt.setString(50, m.getCusRecordDate());
          dataStmt.setString(51, m.getCusCreateDate());
          dataStmt.setString(52, m.getReceiveDate());
          
          dataStmt.addBatch();
        }
        dataStmt.executeBatch();
        conn.commit();
      }
    }
    catch (Exception e)
    {
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

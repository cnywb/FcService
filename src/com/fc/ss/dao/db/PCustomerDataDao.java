package com.fc.ss.dao.db;

import com.fc.dao.conn.DBConnection;
import com.fc.ss.bean.FcPotentialCustomerDataBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.apache.log4j.Logger;

public class PCustomerDataDao
{
  private static Logger log = Logger.getLogger(PCustomerDataDao.class);
  
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
      //String sql = "select count(1) from FC_SS_SAP_P_CUSTOMER_DATA g where g.SELL_NO = '" + localToDealerId + "'";
        String sql = "select count(1) from FC_SS_SAP_P_CUSTOMER_DATA g where g.SELL_NO=?";
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
  
  public void InsertData(List<FcPotentialCustomerDataBean> mList)
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
        String sql = "INSERT INTO FC_SS_SAP_P_CUSTOMER_DATA(SELL_NO,CLIENT_NO,NAME,SEX,MOBILE,PHONE,EMAIL,SOURCE,CHANNEL,ACTIVITY,IS_CHILD,IS_CHAR,PAY_CHAR_FACTOR,INIT_LEVEL,INTENT_LEVEL,SALES_GW,IS_TO_SHOP,TO_SHOP_TIME,IS_TEST_DRIVE,TEST_DRIVE_TIME,IS_TWO_TO_SHOP,TWO_TO_SHOP_TIME,CAR_BRAND,CAR_MODEL,INTENT_BRAND,INTENT_CARS,INTENT_CAR_MODEL,DEFEAT_TYPE,DEFEAT_MODELS,MODELS_REASON,IMP_DATE,IMP_NUMBER,DEALER_CODE,DEALER_NAME,CUS_RECORD_DATE,CUS_CREATE_DATE,CRM_CUSTOMER_NUMBER,BIRTH_DAY,PROVINCE,CITY,POSTCODE,ADDRESS,IF_FIRST_PURCHASE,CHILDREN_N,BUDGET) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        dataStmt = conn.prepareStatement(sql);
        for (FcPotentialCustomerDataBean m : mList)
        {
          dataStmt.setString(1, m.getSellNo());
          dataStmt.setString(2, m.getClientNo());
          dataStmt.setString(3, m.getName());
          dataStmt.setString(4, m.getSex());
          dataStmt.setString(5, m.getMobile());
          dataStmt.setString(6, m.getPhone());
          dataStmt.setString(7, m.getEmail());
          dataStmt.setString(8, m.getSource());
          dataStmt.setString(9, m.getChannel());
          dataStmt.setString(10, m.getAcitity());
          dataStmt.setString(11, m.getIsChild());
          dataStmt.setString(12, m.getIsChar());
          dataStmt.setString(13, m.getPayCharFactor());
          dataStmt.setString(14, m.getInitLevel());
          dataStmt.setString(15, m.getIntentLevel());
          dataStmt.setString(16, m.getSalesGw());
          dataStmt.setString(17, m.getIsToShop());
          dataStmt.setString(18, m.getToShopTime());
          dataStmt.setString(19, m.getIsTestDrive());
          dataStmt.setString(20, m.getTestDriveTime());
          dataStmt.setString(21, m.getIsTwoToShop());
          dataStmt.setString(22, m.getTwoToShopTime());
          dataStmt.setString(23, m.getCarBrand());
          dataStmt.setString(24, m.getCarModel());
          dataStmt.setString(25, m.getIntentBrand());
          dataStmt.setString(26, m.getIntentCars());
          dataStmt.setString(27, m.getIntentCarModel());
          dataStmt.setString(28, m.getDefeatType());
          dataStmt.setString(29, m.getDefeatModels());
          dataStmt.setString(30, m.getModelsReason());
          dataStmt.setString(31, m.getImpDate());
          dataStmt.setString(32, m.getImpNumber());
          dataStmt.setString(33, m.getDealerCode());
          dataStmt.setString(34, m.getDealerName());
          dataStmt.setString(35, m.getCusRecordDate());
          dataStmt.setString(36, m.getCusCreateDate());
          dataStmt.setString(37, m.getCrmCustomerNumber());
          dataStmt.setString(38, m.getBirthDay());
          dataStmt.setString(39, m.getProvince());
          dataStmt.setString(40, m.getCity());
          dataStmt.setString(41, m.getPostCode());
          dataStmt.setString(42, m.getAddress());
          dataStmt.setString(43, m.getIfFirstPurchase());
          dataStmt.setString(44, m.getChildrenN());
          dataStmt.setString(45, m.getBudget());
          dataStmt.addBatch();
        }
        dataStmt.executeBatch();
        conn.commit();
      }
    }
    catch (Exception e)
    {
      log.error("[插入FC_SS_SAP_P_CUSTOMER_DATA数据]异常", e);
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

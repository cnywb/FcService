package com.fc.dao.db;

import com.fc.bean.DmsAddItemBean;
import com.fc.bean.DmsDealBean;
import com.fc.bean.DmsDictionaryBean;
import com.fc.bean.DmsElecBean;
import com.fc.bean.DmsLabourPartBean;
import com.fc.bean.DmsPartsBean;
import com.fc.bean.DmsRepairOrderBean;
import com.fc.bean.DmsRepairPartBean;
import com.fc.bean.DmsSalesPartBean;
import com.fc.bean.DmsSubCallCenterBean;
import com.fc.bean.DmsToDataBean;
import com.fc.bean.DmsToErorBean;
import com.fc.bean.DmsToLogBean;
import com.fc.bean.FcServiceLogBean;
import com.fc.bean.M2FServiceLogBean;
import com.fc.bean.M2FToCcDataBean;
import com.fc.bean.M2FToDmsDataBean;
import com.fc.bean.M2FUserDataBean;
import com.fc.bean.UploadDmsDataErrorBean;
import com.fc.dao.conn.DBConnection;
import com.fc.util.ExtendedPropUtils;
import com.fc.util.PageBean;
import com.fc.util.XmlSaveUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.log4j.Logger;

public class SaveDmsDataDao
{
  private static Logger log = Logger.getLogger(SaveDmsDataDao.class);
  private List list = new ArrayList();
  
  public void saveToDmsData(List<DmsToDataBean> dmsToData, List<DmsToErorBean> dmsToEror, String fileName)
    throws Exception
  {
    PreparedStatement dmsToDataStmt = null;
    Connection conn = null;
    Connection conn1 = null;
    try
    {
      conn = DBConnection.getConnection();
      DmsToDataBean toDataBean;
      if ((dmsToData != null) && (dmsToData.size() > 0))
      {
        String importDate = null;
        conn.setAutoCommit(false);
        log
          .info("[保存车主相关数据开始]" + 
          XmlSaveUtil.formatDate(new Date(), 1));
        dmsToDataStmt = conn
          .prepareStatement("INSERT INTO FC_DMS_TO_DATA_TEMP VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        for (Iterator localIterator = dmsToData.iterator(); localIterator.hasNext();)
        {
          toDataBean = (DmsToDataBean)localIterator.next();
          dmsToDataStmt.setString(1, toDataBean.getOwner_id());
          dmsToDataStmt.setString(2, toDataBean.getBrand());
          dmsToDataStmt.setString(3, toDataBean.getSerise());
          dmsToDataStmt.setString(4, toDataBean.getModel());
          dmsToDataStmt.setString(5, toDataBean.getColor());
          dmsToDataStmt.setString(6, toDataBean.getVin());
          dmsToDataStmt.setString(7, toDataBean.getEngine_no());
          dmsToDataStmt.setString(8, toDataBean.getRadio_no());
          dmsToDataStmt.setString(9, toDataBean.getKey_no());
          dmsToDataStmt.setString(10, toDataBean.getLisence_no());
          dmsToDataStmt.setString(11, toDataBean.getSales_date());
          dmsToDataStmt.setString(12, toDataBean
            .getDealer_sale_code());
          dmsToDataStmt.setString(13, toDataBean.getShort_name());
          dmsToDataStmt.setString(14, toDataBean.getDealer_id());
          dmsToDataStmt.setString(15, toDataBean.getDealer_name());
          dmsToDataStmt.setString(16, toDataBean.getSeller());
          dmsToDataStmt.setString(17, toDataBean
            .getGuarantee_start_date());
          dmsToDataStmt.setString(18, toDataBean
            .getGuarantee_end_date());
          dmsToDataStmt.setString(19, toDataBean
            .getIs_delay_service());
          dmsToDataStmt.setString(20, toDataBean.getIs_wholesale());
          dmsToDataStmt.setString(21, toDataBean.getUse_type());
          dmsToDataStmt.setString(22, toDataBean.getPay_type());
          dmsToDataStmt.setDouble(23, toDataBean.getInmileage().doubleValue());
          dmsToDataStmt.setString(24, toDataBean
            .getLast_maintain_date());
          dmsToDataStmt.setString(25, toDataBean.getInvoice_no());
          dmsToDataStmt.setString(26, toDataBean.getOwner_name());
          dmsToDataStmt.setString(27, toDataBean.getContactor_name());
          dmsToDataStmt.setString(28, toDataBean.getOwner_spell());
          dmsToDataStmt.setString(29, toDataBean.getOwner_property());
          dmsToDataStmt.setString(30, toDataBean.getGender());
          dmsToDataStmt.setString(31, toDataBean.getBirthday());
          dmsToDataStmt.setString(32, toDataBean
            .getCertificate_type());
          dmsToDataStmt.setString(33, toDataBean
            .getCertificate_code());
          dmsToDataStmt.setString(34, toDataBean.getIndustry_first());
          dmsToDataStmt
            .setString(35, toDataBean.getIndustry_second());
          dmsToDataStmt.setString(36, toDataBean.getFamily_income());
          dmsToDataStmt.setString(37, toDataBean.getEdu_level());
          dmsToDataStmt.setString(38, toDataBean.getOwner_marriage());
          dmsToDataStmt.setString(39, toDataBean.getChoose_reasion());
          dmsToDataStmt.setString(40, toDataBean.getBuy_purpose());
          dmsToDataStmt.setString(41, toDataBean
            .getWish_connect_type());
          dmsToDataStmt.setString(42, toDataBean.getProvince());
          dmsToDataStmt.setString(43, toDataBean.getCity());
          dmsToDataStmt.setString(44, toDataBean.getDistrict());
          dmsToDataStmt.setString(45, toDataBean.getZip_code());
          dmsToDataStmt.setString(46, toDataBean.getAddress());
          dmsToDataStmt.setString(47, toDataBean.getE_mail());
          dmsToDataStmt.setString(48, toDataBean.getPhone());
          dmsToDataStmt.setString(49, toDataBean.getFax());
          dmsToDataStmt.setString(50, toDataBean.getMobile());
          dmsToDataStmt.setString(51, toDataBean
            .getContactor_mobile());
          dmsToDataStmt
            .setString(52, toDataBean.getContactor_phone());
          dmsToDataStmt
            .setString(53, toDataBean.getContactor_email());
          dmsToDataStmt.setString(54, toDataBean.getRemark());
          dmsToDataStmt.setString(55, toDataBean.getCreate_date());
          dmsToDataStmt.setString(56, toDataBean.getUpdate_date());
          dmsToDataStmt.setString(57, toDataBean.getImportDate());
          dmsToDataStmt.setString(58, toDataBean.getTime_stamp());
          dmsToDataStmt.setString(59, toDataBean.getSequnce());
          dmsToDataStmt.setLong(60, toDataBean.getIs_valid().longValue());
          dmsToDataStmt.setString(61, toDataBean.getBuy_type());
          dmsToDataStmt.setString(62, toDataBean.getCar_dsid());
          dmsToDataStmt.setString(63, toDataBean.getCar_source());
          dmsToDataStmt.setString(64, toDataBean
            .getDealer_service_code());
          dmsToDataStmt.setString(65, toDataBean.getConfgation());
          dmsToDataStmt.setString(66, toDataBean.getCat_code());
          dmsToDataStmt.setString(67, toDataBean
            .getContactor_address());
          dmsToDataStmt.setString(68, toDataBean
            .getContactor_gender());
          dmsToDataStmt
            .setString(69, toDataBean.getTvb_create_date());
          dmsToDataStmt.setString(70, toDataBean.getIsNewOwer());
          dmsToDataStmt.setLong(71, toDataBean.getObCount().intValue());
          dmsToDataStmt.setString(72, toDataBean.getIsOwnerError());
          dmsToDataStmt
            .setString(73, toDataBean.getCreateOwnerDate());
          if (importDate == null) {
            importDate = toDataBean.getCreateOwnerDate();
          }
          dmsToDataStmt.setString(74, toDataBean.getIsObStr());
          dmsToDataStmt.setString(75, toDataBean.getObType());
          dmsToDataStmt.setString(76, toDataBean.getCarSync());
          dmsToDataStmt.setString(77, toDataBean.getCarEa());
          dmsToDataStmt.setString(78, toDataBean.getCatecode());
          dmsToDataStmt.setString(79, toDataBean.getNoObReason());
          dmsToDataStmt.setString(80, toDataBean.getData_id());
          dmsToDataStmt.setString(81, "");
          dmsToDataStmt.setString(82, toDataBean.getIs_odp_updates());
          dmsToDataStmt.setString(83, toDataBean.getSale_dealer_code());
          dmsToDataStmt.setString(84, toDataBean.getSale_service_code());
          dmsToDataStmt.setString(85, toDataBean.getSale_dealer_name());
          dmsToDataStmt.addBatch();
        }
        dmsToDataStmt.executeBatch();
        conn.commit();
      }
      if ((dmsToEror != null) && (dmsToEror.size() > 0))
      {
        conn1 = DBConnection.getConnection();
        conn1.setAutoCommit(false);
        log.info("执行DMS获取空错号数据更新！");
        dmsToDataStmt = conn1
          .prepareStatement("INSERT INTO FC_DMS_TO_ERROR VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        for (DmsToErorBean dmsToErorBean : dmsToEror)
        {
          dmsToDataStmt.setString(1, dmsToErorBean.getOwnerId());
          dmsToDataStmt.setString(2, dmsToErorBean.getVin());
          dmsToDataStmt.setString(3, dmsToErorBean
            .getDealer_sale_code());
          dmsToDataStmt.setString(4, dmsToErorBean
            .getCreateDate());
          dmsToDataStmt.setDate(5, null);
          dmsToDataStmt.setLong(6, dmsToErorBean.getObCount().intValue());
          dmsToDataStmt.setString(7, dmsToErorBean.getRemark());
          dmsToDataStmt
            .setString(8, dmsToErorBean.getOdp1_code());
          dmsToDataStmt
            .setString(9, dmsToErorBean.getOdp2_code());
          dmsToDataStmt.setString(10, dmsToErorBean
            .getOwner_name());
          dmsToDataStmt.setString(11, dmsToErorBean.getGender());
          dmsToDataStmt.setString(12, dmsToErorBean.getAddress());
          dmsToDataStmt.setString(13, dmsToErorBean.getEmail());
          dmsToDataStmt.setString(14, dmsToErorBean.getMobile());
          dmsToDataStmt.setString(15, dmsToErorBean.getPhone());
          dmsToDataStmt.setDate(16, null);
          dmsToDataStmt.setString(17, dmsToErorBean
            .getContactor_name());
          dmsToDataStmt.setString(18, dmsToErorBean
            .getContactor_gender());
          dmsToDataStmt.setString(19, dmsToErorBean
            .getContactor_mobile());
          dmsToDataStmt.setString(20, dmsToErorBean
            .getContactor_phone());
          dmsToDataStmt.setString(21, dmsToErorBean
            .getContactor_province());
          dmsToDataStmt.setString(22, dmsToErorBean
            .getContactor_city());
          dmsToDataStmt.setString(23, dmsToErorBean
            .getContactor_address());
          dmsToDataStmt.setString(24, dmsToErorBean
            .getContactor_email());
          dmsToDataStmt.setString(25, dmsToErorBean.getCertificate_type());
          dmsToDataStmt.setString(26, dmsToErorBean.getCertificate_code());
          dmsToDataStmt.setString(27, dmsToErorBean.getDataType());
          dmsToDataStmt.addBatch();
        }
        dmsToDataStmt.executeBatch();
        conn1.commit();
      }
    }
    catch (Exception e)
    {
      log.error("执行保存数据出现异常", e);
      conn.rollback();
      throw e;
    }
    finally
    {
      if (dmsToDataStmt != null) {
        dmsToDataStmt.close();
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
      if (conn1 != null) {
        try
        {
          conn1.close();
        }
        catch (SQLException e)
        {
          e.printStackTrace();
        }
      }
    }
  }
  
  public void saveToDmsDataByNewOwners(List<DmsToDataBean> dmsToData, List<DmsToErorBean> dmsErortemp, String createDate)
    throws Exception
  {
    PreparedStatement dmsToDataStmt = null;
    Connection conn = null;
    Connection conn1 = null;
    try
    {
      conn = DBConnection.getConnection();
      DmsToDataBean toDataBean;
      if ((dmsToData != null) && (dmsToData.size() > 0))
      {
        String importDate = null;
        



        conn.setAutoCommit(false);
        log
          .info("[保存车主相关数据开始saveToDmsDataByNewOwners]" + 
          XmlSaveUtil.formatDate(new Date(), 1));
        dmsToDataStmt = conn
          .prepareStatement("INSERT INTO FC_DMS_TO_DATA_NEWOWNERS VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        for (Iterator localIterator = dmsToData.iterator(); localIterator.hasNext();)
        {
          toDataBean = (DmsToDataBean)localIterator.next();
          dmsToDataStmt.setString(1, toDataBean.getOwner_id());
          dmsToDataStmt.setString(2, toDataBean.getBrand());
          dmsToDataStmt.setString(3, toDataBean.getSerise());
          dmsToDataStmt.setString(4, toDataBean.getModel());
          dmsToDataStmt.setString(5, toDataBean.getColor());
          dmsToDataStmt.setString(6, toDataBean.getVin());
          dmsToDataStmt.setString(7, toDataBean.getEngine_no());
          dmsToDataStmt.setString(8, toDataBean.getRadio_no());
          dmsToDataStmt.setString(9, toDataBean.getKey_no());
          dmsToDataStmt.setString(10, toDataBean.getLisence_no());
          dmsToDataStmt.setString(11, toDataBean.getSales_date());
          dmsToDataStmt.setString(12, toDataBean
            .getDealer_sale_code());
          dmsToDataStmt.setString(13, toDataBean.getShort_name());
          dmsToDataStmt.setString(14, toDataBean.getDealer_id());
          dmsToDataStmt.setString(15, toDataBean.getDealer_name());
          dmsToDataStmt.setString(16, toDataBean.getSeller());
          dmsToDataStmt.setString(17, toDataBean
            .getGuarantee_start_date());
          dmsToDataStmt.setString(18, toDataBean
            .getGuarantee_end_date());
          dmsToDataStmt.setString(19, toDataBean
            .getIs_delay_service());
          dmsToDataStmt.setString(20, toDataBean.getIs_wholesale());
          dmsToDataStmt.setString(21, toDataBean.getUse_type());
          dmsToDataStmt.setString(22, toDataBean.getPay_type());
          dmsToDataStmt.setDouble(23, toDataBean.getInmileage().doubleValue());
          dmsToDataStmt.setString(24, toDataBean
            .getLast_maintain_date());
          dmsToDataStmt.setString(25, toDataBean.getInvoice_no());
          dmsToDataStmt.setString(26, toDataBean.getOwner_name());
          dmsToDataStmt.setString(27, toDataBean.getContactor_name());
          dmsToDataStmt.setString(28, toDataBean.getOwner_spell());
          dmsToDataStmt.setString(29, toDataBean.getOwner_property());
          dmsToDataStmt.setString(30, toDataBean.getGender());
          dmsToDataStmt.setString(31, toDataBean.getBirthday());
          dmsToDataStmt.setString(32, toDataBean
            .getCertificate_type());
          dmsToDataStmt.setString(33, toDataBean
            .getCertificate_code());
          dmsToDataStmt.setString(34, toDataBean.getIndustry_first());
          dmsToDataStmt
            .setString(35, toDataBean.getIndustry_second());
          dmsToDataStmt.setString(36, toDataBean.getFamily_income());
          dmsToDataStmt.setString(37, toDataBean.getEdu_level());
          dmsToDataStmt.setString(38, toDataBean.getOwner_marriage());
          dmsToDataStmt.setString(39, toDataBean.getChoose_reasion());
          dmsToDataStmt.setString(40, toDataBean.getBuy_purpose());
          dmsToDataStmt.setString(41, toDataBean
            .getWish_connect_type());
          dmsToDataStmt.setString(42, toDataBean.getProvince());
          dmsToDataStmt.setString(43, toDataBean.getCity());
          dmsToDataStmt.setString(44, toDataBean.getDistrict());
          dmsToDataStmt.setString(45, toDataBean.getZip_code());
          dmsToDataStmt.setString(46, toDataBean.getAddress());
          dmsToDataStmt.setString(47, toDataBean.getE_mail());
          dmsToDataStmt.setString(48, toDataBean.getPhone());
          dmsToDataStmt.setString(49, toDataBean.getFax());
          dmsToDataStmt.setString(50, toDataBean.getMobile());
          dmsToDataStmt.setString(51, toDataBean
            .getContactor_mobile());
          dmsToDataStmt
            .setString(52, toDataBean.getContactor_phone());
          dmsToDataStmt
            .setString(53, toDataBean.getContactor_email());
          dmsToDataStmt.setString(54, toDataBean.getRemark());
          dmsToDataStmt.setString(55, toDataBean.getCreate_date());
          dmsToDataStmt.setString(56, toDataBean.getUpdate_date());
          dmsToDataStmt.setString(57, toDataBean.getImportDate());
          dmsToDataStmt.setString(58, toDataBean.getTime_stamp());
          dmsToDataStmt.setString(59, toDataBean.getSequnce());
          dmsToDataStmt.setLong(60, toDataBean.getIs_valid().longValue());
          dmsToDataStmt.setString(61, toDataBean.getBuy_type());
          dmsToDataStmt.setString(62, toDataBean.getCar_dsid());
          dmsToDataStmt.setString(63, toDataBean.getCar_source());
          dmsToDataStmt.setString(64, toDataBean
            .getDealer_service_code());
          dmsToDataStmt.setString(65, toDataBean.getConfgation());
          dmsToDataStmt.setString(66, toDataBean.getCat_code());
          dmsToDataStmt.setString(67, toDataBean
            .getContactor_address());
          dmsToDataStmt.setString(68, toDataBean
            .getContactor_gender());
          dmsToDataStmt
            .setString(69, toDataBean.getSale_date());
          dmsToDataStmt.setString(70, toDataBean.getIsNewOwer());
          dmsToDataStmt.setLong(71, toDataBean.getObCount().intValue());
          dmsToDataStmt.setString(72, toDataBean.getIsOwnerError());
          dmsToDataStmt
            .setString(73, toDataBean.getCreateOwnerDate());
          if (importDate == null) {
            importDate = toDataBean.getCreateOwnerDate();
          }
          dmsToDataStmt.setString(74, toDataBean.getIsObStr());
          dmsToDataStmt.setString(75, toDataBean.getObType());
          dmsToDataStmt.setString(76, toDataBean.getCarSync());
          dmsToDataStmt.setString(77, toDataBean.getCarEa());
          dmsToDataStmt.setString(78, toDataBean.getCatecode());
          dmsToDataStmt.setString(79, toDataBean.getNoObReason());
          dmsToDataStmt.setString(80, toDataBean.getData_id());
          dmsToDataStmt.setString(81, toDataBean.getDup_type());
          dmsToDataStmt.setString(82, createDate);
          dmsToDataStmt.setString(83, toDataBean.getIs_odp_updates());
          dmsToDataStmt.setString(84, toDataBean.getSale_dealer_code());
          dmsToDataStmt.setString(85, toDataBean.getSale_service_code());
          dmsToDataStmt.setString(86, toDataBean.getSale_dealer_name());
          dmsToDataStmt.addBatch();
        }
        dmsToDataStmt.executeBatch();
        conn.commit();
      }
      if ((dmsErortemp != null) && (dmsErortemp.size() > 0))
      {
        conn1 = DBConnection.getConnection();
        conn1.setAutoCommit(false);
        log.info("执行空错号数据更新！saveToDmsDataByNewOwners");
        dmsToDataStmt = conn1
          .prepareStatement("INSERT INTO FC_DMS_TO_ERROR VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        for (DmsToErorBean dmsToErorBean : dmsErortemp)
        {
          dmsToDataStmt.setString(1, dmsToErorBean.getOwnerId());
          dmsToDataStmt.setString(2, dmsToErorBean.getVin());
          dmsToDataStmt.setString(3, dmsToErorBean
            .getDealer_sale_code());
          dmsToDataStmt.setString(4, dmsToErorBean
            .getCreateDate());
          dmsToDataStmt.setDate(5, null);
          dmsToDataStmt.setLong(6, dmsToErorBean.getObCount().intValue());
          dmsToDataStmt.setString(7, dmsToErorBean.getRemark());
          dmsToDataStmt
            .setString(8, dmsToErorBean.getOdp1_code());
          dmsToDataStmt
            .setString(9, dmsToErorBean.getOdp2_code());
          dmsToDataStmt.setString(10, dmsToErorBean
            .getOwner_name());
          dmsToDataStmt.setString(11, dmsToErorBean.getGender());
          dmsToDataStmt.setString(12, dmsToErorBean.getAddress());
          dmsToDataStmt.setString(13, dmsToErorBean.getEmail());
          dmsToDataStmt.setString(14, dmsToErorBean.getMobile());
          dmsToDataStmt.setString(15, dmsToErorBean.getPhone());
          dmsToDataStmt.setDate(16, null);
          dmsToDataStmt.setString(17, dmsToErorBean
            .getContactor_name());
          dmsToDataStmt.setString(18, dmsToErorBean
            .getContactor_gender());
          dmsToDataStmt.setString(19, dmsToErorBean
            .getContactor_mobile());
          dmsToDataStmt.setString(20, dmsToErorBean
            .getContactor_phone());
          dmsToDataStmt.setString(21, dmsToErorBean
            .getContactor_province());
          dmsToDataStmt.setString(22, dmsToErorBean
            .getContactor_city());
          dmsToDataStmt.setString(23, dmsToErorBean
            .getContactor_address());
          dmsToDataStmt.setString(24, dmsToErorBean
            .getContactor_email());
          dmsToDataStmt.setString(25, dmsToErorBean.getCertificate_type());
          dmsToDataStmt.setString(26, dmsToErorBean.getCertificate_code());
          dmsToDataStmt.setString(27, dmsToErorBean.getDataType());
          dmsToDataStmt.addBatch();
        }
        dmsToDataStmt.executeBatch();
        conn1.commit();
      }
    }
    catch (Exception e)
    {
      log.error("执行保存数据出现异常", e);
      conn.rollback();
      throw e;
    }
    finally
    {
      if (dmsToDataStmt != null) {
        dmsToDataStmt.close();
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
      if (conn1 != null) {
        try
        {
          conn1.close();
        }
        catch (SQLException e)
        {
          e.printStackTrace();
        }
      }
    }
  }
  
  public List<DmsToDataBean> searchListByTemp()
    throws Exception
  {
    PreparedStatement dmsToDataStmt = null;
    Connection conn = null;
    List<DmsToDataBean> arrayList = new ArrayList();
    try
    {
      conn = DBConnection.getConnection();
      conn.setAutoCommit(false);
      StringBuffer sqlB = new StringBuffer();
      sqlB.append("select ");
      sqlB.append(" OWNER_ID,BRAND,SERISE,MODEL,COLOR,VIN,ENGINE_NO,RADIO_NO,KEY_NO,LISENCE_NO,SALES_DATE,DEALER_SALE_CODE,SHORT_NAME,DEALER_ID,DEALER_NAME,");
      sqlB.append(" SELLER,GUARANTEE_START_DATE,GUARANTEE_END_DATE,IS_DELAY_SERVICE,IS_WHOLESALE,USE_TYPE,  ");
      sqlB.append(" PAY_TYPE,INMILEAGE,LAST_MAINTAIN_DATE,INVOICE_NO,OWNER_NAME,CONTACTOR_NAME,OWNER_SPELL,");
      sqlB.append(" OWNER_PROPERTY,GENDER,BIRTHDAY,CERTIFICATE_TYPE,CERTIFICATE_CODE,INDUSTRY_FIRST,INDUSTRY_SECOND,FAMILY_INCOME,EDU_LEVEL,  ");
      sqlB.append(" OWNER_MARRIAGE,CHOOSE_REASION,BUY_PURPOSE,WISH_CONNECT_TYPE,PROVINCE,CITY,DISTRICT,ZIP_CODE,ADDRESS,E_MAIL,PHONE,");
      sqlB.append(" FAX,MOBILE,CONTACTOR_MOBILE,CONTACTOR_PHONE,CONTACTOR_EMAIL, ");
      sqlB.append(" REMARK,CREATE_DATE,UPDATE_DATE,IMPORT_DATE,TIME_STAMP,SEQUNCE,IS_VALID,BUY_TYPE, ");
      sqlB.append(" CAR_DSID,CAR_SOURCE,DEALER_SERVICE_CODE,CONFGATION,CAT_CODE,CONTACTOR_ADDRESS,CONTACTOR_GENDER,SALE_DATE,IS_NEW_OWNER,OB_COUNT, ");
      
      sqlB.append(" IS_OWNER_ERROR,CREATE_OWNER_DATE,NO_OB_STR,OB_TYPE  ,CAR_SYNC  ,CAR_EA  ,CATECODE  ,NO_OB_REASON  ,DATA_ID  ,DUP_TYPE,IS_ODP_UPDATES,SALE_DEALER_CODE,SALE_SERVICE_CODE,SALE_DEALER_NAME  ");
      sqlB.append(" from FC_DMS_TO_DATA_TEMP ");
      dmsToDataStmt = conn.prepareStatement(sqlB.toString());
      ResultSet res = dmsToDataStmt.executeQuery();
      if (res != null) {
        while (res.next())
        {
          DmsToDataBean fcBean = new DmsToDataBean();
          fcBean.setOwner_id(res.getString(1));
          fcBean.setBrand(res.getString(2));
          fcBean.setSerise(res.getString(3));
          fcBean.setModel(res.getString(4));
          fcBean.setColor(res.getString(5));
          fcBean.setVin(res.getString(6));
          fcBean.setEngine_no(res.getString(7));
          fcBean.setRadio_no(res.getString(8));
          fcBean.setKey_no(res.getString(9));
          fcBean.setLisence_no(res.getString(10));
          fcBean.setSales_date(res.getString(11));
          fcBean.setDealer_sale_code(res.getString(12));
          fcBean.setShort_name(res.getString(13));
          fcBean.setData_id(res.getString(14));
          fcBean.setDealer_name(res.getString(15));
          fcBean.setSeller(res.getString(16));
          fcBean.setGuarantee_start_date(res.getString(17));
          fcBean.setGuarantee_end_date(res.getString(18));
          fcBean.setIs_delay_service(res.getString(19));
          fcBean.setIs_wholesale(res.getString(20));
          fcBean.setUse_type(res.getString(21));
          fcBean.setPay_type(res.getString(22));
          fcBean.setInmileage(Double.valueOf(res.getDouble(23)));
          fcBean.setLast_maintain_date(res.getString(24));
          fcBean.setInvoice_no(res.getString(25));
          fcBean.setOwner_name(res.getString(26));
          fcBean.setContactor_name(res.getString(27));
          fcBean.setOwner_spell(res.getString(28));
          fcBean.setOwner_property(res.getString(29));
          fcBean.setGender(res.getString(30));
          fcBean.setBirthday(res.getString(31));
          fcBean.setCertificate_type(res.getString(32));
          fcBean.setCertificate_code(res.getString(33));
          fcBean.setIndustry_first(res.getString(34));
          fcBean.setIndustry_second(res.getString(35));
          fcBean.setFamily_income(res.getString(36));
          fcBean.setEdu_level(res.getString(37));
          fcBean.setOwner_marriage(res.getString(38));
          fcBean.setChoose_reasion(res.getString(39));
          fcBean.setBuy_purpose(res.getString(40));
          fcBean.setWish_connect_type(res.getString(41));
          fcBean.setProvince(res.getString(42));
          fcBean.setCity(res.getString(43));
          fcBean.setDistrict(res.getString(44));
          fcBean.setZip_code(res.getString(45));
          fcBean.setAddress(res.getString(46));
          fcBean.setE_mail(res.getString(47));
          fcBean.setPhone(res.getString(48));
          fcBean.setFax(res.getString(49));
          fcBean.setMobile(res.getString(50));
          fcBean.setContactor_mobile(res.getString(51));
          fcBean.setContactor_phone(res.getString(52));
          fcBean.setContactor_email(res.getString(53));
          fcBean.setRemark(res.getString(54));
          fcBean.setCreate_date(res.getString(55));
          fcBean.setUpdate_date(res.getString(56));
          fcBean.setImportDate(res.getString(57));
          fcBean.setTime_stamp(res.getString(58));
          fcBean.setSequnce(res.getString(59));
          fcBean.setIs_valid(Long.valueOf(res.getLong(60)));
          fcBean.setBuy_type(res.getString(61));
          fcBean.setCar_dsid(res.getString(62));
          fcBean.setCar_source(res.getString(63));
          fcBean.setDealer_service_code(res.getString(64));
          fcBean.setConfgation(res.getString(65));
          fcBean.setCat_code(res.getString(66));
          fcBean.setContactor_address(res.getString(67));
          fcBean.setContactor_gender(res.getString(68));
          fcBean.setSale_date(res.getString(69));
          fcBean.setIsNewOwer(res.getString(70));
          fcBean.setObCount(Integer.valueOf(res.getInt(71)));
          
          fcBean.setIsOwnerError(res.getString(72));
          fcBean.setCreateOwnerDate(res.getString(73));
          fcBean.setIsObStr(res.getString(74));
          fcBean.setObType(res.getString(75));
          fcBean.setCarSync(res.getString(76));
          fcBean.setCarEa(res.getString(77));
          fcBean.setCatecode(res.getString(78));
          fcBean.setNoObReason(res.getString(79));
          fcBean.setData_id(res.getString(80));
          fcBean.setDup_type(res.getString(81));
          fcBean.setIs_odp_updates(res.getString(82));
          fcBean.setSale_dealer_code(res.getString(83));
          fcBean.setSale_service_code(res.getString(84));
          fcBean.setSale_dealer_name(res.getString(85));
          arrayList.add(fcBean);
        }
      }
      conn.commit();
    }
    catch (Exception e)
    {
      log.error("查询全局日志异常", e);
      conn.rollback();
      throw e;
    }
    finally
    {
      if (dmsToDataStmt != null) {
        dmsToDataStmt.close();
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
    return arrayList;
  }
  
  public void insertFcDmsTempByNewOwners(List<String> lists, String importDate)
    throws Exception
  {
    log.info("1.清洗当天清洗的新车主数据到temp表中第一步插入数据start");
    PreparedStatement dmsToDataStmt = null;
    Connection conn = null;
    try
    {
      conn = DBConnection.getConnection();
      log.info("插入当天清洗的新车主数据到temp");
      int day = 0;
      if ((lists.size() > 0) && (lists != null))
      {
        log.info("[获取预发送CallCenter数据]时间差结果1：" + (String)lists.get(0));
        day = Integer.parseInt((String)lists.get(0));
        log.info("[获取预发送CallCenter数据]时间差结果2：" + day);
      }
      SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
      Date importDate_n = sdf.parse(importDate);
      DateFormat formaty = new SimpleDateFormat("yyyyMMdd");
      String endDate = formaty.format(importDate_n);
      Calendar calendar = new GregorianCalendar();
      
      calendar.setTime(importDate_n);
      calendar.add(5, -day);
      importDate_n = calendar.getTime();
      String createDate = formaty.format(importDate_n);
      
      log.info("[1.清洗当天清洗的新车主数据到temp表中第一步插入数据时间" + createDate);
      StringBuffer sqlB = new StringBuffer();
      sqlB.append(" insert into fc_dms_to_data_temp  ");
      sqlB.append(" (OWNER_ID,BRAND,SERISE,MODEL,COLOR,VIN,ENGINE_NO,RADIO_NO,KEY_NO,LISENCE_NO,SALES_DATE,DEALER_SALE_CODE,SHORT_NAME,DEALER_ID,DEALER_NAME,SELLER,GUARANTEE_START_DATE,GUARANTEE_END_DATE,IS_DELAY_SERVICE,IS_WHOLESALE,USE_TYPE, ");
      sqlB.append(" PAY_TYPE,INMILEAGE,LAST_MAINTAIN_DATE,INVOICE_NO,OWNER_NAME,CONTACTOR_NAME,OWNER_SPELL,OWNER_PROPERTY,GENDER,BIRTHDAY,CERTIFICATE_TYPE,CERTIFICATE_CODE,INDUSTRY_FIRST,INDUSTRY_SECOND,FAMILY_INCOME,EDU_LEVEL, ");
      sqlB.append(" OWNER_MARRIAGE,CHOOSE_REASION,BUY_PURPOSE,WISH_CONNECT_TYPE,PROVINCE,CITY,DISTRICT,ZIP_CODE,ADDRESS,E_MAIL,PHONE,FAX,MOBILE,CONTACTOR_MOBILE,CONTACTOR_PHONE,CONTACTOR_EMAIL, ");
      sqlB.append(" REMARK, create_date,UPDATE_DATE,IMPORT_DATE,TIME_STAMP,SEQUNCE,IS_VALID,BUY_TYPE,CAR_DSID,CAR_SOURCE,DEALER_SERVICE_CODE,CONFGATION,CAT_CODE,CONTACTOR_ADDRESS,CONTACTOR_GENDER,SALE_DATE,IS_NEW_OWNER,OB_COUNT, ");
      sqlB.append(" IS_OWNER_ERROR,CREATE_OWNER_DATE,NO_OB_STR,OB_TYPE  ,CAR_SYNC  ,CAR_EA  ,CATECODE  ,NO_OB_REASON  ,DATA_ID  ,DUP_TYPE,IS_ODP_UPDATES,sale_dealer_code,sale_service_code,sale_dealer_name  )  ");
      sqlB.append(" select OWNER_ID,BRAND,SERISE,MODEL,COLOR,VIN,ENGINE_NO,RADIO_NO,KEY_NO,LISENCE_NO,SALES_DATE,DEALER_SALE_CODE,SHORT_NAME,DEALER_ID,DEALER_NAME,SELLER,GUARANTEE_START_DATE,GUARANTEE_END_DATE,IS_DELAY_SERVICE,IS_WHOLESALE, ");
      sqlB.append(" USE_TYPE,PAY_TYPE,INMILEAGE,LAST_MAINTAIN_DATE,INVOICE_NO,OWNER_NAME,CONTACTOR_NAME,OWNER_SPELL,OWNER_PROPERTY,GENDER,BIRTHDAY,CERTIFICATE_TYPE,CERTIFICATE_CODE,INDUSTRY_FIRST,INDUSTRY_SECOND,FAMILY_INCOME,EDU_LEVEL  , ");
      sqlB.append(" OWNER_MARRIAGE  ,CHOOSE_REASION  ,BUY_PURPOSE  ,WISH_CONNECT_TYPE  ,PROVINCE  ,CITY  ,DISTRICT  ,ZIP_CODE  ,ADDRESS  ,E_MAIL  ,PHONE  ,FAX  ,MOBILE  ,CONTACTOR_MOBILE  ,CONTACTOR_PHONE  ,CONTACTOR_EMAIL  ,REMARK  ,create_date ,UPDATE_DATE  ,IMPORT_DATE  ,TIME_STAMP  ,SEQUNCE  ,IS_VALID  ,BUY_TYPE  ,CAR_DSID  ,CAR_SOURCE  ,DEALER_SERVICE_CODE  , ");
      sqlB.append(" CONFGATION  ,CAT_CODE  ,CONTACTOR_ADDRESS  ,CONTACTOR_GENDER  ,SALE_DATE  ,IS_NEW_OWNER  ,OB_COUNT  ,IS_OWNER_ERROR  ,CREATE_OWNER_DATE  , ");
      sqlB.append(" NO_OB_STR  ,OB_TYPE  ,CAR_SYNC  ,CAR_EA  ,CATECODE  ,NO_OB_REASON  ,DATA_ID  , ");
      sqlB.append(" DUP_TYPE,IS_ODP_UPDATES,sale_dealer_code,sale_service_code,sale_dealer_name  from  ");
      sqlB.append(" ( select c.*,row_number() OVER (PARTITION BY vin ORDER BY c.CREATE_DATE desc) AS NUM ");
      sqlB.append(" FROM FC_DMS_TO_DATA c ");
      sqlB.append(" where to_date(c.CREATE_OWNER_DATE,'yyyyMMDD')>=to_date('" + createDate + "','yyyyMMDD') ");
      
      sqlB.append(" and  to_date(c.CREATE_OWNER_DATE,'yyyyMMDD')<=to_date('" + endDate + "','yyyyMMDD') ");
      sqlB.append(" and substr(c.sale_date, 0, 10) =to_char(to_date('" + createDate + "','yyyymmdd'),'yyyy-mm-dd')  ");
      sqlB.append(" and c.DEALER_SALE_CODE=c.sale_dealer_code ");
      sqlB.append(" ) d WHERE d.NUM=1 ");
      dmsToDataStmt = conn.prepareStatement(sqlB.toString());
      conn = DBConnection.getConnection();
      conn.setAutoCommit(false);
      dmsToDataStmt.addBatch();
      dmsToDataStmt.executeBatch();
      conn.commit();
    }
    catch (Exception e)
    {
      log.error("插入当天清洗的新车主数据到temp", e);
      conn.rollback();
      throw e;
    }
    finally
    {
      if (dmsToDataStmt != null) {
        dmsToDataStmt.close();
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
    log.info("1.清洗当天清洗的新车主数据到temp表中第一步插入数据end");
  }
  
  public void insertFcDmsNewOwnersByNewOwners()
    throws Exception
  {
    log.info("3.把temp表中的新车主数据插入到正式表中start ");
    PreparedStatement dmsToDataStmt = null;
    Connection conn = null;
    try
    {
      conn = DBConnection.getConnection();
      
      log.info("插入当天清洗的新车主数据到FC_DMS_TO_DATA_NEWOWNERS");
      StringBuffer sqlB = new StringBuffer();
      sqlB.append(" insert into FC_DMS_TO_DATA_NEWOWNERS  ");
      sqlB.append(" (OWNER_ID,BRAND,SERISE,MODEL,COLOR,VIN,ENGINE_NO,RADIO_NO,KEY_NO,LISENCE_NO,SALES_DATE,DEALER_SALE_CODE,SHORT_NAME,DEALER_ID,DEALER_NAME,SELLER,GUARANTEE_START_DATE,GUARANTEE_END_DATE,IS_DELAY_SERVICE,IS_WHOLESALE,USE_TYPE, ");
      sqlB.append(" PAY_TYPE,INMILEAGE,LAST_MAINTAIN_DATE,INVOICE_NO,OWNER_NAME,CONTACTOR_NAME,OWNER_SPELL,OWNER_PROPERTY,GENDER,BIRTHDAY,CERTIFICATE_TYPE,CERTIFICATE_CODE,INDUSTRY_FIRST,INDUSTRY_SECOND,FAMILY_INCOME,EDU_LEVEL, ");
      sqlB.append(" OWNER_MARRIAGE,CHOOSE_REASION,BUY_PURPOSE,WISH_CONNECT_TYPE,PROVINCE,CITY,DISTRICT,ZIP_CODE,ADDRESS,E_MAIL,PHONE,FAX,MOBILE,CONTACTOR_MOBILE,CONTACTOR_PHONE,CONTACTOR_EMAIL, ");
      sqlB.append(" REMARK,CREATE_DATE,UPDATE_DATE,IMPORT_DATE,TIME_STAMP,SEQUNCE,IS_VALID,BUY_TYPE,CAR_DSID,CAR_SOURCE,DEALER_SERVICE_CODE,CONFGATION,CAT_CODE,CONTACTOR_ADDRESS,CONTACTOR_GENDER,SALE_DATE,IS_NEW_OWNER,OB_COUNT, ");
      sqlB.append(" IS_OWNER_ERROR,CREATE_OWNER_DATE,NO_OB_STR,OB_TYPE  ,CAR_SYNC  ,CAR_EA  ,CATECODE  ,NO_OB_REASON  ,DATA_ID  ,DUP_TYPE  )  ");
      sqlB.append(" select OWNER_ID,BRAND,SERISE,MODEL,COLOR,VIN,ENGINE_NO,RADIO_NO,KEY_NO,LISENCE_NO,SALES_DATE,DEALER_SALE_CODE,SHORT_NAME,DEALER_ID,DEALER_NAME,SELLER,GUARANTEE_START_DATE,GUARANTEE_END_DATE,IS_DELAY_SERVICE,IS_WHOLESALE, ");
      sqlB.append(" USE_TYPE,PAY_TYPE,INMILEAGE,LAST_MAINTAIN_DATE,INVOICE_NO,OWNER_NAME,CONTACTOR_NAME,OWNER_SPELL,OWNER_PROPERTY,GENDER,BIRTHDAY,CERTIFICATE_TYPE,CERTIFICATE_CODE,INDUSTRY_FIRST,INDUSTRY_SECOND,FAMILY_INCOME,EDU_LEVEL  , ");
      sqlB.append(" OWNER_MARRIAGE  ,CHOOSE_REASION  ,BUY_PURPOSE  ,WISH_CONNECT_TYPE  ,PROVINCE  ,CITY  ,DISTRICT  ,ZIP_CODE  ,ADDRESS  ,E_MAIL  ,PHONE  ,FAX  ,MOBILE  ,CONTACTOR_MOBILE  ,CONTACTOR_PHONE  ,CONTACTOR_EMAIL  ,REMARK  ,CREATE_DATE  ,UPDATE_DATE  ,IMPORT_DATE  ,TIME_STAMP  ,SEQUNCE  ,IS_VALID  ,BUY_TYPE  ,CAR_DSID  ,CAR_SOURCE  ,DEALER_SERVICE_CODE  , ");
      sqlB.append(" CONFGATION  ,CAT_CODE  ,CONTACTOR_ADDRESS  ,CONTACTOR_GENDER  ,SALE_DATE  ,IS_NEW_OWNER  ,OB_COUNT  ,IS_OWNER_ERROR  ,CREATE_OWNER_DATE  , ");
      sqlB.append(" NO_OB_STR  ,OB_TYPE  ,CAR_SYNC  ,CAR_EA  ,CATECODE  ,NO_OB_REASON  ,DATA_ID  , ");
      sqlB.append(" DUP_TYPE  from fc_dms_to_data_temp t ");
      dmsToDataStmt = conn
        .prepareStatement(sqlB.toString());
      conn = DBConnection.getConnection();
      conn.setAutoCommit(false);
      dmsToDataStmt.addBatch();
      dmsToDataStmt.executeBatch();
      conn.commit();
    }
    catch (Exception e)
    {
      log.error("插入当天清洗的新车主数据到FC_DMS_TO_DATA_NEWOWNERS", e);
      conn.rollback();
      throw e;
    }
    finally
    {
      if (dmsToDataStmt != null) {
        dmsToDataStmt.close();
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
    log.info("3.把temp表中的新车主数据插入到正式表中end");
  }
  
  public void updateFcDmsDictionaryTempByProc()
    throws Exception
  {
    log.info("[更新数据字典表数据]开始");
    PreparedStatement dmsToDataStmt = null;
    Connection conn = null;
    try
    {
      conn = DBConnection.getConnection();
      conn.setAutoCommit(false);
      dmsToDataStmt = conn.prepareCall("{call PROC_DICTIONARY_MERGE()}");
      dmsToDataStmt.addBatch();
      dmsToDataStmt.executeBatch();
      conn.commit();
    }
    catch (Exception e)
    {
      log.error("[更新数据字典表数据]出现异常", e);
      conn.rollback();
      throw e;
    }
    finally
    {
      if (dmsToDataStmt != null) {
        dmsToDataStmt.close();
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
  
  public void updateFcDmsDictionaryTemp()
    throws Exception
  {
    log.info("更新数据字典表数据updateFcDmsDictionaryTemp  start ");
    PreparedStatement dmsToDataStmt = null;
    Connection conn = null;
    try
    {
      conn = DBConnection.getConnection();
      conn.setAutoCommit(false);
      StringBuffer buf = new StringBuffer();
      buf.append(" MERGE INTO FC_DMS_DICTIONARY a   USING FC_DMS_DICTIONARY_TEMP n ");
      buf.append(" ON (a.type = n.type AND a.code=n.code) WHEN MATCHED THEN ");
      buf.append(" UPDATE ");
      buf.append(" SET a.type_name=n.type_name, ");
      buf.append("  a.code_name=n.code_name, ");
      buf.append(" a.import_date=n.import_date, ");
      buf.append("  a.time_stamp=n.time_stamp, ");
      buf.append("  a.sequnce = n.sequnce ");
      buf.append(" WHEN NOT MATCHED THEN  ");
      buf.append(" INSERT ");
      buf.append(" VALUES (n.type,n.type_name,n.code,n.code_name,n.import_date,n.time_stamp, n.sequnce) ");
      dmsToDataStmt = conn.prepareStatement(buf.toString());
      dmsToDataStmt.addBatch();
      dmsToDataStmt.executeBatch();
      conn.commit();
    }
    catch (Exception e)
    {
      log.error("更新数据字典表数据 updateFcDmsDictionaryTemp", e);
      conn.rollback();
      throw e;
    }
    finally
    {
      if (dmsToDataStmt != null) {
        dmsToDataStmt.close();
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
    log.info("4.清空temp表数据end");
  }
  
  public void deleteFcDmsDictionaryTemp()
    throws Exception
  {
    log.info("清空数据字典temp表数据deleteFcDmsDictionaryTemp  start ");
    PreparedStatement dmsToDataStmt = null;
    Connection conn = null;
    try
    {
      conn = DBConnection.getConnection();
      conn.setAutoCommit(false);
      dmsToDataStmt = conn.prepareStatement("delete from FC_DMS_DICTIONARY_TEMP");
      dmsToDataStmt.addBatch();
      dmsToDataStmt.executeBatch();
      conn.commit();
    }
    catch (Exception e)
    {
      log.error("清空数据字典temp表数据deleteFcDmsDictionaryTemp  ", e);
      conn.rollback();
      throw e;
    }
    finally
    {
      if (dmsToDataStmt != null) {
        dmsToDataStmt.close();
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
    log.info("4.清空temp表数据end");
  }
  
  public void deleteFcDmsTempByNewOwners()
    throws Exception
  {
    log.info("4.清空temp表数据start ");
    PreparedStatement dmsToDataStmt = null;
    Connection conn = null;
    try
    {
      conn = DBConnection.getConnection();
      conn.setAutoCommit(false);
      dmsToDataStmt = conn.prepareStatement("delete from fc_dms_to_data_temp");
      dmsToDataStmt.addBatch();
      dmsToDataStmt.executeBatch();
      conn.commit();
    }
    catch (Exception e)
    {
      log.error("清空temp表数据", e);
      conn.rollback();
      throw e;
    }
    finally
    {
      if (dmsToDataStmt != null) {
        dmsToDataStmt.close();
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
    log.info("4.清空temp表数据end");
  }
  
  public void insertFcDmsLog(Map<String, DmsToLogBean> toLogBeanMap)
    throws Exception
  {
    PreparedStatement dmsToDataStmt = null;
    Connection conn = null;
    try
    {
      if ((toLogBeanMap != null) && (toLogBeanMap.size() > 0))
      {
        conn = DBConnection.getConnection();
        log.info("插入获取DMS数据条数日志");
        dmsToDataStmt = conn
          .prepareStatement("INSERT INTO FC_DMS_TO_LOG VALUES(?,?,?,?)");
        conn = DBConnection.getConnection();
        conn.setAutoCommit(false);
        for (Map.Entry entry : toLogBeanMap.entrySet())
        {
          DmsToLogBean dmsBean = (DmsToLogBean)entry.getValue();
          dmsToDataStmt.setString(1, dmsBean.getReadDate());
          dmsToDataStmt.setString(2, dmsBean.getReadType());
          dmsToDataStmt.setLong(3, dmsBean.getReadCount().longValue());
          dmsToDataStmt.setString(4, dmsBean.getfReadType());
          dmsToDataStmt.addBatch();
        }
        dmsToDataStmt.executeBatch();
        conn.commit();
      }
    }
    catch (Exception e)
    {
      log.error("执行插入日志出现异常", e);
      conn.rollback();
      throw e;
    }
    finally
    {
      if (dmsToDataStmt != null) {
        dmsToDataStmt.close();
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
  
  public boolean insertCrmpotcusfy(List<String[]> values, String importDate, String importFileNumber)
    throws Exception
  {
    log.info("[插入潜客数据]保存数据库开始");
    PreparedStatement dmsToDataStmt = null;
    Connection conn = null;
    boolean b = false;
    try
    {
      if ((values != null) && (values.size() > 0))
      {
        conn = DBConnection.getConnection();
        dmsToDataStmt = conn
          .prepareStatement("INSERT INTO FC_CRMPOTCUSFY_QK VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        for (String[] value : values)
        {
          int number = value.length - 5;
          for (int i = 0; i < number; i++) {
            dmsToDataStmt.setString(i + 1, value[i]);
          }
          dmsToDataStmt.setString(number + 1, importDate);
          dmsToDataStmt.setString(number + 2, importFileNumber);
          





          dmsToDataStmt.setString(number + 3, value[number]);
          dmsToDataStmt.setString(number + 4, value[(number + 1)]);
          dmsToDataStmt.setString(number + 5, value[(number + 2)]);
          dmsToDataStmt.setString(number + 6, value[(number + 3)]);
          dmsToDataStmt.addBatch();
        }
        dmsToDataStmt.executeBatch();
        conn.commit();
        b = true;
      }
    }
    catch (Exception e)
    {
      log.error("[插入潜客数据]出现异常", e);
      conn.rollback();
      throw e;
    }
    finally
    {
      if (dmsToDataStmt != null) {
        dmsToDataStmt.close();
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
    return b;
  }
  
  public boolean insertCrmtrackfy(List<String[]> values, String importDate, String importFileNumber)
    throws Exception
  {
    log.info("[插入销售线索数据]保存数据库开始");
    PreparedStatement dmsToDataStmt = null;
    Connection conn = null;
    boolean b = false;
    try
    {
      if ((values != null) && (values.size() > 0))
      {
        conn = DBConnection.getConnection();
        dmsToDataStmt = conn
          .prepareStatement("INSERT INTO FC_CRMTRACKFY_XS VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        for (String[] value : values)
        {
          int number = value.length - 3;
          for (int i = 0; i < number; i++) {
            dmsToDataStmt.setString(i + 1, value[i]);
          }
          dmsToDataStmt.setString(number + 1, importDate);
          dmsToDataStmt.setString(number + 2, importFileNumber);
          dmsToDataStmt.setString(number + 3, value[number]);
          dmsToDataStmt.setString(number + 4, value[(number + 1)]);
          dmsToDataStmt.addBatch();
        }
        dmsToDataStmt.executeBatch();
        conn.commit();
        b = true;
      }
    }
    catch (Exception e)
    {
      log.error("[插入销售线索数据]出现异常", e);
      conn.rollback();
      throw e;
    }
    finally
    {
      if (dmsToDataStmt != null) {
        dmsToDataStmt.close();
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
    return b;
  }
  
  public void insertRepair(List<DmsRepairOrderBean> repairs, List<DmsAddItemBean> taddItem, List<DmsLabourPartBean> tlabourParts, List<DmsRepairPartBean> trepairParts, List<DmsSalesPartBean> tsalesParts, String importDate, String path)
    throws Exception
  {
    PreparedStatement stsm = null;
    Connection conn = null;
    try
    {
      if ((repairs != null) && (repairs.size() > 0))
      {
        conn = DBConnection.getConnection();
        conn.setAutoCommit(false);
        
        stsm = conn
        
          .prepareStatement("insert into FC_DMS_REPAIR_ORDER_TEMP values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        
        log
          .info("[保存工单相关数据开始]" + 
          XmlSaveUtil.formatDate(new Date(), 1));
        DmsRepairOrderBean repair;
        for (int i = 0; i < repairs.size(); i++)
        {
          repair = (DmsRepairOrderBean)repairs.get(i);
          stsm.setString(1, repair.getOrder_id());
          stsm.setString(2, repair.getRo_no());
          stsm.setLong(3, repair.getRo_id().longValue());
          stsm.setString(4, repair.getDealer_service_code());
          stsm.setString(5, repair.getDealer_name());
          stsm.setString(6, repair.getBrand());
          stsm.setString(7, repair.getSerise());
          stsm.setString(8, repair.getModel());
          stsm.setString(9, repair.getColor());
          stsm.setString(10, repair.getVin());
          stsm.setString(11, repair.getEngine_no());
          stsm.setString(12, repair.getLisence_no());
          stsm.setString(13, repair.getRo_create_date());
          stsm.setString(14, repair.getDelivery_date());
          stsm.setString(15, repair.getFor_balance_time());
          stsm.setString(16, repair.getRo_status());
          stsm.setString(17, repair.getRo_type());
          stsm.setString(18, repair.getLabour_amount());
          stsm.setString(19, repair.getRepair_part_amount());
          stsm.setString(20, repair.getSales_part_amount());
          stsm.setString(21, repair.getAdd_item_amount());
          stsm.setString(22, repair.getOver_item_amount());
          stsm.setString(23, repair.getRepair_amount());
          stsm.setString(24, repair.getBalance_amount());
          stsm.setString(25, repair.getSales_date());
          stsm.setString(26, repair.getIn_mileage());
          stsm.setString(27, repair.getRemark());
          stsm.setString(28, repair.getCreate_date());
          stsm.setString(29, repair.getUpdate_date());
          stsm.setString(30, importDate);
          
          stsm.setString(31, repair.getTime_stamp());
          stsm.setString(32, repair.getSequnce());
          stsm.setString(33, repair.getOwner_id());
          stsm.setLong(34, repair.getIs_valid().longValue());
          stsm.setString(35, repair.getService_advisor_code());
          stsm.setString(36, repair.getService_advisor_name());
          stsm.setString(37, repair.getBig_org_name());
          stsm.setString(38, repair.getOut_mileage());
          
          stsm.setString(39, repair.getDeliverer());
          stsm.setString(40, repair.getDeliverer_gender());
          stsm.setString(41, repair.getDeliverer_mobile());
          stsm.setString(42, repair.getDeliverer_phone());
          stsm.setString(43, repair.getIscsi());
          
          stsm.addBatch();
        }
        stsm.executeBatch();
        if ((trepairParts != null) && (trepairParts.size() > 0))
        {
          stsm = 
            conn.prepareStatement("insert into FC_DMS_REPAIR_PART_TEMP values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
          for (DmsRepairPartBean part : trepairParts)
          {
            stsm.setString(1, part.getPart_id());
            stsm.setString(2, part.getRo_no());
            stsm.setString(3, part.getPart_no());
            stsm.setString(4, part.getPart_name());
            stsm.setDouble(5, part.getPart_quantity().doubleValue());
            stsm.setString(6, part.getCharge_partition_code());
            stsm.setString(7, part.getManage_sort_code());
            stsm.setString(8, part.getIs_main_part());
            stsm.setDouble(9, part.getPart_cost_price().doubleValue());
            stsm.setDouble(10, part.getPart_sales_price().doubleValue());
            stsm.setDouble(11, part.getPart_cost_amount().doubleValue());
            stsm.setDouble(12, part.getPart_sales_amount().doubleValue());
            stsm.setDouble(13, part.getDiscount().doubleValue());
            stsm.setString(14, importDate);
            stsm.setString(15, part.getCharge_partition_name());
            stsm.setString(16, part.getOrder_id());
            stsm.addBatch();
          }
          stsm.executeBatch();
        }
        if ((tlabourParts != null) && (tlabourParts.size() > 0))
        {
          stsm = 
            conn.prepareStatement("insert into FC_DMS_LABOUR_PART_TEMP values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
          for (DmsLabourPartBean part : tlabourParts)
          {
            stsm.setString(1, part.getLabour_id());
            stsm.setString(2, part.getRo_no());
            stsm.setString(3, part.getLabour_code());
            stsm.setString(4, part.getLabour_name());
            stsm.setDouble(5, part.getLabour_price().doubleValue());
            stsm.setDouble(6, part.getStd_labour_hour().doubleValue());
            stsm.setDouble(7, part.getLabour_amount().doubleValue());
            stsm.setDouble(8, part.getDiscount().doubleValue());
            stsm.setString(9, importDate);
            stsm.setString(10, part.getPrimary_code());
            stsm.setString(11, part.getPrimary_name());
            stsm.setString(12, part.getSecond_code());
            stsm.setString(13, part.getSecond_name());
            stsm.setString(14, part.getStd_hour());
            stsm.setString(15, part.getSp_hour());
            stsm.setString(16, part.getPg_hour());
            stsm.setString(17, part.getOrder_id());
            stsm.addBatch();
          }
          stsm.executeBatch();
        }
        if ((tsalesParts != null) && (tsalesParts.size() > 0))
        {
          stsm = 
            conn.prepareStatement("insert into FC_DMS_SALES_PART_TEMP values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
          for (DmsSalesPartBean part : tsalesParts)
          {
            stsm.setString(1, part.getSales_id());
            stsm.setString(2, part.getRo_no());
            stsm.setString(3, part.getPart_no());
            stsm.setString(4, part.getPart_name());
            stsm.setDouble(5, part.getPart_quantity().doubleValue());
            stsm.setString(6, part.getCharge_partition_code());
            stsm.setString(7, part.getManage_sort_code());
            stsm.setDouble(8, part.getPart_cost_price().doubleValue());
            stsm.setDouble(9, part.getPart_sales_price().doubleValue());
            stsm.setDouble(10, part.getPart_cost_amount().doubleValue());
            stsm.setDouble(11, part.getPart_sales_amount().doubleValue());
            stsm.setDouble(12, part.getDiscount().doubleValue());
            stsm.setString(13, importDate);
            stsm.setString(14, part.getCharge_partition_name());
            stsm.setString(15, part.getOrder_id());
            stsm.addBatch();
          }
          stsm.executeBatch();
        }
        if ((taddItem != null) && (taddItem.size() > 0))
        {
          stsm = 
            conn.prepareStatement("insert into FC_DMS_ADD_ITEM_TEMP values(?,?,?,?,?,?,?,?,?)");
          for (DmsAddItemBean part : taddItem)
          {
            stsm.setString(1, part.getAdd_id());
            stsm.setString(2, part.getRo_no());
            stsm.setString(3, part.getManage_sort_code());
            stsm.setString(4, part.getAdd_item_code());
            stsm.setString(5, part.getAdd_item_name());
            stsm.setDouble(6, part.getAdd_item_amount().doubleValue());
            stsm.setDouble(7, part.getDiscount().doubleValue());
            stsm.setString(8, importDate);
            stsm.setString(9, part.getOrder_id());
            stsm.addBatch();
          }
          stsm.executeBatch();
        }
        conn.commit();
      }
    }
    catch (Exception e)
    {
      log.error("插入工单数据出现异常！", e);
      conn.rollback();
      throw e;
    }
    finally
    {
      if (stsm != null) {
        stsm.close();
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
  
  public void insertDic(List<DmsDictionaryBean> dics, String path, String importDate)
    throws Exception
  {
    PreparedStatement stsm = null;
    Connection conn = null;
    try
    {
      if ((dics != null) && (dics.size() > 0))
      {
        log.info("[保存数据字典相关数据开始]" + 
          XmlSaveUtil.formatDate(new Date(), 1));
        conn = DBConnection.getConnection();
        conn.setAutoCommit(false);
        stsm = conn
          .prepareStatement("insert into FC_DMS_DICTIONARY_TEMP values(?,?,?,?,?,?,?)");
        for (DmsDictionaryBean dic : dics) {
          if ((dic.getCode() != null) && (!"".equals(dic.getCode())))
          {
            stsm.setLong(1, dic.getType().longValue());
            stsm.setString(2, dic.getType_name());
            stsm.setString(3, dic.getCode());
            stsm.setString(4, dic.getCode_name());
            stsm.setString(5, importDate);
            stsm.setString(6, dic.getTime_stamp());
            stsm.setString(7, dic.getSequnce());
            stsm.addBatch();
          }
        }
        stsm.executeBatch();
        conn.commit();
      }
    }
    catch (Exception e)
    {
      log.error("插入数据字典出现异常", e);
      conn.rollback();
      throw e;
    }
    finally
    {
      if (stsm != null) {
        stsm.close();
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
  
  public void insertParts(List<DmsPartsBean> parts, String path, String importDate)
    throws Exception
  {
    PreparedStatement stsm = null;
    Connection conn = null;
    try
    {
      if ((parts != null) && (parts.size() > 0))
      {
        log.info("[保存配件相关数据开始]" + 
          XmlSaveUtil.formatDate(new Date(), 1));
        conn = DBConnection.getConnection();
        conn.setAutoCommit(false);
        stsm = conn
          .prepareStatement("insert into FC_DMS_PARTS values(?,?,?,?,?,?,?,?)");
        for (DmsPartsBean part : parts)
        {
          stsm.setString(1, part.getParts_no());
          stsm.setString(2, part.getEn_cesc());
          stsm.setString(3, part.getProduct_code());
          stsm.setDouble(4, part.getRecommend_price().doubleValue());
          stsm.setDouble(5, part.getStock_price().doubleValue());
          part.setImportDate(importDate);
          stsm.setString(6, part.getImportDate());
          stsm.setString(7, part.getTime_stamp());
          stsm.setString(8, part.getSequnce());
          stsm.addBatch();
        }
        stsm.executeBatch();
        conn.commit();
      }
    }
    catch (Exception e)
    {
      log.error("插入配件信息出现异常", e);
      conn.rollback();
      throw e;
    }
    finally
    {
      if (stsm != null) {
        stsm.close();
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
  
  public void insertDeal(List<DmsDealBean> dics, String importDate, String path)
    throws Exception
  {
    PreparedStatement stsm = null;
    Connection conn = null;
    try
    {
      if ((dics != null) && (dics.size() > 0))
      {
        log.info("[保存账户信息数据开始]" + 
          XmlSaveUtil.formatDate(new Date(), 1));
        conn = DBConnection.getConnection();
        conn.setAutoCommit(false);
        stsm = conn
          .prepareStatement("insert into FC_DMS_DEAL values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        for (DmsDealBean dic : dics)
        {
          stsm.setDouble(1, dic.getId().doubleValue());
          stsm.setString(2, dic.getName());
          stsm.setString(3, dic.getVin());
          stsm.setString(4, dic.getCampaign_code());
          stsm.setDouble(5, dic.getAmount().doubleValue());
          stsm.setDouble(6, dic.getUse_amount().doubleValue());
          stsm.setString(7, dic.getValid_begin_date());
          stsm.setString(8, dic.getValid_end_date());
          stsm.setString(9, dic.getStatus());
          stsm.setString(10, dic.getCreate_date());
          stsm.setString(11, dic.getUser_name());
          stsm.setDouble(12, dic.getLowest_amount().doubleValue());
          stsm.setDouble(13, dic.getLimit_deduct().doubleValue());
          stsm.setTimestamp(14, new Timestamp(new Date()
            .getTime()));
          stsm.setTimestamp(15, new Timestamp(new Date()
            .getTime()));
          stsm.setString(16, dic.getDealer_service_code());
          stsm.addBatch();
        }
        stsm.executeBatch();
        conn.commit();
      }
    }
    catch (Exception e)
    {
      log.error("插入账户信息出现异常", e);
      conn.rollback();
      throw e;
    }
    finally
    {
      if (stsm != null) {
        stsm.close();
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
  
  public void insertElec(List<DmsElecBean> elecs, String importDate, String path)
    throws Exception
  {
    PreparedStatement stsm = null;
    Connection conn = null;
    try
    {
      if ((elecs != null) && (elecs.size() > 0))
      {
        log.info("[保存账户明细数据开始]" + 
          XmlSaveUtil.formatDate(new Date(), 1));
        conn = DBConnection.getConnection();
        conn.setAutoCommit(false);
        stsm = conn
          .prepareStatement("insert into FC_DMS_ELEC values(?,?,?,?,?,?,?,?,?,?,?)");
        for (DmsElecBean dic : elecs)
        {
          stsm.setDouble(1, dic.getAccount_id().doubleValue());
          stsm.setString(2, dic.getVin());
          stsm.setString(3, dic.getRo_no());
          stsm.setString(4, dic.getDealer_service_code());
          stsm.setString(5, dic.getUsed_date());
          stsm.setDouble(6, dic.getUsed_amount().doubleValue());
          stsm.setDouble(7, dic.getLost_amount().doubleValue());
          stsm.setString(8, dic.getEle_type());
          stsm.setTimestamp(9, new Timestamp(new Date()
            .getTime()));
          stsm.setTimestamp(10, new Timestamp(new Date()
            .getTime()));
          stsm.setString(11, importDate);
          stsm.addBatch();
        }
        stsm.executeBatch();
        conn.commit();
      }
    }
    catch (Exception e)
    {
      log.error("插入账户明细出现异常", e);
      conn.rollback();
      throw e;
    }
    finally
    {
      if (stsm != null) {
        stsm.close();
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
  
  public Map<String, String> setCXByDmsToData(String type, String importDate)
    throws Exception
  {
    Map<String, String> m = new HashMap();
    PreparedStatement dmsToDataStmt = null;
    Connection conn = null;
    try
    {
      conn = DBConnection.getConnection();
      conn.setAutoCommit(false);
      
      String sql = "select CODE,CODE_NAME from FC_DMS_DICTIONARY Y WHERE   Y.type=" + type + " ";
      dmsToDataStmt = conn.prepareStatement(sql);
      ResultSet res = dmsToDataStmt.executeQuery();
      if (res != null) {
        while (res.next())
        {
          String CODE = res.getString(1);
          if (!m.containsKey(CODE)) {
            m.put(CODE, res.getString(2));
          }
        }
      }
      conn.commit();
    }
    catch (Exception e)
    {
      log.error("查询字典表返回HashMap异常", e);
      conn.rollback();
      throw e;
    }
    finally
    {
      if (dmsToDataStmt != null) {
        dmsToDataStmt.close();
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
    return m;
  }
  
  public List<String> getCustomers()
    throws Exception
  {
    PreparedStatement dmsToDataStmt = null;
    Connection conn = null;
    List<String> acode = null;
    try
    {
      conn = DBConnection.getConnection();
      conn.setAutoCommit(false);
      String sql = "SELECT A.C_NAME FROM FC_CUSTOMERS_DATA A";
      dmsToDataStmt = conn.prepareStatement(sql);
      ResultSet res = dmsToDataStmt.executeQuery();
      if (res != null)
      {
        acode = new ArrayList();
        while (res.next()) {
          acode.add(res.getString(1));
        }
      }
      conn.commit();
    }
    catch (Exception e)
    {
      log.error("[获取关键字名称信息]异常", e);
      conn.rollback();
      throw e;
    }
    finally
    {
      if (dmsToDataStmt != null) {
        dmsToDataStmt.close();
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
    return acode;
  }
  
  public List<String> getLargeVins()
    throws Exception
  {
    PreparedStatement dmsToDataStmt = null;
    Connection conn = null;
    List<String> acode = null;
    try
    {
      conn = DBConnection.getConnection();
      conn.setAutoCommit(false);
      String sql = "SELECT A.VIN FROM FC_DMS_LARGE_DATA A";
      dmsToDataStmt = conn.prepareStatement(sql);
      ResultSet res = dmsToDataStmt.executeQuery();
      if (res != null)
      {
        acode = new ArrayList();
        while (res.next()) {
          acode.add(res.getString(1));
        }
      }
      conn.commit();
    }
    catch (Exception e)
    {
      log.error("[获取大客户VIN码列表]异常", e);
      conn.rollback();
      throw e;
    }
    finally
    {
      if (dmsToDataStmt != null) {
        dmsToDataStmt.close();
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
    return acode;
  }
  
  public String setCXByDmsToData(String code, String type, String importDate)
    throws Exception
  {
    PreparedStatement dmsToDataStmt = null;
    Connection conn = null;
    String cx = null;
    try
    {
      conn = DBConnection.getConnection();
      conn.setAutoCommit(false);
      
      String sql = "select distinct CODE_NAME from FC_DMS_DICTIONARY Y WHERE  Y.type=" + type + " AND Y.CODE='" + code + "' ";
      dmsToDataStmt = conn.prepareStatement(sql);
      ResultSet res = dmsToDataStmt.executeQuery();
      if (res != null) {
        while (res.next()) {
          cx = res.getString(1);
        }
      }
      conn.commit();
    }
    catch (Exception e)
    {
      log.error("根据读取日期查询当期读取数目异常", e);
      conn.rollback();
      throw e;
    }
    finally
    {
      if (dmsToDataStmt != null) {
        dmsToDataStmt.close();
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
    return cx;
  }
  
  public List<String> getEa()
    throws Exception
  {
    PreparedStatement dmsToDataStmt = null;
    Connection conn = null;
    List<String> eaData = new ArrayList();
    try
    {
      conn = DBConnection.getConnection();
      conn.setAutoCommit(false);
      String sql = "select CAREANAME from FC_CAR_EA";
      dmsToDataStmt = conn.prepareStatement(sql);
      ResultSet res = dmsToDataStmt.executeQuery();
      if (res != null) {
        while (res.next()) {
          eaData.add(res.getString(1));
        }
      }
      conn.commit();
    }
    catch (Exception e)
    {
      log.error("根据读取日期查询当期读取数目异常", e);
      conn.rollback();
      throw e;
    }
    finally
    {
      if (dmsToDataStmt != null) {
        dmsToDataStmt.close();
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
    return eaData;
  }
  
  public List<String> getSync(String type)
    throws Exception
  {
    PreparedStatement dmsToDataStmt = null;
    Connection conn = null;
    List<String> eaData = new ArrayList();
    try
    {
      conn = DBConnection.getConnection();
      conn.setAutoCommit(false);
      StringBuffer sql = new StringBuffer();
      sql.append("select SYNCNAME from FC_CAR_SYNC t where 1=1");
      if ("ea".equals(type)) {
        sql.append(" and t.SYNEOREATYPE in ('ea','all') ");
      } else if ("sync".equals(type)) {
        sql.append(" and t.SYNEOREATYPE in ('sync','all') ");
      } else {
        sql.append(" and t.SYNEOREATYPE in ('all') ");
      }
      dmsToDataStmt = conn.prepareStatement(sql.toString());
      ResultSet res = dmsToDataStmt.executeQuery();
      if (res != null) {
        while (res.next()) {
          eaData.add(res.getString(1));
        }
      }
      conn.commit();
    }
    catch (Exception e)
    {
      log.error("根据读取日期查询当期读取数目异常", e);
      conn.rollback();
      throw e;
    }
    finally
    {
      if (dmsToDataStmt != null) {
        dmsToDataStmt.close();
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
    return eaData;
  }
  
  public Integer getErrorObCount(String vin, String iserror)
    throws Exception
  {
    PreparedStatement dmsToDataStmt = null;
    Connection conn = null;
    Integer resNumber = Integer.valueOf(0);
    try
    {
      conn = DBConnection.getConnection();
      conn.setAutoCommit(false);
      if ("Y".equals(iserror)) {
        dmsToDataStmt = 
          conn.prepareStatement("select count(*) from FC_DMS_OB_TO_DATA da where da.VIN=?   and  da.OWNER_OBSTATUS='2' ");
      } else if ("N".equals(iserror)) {
        dmsToDataStmt = 
          conn.prepareStatement("select count(*) from FC_DMS_OB_TO_DATA da where da.VIN=? ");
      }
      dmsToDataStmt.setString(1, vin);
      
      ResultSet res = dmsToDataStmt.executeQuery();
      if (res != null) {
        while (res.next()) {
          resNumber = Integer.valueOf(res.getInt(1));
        }
      }
      conn.commit();
    }
    catch (Exception e)
    {
      log.error("获取拨打次数异常！", e);
      conn.rollback();
      throw e;
    }
    finally
    {
      if (dmsToDataStmt != null) {
        dmsToDataStmt.close();
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
    return resNumber;
  }
  
  public Integer getErrorObCount(String vin, String mobile, String conMobile, String obType)
    throws Exception
  {
    PreparedStatement dmsToDataStmt = null;
    Connection conn = null;
    Integer resNumber = Integer.valueOf(0);
    try
    {
      conn = DBConnection.getConnection();
      conn.setAutoCommit(false);
      if ("owner".equals(obType))
      {
        String sql = "select count(*) from FC_DMS_OB_TO_DATA da where da.VIN=?  and  da.OWNER_MOBILE=?  and  da.OWNER_OBSTATUS='2' ";
        dmsToDataStmt = conn.prepareStatement(sql);
        dmsToDataStmt.setString(1, vin);
        dmsToDataStmt.setString(2, mobile);
      }
      else if ("owner&contacter".equals(obType))
      {
        String sql = "select count(*) from FC_DMS_OB_TO_DATA da where da.VIN=?  and  (da.OWNER_MOBILE=? and da.CONTACT_MOBILE=?)  and  (da.OWNER_OBSTATUS='2' or da.CONTACT_OBSTATUS='2') ";
        dmsToDataStmt = conn.prepareStatement(sql);
        dmsToDataStmt.setString(1, vin);
        dmsToDataStmt.setString(2, mobile);
        dmsToDataStmt.setString(3, conMobile);
      }
      ResultSet res = dmsToDataStmt.executeQuery();
      if (res != null) {
        while (res.next()) {
          resNumber = Integer.valueOf(res.getInt(1));
        }
      }
      conn.commit();
    }
    catch (Exception e)
    {
      log.error("获取拨打次数异常！", e);
      conn.rollback();
      throw e;
    }
    finally
    {
      if (dmsToDataStmt != null) {
        dmsToDataStmt.close();
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
    return resNumber;
  }
  
  public Integer getPreSellCountById(String DID)
    throws Exception
  {
    log.info("[根据ID查询是否存在售前数据]开始");
    PreparedStatement dmsToDataStmt = null;
    Connection conn = null;
    Integer resNumber = Integer.valueOf(0);
    try
    {
      conn = DBConnection.getConnection();
      conn.setAutoCommit(false);
      String sql = "select count(*) from FC_M2F_CALLCENTER_USERDATA da where da.DID=? ";
      dmsToDataStmt = conn.prepareStatement(sql);
      dmsToDataStmt.setString(1, DID);
      ResultSet res = dmsToDataStmt.executeQuery();
      if (res != null) {
        while (res.next()) {
          resNumber = Integer.valueOf(res.getInt(1));
        }
      }
      conn.commit();
    }
    catch (Exception e)
    {
      log.error("[根据ID查询是否存在售前数据]异常！", e);
      conn.rollback();
      throw e;
    }
    finally
    {
      if (dmsToDataStmt != null) {
        dmsToDataStmt.close();
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
    return resNumber;
  }
  
  public void insertPreSell(List<M2FUserDataBean> mList)
    throws Exception
  {
    PreparedStatement dmsToDataStmt = null;
    Connection conn = null;
    try
    {
      if ((mList != null) && (mList.size() > 0))
      {
        log.info("[插入CC返回的售前数据]开始");
        conn = DBConnection.getConnection();
        conn.setAutoCommit(false);
        String sql = "INSERT INTO FC_M2F_CALLCENTER_USERDATA VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        dmsToDataStmt = conn.prepareStatement(sql);
        for (M2FUserDataBean m : mList) {
          if (m.getIsHistory() == 0)
          {
            dmsToDataStmt.setInt(1, Integer.parseInt(m.getLocal_to_dealer_id()));
            dmsToDataStmt.setString(2, m.getCreateDate());
            dmsToDataStmt.setString(3, m.getName());
            dmsToDataStmt.setString(4, m.getGender());
            dmsToDataStmt.setString(5, m.getMobile());
            dmsToDataStmt.setString(6, m.getTel());
            dmsToDataStmt.setString(7, m.getEmail1());
            dmsToDataStmt.setString(8, m.getEmail2());
            dmsToDataStmt.setString(9, m.getInterested());
            dmsToDataStmt.setString(10, m.getBoxNum());
            dmsToDataStmt.setString(11, m.getModel());
            dmsToDataStmt.setString(12, m.getBuyCarTime());
            dmsToDataStmt.setString(13, m.getProvince());
            dmsToDataStmt.setString(14, m.getCity());
            dmsToDataStmt.setString(15, m.getAddress());
            dmsToDataStmt.setString(16, m.getPostcode());
            dmsToDataStmt.setString(17, m.getBooking());
            dmsToDataStmt.setString(18, m.getDealerName());
            dmsToDataStmt.setString(19, m.getDealerCode());
            dmsToDataStmt.setString(20, m.getTestDriveTime());
            dmsToDataStmt.setString(21, m.getHasDriveLicense());
            dmsToDataStmt.setString(22, m.getBudgetCar());
            dmsToDataStmt.setString(23, m.getHasCarBrand());
            dmsToDataStmt.setString(24, m.getCarTime());
            dmsToDataStmt.setString(25, m.getSource());
            dmsToDataStmt.setString(26, m.getFriContact());
            dmsToDataStmt.setString(27, m.getSecContact());
            dmsToDataStmt.setString(28, m.getOtherInterested());
            dmsToDataStmt.setString(29, m.getOtherBrandPropaganda());
            dmsToDataStmt.setString(30, m.getEventCode());
            dmsToDataStmt.setString(31, m.getAge());
            dmsToDataStmt.setString(32, m.getIsMarry());
            dmsToDataStmt.setString(33, m.getBackground());
            dmsToDataStmt.setString(34, m.getIndustry());
            dmsToDataStmt.setString(35, m.getProfession());
            dmsToDataStmt.setString(36, m.getIncome());
            dmsToDataStmt.setString(37, m.getMemo1());
            dmsToDataStmt.setString(38, m.getMemo4());
            dmsToDataStmt.setString(39, m.getMemo5());
            dmsToDataStmt.setString(40, m.getMemo6());
            dmsToDataStmt.setString(41, m.getMemo7());
            dmsToDataStmt.setString(42, m.getMemo8());
            dmsToDataStmt.setString(43, m.getMemo9());
            dmsToDataStmt.setString(44, m.getMemo10());
            dmsToDataStmt.addBatch();
          }
        }
        dmsToDataStmt.executeBatch();
        conn.commit();
      }
    }
    catch (Exception e)
    {
      log.error("[插入CC返回的售前数据]异常", e);
      conn.rollback();
      throw e;
    }
    finally
    {
      if (dmsToDataStmt != null) {
        dmsToDataStmt.close();
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
  
  public Integer getPreSellCount(String date)
    throws Exception
  {
    log.info("[查询当天是否存在售前数据]开始");
    PreparedStatement dmsToDataStmt = null;
    Connection conn = null;
    Integer resNumber = Integer.valueOf(0);
    try
    {
      conn = DBConnection.getConnection();
      conn.setAutoCommit(false);
      String sql = "select count(*) from M2F_USERDATA da where to_char(da.CREATEDATE,'yyyy-MM-dd')=? ";
      dmsToDataStmt = conn.prepareStatement(sql);
      dmsToDataStmt.setString(1, date);
      ResultSet res = dmsToDataStmt.executeQuery();
      if (res != null) {
        while (res.next()) {
          resNumber = Integer.valueOf(res.getInt(1));
        }
      }
      conn.commit();
    }
    catch (Exception e)
    {
      log.error("[查询当天是否存在售前数据]异常！", e);
      conn.rollback();
      throw e;
    }
    finally
    {
      if (dmsToDataStmt != null) {
        dmsToDataStmt.close();
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
    return resNumber;
  }
  
  public List<M2FUserDataBean> getPreSellData(String date)
    throws Exception
  {
    List<M2FUserDataBean> preList = new ArrayList();
    PreparedStatement dmsToDataStmt = null;
    Connection conn = null;
    try
    {
      conn = DBConnection.getConnection();
      conn.setAutoCommit(false);
      String sql = "SELECT m.DID,m.CREATEDATE,m.NAME,m.GENDER,m.MOBILE,m.TEL,m.EMAIL1,m.EMAIL2,m.INTERESTED,m.BOXNUM,m.MODEL,m.BUYCARTIME,m.PROVINCE,m.CITY,m.ADDRESS,m.POSTCODE,m.BOOKING,m.DEALERNAME,m.DEALERCODE,m.TESTDRIVETIME,m.HASDRIVELICENSE,m.BUDGETCAR,m.HASCARBRAND,m.CARTIME,m.SOURCE,m.FRICONTACT,m.SECCONTACT,m.OTHERINTERESTED,m.OTHERBRANDPROPAGANDA,m.EVENTCODE,m.AGE,m.ISMARRY,m.BACKGROUND,m.INDUSTRY,m.PROFESSION,m.INCOME,m.MEMO1,m.MEMO4,m.MEMO5,m.MEMO6,m.MEMO8,m.MEMO9,m.MEMO10  FROM M2F_USERDATA m  WHERE to_char(m.CREATEDATE,'yyyy-MM-dd')=? ";
      



      dmsToDataStmt = conn.prepareStatement(sql);
      dmsToDataStmt.setString(1, date);
      ResultSet res = dmsToDataStmt.executeQuery();
      if (res != null) {
        while (res.next())
        {
          M2FUserDataBean preBean = new M2FUserDataBean();
          preBean.setLocal_to_dealer_id(res.getInt(1));
          preBean.setCreateDate(date);
          preBean.setName(res.getString(3));
          preBean.setGender(res.getString(4));
          preBean.setMobile(res.getString(5));
          preBean.setTel(res.getString(6));
          preBean.setEmail1(res.getString(7));
          preBean.setEmail2(res.getString(8));
          preBean.setInterested(res.getString(9));
          preBean.setBoxNum(res.getString(10));
          preBean.setModel(res.getString(11));
          preBean.setBuyCarTime(res.getString(12));
          preBean.setProvince(res.getString(13));
          preBean.setCity(res.getString(14));
          preBean.setAddress(res.getString(15));
          preBean.setPostcode(res.getString(16));
          preBean.setBooking(res.getString(17));
          preBean.setDealerName(res.getString(18));
          preBean.setDealerCode(res.getString(19));
          preBean.setTestDriveTime(res.getString(20));
          preBean.setHasDriveLicense(res.getString(21));
          preBean.setBudgetCar(res.getString(22));
          preBean.setHasCarBrand(res.getString(23));
          preBean.setCarTime(res.getString(24));
          preBean.setSource(res.getString(25));
          preBean.setFriContact(res.getString(26));
          preBean.setSecContact(res.getString(27));
          preBean.setOtherInterested(res.getString(28));
          preBean.setOtherBrandPropaganda(res.getString(29));
          preBean.setEventCode(res.getString(30));
          preBean.setAge(res.getString(31));
          preBean.setIsMarry(res.getString(32));
          preBean.setBackground(res.getString(33));
          preBean.setIndustry(res.getString(34));
          preBean.setProfession(res.getString(35));
          preBean.setIncome(res.getString(36));
          preBean.setMemo1(res.getString(37));
          preBean.setMemo4(res.getString(38));
          preBean.setMemo5(res.getString(39));
          preBean.setMemo6(res.getString(40));
          preBean.setMemo8(res.getString(41));
          preBean.setMemo9(res.getString(42));
          preBean.setMemo10(res.getString(43));
          preList.add(preBean);
        }
      }
      conn.commit();
    }
    catch (Exception e)
    {
      log.error("[根据日期获取当天可用的售前数据]异常", e);
      conn.rollback();
      throw e;
    }
    finally
    {
      if (dmsToDataStmt != null) {
        dmsToDataStmt.close();
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
    return preList;
  }
  
  public Integer getDmsCallCenterCount(String vin)
    throws Exception
  {
    PreparedStatement dmsToDataStmt = null;
    Connection conn = null;
    Integer resNumber = Integer.valueOf(0);
    try
    {
      conn = DBConnection.getConnection();
      conn.setAutoCommit(false);
      String sql = "select count(*) from FC_DMS_TO_DATA da where da.VIN=? and  da.OB_COUNT>0";
      dmsToDataStmt = conn.prepareStatement(sql);
      dmsToDataStmt.setString(1, vin);
      ResultSet res = dmsToDataStmt.executeQuery();
      if (res != null) {
        while (res.next()) {
          resNumber = Integer.valueOf(res.getInt(1));
        }
      }
      conn.commit();
    }
    catch (Exception e)
    {
      log.error("获取拨打次数异常！", e);
      conn.rollback();
      throw e;
    }
    finally
    {
      if (dmsToDataStmt != null) {
        dmsToDataStmt.close();
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
    return resNumber;
  }
  
  public Integer getEndDate()
    throws Exception
  {
    PreparedStatement dmsToDataStmt = null;
    Connection conn = null;
    int d = 1;
    try
    {
      conn = DBConnection.getConnection();
      conn.setAutoCommit(false);
      String sql = "select endDate from FC_DMS_END_ERRORDATA";
      dmsToDataStmt = conn.prepareStatement(sql);
      ResultSet res = dmsToDataStmt.executeQuery();
      if (res != null) {
        while (res.next()) {
          d = res.getInt(1);
        }
      }
      conn.commit();
    }
    catch (Exception e)
    {
      log.error("获取截至日期异常！", e);
      conn.rollback();
      throw e;
    }
    finally
    {
      if (dmsToDataStmt != null) {
        dmsToDataStmt.close();
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
    return Integer.valueOf(d);
  }
  
  public Integer getDmsDataLogCount(String readDate)
    throws Exception
  {
    PreparedStatement dmsToDataStmt = null;
    Connection conn = null;
    Integer resNumber = Integer.valueOf(0);
    try
    {
      conn = DBConnection.getConnection();
      conn.setAutoCommit(false);
      String sql = "select count(*) from FC_DMS_TO_LOG l where l.READ_DATE=?";
      dmsToDataStmt = conn.prepareStatement(sql);
      dmsToDataStmt.setString(1, readDate);
      ResultSet res = dmsToDataStmt.executeQuery();
      if (res != null) {
        while (res.next()) {
          resNumber = Integer.valueOf(res.getInt(1));
        }
      }
      conn.commit();
    }
    catch (Exception e)
    {
      log.error("根据读取日期查询当期读取数目异常", e);
      conn.rollback();
      throw e;
    }
    finally
    {
      if (dmsToDataStmt != null) {
        dmsToDataStmt.close();
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
    return resNumber;
  }
  
  public Integer getDmsDataLogCount(String readDate, String readType)
    throws Exception
  {
    PreparedStatement dmsToDataStmt = null;
    Connection conn = null;
    Integer resNumber = Integer.valueOf(0);
    try
    {
      conn = DBConnection.getConnection();
      conn.setAutoCommit(false);
      String sql = "select READ_COUNT from FC_DMS_TO_LOG l where l.READ_DATE=? and l.READ_TYPE=? order by  READ_COUNT asc";
      dmsToDataStmt = conn.prepareStatement(sql);
      dmsToDataStmt.setString(1, readDate);
      dmsToDataStmt.setString(2, readType);
      ResultSet res = dmsToDataStmt.executeQuery();
      if ((res != null) && 
        (res.next())) {
        resNumber = Integer.valueOf(res.getInt(1));
      }
      conn.commit();
    }
    catch (Exception e)
    {
      log.error("根据读取日期查询当期读取数目异常", e);
      conn.rollback();
      throw e;
    }
    finally
    {
      if (dmsToDataStmt != null) {
        dmsToDataStmt.close();
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
    return resNumber;
  }
  
  public void arrangeCallCenterData(List<DmsSubCallCenterBean> subList)
    throws Exception
  {
    PreparedStatement dmsToDataStmt = null;
    Connection conn = null;
    try
    {
      if ((subList != null) && (subList.size() > 0))
      {
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        log.info("整理并保存预发送CallCenter相关数据开始");
        conn = DBConnection.getConnection();
        conn.setAutoCommit(false);
        dmsToDataStmt = conn
          .prepareStatement("INSERT INTO FC_DMS_SUB_CALL_CENTER VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        for (DmsSubCallCenterBean dmssub : subList)
        {
          dmsToDataStmt.setString(1, dmssub.getYm());
          dmsToDataStmt.setString(2, dmssub.getSale_date());
          dmsToDataStmt.setString(3, dmssub.getDealer_code());
          dmsToDataStmt.setString(4, dmssub.getDealer_name());
          dmsToDataStmt.setString(5, dmssub.getVin_name());
          dmsToDataStmt.setString(6, dmssub.getColor());
          dmsToDataStmt.setString(7, dmssub.getCar_brand());
          dmsToDataStmt.setString(8, dmssub.getCar_model());
          dmsToDataStmt.setString(9, dmssub.getCatecode());
          dmsToDataStmt.setString(10, dmssub.getCar_carreg());
          dmsToDataStmt.setString(11, dmssub.getOwner_name());
          dmsToDataStmt.setString(12, dmssub.getOwner_sex());
          dmsToDataStmt.setString(13, dmssub.getOwner_birthday());
          dmsToDataStmt.setString(14, dmssub.getOwner_marital());
          dmsToDataStmt.setString(15, dmssub.getOwner_tel());
          dmsToDataStmt.setString(16, dmssub.getOwner_mobile());
          dmsToDataStmt.setString(17, dmssub.getOwner_province());
          dmsToDataStmt.setString(18, dmssub.getOwner_city());
          dmsToDataStmt.setString(19, dmssub.getOwner_address());
          dmsToDataStmt.setString(20, dmssub.getOwner_zip());
          dmsToDataStmt.setString(21, dmssub.getOwner_email());
          dmsToDataStmt.setString(22, dmssub.getContacter_name());
          dmsToDataStmt.setString(23, dmssub.getContacter_sex());
          dmsToDataStmt.setString(24, dmssub.getContacter_tel());
          dmsToDataStmt.setString(25, dmssub.getContacter_mobile());
          dmsToDataStmt.setString(26, dmssub.getContacter_address());
          dmsToDataStmt.setString(27, dmssub.getContacter_email());
          dmsToDataStmt.setString(28, dmssub.getCar_sync());
          dmsToDataStmt.setString(29, dmssub.getCar_ea());
          dmsToDataStmt.setString(30, dmssub.getOb_type());
          
          dmsToDataStmt.setString(31, dmssub.getReceiveDate());
          dmsToDataStmt.setTimestamp(32, ts);
          dmsToDataStmt.addBatch();
        }
        dmsToDataStmt.executeBatch();
        conn.commit();
      }
    }
    catch (Exception e)
    {
      log.error("保存预发送CallCenter数据出现异常", e);
      conn.rollback();
      throw e;
    }
    finally
    {
      if (dmsToDataStmt != null) {
        dmsToDataStmt.close();
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
  
  public Integer getDayCallCenterCount(String createDate)
    throws Exception
  {
    PreparedStatement dmsToDataStmt = null;
    Connection conn = null;
    Integer resNumber = Integer.valueOf(0);
    try
    {
      conn = DBConnection.getConnection();
      conn.setAutoCommit(false);
      String sql = "select count(*) from FC_DMS_SUB_CALL_CENTER  where IMPORTDATE=?";
      dmsToDataStmt = conn.prepareStatement(sql);
      dmsToDataStmt.setString(1, createDate);
      ResultSet res = dmsToDataStmt.executeQuery();
      if (res != null) {
        while (res.next()) {
          resNumber = Integer.valueOf(res.getInt(1));
        }
      }
      conn.commit();
    }
    catch (Exception e)
    {
      log.error("根据读取日期查询当期读取数目异常", e);
      conn.rollback();
      throw e;
    }
    finally
    {
      if (dmsToDataStmt != null) {
        dmsToDataStmt.close();
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
    return resNumber;
  }
  
  public void saveSubccDmsList(List<DmsToDataBean> dmsList, String importDate)
    throws Exception
  {
    PreparedStatement dmsToDataStmt = null;
    Connection conn = null;
    try
    {
      if ((dmsList != null) && (dmsList.size() > 0))
      {
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        log.info("[保存整理好预发送Call Center的DMS反馈数据]开始");
        conn = DBConnection.getConnection();
        conn.setAutoCommit(false);
        for (DmsToDataBean eBean : dmsList)
        {
          StringBuffer sqlB = new StringBuffer();
          sqlB.append(" INSERT INTO FC_DMS_SUB_CALL_CENTER_ERROR (YM,SALE_DATE,DEALER_CODE,DEALER_NAME,VIN_NAME,COLOR,CAR_BRAND,CAR_MODEL,CATECODE,CAR_CARREG,OWNER_NAME,OWNER_SEX,OWNER_BIRTHDAY,OWNER_MARITAL,OWNER_TEL,OWNER_MOBILE,OWNER_PROVINCE,OWNER_CITY,OWNER_ADDRESS,OWNER_ZIP,OWNER_EMAIL,CONTACTER_NAME,CONTACTER_SEX,CONTACTER_TEL,CONTACTER_MOBILE,CONTACTER_ADDRESS,CONTACTER_EMAIL,CAR_SYNC,CAR_EA,OB_TYPE,IMPORTDATE,CREATEDATE,CERTIFICATE_TYPE,CERTIFICATE_CODE,IS_SEND,SEND_DATE,sale_dealer_code,sale_service_code,sale_dealer_name) ");
          sqlB.append(" select  to_char(sysdate, 'yyyy') ym");
          sqlB.append(" ,b.sale_date,b.DEALER_SALE_CODE,b.DEALER_NAME,b.VIN,b.COLOR,b.BRAND,b.MODEL,b.CAT_CODE,b.LISENCE_NO,b.OWNER_NAME, ");
          sqlB.append(" b.GENDER,b.BIRTHDAY,b.OWNER_MARRIAGE,b.PHONE,b.MOBILE,b.PROVINCE,b.CITY,b.ADDRESS,b.ZIP_CODE,");
          sqlB.append("  b.E_MAIL,b.CONTACTOR_NAME,b.CONTACTOR_GENDER,b.CONTACTOR_PHONE,b.CONTACTOR_MOBILE,b.CONTACTOR_ADDRESS,b.CONTACTOR_EMAIL,b.CAR_SYNC,  ");
          sqlB.append("  b.CAR_EA,b.OB_TYPE,b.create_owner_date,sysdate,b.certificate_type,b.certificate_code,'N' ");
          sqlB.append(" , '" + importDate + "',sale_dealer_code,sale_service_code,sale_dealer_name from  ");
          sqlB.append(" ( select c.*,row_number() OVER (PARTITION BY c.vin ORDER BY c.CREATE_DATE desc) AS NUM ");
          sqlB.append("  from fc_dms_to_data c  where  c.vin=? ");
          sqlB.append(" ) b WHERE b.NUM=1 ");
          dmsToDataStmt = conn.prepareStatement(sqlB.toString());
          dmsToDataStmt.setString(1, eBean.getVin());
          dmsToDataStmt.addBatch();
          dmsToDataStmt.executeBatch();
        }
        conn.commit();
      }
    }
    catch (Exception e)
    {
      log.error("[保存整理好预发送Call Center的DMS反馈数据]出现异常", e);
      conn.rollback();
      throw e;
    }
    finally
    {
      if (dmsToDataStmt != null) {
        dmsToDataStmt.close();
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
  
  public List<DmsSubCallCenterBean> getSubCallList(String d1)
    throws Exception
  {
    PreparedStatement dmsToDataStmt = null;
    Connection conn = null;
    List<DmsSubCallCenterBean> subList = null;
    try
    {
      conn = DBConnection.getConnection();
      conn.setAutoCommit(false);
      StringBuilder bud = new StringBuilder();
      bud.append("select * ")
        .append("  from Fc_Dms_Sub_Call_Center_error f ")
        

        .append(" where f.send_date='" + d1 + "' and  nvl(f.is_send,'N')='N'  and f.DEALER_CODE=f.sale_dealer_code ");
      dmsToDataStmt = conn.prepareStatement(bud.toString());
      

      ResultSet res = dmsToDataStmt.executeQuery();
      if (res != null)
      {
        subList = new ArrayList();
        while (res.next())
        {
          DmsSubCallCenterBean dscBean = new DmsSubCallCenterBean();
          dscBean.setYm(res.getString(1));
          dscBean.setSale_date(res.getString(2));
          dscBean.setDealer_code(res.getString(3));
          dscBean.setDealer_name(res.getString(4));
          dscBean.setVin_name(res.getString(5));
          dscBean.setColor(res.getString(6));
          dscBean.setCar_brand(res.getString(7));
          dscBean.setCar_model(res.getString(8));
          dscBean.setCatecode(res.getString(9));
          dscBean.setCar_carreg(res.getString(10));
          dscBean.setOwner_name(res.getString(11));
          dscBean.setOwner_sex(res.getString(12));
          dscBean.setOwner_birthday(res.getString(13));
          dscBean.setOwner_marital(res.getString(14));
          dscBean.setOwner_tel(res.getString(15));
          dscBean.setOwner_mobile(res.getString(16));
          dscBean.setOwner_province(res.getString(17));
          dscBean.setOwner_city(res.getString(18));
          dscBean.setOwner_address(res.getString(19));
          dscBean.setOwner_zip(res.getString(20));
          dscBean.setOwner_email(res.getString(21));
          dscBean.setContacter_name(res.getString(22));
          dscBean.setContacter_sex(res.getString(23));
          dscBean.setContacter_tel(res.getString(24));
          dscBean.setContacter_mobile(res.getString(25));
          dscBean.setContacter_address(res.getString(26));
          dscBean.setContacter_email(res.getString(27));
          dscBean.setCar_sync(res.getString(28));
          dscBean.setCar_ea(res.getString(29));
          dscBean.setOb_type(res.getString(30));
          dscBean.setCreateOwnerDate(res.getString(31));
          dscBean.setCertificate_type(res.getString(33));
          dscBean.setCertificate_code(res.getString(34));
          
          dscBean.setPriority("A");
          dscBean.setDup_type("");
          subList.add(dscBean);
        }
      }
      conn.commit();
    }
    catch (Exception e)
    {
      log.error("[根据日期段查询可用于发发送Call Center的DMS反馈数据]出现异常", e);
      conn.rollback();
      throw e;
    }
    finally
    {
      if (dmsToDataStmt != null) {
        dmsToDataStmt.close();
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
    return subList;
  }
  
  public void saveDmstoCCisXMlInfo(String date)
    throws Exception
  {
    PreparedStatement dmsToDataStmt = null;
    Connection conn = null;
    try
    {
      conn = DBConnection.getConnection();
      conn.setAutoCommit(false);
      dmsToDataStmt = conn.prepareStatement("INSERT INTO FC_SUB_CC_DMS_TO_ERROR VALUES(?,?,?)");
      dmsToDataStmt.setString(1, date);
      dmsToDataStmt.setString(2, "N");
      dmsToDataStmt.setString(3, null);
      dmsToDataStmt.addBatch();
      dmsToDataStmt.executeBatch();
      conn.commit();
    }
    catch (Exception e)
    {
      log.error("[创建DMS反馈数据发送至CC文件，保存创建纪录]出现异常", e);
      conn.rollback();
      throw e;
    }
    finally
    {
      if (dmsToDataStmt != null) {
        dmsToDataStmt.close();
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
  
  public void saveSubccDmsList(String createDate)
    throws Exception
  {
    Connection conn = null;
    try
    {
      conn = DBConnection.getConnection();
      conn.setAutoCommit(false);
      StringBuilder sqlBud = new StringBuilder();
      sqlBud.append(" merge into FC_DMS_SUB_CALL_CENTER_ERROR a ")
        .append(" using ( ")
        .append(" select YM,SALE_DATE,DEALER_SALE_CODE,DEALER_NAME,VIN_NAME,COLOR,CAR_BRAND,CAR_MODEL,CAT_CODE,CAR_CARREG, ")
        .append(" OWNER_NAME,OWNER_SEX,OWNER_BIRTHDAY,OWNER_MARITAL,OWNER_TEL,OWNER_MOBILE,OWNER_PROVINCE,  ")
        .append(" OWNER_CITY,OWNER_ADDRESS,OWNER_ZIP,OWNER_EMAIL,CONTACTOR_NAME,CONTACTER_SEX,CONTACTER_TEL, ")
        .append(" CONTACTER_MOBILE,CONTACTER_ADDRESS,CONTACTER_EMAIL,CAR_SYNC,CAR_EA,OB_TYPE,CTYPE,CCODE,(DANUM+ERNUM) cunum ")
        .append(" from (select to_char(sysdate, 'yyyy') ym,sale_date,DEALER_SALE_CODE,DEALER_NAME,VIN VIN_NAME,  ")
        .append(" (SELECT distinct CODE_NAME  FROM FC_DMS_DICTIONARY FD  WHERE FD.IMPORT_DATE = '" + createDate + "'  AND FD.TYPE = 1103  AND FD.CODE = d.COLOR) COLOR,  ")
        .append(" (SELECT distinct CODE_NAME  FROM FC_DMS_DICTIONARY FD  WHERE FD.IMPORT_DATE = '" + createDate + "'  AND FD.TYPE = 9992  AND FD.CODE = d.SERISE) CAR_BRAND,  ")
        .append(" (SELECT distinct CODE_NAME  FROM FC_DMS_DICTIONARY FD  WHERE FD.IMPORT_DATE = '" + createDate + "'  AND FD.TYPE = 9993  AND FD.CODE = d.MODEL) CAR_MODEL,  ")
        .append(" CAT_CODE,LISENCE_NO CAR_CARREG,OWNER_NAME,  ")
        .append(" (SELECT distinct CODE_NAME  FROM FC_DMS_DICTIONARY FD  WHERE FD.IMPORT_DATE = '" + createDate + "'  AND FD.TYPE = 1006  AND FD.CODE = d.GENDER) Owner_Sex, ")
        .append(" BIRTHDAY Owner_Birthday,  ")
        .append(" (SELECT distinct CODE_NAME  FROM FC_DMS_DICTIONARY FD  WHERE FD.IMPORT_DATE = '" + createDate + "'  AND FD.TYPE = 1119  AND FD.CODE = d.OWNER_MARRIAGE) Owner_Marital,  ")
        .append(" PHONE Owner_Tel,MOBILE Owner_Mobile,  ")
        .append(" (SELECT distinct CODE_NAME  FROM FC_DMS_DICTIONARY FD  WHERE FD.IMPORT_DATE = '" + createDate + "'  AND FD.TYPE = 9995  AND FD.CODE = d.PROVINCE) Owner_Province,  ")
        .append(" (SELECT distinct CODE_NAME  FROM FC_DMS_DICTIONARY FD  WHERE FD.IMPORT_DATE = '" + createDate + "'  AND FD.TYPE = 9996  AND FD.CODE = d.CITY) Owner_City,  ")
        .append(" ADDRESS Owner_Address,ZIP_CODE Owner_Zip,E_MAIL Owner_Email,CONTACTOR_NAME,  ")
        .append(" (SELECT distinct CODE_NAME  FROM FC_DMS_DICTIONARY FD  WHERE FD.IMPORT_DATE = '" + createDate + "'  AND FD.TYPE = 1006  AND FD.CODE = d.CONTACTOR_GENDER) Contacter_Sex,  ")
        .append(" CONTACTOR_PHONE Contacter_Tel,CONTACTOR_MOBILE Contacter_Mobile,CONTACTOR_ADDRESS Contacter_Address,CONTACTOR_EMAIL Contacter_Email,  ")
        .append(" CAR_SYNC,CAR_EA,OB_TYPE OB_TYPE,  ")
        .append(" (SELECT distinct Y.CODE_NAME FROM FC_DMS_DICTIONARY Y  WHERE  Y.IMPORT_DATE = '" + createDate + "'  AND  Y.TYPE = 1239  AND Y.CODE = d.certificate_type) ctype,  ")
        .append(" certificate_code ccode,  ")
        .append(" nvl((select count(1)   from Fc_Dms_Ob_To_Data T  where T.vin = d.vin  ")
        .append(" and ((T.OB_TYPE = 'owner' AND T.OWNER_OBSTATUS = '2') or  (T.OB_TYPE like 'owner%contacter' and  (t.owner_obstatus = '2' or T.CONTACT_OBSTATUS = '2')) or (T.OB_TYPE = 'contacter' AND T.CONTACT_OBSTATUS = '2')   )  ")
        .append(" group by T.Vin),0) danum,  ")
        .append(" nvl((select count(1) from Fc_Dms_To_Error r where r.vin = d.vin),0) ernum  ")
        .append(" FROM FC_DMS_TO_DATA d  ")
        .append(" where d.CREATE_OWNER_DATE = '" + createDate + "' and d.NO_OB_STR = 'K_17') p ")
        .append(" ) b  ")
        .append(" on (a.vin_name=b.VIN_NAME) ")
        .append(" when MATCHED then ")
        .append("   update set a.ym=b.ym,a.sale_date=b.sale_date,a.dealer_code=b.DEALER_SALE_CODE,a.dealer_name=b.DEALER_NAME, ")
        .append("   a.color=b.COLOR,a.car_brand=b.CAR_BRAND,a.car_model=b.CAR_MODEL, ")
        .append("   a.catecode=b.CAT_CODE,a.car_carreg=b.CAR_CARREG,a.owner_name=b.OWNER_NAME,a.owner_sex=b.OWNER_SEX,a.owner_birthday=b.OWNER_BIRTHDAY,a.owner_marital=b.OWNER_MARITAL,a.owner_tel=b.OWNER_TEL, ")
        .append("   a.owner_mobile=b.OWNER_MOBILE,a.owner_province=b.OWNER_PROVINCE,a.owner_city=b.OWNER_CITY,a.owner_address=b.OWNER_ADDRESS,a.owner_zip=b.OWNER_ZIP,a.owner_email=b.OWNER_EMAIL,a.contacter_name=b.CONTACTOR_NAME, ")
        .append("   a.contacter_sex=b.CONTACTER_SEX,a.contacter_tel=b.CONTACTER_TEL,a.contacter_mobile=b.CONTACTER_MOBILE,a.contacter_address=b.CONTACTER_ADDRESS,a.contacter_email=b.CONTACTER_EMAIL,a.car_sync=b.CAR_SYNC, ")
        .append("   a.car_ea=b.CAR_EA,a.ob_type=b.OB_TYPE,a.certificate_type=b.CTYPE,a.certificate_code=b.CCODE,a.importdate='" + createDate + "' ")
        .append("   where a.is_send='N' and b.cunum<2 ")
        .append(" when NOT MATCHED then ")
        .append("   insert(a.ym,a.sale_date,a.dealer_code,a.dealer_name,a.vin_name,a.color,a.car_brand,a.car_model,a.catecode,a.car_carreg,a.owner_name, ")
        .append("   a.owner_sex,a.owner_birthday,a.owner_marital,a.owner_tel,a.owner_mobile,a.owner_province,a.owner_city,a.owner_address,a.owner_zip, ")
        .append("   a.owner_email,a.contacter_name,a.contacter_sex,a.contacter_tel,a.contacter_mobile,a.contacter_address,a.contacter_email,a.car_sync, ")
        .append("   a.car_ea,a.ob_type,a.certificate_type,a.certificate_code,a.createdate,a.importdate,a.is_send) ")
        .append("   values(b.ym,b.sale_date,b.DEALER_SALE_CODE,b.DEALER_NAME,b.VIN_NAME,b.COLOR,b.CAR_BRAND,b.CAR_MODEL,b.CAT_CODE,b.CAR_CARREG,b.OWNER_NAME, ")
        .append("   b.OWNER_SEX,b.OWNER_BIRTHDAY,b.OWNER_MARITAL,b.OWNER_TEL,b.OWNER_MOBILE,b.OWNER_PROVINCE,b.OWNER_CITY,b.OWNER_ADDRESS,b.OWNER_ZIP, ")
        .append("   b.OWNER_EMAIL,b.CONTACTOR_NAME,b.CONTACTER_SEX,b.CONTACTER_TEL,b.CONTACTER_MOBILE,b.CONTACTER_ADDRESS,b.CONTACTER_EMAIL,b.CAR_SYNC, ")
        .append("   b.CAR_EA,b.OB_TYPE,b.CTYPE,b.CCODE,sysdate,'" + createDate + "','N') ")
        .append("   where b.cunum<2 ");
      conn.prepareStatement(sqlBud.toString()).executeUpdate();
      conn.commit();
    }
    catch (Exception e)
    {
      log.error("[根据当前日期查询DMS反馈的数据集]", e);
      conn.rollback();
      throw e;
    }
    finally
    {
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
  
  public List<DmsSubCallCenterBean> getSubCcDmsList(String createDate)
    throws Exception
  {
    PreparedStatement dmsToDataStmt = null;
    Connection conn = null;
    List<DmsSubCallCenterBean> subList = null;
    try
    {
      conn = DBConnection.getConnection();
      conn.setAutoCommit(false);
      StringBuilder sqlBud = new StringBuilder();
      
      sqlBud.append("select YM,SALE_DATE,DEALER_SALE_CODE,DEALER_NAME,VIN_NAME,COLOR,CAR_BRAND,CAR_MODEL,CAT_CODE, ")
        .append("CAR_CARREG,OWNER_NAME,OWNER_SEX,OWNER_BIRTHDAY,OWNER_MARITAL,OWNER_TEL,OWNER_MOBILE,OWNER_PROVINCE, ")
        .append("OWNER_CITY,OWNER_ADDRESS,OWNER_ZIP,OWNER_EMAIL,CONTACTOR_NAME,CONTACTER_SEX,CONTACTER_TEL,CONTACTER_MOBILE, ")
        .append("CONTACTER_ADDRESS,CONTACTER_EMAIL,CAR_SYNC,CAR_EA,OB_TYPE,CTYPE,CCODE,(DANUM+ERNUM) cunum ")
        .append("from (select to_char(sysdate, 'yyyy') ym,sale_date,DEALER_SALE_CODE,DEALER_NAME,VIN VIN_NAME, ")
        .append("(SELECT distinct CODE_NAME  FROM FC_DMS_DICTIONARY FD  WHERE FD.IMPORT_DATE = '" + createDate + "'  AND FD.TYPE = 1103  AND FD.CODE = d.COLOR) COLOR, ")
        .append("(SELECT distinct CODE_NAME  FROM FC_DMS_DICTIONARY FD  WHERE FD.IMPORT_DATE = '" + createDate + "'  AND FD.TYPE = 9992  AND FD.CODE = d.SERISE) CAR_BRAND, ")
        .append("(SELECT distinct CODE_NAME  FROM FC_DMS_DICTIONARY FD  WHERE FD.IMPORT_DATE = '" + createDate + "'  AND FD.TYPE = 9993  AND FD.CODE = d.MODEL) CAR_MODEL, ")
        .append("CAT_CODE,LISENCE_NO CAR_CARREG,OWNER_NAME, ")
        .append("(SELECT distinct CODE_NAME  FROM FC_DMS_DICTIONARY FD  WHERE FD.IMPORT_DATE = '" + createDate + "'  AND FD.TYPE = 1006  AND FD.CODE = d.GENDER) Owner_Sex, ")
        .append("BIRTHDAY Owner_Birthday, ")
        .append("(SELECT distinct CODE_NAME  FROM FC_DMS_DICTIONARY FD  WHERE FD.IMPORT_DATE = '" + createDate + "'  AND FD.TYPE = 1119  AND FD.CODE = d.OWNER_MARRIAGE) Owner_Marital, ")
        .append("PHONE Owner_Tel,MOBILE Owner_Mobile, ")
        .append("(SELECT distinct CODE_NAME  FROM FC_DMS_DICTIONARY FD  WHERE FD.IMPORT_DATE = '" + createDate + "'  AND FD.TYPE = 9995  AND FD.CODE = d.PROVINCE) Owner_Province, ")
        .append("(SELECT distinct CODE_NAME  FROM FC_DMS_DICTIONARY FD  WHERE FD.IMPORT_DATE = '" + createDate + "'  AND FD.TYPE = 9996  AND FD.CODE = d.CITY) Owner_City, ")
        .append("ADDRESS Owner_Address,ZIP_CODE Owner_Zip,E_MAIL Owner_Email,CONTACTOR_NAME, ")
        .append("(SELECT distinct CODE_NAME  FROM FC_DMS_DICTIONARY FD  WHERE FD.IMPORT_DATE = '" + createDate + "'  AND FD.TYPE = 1006  AND FD.CODE = d.CONTACTOR_GENDER) Contacter_Sex, ")
        .append("CONTACTOR_PHONE Contacter_Tel,CONTACTOR_MOBILE Contacter_Mobile,CONTACTOR_ADDRESS Contacter_Address,CONTACTOR_EMAIL Contacter_Email, ")
        .append("CAR_SYNC,CAR_EA,OB_TYPE OB_TYPE, ")
        .append("(SELECT distinct Y.CODE_NAME FROM FC_DMS_DICTIONARY Y  WHERE  Y.IMPORT_DATE = '" + createDate + "'  AND  Y.TYPE = 1239  AND Y.CODE = d.certificate_type) ctype, ")
        .append("certificate_code ccode, ")
        .append("(select count(1)   from Fc_Dms_Ob_To_Data T  where T.vin = d.vin ")
        .append("and ((T.OB_TYPE = 'owner' AND T.OWNER_OBSTATUS = '2') or  (T.OB_TYPE like 'owner%contacter' and  (t.owner_obstatus = '2' or T.CONTACT_OBSTATUS = '2'))  or (T.OB_TYPE = 'contacter' AND T.CONTACT_OBSTATUS = '2')  ) ")
        .append("group by T.Vin) danum, ")
        .append("(select count(1) from Fc_Dms_To_Error r where r.vin = d.vin) ernum ")
        .append("FROM FC_DMS_TO_DATA d ")
        .append("where d.CREATE_OWNER_DATE = '" + createDate + "' and d.NO_OB_STR = 'K_17') p ");
      
      dmsToDataStmt = conn.prepareStatement(sqlBud.toString());
      ResultSet res = dmsToDataStmt.executeQuery();
      if (res != null)
      {
        subList = new ArrayList();
        while (res.next())
        {
          DmsSubCallCenterBean dscBean = new DmsSubCallCenterBean();
          dscBean.setYm(res.getString(1));
          dscBean.setSale_date(res.getString(2));
          dscBean.setDealer_code(res.getString(3));
          dscBean.setDealer_name(res.getString(4));
          dscBean.setVin_name(res.getString(5));
          dscBean.setColor(res.getString(6));
          dscBean.setCar_brand(res.getString(7));
          dscBean.setCar_model(res.getString(8));
          dscBean.setCatecode(res.getString(9));
          dscBean.setCar_carreg(res.getString(10));
          dscBean.setOwner_name(res.getString(11));
          dscBean.setOwner_sex(res.getString(12));
          dscBean.setOwner_birthday(res.getString(13));
          dscBean.setOwner_marital(res.getString(14));
          dscBean.setOwner_tel(res.getString(15));
          dscBean.setOwner_mobile(res.getString(16));
          dscBean.setOwner_province(res.getString(17));
          dscBean.setOwner_city(res.getString(18));
          dscBean.setOwner_address(res.getString(19));
          dscBean.setOwner_zip(res.getString(20));
          dscBean.setOwner_email(res.getString(21));
          dscBean.setContacter_name(res.getString(22));
          dscBean.setContacter_sex(res.getString(23));
          dscBean.setContacter_tel(res.getString(24));
          dscBean.setContacter_mobile(res.getString(25));
          dscBean.setContacter_address(res.getString(26));
          dscBean.setContacter_email(res.getString(27));
          dscBean.setCar_sync(res.getString(28));
          dscBean.setCar_ea(res.getString(29));
          dscBean.setOb_type(res.getString(30));
          dscBean.setCertificate_type(res.getString(31));
          dscBean.setCertificate_code(res.getString(32));
          dscBean.setSubCcNumber(Integer.valueOf(res.getInt(33)));
          dscBean.setCreateOwnerDate(createDate);
          subList.add(dscBean);
        }
      }
      conn.commit();
    }
    catch (Exception e)
    {
      log.error("[根据当前日期查询DMS反馈的数据集]", e);
      conn.rollback();
      throw e;
    }
    finally
    {
      if (dmsToDataStmt != null) {
        dmsToDataStmt.close();
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
    return subList;
  }
  
  public int selectCallCenterDataCount(String vin, String subDate)
    throws Exception
  {
    PreparedStatement dmsToDataStmt = null;
    Connection conn = null;
    Integer resNumber = Integer.valueOf(0);
    try
    {
      conn = DBConnection.getConnection();
      conn.setAutoCommit(false);
      String sql = "select count(*) from FC_DMS_OB_TO_DATA f where  f.SUB_DATE=?  and f.VIN=? ";
      dmsToDataStmt = conn.prepareStatement(sql);
      dmsToDataStmt.setString(1, subDate);
      dmsToDataStmt.setString(2, vin);
      ResultSet res = dmsToDataStmt.executeQuery();
      if (res != null) {
        while (res.next()) {
          resNumber = Integer.valueOf(res.getInt(1));
        }
      }
      conn.commit();
    }
    catch (Exception e)
    {
      log.error("根据读取日期查询当期读取数目异常", e);
      conn.rollback();
      throw e;
    }
    finally
    {
      if (dmsToDataStmt != null) {
        dmsToDataStmt.close();
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
    return resNumber.intValue();
  }
  
  public List<String> queryPropertiesByKey(String key)
    throws Exception
  {
    PreparedStatement dmsToDataStmt = null;
    Connection conn = null;
    List<String> pData = new ArrayList();
    log.info("[获取预发送CallCenter数据]时间差开始：" + key);
    try
    {
      conn = DBConnection.getConnection();
      conn.setAutoCommit(false);
      String sql = "select f.p_value from fc_dms_properties f where  f.p_key=?  ";
      dmsToDataStmt = conn.prepareStatement(sql);
      dmsToDataStmt.setString(1, key);
      ResultSet res = dmsToDataStmt.executeQuery();
      if (res != null) {
        while (res.next()) {
          pData.add(res.getString(1));
        }
      }
      conn.commit();
    }
    catch (Exception e)
    {
      log.error("查询properties表数据数目异常", e);
      conn.rollback();
      throw e;
    }
    finally
    {
      if (dmsToDataStmt != null) {
        dmsToDataStmt.close();
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
    return pData;
  }
  
  public void getSubCallCenterData(String readDate, List<DmsSubCallCenterBean> subList, List<String> lists)
    throws Exception
  {
    PreparedStatement dmsToDataStmt = null;
    Connection conn = null;
    log.info("[获取预发送CallCenter数据]开始");
    int day = 0;
    if ((lists.size() > 0) && (lists != null))
    {
      log.info("[获取预发送CallCenter数据]时间差结果1：getSubCallCenterData:" + (String)lists.get(0));
      day = Integer.parseInt((String)lists.get(0));
      log.info("[获取预发送CallCenter数据]时间差结果2：getSubCallCenterData:" + day);
    }
    try
    {
      log.info("[获取预发送CallCenter数据]时间：getSubCallCenterData:" + readDate);
      StringBuilder bud = new StringBuilder();
      
      bud.append(" with table1 as (select a.code,a.code_name,a.type from fc_dms_dictionary a )");
      bud.append("select to_char(sysdate,'yyyy') ym,sales_date,DEALER_SALE_CODE,DEALER_NAME,VIN VIN_NAME,")
        .append(" d.colorN as COLOR,")
        .append(" d.seriseN as CAR_BRAND,")
        .append(" d.modelN as CAR_MODEL,CAT_CODE,LISENCE_NO CAR_CARREG,OWNER_NAME,")
        .append(" d.GENDER ")
        .append(" ,d.BIRTHDAY Owner_Birthday,")
        .append(" d.owner_marriageN as Owner_Marital,PHONE Owner_Tel,MOBILE Owner_Mobile,")
        .append(" d.provinceN as  Owner_Province,")
        .append(" d.cityN as Owner_City,")
        .append("ADDRESS Owner_Address,ZIP_CODE Owner_Zip,E_MAIL Owner_Email,CONTACTOR_NAME,")
        .append("d.CONTACTOR_GENDER Contacter_Sex,")
        .append("CONTACTOR_PHONE Contacter_Tel,CONTACTOR_MOBILE Contacter_Mobile,CONTACTOR_ADDRESS Contacter_Address,")
        .append("CONTACTOR_EMAIL Contacter_Email,CAR_SYNC,CAR_EA,OB_TYPE OB_TYPE,d.IS_OWNER_ERROR,d.NO_OB_STR,")
        .append("0,")
        .append("0,")
        .append("0,")
        .append("0,")
        .append(" d.certitypeN as  ctype,certificate_code ccode")
        .append(" ,d.dup_type,d.receive_date");
      bud.append(" FROM ( select t2.*");
      bud.append(" ,serise.code_name  as seriseN ");
      bud.append(" ,model1.code_name           as modelN");
      bud.append(" ,confgation.code_name       as confgationN");
      bud.append(" ,color.code_name            as colorN");
      bud.append(" , province.code_name         as provinceN");
      bud.append(" ,city.code_name             as cityN");
      bud.append(" ,family_income.code_name    as familyN");
      bud.append(" ,certificate_type.code_name as certitypeN");
      bud.append(" ,owner_marriage.code_name   as owner_marriageN");
      
      bud.append(" FROM FC_DMS_TO_DATA_NEWOWNERS t2   \n ");
      bud.append(" left join  table1 serise on t2.serise = serise.code  and serise.type=9992 ");
      bud.append("  left join  table1 model1 on t2.MODEL = model1.code and model1.type=9993 ");
      bud.append("  left join  table1 confgation on t2.confgation = confgation.code ");
      bud.append(" left join  table1 color on t2.color= color.code and color.type=1103 ");
      bud.append(" left join  table1 province on t2.province= province.code and province.type=9995 ");
      bud.append(" left join  table1  city  on t2.city= city.code and city.type=9996 ");
      bud.append(" left join table1 owner_marriage on t2.owner_marriage=owner_marriage.code  and owner_marriage.type=1119 ");
      bud.append("  left join table1 family_income on t2.family_income = family_income.code and family_income.type=1118 ");
      bud.append("   left join  table1 certificate_type on t2.certificate_type = certificate_type.code and certificate_type.type=1239 ")
      
        .append(" where t2.receive_date='" + readDate + "'")
        .append(" and t2.IS_OWNER_ERROR='N'  and  ( nvl(t2.NO_OB_STR,'NC')='NC' or t2.NO_OB_STR in ('K_24','K_25','K_26','K_24,K_25','K_25,K_26','K_24,K_25,K_26','K_24,K_26'))");
      bud.append(" )d  ");
      conn = DBConnection.getConnection();
      conn.setAutoCommit(false);
      dmsToDataStmt = conn.prepareStatement(bud.toString());
      log.info("[获取预发送CallCenter数据]sql：" + bud.toString());
      ResultSet res = dmsToDataStmt.executeQuery();
      if (res != null) {
        while (res.next())
        {
          DmsSubCallCenterBean dscBean = new DmsSubCallCenterBean();
          dscBean.setYm(res.getString(1));
          dscBean.setSale_date(res.getString(2));
          dscBean.setDealer_code(res.getString(3));
          dscBean.setDealer_name(res.getString(4));
          dscBean.setVin_name(res.getString(5));
          dscBean.setColor(res.getString(6));
          dscBean.setCar_brand(res.getString(7));
          dscBean.setCar_model(res.getString(8));
          dscBean.setCatecode(res.getString(9));
          dscBean.setCar_carreg(res.getString(10));
          dscBean.setOwner_name(res.getString(11));
          if ((res.getString(12) != null) && (!"".equals(res.getString(12))))
          {
            if ("10061001".equals(res.getString(12))) {
              dscBean.setOwner_sex("男");
            } else if ("10061002".equals(res.getString(12))) {
              dscBean.setOwner_sex("女");
            } else if ("10061003".equals(res.getString(12))) {
              dscBean.setOwner_sex("未知");
            } else {
              dscBean.setOwner_sex(res.getString(12));
            }
          }
          else {
            dscBean.setOwner_sex(res.getString(12));
          }
          dscBean.setOwner_birthday(res.getString(13));
          dscBean.setOwner_marital(res.getString(14));
          dscBean.setOwner_tel(res.getString(15));
          dscBean.setOwner_mobile(res.getString(16));
          dscBean.setOwner_province(res.getString(17));
          dscBean.setOwner_city(res.getString(18));
          dscBean.setOwner_address(res.getString(19));
          dscBean.setOwner_zip(res.getString(20));
          dscBean.setOwner_email(res.getString(21));
          dscBean.setContacter_name(res.getString(22));
          dscBean.setContacter_sex(res.getString(23));
          dscBean.setContacter_tel(res.getString(24));
          dscBean.setContacter_mobile(res.getString(25));
          dscBean.setContacter_address(res.getString(26));
          dscBean.setContacter_email(res.getString(27));
          dscBean.setCar_sync(res.getString(28));
          dscBean.setCar_ea(res.getString(29));
          dscBean.setOb_type(res.getString(30));
          dscBean.setIsOwnerError(res.getString(31));
          dscBean.setIsObStr(res.getString(32));
          dscBean.setNumber1(res.getInt(33));
          dscBean.setNumber2(res.getInt(34));
          dscBean.setNumber3(res.getInt(35));
          dscBean.setNumber4(res.getInt(36));
          dscBean.setCreateOwnerDate(readDate);
          dscBean.setCertificate_type(res.getString(37));
          dscBean.setCertificate_code(res.getString(38));
          
          dscBean.setPriority("B");
          dscBean.setDup_type(res.getString(39));
          dscBean.setReceiveDate(res.getString(40));
          subList.add(dscBean);
        }
      }
      conn.commit();
    }
    catch (Exception e)
    {
      log.error("获取预发送CallCenter数据出现异常", e);
      conn.rollback();
      throw e;
    }
    finally
    {
      if (dmsToDataStmt != null) {
        dmsToDataStmt.close();
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
  
  public void getSubCallCenterData2(String readDate, List<DmsSubCallCenterBean> subList, List<String> lists)
    throws Exception
  {
    PreparedStatement dmsToDataStmt = null;
    Connection conn = null;
    log.info("[获取预发送CallCenter数据]开始");
    int day = 0;
    if ((lists.size() > 0) && (lists != null))
    {
      log.info("[获取预发送CallCenter数据]时间差结果1：getSubCallCenterData:" + (String)lists.get(0));
      day = Integer.parseInt((String)lists.get(0));
      log.info("[获取预发送CallCenter数据]时间差结果2：getSubCallCenterData:" + day);
    }
    try
    {
      log.info("[获取预发送CallCenter数据]时间：getSubCallCenterData:" + readDate);
      StringBuilder bud = new StringBuilder();
      bud.append("select to_char(sysdate,'yyyy') ym,sales_date,DEALER_SALE_CODE,DEALER_NAME,VIN VIN_NAME,")
        .append("(SELECT distinct CODE_NAME FROM FC_DMS_DICTIONARY FD WHERE FD.IMPORT_DATE='" + readDate + "' ")
        .append(" AND FD.TYPE=1103 AND FD.CODE=d.COLOR) COLOR,")
        .append("(SELECT distinct CODE_NAME FROM FC_DMS_DICTIONARY FD WHERE FD.IMPORT_DATE='" + readDate + "' ")
        .append(" AND FD.TYPE=9992 AND FD.CODE=d.SERISE) CAR_BRAND,")
        .append("(SELECT distinct CODE_NAME FROM FC_DMS_DICTIONARY FD WHERE FD.IMPORT_DATE='" + readDate + "' ")
        .append(" AND FD.TYPE=9993 AND FD.CODE=d.MODEL)  CAR_MODEL,CAT_CODE,LISENCE_NO CAR_CARREG,OWNER_NAME,")
        .append("(SELECT distinct CODE_NAME FROM FC_DMS_DICTIONARY FD WHERE FD.IMPORT_DATE='" + readDate + "' ")
        .append("AND FD.TYPE=1006 AND FD.CODE=d.GENDER) Owner_Sex,")
        .append("BIRTHDAY Owner_Birthday,")
        .append("(SELECT distinct CODE_NAME FROM FC_DMS_DICTIONARY FD WHERE FD.IMPORT_DATE='" + readDate + "' ")
        .append(" AND FD.TYPE=1119 AND FD.CODE=d.OWNER_MARRIAGE) Owner_Marital,PHONE Owner_Tel,MOBILE Owner_Mobile,")
        .append("(SELECT distinct CODE_NAME FROM FC_DMS_DICTIONARY FD WHERE FD.IMPORT_DATE='" + readDate + "' ")
        .append("AND FD.TYPE=9995 AND FD.CODE=d.PROVINCE) Owner_Province,")
        .append("(SELECT distinct CODE_NAME FROM FC_DMS_DICTIONARY FD WHERE FD.IMPORT_DATE='" + readDate + "' ")
        .append("AND FD.TYPE=9996 AND FD.CODE=d.CITY) Owner_City,")
        .append("ADDRESS Owner_Address,ZIP_CODE Owner_Zip,E_MAIL Owner_Email,CONTACTOR_NAME,")
        .append("(SELECT distinct CODE_NAME FROM FC_DMS_DICTIONARY FD WHERE FD.IMPORT_DATE='" + readDate + "' AND FD.TYPE=1006 AND FD.CODE=d.CONTACTOR_GENDER) Contacter_Sex,")
        .append("CONTACTOR_PHONE Contacter_Tel,CONTACTOR_MOBILE Contacter_Mobile,CONTACTOR_ADDRESS Contacter_Address,")
        .append("CONTACTOR_EMAIL Contacter_Email,CAR_SYNC,CAR_EA,OB_TYPE OB_TYPE,d.IS_OWNER_ERROR,d.NO_OB_STR,")
        .append("0,")
        .append("0,")
        .append("0,")
        .append("0,")
        .append("(SELECT distinct Y.CODE_NAME FROM FC_DMS_DICTIONARY Y WHERE Y.IMPORT_DATE='" + readDate + "' AND Y.TYPE=1239 AND Y.CODE=d.certificate_type) ctype,certificate_code ccode")
        .append(" ,d.dup_type,d.receive_date")
        .append(" FROM FC_DMS_TO_DATA_NEWOWNERS d  ")
        
        .append(" where d.receive_date='" + readDate + "'")
        .append(" and d.IS_OWNER_ERROR='N'  and  ( nvl(d.NO_OB_STR,'NC')='NC' or d.NO_OB_STR in ('K_24','K_25','K_26','K_24,K_25','K_25,K_26','K_24,K_25,K_26','K_24,K_26'))");
      conn = DBConnection.getConnection();
      conn.setAutoCommit(false);
      dmsToDataStmt = conn.prepareStatement(bud.toString());
      log.info("[获取预发送CallCenter数据]sql：" + bud.toString());
      ResultSet res = dmsToDataStmt.executeQuery();
      if (res != null) {
        while (res.next())
        {
          DmsSubCallCenterBean dscBean = new DmsSubCallCenterBean();
          dscBean.setYm(res.getString(1));
          dscBean.setSale_date(res.getString(2));
          dscBean.setDealer_code(res.getString(3));
          dscBean.setDealer_name(res.getString(4));
          dscBean.setVin_name(res.getString(5));
          dscBean.setColor(res.getString(6));
          dscBean.setCar_brand(res.getString(7));
          dscBean.setCar_model(res.getString(8));
          dscBean.setCatecode(res.getString(9));
          dscBean.setCar_carreg(res.getString(10));
          dscBean.setOwner_name(res.getString(11));
          dscBean.setOwner_sex(res.getString(12));
          dscBean.setOwner_birthday(res.getString(13));
          dscBean.setOwner_marital(res.getString(14));
          dscBean.setOwner_tel(res.getString(15));
          dscBean.setOwner_mobile(res.getString(16));
          dscBean.setOwner_province(res.getString(17));
          dscBean.setOwner_city(res.getString(18));
          dscBean.setOwner_address(res.getString(19));
          dscBean.setOwner_zip(res.getString(20));
          dscBean.setOwner_email(res.getString(21));
          dscBean.setContacter_name(res.getString(22));
          dscBean.setContacter_sex(res.getString(23));
          dscBean.setContacter_tel(res.getString(24));
          dscBean.setContacter_mobile(res.getString(25));
          dscBean.setContacter_address(res.getString(26));
          dscBean.setContacter_email(res.getString(27));
          dscBean.setCar_sync(res.getString(28));
          dscBean.setCar_ea(res.getString(29));
          dscBean.setOb_type(res.getString(30));
          dscBean.setIsOwnerError(res.getString(31));
          dscBean.setIsObStr(res.getString(32));
          dscBean.setNumber1(res.getInt(33));
          dscBean.setNumber2(res.getInt(34));
          dscBean.setNumber3(res.getInt(35));
          dscBean.setNumber4(res.getInt(36));
          dscBean.setCreateOwnerDate(readDate);
          dscBean.setCertificate_type(res.getString(37));
          dscBean.setCertificate_code(res.getString(38));
          
          dscBean.setPriority("B");
          dscBean.setDup_type(res.getString(39));
          dscBean.setReceiveDate(res.getString(40));
          subList.add(dscBean);
        }
      }
      conn.commit();
    }
    catch (Exception e)
    {
      log.error("获取预发送CallCenter数据出现异常", e);
      conn.rollback();
      throw e;
    }
    finally
    {
      if (dmsToDataStmt != null) {
        dmsToDataStmt.close();
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
  
  public List<FcServiceLogBean> getFcLogBean()
    throws Exception
  {
    PreparedStatement dmsToDataStmt = null;
    Connection conn = null;
    List<FcServiceLogBean> arrayList = new ArrayList();
    try
    {
      conn = DBConnection.getConnection();
      conn.setAutoCommit(false);
      String sql = "select START_DATE,IS_DMS_TO_DATA,IS_CHECKED_DATA,IS_PRODUCE_ERROR,IS_SUB_CALL,IS_CC_GET,IS_ZEIT_CC,IS_CC_ERROR,IS_SUB_DMS from FC_SERVICE_LOG order by START_DATE desc";
      dmsToDataStmt = conn.prepareStatement(sql);
      ResultSet res = dmsToDataStmt.executeQuery();
      if (res != null) {
        while (res.next())
        {
          FcServiceLogBean fcBean = new FcServiceLogBean();
          fcBean.setStartDate(res.getString(1));
          fcBean.setIsDmsToData(Long.valueOf(res.getLong(2)));
          fcBean.setIsCheckedData(Long.valueOf(res.getLong(3)));
          fcBean.setIsProduceError(Long.valueOf(res.getLong(4)));
          fcBean.setIsSubCall(Long.valueOf(res.getLong(5)));
          fcBean.setIsCCGet(Long.valueOf(res.getLong(6)));
          fcBean.setIsZeitCC(Long.valueOf(res.getLong(7)));
          fcBean.setIsCCError(Long.valueOf(res.getLong(8)));
          fcBean.setIsSubDms(Long.valueOf(res.getLong(9)));
          arrayList.add(fcBean);
        }
      }
      conn.commit();
    }
    catch (Exception e)
    {
      log.error("查询全局日志异常", e);
      conn.rollback();
      throw e;
    }
    finally
    {
      if (dmsToDataStmt != null) {
        dmsToDataStmt.close();
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
    return arrayList;
  }
  
  public List<M2FServiceLogBean> getM2LogBean()
    throws Exception
  {
    PreparedStatement dmsToDataStmt = null;
    Connection conn = null;
    List<M2FServiceLogBean> m2List = new ArrayList();
    try
    {
      conn = DBConnection.getConnection();
      conn.setAutoCommit(false);
      String sql = "select CREATE_DATE,IS_SUB_CALL_CENTER,IS_GET_CALL_CENTER,IS_SUB_DMS,IS_GET_DMS  from FC_M2F_SERVICE_LOG  ";
      dmsToDataStmt = conn.prepareStatement(sql);
      ResultSet res = dmsToDataStmt.executeQuery();
      if (res != null) {
        while (res.next())
        {
          M2FServiceLogBean m2Bean = new M2FServiceLogBean();
          m2Bean.setCREATE_DATE(res.getString(1));
          m2Bean.setIS_SUB_CALL_CENTER(res.getInt(2));
          m2Bean.setIS_GET_CALL_CENTER(res.getInt(3));
          m2Bean.setIS_SUB_DMS(res.getInt(4));
          m2Bean.setIS_GET_DMS(res.getInt(5));
          m2List.add(m2Bean);
        }
      }
      conn.commit();
    }
    catch (Exception e)
    {
      log.error("查询售前全局日志异常", e);
      conn.rollback();
      throw e;
    }
    finally
    {
      if (dmsToDataStmt != null) {
        dmsToDataStmt.close();
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
    return m2List;
  }
  
  public void updateFcServiceLog(FcServiceLogBean fcBean)
    throws Exception
  {
    PreparedStatement dmsToDataStmt = null;
    Connection conn = null;
    try
    {
      log.info("[更新售后全局日志]开始");
      conn = DBConnection.getConnection();
      conn.setAutoCommit(false);
      String sql = "UPDATE FC_SERVICE_LOG  SET  IS_DMS_TO_DATA=?,IS_CHECKED_DATA=?,IS_PRODUCE_ERROR=?,IS_SUB_CALL=?,IS_CC_GET=?,IS_ZEIT_CC=?,IS_CC_ERROR=?,IS_SUB_DMS=? WHERE START_DATE=? ";
      dmsToDataStmt = conn.prepareStatement(sql);
      dmsToDataStmt.setLong(1, fcBean.getIsDmsToData().longValue());
      dmsToDataStmt.setLong(2, fcBean.getIsCheckedData().longValue());
      dmsToDataStmt.setLong(3, fcBean.getIsProduceError().longValue());
      dmsToDataStmt.setLong(4, fcBean.getIsSubCall().longValue());
      dmsToDataStmt.setLong(5, fcBean.getIsCCGet().longValue());
      dmsToDataStmt.setLong(6, fcBean.getIsZeitCC().longValue());
      dmsToDataStmt.setLong(7, fcBean.getIsCCError().longValue());
      dmsToDataStmt.setLong(8, fcBean.getIsSubDms().longValue());
      dmsToDataStmt.setString(9, fcBean.getStartDate());
      dmsToDataStmt.addBatch();
      dmsToDataStmt.executeBatch();
      conn.commit();
    }
    catch (Exception e)
    {
      log.error("更新售后全局日志出现异常", e);
      conn.rollback();
      if (dmsToDataStmt != null) {
        dmsToDataStmt.close();
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
      if (dmsToDataStmt != null) {
        dmsToDataStmt.close();
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
  
  public void updateM2ServiceLog(M2FServiceLogBean m2Bean)
    throws Exception
  {
    PreparedStatement dmsToDataStmt = null;
    Connection conn = null;
    try
    {
      log.info("[更新售前全局日志]开始");
      conn = DBConnection.getConnection();
      conn.setAutoCommit(false);
      String sql = "UPDATE FC_M2F_SERVICE_LOG  SET  IS_SUB_CALL_CENTER=?,IS_GET_CALL_CENTER=?,IS_SUB_DMS=?,IS_GET_DMS=?  WHERE CREATE_DATE=? ";
      dmsToDataStmt = conn.prepareStatement(sql);
      dmsToDataStmt.setLong(1, m2Bean.getIS_SUB_CALL_CENTER());
      dmsToDataStmt.setLong(2, m2Bean.getIS_GET_CALL_CENTER());
      dmsToDataStmt.setLong(3, m2Bean.getIS_SUB_DMS());
      dmsToDataStmt.setLong(4, m2Bean.getIS_GET_DMS());
      dmsToDataStmt.setString(5, m2Bean.getCREATE_DATE());
      dmsToDataStmt.addBatch();
      dmsToDataStmt.executeBatch();
      conn.commit();
    }
    catch (Exception e)
    {
      log.error("更新售前全局日志出现异常", e);
      conn.rollback();
      if (dmsToDataStmt != null) {
        dmsToDataStmt.close();
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
      if (dmsToDataStmt != null) {
        dmsToDataStmt.close();
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
  
  public void updateOwnersTypes(String importDate)
    throws Exception
  {
    PreparedStatement dmsToDataStmt = null;
    Connection conn = null;
    try
    {
      log.info("[更新新车主拆分类别]开始");
      conn = DBConnection.getConnection();
      conn.setAutoCommit(false);
      StringBuilder bud = new StringBuilder();
      bud.append(" UPDATE FC_DMS_TO_DATA_TEMP D ")
        .append(" SET D.IS_NEW_OWNER = 'X' ")
        .append("  WHERE ")
        .append("  D.IS_NEW_OWNER = 'D' ");
      dmsToDataStmt = conn.prepareStatement(bud.toString());
      dmsToDataStmt.addBatch();
      dmsToDataStmt.executeBatch();
      conn.commit();
    }
    catch (Exception e)
    {
      log.error("[更新新车主拆分类别]出现异常", e);
      conn.rollback();
      if (dmsToDataStmt != null) {
        dmsToDataStmt.close();
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
      if (dmsToDataStmt != null) {
        dmsToDataStmt.close();
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
  
  public void updateDmsOld(List<DmsToDataBean> ownersold)
    throws SQLException
  {
    if ((ownersold != null) && (ownersold.size() > 0))
    {
      PreparedStatement dmsToDataStmt = null;
      Connection conn = null;
      try
      {
        log.info("[update 7天前的dms数据]开始");
        conn = DBConnection.getConnection();
        conn.setAutoCommit(false);
        StringBuilder bud = new StringBuilder();
        bud.append(" UPDATE FC_DMS_TO_DATA t ")
          .append(" SET t.car_sync = ?,t.car_ea=?,t.no_ob_str=?,t.mobile=?,t.phone=?,t.contactor_mobile=?,t.contactor_phone=? ")
          .append(" ,t.address=?,t.contactor_address=? ")
          .append(" ,t.owner_name=?,t.gender=?,t.e_mail=? ")
          .append(" ,t.ob_type=?,t.is_owner_error=?")
          .append(" ,t.data_type=? ")
          .append("  WHERE ")
          .append("   t.create_owner_date=? and t.vin=?");
        dmsToDataStmt = conn.prepareStatement(bud.toString());
        int i = 0;
        for (DmsToDataBean dmsToBeanOld : ownersold)
        {
          String vin = dmsToBeanOld.getVin();
          i++;
          String createOwnerDate = dmsToBeanOld.getCreateOwnerDate();
          log.info("[update 7天前的dms数据]" + vin + "........" + createOwnerDate + "......" + i);
          dmsToDataStmt.setString(1, dmsToBeanOld.getCarSync());
          dmsToDataStmt.setString(2, dmsToBeanOld.getCarEa());
          dmsToDataStmt.setString(3, dmsToBeanOld.getIsObStr());
          dmsToDataStmt.setString(4, dmsToBeanOld.getMobile());
          dmsToDataStmt.setString(5, dmsToBeanOld.getPhone());
          dmsToDataStmt.setString(6, dmsToBeanOld.getContactor_mobile());
          dmsToDataStmt.setString(7, dmsToBeanOld.getContactor_phone());
          
          dmsToDataStmt.setString(8, dmsToBeanOld.getAddress());
          dmsToDataStmt.setString(9, dmsToBeanOld.getContactor_address());
          
          dmsToDataStmt.setString(10, dmsToBeanOld.getOwner_name());
          dmsToDataStmt.setString(11, dmsToBeanOld.getGender());
          dmsToDataStmt.setString(12, dmsToBeanOld.getE_mail());
          dmsToDataStmt.setString(13, dmsToBeanOld.getObType());
          
          dmsToDataStmt.setString(14, dmsToBeanOld.getIsOwnerError());
          
          dmsToDataStmt.setString(15, dmsToBeanOld.getData_type());
          
          dmsToDataStmt.setString(16, dmsToBeanOld.getCreateOwnerDate());
          dmsToDataStmt.setString(17, dmsToBeanOld.getVin());
          dmsToDataStmt.addBatch();
        }
        log.info("[update 7天前的dms数据execute...start]");
        dmsToDataStmt.executeBatch();
        log.info("[update 7天前的dms数据结束]");
        conn.commit();
      }
      catch (Exception e)
      {
        log.error("[update 7天前的dms数据]出现异常", e);
        conn.rollback();
        if (dmsToDataStmt != null) {
          dmsToDataStmt.close();
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
        if (dmsToDataStmt != null) {
          dmsToDataStmt.close();
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
  
  public synchronized List<String> searchIsObHistory(String importDate)
    throws Exception
  {
    log.info("[查询是否存在OB历史]开始");
    PreparedStatement dmsToDataStmt = null;
    Connection conn = null;
    List<String> vinList = new ArrayList();
    try
    {
      conn = DBConnection.getConnection();
      StringBuffer sqlBuf = new StringBuffer();
      sqlBuf.append("SELECT F.VIN FROM FC_DMS_OB_TO_DATA F WHERE  ");
      sqlBuf.append("  ((F.OB_TYPE = 'owner' AND F.OWNER_OBSTATUS = '2') or ");
      sqlBuf.append("  (F.OB_TYPE like 'owner%contacter' and  ");
      sqlBuf.append("  (F.owner_obstatus = '2' or F.CONTACT_OBSTATUS = '2'))  or (F.OB_TYPE = 'contacter' AND F.CONTACT_OBSTATUS = '2')   ) ");
      
      sqlBuf.append(" and  F.RETURN_DATE =? ");
      sqlBuf.append(" group by F.VIN ");
      dmsToDataStmt = conn.prepareStatement(sqlBuf.toString());
      SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
      Date importDate_n = sdf.parse(importDate);
      DateFormat formaty = new SimpleDateFormat("yyyyMMdd");
      Calendar calendar = new GregorianCalendar();
      
      calendar.setTime(importDate_n);
      calendar.add(5, -8);
      importDate_n = calendar.getTime();
      String returnDate = formaty.format(importDate_n);
      dmsToDataStmt.setString(1, returnDate);
      log.info("查询是否存在OB历史searchIsObHistory，查询日期为：" + returnDate);
      
      ResultSet res = dmsToDataStmt.executeQuery();
      if (res != null) {
        while (res.next()) {
          vinList.add(res.getString(1));
        }
      }
    }
    catch (Exception e)
    {
      log.error("[查询是否存在OB历史]searchIsObHistory出现异常", e);
      conn.rollback();
      throw e;
    }
    finally
    {
      if (dmsToDataStmt != null) {
        dmsToDataStmt.close();
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
    return vinList;
  }
  
  public synchronized List<String> searchIsFCHistory(String importDate)
    throws Exception
  {
    log.info("[查询是否存在空错号历史]开始");
    PreparedStatement dmsToDataStmt = null;
    Connection conn = null;
    List<String> vinList = new ArrayList();
    try
    {
      SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
      Date importDate_n = sdf.parse(importDate);
      DateFormat formaty = new SimpleDateFormat("yyyyMMdd");
      Calendar calendar = new GregorianCalendar();
      
      calendar.setTime(importDate_n);
      calendar.add(5, -7);
      importDate_n = calendar.getTime();
      String createDate = formaty.format(importDate_n);
      conn = DBConnection.getConnection();
      
      StringBuffer sqlB = new StringBuffer();
      log.info("查询是否存在 空错号 历史searchIsFCHistory，查询日期为：" + createDate);
      sqlB.append("SELECT  R.VIN  FROM FC_DMS_TO_ERROR R WHERE substr(r.CREATE_DATE,0,8)='" + createDate + "' GROUP BY R.VIN");
      dmsToDataStmt = conn.prepareStatement(sqlB.toString());
      ResultSet res = dmsToDataStmt.executeQuery();
      if (res != null) {
        while (res.next()) {
          vinList.add(res.getString(1));
        }
      }
    }
    catch (Exception e)
    {
      log.error("[查询是否存在 空错号 历史]searchIsFCHistory出现异常", e);
      conn.rollback();
      throw e;
    }
    finally
    {
      if (dmsToDataStmt != null) {
        dmsToDataStmt.close();
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
    return vinList;
  }
  
  public synchronized List<DmsToDataBean> searchDmsHistory(String importDate)
    throws Exception
  {
    log.info("[查询7天前接收到的dms数据]searchDmsHistory开始");
    PreparedStatement dmsToDataStmt = null;
    Connection conn = null;
    List<DmsToDataBean> dmsList = new ArrayList();
    try
    {
      conn = DBConnection.getConnection();
      
      SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
      Date importDate_n = sdf.parse(importDate);
      DateFormat formaty = new SimpleDateFormat("yyyyMMdd");
      String endDate = formaty.format(importDate_n);
      Calendar calendar = new GregorianCalendar();
      
      calendar.setTime(importDate_n);
      calendar.add(5, -7);
      importDate_n = calendar.getTime();
      String createDate = formaty.format(importDate_n);
      StringBuffer sqlB = new StringBuffer();
      sqlB.append("SELECT  t.MOBILE,t.PHONE,t.CONTACTOR_MOBILE,t.CONTACTOR_PHONE,t.OB_TYPE,t.vin,t.OWNER_ID,t.DEALER_SALE_CODE,t.OWNER_NAME,t.GENDER,t.ADDRESS,t.E_MAIL  ");
      sqlB.append(" ,t.CONTACTOR_NAME,t.CONTACTOR_GENDER,t.PROVINCE,t.CITY,t.CONTACTOR_ADDRESS,t.CONTACTOR_EMAIL,t.CERTIFICATE_TYPE,t.CERTIFICATE_CODE ");
      sqlB.append(" FROM ( select c.*,row_number() OVER (PARTITION BY vin ORDER BY c.CREATE_OWNER_DATE desc) AS NUM");
      sqlB.append(" from  fc_dms_to_data c WHERE to_date(c.CREATE_OWNER_DATE,'yyyyMMDD') >= to_date('" + createDate + "','yyyymmdd') ");
      sqlB.append(" and to_date(c.CREATE_OWNER_DATE,'yyyyMMDD') <= to_date('" + endDate + "','yyyymmdd')");
      sqlB.append("  ) t WHERE t.NUM=1 ");
      dmsToDataStmt = conn.prepareStatement(sqlB.toString());
      ResultSet res = dmsToDataStmt.executeQuery();
      if (res != null) {
        while (res.next())
        {
          DmsToDataBean dms = new DmsToDataBean();
          dms.setMobile(res.getString(1));
          dms.setPhone(res.getString(2));
          dms.setContactor_mobile(res.getString(3));
          dms.setContactor_phone(res.getString(4));
          dms.setObType(res.getString(5));
          dms.setVin(res.getString(6));
          dms.setCreateOwnerDate(endDate);
          dms.setOwner_id(res.getString(7));
          dms.setDealer_sale_code(res.getString(8));
          dms.setOwner_name(res.getString(9));
          dms.setGender(res.getString(10));
          dms.setAddress(res.getString(11));
          dms.setE_mail(res.getString(12));
          dms.setContactor_name(res.getString(13));
          dms.setContactor_gender(res.getString(14));
          dms.setProvince(res.getString(15));
          dms.setCity(res.getString(16));
          dms.setContactor_address(res.getString(17));
          dms.setContactor_email(res.getString(18));
          dms.setCertificate_type(res.getString(19));
          dms.setCertificate_code(res.getString(20));
          dmsList.add(dms);
        }
      }
    }
    catch (Exception e)
    {
      log.error("[查询7天前接收到的dms数据]searchDmsHistory出现异常", e);
      conn.rollback();
      throw e;
    }
    finally
    {
      if (dmsToDataStmt != null) {
        dmsToDataStmt.close();
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
    return dmsList;
  }
  
  public List<UploadDmsDataErrorBean> getErrorDmsDataHistory()
    throws Exception
  {
    PreparedStatement dmsToDataStmt = null;
    Connection conn = null;
    List<UploadDmsDataErrorBean> uploadList = null;
    try
    {
      DateFormat formaty = new SimpleDateFormat("yyyyMMdd");
      Calendar calendar = new GregorianCalendar();
      Date date = new Date();
      calendar.setTime(date);
      calendar.add(5, -7);
      date = calendar.getTime();
      String createDate = formaty.format(date);
      log.info("查询7天之内的空错号次数Fc_Dms_To_Error，查询日期为：" + createDate);
      conn = DBConnection.getConnection();
      conn.setAutoCommit(false);
      StringBuilder bud = new StringBuilder();
      bud.append("select distinct r.vin, ")
        .append("r.dealer_sale_code, ")
        .append("nvl(r.ODP1_CODE, '2'), ")
        .append("nvl(r.ODP2_CODE, '2'), ")
        .append("r.owner_name,r.gender,")
        .append("r.address,r.email,r.mobile,r.phone,to_char(sysdate,'yyyy-MM-dd HH24:mm:ss') REBACK_DATE,r.contactor_name,r.contactor_gender, ")
        .append("r.contactor_phone,r.contactor_mobile,r.contactor_province,r.contactor_city, ")
        .append("r.contactor_address,r.contactor_email, ")
        .append("(select distinct y.code_name ")
        .append(" from Fc_Dms_Dictionary y ")
        .append("  where y.type = 1239 ")
        .append(" and y.code = r.CERTIFICATE_TYPE ")
        .append(" ) CERTIFICATE_TYPE, ")
        
        .append("r.certificate_code    ")
        .append("  from  ( select c.*,row_number() OVER (PARTITION BY vin ORDER BY c.CREATE_DATE desc) AS NUM  ")
        .append("  from Fc_Dms_To_Error c   where  ")
        .append(" to_date(c.create_date,'yyyyMMDD')>=to_date('" + createDate + "','yyyyMMDD')   ")
        .append(" and c.data_type = 'second'  ) r WHERE r.NUM=1  ");
      dmsToDataStmt = conn.prepareStatement(bud.toString());
      log.info("查询7天前的空错号数据Fc_Dms_To_Error，查询日期为：" + createDate);
      dmsToDataStmt.setString(1, createDate);
      ResultSet res = dmsToDataStmt.executeQuery();
      if (res != null)
      {
        uploadList = new ArrayList();
        while (res.next())
        {
          UploadDmsDataErrorBean upload = new UploadDmsDataErrorBean();
          upload.setVin(res.getString(1));
          upload.setDealer_sale_code(res.getString(2));
          

          upload.setOwner_name(res.getString(5));
          upload.setGender(res.getString(6));
          upload.setAddress(res.getString(7));
          upload.setEmail(res.getString(8));
          upload.setMobile(res.getString(9));
          upload.setPhone(res.getString(10));
          upload.setReback_date(res.getString(11));
          upload.setContactor_name(res.getString(12));
          upload.setContactor_gender(res.getString(13));
          upload.setContactor_phone(res.getString(14));
          upload.setContactor_mobile(res.getString(15));
          upload.setContactor_province(res.getString(16));
          upload.setContactor_city(res.getString(17));
          upload.setContactor_address(res.getString(18));
          upload.setContactor_email(res.getString(19));
          upload.setCERTIFICATE_TYPE(res.getString(20));
          upload.setCERTIFICATE_CODE(res.getString(21));
          uploadList.add(upload);
        }
      }
      conn.commit();
      log.info("查询7天前的空错号数据Fc_Dms_To_Error，总条数为：" + uploadList.size());
    }
    catch (Exception e)
    {
      log.error("查询7天前的空错号数据Fc_Dms_To_Error异常", e);
      conn.rollback();
      throw e;
    }
    finally
    {
      if (dmsToDataStmt != null) {
        dmsToDataStmt.close();
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
    return uploadList;
  }
  
  public Long searchDmsErrorCount(String importDate, String vin)
    throws Exception
  {
    PreparedStatement dmsToDataStmt = null;
    Connection conn = null;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    Date importDate_n = sdf.parse(importDate);
    DateFormat formaty = new SimpleDateFormat("yyyyMMdd");
    Calendar calendar = new GregorianCalendar();
    calendar.setTime(importDate_n);
    calendar.add(5, -7);
    importDate_n = calendar.getTime();
    String createDate = formaty.format(importDate_n);
    log.info("查询7天之内的空错号次数searchDmsErrorCount，查询日期为：" + createDate);
    try
    {
      conn = DBConnection.getConnection();
      conn.setAutoCommit(false);
      StringBuilder bud = new StringBuilder();
      bud.append(" select nvl((DANUM+ERNUM),0) cunum,p.vin from ( select   nvl((select count(1)   from Fc_Dms_Ob_To_Data T  where T.vin = d.vin ")
      
        .append(" and ((T.OB_TYPE = 'owner' AND T.OWNER_OBSTATUS = '2') or  (T.OB_TYPE like 'owner%contacter' and  (t.owner_obstatus = '2' or T.CONTACT_OBSTATUS = '2')) or (T.OB_TYPE = 'contacter' AND T.CONTACT_OBSTATUS = '2')  )  ")
        .append(" group by T.Vin),0) danum, ")
        .append(" nvl((select count(1) from Fc_Dms_To_Error r where r.vin = d.vin),0) ernum,d.vin vin ")
        .append(" FROM fc_dms_to_data_newowners d ")
        .append("  where d.CREATE_OWNER_DATE >= '" + createDate + "' and  d.create_owner_date<='" + importDate + "' and d.NO_OB_STR like  '%K_02%') p  ")
        .append(" where p.vin='" + vin + "' ");
      dmsToDataStmt = conn.prepareStatement(bud.toString());
      ResultSet res = dmsToDataStmt.executeQuery();
      if ((res != null) && 
        (res.next()))
      {
        Long cnum = Long.valueOf(res.getLong(1));
        return cnum;
      }
     
      conn.commit();
      log.info("查询7天之内的空错号次数searchDmsErrorCount");
    }
    catch (Exception e)
    {
      log.error("查询7天之内的空错号次数searchDmsErrorCount异常", e);
      conn.rollback();
      throw e;
    }
    finally
    {
      if (dmsToDataStmt != null) {
        dmsToDataStmt.close();
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
    if (dmsToDataStmt != null) {
      dmsToDataStmt.close();
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
    return Long.valueOf(0L);
  }
  
  public List<DmsToDataBean> searchDmsErrorCountByObAndTo(String importDate)
    throws Exception
  {
    PreparedStatement dmsToDataStmt = null;
    Connection conn = null;
    List<DmsToDataBean> uploadList = null;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    Date importDate_n = sdf.parse(importDate);
    DateFormat formaty = new SimpleDateFormat("yyyyMMdd");
    Calendar calendar = new GregorianCalendar();
    
    calendar.setTime(importDate_n);
    calendar.add(5, -7);
    importDate_n = calendar.getTime();
    String createDate = formaty.format(importDate_n);
    log.info("查询7天之内的空错号次数2Fc_Dms_To_data，查询日期为：" + createDate);
    try
    {
      conn = DBConnection.getConnection();
      conn.setAutoCommit(false);
      StringBuilder bud = new StringBuilder();
      bud.append("select c.vin ")
        .append(" from Fc_Dms_To_data c join fc_dms_ob_to_data t on  t.vin =c.vin ")
        .append(" and to_date(c.create_owner_date,'yyyyMMDD')>=to_date('" + createDate + "','yyyyMMDD')   ")
        .append("  and c.no_ob_str like '%K_02%' ")
        .append("  and ((T.OB_TYPE = 'owner' AND T.OWNER_OBSTATUS = '2') or (T.OB_TYPE like 'owner%contacter' and   (t.owner_obstatus = '2' or T.CONTACT_OBSTATUS = '2')) or (T.OB_TYPE = 'contacter' AND T.CONTACT_OBSTATUS = '2') ) ");
      dmsToDataStmt = conn.prepareStatement(bud.toString());
      ResultSet res = dmsToDataStmt.executeQuery();
      if (res != null)
      {
        uploadList = new ArrayList();
        while (res.next())
        {
          DmsToDataBean upload = new DmsToDataBean();
          upload.setVin(res.getString(1));
          uploadList.add(upload);
        }
      }
      conn.commit();
      log.info("查询7天之内的空错号次数2Fc_Dms_To_data，总条数为：" + uploadList.size());
    }
    catch (Exception e)
    {
      log.error("查询7天之内的空错号次数2Fc_Dms_To_data异常", e);
      conn.rollback();
      throw e;
    }
    finally
    {
      if (dmsToDataStmt != null) {
        dmsToDataStmt.close();
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
    return uploadList;
  }
  
  public List<DmsToDataBean> searchDmsObErrorCount()
    throws Exception
  {
    PreparedStatement dmsToDataStmt = null;
    List<DmsToDataBean> uploadList = null;
    Connection conn = null;
    log.info("查询是否ob两次FC_DMS_OB_TO_DATA，");
    try
    {
      conn = DBConnection.getConnection();
      conn.setAutoCommit(false);
      StringBuilder bud = new StringBuilder();
      bud.append("select t.vin")
        .append(" from FC_DMS_OB_TO_DATA t    ")
        .append(" where 1=1 ")
        
        .append("  GROUP BY vin  HAVING COUNT(1)>1 ");
      dmsToDataStmt = conn.prepareStatement(bud.toString());
      ResultSet res = dmsToDataStmt.executeQuery();
      if (res != null)
      {
        uploadList = new ArrayList();
        while (res.next())
        {
          DmsToDataBean upload = new DmsToDataBean();
          upload.setVin(res.getString(1));
          uploadList.add(upload);
        }
      }
      conn.commit();
      log.info("查询是否ob两次FC_DMS_OB_TO_DATA，总条数为：" + uploadList.size());
    }
    catch (Exception e)
    {
      log.error("查询是否ob两次FC_DMS_OB_TO_DATA异常", e);
      conn.rollback();
      throw e;
    }
    finally
    {
      if (dmsToDataStmt != null) {
        dmsToDataStmt.close();
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
    return uploadList;
  }
  
  public List<DmsToDataBean> getErrorDataHistoryBySecond()
    throws Exception
  {
    PreparedStatement dmsToDataStmt = null;
    Connection conn = null;
    List<DmsToDataBean> uploadList = null;
    DateFormat formaty = new SimpleDateFormat("yyyyMMdd");
    Calendar calendar = new GregorianCalendar();
    Date date = new Date();
    calendar.setTime(date);
    calendar.add(5, -7);
    date = calendar.getTime();
    String createDate = formaty.format(date);
    log.info("查询7天内的空错号反馈数据Fc_Dms_To_data，查询日期为：" + createDate);
    try
    {
      conn = DBConnection.getConnection();
      conn.setAutoCommit(false);
      StringBuilder bud = new StringBuilder();
      bud.append("select r.vin,r.no_ob_str ")
        .append("from  ( select c.*,row_number() OVER (PARTITION BY vin ORDER BY c.CREATE_DATE desc) AS NUM   ")
        .append(" from Fc_Dms_To_data c   where to_date(c.create_owner_date,'yyyyMMDD')>=to_date('" + createDate + "','yyyyMMDD')   ")
        .append(" and c.data_type='second'  and   c.no_ob_str not like '%K_02%' ")
        .append(" )r WHERE r.NUM=1 ");
      dmsToDataStmt = conn.prepareStatement(bud.toString());
      ResultSet res = dmsToDataStmt.executeQuery();
      if (res != null)
      {
        uploadList = new ArrayList();
        while (res.next())
        {
          DmsToDataBean upload = new DmsToDataBean();
          upload.setVin(res.getString(1));
          uploadList.add(upload);
        }
      }
      conn.commit();
      log.info("查询7天内的空错号反馈数据Fc_Dms_To_data，总条数为：" + uploadList.size());
    }
    catch (Exception e)
    {
      log.error("查询7天内的空错号反馈数据Fc_Dms_To_data异常", e);
      conn.rollback();
      throw e;
    }
    finally
    {
      if (dmsToDataStmt != null) {
        dmsToDataStmt.close();
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
    return uploadList;
  }
  
  public void saveToCcDmsData(FcServiceLogBean fcBean)
    throws Exception
  {
    PreparedStatement dmsToDataStmt = null;
    Connection conn = null;
    try
    {
      log.info("[保存售后全局日志]开始");
      conn = DBConnection.getConnection();
      conn.setAutoCommit(false);
      String sql = "INSERT INTO FC_SERVICE_LOG VALUES(?,?,?,?,?,?,?,?,?)";
      dmsToDataStmt = conn.prepareStatement(sql);
      dmsToDataStmt.setString(1, fcBean.getStartDate());
      dmsToDataStmt.setLong(2, fcBean.getIsDmsToData().longValue());
      dmsToDataStmt.setLong(3, fcBean.getIsCheckedData().longValue());
      dmsToDataStmt.setLong(4, fcBean.getIsProduceError().longValue());
      dmsToDataStmt.setLong(5, fcBean.getIsSubCall().longValue());
      dmsToDataStmt.setLong(6, fcBean.getIsCCGet().longValue());
      dmsToDataStmt.setLong(7, fcBean.getIsZeitCC().longValue());
      dmsToDataStmt.setLong(8, fcBean.getIsCCError().longValue());
      dmsToDataStmt.setLong(9, fcBean.getIsSubDms().longValue());
      dmsToDataStmt.addBatch();
      dmsToDataStmt.executeBatch();
      conn.commit();
    }
    catch (Exception e)
    {
      log.error("保存售后全局日志出现异常", e);
      conn.rollback();
      if (dmsToDataStmt != null) {
        dmsToDataStmt.close();
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
      if (dmsToDataStmt != null) {
        dmsToDataStmt.close();
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
  
  public boolean searchIsCCGet(String createDate)
    throws Exception
  {
    PreparedStatement dmsToDataStmt = null;
    Connection conn = null;
    boolean fany = false;
    try
    {
      conn = DBConnection.getConnection();
      String sql = "SELECT COUNT(1) FROM FC_SUB_CC_DMS_TO_ERROR R  WHERE R.OWNER_CREATE_DATE='" + createDate + "' and  R.IS_GET_DATA='N' ";
      dmsToDataStmt = conn.prepareStatement(sql);
      ResultSet res = dmsToDataStmt.executeQuery();
      if (res != null) {
        while (res.next())
        {
          int k = res.getInt(1);
          if (k > 0)
          {
            fany = true;
            break;
          }
        }
      }
    }
    catch (Exception e)
    {
      log.error("[查询CC是否已获取 DMS反馈至Call Center的XML文件]出现异常", e);
      conn.rollback();
      throw e;
    }
    finally
    {
      if (dmsToDataStmt != null) {
        dmsToDataStmt.close();
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
    return fany;
  }
  
  public void updateDmsToCC(String createDate)
    throws Exception
  {
    PreparedStatement dmsToDataStmt = null;
    Connection conn = null;
    try
    {
      log.info("[更新 DMS反馈Call Center获取记录表]开始");
      conn = DBConnection.getConnection();
      conn.setAutoCommit(false);
      StringBuilder bud = new StringBuilder();
      bud.append(" UPDATE FC_SUB_CC_DMS_TO_ERROR D ")
        .append(" SET D.IS_GET_DATA = 'Y',D.GET_DATE=sysdate ")
        .append("  WHERE ")
        .append("  D.OWNER_CREATE_DATE=? ");
      dmsToDataStmt = conn.prepareStatement(bud.toString());
      dmsToDataStmt.setString(1, createDate);
      dmsToDataStmt.addBatch();
      dmsToDataStmt.executeBatch();
      conn.commit();
    }
    catch (Exception e)
    {
      log.error("[更新 DMS反馈Call Center获取记录表]出现异常", e);
      conn.rollback();
      if (dmsToDataStmt != null) {
        dmsToDataStmt.close();
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
      if (dmsToDataStmt != null) {
        dmsToDataStmt.close();
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
  
  public boolean searchDmstoCCisXMl(String createDate)
    throws Exception
  {
    PreparedStatement dmsToDataStmt = null;
    Connection conn = null;
    boolean fany = false;
    try
    {
      conn = DBConnection.getConnection();
      String sql = "SELECT COUNT(1) FROM FC_SUB_CC_DMS_TO_ERROR R  WHERE R.OWNER_CREATE_DATE='" + createDate + "' ";
      dmsToDataStmt = conn.prepareStatement(sql);
      ResultSet res = dmsToDataStmt.executeQuery();
      if (res != null) {
        while (res.next())
        {
          int k = res.getInt(1);
          if (k > 0)
          {
            fany = true;
            break;
          }
        }
      }
    }
    catch (Exception e)
    {
      log.error("[查询是否已生成DMS反馈至Call Center的XML文件]出现异常", e);
      conn.rollback();
      throw e;
    }
    finally
    {
      if (dmsToDataStmt != null) {
        dmsToDataStmt.close();
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
    return fany;
  }
  
  public Integer selectFcServiceCount(String readDate)
    throws Exception
  {
    PreparedStatement dmsToDataStmt = null;
    Connection conn = null;
    int tmepNumber = 0;
    try
    {
      log.info("[查询当天是否存在售后全局日志]开始");
      conn = DBConnection.getConnection();
      String sql = "SELECT COUNT(1) FROM FC_SERVICE_LOG F WHERE F.START_DATE='" + readDate + "'";
      dmsToDataStmt = conn.prepareStatement(sql);
      ResultSet res = dmsToDataStmt.executeQuery();
      if (res != null) {
        while (res.next()) {
          tmepNumber = res.getInt(1);
        }
      }
    }
    catch (Exception e)
    {
      log.error("[查询当天是否存在售后全局日志]出现异常", e);
      if (dmsToDataStmt != null) {
        dmsToDataStmt.close();
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
      if (dmsToDataStmt != null) {
        dmsToDataStmt.close();
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
    return Integer.valueOf(tmepNumber);
  }
  
  public Integer selectM2ServiceCount(String readDate)
    throws Exception
  {
    PreparedStatement dmsToDataStmt = null;
    Connection conn = null;
    int tmepNumber = 0;
    try
    {
      log.info("[查询当天是否存在售前全局日志]开始");
      conn = DBConnection.getConnection();
      String sql = "SELECT COUNT(1) FROM FC_M2F_SERVICE_LOG F WHERE F.CREATE_DATE='" + readDate + "'";
      dmsToDataStmt = conn.prepareStatement(sql);
      ResultSet res = dmsToDataStmt.executeQuery();
      if (res != null) {
        while (res.next()) {
          tmepNumber = res.getInt(1);
        }
      }
    }
    catch (Exception e)
    {
      log.error("[查询当天是否存在售前全局日志]出现异常", e);
      if (dmsToDataStmt != null) {
        dmsToDataStmt.close();
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
      if (dmsToDataStmt != null) {
        dmsToDataStmt.close();
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
    return Integer.valueOf(tmepNumber);
  }
  
  public void saveFcServiceLog(FcServiceLogBean fcBean)
    throws Exception
  {
    PreparedStatement dmsToDataStmt = null;
    Connection conn = null;
    try
    {
      log.info("[保存售后全局日志]开始");
      conn = DBConnection.getConnection();
      conn.setAutoCommit(false);
      String sql = "INSERT INTO FC_SERVICE_LOG VALUES(?,?,?,?,?,?,?,?,?)";
      dmsToDataStmt = conn.prepareStatement(sql);
      dmsToDataStmt.setString(1, fcBean.getStartDate());
      dmsToDataStmt.setLong(2, fcBean.getIsDmsToData().longValue());
      dmsToDataStmt.setLong(3, fcBean.getIsCheckedData().longValue());
      dmsToDataStmt.setLong(4, fcBean.getIsProduceError().longValue());
      dmsToDataStmt.setLong(5, fcBean.getIsSubCall().longValue());
      dmsToDataStmt.setLong(6, fcBean.getIsCCGet().longValue());
      dmsToDataStmt.setLong(7, fcBean.getIsZeitCC().longValue());
      dmsToDataStmt.setLong(8, fcBean.getIsCCError().longValue());
      dmsToDataStmt.setLong(9, fcBean.getIsSubDms().longValue());
      dmsToDataStmt.addBatch();
      dmsToDataStmt.executeBatch();
      conn.commit();
    }
    catch (Exception e)
    {
      log.error("保存售后全局日志出现异常", e);
      conn.rollback();
      if (dmsToDataStmt != null) {
        dmsToDataStmt.close();
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
      if (dmsToDataStmt != null) {
        dmsToDataStmt.close();
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
  
  public void saveM2ServiceLog(M2FServiceLogBean m2Bean)
    throws Exception
  {
    PreparedStatement dmsToDataStmt = null;
    Connection conn = null;
    try
    {
      log.info("[保存售前全局日志]开始");
      conn = DBConnection.getConnection();
      conn.setAutoCommit(false);
      String sql = "INSERT INTO FC_M2F_SERVICE_LOG VALUES(?,?,?,?,?)";
      dmsToDataStmt = conn.prepareStatement(sql);
      dmsToDataStmt.setString(1, m2Bean.getCREATE_DATE());
      dmsToDataStmt.setLong(2, m2Bean.getIS_SUB_CALL_CENTER());
      dmsToDataStmt.setLong(3, m2Bean.getIS_GET_CALL_CENTER());
      dmsToDataStmt.setLong(4, m2Bean.getIS_SUB_DMS());
      dmsToDataStmt.setLong(5, m2Bean.getIS_GET_DMS());
      dmsToDataStmt.addBatch();
      dmsToDataStmt.executeBatch();
      conn.commit();
    }
    catch (Exception e)
    {
      log.error("保存售前全局日志出现异常", e);
      conn.rollback();
      if (dmsToDataStmt != null) {
        dmsToDataStmt.close();
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
      if (dmsToDataStmt != null) {
        dmsToDataStmt.close();
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
  
  public List<UploadDmsDataErrorBean> getErrorData(String createDate)
    throws Exception
  {
    PreparedStatement dmsToDataStmt = null;
    Connection conn = null;
    List<UploadDmsDataErrorBean> uploadList = null;
    try
    {
      conn = DBConnection.getConnection();
      conn.setAutoCommit(false);
      StringBuilder bud = new StringBuilder();
      bud.append("select r.vin, ")
        .append("r.dealer_sale_code, ")
        .append("nvl(r.ODP1_CODE, '2'), ")
        .append("nvl(r.ODP2_CODE, '2'), ")
        .append("r.owner_name,r.gender,")
        .append("r.address,r.email,r.mobile,r.phone,to_char(sysdate,'yyyy-MM-dd HH24:mm:ss') REBACK_DATE,r.contactor_name,r.contactor_gender, ")
        .append("r.contactor_phone,r.contactor_mobile,r.contactor_province,r.contactor_city, ")
        .append("r.contactor_address,r.contactor_email, ")
        .append("(select distinct y.code_name ")
        .append(" from Fc_Dms_Dictionary y ")
        .append("  where y.type = 1239 ")
        .append(" and y.code = r.CERTIFICATE_TYPE ")
        .append("  ) CERTIFICATE_TYPE, ")
        
        .append("r.certificate_code ")
        .append(" FROM ")
        .append(" ( select c.*,row_number() OVER (PARTITION BY vin ORDER BY c.CREATE_DATE desc) AS NUM ")
        .append(" from Fc_Dms_To_Error c where c.create_date = '" + createDate + "' ")
        .append("   )r WHERE r.NUM=1 and r.OWNER_ID is not null ");
      dmsToDataStmt = conn.prepareStatement(bud.toString());
      DateFormat formaty = new SimpleDateFormat("yyyyMMdd");
      String batchDate = formaty.format(new Date());
      log.info("开始查询FC返回的空错号信息，查询日期为：" + createDate);
      ResultSet res = dmsToDataStmt.executeQuery();
      if (res != null)
      {
        uploadList = new ArrayList();
        while (res.next())
        {
          UploadDmsDataErrorBean upload = new UploadDmsDataErrorBean();
          upload.setVin(res.getString(1));
          upload.setDealer_sale_code(res.getString(2));
          upload.setOwner_status(res.getString(3));
          upload.setContact_status(res.getString(4));
          upload.setOwner_name(res.getString(5));
          upload.setGender(res.getString(6));
          upload.setAddress(res.getString(7));
          upload.setEmail(res.getString(8));
          upload.setMobile(res.getString(9));
          upload.setPhone(res.getString(10));
          upload.setReback_date(res.getString(11));
          upload.setContactor_name(res.getString(12));
          upload.setContactor_gender(res.getString(13));
          upload.setContactor_phone(res.getString(14));
          upload.setContactor_mobile(res.getString(15));
          upload.setContactor_province(res.getString(16));
          upload.setContactor_city(res.getString(17));
          upload.setContactor_address(res.getString(18));
          upload.setContactor_email(res.getString(19));
          upload.setCERTIFICATE_TYPE(res.getString(20));
          upload.setCERTIFICATE_CODE(res.getString(21));
          



          upload.setOdp_type("1");
          upload.setBATCH_COMMENT(batchDate);
          uploadList.add(upload);
        }
      }
      conn.commit();
      log.info("查询FC空错号数据成功，总条数为：" + uploadList.size());
    }
    catch (Exception e)
    {
      log.error("根据读取日期查询当期读取数目异常", e);
      conn.rollback();
      throw e;
    }
    finally
    {
      if (dmsToDataStmt != null) {
        dmsToDataStmt.close();
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
    return uploadList;
  }
  
  public String settingOdpCode(String str)
  {
    if ((str == null) || ("".equals(str))) {
      return "";
    }
    if ((str.contains("空号")) || (str.contains("错号"))) {
      return "2";
    }
    if (str.contains("关机")) {
      return "3";
    }
    if (str.contains("秘书台")) {
      return "4";
    }
    if (str.contains("不在服务区")) {
      return "5";
    }
    if (str.contains("无人接听")) {
      return "6";
    }
    if (str.contains("忙音")) {
      return "7";
    }
    if (str.contains("未找到关键人")) {
      return "8";
    }
    if (str.contains("稍后联系")) {
      return "9";
    }
    if (str.contains("拒绝")) {
      return "10";
    }
    if (str.contains("联系上")) {
      return "G";
    }
    if (str.contains("空错号未反馈")) {
      return "12";
    }
    return str;
  }
  
  public List<UploadDmsDataErrorBean> getCallErrorData()
    throws Exception
  {
    List<UploadDmsDataErrorBean> uploadList = null;
    PreparedStatement dmsToDataStmt = null;
    Connection conn = null;
    try
    {
      conn = DBConnection.getConnection();
      conn.setAutoCommit(false);
      StringBuffer buf = new StringBuffer();
      

      buf.append("SELECT T.VIN,")
        .append("T.DEALER_CODE AS DEALER_SALE_CODE, ")
        .append(" CASE WHEN T.OWNER_OBSTATUS LIKE '%联系上%' THEN '联系上' ELSE T.OWNER_OBSTATUS END AS Owner_Status, ")
        .append("CASE WHEN T.CONTACT_OBSTATUS LIKE '%联系上%' THEN '联系上' ELSE T.CONTACT_OBSTATUS END AS Contact_Status, ")
        .append("nvl(T.Uowner_Name, T.OWNER_NAME) AS OWNER_NAME, ")
        


        .append(" DOwner_sex AS GENDER,  ")
        .append("nvl(T.Uowner_Address, T.OWNER_ADDRESS) AS ADDRESS, ")
        .append("nvl(T.Uowner_Email, T.OWNEREMAIL) AS EMAIL, ")
        .append("nvl(T.Uowner_Mobile, T.OWNER_MOBILE) AS MOBILE, ")
        .append("nvl(T.Uowner_Landline, T.OWNER_LANDLINE) AS PHONE, ")
        .append("to_char(sysdate, 'yyyy-MM-dd HH24:mm:ss') REBACK_DATE, ")
        .append("nvl(T.Ucontact_Name, T.CONTACT_NAME) AS CONTACTOR_NAME, ")
        
        .append(" DContact_sex AS CONTACTOR_GENDER , ")
        .append("nvl(T.Ucontact_Landline, T.CONTACT_LANDLINE) AS CONTACTOR_PHONE, ")
        .append("nvl(T.Ucontact_Mobile, T.CONTACT_MOBILE) AS CONTACTOR_MOBILE, ")
        
        .append(" DProvince_name as provinceCode, ")
        
        .append(" DCity_name as cityCode,")
        .append("nvl(T.Ucontact_Address, T.CONTACT_ADDRESS) AS CONTACTOR_ADDRESS, ")
        .append("nvl(T.Ucontact_Email, T.CONTACT_EMAIL) AS CONTACTOR_EMAIL, ")
        .append("T.CERTIFICATE_TYPE AS CERTIFICATE_TYPE, ")
        .append("T.CERTIFICATE_CODE AS CERTIFICATE_CODE, ")
        .append("T.ODP1 AS ODP1, ")
        .append("T.ODP2 AS ODP2, ")
        .append("T.ODP1 AS ODP1_CODE, ")
        .append("T.ODP2 AS ODP2_CODE ")
        .append(" from (SELECT * ")
        .append("from (SELECT a.*")
        

        .append(" ,CASE WHEN a.OWNER_SEX is null then a.Uowner_Sex else a.OWNER_SEX end as DOwner_sex ")
        .append(" ,CASE WHEN a.CONTACT_SEX is null then a.UContact_Sex else a.CONTACT_SEX end as DContact_sex ")
        .append(" ,row_number() OVER (PARTITION BY a.vin ORDER BY a.OBDATE DESC) AS NUM  ")
        .append("FROM FC_DMS_OB_TO_DATA a ) T ")
        
        .append(" WHERE T.num=1 AND  T.RETURN_DATE=? ")
        .append(" )T ")
        .append(" LEFT JOIN (SELECT a.o_id,CASE WHEN b.code IS NULL THEN NULL ELSE DProvince_name_1 END AS DProvince_name ")
        .append(" FROM (SELECT a.o_id,CASE WHEN a.OWNER_PROVINCE is null then a.Uowner_Province else a.owner_Province end as DProvince_name_1 FROM FC_DMS_OB_TO_DATA a   ")
        .append(" where a.RETURN_DATE=? )a")
        .append(" LEFT JOIN fc_dms_dictionary b ON a.DProvince_name_1=b.code ")
        .append(" )b ON t.o_id=b.o_id ")
        .append(" LEFT JOIN (SELECT a.o_id,CASE WHEN b.code IS NULL THEN NULL ELSE DCity_name_1 END AS DCity_name ")
        .append(" FROM (SELECT a.o_id,CASE WHEN a.OWNER_CITY is null then a.Uowner_City else a.OWNER_CITY end as DCity_name_1 FROM FC_DMS_OB_TO_DATA a ")
        .append(" where a.RETURN_DATE=? )a")
        .append(" LEFT JOIN fc_dms_dictionary b ON a.DCity_name_1=b.code ")
        .append(" )c ON t.o_id=c.o_id ");
      dmsToDataStmt = conn.prepareStatement(buf.toString());
      DateFormat formaty = new SimpleDateFormat("yyyyMMdd");
      Calendar calendar = new GregorianCalendar();
      Date date = new Date();
      calendar.setTime(date);
      calendar.add(5, -1);
      date = calendar.getTime();
      String returnDate = formaty.format(date);
      dmsToDataStmt.setString(1, returnDate);
      dmsToDataStmt.setString(2, returnDate);
      dmsToDataStmt.setString(3, returnDate);
      log.info("开始查询CallCenter返回的空错号，查询日期为：" + returnDate);
      ResultSet res = dmsToDataStmt.executeQuery();
      
      String batchDate = formaty.format(new Date());
      if (res != null)
      {
        uploadList = new ArrayList();
        while (res.next())
        {
          UploadDmsDataErrorBean upload = new UploadDmsDataErrorBean();
          upload.setVin(res.getString(1));
          upload.setDealer_sale_code(res.getString(2));
          if (res.getString(3) != null) {
            upload.setOwner_status(settingOdpCode(res.getString(3)));
          } else {
            upload.setOwner_status(res.getString(3));
          }
          if (res.getString(4) != null) {
            upload.setContact_status(settingOdpCode(res.getString(4)));
          } else {
            upload.setContact_status(res.getString(4));
          }
          upload.setOwner_name(res.getString(5));
          upload.setGender(res.getString(6));
          upload.setAddress(res.getString(7));
          upload.setEmail(res.getString(8));
          upload.setMobile(res.getString(9));
          upload.setPhone(res.getString(10));
          upload.setReback_date(res.getString(11));
          upload.setContactor_name(res.getString(12));
          upload.setContactor_gender(res.getString(13));
          upload.setContactor_phone(res.getString(14));
          upload.setContactor_mobile(res.getString(15));
          upload.setContactor_province(res.getString(16));
          upload.setContactor_city(res.getString(17));
          upload.setContactor_address(res.getString(18));
          upload.setContactor_email(res.getString(19));
          upload.setCERTIFICATE_TYPE(res.getString(20));
          upload.setCERTIFICATE_CODE(res.getString(21));
          if ("1".equals(res.getString(24))) {
            upload.setOdp_type("1");
          } else if ("1".equals(res.getString(25))) {
            upload.setOdp_type("2");
          }
          upload.setBATCH_COMMENT(batchDate);
          uploadList.add(upload);
        }
      }
      conn.commit();
      log.info("查询CallCenter返回的空错号成功，数量为：" + uploadList.size());
    }
    catch (Exception e)
    {
      log.error("根据读取日期查询当期读取数目异常", e);
      conn.rollback();
      throw e;
    }
    finally
    {
      if (dmsToDataStmt != null) {
        dmsToDataStmt.close();
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
    return uploadList;
  }
  
  public String getDictCodeByName(String type, String code_name)
    throws Exception
  {
    PreparedStatement dmsToDataStmt = null;
    Connection conn = null;
    String code = "";
    try
    {
      conn = DBConnection.getConnection();
      conn.setAutoCommit(false);
      String sql = "SELECT distinct CODE FROM FC_DMS_DICTIONARY T WHERE T.TYPE=? AND T.CODE_NAME=?";
      DateFormat formaty = new SimpleDateFormat("yyyyMMdd");
      String createDate = formaty.format(new Date());
      dmsToDataStmt = conn.prepareStatement(sql);
      dmsToDataStmt.setString(1, type);
      dmsToDataStmt.setString(2, code_name);
      
      ResultSet res = dmsToDataStmt.executeQuery();
      if (res != null) {
        while (res.next()) {
          code_name = res.getString(1);
        }
      }
      conn.commit();
    }
    catch (Exception e)
    {
      log.error("根据CODE、日期查询字典表异常", e);
      conn.rollback();
      throw e;
    }
    finally
    {
      if (dmsToDataStmt != null) {
        dmsToDataStmt.close();
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
    return code_name;
  }
  
  public String getCountByDealCode(String dealer_code, String sub_date)
    throws Exception
  {
    PreparedStatement dmsToDataStmt = null;
    Connection conn = null;
    String count = "";
    try
    {
      conn = DBConnection.getConnection();
      conn.setAutoCommit(false);
      String sql = " SELECT count(1) FROM FC_DMS_OB_TO_DATA t where t.dealer_code=? and t.sub_date=? ";
      dmsToDataStmt = conn.prepareStatement(sql);
      dmsToDataStmt.setString(1, dealer_code);
      dmsToDataStmt.setString(2, sub_date);
      ResultSet res = dmsToDataStmt.executeQuery();
      if (res != null) {
        while (res.next()) {
          count = res.getString(1);
        }
      }
      conn.commit();
    }
    catch (Exception e)
    {
      log.error("根据经销商代码和提交时间查询CallCenter返回的空错号数据异常", e);
      conn.rollback();
      throw e;
    }
    finally
    {
      if (dmsToDataStmt != null) {
        dmsToDataStmt.close();
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
    return count;
  }
  
  public void getDateRemove(String date)
    throws Exception
  {
    log.info("[根据日期清除对应的数据]当前删除：" + date);
    Statement stam = null;
    Connection conn = null;
    try
    {
      conn = DBConnection.getConnection();
      conn.setAutoCommit(false);
      stam = conn.createStatement();
      String[] Sqls = {
        "delete from fc_dms_to_data_temp ", 
        "delete from FC_DMS_TO_ERROR r where r.CREATE_DATE='" + date + "'", 
        //"delete from Fc_Dms_To_Data r where r.CREATE_OWNER_DATE='" + date + "'", 
        "delete from FC_DMS_TO_DATA_NEWOWNERS r where r.RECEIVE_DATE='" + date + "'", 
        "delete from FC_DMS_ADD_ITEM r where r.IMPORT_DATE='" + date + "'", 
        "delete from FC_DMS_DICTIONARY r where r.IMPORT_DATE='" + date + "'", 
        "delete from Fc_Dms_Parts r where r.IMPORT_DATE='" + date + "'", 
        "delete from Fc_Dms_Labour_Part r where r.IMPORT_DATE='" + date + "'", 
        "delete from FC_DMS_REPAIR_ORDER r where r.IMPORT_DATE='" + date + "'", 
        "delete from Fc_Dms_Repair_Part r where r.IMPORT_DATE='" + date + "'", 
        "delete from Fc_Dms_To_Log r where r.READ_DATE='" + date + "'", 
        "delete from Fc_Dms_Sub_Call_Center r where r.IMPORTDATE='" + date + "'", 
        "delete from FC_SERVICE_LOG r where r.START_DATE='" + date + "'", 
        "delete from FC_DMS_ELEC c where c.IMPORTDATE='" + date + "'", 
        "delete from FC_DMS_SALES_PART t where t.IMPORT_DATE='" + date + "'" };
     
      String handleDelayOnwer=ExtendedPropUtils.getPropertyValue("ftpConn.properties","manual.handle.delay.onwer");
      
      if(handleDelayOnwer==null||"false".equals(handleDelayOnwer)){//如果手动处理由于DMS发送滞留数据未开启
    	  stam.execute("delete from Fc_Dms_To_Data r where r.CREATE_OWNER_DATE='" + date + "'");
      }
      
      for (int i = 0; i < Sqls.length; i++) {
        stam.execute(Sqls[i]);
      }
      conn.commit();
    }
    catch (Exception e)
    {
      log.error("根据日期清除对应的数据异常！", e);
      conn.rollback();
      throw e;
    }
    finally
    {
      if (stam != null) {
        stam.close();
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
  
  public void saveDmsToDataForTempTab()
    throws Exception
  {
    log.info("[保存去重后的车主正式数据表]开始");
    PreparedStatement dmsToDataStmt = null;
    Connection conn = null;
    try
    {
      conn = DBConnection.getConnection();
      conn.setAutoCommit(false);
      
      dmsToDataStmt = conn.prepareStatement(getSaveDmsToDataStr());
      dmsToDataStmt.addBatch();
      dmsToDataStmt.executeBatch();
      dmsToDataStmt = conn.prepareStatement("delete from fc_dms_to_data_temp");
      dmsToDataStmt.addBatch();
      dmsToDataStmt.executeBatch();
      conn.commit();
    }
    catch (Exception e)
    {
      log.error("[保存去重后的车主正式数据表]出现异常", e);
      conn.rollback();
      throw e;
    }
    finally
    {
      if (dmsToDataStmt != null) {
        dmsToDataStmt.close();
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
  
  public void deleteDmsToDataStr()
    throws Exception
  {
    log.info("[FC,ODP,CSI历史数据去重]开始");
    PreparedStatement dmsToDataStmt = null;
    Connection conn = null;
    try
    {
      conn = DBConnection.getConnection();
      conn.setAutoCommit(false);
      dmsToDataStmt = conn.prepareStatement("delete from FC_DMS_TO_DATA_TEMP t where  exists (select * from FC_DMS_OB_TO_DATA a where a.vin=t.vin) or exists (select * from FC_ODP_CSI_OB_RAWDATA where c.VIN_NAME=t.vin )");
      dmsToDataStmt.addBatch();
      dmsToDataStmt.executeBatch();
      conn.commit();
    }
    catch (Exception e)
    {
      log.error("[FC,ODP,CSI历史数据去重]出现异常", e);
      conn.rollback();
      throw e;
    }
    finally
    {
      if (dmsToDataStmt != null) {
        dmsToDataStmt.close();
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
  
  public void deleteOrUpdateDmsToData()
    throws Exception
  {
    log.info("[FC,ODP,CSI数据去重]开始");
    PreparedStatement dmsToDataStmt = null;
    Connection conn = null;
    try
    {
      conn = DBConnection.getConnection();
      conn.setAutoCommit(false);
      dmsToDataStmt = conn.prepareCall("{call PROC_FC_DMS_TO_DATA_TEMP_QC()}");
      dmsToDataStmt.addBatch();
      dmsToDataStmt.executeBatch();
      conn.commit();
    }
    catch (Exception e)
    {
      log.error("[FC,ODP,CSI数据去重]出现异常", e);
      conn.rollback();
      throw e;
    }
    finally
    {
      if (dmsToDataStmt != null) {
        dmsToDataStmt.close();
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
  
  public void insertFcLogByParames(String readDate)
    throws Exception
  {
    log.info("[插入DMS数据日志 insertFcLogByParames ]开始");
    PreparedStatement dmsToDataStmt = null;
    Connection conn = null;
    try
    {
      conn = DBConnection.getConnection();
      conn.setAutoCommit(false);
      dmsToDataStmt = conn.prepareCall("{call Proc_fc_dms_to_log_insert(?)}");
      dmsToDataStmt.setString(1, readDate);
      log.info("[插入DMS数据日志 insertFcLogByParames]01;readDate:" + readDate);
      dmsToDataStmt.addBatch();
      log.info("[插入DMS数据日志 insertFcLogByParames]02");
      dmsToDataStmt.executeBatch();
      log.info("[插入DMS数据日志 insertFcLogByParames]03");
      conn.commit();
      log.info("[插入DMS数据日志 insertFcLogByParames]04");
    }
    catch (Exception e)
    {
      log.error("[插入DMS数据日志 insertFcLogByParames]出现异常", e);
      conn.rollback();
      throw e;
    }
    finally
    {
      log.info("[插入DMS数据日志]结束");
      if (dmsToDataStmt != null) {
        dmsToDataStmt.close();
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
  
  public void insertFcLogByParames_old(String readDate)
    throws Exception
  {
    log.info("[插入DMS数据日志]开始");
    PreparedStatement dmsToDataStmt = null;
    Connection conn = null;
    try
    {
      conn = DBConnection.getConnection();
      conn.setAutoCommit(false);
      
      dmsToDataStmt = conn.prepareStatement("insert into fc_dms_to_log  (read_date,read_type,read_count,f_read_type) select ?,?,count(1),? from FC_DMS_TO_DATA p where p.create_owner_date=? ");
      dmsToDataStmt.setString(1, readDate);
      dmsToDataStmt.setString(2, "DO01");
      dmsToDataStmt.setString(3, null);
      dmsToDataStmt.setString(4, readDate);
      dmsToDataStmt.addBatch();
      dmsToDataStmt.executeBatch();
      
      dmsToDataStmt = conn.prepareStatement("insert into fc_dms_to_log  (read_date,read_type,read_count,f_read_type) select ?,?,count(1),? from FC_DMS_REPAIR_ORDER p where p.import_date=? ");
      dmsToDataStmt.setString(1, readDate);
      dmsToDataStmt.setString(2, "DO02");
      dmsToDataStmt.setString(3, null);
      dmsToDataStmt.setString(4, readDate);
      dmsToDataStmt.addBatch();
      dmsToDataStmt.executeBatch();
      dmsToDataStmt = conn.prepareStatement("insert into fc_dms_to_log  (read_date,read_type,read_count,f_read_type) select ?,?,count(1),? from FC_DMS_ADD_ITEM p where p.import_date=? ");
      dmsToDataStmt.setString(1, readDate);
      dmsToDataStmt.setString(2, "DO0201");
      dmsToDataStmt.setString(3, null);
      dmsToDataStmt.setString(4, readDate);
      dmsToDataStmt.addBatch();
      dmsToDataStmt.executeBatch();
      dmsToDataStmt = conn.prepareStatement("insert into fc_dms_to_log  (read_date,read_type,read_count,f_read_type) select ?,?,count(1),? from FC_DMS_LABOUR_PART p where p.import_date=? ");
      dmsToDataStmt.setString(1, readDate);
      dmsToDataStmt.setString(2, "DO0202");
      dmsToDataStmt.setString(3, null);
      dmsToDataStmt.setString(4, readDate);
      dmsToDataStmt.addBatch();
      dmsToDataStmt.executeBatch();
      dmsToDataStmt = conn.prepareStatement("insert into fc_dms_to_log  (read_date,read_type,read_count,f_read_type) select ?,?,count(1),? from FC_DMS_REPAIR_PART p where p.import_date=? ");
      dmsToDataStmt.setString(1, readDate);
      dmsToDataStmt.setString(2, "DO0203");
      dmsToDataStmt.setString(3, null);
      dmsToDataStmt.setString(4, readDate);
      dmsToDataStmt.addBatch();
      dmsToDataStmt.executeBatch();
      dmsToDataStmt = conn.prepareStatement("insert into fc_dms_to_log  (read_date,read_type,read_count,f_read_type) select ?,?,count(1),? from FC_DMS_SALES_PART p where p.import_date=? ");
      dmsToDataStmt.setString(1, readDate);
      dmsToDataStmt.setString(2, "DO0204");
      dmsToDataStmt.setString(3, null);
      dmsToDataStmt.setString(4, readDate);
      dmsToDataStmt.addBatch();
      dmsToDataStmt.executeBatch();
      dmsToDataStmt = conn.prepareStatement("insert into fc_dms_to_log  (read_date,read_type,read_count,f_read_type) select ?,?,count(1),? from Fc_Dms_Dictionary p where p.import_date=? ");
      dmsToDataStmt.setString(1, readDate);
      dmsToDataStmt.setString(2, "DO03");
      dmsToDataStmt.setString(3, null);
      dmsToDataStmt.setString(4, readDate);
      dmsToDataStmt.addBatch();
      dmsToDataStmt.executeBatch();
      dmsToDataStmt = conn.prepareStatement("insert into fc_dms_to_log  (read_date,read_type,read_count,f_read_type) select ?,?,count(1),? from FC_DMS_ELEC p where p.IMPORTDATE=? ");
      dmsToDataStmt.setString(1, readDate);
      dmsToDataStmt.setString(2, "DO06");
      dmsToDataStmt.setString(3, null);
      dmsToDataStmt.setString(4, readDate);
      dmsToDataStmt.addBatch();
      dmsToDataStmt.executeBatch();
      conn.commit();
    }
    catch (Exception e)
    {
      log.error("[插入DMS数据日志]出现异常", e);
      conn.rollback();
      throw e;
    }
    finally
    {
      if (dmsToDataStmt != null) {
        dmsToDataStmt.close();
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
  
  public void updateFcLogCount(String imoprtDate)
    throws Exception
  {
    log.info("[更新DMS数据日志]开始");
    PreparedStatement dmsToDataStmt = null;
    Connection conn = null;
    try
    {
      conn = DBConnection.getConnection();
      conn.setAutoCommit(false);
      
      dmsToDataStmt = conn.prepareStatement("update FC_DMS_TO_LOG g set g.read_count=(select count(1) from FC_DMS_TO_DATA p where p.create_owner_date=g.read_date)  where g.read_type='DO01' and g.read_date='" + imoprtDate + "'");
      dmsToDataStmt.addBatch();
      dmsToDataStmt.executeBatch();
      
      dmsToDataStmt = conn.prepareStatement("update FC_DMS_TO_LOG g set g.read_count=(select count(1) from FC_DMS_REPAIR_ORDER p where p.import_date=g.read_date)  where g.read_type='DO02' and g.read_date='" + imoprtDate + "'");
      dmsToDataStmt.addBatch();
      dmsToDataStmt.executeBatch();
      
      dmsToDataStmt = conn.prepareStatement("update FC_DMS_TO_LOG g set g.read_count=(select count(1) from FC_DMS_ADD_ITEM p where p.import_date=g.read_date)  where g.read_type='DO0201' and g.read_date='" + imoprtDate + "'");
      dmsToDataStmt.addBatch();
      dmsToDataStmt.executeBatch();
      dmsToDataStmt = conn.prepareStatement("update FC_DMS_TO_LOG g set g.read_count=(select count(1) from FC_DMS_LABOUR_PART p where p.import_date=g.read_date)  where g.read_type='DO0202' and g.read_date='" + imoprtDate + "'");
      dmsToDataStmt.addBatch();
      dmsToDataStmt.executeBatch();
      dmsToDataStmt = conn.prepareStatement("update FC_DMS_TO_LOG g set g.read_count=(select count(1) from FC_DMS_REPAIR_PART p where p.import_date=g.read_date)  where g.read_type='DO0203' and g.read_date='" + imoprtDate + "'");
      dmsToDataStmt.addBatch();
      dmsToDataStmt.executeBatch();
      dmsToDataStmt = conn.prepareStatement("update FC_DMS_TO_LOG g set g.read_count=(select count(1) from FC_DMS_SALES_PART p where p.import_date=g.read_date)  where g.read_type='DO0204' and g.read_date='" + imoprtDate + "'");
      dmsToDataStmt.addBatch();
      dmsToDataStmt.executeBatch();
      dmsToDataStmt = conn.prepareStatement("update FC_DMS_TO_LOG g set g.read_count=(select count(1) from Fc_Dms_Dictionary p where p.import_date=g.read_date)  where g.read_type='DO03' and g.read_date='" + imoprtDate + "'");
      dmsToDataStmt.addBatch();
      dmsToDataStmt.executeBatch();
      dmsToDataStmt = conn.prepareStatement("update FC_DMS_TO_LOG g set g.read_count=(select count(1) from FC_DMS_ELEC p where p.IMPORTDATE=g.read_date)  where g.read_type='DO06' and g.read_date='" + imoprtDate + "'");
      dmsToDataStmt.addBatch();
      dmsToDataStmt.executeBatch();
      conn.commit();
    }
    catch (Exception e)
    {
      log.error("[更新DMS数据日志]出现异常", e);
      conn.rollback();
      throw e;
    }
    finally
    {
      if (dmsToDataStmt != null) {
        dmsToDataStmt.close();
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
  
  public void saveOrderFormalTab(String imoprtDate)
    throws Exception
  {
    log.info("[保存工单数据到正式数据表]开始");
    PreparedStatement dmsToDataStmt = null;
    Connection conn = null;
    try
    {
      conn = DBConnection.getConnection();
      conn.setAutoCommit(false);
      
      dmsToDataStmt = conn.prepareStatement(orderFormalStr());
      dmsToDataStmt.addBatch();
      dmsToDataStmt.executeBatch();
      dmsToDataStmt = conn.prepareStatement(addItemStr(imoprtDate));
      dmsToDataStmt.addBatch();
      dmsToDataStmt.executeBatch();
      dmsToDataStmt = conn.prepareStatement(labourPartStr(imoprtDate));
      dmsToDataStmt.addBatch();
      dmsToDataStmt.executeBatch();
      dmsToDataStmt = conn.prepareStatement(repairPartStr(imoprtDate));
      dmsToDataStmt.addBatch();
      dmsToDataStmt.executeBatch();
      dmsToDataStmt = conn.prepareStatement(salesPartStr(imoprtDate));
      dmsToDataStmt.addBatch();
      dmsToDataStmt.executeBatch();
      dmsToDataStmt = conn.prepareStatement("delete from FC_DMS_REPAIR_ORDER_TEMP");
      dmsToDataStmt.addBatch();
      dmsToDataStmt.executeBatch();
      dmsToDataStmt = conn.prepareStatement("delete from FC_DMS_ADD_ITEM_TEMP");
      dmsToDataStmt.addBatch();
      dmsToDataStmt.executeBatch();
      dmsToDataStmt = conn.prepareStatement("delete from FC_DMS_LABOUR_PART_TEMP");
      dmsToDataStmt.addBatch();
      dmsToDataStmt.executeBatch();
      dmsToDataStmt = conn.prepareStatement("delete from FC_DMS_REPAIR_PART_TEMP");
      dmsToDataStmt.addBatch();
      dmsToDataStmt.executeBatch();
      dmsToDataStmt = conn.prepareStatement("delete from FC_DMS_SALES_PART_TEMP");
      dmsToDataStmt.addBatch();
      dmsToDataStmt.executeBatch();
      conn.commit();
    }
    catch (Exception e)
    {
      log.error("[保存工单数据到正式数据表]出现异常", e);
      conn.rollback();
      throw e;
    }
    finally
    {
      if (dmsToDataStmt != null) {
        dmsToDataStmt.close();
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
  
  private String orderFormalStr()
  {
    StringBuilder bud = new StringBuilder();
    bud.append("insert into FC_DMS_REPAIR_ORDER ")
      .append("( ")
      .append(" select  ro_no,ro_id,dealer_service_code,dealer_name,brand,serise,model,color,vin,engine_no,lisence_no,ro_create_date,delivery_date,for_balance_time,ro_status,")
      .append("ro_type,labour_amount,repair_part_amount,sales_part_amount,add_item_amount,over_item_amount,repair_amount,balance_amount,sales_date,in_mileage,remark,create_date,")
      .append("update_date,import_date,time_stamp,sequnce,owner_id,is_valid,service_advisor_code,service_advisor_name,big_org_name,out_mileage,order_id, ")
      .append("deliverer,deliverer_gender,deliverer_mobile,deliverer_phone,iscsi ")
      .append(" from (select order_id,ro_no,ro_id,dealer_service_code,dealer_name,brand,serise,model,color,vin,engine_no,lisence_no,ro_create_date,delivery_date,for_balance_time,")
      .append("ro_status,ro_type,labour_amount,repair_part_amount,sales_part_amount,add_item_amount,over_item_amount,repair_amount,balance_amount,sales_date,in_mileage,")
      .append("remark,create_date,update_date,import_date,time_stamp,sequnce,owner_id,is_valid,service_advisor_code,service_advisor_name,big_org_name,out_mileage,")
      .append("deliverer,deliverer_gender,deliverer_mobile,deliverer_phone,iscsi,")
      .append("rank() over(partition by c.ro_id, c.dealer_service_code order by c.order_id) cnt ")
      .append(" from FC_DMS_REPAIR_ORDER_temp c) c ")
      .append(" where c.cnt = 1) ");
    return bud.toString();
  }
  
  private String addItemStr(String importDate)
  {
    StringBuilder bud = new StringBuilder();
    bud.append("insert into Fc_Dms_Add_Item ( ")
      .append("select * from Fc_Dms_Add_Item_temp p where p.order_id in ")
      .append("(select o.order_id  from FC_DMS_REPAIR_ORDER o where o.import_date='" + importDate + "')) ");
    return bud.toString();
  }
  
  private String labourPartStr(String importDate)
  {
    StringBuilder bud = new StringBuilder();
    bud.append("insert into Fc_Dms_Labour_Part ( ")
      .append("select * from Fc_Dms_Labour_Part_temp p where p.order_id in ")
      .append("(select o.order_id  from FC_DMS_REPAIR_ORDER o where o.import_date='" + importDate + "')) ");
    return bud.toString();
  }
  
  private String repairPartStr(String importDate)
  {
    StringBuilder bud = new StringBuilder();
    bud.append("insert into Fc_Dms_Repair_Part ( ")
      .append("select * from Fc_Dms_Repair_Part_temp p where p.order_id in ")
      .append("(select o.order_id  from FC_DMS_REPAIR_ORDER o where o.import_date='" + importDate + "')) ");
    return bud.toString();
  }
  
  private String salesPartStr(String importDate)
  {
    StringBuilder bud = new StringBuilder();
    bud.append("insert into Fc_Dms_Sales_Part ( ")
      .append("select * from Fc_Dms_Sales_Part_temp p where p.order_id in ")
      .append("(select o.order_id  from FC_DMS_REPAIR_ORDER o where o.import_date='" + importDate + "')) ");
    return bud.toString();
  }
  
  private String getSaveDmsToDataStr()
  {
    StringBuilder bud = new StringBuilder();
    bud.append("insert into Fc_Dms_To_Data  (select owner_id,brand,serise,model,color, ")
      .append("vin,engine_no,radio_no,key_no,lisence_no,sales_date,dealer_sale_code, ")
      .append("short_name,dealer_id,dealer_name,seller,guarantee_start_date,guarantee_end_date, ")
      .append("is_delay_service,is_wholesale,use_type,pay_type,inmileage,last_maintain_date, ")
      .append("invoice_no,owner_name,contactor_name,owner_spell,owner_property,gender,birthday, ")
      .append("certificate_type,certificate_code,industry_first,industry_second,family_income, ")
      .append("edu_level,owner_marriage,choose_reasion,buy_purpose,wish_connect_type,province, ")
      .append("city,district,zip_code,address,e_mail,phone,fax,mobile,contactor_mobile, ")
      .append("contactor_phone,contactor_email,remark,create_date,update_date,import_date, ")
      .append("time_stamp,sequnce,is_valid,buy_type,car_dsid,car_source,dealer_service_code, ")
      .append("confgation,cat_code,contactor_address,contactor_gender,sale_date,is_new_owner, ")
      .append("ob_count,is_owner_error,create_owner_date,no_ob_str,ob_type,car_sync,car_ea, ")
      

      .append("catecode,no_ob_reason,data_id,dup_type,'',IS_ODP_UPDATES,SALE_DEALER_CODE,SALE_SERVICE_CODE,SALE_DEALER_NAME from (select d.*,rank() over(partition by d.vin order by d.CREATE_DATE desc, d.data_id) cnt ")
      .append(" from FC_DMS_TO_DATA_TEMP d) p where p.cnt = 1 ) ");
    return bud.toString();
  }
  
  public String getFedbackStartDay()
    throws Exception
  {
    PreparedStatement dmsToDataStmt = null;
    Connection conn = null;
    String startDay = "";
    try
    {
      log.info("[获取前2月始日期]开始");
      conn = DBConnection.getConnection();
      conn.setAutoCommit(false);
      String sql = "SELECT T.START_DAY FROM FC_SET_FEEDBACK_END_DATE T  ";
      dmsToDataStmt = conn.prepareStatement(sql);
      ResultSet res = dmsToDataStmt.executeQuery();
      if ((res != null) && 
        (res.next())) {
        startDay = res.getString(1);
      }
      conn.commit();
      DateFormat format = new SimpleDateFormat("yyyyMMdd");
      Calendar c15 = Calendar.getInstance();
      c15.add(2, -2);
      Date d = c15.getTime();
      String lastDate = format.format(d);
      startDay = lastDate.substring(0, 6) + startDay;
    }
    catch (Exception e)
    {
      log.error("[获取前2月始日期]异常", e);
      conn.rollback();
      throw e;
    }
    finally
    {
      if (dmsToDataStmt != null) {
        dmsToDataStmt.close();
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
    return startDay;
  }
  
  public String getFedbackEndDay()
    throws Exception
  {
    PreparedStatement dmsToDataStmt = null;
    Connection conn = null;
    String endDay = "";
    try
    {
      log.info("[获取前1月始日期]开始");
      conn = DBConnection.getConnection();
      conn.setAutoCommit(false);
      String sql = "SELECT T.END_DAY FROM FC_SET_FEEDBACK_END_DATE T  ";
      dmsToDataStmt = conn.prepareStatement(sql);
      ResultSet res = dmsToDataStmt.executeQuery();
      if ((res != null) && 
        (res.next())) {
        endDay = res.getString(1);
      }
      conn.commit();
      DateFormat format = new SimpleDateFormat("yyyyMMdd");
      Calendar c15 = Calendar.getInstance();
      c15.add(2, -1);
      Date d = c15.getTime();
      String lastDate = format.format(d);
      endDay = lastDate.substring(0, 6) + endDay;
    }
    catch (Exception e)
    {
      log.error("[获取前1月始日期]异常", e);
      conn.rollback();
      throw e;
    }
    finally
    {
      if (dmsToDataStmt != null) {
        dmsToDataStmt.close();
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
    return endDay;
  }
  
  public String getFedbackCurrentDay()
    throws Exception
  {
    PreparedStatement dmsToDataStmt = null;
    Connection conn = null;
    String endDay = "";
    try
    {
      log.info("[获取当前月截止日期]开始");
      conn = DBConnection.getConnection();
      conn.setAutoCommit(false);
      String sql = "SELECT T.SEND_DAY FROM FC_SET_FEEDBACK_END_DATE T  ";
      dmsToDataStmt = conn.prepareStatement(sql);
      ResultSet res = dmsToDataStmt.executeQuery();
      if ((res != null) && 
        (res.next())) {
        endDay = res.getString(1);
      }
      conn.commit();
      DateFormat format = new SimpleDateFormat("yyyyMMdd");
      String lastDate = format.format(new Date());
      endDay = lastDate.substring(0, 6) + endDay;
    }
    catch (Exception e)
    {
      log.error("[获取当前月截止日期]异常", e);
      conn.rollback();
      throw e;
    }
    finally
    {
      if (dmsToDataStmt != null) {
        dmsToDataStmt.close();
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
    return endDay;
  }
  
  public void updateOSendDay(String importDate)
    throws Exception
  {
    PreparedStatement dmsToDataStmt = null;
    Connection conn = null;
    try
    {
      log.info("[根据开始及结束日期更新DMS反馈的数据为已发送状态]开始");
      conn = DBConnection.getConnection();
      conn.setAutoCommit(false);
      StringBuilder bud = new StringBuilder();
      
      bud.append("UPDATE FC_DMS_SUB_CALL_CENTER_ERROR R SET R.IS_SEND='Y' WHERE r.send_date='" + importDate + "'");
      dmsToDataStmt = conn.prepareStatement(bud.toString());
      

      dmsToDataStmt.addBatch();
      dmsToDataStmt.executeBatch();
      conn.commit();
    }
    catch (Exception e)
    {
      log.error("[根据开始及结束日期更新DMS反馈的数据为已发送状态]出现异常", e);
      conn.rollback();
      if (dmsToDataStmt != null) {
        dmsToDataStmt.close();
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
      if (dmsToDataStmt != null) {
        dmsToDataStmt.close();
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
  
  public Integer getPreSellCountByNewImpl(String date)
    throws Exception
  {
    log.info("[查询当天是否存在售前数据 new dao]开始");
    PreparedStatement dmsToDataStmt = null;
    Connection conn = null;
    Integer resNumber = Integer.valueOf(0);
    try
    {
      conn = DBConnection.getConnection();
      conn.setAutoCommit(false);
      String sql = "select count(*) from fc_m2f_to_callcenter da where da.senddate is null ";
      dmsToDataStmt = conn.prepareStatement(sql);
      ResultSet res = dmsToDataStmt.executeQuery();
      if (res != null) {
        while (res.next()) {
          resNumber = Integer.valueOf(res.getInt(1));
        }
      }
      conn.commit();
    }
    catch (Exception e)
    {
      log.error("[查询当天是否存在售前数据 new dao]异常！", e);
      conn.rollback();
      throw e;
    }
    finally
    {
      if (dmsToDataStmt != null) {
        dmsToDataStmt.close();
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
    return resNumber;
  }
  
  public List<M2FToCcDataBean> getPreSellDataByNewImpl(String sendDate)
    throws Exception
  {
    List<M2FToCcDataBean> preList = new ArrayList();
    PreparedStatement dmsToDataStmt = null;
    Connection conn = null;
    log.info("[根据日期获取当天可用的售前数据 new dao] start ");
    try
    {
      conn = DBConnection.getConnection();
      conn.setAutoCommit(false);
      String sql = "SELECT *  FROM fc_m2f_to_callcenter m  WHERE m.SENDDATE is null ";
      
      dmsToDataStmt = conn.prepareStatement(sql);
      ResultSet res = dmsToDataStmt.executeQuery();
      if (res != null) {
        while (res.next())
        {
          M2FToCcDataBean preBean = new M2FToCcDataBean();
          preBean.setData_dist_date(res.getString(1));
          preBean.setData_priority(res.getString(2));
          preBean.setName(res.getString(3));
          preBean.setSex(res.getString(4));
          preBean.setMobile(res.getString(5));
          preBean.setMobile2(res.getString(6));
          preBean.setP_tel(res.getString(7));
          preBean.setPhone(res.getString(8));
          preBean.setEmail(res.getString(9));
          preBean.setProvince(res.getString(10));
          preBean.setCity(res.getString(11));
          preBean.setClient_source(res.getString(12));
          preBean.setLike_model(res.getString(13));
          preBean.setJh_car_time(res.getString(14));
          preBean.setClient_demand(res.getString(15));
          preBean.setManame(res.getString(16));
          preBean.setDealer_code(res.getString(17));
          preBean.setDealer_region(res.getString(18));
          preBean.setDealer_community(res.getString(19));
          preBean.setDealer_name(res.getString(20));
          preBean.setSell_id(res.getString(21));
          preBean.setImport_code(res.getString(22));
          preBean.setData_uid(res.getString(23));
          preBean.setSenddate(sendDate);
          preBean.setCreatedate(res.getString(25));
          preBean.setLocal_to_dealer_id(res.getString(26));
          preBean.setC_jobtitle(res.getString(27));
          preBean.setC_nationalid(res.getString(28));
          preBean.setC_dateofbirth(res.getString(29));
          preBean.setC_maritalstatus(res.getString(30));
          preBean.setC_monthlyhouserholdincome(res.getString(31));
          preBean.setC_ext1(res.getString(32));
          preBean.setC_ext2(res.getString(33));
          preBean.setC_ext3(res.getString(34));
          preBean.setC_ext4(res.getString(35));
          preBean.setC_ext5(res.getString(36));
          preBean.setC_ext6(res.getString(37));
          preBean.setC_ext7(res.getString(38));
          preBean.setC_ext8(res.getString(39));
          preList.add(preBean);
        }
      }
      conn.commit();
    }
    catch (Exception e)
    {
      log.error("[根据日期获取当天可用的售前数据 new dao]异常", e);
      conn.rollback();
      throw e;
    }
    finally
    {
      if (dmsToDataStmt != null) {
        dmsToDataStmt.close();
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
    log.info("[根据日期获取当天可用的售前数据 new dao] end ;" + preList.size());
    return preList;
  }
  
  public void updateSendDate(String date)
    throws Exception
  {
    PreparedStatement dmsToDataStmt = null;
    Connection conn = null;
    DateFormat formaty = new SimpleDateFormat("yyyyMMdd");
    String sendDate = formaty.format(new Date());
    try
    {
      log.info("[根据发送日期更新sendDate fc_m2f_to_callcenter]开始");
      conn = DBConnection.getConnection();
      conn.setAutoCommit(false);
      StringBuilder bud = new StringBuilder();
      bud.append("UPDATE fc_m2f_to_callcenter R SET R.senddate='" + sendDate + "' WHERE r.senddate is null ");
      dmsToDataStmt = conn.prepareStatement(bud.toString());
      dmsToDataStmt.addBatch();
      dmsToDataStmt.executeBatch();
      conn.commit();
    }
    catch (Exception e)
    {
      log.error("[根据发送日期更新sendDate fc_m2f_to_callcenter]出现异常", e);
      conn.rollback();
      if (dmsToDataStmt != null) {
        dmsToDataStmt.close();
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
      if (dmsToDataStmt != null) {
        dmsToDataStmt.close();
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
  
  public void updateFcM2fToDms(String imoprtDate)
    throws Exception
  {
    log.info("[手动执行发送给dms的售前数据时，清除senddate]开始");
    PreparedStatement dmsToDataStmt = null;
    Connection conn = null;
    try
    {
      conn = DBConnection.getConnection();
      conn.setAutoCommit(false);
      dmsToDataStmt = conn.prepareStatement("update FC_M2F_TO_DMS g set g.senddate='' where  g.senddate is null ");
      dmsToDataStmt.addBatch();
      dmsToDataStmt.executeBatch();
      conn.commit();
    }
    catch (Exception e)
    {
      log.error("[手动执行发送给dms的售前数据时，清除senddate]出现异常", e);
      conn.rollback();
      throw e;
    }
    finally
    {
      if (dmsToDataStmt != null) {
        dmsToDataStmt.close();
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
  
  public PageBean queryPageByFcToDmsPreSell(int firstSize, int maxSize, String importDate)
    throws SQLException
  {
    log.info("[查询上传至dms的售前数据]开始");
    PreparedStatement dmsToDataStmt = null;
    Connection conn = null;
    try
    {
      conn = DBConnection.getConnection();
      conn.setAutoCommit(false);
      StringBuffer sqlB = new StringBuffer();
      sqlB.append("  select b.* from  ");
      sqlB.append(" (select g.*,row_number() OVER (PARTITION BY g.mobile ORDER BY g.createdate desc) AS NUM ");
      sqlB.append("  from FC_M2F_TO_DMS g  where  g.senddate is null ) b where num=1 ");
      dmsToDataStmt = conn.prepareStatement(sqlB.toString(), 1004, 1007);
      
      ResultSet rs = dmsToDataStmt.executeQuery();
      
      List<M2FToDmsDataBean> list = new ArrayList();
      PageBean pageBean = new PageBean(firstSize, maxSize, rs);
      
      List<M2FToDmsDataBean> bL = settingDmsPreSellData(rs, maxSize);
      pageBean.setDataList(bL);
      
      return pageBean;
    }
    catch (Exception e)
    {
      log.error("[查询上传至dms的售前数据]出现异常", e);
      conn.rollback();
    }
    finally
    {
      if (dmsToDataStmt != null) {
        dmsToDataStmt.close();
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
    return null;
  }
  
  public Integer queryCountByFcToDmsPreSell(String importDate)
    throws SQLException
  {
    log.info("[查询售前dms数据总数 new dao]开始");
    PreparedStatement dmsToDataStmt = null;
    Connection conn = null;
    Integer resNumber = Integer.valueOf(0);
    try
    {
      conn = DBConnection.getConnection();
      conn.setAutoCommit(false);
      String sql = "select count(*) from FC_M2F_TO_DMS g  where  g.senddate is null ";
      dmsToDataStmt = conn.prepareStatement(sql);
      ResultSet res = dmsToDataStmt.executeQuery();
      if (res != null) {
        while (res.next()) {
          resNumber = Integer.valueOf(res.getInt(1));
        }
      }
      conn.commit();
    }
    catch (Exception e)
    {
      log.error("[查询售前dms数据总数 new dao]异常！", e);
      conn.rollback();
      if (dmsToDataStmt != null) {
        dmsToDataStmt.close();
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
      if (dmsToDataStmt != null) {
        dmsToDataStmt.close();
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
    return resNumber;
  }
  
  public List<M2FToDmsDataBean> settingDmsPreSellData(ResultSet res, int maxSize)
    throws SQLException
  {
    List<M2FToDmsDataBean> uplist = new ArrayList();
    int count = 0;
    while (res.next())
    {
      count++;
      if (count > maxSize) {
        break;
      }
      M2FToDmsDataBean preBean = new M2FToDmsDataBean();
      preBean.setDataDistDate(res.getString(1));
      preBean.setDataPriority(res.getString(2));
      preBean.setName(res.getString(3));
      if ("男".equals(res.getString(4))) {
        preBean.setSex("10061001");
      } else if ("女".equals(res.getString(4))) {
        preBean.setSex("10061002");
      } else if ("未知".equals(res.getString(4))) {
        preBean.setSex("10061003");
      } else {
        preBean.setSex(res.getString(4));
      }
      preBean.setMobile(res.getString(5));
      preBean.setMobile2(res.getString(6));
      preBean.setpTel(res.getString(7));
      preBean.setPhone(res.getString(8));
      preBean.setEmail(res.getString(9));
      preBean.setProvince(res.getString(10));
      preBean.setCity(res.getString(11));
      preBean.setClientSource(res.getString(12));
      preBean.setLikeModel(res.getString(13));
      preBean.setJhCarTime(res.getString(35));
      preBean.setClientDemand(res.getString(15));
      preBean.setManame(res.getString(16));
      preBean.setDealerCode(res.getString(17));
      preBean.setDealerRegion(res.getString(18));
      preBean.setDealerCommunity(res.getString(19));
      preBean.setDealerName(res.getString(20));
      preBean.setSellId(res.getString(21));
      

      uplist.add(preBean);
    }
    return uplist;
  }
  
  public void updateFcM2fToDmsBySendDate(String sendDate)
    throws Exception
  {
    log.info("[手动执行发送给dms的售前数据updateFcM2fToDmsBySendDate时，senddate]开始");
    PreparedStatement dmsToDataStmt = null;
    Connection conn = null;
    try
    {
      conn = DBConnection.getConnection();
      conn.setAutoCommit(false);
      dmsToDataStmt = conn.prepareStatement("update FC_M2F_TO_DMS g set g.sendDate='" + sendDate + "' where  g.senddate is null ");
      dmsToDataStmt.addBatch();
      dmsToDataStmt.executeBatch();
      conn.commit();
    }
    catch (Exception e)
    {
      log.error("[手动执行发送给dms的售前数据updateFcM2fToDmsBySendDate时，senddate]出现异常", e);
      conn.rollback();
      throw e;
    }
    finally
    {
      if (dmsToDataStmt != null) {
        dmsToDataStmt.close();
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
  
  public void saveFcm2fToDms()
    throws Exception
  {
    PreparedStatement dmsToDataStmt = null;
    Connection conn = null;
    try
    {
      Timestamp ts = new Timestamp(System.currentTimeMillis());
      log.info("[保存整理好发送给dms的售前数据]开始");
      conn = DBConnection.getConnection();
      conn.setAutoCommit(false);
      
      StringBuffer sqlB = new StringBuffer();
      sqlB.append("  insert into fc_m2f_to_dms ( data_dist_date,data_priority, name ,sex, mobile ,mobile2,p_tel, ");
      sqlB.append("  phone, email, province,city ,client_source,like_model , ");
      sqlB.append("  jh_car_time , client_demand , maname,dealer_code,dealer_region ,dealer_community ,  ");
      sqlB.append(" dealer_name, sell_id,import_code,data_uid  ,senddate,createdate , local_to_dealer_id,JHCARTIME  ) ");
      sqlB.append("  select to_char(sysdate,'yyyy-MM-dd'),decode(md.buycartime,'0-3 Months','高','4-6 Months','中','低'),md.name,md.gender ");
      sqlB.append("  ,md.mobile,'','',md.phone,md.EMAILMERGE,md.province,md.city, ");
      sqlB.append(" md.CUSTOMERSOURCE,md.Interestedmodel,md.buycartime,md.CUSTOMERDEMAND,md.ACTIVITYNAME ");
      sqlB.append("  ,md.DEALERCODE,md.BIGAREA,md.SMALLAREA,md.DEALERNAME,seq_fc_m2f_to_dms.nextval ");
      sqlB.append("  ,to_char(sysdate,'yyyyMMdd')||seq_fc_m2f_to_dms.nextval,seq_fc_m2f_to_dms.nextval,'',to_char(sysdate,'yyyyMMddHHmmss'),md.tid,md.jhcartime ");
      sqlB.append("   from ( SELECT * FROM (SELECT a.*, row_number() OVER(PARTITION BY mobile ORDER BY mid DESC) AS NUM ");
      sqlB.append("    FROM fc_m2f_mobile_sms a WHERE a.status = '1'  and a.sendstatus !='e0' and a.sendstatus !='ew0' and substr(a.SENDTIME,1,8)=to_char(sysdate,'yyyyMMdd')");
      sqlB.append(" ) x  ");
      sqlB.append("  WHERE x.num = 1 )sr ");
      sqlB.append(" JOIN (SELECT * FROM (SELECT a.*,row_number()OVER(PARTITION BY mobile ORDER BY tid DESC) AS NUM FROM fc_m2f_dataraw a    )x WHERE x.num=1 ");
      sqlB.append("  ) md ON sr.mobile=md.mobile and  md.dup_type IS  NULL  ");
      sqlB.append("  and (md.DUP_ERROR is  null   or md.DUP_ERROR ='' )");
      

      sqlB.append("  where ( (sr.replycontent IS NOT NULL AND sr.replytime IS NOT NULL   ");
      sqlB.append("  and Upper(sr.replycontent)!='N'  AND floor(to_number(to_date(sr.replytime,'yyyymmddHH24miss')-to_date(sr.sendtime,'yyyymmddHH24miss'))*24*60*60)<=3600) ");
      sqlB.append(" or sr.sendstatus='p0' or sr.sendstatus='y0' ");
      sqlB.append("  OR ( (  ( replytime IS NULL and sr.sendstatus !='e0' and sr.sendstatus !='ew0' )or sr.sendstatus  IS NULL ) AND floor(to_number(SYSDATE-to_date(sr.sendtime,'yyyymmddHH24miss'))*24*60*60)>3600) ");
      sqlB.append(" or sr.sendstatus='2'  or sr.sendstatus='b0'  ");
      sqlB.append(" )  ");
      sqlB.append(" and  not exists (select 1 from fc_m2f_to_dms a where a.local_to_dealer_id=md.tid)  ");
      dmsToDataStmt = conn.prepareStatement(sqlB.toString());
      dmsToDataStmt.addBatch();
      dmsToDataStmt.executeBatch();
      conn.commit();
    }
    catch (Exception e)
    {
      log.error("[保存整理好发送给dms的售前数据]出现异常", e);
      conn.rollback();
      throw e;
    }
    finally
    {
      if (dmsToDataStmt != null) {
        dmsToDataStmt.close();
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
  
  public void saveFcm2fTocc2()
    throws Exception
  {
    PreparedStatement dmsToDataStmt = null;
    Connection conn = null;
    try
    {
      Timestamp ts = new Timestamp(System.currentTimeMillis());
      log.info("[保存整理好发送给cc的售前数据]开始");
      conn = DBConnection.getConnection();
      conn.setAutoCommit(false);
      
      StringBuffer sqlB = new StringBuffer();
      sqlB.append(" insert into fc_m2f_to_callcenter ( data_dist_date,data_priority, name ,sex, mobile ,mobile2,p_tel, ");
      sqlB.append("  phone, email, province,city ,client_source,like_model ,  ");
      sqlB.append("  jh_car_time , client_demand , maname,dealer_code,dealer_region ,dealer_community , ");
      sqlB.append("  dealer_name, sell_id,import_code,data_uid  ,senddate,createdate , local_to_dealer_id,jhcartime ) ");
      sqlB.append("  select to_char(sysdate,'yyyy-MM-dd'),decode(md.buycartime,'0-3 Months','高','4-6 Months','中','低'),md.name,md.gender ");
      sqlB.append(" ,md.mobile,'','',md.phone,md.EMAILMERGE,md.province,md.city,  ");
      sqlB.append(" md.CUSTOMERSOURCE ,md.Interestedmodel,md.buycartime,md.CUSTOMERDEMAND,md.ACTIVITYNAME ");
      sqlB.append("  ,md.DEALERCODE,md.BIGAREA,md.SMALLAREA,md.DEALERNAME,seq_fc_m2f_to_dms.nextval ");
      sqlB.append("    ,to_char(sysdate,'yyyyMMdd')||seq_fc_m2f_to_dms.nextval,seq_fc_m2f_to_callcenter.nextval,'',to_char(sysdate,'yyyyMMddHH24mmss'),md.tid,md.jhcartime ");
      sqlB.append("   from ( SELECT * FROM (SELECT a.*, row_number() OVER(PARTITION BY mobile ORDER BY mid DESC) AS NUM ");
      sqlB.append("    FROM fc_m2f_mobile_sms a WHERE (a.status = '1' or a.status='k11' ) and  substr(a.SENDTIME,1,8)=to_char(sysdate,'yyyyMMdd')  ) x ");
      sqlB.append("  WHERE x.num = 1 )sr ");
      sqlB.append("  JOIN (SELECT * FROM (SELECT a.*,row_number()OVER(PARTITION BY mobile ORDER BY tid DESC) AS NUM FROM fc_m2f_dataraw a  ");
      sqlB.append(" where   a.dup_type IS  NULL  ");
      sqlB.append("  )x WHERE x.num=1  ");
      
      sqlB.append("  ) md ON sr.mobile=md.mobile  and ( md.dup_type IS  NULL ) ");
      
      sqlB.append("  where ( (sr.replycontent IS NOT NULL AND sr.replytime IS NOT NULL ");
      sqlB.append("  and Upper(sr.replycontent)!='N' and md.DUP_ERROR is not null  AND floor(to_number(to_date(sr.replytime,'yyyymmddHH24miss')-to_date(sr.sendtime,'yyyymmddHH24miss'))*24*60*60)<=3600)  ");
      
      sqlB.append(" or sr.sendstatus='p0' or sr.sendstatus='y0' ");
      sqlB.append(" OR ( ( ( replytime IS NULL and sr.sendstatus !='e0' and sr.sendstatus !='ew0' and  md.DUP_ERROR is not null)  or sr.sendstatus  IS NULL ) AND floor(to_number(SYSDATE-to_date(sr.sendtime,'yyyymmddHH24miss'))*24*60*60)>3600) ");
      sqlB.append(" OR (   replytime IS not  NULL and  md.DUP_ERROR is not null and  floor(to_number(to_date(sr.replytime,'yyyymmddHH24miss')-to_date(sr.sendtime,'yyyymmddHH24miss'))*24*60*60)>3600)  ");
      
      sqlB.append(" or sr.sendstatus='2' or sr.sendstatus='b0'   ");
      sqlB.append(" ) ");
      sqlB.append(" AND not exists (select 1 from fc_m2f_to_callcenter a where a.local_to_dealer_id=md.tid) ");
      dmsToDataStmt = conn.prepareStatement(sqlB.toString());
      dmsToDataStmt.addBatch();
      dmsToDataStmt.executeBatch();
      conn.commit();
    }
    catch (Exception e)
    {
      log.error("[保存整理好发送给cc的售前数据]出现异常", e);
      conn.rollback();
      throw e;
    }
    finally
    {
      if (dmsToDataStmt != null) {
        dmsToDataStmt.close();
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
  
  public void saveFcm2fTocc()
    throws Exception
  {
    PreparedStatement dmsToDataStmt = null;
    Connection conn = null;
    try
    {
      Timestamp ts = new Timestamp(System.currentTimeMillis());
      log.info("[保存整理好发送给cc的售前数据]开始");
      conn = DBConnection.getConnection();
      conn.setAutoCommit(false);
      
      StringBuffer sqlB = new StringBuffer();
      sqlB.append(" insert into fc_m2f_to_callcenter ( data_dist_date,data_priority, name ,sex, mobile ,mobile2,p_tel, ");
      sqlB.append("  phone, email, province,city ,client_source,like_model ,  ");
      sqlB.append("  jh_car_time , client_demand , maname,dealer_code,dealer_region ,dealer_community , ");
      sqlB.append("  dealer_name, sell_id,import_code,data_uid  ,senddate,createdate , local_to_dealer_id,jhcartime ) ");
      sqlB.append("  select to_char(sysdate,'yyyy-MM-dd'),decode(md.buycartime,'0-3 Months','高','4-6 Months','中','低'),md.name,md.gender ");
      sqlB.append(" ,md.mobile,'','',md.phone,md.EMAILMERGE,md.province,md.city,  ");
      sqlB.append(" md.CUSTOMERSOURCE ,md.Interestedmodel,md.buycartime,md.CUSTOMERDEMAND,md.ACTIVITYNAME ");
      sqlB.append("  ,md.DEALERCODE,md.BIGAREA,md.SMALLAREA,md.DEALERNAME,seq_fc_m2f_to_dms.nextval ");
      sqlB.append("  ,to_char(sysdate,'yyyyMMdd')||seq_fc_m2f_to_dms.nextval,seq_fc_m2f_to_callcenter.nextval,'',to_char(sysdate,'yyyyMMddHH24mmss'),md.tid,md.jhcartime                                   ");
      sqlB.append("   from ( SELECT * FROM (SELECT a.*, row_number() OVER(PARTITION BY mobile ORDER BY mid DESC) AS NUM ");
      sqlB.append("    FROM fc_m2f_mobile_sms a WHERE (a.status = '1' or a.status='K11') and  substr(a.SENDTIME,1,8)=to_char(sysdate,'yyyyMMdd')  ) x ");
      sqlB.append("  WHERE x.num = 1 )sr ");
      sqlB.append("  JOIN (SELECT * FROM (SELECT a.*,row_number()OVER(PARTITION BY mobile ORDER BY tid DESC) AS NUM FROM fc_m2f_dataraw a  ");
      sqlB.append(" where   (a.dup_type IS  NULL  or a.dup_type='K_13' )");
      sqlB.append("  )x WHERE x.num=1  ");
      sqlB.append("  ) md ON sr.mobile=md.mobile  and ( md.dup_type IS  NULL or md.dup_type='K_13' ) ");
      
      sqlB.append(" where ( (((sr.sendstatus!='0' and sr.sendstatus!='ew0' and sr.sendstatus!='e0') or sr.sendstatus is null)  and md.dup_error is null ) ");
      sqlB.append("  OR (nvl(md.REMARKS1,'1')='品牌朋友圈活动' AND ((sr.sendstatus!='ew0' AND sr.sendstatus!='e0') or sr.sendstatus is null) and  Upper(sr.replycontent)!='N' )  ");
      sqlB.append(" ) ");
      sqlB.append(" AND not exists (select 1 from fc_m2f_to_callcenter a where a.local_to_dealer_id=md.tid) ");
      dmsToDataStmt = conn.prepareStatement(sqlB.toString());
      dmsToDataStmt.addBatch();
      dmsToDataStmt.executeBatch();
      conn.commit();
    }
    catch (Exception e)
    {
      log.error("[保存整理好发送给cc的售前数据]出现异常", e);
      conn.rollback();
      throw e;
    }
    finally
    {
      if (dmsToDataStmt != null) {
        dmsToDataStmt.close();
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
  
  public List<DmsSubCallCenterBean> getSubCallList2()
    throws Exception
  {
    PreparedStatement dmsToDataStmt = null;
    Connection conn = null;
    List<DmsSubCallCenterBean> subList = null;
    try
    {
      conn = DBConnection.getConnection();
      conn.setAutoCommit(false);
      StringBuilder bud = new StringBuilder();
      bud.append("select * ")
        .append("  from abc f ");
      
      dmsToDataStmt = conn.prepareStatement(bud.toString());
      
      ResultSet res = dmsToDataStmt.executeQuery();
      if (res != null)
      {
        subList = new ArrayList();
        while (res.next())
        {
          DmsSubCallCenterBean dscBean = new DmsSubCallCenterBean();
          dscBean.setYm(res.getString(1));
          dscBean.setSale_date(res.getString(2));
          dscBean.setDealer_code(res.getString(3));
          dscBean.setDealer_name(res.getString(4));
          dscBean.setVin_name(res.getString(5));
          dscBean.setColor(res.getString(6));
          dscBean.setCar_brand(res.getString(7));
          dscBean.setCar_model(res.getString(8));
          dscBean.setCatecode(res.getString(9));
          dscBean.setCar_carreg(res.getString(10));
          dscBean.setOwner_name(res.getString(11));
          dscBean.setOwner_sex(res.getString(12));
          dscBean.setOwner_birthday(res.getString(13));
          dscBean.setOwner_marital(res.getString(14));
          dscBean.setOwner_tel(res.getString(15));
          dscBean.setOwner_mobile(res.getString(16));
          dscBean.setOwner_province(res.getString(17));
          dscBean.setOwner_city(res.getString(18));
          dscBean.setOwner_address(res.getString(19));
          dscBean.setOwner_zip(res.getString(20));
          dscBean.setOwner_email(res.getString(21));
          dscBean.setContacter_name(res.getString(22));
          dscBean.setContacter_sex(res.getString(23));
          dscBean.setContacter_tel(res.getString(24));
          dscBean.setContacter_mobile(res.getString(25));
          dscBean.setContacter_address(res.getString(26));
          dscBean.setContacter_email(res.getString(27));
          dscBean.setCar_sync(res.getString(28));
          dscBean.setCar_ea(res.getString(29));
          dscBean.setOb_type(res.getString(30));
          dscBean.setCreateOwnerDate(res.getString(31));
          dscBean.setCertificate_type(res.getString(33));
          dscBean.setCertificate_code(res.getString(34));
          
          dscBean.setPriority("B");
          dscBean.setDup_type("");
          subList.add(dscBean);
        }
      }
      conn.commit();
    }
    catch (Exception e)
    {
      log.error("[根据日期段查询可用于发发送Call Center的数据2]出现异常", e);
      conn.rollback();
      throw e;
    }
    finally
    {
      if (dmsToDataStmt != null) {
        dmsToDataStmt.close();
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
    return subList;
  }
}

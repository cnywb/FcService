package com.fc.dao.db.businessDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.fc.bean.DmsObQuestionsBean;
import com.fc.bean.DmsObToData;
import com.fc.bean.FcServiceLogBean;
import com.fc.dao.conn.DBConnection;

/**
 * 接收CallCenter售后数据业务DAO
 * @author lq
 *
 */
public class GetCallCenterDataDao {
	private static Connection conn = null;
	private static Logger log = Logger.getLogger(GetCallCenterDataDao.class);
	/**
	 * 根据VIN码，提交日期查询OB历史
	 * @param vin
	 * @param subDate
	 * @return
	 * @throws Exception
	 */
	public int selectCallCenterDataCount(String vin,String subDate)throws Exception{

		PreparedStatement dmsToDataStmt = null;
		Integer resNumber = 0;
		try {
			conn = DBConnection.getConnection();
			conn.setAutoCommit(false);
			String sql = "select count(*) from FC_DMS_OB_TO_DATA f where  f.SUB_DATE=?  and f.VIN=? ";
			dmsToDataStmt = conn.prepareStatement(sql);
			dmsToDataStmt.setString(1,subDate);
			dmsToDataStmt.setString(2, vin);
			ResultSet res = dmsToDataStmt.executeQuery();
			if (res != null) {
				while (res.next()) {
					resNumber = res.getInt(1);
				}
			}
			conn.commit();
		} catch (Exception e) {
			log.error("根据读取日期查询当期读取数目异常", e);
			conn.rollback();
			throw e;
		} finally {
			if (dmsToDataStmt != null) {
				dmsToDataStmt.close();
			}
			DBConnection.closeConnection();
		}
		return resNumber;
	}
	/**
	 * 根据VIN码，
	 * @param vin
	 * @return
	 * @throws Exception
	 */
	public int selectCallCenterDataCountByVin(String vin)throws Exception{
		PreparedStatement dmsToDataStmt = null;
		Integer resNumber = 0;
		  Connection conncount = DBConnection.getConnection();
		try {
		    conncount.setAutoCommit(false);
			String sql = "select count(*) from FC_DMS_OB_TO_DATA f where f.VIN=? ";
			dmsToDataStmt = conncount.prepareStatement(sql);
			dmsToDataStmt.setString(1, vin);
			ResultSet res = dmsToDataStmt.executeQuery();
			if (res != null) {
				while (res.next()) {
					resNumber = res.getInt(1);
				}
			}
			conncount.commit();
		} catch (Exception e) {
			log.error("根据读取日期查询当期读取数目异常", e);
			conncount.rollback();
			throw e;
		} finally {
			if (dmsToDataStmt != null) {
				dmsToDataStmt.close();
			}
			conncount.close();
		}
		return resNumber;
	}
	/**
	 * 根据日期清除对应的数据
	 * @throws Exception
	 */
	public void getCCDateRemove(String date)throws Exception{
		log.info("[根据日期清除对应的数据]当前删除getCCDateRemove："+date);
		java.sql.Statement  stam=null;
		try {
			conn = DBConnection.getConnection();
			conn.setAutoCommit(false);
			stam=conn.createStatement();
			String[] Sqls={
			"delete from fc_dms_ob_to_data t where t.return_date='"+date+"'"};
			for (int i = 0; i < Sqls.length; i++) {
				stam.execute(Sqls[i]);
			}
			conn.commit();
		}catch(Exception e){
			log.error("根据日期清除对应的数据异常getCCDateRemove！",e);
			conn.rollback();
			throw e;
		}finally{
			if(stam!=null){
				stam.close();
			}
			DBConnection.closeConnection();
		}
	}
	/**
	 * 插入CallCenter返回的数据
	 */
	public void insertCallCenterToData(List<DmsObToData> obList)
			throws Exception {
		List<DmsObQuestionsBean> quest = new ArrayList<DmsObQuestionsBean>();
		PreparedStatement dmsToDataStmt = null;
		try {
			if (obList != null && obList.size() > 0) {
				log.info("[插入CallCenter返回的数据]开始");
				conn = DBConnection.getConnection();
				for (DmsObToData ob : obList) {
					String sql = "select count(*) from FC_DMS_OB_TO_DATA f where  f.SUB_DATE=?  and f.VIN=? ";
					dmsToDataStmt = conn.prepareStatement(sql);
					dmsToDataStmt.setString(1,ob.getSubDate());
					dmsToDataStmt.setString(2, ob.getVin());
					ResultSet res = dmsToDataStmt.executeQuery();
					if (res != null) {
						while (res.next()) {
							if (res.getInt(1)>0)
									ob.setIsHistory(1);
						}
					}
					res.close();
					dmsToDataStmt.close();
				}
				
				conn.setAutoCommit(false);
				//String sql = "INSERT INTO FC_DMS_OB_TO_DATA VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				  String sql = "INSERT INTO FC_DMS_OB_TO_DATA VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				dmsToDataStmt = conn.prepareStatement(sql);
				for (DmsObToData dmsob : obList) {
					if(dmsob.getIsHistory()==0){
						if (dmsob.getListQuestions() != null
								&& dmsob.getListQuestions().size() > 0) {
							quest.addAll(dmsob.getListQuestions());
						}
						dmsToDataStmt.setString(1, dmsob.getReturnDate());
						dmsToDataStmt.setString(2, dmsob.getBuyCarDate());
						dmsToDataStmt.setString(3, dmsob.getCarType());
						dmsToDataStmt.setString(4, dmsob.getCars());
						dmsToDataStmt.setString(5, dmsob.getCatcode());
						dmsToDataStmt.setString(6, dmsob.getDealerCode());
						dmsToDataStmt.setString(7, dmsob.getDealerName());
						dmsToDataStmt.setString(8, dmsob.getOwnerName());
						dmsToDataStmt.setString(9, dmsob.getInfoClear1());
						dmsToDataStmt.setString(10, dmsob.getuOwnerName());
						dmsToDataStmt.setString(11, dmsob.getContactName());
						dmsToDataStmt.setString(12, dmsob.getInfoClear2());
						dmsToDataStmt.setString(13, dmsob.getuContactName());
						dmsToDataStmt.setString(14, dmsob.getBuyCarNature());
						dmsToDataStmt.setString(15, dmsob.getCarUseFolk());
						dmsToDataStmt.setString(16, dmsob.getReferColor());
						dmsToDataStmt.setString(17, dmsob.getWhetherColor());
						dmsToDataStmt.setString(18, dmsob.getWhetherCompany());
						dmsToDataStmt.setString(19, dmsob.getCompanyName());
						dmsToDataStmt.setString(20, dmsob.getInfoClear3());
						dmsToDataStmt.setString(21, dmsob.getuCompanyName());
						dmsToDataStmt.setString(22, dmsob.getOwnerSex());
						dmsToDataStmt.setString(23, dmsob.getInfoClear4());
						dmsToDataStmt.setString(24, dmsob.getuOwnerSex());
						dmsToDataStmt.setString(25, dmsob.getOwnerLandline());
						dmsToDataStmt.setString(26, dmsob.getOwnerMobile());
						dmsToDataStmt.setString(27, dmsob.getInfoClear5());
						dmsToDataStmt.setString(28, dmsob.getuOwnerLandline());
						dmsToDataStmt.setString(29, dmsob.getuOwnerMobile());
						dmsToDataStmt.setString(30, dmsob.getOwnerProvince());
						dmsToDataStmt.setString(31, dmsob.getInfoClear6());
						dmsToDataStmt.setString(32, dmsob.getuOwnerProvince());
						dmsToDataStmt.setString(33, dmsob.getOwnerCity());
						dmsToDataStmt.setString(34, dmsob.getInfoClear7());
						dmsToDataStmt.setString(35, dmsob.getuOwnerCity());
						dmsToDataStmt.setString(36, dmsob.getOwnerAddress());
						dmsToDataStmt.setString(37, dmsob.getInfoClear8());
						dmsToDataStmt.setString(38, dmsob.getuOwnerAddress());
						dmsToDataStmt.setString(39, dmsob.getOwnerZipCode());
						dmsToDataStmt.setString(40, dmsob.getInfoClear9());
						dmsToDataStmt.setString(41, dmsob.getuOwnerZipCode());
						dmsToDataStmt.setString(42, dmsob.getOwnerEmail());
						dmsToDataStmt.setString(43, dmsob.getInfoClear10());
						dmsToDataStmt.setString(44, dmsob.getuOwnerEmail());
						dmsToDataStmt.setString(45, dmsob.getContactSex());
						dmsToDataStmt.setString(46, dmsob.getInfoClear11());
						dmsToDataStmt.setString(47, dmsob.getuContactSex());
						dmsToDataStmt.setString(48, dmsob.getContactLandline());
						dmsToDataStmt.setString(49, dmsob.getContactMobile());
						dmsToDataStmt.setString(50, dmsob.getInfoClear12());
						dmsToDataStmt.setString(51, dmsob.getUcontactLandline());
						dmsToDataStmt.setString(52, dmsob.getUcontactMobile());
						dmsToDataStmt.setString(53, dmsob.getContactAddress());
						dmsToDataStmt.setString(54, dmsob.getInfoClear13());
						dmsToDataStmt.setString(55, dmsob.getUcontactAddress());
						dmsToDataStmt.setString(56, dmsob.getContactEmail());
						dmsToDataStmt.setString(57, dmsob.getInfoClear14());
						dmsToDataStmt.setString(58, dmsob.getUcontact_email());
						dmsToDataStmt.setString(59, dmsob.getPlateCode());
						dmsToDataStmt.setString(60, dmsob.getVin());
						dmsToDataStmt.setString(61, dmsob.getInfoClear15());
						dmsToDataStmt.setString(62, dmsob.getuVin());
						dmsToDataStmt.setString(63, dmsob.getObDate());
						dmsToDataStmt.setString(64, dmsob.getVerificationres());
						dmsToDataStmt.setString(65, dmsob.getOwnerObstatus());
						dmsToDataStmt.setString(66, dmsob.getContactObstatus());
						dmsToDataStmt.setString(67, dmsob.getObCount());
						dmsToDataStmt.setString(68, dmsob.getCAR_SYNC());
						dmsToDataStmt.setString(69, dmsob.getCAR_EA());
						dmsToDataStmt.setString(70, dmsob.getOB_TYPE());
						dmsToDataStmt.setString(71, dmsob.getOwner_Birthday());
						dmsToDataStmt.setString(72, dmsob.getOwner_Marital());
						dmsToDataStmt.setString(73, dmsob.getSubDate());
						dmsToDataStmt.setString(74, dmsob.getODP1());
						dmsToDataStmt.setString(75, dmsob.getODP2());
						dmsToDataStmt.setString(76, dmsob.getCertificate_type());
						dmsToDataStmt.setString(77, dmsob.getCertificate_code());
						dmsToDataStmt.setString(78, dmsob.getIdentity_sit());
						dmsToDataStmt.setString(79, dmsob.getIdentity_code());
						dmsToDataStmt.setString(80, dmsob.getOid());
						//ping.qi 2014-07-17 add 接收CC拨打数据  新增拨打记录结果字段
						dmsToDataStmt.setString(81, dmsob.getCalling1());
						dmsToDataStmt.setString(82, dmsob.getRecording1());
						dmsToDataStmt.setString(83, dmsob.getCalling2());
						dmsToDataStmt.setString(84, dmsob.getRecording2());
						dmsToDataStmt.setString(85, dmsob.getCalling3());
						dmsToDataStmt.setString(86, dmsob.getRecording3());
						dmsToDataStmt.setString(87, dmsob.getCalling4());
						dmsToDataStmt.setString(88, dmsob.getRecording4());
						dmsToDataStmt.setString(89, dmsob.getDup_type());
						//ping.qi 2014-11-05 接收cc拨打数据 新增分类级别
						dmsToDataStmt.setString(90, dmsob.getPriority());
						int number=this.selectCallCenterDataCountByVin(dmsob.getVin());
						if(number >0 ){
						    dmsToDataStmt.setString(91, "");
						    dmsToDataStmt.setString(92, "1");
						}else{
						    dmsToDataStmt.setString(91, "1");
						    dmsToDataStmt.setString(92, ""); 
						}
						dmsToDataStmt.addBatch();// 加入批处理
					}
				}
				
				dmsToDataStmt.executeBatch();
				if (quest != null && quest.size() > 0) {
					log.info("[插入CallCenter返回的数据]问卷调查数据开始");
					dmsToDataStmt = conn
							.prepareStatement("INSERT INTO FC_DMS_OB_QUESTIONS VALUES(?,?,?,?,?,?,?)");
					for (DmsObQuestionsBean ques : quest) {
						dmsToDataStmt.setString(1, ques.getReturnDate());
						dmsToDataStmt.setString(2, ques.getVin());
						dmsToDataStmt.setString(3, ques.getOwnerMobile());
						dmsToDataStmt.setString(4, ques.getQuestionName());
						dmsToDataStmt.setString(5, ques.getAnswerValue());
						dmsToDataStmt.setString(6, ques.getOid());
						dmsToDataStmt.setInt(7,ques.getQnumber());
						dmsToDataStmt.addBatch();// 加入批处理
					}
					dmsToDataStmt.executeBatch();
				}
				conn.commit();
			}
		} catch (Exception e) {
			log.error("[插入CallCenter返回的数据]异常", e);
			conn.rollback();
			throw e;
		} finally {
			if (dmsToDataStmt != null) {
				dmsToDataStmt.close();
			}
			DBConnection.closeConnection();
		}
	}
	/**
	 * 更新售后全局日志
	 */
	public void updateFcServiceLog(FcServiceLogBean fcBean) throws Exception {
		PreparedStatement dmsToDataStmt = null;
		try {
			log.info("[更新售后全局日志]开始");
			conn = DBConnection.getConnection();
			conn.setAutoCommit(false);
			String sql = "UPDATE FC_SERVICE_LOG  SET  IS_DMS_TO_DATA=?,IS_CHECKED_DATA=?,IS_PRODUCE_ERROR=?,IS_SUB_CALL=?,IS_CC_GET=?,IS_ZEIT_CC=?,IS_CC_ERROR=?,IS_SUB_DMS=? WHERE START_DATE=? ";
			dmsToDataStmt = conn.prepareStatement(sql);
			dmsToDataStmt.setLong(1, fcBean.getIsDmsToData());
			dmsToDataStmt.setLong(2, fcBean.getIsCheckedData());
			dmsToDataStmt.setLong(3, fcBean.getIsProduceError());
			dmsToDataStmt.setLong(4, fcBean.getIsSubCall());
			dmsToDataStmt.setLong(5, fcBean.getIsCCGet());
			dmsToDataStmt.setLong(6, fcBean.getIsZeitCC());
			dmsToDataStmt.setLong(7, fcBean.getIsCCError());
			dmsToDataStmt.setLong(8, fcBean.getIsSubDms());
			dmsToDataStmt.setString(9, fcBean.getStartDate());
			dmsToDataStmt.addBatch();
			dmsToDataStmt.executeBatch();
			conn.commit();
		} catch (Exception e) {
			log.error("更新售后全局日志出现异常", e);
			conn.rollback();
		} finally {
			if (dmsToDataStmt != null) {
				dmsToDataStmt.close();
			}
			DBConnection.closeConnection();
		}
	}
	
	/**
	 * 更新OB后数据将城市及性别转换为代码
	 * @param createDate
	 */
	public void updateObToDataold(String createDate)throws Exception{
		log.info("[更新OB后数据将城市及性别转换为代码]开始");
		PreparedStatement dmsToDataStmt = null;
		try {
			conn = DBConnection.getConnection();
			conn.setAutoCommit(false);
			//处理车主省份
			dmsToDataStmt = conn.prepareStatement("update Fc_Dms_Ob_To_Data d "+
												  "set d.owner_province="+
												  "(select y.code "+
												  "from Fc_Dms_Dictionary y "+
												 // "where y.import_date = '"+createDate+"' "+
												  "where 1=1 "+
												  "and y.type = '9995' "+
												  "and y.code_name like  nvl(d.owner_province,'ABC')||'%' ) "+
												  "where d.return_date = '"+createDate+"' ");
			dmsToDataStmt.addBatch();// 加入批处理
			dmsToDataStmt.executeBatch();// 执行批处理
			//处理车主更新后省份
			dmsToDataStmt = conn.prepareStatement("update Fc_Dms_Ob_To_Data d "+
					  							  "set d.uowner_province="+
					  							  "(select y.code "+
					  							  "from Fc_Dms_Dictionary y "+
					  							  //"where y.import_date = '"+createDate+"' "+
					  							  "where 1=1 "+
					  							  "and y.type = '9995' "+
					  							  "and y.code_name like  nvl(d.uowner_province,'ABC')||'%' ) "+
					  							  "where d.return_date = '"+createDate+"' ");
			dmsToDataStmt.addBatch();// 加入批处理
			dmsToDataStmt.executeBatch();// 执行批处理
			//处理车主城市
			dmsToDataStmt = conn.prepareStatement("update Fc_Dms_Ob_To_Data d "+
					  							  "set d.owner_city="+
					  							  "(select y.code "+
					  							  "from Fc_Dms_Dictionary y "+
					  							  //"where y.import_date = '"+createDate+"' "+
					  							  "where 1=1 "+
					  							  "and y.type = '9996' "+
					  							  "and y.code_name like  nvl(d.owner_city,'ABC')||'%' ) "+
					  							  "where d.return_date = '"+createDate+"' ");
			dmsToDataStmt.addBatch();// 加入批处理
			dmsToDataStmt.executeBatch();// 执行批处理
			//处理车主更新后城市
			dmsToDataStmt = conn.prepareStatement("update Fc_Dms_Ob_To_Data d "+
					  							  "set d.uowner_city="+
					  							  "(select y.code "+
					  							  "from Fc_Dms_Dictionary y "+
					  							  //"where y.import_date = '"+createDate+"' "+
					  							  "where 1=1 "+
					  							  "and y.type = '9996' "+
					  							  "and y.code_name like  nvl(d.uowner_city,'ABC')||'%' ) "+
					  							  "where d.return_date = '"+createDate+"' ");
			dmsToDataStmt.addBatch();// 加入批处理
			dmsToDataStmt.executeBatch();// 执行批处理
			//处理车主性别字段
			dmsToDataStmt = conn.prepareStatement("update Fc_Dms_Ob_To_Data d "+
					  							  "set d.owner_sex="+
					  							  "(select y.code "+
					  							  "from Fc_Dms_Dictionary y "+
					  							  //"where y.import_date = '"+createDate+"' "+
					  							  "where 1=1 "+
					  							  "and y.type = '1006' "+
					  							  "and y.code_name like  nvl(d.owner_sex,'ABC')||'%' ) "+
					  							  "where d.return_date = '"+createDate+"' ");
			dmsToDataStmt.addBatch();// 加入批处理
			dmsToDataStmt.executeBatch();// 执行批处理
			//处理车主更新后性别字段
			dmsToDataStmt = conn.prepareStatement("update Fc_Dms_Ob_To_Data d "+
					  							  "set d.uowner_sex="+
					  							  "(select y.code "+
					  							  "from Fc_Dms_Dictionary y "+
					  							  //"where y.import_date = '"+createDate+"' "+
					  							  "where 1=1 "+
					  							  "and y.type = '1006' "+
					  							  "and y.code_name like  nvl(d.uowner_sex,'ABC')||'%' ) "+
					  							  "where d.return_date = '"+createDate+"' ");
			dmsToDataStmt.addBatch();// 加入批处理
			dmsToDataStmt.executeBatch();// 执行批处理
			//处理联系人性别字段
			dmsToDataStmt = conn.prepareStatement("update Fc_Dms_Ob_To_Data d "+
					  							  "set d.contact_sex="+
					  							  "(select y.code "+
					  							  "from Fc_Dms_Dictionary y "+
					  							  //"where y.import_date = '"+createDate+"' "+
					  							  "where 1=1 "+
					  							  "and y.type = '1006' "+
					  							  "and y.code_name like  nvl(d.contact_sex,'ABC')||'%' ) "+
					  							  "where d.return_date = '"+createDate+"' ");
			dmsToDataStmt.addBatch();// 加入批处理
			dmsToDataStmt.executeBatch();// 执行批处理
			//处理联系人更新后性别字段
			dmsToDataStmt = conn.prepareStatement("update Fc_Dms_Ob_To_Data d "+
					  							  "set d.ucontact_sex="+
					  							  "(select y.code "+
					  							  "from Fc_Dms_Dictionary y "+
					  							  //"where y.import_date = '"+createDate+"' "+
					  							  "where 1=1 "+
					  							  "and y.type = '1006' "+
					  							  "and y.code_name like  nvl(d.ucontact_sex,'ABC')||'%' ) "+
					  							  "where d.return_date = '"+createDate+"' ");
			dmsToDataStmt.addBatch();// 加入批处理
			dmsToDataStmt.executeBatch();// 执行批处理
			conn.commit();
		} catch (Exception e) {
			log.error("[更新OB后数据将城市及性别转换为代码]出现异常",e);
			conn.rollback();
			throw e;
		}finally{
			if(dmsToDataStmt!=null){
				dmsToDataStmt.close();
			}
			DBConnection.closeConnection();
		}
		log.info("[更新OB后数据将城市及性别转换为代码]结束");
	}
	/**
	 * 更新OB后数据将城市及性别转换为代码
	 * @param createDate
	 */
	public void updateObToData(String createDate)throws Exception{
		log.info("[更新OB后数据将城市及性别转换为代码]开始");
		PreparedStatement dmsToDataStmt = null;
		try {
			conn = DBConnection.getConnection();
			conn.setAutoCommit(false);
			//处理车主省份
			dmsToDataStmt = conn.prepareStatement(" update Fc_Dms_Ob_To_Data d  set d.owner_province= (SELECT a.code  FROM "+
							      " (SELECT code,code_name,row_number() over (partition by code_name   order by import_date DESC) AS NUM FROM Fc_Dms_Dictionary y " +
				                              " where 1=1  and y.type = '9995'  )a "+ 
							      " WHERE a.num=1 and a.code_name like   nvl(d.owner_province,'ABC')||'%' ) "+
							      " WHERE EXISTS (SELECT 1 FROM (SELECT code,code_name,row_number() over (partition by code_name "+ 
							      " order by import_date DESC) AS NUM "+
							      " FROM Fc_Dms_Dictionary y "+
							      " where 1=1  and y.type = '9995')a  "+
							      " WHERE a.num=1 and a.code_name like   nvl(d.owner_province,'ABC')||'%') and  d.return_date = '"+createDate+"' ");
			dmsToDataStmt.addBatch();// 加入批处理
			dmsToDataStmt.executeBatch();// 执行批处理
			//处理车主更新后省份  
			dmsToDataStmt = conn.prepareStatement(" update Fc_Dms_Ob_To_Data d  set d.uowner_province= (SELECT a.code  FROM "+
				                              " (SELECT code,code_name,row_number() over (partition by code_name   order by import_date DESC) AS NUM FROM Fc_Dms_Dictionary y " +
				                              " where 1=1  and y.type = '9995'  )a "+ 
				                              " WHERE a.num=1 and a.code_name like   nvl(d.uowner_province,'ABC')||'%'  ) "+
				                              " WHERE EXISTS (SELECT 1 FROM (SELECT code,code_name,row_number() over (partition by code_name "+ 
							      " order by import_date DESC) AS NUM "+
							      " FROM Fc_Dms_Dictionary y "+
							      " where 1=1  and y.type = '9995')a  "+
							      " WHERE a.num=1 and a.code_name like   nvl(d.uowner_province,'ABC')||'%') and  d.return_date = '"+createDate+"' ");
			dmsToDataStmt.addBatch();// 加入批处理
			dmsToDataStmt.executeBatch();// 执行批处理
			//处理车主城市    
			dmsToDataStmt = conn.prepareStatement(" update Fc_Dms_Ob_To_Data d  set d.owner_city= (SELECT a.code  FROM "+
	                                		      " (SELECT code,code_name,row_number() over (partition by code_name   order by import_date DESC) AS NUM FROM Fc_Dms_Dictionary y " +
	                                		      " where 1=1  and y.type = '9996' )a "+ 
	                                		      " WHERE a.num=1 and a.code_name like   nvl(d.owner_city,'ABC')||'%' ) "+
	                                		      " WHERE EXISTS (SELECT 1 FROM (SELECT code,code_name,row_number() over (partition by code_name "+ 
							      " order by import_date DESC) AS NUM "+
							      " FROM Fc_Dms_Dictionary y "+
							      " where 1=1  and y.type = '9996')a  "+
							      " WHERE a.num=1 and a.code_name like   nvl(d.owner_city,'ABC')||'%') and  d.return_date = '"+createDate+"' ");
			dmsToDataStmt.addBatch();// 加入批处理
			dmsToDataStmt.executeBatch();// 执行批处理
			//处理车主更新后城市  
			dmsToDataStmt = conn.prepareStatement(" update Fc_Dms_Ob_To_Data d  set d.uowner_city= (SELECT a.code  FROM "+
							      " (SELECT code,code_name,row_number() over (partition by code_name   order by import_date DESC) AS NUM FROM Fc_Dms_Dictionary y " +
							      " where 1=1  and y.type = '9996' )a "+ 
							      " WHERE a.num=1 and a.code_name like   nvl(d.uowner_city,'ABC')||'%'  ) "+
							      " WHERE EXISTS (SELECT 1 FROM (SELECT code,code_name,row_number() over (partition by code_name "+ 
							      " order by import_date DESC) AS NUM "+
							      " FROM Fc_Dms_Dictionary y "+
							      " where 1=1  and y.type = '9996')a  "+
							      " WHERE a.num=1 and a.code_name like   nvl(d.uowner_city,'ABC')||'%') and  d.return_date = '"+createDate+"' ");
   			      
			dmsToDataStmt.addBatch();// 加入批处理
			dmsToDataStmt.executeBatch();// 执行批处理
			//处理车主性别字段    
			dmsToDataStmt = conn.prepareStatement(" update Fc_Dms_Ob_To_Data d  set d.owner_sex= (SELECT a.code  FROM "+
				      			      " (SELECT code,code_name,row_number() over (partition by code_name   order by import_date DESC) AS NUM FROM Fc_Dms_Dictionary y " +
				      			      " where 1=1  and y.type = '1006' )a "+ 
				      			      " WHERE a.num=1 and a.code_name like   nvl(d.owner_sex,'ABC')||'%' ) " +
				      			      " WHERE EXISTS (SELECT 1 FROM (SELECT code,code_name,row_number() over (partition by code_name "+ 
				      			      " order by import_date DESC) AS NUM "+
				      			      " FROM Fc_Dms_Dictionary y "+
				      			      " where 1=1  and y.type = '1006')a  "+
				      			      " WHERE a.num=1 and a.code_name like   nvl(d.owner_sex,'ABC')||'%') and  d.return_date = '"+createDate+"' ");
			dmsToDataStmt.addBatch();// 加入批处理
			dmsToDataStmt.executeBatch();// 执行批处理
			
			//处理车主更新后性别字段  
			dmsToDataStmt = conn.prepareStatement(" update Fc_Dms_Ob_To_Data d  set d.uowner_sex= (SELECT a.code  FROM "+
	      			      			      " (SELECT code,code_name,row_number() over (partition by code_name   order by import_date DESC) AS NUM FROM Fc_Dms_Dictionary y " +
	      			      			      " where 1=1  and y.type = '1006'  )a "+ 
	      			      			      " WHERE a.num=1 and a.code_name like   nvl(d.uowner_sex,'ABC')||'%' ) "+
	      			      			      " WHERE EXISTS (SELECT 1 FROM (SELECT code,code_name,row_number() over (partition by code_name "+ 
	      			      			      " order by import_date DESC) AS NUM "+
	      			      			      " FROM Fc_Dms_Dictionary y "+
	      			      			      " where 1=1  and y.type = '1006')a  "+
	      			      			      " WHERE a.num=1 and a.code_name like   nvl(d.uowner_sex,'ABC')||'%') and  d.return_date = '"+createDate+"' ");
			dmsToDataStmt.addBatch();// 加入批处理
			dmsToDataStmt.executeBatch();// 执行批处理
			
			
			//处理联系人性别字段 
			dmsToDataStmt = conn.prepareStatement(" update Fc_Dms_Ob_To_Data d  set d.contact_sex= (SELECT a.code  FROM "+
	      			      			      " (SELECT code,code_name,row_number() over (partition by code_name   order by import_date DESC) AS NUM FROM Fc_Dms_Dictionary y " +
	      			      			      " where 1=1  and y.type = '1006'  )a "+ 
	      			      			      " WHERE a.num=1 and a.code_name like   nvl(d.contact_sex,'ABC')||'%' )  "+
	      			      			      " WHERE EXISTS (SELECT 1 FROM (SELECT code,code_name,row_number() over (partition by code_name "+ 
	      			      			      " order by import_date DESC) AS NUM "+
	      			      			      " FROM Fc_Dms_Dictionary y "+
	      			      			      " where 1=1  and y.type = '1006')a  "+
	      			      			      " WHERE a.num=1 and a.code_name like   nvl(d.contact_sex,'ABC')||'%') and  d.return_date = '"+createDate+"' ");
			dmsToDataStmt.addBatch();// 加入批处理
			dmsToDataStmt.executeBatch();// 执行批处理
			
			
			//处理联系人更新后性别字段  
			dmsToDataStmt = conn.prepareStatement(" update Fc_Dms_Ob_To_Data d  set d.ucontact_sex= (SELECT a.code  FROM "+
							      " (SELECT code,code_name,row_number() over (partition by code_name   order by import_date DESC) AS NUM FROM Fc_Dms_Dictionary y " +
							      " where 1=1  and y.type = '1006'  )a "+ 
							      " WHERE a.num=1 and a.code_name like   nvl(d.ucontact_sex,'ABC')||'%' ) "+
							      " WHERE EXISTS (SELECT 1 FROM (SELECT code,code_name,row_number() over (partition by code_name "+ 
	      			      			      " order by import_date DESC) AS NUM "+
	      			      			      " FROM Fc_Dms_Dictionary y "+
	      			      			      " where 1=1  and y.type = '1006')a  "+
	      			      			      " WHERE a.num=1 and a.code_name like   nvl(d.ucontact_sex,'ABC')||'%') and  d.return_date = '"+createDate+"' ");
			dmsToDataStmt.addBatch();// 加入批处理
			dmsToDataStmt.executeBatch();// 执行批处理
			
			conn.commit();
		} catch (Exception e) {
			log.error("[更新OB后数据将城市及性别转换为代码]出现异常",e);
			conn.rollback();
			throw e;
		}finally{
			if(dmsToDataStmt!=null){
				dmsToDataStmt.close();
			}
			DBConnection.closeConnection();
		}
		log.info("[更新OB后数据将城市及性别转换为代码]结束");
	}
	public static void main(String[] args) {
		GetCallCenterDataDao dao=new GetCallCenterDataDao();
		try {
			dao.updateObToData("20140411");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}

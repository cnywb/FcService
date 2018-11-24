package com.fc.dao.db.businessDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.fc.bean.M2FServiceLogBean;
import com.fc.bean.M2FToCcDataBean;
import com.fc.bean.M2FUserDataBean;
import com.fc.dao.conn.DBConnection;

/**
 * 接收CallCenter售前数据DAO
 */
public class GetCCPreSellDataDao {
	private static Connection conn = null;
	private static Logger log = Logger.getLogger(GetCCPreSellDataDao.class);
	
	/**
	 * 更新售前全局日志
	 */
	public void updateM2ServiceLog(M2FServiceLogBean m2Bean) throws Exception {
		PreparedStatement dmsToDataStmt = null;
		try {
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
			log.info("[更新售前全局日志]end");
		} catch (Exception e) {
			log.error("更新售前全局日志出现异常", e);
			conn.rollback();
		} finally {
			if (dmsToDataStmt != null) {
				dmsToDataStmt.close();
			}
			DBConnection.closeConnection();
		}
	}
	/**
	 * 根据ID查询是否存在售前数据
	 * 
	 * @return
	 */
	public Integer getPreSellCountById(String DID) throws Exception {
		PreparedStatement dmsToDataStmt = null;
		int tmepNumber=0;
		try {
			conn = DBConnection.getConnection();
			String sql = "select count(*) from FC_M2F_CALLCENTER_USERDATA da where da.DID="+DID+"";
			dmsToDataStmt=conn.prepareStatement(sql);
			ResultSet res = dmsToDataStmt.executeQuery();
			if(res!=null){
				while(res.next()){
					tmepNumber=res.getInt(1);
				}
			}
		} catch (Exception e) {
			log.error("[根据ID查询是否存在售前数据]异常！", e);
		}finally {
			if (dmsToDataStmt != null) {
				dmsToDataStmt.close();
			}
			DBConnection.closeConnection();
		}
		return tmepNumber;
	}
	/**
	 * 插入CallCenter返回的售前数据
	 * @throws Exception
	 */
	public void insertPreSell(List<M2FUserDataBean>  mList)throws Exception{
		PreparedStatement dmsToDataStmt = null;
		try {
			if (mList != null && mList.size() > 0) {
				log.info("[插入CC返回的售前数据]开始");
				conn = DBConnection.getConnection();
				conn.setAutoCommit(false);
				String sql = "INSERT INTO FC_M2F_CALLCENTER_USERDATA VALUES(?,sysdate,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				dmsToDataStmt = conn.prepareStatement(sql);
				for (M2FUserDataBean m : mList) {
					if(m.getIsHistory()==0){
						dmsToDataStmt.setInt(1, Integer.parseInt(m.getLocal_to_dealer_id()));
						dmsToDataStmt.setString(2,m.getName());
						dmsToDataStmt.setString(3,m.getGender());
						dmsToDataStmt.setString(4,m.getMobile());
						dmsToDataStmt.setString(5,m.getTel());
						dmsToDataStmt.setString(6,m.getEmail1());
						dmsToDataStmt.setString(7,m.getEmail2());
						dmsToDataStmt.setString(8,m.getInterested());
						dmsToDataStmt.setString(9,m.getBoxNum());
						dmsToDataStmt.setString(10,m.getModel());
						dmsToDataStmt.setString(11,m.getBuyCarTime());
						dmsToDataStmt.setString(12,m.getProvince());
						dmsToDataStmt.setString(13,m.getCity());
						dmsToDataStmt.setString(14,m.getAddress());
						dmsToDataStmt.setString(15,m.getPostcode());
						dmsToDataStmt.setString(16,m.getBooking());
						dmsToDataStmt.setString(17,m.getDealerName());
						dmsToDataStmt.setString(18,m.getDealerCode());
						dmsToDataStmt.setString(19,m.getTestDriveTime());
						dmsToDataStmt.setString(20,m.getHasDriveLicense());
						dmsToDataStmt.setString(21,m.getBudgetCar());
						dmsToDataStmt.setString(22,m.getHasCarBrand());
						dmsToDataStmt.setString(23,m.getCarTime());
						dmsToDataStmt.setString(24,m.getSource());
						dmsToDataStmt.setString(25,m.getFriContact());
						dmsToDataStmt.setString(26,m.getSecContact());
						dmsToDataStmt.setString(27,m.getOtherInterested());
						dmsToDataStmt.setString(28,m.getOtherBrandPropaganda());
						dmsToDataStmt.setString(29,m.getEventCode());
						dmsToDataStmt.setString(30,m.getAge());
						dmsToDataStmt.setString(31,m.getIsMarry());
						dmsToDataStmt.setString(32,m.getBackground());
						dmsToDataStmt.setString(33,m.getIndustry());
						dmsToDataStmt.setString(34,m.getProfession());
						dmsToDataStmt.setString(35,m.getIncome());
						dmsToDataStmt.setString(36,m.getMemo1());
						dmsToDataStmt.setString(37,m.getMemo4());
						dmsToDataStmt.setString(38,m.getMemo5());
						dmsToDataStmt.setString(39,m.getMemo6());
						dmsToDataStmt.setString(40,m.getMemo7());
						dmsToDataStmt.setString(41,m.getMemo8());
						dmsToDataStmt.setString(42,m.getMemo9());
						dmsToDataStmt.setString(43,m.getMemo10());
						dmsToDataStmt.addBatch();// 加入批处理
					}
				}
				dmsToDataStmt.executeBatch();
				conn.commit();
			}
		} catch (Exception e) {
			log.error("[插入CC返回的售前数据]异常", e);
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
	 * 根据ID查询是否存在售前数据
	 * FC_M2F_CCTOFC
	 * @return
	 */
	public Integer getPreSellCountById_New(String DID) throws Exception {
		PreparedStatement dmsToDataStmt = null;
		int tmepNumber=0;
		try {
			conn = DBConnection.getConnection();
			String sql = "select count(*) from FC_M2F_CCTOFC da where da.DATA_UID='"+DID+"'";
			dmsToDataStmt=conn.prepareStatement(sql);
			ResultSet res = dmsToDataStmt.executeQuery();
			if(res!=null){
				while(res.next()){
					tmepNumber=res.getInt(1);
				}
			}
		} catch (Exception e) {
			log.error("[根据ID查询是否存在售前数据new dao]异常！", e);
		}finally {
			if (dmsToDataStmt != null) {
				dmsToDataStmt.close();
			}
			DBConnection.closeConnection();
		}
		return tmepNumber;
	}
	/**
	 * 插入CallCenter返回的售前数据
	 * @throws Exception
	 */
	public void insertPreSellNew(List<M2FToCcDataBean>  mList)throws Exception{
		PreparedStatement dmsToDataStmt = null;
		try {
			if (mList != null && mList.size() > 0) {
				log.info("[插入CC返回的售前数据]开始");
				conn = DBConnection.getConnection();
				conn.setAutoCommit(false);
				String sql = "INSERT INTO FC_M2F_CCTOFC_TEMP VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,'')";
				dmsToDataStmt = conn.prepareStatement(sql);
				for (M2FToCcDataBean m : mList) {
					//if(!"1".equals(m.getC_ext1())){
					    DateFormat formaty = new SimpleDateFormat("yyyyMMdd");
						String createdate = formaty.format(new Date());
						dmsToDataStmt.setString(1,m.getData_dist_date());
						dmsToDataStmt.setString(2,m.getData_priority());
						dmsToDataStmt.setString(3,m.getName());
						dmsToDataStmt.setString(4,m.getSex());
						dmsToDataStmt.setString(5,m.getMobile());
						dmsToDataStmt.setString(6,m.getMobile2());
						dmsToDataStmt.setString(7,m.getP_tel());
						dmsToDataStmt.setString(8,m.getPhone());
						dmsToDataStmt.setString(9,m.getEmail());
						dmsToDataStmt.setString(10,m.getProvince());
						dmsToDataStmt.setString(11,m.getCity());
						dmsToDataStmt.setString(12,m.getClient_source());
						dmsToDataStmt.setString(13,m.getLike_model());
						dmsToDataStmt.setString(14,m.getJh_car_time());
						dmsToDataStmt.setString(15,m.getClient_demand());
						dmsToDataStmt.setString(16,m.getManame());
						dmsToDataStmt.setString(17,m.getDealer_code());
						dmsToDataStmt.setString(18,m.getDealer_region());
						dmsToDataStmt.setString(19,m.getDealer_community());
						dmsToDataStmt.setString(20,m.getDealer_name());
						dmsToDataStmt.setString(21,m.getSell_id());
						dmsToDataStmt.setString(22,m.getImport_code());
						dmsToDataStmt.setString(23,m.getData_uid());
						dmsToDataStmt.setString(24,m.getSenddate());
						dmsToDataStmt.setString(25,createdate);
						dmsToDataStmt.setString(26,m.getLocal_to_dealer_id());
						dmsToDataStmt.setString(27,m.getC_jobtitle());
						dmsToDataStmt.setString(28,m.getC_nationalid());
						dmsToDataStmt.setString(29,m.getC_dateofbirth());
						dmsToDataStmt.setString(30,m.getC_maritalstatus());
						dmsToDataStmt.setString(31,m.getC_monthlyhouserholdincome());
						dmsToDataStmt.setString(32,m.getC_ext1());
						dmsToDataStmt.setString(33,m.getC_ext2());
						dmsToDataStmt.setString(34,m.getC_ext3());
						dmsToDataStmt.setString(35,m.getC_ext4());
						dmsToDataStmt.setString(36,m.getC_ext5());
						dmsToDataStmt.setString(37,m.getC_ext6());
						dmsToDataStmt.setString(38,m.getC_ext7());
						dmsToDataStmt.setString(39,m.getC_ext8());
						dmsToDataStmt.addBatch();// 加入批处理
						
						
						
						
						
					//}
				}
				dmsToDataStmt.executeBatch();
				conn.commit();
			}
		} catch (Exception e) {
			log.error("[插入CC返回的售前数据]异常", e);
			conn.rollback();
			throw e;
		} finally {
			if (dmsToDataStmt != null) {
				dmsToDataStmt.close();
			}
			DBConnection.closeConnection();
		}
	}
	//update FC_M2F_CCTOFC_TEMP 的 dup_error
	public void updateFcM2fCctofcTemp() throws SQLException{
	    PreparedStatement dmsToDataStmt = null;
		try {
			log.info("[更新售前全局日志]开始");
			conn = DBConnection.getConnection();
			conn.setAutoCommit(false);
			String sql = "UPDATE FC_M2F_CCTOFC_TEMP  t SET  dup_error=? where  t.name is not null and t.mobile is not null and t.jh_car_time is not null and t.like_model is not null and t.city is not null and t.dealer_code is not null";
			dmsToDataStmt = conn.prepareStatement(sql);
			dmsToDataStmt.setString(1, "1");
			dmsToDataStmt.addBatch();
			dmsToDataStmt.executeBatch();
			conn.commit();
		} catch (Exception e) {
			log.error("更新售前全局日志出现异常", e);
			conn.rollback();
		} finally {
			if (dmsToDataStmt != null) {
				dmsToDataStmt.close();
			}
			DBConnection.closeConnection();
		}
	}
	
	/**更新PROC_FC_M2F_CCTOFC_TEMP表数据*/
        public void updateCctofcTemp()throws Exception{
            
            log.info("[更新数据PROC_FC_M2F_CCTOFC_TEMP表数据]开始");
		PreparedStatement dmsToDataStmt = null;
		try {
			conn = DBConnection.getConnection();
			conn.setAutoCommit(false);
			dmsToDataStmt =conn.prepareCall("{call PROC_FC_M2F_CCTOFC_TEMP()}"); 
			dmsToDataStmt.addBatch();// 加入批处理
			dmsToDataStmt.executeBatch();// 执行批处理
			conn.commit();
		}catch (Exception e) {
			log.error("[更新数据PROC_FC_M2F_CCTOFC_TEMP表数据]出现异常",e);
			conn.rollback();
			throw e;
		}finally{
			if(dmsToDataStmt!=null){
				dmsToDataStmt.close();
			}
			DBConnection.closeConnection();
		}
        }
}

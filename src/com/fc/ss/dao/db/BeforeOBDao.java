package com.fc.ss.dao.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.fc.dao.conn.DBConnection;
import com.fc.ss.bean.FcBeforeOBBean;


/**
 * 保存DMS所获取数据
 * 
 * @author lq
 * 
 */
public class BeforeOBDao {
	private static Logger log = Logger.getLogger(BeforeOBDao.class);
//	private List list = new ArrayList();

	/**查询售前dms数据总数*/
	public Integer  queryCount(String startTime,String endTime) throws SQLException{
	    log.info("[查询BeforeOB数据总数 new dao]开始");
		PreparedStatement dmsToDataStmt = null;
		Connection conn=null;
		Integer resNumber = 0;
		try {
			conn = DBConnection.getConnection();
			conn.setAutoCommit(false);
//			String sql = "select count(*) from FC_SS_BEFOREOB g where to_date(g.IMPORT_DATE,'yyyy-mm-dd hh24:mi:ss')>=to_date('"+startTime+"','yyyy-MM-dd HH24:mi:ss') and to_date(g.IMPORT_DATE,'yyyy-mm-dd hh24:mi:ss')<=to_date('"+endTime+"','yyyy-MM-dd HH24:mi:ss')";
			String sql = "select count(*) from FC_SS_BEFOREOB g where to_char(to_date(g.IMPORT_DATE,'yyyy-MM-dd hh24:mi:ss'),'yyyymmddhh24') between to_char(sysdate-1/24,'yyyymmddhh24') and to_char(sysdate,'yyyymmddhh24')";
//			to_char(to_date(g.IMPORT_DATE,'yyyy-MM-dd hh24:mi:ss'),'yyyy-MM-dd hh24:mi:ss') between to_char(to_date('2016/2/26 18:00:00','yyyy-MM-dd hh24:mi:ss'),'yyyy-MM-dd hh24') and to_char(to_date('2016/2/26 18:59:59','yyyy-MM-dd hh24:mi:ss'),'yyyy-MM-dd hh24:mi:ss')
//			to_date(g.IMPORT_DATE,'yyyy-MM-dd hh24:mi:ss') between to_date('2016/2/26 18:00:00','yyyy-MM-dd hh24:mi:ss') and to_date('2016/2/26 18:59:59','yyyy-MM-dd hh24:mi:ss')
			dmsToDataStmt = conn.prepareStatement(sql);
			ResultSet res = dmsToDataStmt.executeQuery();
			if (res != null) {
				while (res.next()) {
					resNumber = res.getInt(1);
				}
			}
			conn.commit();
		} catch (Exception e) {
			log.error("[查询BeforeOB数据总数 new dao]异常！", e);
			conn.rollback();
		} finally {
			if (dmsToDataStmt != null) {
				dmsToDataStmt.close();
			}
			//DBConnection.closeConnection();
			if (conn != null) {
			    try {
				conn.close();
			    } catch (SQLException e) {
				e.printStackTrace();
			    }
			}
		}
		return resNumber;
	}
	
	/**查询上传至dms的售前数据
	 * @throws SQLException */
//	public PageBean  queryPage(int firstSize,int maxSize,String importDate) throws SQLException{
//	    log.info("[查询BeforeOB数据]开始");
//		PreparedStatement dmsToDataStmt = null;
//		Connection conn=null;
//		try {
//			conn = DBConnection.getConnection();
//			conn.setAutoCommit(false);
//			StringBuffer sqlB= new StringBuffer();
//			sqlB.append("  select b.* from  ");
//			sqlB.append(" (select g.*,row_number() OVER (ORDER BY g.CREATE_DATE) AS NUM ");
//			sqlB.append("  from FC_SS_BEFOREOB g  where (g.PROCESSED_FLAG=0 or g.PROCESSED_FLAG is null)) b where num <=" + maxSize);
//			dmsToDataStmt = conn.prepareStatement(sqlB.toString(),ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
//		        ResultSet rs = dmsToDataStmt.executeQuery();  
//		        List<FcBeforeOBBean> list = new ArrayList<FcBeforeOBBean>();
//		        PageBean pageBean = new PageBean(firstSize,maxSize, rs);//第几页, 每页数据行数, 可滚动的结果集
//		      
//		        List<FcBeforeOBBean> bL=     this.settingDates(rs,maxSize);
//		        pageBean.setDataList(bL);
//
//		        return pageBean;
//		}catch(Exception e){
//			log.error("[查询BeforeOB数据]出现异常",e);
//			conn.rollback();
//		}finally{
//			if(dmsToDataStmt!=null){
//				dmsToDataStmt.close();
//			}
//			//DBConnection.closeConnection();
//			if (conn != null) {
//			    try {
//				conn.close();
//			    } catch (SQLException e) {
//				e.printStackTrace();
//			    }
//			}
//		}
//		return null;
//	}
	
	public List<FcBeforeOBBean>  queryPage(String startTime, String endTime) throws SQLException{
	    log.info("[查询BeforeOB数据]开始");
		PreparedStatement dmsToDataStmt = null;
		Connection conn=null;
		try {
			log.info("StartTime："+startTime+"EndTime："+endTime);
			conn = DBConnection.getConnection();
			conn.setAutoCommit(false);
			StringBuffer sqlB= new StringBuffer();
			sqlB.append("  select b.* from FC_SS_BEFOREOB b left join FC_SS_IDS d on b.id=d.id where b.SAP_FLAG = '0' and (to_date(b.IMPORT_DATE,'yyyy-MM-dd hh24:mi:ss') < to_date('"+startTime+"','yyyy-MM-dd hh24:mi:ss')) and d.id is null");
//			sqlB.append(" select b.* from FC_SS_BEFOREOB b ");
			dmsToDataStmt = conn.prepareStatement(sqlB.toString(),ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
	        ResultSet rs = dmsToDataStmt.executeQuery();  		      
	        List<FcBeforeOBBean> bL = this.settingDates(rs);
	        return bL;
		}catch(Exception e){
			log.error("[查询BeforeOB数据]出现异常",e);
			conn.rollback();
		}finally{
			log.info("[查询BeforeOB数据]结束");
			if(dmsToDataStmt!=null){
				dmsToDataStmt.close();
			}
			if (conn != null) {
			    try {
				conn.close();
			    } catch (SQLException e) {
				e.printStackTrace();
			    }
			}
		}
		return null;
	}
	
//	public List<FcBeforeOBBean> settingDates(ResultSet res,int maxSize) throws SQLException{
	public List<FcBeforeOBBean> settingDates(ResultSet res) throws SQLException{
	    List<FcBeforeOBBean> uplist=new ArrayList<FcBeforeOBBean>();
	    
	    SimpleDateFormat dfs = new SimpleDateFormat("yyyyMMddHHmmss");
	    
//	    int count = 0;
	        while(res.next()) {
//	            count++;
//	            if(count > maxSize) {
//
//	                break;
//
//	            }
	        FcBeforeOBBean  preBean=new FcBeforeOBBean();
			preBean.setId(res.getLong(1));
            preBean.setCampaignName_CHI(res.getString(2)==null?"":res.getString(2));
            preBean.setCampaignName_ENG(res.getString(3)==null?"":res.getString(3));
            preBean.setReceive_Date(res.getString(4)==null?"":dfs.format(res.getDate(4)));
            preBean.setCreate_Date(res.getString(5)==null?"":res.getString(5));
            preBean.setGender(res.getString(6)==null?"":res.getString(6));
            preBean.setName(res.getString(7)==null?"":res.getString(7));
            preBean.setJob_Title(res.getString(8)==null?"":res.getString(8));
            preBean.setAgeRange(res.getString(9)==null?"":res.getString(9));
            preBean.setDateOfBirth(res.getDate(10));
            preBean.setEmail(res.getString(11)==null?"":res.getString(11));
            preBean.setHomePhone(res.getString(12)==null?"":res.getString(12));
            preBean.setMobile(res.getString(13)==null?"":res.getString(13));
            preBean.setMobile_2(res.getString(14)==null?"":res.getString(14));
            preBean.setTelephone(res.getString(15)==null?"":res.getString(15));
            preBean.setAddress(res.getString(16)==null?"":res.getString(16));
            preBean.setCity(res.getString(17)==null?"":res.getString(17));
            preBean.setProvince(res.getString(18)==null?"":res.getString(18));
            preBean.setZipCode(res.getString(19)==null?"":res.getString(19));
            preBean.setPrimary_CommunicationPreference(res.getString(20)==null?"":res.getString(20));
            preBean.setSecondary_CommunicationPreference(res.getString(21)==null?"":res.getString(21));
//            preBean.setCustomerId(res.getString(22));
            preBean.setOccupation(res.getString(23)==null?"":res.getString(23));
            preBean.setCurrent_VehicleOwned(res.getString(24)==null?"":res.getString(24));
            preBean.setCurrent_DrivingLicense(res.getString(25)==null?"":res.getString(25));
            preBean.setCurrent_VehicleModel(res.getString(26)==null?"":res.getString(26));
            preBean.setCurrent_VehicleModelList(res.getString(27)==null?"":res.getString(27));
            preBean.setCurVinYear(res.getString(28)==null?"":res.getString(28));
            preBean.setMaritalStatus(res.getString(29)==null?"":res.getString(29));
            preBean.setNoChild(res.getString(30)==null?"":res.getString(30));
            preBean.setMonthlyHouseholdIncome(res.getString(31)==null?"":res.getString(31));
            preBean.setEducationLevel(res.getString(32)==null?"":res.getString(32));
            preBean.setInterested_Car_Model(res.getString(33)==null?"":res.getString(33));
            preBean.setInterested_Car_Model_Detail(res.getString(34)==null?"":res.getString(34));
            preBean.setBudget_Range(res.getString(35)==null?"":res.getString(35));
            preBean.setEstimate_BuyCarTime(res.getString(36)==null?"":res.getString(36));
            preBean.setLocal_To_Dealer_ID(res.getString(37)==null?"":res.getString(37));
            preBean.setMediaSource(res.getString(38)==null?"":res.getString(38));
            preBean.setMemo1(res.getString(39)==null?"":res.getString(39));
            preBean.setMemo2(res.getString(40)==null?"":res.getString(40));
            preBean.setMemo3(res.getString(41)==null?"":res.getString(41));
            preBean.setMemo4(res.getString(42)==null?"":res.getString(42));
            preBean.setMemo5(res.getString(43)==null?"":res.getString(43));
            preBean.setSymbol(res.getString(44)==null?"":res.getString(44));
//            preBean.setIsDownload(res.getString(45));
            preBean.setProcess_Mode(res.getString(46)==null?"":res.getString(46));
            preBean.setValidity_Mark(res.getString(47)==null?"":res.getString(47));
            preBean.setImport_Mode(res.getString(48)==null?"":res.getString(48));
            preBean.setAssign_Dealer_Name(res.getString(49)==null?"":res.getString(49));
            preBean.setAssign_Dealer_Code(res.getString(50)==null?"":res.getString(50));
            preBean.setImport_Date(res.getString(51)==null?"":res.getString(51));
//            preBean.setProcessedFlag(res.getLong(52));
            if(res.getString(54)== null){
            	preBean.setCampaign_Channel_ID(new Long(0));
            }else {
            	preBean.setCampaign_Channel_ID(res.getLong(54));
			}
            
            
			uplist.add(preBean);
	        }
		return uplist;
	
	}
	
	public void updateSapFlag(List<FcBeforeOBBean> list)throws Exception{
		log.info("[updateSapFlag任务执行开始 Before]");
		PreparedStatement stmt=null;
		Connection conn=null;
		conn = DBConnection.getConnection();
		conn.setAutoCommit(false);
		stmt = conn.prepareStatement("UPDATE FC_SS_BEFOREOB SET SAP_FLAG='1' WHERE ID=?");
		try {
			for(FcBeforeOBBean bean:list){
				stmt.setLong(1, bean.getId());
				stmt.addBatch();
			}
			stmt.executeBatch();
			conn.commit();
		}catch(Exception e){
			log.error("[数据更新时出现异常！更新失败]");
			conn.rollback();
			throw e;
		}finally{
			log.info("[updateSapFlag任务执行完毕,更新数据标识位 SapFlag_Before]");
			if(stmt!=null){
				stmt.close();
			}
			if (conn != null) {
			    try {
				conn.close();
			    } catch (SQLException e) {
				e.printStackTrace();
			    }
			}
		}
	}
	
	public void updateProcessedFlag(int num)throws Exception{
		log.info("[手动执行BeforeOB数据updateProcessedFlag时，senddate]开始");
		PreparedStatement dmsToDataStmt = null;
		Connection conn=null;
		try {
			conn = DBConnection.getConnection();
			conn.setAutoCommit(false);
			dmsToDataStmt = conn.prepareStatement("update FC_SS_BEFOREOB a set PROCESSED_FLAG=1 where CREATE_DATE in(select CREATE_DATE from(select CREATE_DATE,row_number()over(order by CREATE_DATE )rn from FC_SS_BEFOREOB where PROCESSED_FLAG=0 or PROCESSED_FLAG is null)where rn <"+num+")");
			dmsToDataStmt.addBatch();
			dmsToDataStmt.executeBatch();
			conn.commit();
		}catch(Exception e){
			log.error("[手动执行BeforeOB数据updateProcessedFlag时，senddate]出现异常",e);
			conn.rollback();
			throw e;
		}finally{
			log.info("[手动执行BeforeOB数据updateProcessedFlag时，senddate]结束");
			if(dmsToDataStmt!=null){
				dmsToDataStmt.close();
			}
			//DBConnection.closeConnection();
			if (conn != null) {
			    try {
				conn.close();
			    } catch (SQLException e) {
				e.printStackTrace();
			    }
			}
		}
	}
	
	public void insertSapId(List<FcBeforeOBBean> list) throws Exception{
		log.info("SAP_ID数据录入——Before[开始]");
		PreparedStatement dataStmt = null;
		Connection conn=null;
		try {
			conn = DBConnection.getConnection();
			conn.setAutoCommit(false);
			dataStmt = conn.prepareStatement("INSERT INTO FC_SS_IDS (ID) VALUES (?)");
			for(FcBeforeOBBean bean:list){
				dataStmt.setLong(1, bean.getId());
				dataStmt.addBatch();
			}
			dataStmt.executeBatch();
			conn.commit();
		}catch(Exception e){
			log.error("录入SAP_ID时出现异常！"+e);
			conn.rollback();
			throw e;
		}finally{
			log.info("SAP_ID数据录入——Before[结束]");
			if(dataStmt!=null){
				dataStmt.close();
			}
			if (conn != null) {
			    try {
				conn.close();
			    } catch (SQLException e) {
				e.printStackTrace();
			    }
			}
		}
	}
	
	public void insertUpdateFile(String fileName, String tableName, String status)throws Exception{
		log.info("[手动执行BeforeOB数据insertUpdateFile时，senddate]开始");
		PreparedStatement dataStmt = null;
		Connection conn=null;
		try {
			conn = DBConnection.getConnection();
			conn.setAutoCommit(false);
			dataStmt = conn.prepareStatement("INSERT INTO FC_SS_SAPUPLOAD_FILE VALUES(?,?,?,?,?)");
			dataStmt.setString(1, java.util.UUID.randomUUID().toString());
			dataStmt.setString(2, fileName);
			dataStmt.setString(3, tableName);
			dataStmt.setDate(4, new java.sql.Date(new Date().getTime()));
			dataStmt.setString(5, status);
			dataStmt.addBatch();
			dataStmt.executeBatch();
			conn.commit();
		}catch(Exception e){
			log.error("[手动执行BeforeOB数据insertUpdateFile时]出现异常",e);
			conn.rollback();
			throw e;
		}finally{
			log.info("[手动执行BeforeOB数据insertUpdateFile时，senddate]结束");
			if(dataStmt!=null){
				dataStmt.close();
			}
			//DBConnection.closeConnection();
			if (conn != null) {
			    try {
				conn.close();
			    } catch (SQLException e) {
				e.printStackTrace();
			    }
			}
		}
	}
	public int getSeqNextValue(String seqName)throws Exception{
		
		PreparedStatement dataStmt = null;
		Connection conn=null;
		int retVal=0;
		try {
			conn = DBConnection.getConnection();
			dataStmt = conn.prepareStatement("select "+seqName+".nextval from dual");
			ResultSet rs=dataStmt.executeQuery();
			while(rs.next()){
				retVal=rs.getInt(1);
			}
			log.info("获取系列"+seqName+"的值为"+retVal);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(dataStmt!=null){
				dataStmt.close();
			}
			//DBConnection.closeConnection();
			if (conn != null) {
			    try {
				conn.close();
			    } catch (SQLException e) {
				e.printStackTrace();
			    }
			}
		}
		return retVal;
	}
	

}

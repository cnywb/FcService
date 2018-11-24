package com.fc.ss.dao.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;






import org.apache.log4j.Logger;

import com.fc.dao.conn.DBConnection;
import com.fc.ss.bean.FcCampaignMasterBean;
import com.fc.ss.bean.FcSMSBean;
/**
 * 保存DMS所获取数据
 * 
 * @author lq
 * 
 */
public class CampaignMasterDao {
	private static Logger log = Logger.getLogger(CampaignMasterDao.class);
//	private List list = new ArrayList();

	/**查询CampaignMaster需要生成XML上传的数据
	 * @throws SQLException */
	public List<FcCampaignMasterBean>  queryPage(String startTime, String endTime) throws SQLException{
	    log.info("[查询CampaignMaster数据]开始");
		PreparedStatement dmsToDataStmt = null;
		Connection conn=null;
		try {
			conn = DBConnection.getConnection();
			conn.setAutoCommit(false);
			StringBuffer sqlB= new StringBuffer();
			sqlB.append("  select b.* from FC_SS_CAMPAIGNMASTER b where 1 = 1 and b.SAP_FLAG='0'");
			sqlB.append(" and (b.IMPORT_DATE < to_date('"+startTime+"','yyyy-MM-dd hh24:mi:ss') or  b.UPDATE_DATE < to_date('"+startTime+"','yyyy-MM-dd hh24:mi:ss'))");
			
			dmsToDataStmt = conn.prepareStatement(sqlB.toString(),ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
	        ResultSet rs = dmsToDataStmt.executeQuery();  		      
	        List<FcCampaignMasterBean> bL = this.settingData(rs);
	        return bL;
		}catch(Exception e){
			log.error("[查询CampaignMaster数据]出现异常",e);
			conn.rollback();
		}finally{
			log.info("[查询CampaignMaster数据]结束");
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
	
	public List<FcCampaignMasterBean> settingData(ResultSet res) throws SQLException{
	    List<FcCampaignMasterBean> uplist=new ArrayList<FcCampaignMasterBean>();
        while(res.next()) {
	        FcCampaignMasterBean  bean=new FcCampaignMasterBean();
            bean.setCampaign_Master_ID(res.getLong(1));
            bean.setCampaignName_CHI(res.getString(2)==null?"":res.getString(2));
            bean.setCampaignName_ENG(res.getString(3)==null?"":res.getString(3));
            bean.setSource(res.getString(4)==null?"":res.getString(4));
            bean.setCar_Type(res.getString(5)==null?"":res.getString(5));
            bean.setRemark(res.getString(6)==null?"":res.getString(6));
            bean.setMemo1(res.getString(7)==null?"":res.getString(7));
            bean.setMemo2(res.getString(8)==null?"":res.getString(8));
            bean.setImport_Date(res.getDate(9));
            bean.setUpdate_Date(res.getDate(10));
            bean.setCreate_User(res.getString(11)==null?"":res.getString(11));
            bean.setUpdate_User(res.getString(12)==null?"":res.getString(12));
//            bean.setIsDownload(res.getString(13));
//            bean.setDeleteFlag(res.getLong(14));
//            bean.setIsValid(res.getLong(15));
//            bean.setProcessedFlag(res.getLong(16));
			
			uplist.add(bean);
        }
		return uplist;
	
	}
	
	public void updateSapFlag(List<FcCampaignMasterBean> list)throws Exception{
		log.info("[updateSapFlag任务执行开始 Master]");
		PreparedStatement stmt=null;
		Connection conn=null;
		conn = DBConnection.getConnection();
		conn.setAutoCommit(false);
		
		stmt = conn.prepareStatement("UPDATE FC_SS_CAMPAIGNMASTER SET SAP_FLAG='1' WHERE ID=?");

		try {
			for(FcCampaignMasterBean bean:list){
				stmt.setLong(1,bean.getCampaign_Master_ID());
				stmt.addBatch();
			}
			stmt.executeBatch();
			conn.commit();
		}catch(Exception e){
			log.error("[数据更新时出现异常！更新失败]");
			conn.rollback();
			throw e;
		}
		finally{
			log.info("[定时任务执行完毕,更新数据标识位 SapFlag_Master]");
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
		log.info("[手动执行CampaignMaster数据updateProcessedFlag时]开始");
		PreparedStatement dmsToDataStmt = null;
		Connection conn=null;
		try {
			conn = DBConnection.getConnection();
			conn.setAutoCommit(false);
			dmsToDataStmt = conn.prepareStatement("update FC_SS_CAMPAIGNMASTER a set PROCESSED_FLAG=1 where id in(select id from(select id,row_number()over(order by id )rn from FC_SS_CAMPAIGNMASTER where PROCESSED_FLAG=0 or PROCESSED_FLAG is null)where rn <"+num+")");
			dmsToDataStmt.addBatch();
			dmsToDataStmt.executeBatch();
			conn.commit();
		}catch(Exception e){
			log.error("[手动执行CampaignMaster数据updateProcessedFlag时]出现异常",e);
			conn.rollback();
			throw e;
		}finally{
			log.info("[手动执行CampaignMaster数据updateProcessedFlag时]结束");
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
	
	public void insertUpdateFile(String fileName, String tableName, String status)throws Exception{
		log.info("[手动执行CampaignMaster数据insertUpdateFile时]开始");
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
			log.error("[手动执行CampaignMaster数据insertUpdateFile时]出现异常",e);
			conn.rollback();
			throw e;
		}finally{
			log.info("[手动执行CampaignMaster数据insertUpdateFile时]结束");
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
	
}

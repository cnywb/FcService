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
import com.fc.ss.bean.FcSMSBean;

/**
 * 保存DMS所获取数据
 * 
 * @author lq
 * 
 */
public class SMSDao {
	private static Logger log = Logger.getLogger(SMSDao.class);

	/**查询SMS需要生成XML上传的数据
	 * @throws SQLException */
	public List<FcSMSBean>  queryPage(String startTime, String endTime) throws SQLException{
	    log.info("[查询FC_M2F_MOBILE_SMS数据]开始");
		PreparedStatement dmsToDataStmt = null;
		Connection conn=null;
		try {
			
			conn = DBConnection.getConnection();
			conn.setAutoCommit(false);
			StringBuffer sqlB= new StringBuffer();
			
			sqlB.append(" select ");
			sqlB.append(" b.mid, b.mobile, b.status, b.sendTime, b.sendStatus, b.replyTime, b.replycontent, b.remark, b.msgid, b.wgcode, b.receivedate, b.statusdesc, b.systemdate, b.local_to_dealer_id ");
			sqlB.append(" from FC_M2F_MOBILE_SMS b ");
			sqlB.append(" where b.status = 1 and b.replycontent is not null  and b.sap_flag='0'");
			sqlB.append(" ");
			
//			sqlB.append("  select b.mid, b.mobile, b.status, b.sendTime, b.sendStatus, b.replyTime, b.replycontent, b.remark, b.msgid, b.wgcode, b.receivedate, b.statusdesc, b.systemdate, b.local_to_dealer_id from FC_M2F_MOBILE_SMS b where b.status = 1 and b.local_to_dealer_id in ");
//			sqlB.append("  (select fsb.local_to_dealer_id from FC_SS_BEFOREOB fsb where to_date(fsb.IMPORT_DATE,'yyyy-MM-dd hh24:mi:ss') between to_date('"+startTime+"','yyyy-MM-dd hh24:mi:ss') and to_date('"+endTime+"','yyyy-MM-dd hh24:mi:ss'))");
//			
//			sqlB.append(" union ");
//			
//			sqlB.append("  select b.mid, b.mobile, b.status, b.sendTime, b.sendStatus, b.replyTime, b.replycontent, b.remark, b.msgid, b.wgcode, b.receivedate, b.statusdesc, b.systemdate, b.local_to_dealer_id from FC_M2F_MOBILE_SMS b where b.status = 1 and b.replycontent is not null and b.local_to_dealer_id in ");
//			sqlB.append("  (select fsb.local_to_dealer_id from FC_SS_BEFOREOB fsb where to_date(fsb.IMPORT_DATE,'yyyy-MM-dd hh24:mi:ss') between to_date(to_char(to_date('"+startTime+"','yyyy-MM-dd hh24:mi:ss') - 1/24, 'yyyy-MM-dd hh24') || ':00:00','yyyy-MM-dd hh24:mi:ss') and to_date(to_char(to_date('"+endTime+"','yyyy-MM-dd hh24:mi:ss') - 1/24, 'yyyy-MM-dd hh24') || ':59:59','yyyy-MM-dd hh24:mi:ss'))");

			//sqlB.append(" select b.* from FC_M2F_MOBILE_SMS b where b.local_to_dealer_id in (select fsb.local_to_dealer_id from FC_SS_BEFOREOB fsb where 1 = 1)");
			
			log.info(sqlB.toString());
			
			dmsToDataStmt = conn.prepareStatement(sqlB.toString(),ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			
	        ResultSet rs = dmsToDataStmt.executeQuery();  		      
	        List<FcSMSBean> bL = this.settingData(rs);
	        
	        return bL;
		}catch(Exception e){
			e.printStackTrace();
			log.error("[查询FC_M2F_MOBILE_SMS数据]出现异常",e);
			conn.rollback();
		}finally{
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
	
	public List<FcSMSBean> settingData(ResultSet res) throws Exception{
		List<FcSMSBean> uplist = null;
		
		try {
				uplist = new ArrayList<FcSMSBean>();
		        while(res.next()) {
		        FcSMSBean bean=new FcSMSBean();
		        bean.setMid(res.getLong(1));
		        bean.setMobile(res.getString(2)==null?"":res.getString(2));
		        bean.setStatus(res.getString(3)==null?"":res.getString(3));
		        bean.setSendTime(res.getString(4)==null?"":res.getString(4));
		        bean.setSendStatus(res.getString(5)==null?"":res.getString(5));
		        bean.setReplyTime(res.getString(6)==null?"":res.getString(6));
		        bean.setReplyContent(res.getString(7)==null?"":res.getString(7));
		        bean.setRemark(res.getString(8)==null?"":res.getString(8));
		        bean.setMsgId(res.getString(9)==null?"":res.getString(9));
		        bean.setWgCode(res.getString(10)==null?"":res.getString(10));
		        bean.setReceiveDate(res.getString(11)==null?"":res.getString(11));
		        bean.setStatusDesc(res.getString(12)==null?"":res.getString(12));
		        bean.setSystemDate(res.getDate(13));
		        bean.setLocalToDealerId(res.getString(14)==null?"":res.getString(14));
				
				uplist.add(bean);
	        }
		}catch(Exception e){
			log.error("[FcSMSBean SettingData]异常",e);
			e.printStackTrace();
			throw e;
		}
	    
		return uplist;
	
	}
	
	public void updateSapFlag(List<FcSMSBean> list)throws Exception{
		int sum=1;
		log.info("[定时任务执行完毕,将数据标识位置为1 SMS]");
		PreparedStatement stmt=null;
		Connection conn=null;
		conn = DBConnection.getConnection();
		conn.setAutoCommit(false);
		
		stmt = conn.prepareStatement("update FC_M2F_MOBILE_SMS f set f.SAP_FLAG='1' where MID=?");
		
		try {
			for(FcSMSBean bean:list){
				stmt.setLong(1,bean.getMid());
				stmt.addBatch();
				
				sum++;
				
				if((sum % 1000 ==0 && sum!=0) || sum == list.size()){
					stmt.executeBatch();
					conn.commit();
					conn.setAutoCommit(false);
					stmt.close();
					stmt = conn.prepareStatement("update FC_M2F_MOBILE_SMS f set f.SAP_FLAG='1' where MID=?");
				}
			}
			stmt.executeBatch();
			conn.commit();
		}catch(Exception e){
			log.error("[数据更新时出现异常！更新失败]");
			conn.rollback();
			throw e;
		}
		finally{
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
		log.info("[手动执行FC_M2F_MOBILE_SMS数据updateProcessedFlag时]开始");
		PreparedStatement dmsToDataStmt = null;
		Connection conn=null;
		try {
			conn = DBConnection.getConnection();
			conn.setAutoCommit(false);
			dmsToDataStmt = conn.prepareStatement("update FC_M2F_MOBILE_SMS a set PROCESSED_FLAG=1 where MID in(select MID from(select MID,row_number()over(order by MID )rn from FC_M2F_MOBILE_SMS where PROCESSED_FLAG=0 or PROCESSED_FLAG is null)where rn <"+num+")");
			dmsToDataStmt.addBatch();
			dmsToDataStmt.executeBatch();
			conn.commit();
		}catch(Exception e){
			log.error("[手动执行FC_M2F_MOBILE_SMS数据updateProcessedFlag时]出现异常",e);
			conn.rollback();
			throw e;
		}finally{
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
	}
	
	public void insertUpdateFile(String fileName, String tableName, String status)throws Exception{
		log.info("[手动执行FC_M2F_MOBILE_SMS数据insertUpdateFile]开始");
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
			log.error("[手动执行FC_M2F_MOBILE_SMS数据insertUpdateFile时]出现异常",e);
			conn.rollback();
			throw e;
		}finally{
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
	
}

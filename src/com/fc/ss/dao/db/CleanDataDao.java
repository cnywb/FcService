package com.fc.ss.dao.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import org.apache.log4j.Logger;

import com.fc.bean.DmsToDataBean;
import com.fc.bean.DmsToErorBean;

import com.fc.dao.conn.DBConnection;
import com.fc.ss.bean.FcPreSalesDataToDMSBean;
import com.fc.util.PageBean;
import com.fc.util.XmlSaveUtil;

/**
 * 保存DMS所获取数据
 * 
 * @author lq
 * 
 */
public class CleanDataDao {
	private static Logger log = Logger.getLogger(CleanDataDao.class);
	private List list = new ArrayList();

	/**查询售前dms数据总数*/
	public Integer  queryCountByFcToDmsPreSell(String importDate) throws SQLException{
	    log.info("[查询FC_SS_PRESALES_DATA_TO_DMS]开始");
		PreparedStatement dmsToDataStmt = null;
		Connection conn=null;
		Integer resNumber = 0;
		try {
			conn = DBConnection.getConnection();
			conn.setAutoCommit(false);
			String sql = "select count(1) from FC_SS_PRESALES_DATA_TO_DMS g where g.PROCESSED_FLAG=0 or g.PROCESSED_FLAG is null";
			dmsToDataStmt = conn.prepareStatement(sql);
			ResultSet res = dmsToDataStmt.executeQuery();
			if (res != null) {
				while (res.next()) {
					resNumber = res.getInt(1);
				}
			}
			conn.commit();
		} catch (Exception e) {
			log.error("[查询FC_SS_PRESALES_DATA_TO_DMS]异常！", e);
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
	public PageBean  queryPageByFcToDmsPreSell(int firstSize,int maxSize,String importDate) throws SQLException{
	    log.info("[生成FC_SS_PRESALES_DATA_TO_DMS的excel文件]开始");
		PreparedStatement dmsToDataStmt = null;
		Connection conn=null;
		try {
			conn = DBConnection.getConnection();
			conn.setAutoCommit(false);
			StringBuffer sqlB= new StringBuffer();
			sqlB.append("  select b.* from  ");
			sqlB.append(" (select g.*,row_number() OVER (ORDER BY g.DATA_UID) AS NUM ");
			sqlB.append("  from FC_SS_PRESALES_DATA_TO_DMS g  where (g.PROCESSED_FLAG=0 or g.PROCESSED_FLAG is null)) b where num <=" + maxSize);
			dmsToDataStmt = conn.prepareStatement(sqlB.toString(),ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			//dmsToDataStmt.setMaxRows(maxSize);  
		        ResultSet rs = dmsToDataStmt.executeQuery();  
		        //rs.absolute(firstSize * maxSize);  
		        List<FcPreSalesDataToDMSBean> list = new ArrayList<FcPreSalesDataToDMSBean>();
		        PageBean pageBean = new PageBean(firstSize,maxSize, rs);//第几页, 每页数据行数, 可滚动的结果集
		      
		        List<FcPreSalesDataToDMSBean> bL=     this.settingDmsPreSellData(rs,maxSize);
		        pageBean.setDataList(bL);

		        return pageBean;
		}catch(Exception e){
			log.error("[生成FC_SS_PRESALES_DATA_TO_DMS的excel文件]出现异常",e);
			conn.rollback();
		}finally{
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
		return null;
	}
	
	public List<FcPreSalesDataToDMSBean> settingDmsPreSellData(ResultSet res,int maxSize) throws SQLException{
	    List<FcPreSalesDataToDMSBean> uplist=new ArrayList<FcPreSalesDataToDMSBean>();
	    int count = 0;
	        while(res.next()) {
	            count++;
	            if(count > maxSize) {

	                break;

	            }
            FcPreSalesDataToDMSBean  preBean=new FcPreSalesDataToDMSBean();
//            preBean.setDataUid(res.getString(1));
            preBean.setDataDistDate(res.getString(2));
            preBean.setDataPriority(res.getString(3));
            preBean.setName(res.getString(4));
            preBean.setSex(res.getString(5));
            preBean.setMobile(res.getString(6));
            preBean.setMobile2(res.getString(7));
            preBean.setpTel(res.getString(8));
            preBean.setPhone(res.getString(9));
            preBean.setEmail(res.getString(10));
            preBean.setProvince(res.getString(11));
            preBean.setCity(res.getString(12));
            preBean.setClientSource(res.getString(13));
            preBean.setLikeModel(res.getString(14));
            preBean.setJhCarTime(res.getString(15));
            preBean.setClientDemand(res.getString(16));
            preBean.setManame(res.getString(17));
            preBean.setDealerCode(res.getString(18));
            preBean.setDealerRegion(res.getString(19));
            preBean.setDealerCommunity(res.getString(20));
            preBean.setDealerName(res.getString(21));
            preBean.setSellId(res.getString(22));
//            preBean.setImportCode(res.getString(23));
//            preBean.setProcessedFlag(res.getLong(24));
			
//			if("男".equals(res.getString(4))){
//			   preBean.setSex("10061001");
//			}else if("女".equals(res.getString(4))){
//			   preBean.setSex("10061002");
//			}else if("未知".equals(res.getString(4))){
//		           preBean.setSex("10061003");
//			}else{
//			   preBean.setSex(res.getString(4) );
//			}
			
			uplist.add(preBean);
	        }
		return uplist;
	
	}
	
	public void updateFcM2fToDmsByProcessedFlag(int num)throws Exception{
		log.info("[手动执行发送给dms的售前数据updateFcM2fToDmsBySendDate时]开始");
		PreparedStatement dmsToDataStmt = null;
		Connection conn=null;
		try {
			conn = DBConnection.getConnection();
			conn.setAutoCommit(false);
			dmsToDataStmt = conn.prepareStatement("update FC_SS_PRESALES_DATA_TO_DMS a set PROCESSED_FLAG=1 where DATA_UID in(select DATA_UID from(select DATA_UID,row_number()over(order by DATA_UID )rn from FC_SS_PRESALES_DATA_TO_DMS where PROCESSED_FLAG=0 or PROCESSED_FLAG is null)where rn <="+num+")");
			dmsToDataStmt.addBatch();
			dmsToDataStmt.executeBatch();
			conn.commit();
		}catch(Exception e){
			log.error("[手动执行发送给dms的售前数据updateFcM2fToDmsBySendDate时]出现异常",e);
			conn.rollback();
			throw e;
		}finally{
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
	
	public void insertUpdateFile(String fileName, String tableName, String createDate, String status)throws Exception{
		log.info("[手动执行发送给dms的售前数据updateFcM2fToDmsBySendDate时，senddate]开始");
		PreparedStatement dataStmt = null;
		Connection conn=null;
		try {
			conn = DBConnection.getConnection();
			conn.setAutoCommit(false);
			dataStmt = conn.prepareStatement("INSERT INTO FC_SS_LMCUPLOAD_FILE VALUES(?,?,?,?,?)");
			dataStmt.setString(1, java.util.UUID.randomUUID().toString());
			dataStmt.setString(2, fileName);
			dataStmt.setString(3, tableName);
			dataStmt.setDate(4, new java.sql.Date(new Date().getTime()));
			dataStmt.setString(5, status);
			dataStmt.addBatch();
			dataStmt.executeBatch();
			conn.commit();
		}catch(Exception e){
			log.error("[手动执行发送给dms的有效数据时，senddate]出现异常",e);
			conn.rollback();
			throw e;
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
	}
	
	/**
	 * 保存DMS获取的相关数据
	 * 
	 * @param dmsToData
	 *            车主信息表
	 * @param dmsToEror
	 *            空错号信息表
	 * @throws Exception
	 */
	public void saveToDmsData(List<DmsToDataBean> dmsToData,
			List<DmsToErorBean> dmsToEror, String fileName) throws Exception {
		PreparedStatement dmsToDataStmt = null;
		  	Connection conn=null;
		try {
				    conn = DBConnection.getConnection();
					if (dmsToData != null && dmsToData.size() > 0) {
						String importDate = null;
						conn.setAutoCommit(false);
						log.info("[保存车主相关数据开始]"+ XmlSaveUtil.formatDate(new Date(), 1));
						dmsToDataStmt =conn.prepareCall("{call PROC_fc_m2f_dataraw()}"); 
						dmsToDataStmt.addBatch();// 加入批处理
						dmsToDataStmt.executeBatch();// 执行批处理
						conn.commit();
					}
			} catch (Exception e) {
					log.error("执行保存数据出现异常", e);
					conn.rollback();
					throw e;
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
	}

	
	
	
}

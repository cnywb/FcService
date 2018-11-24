package com.fc.util; 

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
   * @author qp
   * 用分页形式来封装结果集
   */
  public class PageBean {

 


    private int currentPage = 0;  //当前是第几页

 

    private int rowsPerPage = 0;  //每页多少行数据

 

    private int totalRowCount = 0;  //一共多少行
    private int totalPage = 0;  //一共多少页

    private List dataList = null;  //本页要显示数据的List
    
    /**
     * 注意：ResultSet对象，ResultSet必须是可滚动的数据集，可以通过以下的Statement执行查询。
     * PreparedStatement prep = iConn.prepareStatement(
     *          sql,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY); 
     * 
     * @param pageNum
     * @param rowsNum
     * @param rs
     */
    public PageBean(int pageNum,int rowsNum,ResultSet rs) {
        int totalRowCountTemp = 0;
        try {
           rs.last();
           totalRowCountTemp = rs.getRow();  //总行数
           rs.beforeFirst();


           int startNum = (pageNum - 1) * rowsNum ;
           if(startNum > 0) {
              rs.absolute(startNum);  //定位到请求页的第一条记录
           }
        }catch(SQLException e) {
            e.printStackTrace();
        }


         totalRowCount = totalRowCountTemp;//设置总的行数.
        
         rowsPerPage = rowsNum;//每页显示多少行
        
//         setMaxPage(totalRowCountTemp); //设置总的页数
        
          if (pageNum > totalPage) {
            pageNum = totalPage; //用户输入的页数大于了总的页数
          } else if (pageNum < 1) {
            pageNum = 1; //用户输入的页数小于了 1
          }
          //当前第几页
          currentPage = pageNum;
    }
    
    /**
     *设置第页显示多少行
     */
    public void setRowsPerPage(int rows) {
      rowsPerPage = rows;
    }
    
    public int getRowsPerPage() {
        return rowsPerPage; 
    }

 

   
    /**
     * 设置总页数(根据总行数计算总页数)
     */
    public void setTotalPage(int RowCount) {
        if(RowCount % rowsPerPage == 0) {
            totalPage = RowCount / rowsPerPage;
        } else {
            totalPage = RowCount / rowsPerPage + 1;
        }
    }
    
    public int getTotalPage() {
        return totalPage;
    }    
    
    /**
    * @param 设置总的行数
    * 
    */
    public void setTotalRowCount(int rows) {
       totalRowCount = rows;
    }
    
    public int getTotalRowCount() {
        return  totalRowCount;
    }



  /**
    * @return Returns the dataList.
    */
  public List getDataList() {
    return dataList;
  }


 /**
  * @param dataList The dataList to set.
  */
  public void setDataList(List dataList) {
    this.dataList = dataList;
 }
 
 
}
package com.fc.util;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * excel 导出工具类
 * 
 * @author lq
 * 
 */
public class ExcelExportUtilForDms {
	private Logger log = LoggerFactory.getLogger(ExcelExportUtilForDms.class);
	private static final String SEPARATOR = File.separator;
	private SXSSFWorkbook workBook =null;
	private HSSFWorkbook  hWorkBook=null;
	private HSSFSheet     hsheet;
	private Sheet sheet;
	private List<String> titiles;// excel标题
	private String readFilePah; // 读取路径
	public String fileName; // excel 文件名称
	public String sheetName; // excel 单元名称
	private int alreadyNumber = 0;// 记录已写入总数量
	private int readNumber = 0; // 记录已写入的量
	public File file;
	public String path;
	public int dataCount = 0;
	public int pageData = 1000;
	public int pageIndex = 0;
	public boolean isExport = true;
	public String exportTypeFomat="XLSX";


	public ExcelExportUtilForDms() {
		dataCount = 0;
		pageIndex = 0;
	}

	public void pageIndexNext() {
		pageIndex = pageIndex + 1;
	}

	/**
	 * 分页的开始数量
	 * 
	 * @param pageIndex
	 * @return
	 */
	public int startCount() {
		return pageIndex * pageData;
	}

	/**
	 * 分页的结束数量
	 * 
	 * @param ob
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public int endCount() {
		return (pageIndex * pageData) + pageData;
	}


	public Object getValueByKey(Object ob, String key) throws Exception {
		Field field = ob.getClass().getDeclaredField(key);
		field.setAccessible(true);
		Object obj = field.get(ob);
		return obj;
	}

	/**
	 * 初始化相关参数
	 * @param clazz
	 * @param sheetName
	 */
	private void intWorkbook(Class<?> clazz, String sheetName) {
		if("XLSX".equals(exportTypeFomat)){
			titiles = getTitles(clazz);
			workBook = new SXSSFWorkbook(1000);
			sheet = workBook.createSheet();
			if(fileName==null){
				fileName = getFileName(sheetName, "xlsx");
			}
			if(path==null){
				path = ServletActionContext.getServletContext().getRealPath("WEB-INF")
						+ SEPARATOR + "dms";
				readFilePah = path + SEPARATOR + fileName;
			}else{
				readFilePah=path+fileName;
			}
			
			file = new File(readFilePah);
			if (!file.exists()) {
				file.getParentFile().mkdirs();
			}
		}else{
			titiles = getTitles(clazz);
			hWorkBook=new HSSFWorkbook();
			hsheet = hWorkBook.createSheet();
			if(fileName==null){
				fileName = getFileName(sheetName, "xls");
			}
			if(path==null){
				path = ServletActionContext.getServletContext().getRealPath("WEB-INF")
						+ SEPARATOR + "dms";
				readFilePah = path + SEPARATOR + fileName;
			}else{
				readFilePah=path+fileName;
			}
			
			file = new File(readFilePah);
			if (!file.exists()) {
				file.getParentFile().mkdirs();
			}
		}
	}

	/**
	 * 根据类型对象获取其字段名称
	 * 
	 * @param o
	 * @return
	 */
	public  List<String> getTitles(Class<?> clazz) {
		if (clazz == null)
			return null;
		List<String> result = null;
		Field[] fields = clazz.getDeclaredFields();
		if (fields != null && fields.length > 0) {
			result = new ArrayList<String>();
			for (Field field : fields) {
				if (!field.getName().equals("serialVersionUID"))
					result.add(field.getName());
			}
		}
		return result;
	}
	/**
	 * 根据文件名称以及文件类型和时间生成每天要生成的文件名称
	 * 
	 * @param fileName
	 * @param fileType
	 * @return
	 */
	public  String getFileName(String fileName, String fileType) {
		// String dayStr =
		// ComUtils.formatDate(DateUtils.getPreOrAfterDateByCondition(-1, 0, 0,
		// 0), 5);// 获得前一天年月日时间标识
		return fileName + "." + fileType;
	}
	/**
	 * 导出excel
	 * 
	 * @param clazz
	 * @param sheetName
	 * @param list
	 * @param pageIndex
	 * @param pageCount
	 */
	public void exportExcel(Class<?> clazz, List<?> list) {
		try {
			if (isExport) {
				log.info(sheetName + "工作簿第 " + pageIndex + " 批数据写入，每批数据  "+pageData+" 条");
				if (getPageIndex() == 1) {
					// 初始化实体
					intWorkbook(clazz, sheetName);
					Row writeRow = (Row) sheet.createRow(0);
					// 遍历插入标题
					for (int k = 0; k < titiles.size(); k++) {
						String title = titiles.get(k);
						Cell cell = writeRow.createCell(k);
						cell.setCellValue(title);
					}
				}
				for (Object ob : list) {
					readNumber = readNumber + 1;
					Row row1 = (Row) sheet.createRow(readNumber);
					for (int k = 0; k < titiles.size(); k++) {
						String title = titiles.get(k);
						Cell cell = row1.createCell(k);
						Object obj = getValueByKey(ob, title);
						cell.setCellValue(((obj != null && !"".equals(obj)) ? obj
								.toString() : ""));
					}
				}
				alreadyNumber = alreadyNumber + list.size();
			} else {
			     if(file!=null){
					FileOutputStream stream = new FileOutputStream(file);
					workBook.write(stream);// 最终写入文件
					stream.close();
			     }else{
				 log.error("写入数据至Excel报错", "file is null");
			     }
			}
		} catch (Exception e) {
			log.error("写入数据至Excel报错", e);
		}
	}
	

	/**
	 * 导出excel
	 * 
	 * @param clazz
	 * @param sheetName
	 * @param list
	 * @param pageIndex
	 * @param pageCount
	 */
	public void exportExcel2003(Class<?> clazz, List<?> list) {
		try {
			if (isExport) {
				log.info(sheetName + "工作簿第 " + pageIndex + " 批数据写入，每批数据  "+pageData+" 条");
				if (pageIndex == 1) {
					// 初始化实体
					intWorkbook(clazz, sheetName);
					Row writeRow = (Row) hsheet.createRow(0);
					// 遍历插入标题
					for (int k = 0; k < titiles.size(); k++) {
						String title = titiles.get(k);
						Cell cell = writeRow.createCell(k);
						cell.setCellValue(title);
					}
				}
				for (Object ob : list) {
					readNumber = readNumber + 1;
					Row row1 = (Row) hsheet.createRow(readNumber);
					for (int k = 0; k < titiles.size(); k++) {
						String title = titiles.get(k);
						Cell cell = row1.createCell(k);
						Object obj = getValueByKey(ob, title);
						cell.setCellValue(((obj != null && !"".equals(obj)) ? obj
								.toString() : ""));
					}
				}
				alreadyNumber = alreadyNumber + list.size();
			} else {
					FileOutputStream stream = new FileOutputStream(file);
					hWorkBook.write(stream);// 最终写入文件
					stream.close();
			}
		} catch (Exception e) {
			log.error("写入数据至Excel报错", e);
		}
	}
	
	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
}

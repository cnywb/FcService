package com.fc.util;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import com.fc.bean.DmsToDataBean;
import com.fc.bean.DmsToErorBean;
import com.fc.bean.ExcelBean;
import com.fc.dao.conn.DBConnection;
import com.fc.dao.db.SaveDmsDataDao;
import com.fc.service.ProcessDmsDataService;

/**
 * excel 导出工具类
 * 
 * @author lq
 * 
 */
public class ExcelExportUtil {
	private static Connection conn = null;
	private SXSSFWorkbook workBook;
	private Sheet sheet;
	private String readFilePah; // 读取路径
	private String fileName; // excel 文件名称
	public String sheetName; // excel 单元名称
	private int alreadyNumber = 0;// 记录已写入总数量
	private int readNumber = 0; // 记录已写入的量
	public File file;
	private String path;
	public int dataCount = 0;
	public int pageData = 1000;
	public int pageIndex = 0;
	public boolean isExport = true;
	private Map<String, String> titleMap = new HashMap<String, String>();
	private List<String> titName = new ArrayList<String>();
	private List<String> titleStr = new ArrayList<String>();
	ProcessDmsDataService prodms = new ProcessDmsDataService();
	private static String[] sheetStr = new String[11];
	DateFormat formaty = new SimpleDateFormat("yyyyMMdd");
	private int importSwitch = 0;

	public ExcelExportUtil() {
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
	 * 
	 * @param clazz
	 * @param sheetName
	 */
	private void intWorkbook(String sheetName, String importDate) {
		initMap();
		initTitle();
		workBook = new SXSSFWorkbook(pageData);
		sheet = workBook.createSheet(sheetName);
		fileName = getFileName(importDate + "[测试结果]", "xlsx");
		path = "D:\\PYSYSTEM\\dataTEST\\";
		readFilePah = path + fileName;
		file = new File(readFilePah);
		if (!file.exists()) {
			file.delete();
			file.getParentFile().mkdirs();
		}
	}

	/**
	 *创建工作簿
	 */
	private void createSheet(String sheetName) {
		sheet = workBook.createSheet(sheetName);
	}

	/**
	 * 导出excel
	 * 
	 * @param clazz
	 * @param sheetName
	 * @param list
	 * @param pageIndex
	 * @param pageCount
	 *            //
	 */
	// public void exportExcel(Class<?> clazz, List<?> list) {
	// try {
	// if (isExport) {
	// if (pageIndex == 1) {
	// // 初始化实体
	// intWorkbook(clazz, sheetName);
	// Row writeRow = (Row) sheet.createRow(0);
	// // 遍历插入标题
	// for (int k = 0; k < titiles.size(); k++) {
	// String title = titiles.get(k);
	// Cell cell = writeRow.createCell(k);
	// cell.setCellValue(title);
	// }
	// }
	// for (Object ob : list) {
	// readNumber = readNumber + 1;
	// Row row1 = (Row) sheet.createRow(readNumber);
	// for (int k = 0; k < titiles.size(); k++) {
	// String title = titiles.get(k);
	// Cell cell = row1.createCell(k);
	// Object obj = getValueByKey(ob, title);
	// cell
	// .setCellValue(((obj != null && !"".equals(obj)) ? obj
	// .toString()
	// : ""));
	// }
	// }
	// alreadyNumber = alreadyNumber + list.size();
	// } else {
	// FileOutputStream stream = new FileOutputStream(file);
	// workBook.write(stream);// 最终写入文件
	// stream.close();
	// }
	// } catch (Exception e) {
	// }
	// }

	/**
	 * 根据类型对象获取其字段名称
	 * 
	 * @param o
	 * @return
	 */
	public List<String> getTitles(Class<?> clazz) {
		if (clazz == null)
			return null;
		List<String> result = null;
		Field[] fields = clazz.getDeclaredFields();
		if (fields != null && fields.length > 0) {
			result = new ArrayList<String>();
			for (Field field : fields) {
				if (!field.getName().equals("serialVersionUID")) {
					result.add(titleMap.get(field.getName()));
					titleStr.add(field.getName());
				}
			}
		}
		return result;
	}

	/**
	 * 首次导入添加说明
	 */
	private void exportExcelOne() {
		Row writeRow = (Row) sheet.createRow(0);
		Cell cell1 = writeRow.createCell(0);
		cell1.setCellValue("Sheet代码");
		Cell cell2 = writeRow.createCell(1);
		cell2.setCellValue("Sheet名称");

		for (int i = 0; i < sheetStr.length; i++) {
			Row wrRow = (Row) sheet.createRow((i + 1));
			Cell cel1 = wrRow.createCell(0);
			cel1.setCellValue("Filter" + i);
			Cell cel2 = wrRow.createCell(1);
			cel2.setCellValue(sheetStr[i]);
		}
	}

	/**
	 * 写入文件
	 */
	private void outFile() {
		try {
			FileOutputStream stream = new FileOutputStream(file);
			workBook.write(stream);// 最终写入文件
			stream.close();
		} catch (Exception e) {
			System.out.println("写入文件异常");
			e.printStackTrace();
		}
	}

	/**
	 * 初始化标题
	 */
	private void initTitle() {
		titName = getTitles(ExcelBean.class);
	}

	private void initMap() {
		titleMap.put("VIN", "VIN码");
		titleMap.put("ownerName", "车主姓名");
		titleMap.put("contactorName", "联系人姓名");
		titleMap.put("ownerMobile", "车主手机");
		titleMap.put("ownerPhone", "车主电话");
		titleMap.put("contactorMobile", "联系人手机");
		titleMap.put("contactorPhone", "联系人电话");
		titleMap.put("ownerAddress", "车主地址");
		titleMap.put("contactorAddress", "联系人地址");
		titleMap.put("carTvbDate", "车辆销售时间");
		titleMap.put("dealerCode", "经销商代码");
		titleMap.put("dataMark", "数据标识");
		titleMap.put("isNewOwner", "是否为新车主");
		titleMap.put("obType", "OB类型");
		titleMap.put("CAR_SYNC", "CAR_SYNC");
		titleMap.put("CAR_EA", "CAR_EA");
		sheetStr[0] = "初始数据";
		sheetStr[1] = "清理手机电话特殊字符";
		sheetStr[2] = "地址特殊字符清理";
		sheetStr[3] = "筛选代购数据";
		sheetStr[4] = "校验车主联系人相同的手机";
		sheetStr[5] = "校验手机号码是否正确";
		sheetStr[6] = "校验是否可以OB";
		sheetStr[7] = "拆分新车主或其它车主";
		sheetStr[8] = "校验EA";
		sheetStr[9] = "校验SYNC";
		sheetStr[10] = "最终发送至CallCenter数据";
	}

	/**
	 * 根据文件名称以及文件类型和时间生成每天要生成的文件名称
	 * 
	 * @param fileName
	 * @param fileType
	 * @return
	 */
	public static String getFileName(String fileName, String fileType) {
		// String dayStr =
		// ComUtils.formatDate(DateUtils.getPreOrAfterDateByCondition(-1, 0, 0,
		// 0), 5);// 获得前一天年月日时间标识
		return fileName + "." + fileType;
	}

	/**
	 * 根据类别的字段名称获取字段值并组成字符串数组
	 * 
	 * @param title
	 * @param o
	 * @return
	 * @throws IllegalAccessException
	 * @throws NoSuchFieldException
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 */
	private static String[] getRowByField(Object o, List<String> titles)
			throws Exception {
		String[] result = null;
		if (titles != null && titles.size() > 0) {
			result = new String[titles.size()];
			for (int i = 0; i < titles.size(); i++) {
				String field = titles.get(i);
				if (field.indexOf(".") != -1) {
					String[] fields = field.split("\\.");
					result[i] = getValueWithFiled(fields, o);
				} else {
					result[i] = getValueWithFiled(field, o);
				}
			}
		}
		return result;
	}

	/**
	 * 利用反射根据类的字段名和实例获取该实例的字段值
	 * 
	 * @param field
	 * @param o
	 * @return
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 */
	public static String getValueWithFiled(String fieldName, Object o)
			throws Exception {
		Class<?> clazz = o.getClass();
		Field field = clazz.getDeclaredField(fieldName);
		String result = "";
		if (field != null) {
			field.setAccessible(true);
			Object oValue = field.get(o);
			try {
				if (oValue != null) {
					if (oValue instanceof Date) {
						result = XmlSaveUtil.formatDate((Date) oValue, 0);
					} else {
						if (null != result)
							result = oValue.toString();
					}
				}
			} catch (Exception ex) {
				throw ex;
			}
		}
		return result;
	}

	/**
	 * 利用反射根据类的字段名和实例获取该实例的字段值
	 * 
	 * @param fieldNames
	 * @param o
	 * @return
	 * @throws Exception
	 */
	public static String getValueWithFiled(String[] fieldNames, Object o)
			throws Exception {

		Class<?> clazz = o.getClass();
		System.out.println(fieldNames.length);
		Field field = clazz.getDeclaredField(fieldNames[0]);
		String result = "";
		if (field != null) {
			field.setAccessible(true);
			Object oValue = field.get(o);
			try {
				if (oValue != null && fieldNames.length == 1) {
					if (oValue instanceof Date) {
						result = XmlSaveUtil.formatDate((Date) oValue, 0);
					} else {
						if (null != result)
							result = oValue.toString();
					}
				} else if (oValue != null) {
					String[] fields = new String[fieldNames.length - 1];
					System.arraycopy(fieldNames, 1, fields, 0,
							fieldNames.length - 1);
					result = getValueWithFiled(fields, oValue);
				}
			} catch (Exception ex) {
				throw ex;
			}
		}
		return result;
	}

	/**
	 * 根据车主信息表产生Excel 导出信息表
	 * 
	 * @param dmsData
	 * @return
	 */
	private List<ExcelBean> getExcelBean(List<DmsToDataBean> dmsData,
			String checked, Map<String, String> ma9992,
			Map<String, String> mb9993, List<String> syncList, String X)throws Exception {
		System.out.println("[转换为导出数据]开始");
		List<ExcelBean> ecBList = new ArrayList<ExcelBean>();
		for (DmsToDataBean db : dmsData) {
			if (X == null) {
//				checkedData(checked, db, ma9992, mb9993, syncList);
				ExcelBean ecBean = new ExcelBean();
				ecBean.setVIN(db.getVin());
				ecBean.setOwnerName(db.getOwner_name());
				ecBean.setContactorName(db.getContactor_name());
				ecBean.setOwnerMobile(db.getMobile());
				ecBean.setContactorMobile(db.getContactor_mobile());
				ecBean.setOwnerPhone(db.getPhone());
				ecBean.setContactorPhone(db.getContactor_phone());
				ecBean.setOwnerAddress(db.getAddress());
				ecBean.setContactorAddress(db.getContactor_address());
				ecBean.setCarTvbDate(db.getTvb_create_date());
				ecBean.setDealerCode(db.getDealer_sale_code());
				ecBean.setDataMark(db.getIsObStr());
				ecBean.setIsNewOwner(db.getIsNewOwer());
				ecBean.setObType(db.getObType());
				ecBean.setCAR_SYNC(db.getCarSync());
				ecBean.setCAR_EA(db.getCarEa());
				ecBList.add(ecBean);
			} else {
				if ("N".equals(db.getIsOwnerError())
						&& "X".equals(db.getIsNewOwer())
						&& ("".equals(db.getIsObStr()) || db.getIsObStr() == null)) {
					ExcelBean ecBean = new ExcelBean();
					ecBean.setVIN(db.getVin());
					ecBean.setOwnerName(db.getOwner_name());
					ecBean.setContactorName(db.getContactor_name());
					ecBean.setOwnerMobile(db.getMobile());
					ecBean.setContactorMobile(db.getContactor_mobile());
					ecBean.setOwnerPhone(db.getPhone());
					ecBean.setContactorPhone(db.getContactor_phone());
					ecBean.setOwnerAddress(db.getAddress());
					ecBean.setContactorAddress(db.getContactor_address());
					ecBean.setCarTvbDate(db.getTvb_create_date());
					ecBean.setDealerCode(db.getDealer_sale_code());
					ecBean.setDataMark(db.getIsObStr());
					ecBean.setIsNewOwner(db.getIsNewOwer());
					ecBean.setObType(db.getObType());
					ecBean.setCAR_SYNC(db.getCarSync());
					ecBean.setCAR_EA(db.getCarEa());
					ecBList.add(ecBean);
				}
			}

		}
		return ecBList;
	}

	/**
	 * 返回ExcelBean集合
	 * 
	 * @return
	 */
	public List<DmsToDataBean> getDmsToDataBean(String importDate)throws Exception {
		System.out.println("[查询车主数据]开始");
		List<DmsToDataBean> ecBList = new ArrayList<DmsToDataBean>();
		PreparedStatement dmsToDataStmt = null;
		try {
			conn = DBConnection.getConnection();
			conn.setAutoCommit(false);
			String sql = "SELECT "
					+ "DA.VIN,DA.OWNER_NAME,DA.CONTACTOR_NAME,DA.MOBILE,"
					+ "DA.CONTACTOR_MOBILE,DA.PHONE,DA.CONTACTOR_PHONE,"
					+ "DA.ADDRESS,DA.CONTACTOR_ADDRESS,DA.SALE_DATE,"
					+ "DA.DEALER_SALE_CODE,SERISE,Cat_Code,model  "
					+ "FROM FC_DMS_TO_DATA DA WHERE DA.CREATE_OWNER_DATE='"
					+ importDate + "'  AND DA.SALE_DATE IS NOT NULL";
			dmsToDataStmt = conn.prepareStatement(sql);
			ResultSet res = dmsToDataStmt.executeQuery();
			if (res != null) {
				while (res.next()) {
					DmsToDataBean dmsBean = new DmsToDataBean();
					dmsBean.setVin(res.getString(1));
					dmsBean.setOwner_name(res.getString(2));
					dmsBean.setContactor_name(res.getString(3));
					dmsBean.setMobile(res.getString(4));
					dmsBean.setContactor_mobile(res.getString(5));
					dmsBean.setPhone(res.getString(6));
					dmsBean.setContactor_phone(res.getString(7));
					dmsBean.setAddress(res.getString(8));
					dmsBean.setContactor_address(res.getString(9));
					dmsBean.setTvb_create_date(res.getString(10));
					dmsBean.setDealer_sale_code(res.getString(11));
					dmsBean.setSerise(res.getString(12));
					dmsBean.setCat_code(res.getString(13));
					dmsBean.setModel(res.getString(14));
					ecBList.add(dmsBean);
				}
			}
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if (dmsToDataStmt != null) {
				dmsToDataStmt.close();
			}
			DBConnection.closeConnection();
		}
		return ecBList;
	}

	/**
	 * 插入标题
	 */
	private void insertTitle(String sheetName) {
		importSwitch = 0;
		createSheet(sheetName);
		Row writeRow = (Row) sheet.createRow(importSwitch);
		for (int i = 0; i < titName.size(); i++) {
			String title = titName.get(i);
			Cell cel1 = writeRow.createCell(i);
			cel1.setCellValue(title);
		}
	}

	/**
	 * 插入内容
	 */
	private void insertConcent(List<ExcelBean> excelBean) {
		System.out.println("[写入内容]开始");
		try {
			for (ExcelBean ob : excelBean) {
				importSwitch = importSwitch + 1;
				Row row1 = (Row) sheet.createRow(importSwitch);
				for (int k = 0; k < titleStr.size(); k++) {
					String title = titleStr.get(k);
					Cell cell = row1.createCell(k);
					Object obj = getValueByKey(ob, title);
					cell.setCellValue(((obj != null && !"".equals(obj)) ? obj
							.toString() : ""));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		ExcelExportUtil ut = new ExcelExportUtil();
		SaveDmsDataDao dao = new SaveDmsDataDao();
		String importDate = ut.formaty.format(new Date());
		Map<String, String> ma9992 = null;
		Map<String, String> mb9993 = null;
		List<String> syncList = null;
		List<DmsToDataBean> dmsToData=null;
		String[] filters={"Filter1","Filter2","Filter3","Filter4","Filter5","Filter6","Filter7","Filter8","Filter9","Filter10","Filter11"};
		try {
			syncList = dao.getSync("ea");
			ma9992 = dao.setCXByDmsToData("9992", importDate);
			mb9993 = dao.setCXByDmsToData("9993", importDate);
			dmsToData = ut.getDmsToDataBean(importDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ut.intWorkbook("说明", importDate);
		ut.exportExcelOne();// 添加说明
		try {
			List<ExcelBean>	 ecBean=new ArrayList<ExcelBean>();
			String zt="Q";
				for (DmsToDataBean dt : dmsToData) {
//					ExcelBean ex=checkedData();
				}
			
//		// 初始数据
//		System.out.println("[写入Excel]初始数据开始");
//		ut.insertTitle("Filter0");
//		ut.insertConcent(ut.getExcelBean(dmsToData, "Filter0", ma9992, mb9993,
//				syncList, null));
//		System.out.println("[写入Excel]初始数据结束");
//
//		System.out.println("[写入Excel]清理手机电话特殊字符开始");
//		ut.insertTitle("Filter1");
//		ut.insertConcent(ut.getExcelBean(dmsToData, "Filter1", ma9992, mb9993,
//				syncList, null));
//		System.out.println("[写入Excel]清理手机电话特殊字符结束");
//
//		System.out.println("[写入Excel]清理地址特殊字符开始");
//		ut.insertTitle("Filter2");
//		ut.insertConcent(ut.getExcelBean(dmsToData, "Filter2", ma9992, mb9993,
//				syncList, null));
//		System.out.println("[写入Excel]清理地址特殊字符结束");
//
//		System.out.println("[写入Excel]筛选代购数据开始");
//		ut.insertTitle("Filter3");
//		ut.insertConcent(ut.getExcelBean(dmsToData, "Filter3", ma9992, mb9993,
//				syncList, null));
//		System.out.println("[写入Excel]筛选代购数据结束");
//
//		System.out.println("[写入Excel]校验车主联系人相同的手机开始");
//		ut.insertTitle("Filter4");
//		ut.insertConcent(ut.getExcelBean(dmsToData, "Filter4", ma9992, mb9993,
//				syncList, null));
//		System.out.println("[写入Excel]校验车主联系人相同的手机结束");
//
//		System.out.println("[写入Excel]校验手机电话是否正确开始");
//		ut.insertTitle("Filter5");
//		ut.insertConcent(ut.getExcelBean(dmsToData, "Filter5", ma9992, mb9993,
//				syncList, null));
//		System.out.println("[写入Excel]校验手机电话是否正确结束");
//
//		System.out.println("[写入Excel]校验数据是否可以OB开始");
//		ut.insertTitle("Filter6");
//		ut.insertConcent(ut.getExcelBean(dmsToData, "Filter6", ma9992, mb9993,
//				syncList, null));
//		System.out.println("[写入Excel]校验数据是否可以OB结束");
//
//		System.out.println("[写入Excel]拆分新车主或其它车主开始");
//		ut.insertTitle("Filter7");
//		ut.insertConcent(ut.getExcelBean(dmsToData, "Filter7", ma9992, mb9993,
//				syncList, null));
//		System.out.println("[写入Excel]拆分新车主或其它车主结束");
//
//		System.out.println("[写入Excel]校验EA开始");
//		ut.insertTitle("Filter8");
//		ut.insertConcent(ut.getExcelBean(dmsToData, "Filter8", ma9992, mb9993,
//				syncList, null));
//		System.out.println("[写入Excel]校验EA结束");
//
//		System.out.println("[写入Excel]校验SYNC开始");
//		ut.insertTitle("Filter9");
//		ut.insertConcent(ut.getExcelBean(dmsToData, "Filter9", ma9992, mb9993,
//				syncList, null));
//		System.out.println("[写入Excel]校验SYNC结束");
//
//		System.out.println("[写入Excel]最终数据开始");
//		ut.insertTitle("Filter10");
//		ut.insertConcent(ut.getExcelBean(dmsToData, "Filter10", ma9992, mb9993,
//				syncList, "X"));
//		System.out.println("[写入Excel]最终数据结束");
		
		
		

		ut.outFile();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private ExcelBean checkedData(String createDate,String checked, DmsToDataBean db,
			Map<String, String> ma9992, Map<String, String> mb9993,
			List<String> syncList,List<String> customers,List<String> vins,String isx)throws Exception {
		List<DmsToErorBean> error=new ArrayList<DmsToErorBean>();
		if ("Filter1".equals(checked)) {
//			System.out.println("[筛选程序]拆分新车主或其它车主");
			prodms.dismantleOwner(db, createDate);
		}
		if ("Filter2".equals(checked)) {
//			System.out.println("[筛选程序]剔除不参与拨打的关键字数据");
			prodms.excludingMax(db, customers);
		}
		if ("Filter3".equals(checked)) {
//			System.out.println("[筛选程序]根据VIN码判断提出不参与拨打的大客户信息");
			prodms.excludingMax(db, vins);
		}
		if("X".equals(db.getIsNewOwer())){
			if ("Filter4".equals(checked)){
//				System.out.println("[筛选程序]清理手机电话特殊字符");
				prodms.clearPhoneMobile(db);
			}
			if ("Filter5".equals(checked)){
//				System.out.println("[筛选程序]地址特殊字符清理");
				prodms.addressChecked(db);
			}
			if ("Filter6".equals(checked)){
//				System.out.println("[筛选程序]筛选代购数据");
				prodms.ownerShopping(db);
			}
			if ("Filter7".equals(checked)){
//				System.out.println("[筛选程序]校验车主联系人相同的手机");
				prodms.mobileSame(db);
			}
			if ("Filter8".equals(checked)){
//				System.out.println("[筛选程序]手机校验错误，则加入到空错号信息表中");
				prodms.checkedTel(db,error,"");
			}
			if ("Filter9".equals(checked)){
//				System.out.println("[筛选程序]校验是否可以OB");
				prodms.isObChecked(db);
			}
//			if ("Filter10".equals(checked)){
////				System.out.println("[筛选程序]校验EA");
//				prodms.processEa(db,ma9992,mb9993);
//			}
//			if ("Filter11".equals(checked)){
////				System.out.println("[筛选程序]校验SYNC");
//				prodms.processSync(db,syncList);
//			}
		}
		System.out.println("[筛选程序]结束");
		if("X".equals(isx) &&  !"X".equals(db.getIsNewOwer())){
			return null;
		}
		ExcelBean ecBean = new ExcelBean();
		ecBean.setVIN(db.getVin());
		ecBean.setOwnerName(db.getOwner_name());
		ecBean.setContactorName(db.getContactor_name());
		ecBean.setOwnerMobile(db.getMobile());
		ecBean.setContactorMobile(db.getContactor_mobile());
		ecBean.setOwnerPhone(db.getPhone());
		ecBean.setContactorPhone(db.getContactor_phone());
		ecBean.setOwnerAddress(db.getAddress());
		ecBean.setContactorAddress(db.getContactor_address());
		ecBean.setCarTvbDate(db.getTvb_create_date());
		ecBean.setDealerCode(db.getDealer_sale_code());
		ecBean.setDataMark(db.getIsObStr());
		ecBean.setIsNewOwner(db.getIsNewOwer());
		ecBean.setObType(db.getObType());
		ecBean.setCAR_SYNC(db.getCarSync());
		ecBean.setCAR_EA(db.getCarEa());
		return ecBean;
	}
}

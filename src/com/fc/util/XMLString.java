package com.fc.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import com.fc.bean.DmsAddItemBean;
import com.fc.bean.DmsDictionaryBean;
import com.fc.bean.DmsLabourPartBean;
import com.fc.bean.DmsObQuestionsBean;
import com.fc.bean.DmsObToData;
import com.fc.bean.DmsRepairOrderBean;
import com.fc.bean.DmsRepairPartBean;
import com.fc.bean.DmsSalesPartBean;
import com.fc.bean.DmsToDataBean;

public class XMLString {
	private static Logger log = Logger.getLogger(XMLString.class);
	/**
	 * 反射设置实体不同类型字段的值 <暂时只支持 日期 字符串 boolean Integer值设置 待扩建>
	 * 
	 * @param field
	 * @param obj
	 * @param value
	 * @throws Exception
	 * 
	 */
	public static void convertValue(Field field, Object obj, String value,
			List values) throws Exception {
		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (field.getGenericType().toString().equals("class java.lang.Integer")) {
			field.set(obj, Integer.parseInt(value));
		} else if (field.getGenericType().toString().equals("boolean")) {
			field.set(obj, Boolean.parseBoolean(value));
		} else if (field.getGenericType().toString().equals(
				"class java.util.Date")) {
			field.set(obj, sim.parse(value));
		} else if (field.getGenericType().toString().equals(
				"class java.lang.Long")) {
			field.set(obj, Long.parseLong(value));
		} else if (field.getGenericType().toString().equals(
				"class java.lang.Double")) {
			field.set(obj, Double.parseDouble(value));
		} else if (field.getGenericType().toString().equals(
				"java.util.List<com.fc.bean.DmsRepairPartBean>")) {
			field.set(obj, values);
		} else if (field.getGenericType().toString().equals(
				"java.util.List<com.fc.bean.DmsLabourPartBean>")) {
			field.set(obj, values);
		} else if (field.getGenericType().toString().equals(
				"java.util.List<com.fc.bean.DmsAddItemBean>")) {
			field.set(obj, values);
		} else if (field.getGenericType().toString().equals(
				"java.util.List<com.fc.bean.DmsSalesPartBean>")) {
			field.set(obj, values);
		} else if (field.getGenericType().toString().equals(
				"java.util.List<com.fc.bean.DmsObQuestionsBean>")) {
			field.set(obj, values);
		} else {
			field.set(obj, value);
		}
	}

	/**
	 * 解析xml文件返回保存cls的List集合，如果返回码resultCode=1时则返回List为null
	 * 
	 * @param xml
	 * @param cls
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static List<?> parseXml2List(String xml, Class<?> cls)
			throws Exception {
		List<Object> lists = null;
		Document doc = DocumentHelper.parseText(xml);
		Element et = doc.getRootElement();
		String root = et.getName();
		// 查看返回码是否为真.
		List<Element> list = doc.selectNodes("//" + root + "/resultCode");
		if (!list.isEmpty() && list.size() > 0) {
			Element element = list.get(0);
			String returnResult = element.getText();
			if (returnResult.equals("0")) {
				List<Element> father = doc.selectNodes("//" + root + "/"
						+ cls.getSimpleName() + "s");
				// 判断对象父节点是否有包含数据
				if (father != null && !father.isEmpty() && father.size() == 1) {
					List<Element> userLists = father.get(0).elements();
					if (userLists != null && !list.isEmpty()) {
						lists = new ArrayList<Object>();
						for (Element e : userLists) {
							List<Element> li = e.elements();
							Class<?> cl = (Class<?>) Class.forName(cls
									.getName());
							Object ob = cl.newInstance();
							for (Element element2 : li) {
								String name = element2.getName();
								String value = element2.getText();
								Field field = ob.getClass().getDeclaredField(
										name);
								field.setAccessible(true);
								convertValue(field, ob, value, null);
							}
							lists.add(ob);
						}
					}
				}
			}

		}
		return lists;
	}

	/**
	 * 根据字符串解析XML文件信息，应对与CallCenter返回数据
	 * 
	 * @param xml
	 *            xml 字符串信息
	 * @param cls
	 *            相关类
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static List<?> parseXmlList(File file, Class<?> cls)
			throws Exception {
		List<Object> lists = new ArrayList<Object>();
		SAXReader saxReader = new SAXReader();
		Document doc = saxReader.read(file);
		Element et = doc.getRootElement();
		String root = et.getName();
		// 查看返回码是否为真.
		List<Element> list = doc.selectNodes("//" + root);
		Element elem = list.get(0);
		List<Element> elemli = elem.elements();
		String returnDate = "";
		for (Element element : elemli) {
			if ("returnDate".equals(element.getName())) {
				Date date=XmlSaveUtil.formatw.parse(element.getText());
				returnDate =XmlSaveUtil.formaty.format(date);
			} else {
				if (element.elements().size() > 0) {
					List<Element> elets = element.elements();
					for (Element elet : elets) {
						Class<?> cl = (Class<?>) Class.forName(cls.getName());
						Object ob = cl.newInstance();
						String vin = "";
						String ownerMobile = "";
						List<Element> e = elet.elements();
						String uuid_str=""; 
						if("com.fc.bean.DmsObToData".equals(cls.getName())){
							DmsObToData ro=(DmsObToData)ob;
							UUID uuid = UUID.randomUUID();
							ro.setOid(uuid.toString());
							uuid_str=uuid.toString();
							ob=ro;
						}
						for (Element eBean : e) {
							if (eBean.elements().size() > 0) {
								String name = eBean.getName();
								if ("listQuestions".equals(name)) {
									Field field = ob.getClass()
											.getDeclaredField(name);
									field.setAccessible(true);
									convertValue(field, ob, null,
											saveXmlList(eBean, vin,
													ownerMobile, returnDate,uuid_str,null));
								}
							} else {
								String name = eBean.getName();
								String value = eBean.getText();
								if ("vin".equals(name)) {
									vin = value;
								}
								if ("ownerMobile".equals(name)) {
									ownerMobile = value;
								}
								if(name.toLowerCase().equals("priority"))
								    name=name.toLowerCase();
								Field field = ob.getClass().getDeclaredField(
										name);
								
								field.setAccessible(true);
								convertValue(field, ob, value, null);
							}
						}
						Field field = ob.getClass().getDeclaredField(
								"returnDate");
						field.setAccessible(true);
						convertValue(field, ob, returnDate, null);
						
						lists.add(ob);
					}
				}
			}
		}
		return lists;
	}

	/**
	 * 解析xml文件返回保存cls的List集合，如果返回码resultCode=1时则返回List为null
	 * 
	 * @param url
	 * @param cls
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static List<?> parseXml2List(File file, Class<?> cls)
			throws Exception {
		List<Object> lists = new ArrayList<Object>();
		SAXReader saxReader = new SAXReader();
		

		FileInputStream in = new FileInputStream(file);  
		Reader read = new InputStreamReader(in,"gbk");  
		Document doc = saxReader.read(read);  
		//Document doc = saxReader.read(file);
		Element et = doc.getRootElement();
		String root = et.getName();
		// 查看返回码是否为真.
		List<Element> list = doc.selectNodes("//" + root);
		Element elem = list.get(0);
		List<Element> elemli = elem.elements();
		String time_stamp = "";
		String sequnce = "";
		for (Element elent : elemli) {
			if ("time_stamp".equals(elent.getName())) {
				time_stamp = elent.getText();
			} else if ("sequnce".equals(elent.getName())) {
				sequnce = elent.getText();
			} else {
				List<Element> li = elent.elements();
				for (Element el : li) {
					Class<?> cl = (Class<?>) Class.forName(cls.getName());
					Object ob = cl.newInstance();
					List<Element> e = el.elements();
					String uuid_str="";
					String ro_no="";
					if ("com.fc.bean.DmsRepairOrderBean".equals(cls.getName())) {
						DmsRepairOrderBean ro = (DmsRepairOrderBean) ob;
						ro.setImportDate(getImportDate());
						UUID uuid = UUID.randomUUID();
						ro.setOrder_id(uuid.toString());
						uuid_str=uuid.toString();
						//2014-05-09 添加人xuw 获取ro_id
						for(Element t : e)
						{
							if(t.getName().toLowerCase().equals("ro_id"))
							{
								ro_no =  t.getText().trim();
								break;
							}
						}
						ob = ro;
					}
					if("com.fc.bean.DmsToDataBean".equals(cls.getName())){
						DmsToDataBean ro = (DmsToDataBean) ob;
						UUID uuid = UUID.randomUUID();
						ro.setData_id(uuid.toString());
						uuid_str=uuid.toString();
						ob = ro;
					}
					for (Element t : e) {
						if (t.elements().size() > 0) {
							String name = t.getName();
							Field field = ob.getClass().getDeclaredField(name);
							field.setAccessible(true);
							convertValue(field, ob, null, saveXmlList(t, null,
									null, null,uuid_str,ro_no));
						} else {
							String name = t.getName();
							String value = t.getText();
							Field field = ob.getClass().getDeclaredField(name);
							field.setAccessible(true);
							convertValue(field, ob, value, null);
						}
					}
					Field field = ob.getClass().getDeclaredField("time_stamp");
					field.setAccessible(true);
					convertValue(field, ob, time_stamp, null);
					Field fielde = ob.getClass().getDeclaredField("sequnce");
					fielde.setAccessible(true);
					convertValue(fielde, ob, sequnce, null);
					if ("com.fc.bean.DmsRepairOrderBean".equals(cls.getName())) {
						DmsRepairOrderBean ro = (DmsRepairOrderBean) ob;
						ro.setImportDate(getImportDate());
						ob = ro;
					}
					if ("com.fc.bean.DmsDictionaryBean".equals(cls.getName())) {
						DmsDictionaryBean ro = (DmsDictionaryBean) ob;
						ro.setImportDate(getImportDate());
						ob = ro;
					}
					lists.add(ob);
				}
			}
		}
		return lists;
	}

	/**
	 * 解析售前XML数据
	 * 
	 * @param url
	 * @param cls
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static List<?> parseXmlPreSellList(File file, Class<?> cls)
			throws Exception {
		List<Object> lists = new ArrayList<Object>();
		SAXReader saxReader = new SAXReader();
		Document doc = saxReader.read(file);
		Element et = doc.getRootElement();
		String root = et.getName();
		// 查看返回码是否为真.
		List<Element> list = doc.selectNodes("//" + root);
		Element elem = list.get(0);
		List<Element> elemli = elem.elements();
		String time_stamp = "";
		for (Element elent : elemli) {
			if ("time_stamp".equals(elent.getName())) {
				time_stamp = elent.getText();
			}else {
				List<Element> li = elent.elements();
				for (Element el : li) {
					Class<?> cl = (Class<?>) Class.forName(cls.getName());
					Object ob = cl.newInstance();
					List<Element> e = el.elements();
					for (Element t : e) {
							String name = t.getName();
							String value = t.getText();
							Field field = ob.getClass().getDeclaredField(name);
							field.setAccessible(true);
							convertValue(field, ob, value, null);
					}
					lists.add(ob);
				}
			}
		}
		return lists;
	}
	
	/**
	 * 修改时间 2014-05-09
	 * 修改人 xuw
	 * 添加维修相关表的ro_id和主键值
	 * @param t
	 * @param vin
	 * @param ownerMobile
	 * @param returnDate
	 * @param uuid
	 * @param ro_id  维修明细里面的ro_no值
	 * @return
	 * @throws Exception
	 */
	private static List saveXmlList(Element t, String vin, String ownerMobile,
			String returnDate,String uuid,String ro_id) throws Exception {
		Object ob = null;
		int number=1;
		List obs = new ArrayList();
		List<Element> e = t.elements();
		if (e != null && e.size() > 0) {
			for (Element em : e) {
				List<Element> et = em.elements();
				String sxName = t.getName();
				if ("repair_labour_list".equals(sxName)) {
					Class<?> cl = (Class<?>) Class
							.forName((DmsLabourPartBean.class).getName());
					ob = cl.newInstance();
				} else if ("repair_part_list".equals(sxName)) {
					Class<?> cl = (Class<?>) Class
							.forName((DmsRepairPartBean.class).getName());
					ob = cl.newInstance();
				} else if ("sales_part_list".equals(sxName)) {
					Class<?> cl = (Class<?>) Class
							.forName((DmsSalesPartBean.class).getName());
					ob = cl.newInstance();
				} else if ("add_item_list".equals(sxName)) {
					Class<?> cl = (Class<?>) Class
							.forName((DmsAddItemBean.class).getName());
					ob = cl.newInstance();
				} else if ("listQuestions".equals(sxName)) {
					if (et != null && et.size() > 0) {
						Class<?> cl = (Class<?>) Class
								.forName((DmsObQuestionsBean.class).getName());
						ob = cl.newInstance();
						for (Element mel : et) {
							String name = mel.getName();
							String value = mel.getText();
							Field field = ob.getClass().getDeclaredField(name);
							field.setAccessible(true);
							convertValue(field, ob, value, null);
						}
						DmsObQuestionsBean oq = (DmsObQuestionsBean) ob;
						oq.setVin(vin);
						oq.setOwnerMobile(ownerMobile);
						oq.setReturnDate(returnDate);
						oq.setOid(uuid);
						oq.setQnumber(number);
						ob = oq;
						obs.add(ob);
						number++;
					}
				} 
					if ( (et != null && et.size() > 0) && !"listQuestions".equals(sxName) ) {
						for (Element mel : et) {
							String name = mel.getName();
							String value = mel.getText();
							Field field = ob.getClass().getDeclaredField(name);
							field.setAccessible(true);
							convertValue(field, ob, value, null);
						}
					    String randomId = UUID.randomUUID().toString();//维修明细四张表的主键
						if ("repair_part_list".equals(sxName)) {
							DmsRepairPartBean dm = (DmsRepairPartBean) ob;
							dm.setImportDate(getImportDate());
							dm.setOrder_id(uuid);
							dm.setRo_no(ro_id);
							dm.setPart_id(randomId);
							ob = dm;
						}
						if ("sales_part_list".equals(sxName)) {
							DmsSalesPartBean dm = (DmsSalesPartBean) ob;
							dm.setImportDate(getImportDate());
							dm.setOrder_id(uuid);
							dm.setRo_no(ro_id);
							dm.setSales_id(randomId);
							ob = dm;
						}
						if ("add_item_list".equals(sxName)) {
							DmsAddItemBean dm = (DmsAddItemBean) ob;
							dm.setImportDate(getImportDate());
							dm.setOrder_id(uuid);
							dm.setRo_no(ro_id);
							dm.setAdd_id(randomId);
							ob = dm;
						}
						if ("repair_labour_list".equals(sxName)) {
							DmsLabourPartBean dm = (DmsLabourPartBean) ob;
							dm.setImportDate(getImportDate());
							dm.setOrder_id(uuid);
							dm.setRo_no(ro_id);
							dm.setLabour_id(randomId);
							ob = dm;
						}
						obs.add(ob);
					}
				
			}
		}
		return obs;
	}

	private static String getImportDate() {
		DateFormat formaty = new SimpleDateFormat("yyyyMMdd");
		return formaty.format(new Date());
	}
    /**
     * 根据Java类及集合，动态创建含有集合数据的XML  (售前)
     * @param obj JavaBean的Class
     * @param entitys 对应JavaBean的集合
     * @param Encode  XML的编码格式如:GBK
     * @param XMLPath XML的文件夹路径
     * @param createDate XML生成日期
     * @param XmlName XML名称，不包含后缀名.XML
     */
	public static void createXMLPreSellDocument(Class<?> obj, List<?> entitys,
			String Encode, String XMLPath,String createDate,String XmlName) {
		try {
			XMLWriter writer = null;// 声明写XML的对象
			OutputFormat format = OutputFormat.createPrettyPrint();
			format.setEncoding(Encode); // 设置XML文件的编码格式 UTF-8

			File file = new File(XMLPath);// 获得文件
			if (!file.isDirectory()) {
				file.mkdirs();
			}
			// 创建xml文件
			Document document = DocumentHelper.createDocument();
			Element root = document.addElement("data");// 添加根节点
			root.addElement("time_stamp").setText(createDate);
			Element zroot = root.addElement("beans");// 添加根节点
			Field[] properties = obj.getDeclaredFields();// 获得实体类的所有属性
			
			for (Object t : entitys) { // 递归实体
				Element element = zroot.addElement("bean"); // 二级节点
				for (int i = 0; i < properties.length; i++) {
					if (!"serialVersionUID".equals(properties[i].getName())) {
						// 反射get方法
						Method meth = t.getClass().getMethod(
								"get"
										+ (properties[i].getName().substring(0,
												1)).toUpperCase()
										+ properties[i].getName().substring(1));
						// 为二级节点添加属性，属性值为对应属性的值
						Object invo=meth.invoke(t);
						element.addElement(properties[i].getName()).setText((invo!=null?invo.toString():""));
					}
				}
			}
			File fil=new File(XMLPath+"\\"+XmlName+".xml");
			 writer = new XMLWriter(new FileOutputStream(fil), format);
			 writer.write(document);
			 writer.close();
		} catch (Exception e) {
			log.error("创建售前XML文件出现异常", e);
		}
	}
	/**
	 * 解析xml文件返回保存Map的集合，map中可能包含key值为returnCode、desc、totalCount等单字段.
	 * 也可能包含存储对象为List<cls>的集合值. 获取List值key cls_List
	 * 
	 * @param requestPath
	 * @param cls
	 * @return map
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> parseXml2Map(String requestPath,
			Class<?> cls) throws Exception {
		Map<String, Object> maps = new HashMap<String, Object>();
		List<Object> lists = new ArrayList<Object>();
		SAXReader saxReader = new SAXReader();
		// Document doc = saxReader.read(new File(requestPath));
		Document doc = saxReader.read(new URL(requestPath));
		Element et = doc.getRootElement();
		// 标记List是否为空
		// boolean bool = true ;
		// 根节点名字
		List<Element> rList = et.elements();
		for (Element element : rList) {
			List<Element> rLists = element.elements();
			if (!rLists.isEmpty() && rLists.size() > 0) {
				// bool = false;
				// 判断二级节点
				for (Element e : rLists) {
					List<Element> li = e.elements();
					Class<?> cl = (Class<?>) Class.forName(cls.getName());
					Object ob = cl.newInstance();
					for (Element element2 : li) {
						String name = element2.getName();
						String value = element2.getText();
						Field field = ob.getClass().getDeclaredField(name);
						field.setAccessible(true);
						convertValue(field, ob, value, null);
					}
					lists.add(ob);
				}
			} else {
				maps.put(element.getName(), element.getText());
			}
			maps.put(cls.getSimpleName() + "_List", lists);
		}
		return maps;
	}

	/**
	 * 只获取返回码0为保存成功(true)1为保存失败(false)
	 */
	@SuppressWarnings("unchecked")
	public static boolean parseXmlReturnCode(String xml) {
		boolean bool = false;
		try {
			Document doc = DocumentHelper.parseText(xml);
			Element et = doc.getRootElement();
			String root = et.getName();
			// 查看返回码是否为真.
			List<Element> list = doc.selectNodes("//" + root + "/resultCode");
			if (!list.isEmpty() && list.size() > 0) {
				Element element = list.get(0);
				String returnResult = element.getText();
				if (returnResult.equals("0")) {
					bool = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bool;
	}

	public final static String MD5(String s) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			byte[] strTemp = s.getBytes();
			// 使用MD5创建MessageDigest对象
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);
			byte[] md = mdTemp.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte b = md[i];
				// System.out.println((int)b);
				// 将没个数(int)b进行双字节加密
				str[k++] = hexDigits[b >> 4 & 0xf];
				str[k++] = hexDigits[b & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			return null;
		}
	}
}

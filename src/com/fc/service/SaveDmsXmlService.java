package com.fc.service;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import com.fc.bean.DmsAddItemBean;
import com.fc.bean.DmsDealBean;
import com.fc.bean.DmsDictionaryBean;
import com.fc.bean.DmsElecBean;
import com.fc.bean.DmsLabourPartBean;
import com.fc.bean.DmsPartsBean;
import com.fc.bean.DmsRepairOrderBean;
import com.fc.bean.DmsRepairPartBean;
import com.fc.bean.DmsSalesPartBean;
import com.fc.bean.DmsToDataBean;
import com.fc.bean.DmsToErorBean;
import com.fc.bean.DmsToLogBean;
import com.fc.dao.db.SaveDmsDataDao;
import com.fc.listener.DmsTask;
import com.fc.util.FcUtil;
import com.fc.util.XmlSaveUtil;

/**
 * 读取XML数据
 * 
 * @author lq
 * 
 */
public class SaveDmsXmlService {
	private static Logger log = Logger.getLogger(SaveDmsXmlService.class);
	public static XmlSaveUtil xmlBean;
	public static String[] aryReadType;
	private ProcessDmsDataService dmsProcess = new ProcessDmsDataService();
	private SubmitCallCenterService subService=new SubmitCallCenterService();
	private static SaveDmsDataDao dao = new SaveDmsDataDao();
	public static boolean isSub = false;
	private static SaveDmsDataDao saveDmsBean = new SaveDmsDataDao();
	public static Map<String, String> directory;
	public static int setpNumber = 0; // 完成步骤计数

	public SaveDmsXmlService(XmlSaveUtil xmlBeans, String[] aryReadTypes,
			Map<String, String> directorys) {
		xmlBean = xmlBeans;
		aryReadType = aryReadTypes;
		directory = directorys;
	}

	/**
	 * 读取并存储XML数据
	 */
	public void readDmsXml() throws Exception {
		log.info("读取XML数据开始...");
		String path = xmlBean.DMS_TO_DATE_PATH + "\\" + xmlBean.importDate;
		File file = new File(path);
		if (file.exists()) {
			traversalFile(file);
			isSub = true;
		}
	}

	/**
	 * 遍历文件夹导入XML入数据库
	 * 
	 * @param dic
	 * @param type
	 * @throws Exception
	 */
	private void traversalFile(File dic) throws Exception {
		log.info("遍历导入XML进入数据库开始" + XmlSaveUtil.formatDate(new Date(), 1));

		if (dic.exists()) {
			String QDate = getQDate();
			File[] dics = dic.listFiles();
			List<DmsAddItemBean> taddItem = new ArrayList<DmsAddItemBean>();
			List<DmsLabourPartBean> tlabourParts = new ArrayList<DmsLabourPartBean>();
			List<DmsRepairPartBean> trepairParts = new ArrayList<DmsRepairPartBean>();
			List<DmsSalesPartBean> tsalesParts = new ArrayList<DmsSalesPartBean>();

			List<DmsToDataBean> dmsToData = new ArrayList<DmsToDataBean>();
			List<DmsToErorBean> dmsToEror = new ArrayList<DmsToErorBean>();
			List<DmsRepairOrderBean> repairList = new ArrayList<DmsRepairOrderBean>();
			List<DmsDictionaryBean> dicse = new ArrayList<DmsDictionaryBean>();
			List<DmsPartsBean> partss = new ArrayList<DmsPartsBean>();
			List<DmsDealBean> deals = new ArrayList<DmsDealBean>();
			List<DmsElecBean> elecs = new ArrayList<DmsElecBean>();
			for (File dicc : dics) {
				// 解析工单信息
				if ((dicc.getAbsolutePath().indexOf(directory
						.get(aryReadType[1]))) > 0) {
					int k = saveDmsBean.getDmsDataLogCount(xmlBean.importDate,
							aryReadType[1]);
					if (k == 0) {
						File[] fileXml = dicc.listFiles();
						if (fileXml != null && fileXml.length > 0) {
							log.info("[2]解析工单信息开始，时间"
									+ XmlSaveUtil.formatDate(new Date(), 1));
							for (File f : fileXml) {
								repairList.clear();
								tlabourParts.clear();
								trepairParts.clear();
								taddItem.clear();
								tsalesParts.clear();
								List<DmsRepairOrderBean> orders = xmlBean
										.parseAndPrint(f,
												DmsRepairOrderBean.class);
								for (DmsRepairOrderBean dmrepair : orders) {
									repairList.add(dmrepair);
									if (dmrepair.getRepair_labour_list() != null
											&& dmrepair.getRepair_labour_list()
													.size() > 0) {
										initDmsDmsToLogBean(
												"DO0202",
												"DO02",
												dmrepair
														.getRepair_labour_list()
														.size());
										tlabourParts.addAll(dmrepair
												.getRepair_labour_list());
									}
									if (dmrepair.getRepair_part_list() != null
											&& dmrepair.getRepair_part_list()
													.size() > 0) {
										initDmsDmsToLogBean("DO0203", "DO02",
												dmrepair.getRepair_part_list()
														.size());
										trepairParts.addAll(dmrepair
												.getRepair_part_list());
									}
									if (dmrepair.getAdd_item_list() != null
											&& dmrepair.getAdd_item_list()
													.size() > 0) {
										initDmsDmsToLogBean("DO0201", "DO02",
												dmrepair.getAdd_item_list()
														.size());
										taddItem.addAll(dmrepair
												.getAdd_item_list());
									}
									if (dmrepair.getSales_part_list() != null
											&& dmrepair.getSales_part_list()
													.size() > 0) {
										initDmsDmsToLogBean("DO0204", "DO02",
												dmrepair.getSales_part_list()
														.size());
										tsalesParts.addAll(dmrepair
												.getSales_part_list());
									}
								}
								if (repairList != null && repairList.size() > 0) {
									dao.insertRepair(repairList, taddItem,
											tlabourParts, trepairParts,
											tsalesParts, xmlBean.importDate, f
													.getCanonicalPath());
								}
							}
							if (repairList.size() > 0) {
								SaveDmsXmlService.setpNumber++;
							} else {
								log
										.info("[2]解析工单信息，没有解析到该数据！"
												+ XmlSaveUtil.formatDate(
														new Date(), 1));
							}
						}
					}
				}
				// 解析数据字典信息
				if ((dicc.getAbsolutePath().indexOf(directory
						.get(aryReadType[2]))) > 0) {
					int k = saveDmsBean.getDmsDataLogCount(xmlBean.importDate,
							aryReadType[2]);
					if (k == 0) {
						File[] fileXml = dicc.listFiles();
						if (fileXml != null && fileXml.length > 0) {
							log.info("[3]解析数据字典信息开始，时间"
									+ XmlSaveUtil.formatDate(new Date(), 1));
							for (File f : fileXml) {
								dicse.clear();
								List<DmsDictionaryBean> orders = xmlBean
										.parseAndPrint(f,
												DmsDictionaryBean.class);
								dicse.addAll(orders);
								if (dicse != null && dicse.size() > 0) {
									dao.insertDic(dicse, f.getCanonicalPath(),xmlBean.importDate);
								}
							}
							if (dicse.size() > 0) {
								SaveDmsXmlService.setpNumber++;
							} else {
								log
										.info("[3]解析数据字典信息，没有解析到该数据！"
												+ XmlSaveUtil.formatDate(
														new Date(), 1));
							}
						}
					}
				}
				// 解析配件信息
				if ((dicc.getAbsolutePath().indexOf(directory
						.get(aryReadType[3]))) > 0) {
					int k = saveDmsBean.getDmsDataLogCount(xmlBean.importDate,
							aryReadType[3]);
					if (k == 0) {
						File[] fileXml = dicc.listFiles();
						if (fileXml != null && fileXml.length > 0) {
							log.info("[4]解析配件信息开始，时间"
									+ XmlSaveUtil.formatDate(new Date(), 1));

							for (File f : fileXml) {
								partss.clear();
								List<DmsPartsBean> orders = xmlBean
										.parseAndPrint(f, DmsPartsBean.class);

								partss.addAll(orders);
								if (partss != null && partss.size() > 0) {
									dao.insertParts(partss, f
											.getCanonicalPath(),
											xmlBean.importDate);
								}
							}
							if (partss.size() > 0) {
								SaveDmsXmlService.setpNumber++;
							} else {
								log
										.info("[4]解析配件信息，没有解析到该数据！"
												+ XmlSaveUtil.formatDate(
														new Date(), 1));
							}
						}
					}
				}
				// 解析账户信息
				if ((dicc.getAbsolutePath().indexOf(directory
						.get(aryReadType[4]))) > 0) {
					int k = saveDmsBean.getDmsDataLogCount(xmlBean.importDate,
							aryReadType[4]);
					if (k == 0) {
						File[] fileXml = dicc.listFiles();
						if (fileXml != null && fileXml.length > 0) {
							log.info("[5]解析账户信息开始，时间"
									+ XmlSaveUtil.formatDate(new Date(), 1));

							for (File f : fileXml) {
								deals.clear();
								List<DmsDealBean> orders = xmlBean
										.parseAndPrint(f, DmsDealBean.class);

								deals.addAll(orders);
								if (deals != null && deals.size() > 0) {
									dao.insertDeal(deals, xmlBean.importDate, f
											.getCanonicalPath());
								}
							}
							if (deals.size() > 0) {
								SaveDmsXmlService.setpNumber++;
							} else {
								log
										.info("[5]解析账户信息，没有解析到该数据！"
												+ XmlSaveUtil.formatDate(
														new Date(), 1));
							}
						}
					}
				}
				// 解析账户明细
				if ((dicc.getAbsolutePath().indexOf(directory
						.get(aryReadType[5]))) > 0) {
					int k = saveDmsBean.getDmsDataLogCount(xmlBean.importDate,
							aryReadType[5]);
					if (k == 0) {
						File[] fileXml = dicc.listFiles();
						if (fileXml != null && fileXml.length > 0) {
							log.info("[6]解析账户明细信息开始，时间"
									+ XmlSaveUtil.formatDate(new Date(), 1));

							for (File f : fileXml) {
								elecs.clear();
								List<DmsElecBean> orders = xmlBean
										.parseAndPrint(f, DmsElecBean.class);

								elecs.addAll(orders);
								if (elecs != null && elecs.size() > 0) {
									dao.insertElec(elecs, xmlBean.importDate, f
											.getCanonicalPath());
								}
							}
							if (elecs.size() > 0) {
								SaveDmsXmlService.setpNumber++;
							} else {
								log
										.info("[6]解析账户明细信息，没有解析到该数据！"
												+ XmlSaveUtil.formatDate(
														new Date(), 1));
							}

						}
					}
				}
			}
			List<String> syncList = dao.getSync("ea");
			List<String> listSyncs=dao.getSync("sync");
			Map<String, String> ma9992 = dao.setCXByDmsToData("9992",
					xmlBean.importDate);
			Map<String, String> mb9993 = dao.setCXByDmsToData("9993",
					xmlBean.importDate);
			List<String> customers=dao.getCustomers();
			List<String> vins=dao.getLargeVins();
			for (File dicc : dics) {
				// 解析车主信息
				if ((dicc.getAbsolutePath().indexOf(directory
						.get(aryReadType[0]))) > 0) {
					int k = saveDmsBean.getDmsDataLogCount(xmlBean.importDate,
							aryReadType[0]);
					if (k == 0) {
						File[] fileXml = dicc.listFiles();
						if (fileXml != null && fileXml.length > 0) {
							log.info("[1]解析车主信息开始，时间"
									+ XmlSaveUtil.formatDate(new Date(), 1));
							for (File f : fileXml) {
								dmsToData.clear();
								//dmsToEror.clear();//20140522 xuw
								List<DmsToDataBean> owners = xmlBean
										.parseAndPrint(f, DmsToDataBean.class);
								if (owners != null && owners.size() > 0) {
									List<DmsToErorBean> dmsEror = new ArrayList<DmsToErorBean>();// 声明临时拆分EROR数据LIST
									String vinls=subService.getVins(owners);
									//List<String>  hisList=dao.searchIsObHistory(vinls);
									//List<String>  visList=dao.searchIsFCHistory(vinls);
									dmsProcess.checkedDmsData(owners, dmsEror,
											xmlBean.importDate, QDate,
											syncList, ma9992, mb9993,customers,vins,null,null,listSyncs,null);// 校验数据
									dmsToData.addAll(owners);
									dmsToEror.addAll(dmsEror);
									if (dmsToData != null
											&& dmsToData.size() > 0) {
//										dao.saveToDmsData(dmsToData, dmsToEror,
//												f.getCanonicalPath());// 保存数据
										//20140522 xuw
										dao.saveToDmsData(dmsToData, null,
												f.getCanonicalPath());// 保存数据
									}
								}
							}
							if (dmsToData.size() > 0) {
								SaveDmsXmlService.setpNumber++;
							} else {
								log.info("[1]解析车主信息，没有解析到该数据！"
												+ XmlSaveUtil.formatDate(
														new Date(), 1));
							}
						}
					}
					break;
				}
			}
			if (SaveDmsXmlService.setpNumber > 0) {
				FcUtil.getFcLOG().setIsDmsToData(1L);
				dao.updateFcServiceLog(FcUtil.getFcLOG());
				//更新新车主拆分类别
				dao.updateOwnersTypes(xmlBean.importDate);
				///保存工单数据到正式数据表
				dao.saveOrderFormalTab(xmlBean.importDate);
				
				//ping.qi 20141120 2.update数据字典  3.清空temp表
				//dao.updateFcDmsDictionaryTemp();
				//dao.deleteFcDmsDictionaryTemp();
				dao.updateFcDmsDictionaryTempByProc();
				
				//保存去重后的车主正式数据表
				dao.saveDmsToDataForTempTab();
				
				//ping.qi update 20141029 start
				List<String>  obVins=dao.searchIsObHistory(xmlBean.importDate);
				List<String>  fcVins=dao.searchIsFCHistory(xmlBean.importDate);
				List<DmsToDataBean> ownersold=dao.searchDmsHistory(xmlBean.importDate);
				if(ownersold!= null &&ownersold.size()>0){
				    List<DmsToErorBean> dmsEror2 = new ArrayList<DmsToErorBean>();// 声明临时拆分EROR数据LIST
				    log.info("清洗7天前的空错号数据，并整理出反馈数据"+ownersold.size());
				 dmsProcess.checkedDmsOld(dmsEror2, syncList, customers, vins, obVins, fcVins, listSyncs, ownersold,xmlBean.importDate);
				 dmsToEror.addAll(dmsEror2);
				}
				
				//1.插入9天前的新车主数据到temp，2.调存储过程进行清洗   3.判断空错号  4.把temp表中的新车主数据插入到正式表中并清空temp表   5.发送newowners的数据给CC
			       List<String> lists=dao.queryPropertiesByKey("createCCxmlDays"); //发送给CC数据的时间差
				dao.insertFcDmsTempByNewOwners(lists,xmlBean.importDate);//1.插入9天之内清洗的新车主数据到temp表中
				log.info("2.清洗当天清洗的新车主数据到temp表中第二步调存储过程start");
				dao.deleteOrUpdateDmsToData();//2.调存储过程
				List<DmsToErorBean> dmsErortemp = new ArrayList<DmsToErorBean>();
				List<DmsToDataBean>  listTemp=this.dao.searchListByTemp();//3.判断空错号集合
				dmsProcess.checkDmsTemp(listTemp, dmsErortemp);//校验是否可以ob
				dao.saveToDmsDataByNewOwners(listTemp, dmsErortemp,xmlBean.importDate);//4.把temp表中的新车主数据插入到正式表中并清空temp表
				dao.deleteFcDmsTempByNewOwners();//并清空temp表 
				//ping.qi update 20141106 end
				
				/**ping.qi 插入日志*/
				dao.insertFcLogByParames(xmlBean.importDate);
				//去重系统清洗空错号
				removeDuplicate(dmsToEror);
				//插入去重后的系统清洗空错号 20140522 xuw
				dao.saveToDmsData(null, dmsToEror,null);// 保存数据
				
				
			       	
				
			}
		}
	}
      
	/**
	 * 去重系统清洗空错号
	 * @param dmsToEror
	 */
	private  void removeDuplicate(List<DmsToErorBean> dmsToErors) {
		if(dmsToErors != null && dmsToErors.size()>0)
		{
			List<DmsToErorBean> temps = new ArrayList<DmsToErorBean>(dmsToErors);
			for(DmsToErorBean temp:temps)
			{
				int count=0;
				String tvin=temp.getVin().trim().toLowerCase();
				for(int i=dmsToErors.size()-1;i>=0;i--)
				{				
					String evin = dmsToErors.get(i).getVin().trim().toLowerCase();
					if(tvin.trim().equals(evin))
					{
						if(count==0)
							count++;
						else if(count==1)
							dmsToErors.remove(i);
					}
				}
			}
		}
	}

	/**
	 * 创建对应的日志数据
	 * 
	 * @param code
	 * @param fCode
	 * @param number
	 */
	private static void initDmsDmsToLogBean(String code, String fCode,
			int number) {
		if (DmsTask.toLogBeanMap.containsKey(code)) {
			Long lNumber = DmsTask.toLogBeanMap.get(code).getReadCount();// 获取历史数量
			DmsTask.toLogBeanMap.get(code).setReadCount((number + lNumber)); // 将获取的数量存储到日志表中
		} else {
			DmsToLogBean tologBean = DmsTask.createDmsToLogBean(code,
					xmlBean.importDate);
			tologBean.setfReadType(fCode);
			tologBean.setReadCount((number + 0L));
			DmsTask.toLogBeanMap.put(code, tologBean);
		}
	}

	/**
	 * 根据Java类及集合，动态创建含有集合数据的XML
	 * 
	 * @param obj
	 *            JavaBean的Class
	 * @param entitys
	 *            对应JavaBean的集合
	 * @param Encode
	 *            XML的编码格式如:GBK
	 * @param XMLPath
	 *            XML的文件夹路径
	 * @param createDate
	 *            XML生成日期
	 * @param XmlName
	 *            XML名称，不包含后缀名.XML
	 */
	public static void createXmlDocument(Class<?> obj, List<?> entitys,
			String Encode, String XMLPath, String createDate, String XmlName) {
		log.info("动态生成XML文件开始");
		try {
			XMLWriter writer = null;// 声明写XML的对象
			OutputFormat format = OutputFormat.createPrettyPrint();
			format.setEncoding(Encode); // 设置XML文件的编码格式 UTF-8

			File file = new File(XMLPath);// 获得文件
			if (!file.exists()) {
				file.mkdirs();
			}
			// 创建xml文件
			Document document = DocumentHelper.createDocument();
			Element root = document.addElement("data");// 添加根节点
			root.addElement("subDate").setText(createDate);
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
						Object invo = meth.invoke(t);
						element.addElement(properties[i].getName()).setText(
								(invo != null ? invo.toString() : ""));
					}
				}
			}
			File fil = new File(XMLPath + "\\" + XmlName + ".xml");
			writer = new XMLWriter(new FileOutputStream(fil), format);
			writer.write(document);
			writer.close();
		} catch (Exception e) {
			log.error("动态生成XML失败", e);
		}
	}

	/**
	 * 得到当期时间的前9天的时间
	 * 
	 * @return
	 */
	public String getQDate() {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH, -9);// 得到前5
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String mDateTime = formatter.format(c.getTime());
		return mDateTime;
	}
	
}

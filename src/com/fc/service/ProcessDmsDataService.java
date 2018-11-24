package com.fc.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.fc.bean.DmsToDataBean;
import com.fc.bean.DmsToErorBean;
import com.fc.bean.UploadDmsDataErrorBean;
import com.fc.dao.db.SaveDmsDataDao;
import com.fc.util.Constant;
import com.fc.util.FcUtil;

/**
 * DMS获取数据校验接口
 * 
 * @author lq
 * 
 */
public class ProcessDmsDataService {
	SaveDmsDataDao sDao = null;
	private static Logger log = Logger.getLogger(ProcessDmsDataService.class);
	DateFormat format = null;
	DateFormat formata = new SimpleDateFormat("yyyyMMdd");
	DateFormat formatb = new SimpleDateFormat("yyyy-MM-dd");
	private String[] syncSTR={"新福克斯","新嘉年华","翼虎","翼博","新蒙迪欧"};

	public ProcessDmsDataService() {
		format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sDao = new SaveDmsDataDao();
	}

	/**
	 * 校验DMS所获取数据
	 */
	public void checkedDmsData(List<DmsToDataBean> dmsToData,
			List<DmsToErorBean> dmsToEror, String createDate, String QDate,
			List<String> syncList, Map<String, String> ma,
			Map<String, String> mb, List<String> customers, List<String> vins,
			List<String> obVins, List<String> fcVins,List<String> listSyncs,List<DmsToDataBean> ownersold) throws Exception {
		log.info("[校验DMS所获取车主信息数据]开始");
		int c = 0;
		for (DmsToDataBean dmsToBean : dmsToData) {//当天数据清洗流程
			c++;
			//ping.qi update 修改syne ea标示
			processSyncByList(dmsToBean,listSyncs);//校验sync
			processEA(dmsToBean, syncList); // 校验EA
			dmsToBean.setCreateOwnerDate(createDate); // 设置当期时间
			excludingMax(dmsToBean, customers);// 剔除不参与拨打的关键字数据
			checkedLargeVins(dmsToBean, vins); // 根据VIN码判断提出不参与拨打的大客户信息
			clearPhoneMobile(dmsToBean);// 清理手机电话特殊字符
			//addressChecked(dmsToBean); // 地址特殊字符清理
			//ownerShopping(dmsToBean);// 筛选代购数据
			mobileSame(dmsToBean);// 校验车主联系人相同的手机
			dismantleOwner(dmsToBean, createDate);// 拆分新车主或其它车主
		}
		
		
		if (c > 0) {
			try {
				if (FcUtil.getFcLOG().getIsCheckedData() == 0) {
					FcUtil.getFcLOG().setIsCheckedData(1L);
					log.info("[全局日志：是否校验数据]更新");
					sDao.updateFcServiceLog(FcUtil.getFcLOG());
				}
				if (FcUtil.getFcLOG().getIsProduceError() == 0
						&& dmsToEror.size() > 0) {
					FcUtil.getFcLOG().setIsProduceError(1L);
					log.info("[全局日志：是否存在空错号数据]更新");
					sDao.updateFcServiceLog(FcUtil.getFcLOG());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		log.info("[校验DMS所获取车主信息数据]结束");
	}
        /**校验dms7天前数据*/
	public void checkedDmsOld(List<DmsToErorBean> dmsToEror, 
		List<String> syncList,  List<String> customers, List<String> vins,
		List<String> obVins, List<String> fcVins,List<String> listSyncs,List<DmsToDataBean> ownersold,String importDate){
	      int k=0;
	      List<DmsToDataBean> bList=new ArrayList<DmsToDataBean>();//7天前空错号反馈数据
		for(DmsToDataBean dmsToBeanOld : ownersold){//7天前数据清洗流程
		     k++;
        		if (obVins.contains(dmsToBeanOld.getVin().trim()) || fcVins.contains(dmsToBeanOld.getVin().trim()) ) {
        		     setIsObStr(dmsToBeanOld, Constant.K_17);// 若在OB历史（拨打状态是空错号）  或者空错号历史  的VIN码中存在，则标识为K_17
        		     dmsToBeanOld.setData_type("second");
        		     Boolean flag=this.removeErrorDataBySecond(dmsToBeanOld.getVin().trim(),importDate);
               		    if(flag){
        			checkedTel(dmsToBeanOld, dmsToEror,"second");// 手机校验错误，则加入到空错号信息表中 second：反馈数据
        			String obStr=dmsToBeanOld.getIsObStr();
        			if((!"".equals(obStr) && null!=obStr && !obStr.contains("K_02") ) || ("".equals(obStr) || null == obStr)){
                        		     DmsToDataBean b=new DmsToDataBean();
                        		     b.setVin(dmsToBeanOld.getVin());
                        		     bList.add(b);
                        		 }
        			}
        			
        		}
		}
		try {
		    if(bList != null && bList.size()>0){
		       this.sDao.saveSubccDmsList(bList,importDate);
		    }
		} catch (Exception e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
	}
	public void checkDmsTemp( List<DmsToDataBean>  listTemp,List<DmsToErorBean> dmsErortemp){
	    for(DmsToDataBean temp : listTemp){
		isObChecked(temp);// 校验是否可以OB
		checkedTel(temp, dmsErortemp,"");// 手机校验错误
	    }
	    
	}
	
	 /**7天前的空错号反馈数据清洗 判断是否存在两次空错号 */
		public  boolean removeErrorDataBySecond(String vin,String importDate){
		   //1.判断是否存在两次空错号 
			try{
	        		long cunum=this.sDao.searchDmsErrorCount(importDate,vin);
	        		return cunum>=2?false:true;
			}catch(Exception e){
			    e.printStackTrace();
			}
			return true;
			
		}  
	/**
	 * 返回已转型的时间
	 * 
	 * @param vin
	 * @param createDate
	 * @return
	 */
	private Date getDate(String createDate) {
		Date d = null;
		try {
			d = format.parse(createDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return d;
	}

	/**
	 * 拆分新车主或其它车主 新逻辑，查看TVB_CREATE_DATE 是否为当前时间的前一天，若为当前时间的前一天则表示为新车主
	 * 
	 * @param dmsToBean
	 */
	public void dismantleOwner(DmsToDataBean dmsToBean, String createDate)
			throws Exception {
	  
		if (dmsToBean.getTvb_create_date() != null) {
			if (dmsToBean.getTvb_create_date().length() >= 10) {
				String tvbCreateDate = dmsToBean.getTvb_create_date();
				tvbCreateDate = tvbCreateDate.substring(0, 10);
				String oneDate = get1Date(createDate);
				if (tvbCreateDate.equals(oneDate)) {
					dmsToBean.setIsNewOwer("X");
				} //else {
					//setIsObStr(dmsToBean, Constant.K_14);
					//dmsToBean.setIsNewOwer("Q");
				//}
			} else {
				setIsObStr(dmsToBean, Constant.K_13);
				dmsToBean.setIsNewOwer("Q");
			}
		} else {
			setIsObStr(dmsToBean, Constant.K_12);
			dmsToBean.setIsNewOwer("Q");
		}
	}

	/**
	 * 剔除不参与拨打的关键字信息
	 * 
	 */
	public void excludingMax(DmsToDataBean dmsToBean, List<String> customers) {
		if (customers != null && customers.size() > 0) {
			if (dmsToBean.getOwner_name() != null
					&& !"".equals(dmsToBean.getOwner_name())) {
				for (String st : customers) {
					if (dmsToBean.getOwner_name().indexOf(st) > -1) {
						setIsObStr(dmsToBean, Constant.K_15);
						break;
					}
				}
			}
		}
	}

	/**
	 * 根据VIN码判断提出不参与拨打的大客户信息
	 * 
	 * @param dmsToBean
	 * @param vins
	 */
	public void checkedLargeVins(DmsToDataBean dmsToBean, List<String> vins) {
		if (vins != null && vins.size() > 0) {
			if (dmsToBean.getVin() != null && !"".equals(dmsToBean.getVin())) {
				if (vins.contains(dmsToBean.getVin().trim())) {
					setIsObStr(dmsToBean, Constant.K_16);
				}
			}
		}
	}

	/**
	 * 拆分车主数据时间校验
	 * 
	 * @param date
	 *            购车时间
	 * @return
	 */
	public boolean splitOwner(String tvb_create_date) {
		boolean temp = false;
		tvb_create_date = tvb_create_date.substring(0, 10);
		try {
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Calendar c15 = Calendar.getInstance();
			c15.add(Calendar.DATE, -15);
			Calendar c9 = Calendar.getInstance();
			c9.add(Calendar.DATE, -9);
			Date startDate = c15.getTime();
			Date endDate = c9.getTime();
			String startStr = format.format(startDate);
			String endStr = format.format(endDate);
			if (startStr.equals(tvb_create_date)
					|| endStr.equals(tvb_create_date)) {
				temp = true;
			} else if (endDate.after(format.parse(tvb_create_date))
					&& startDate.before(format.parse(tvb_create_date))) {
				temp = true;
			} else {
				temp = false;
			}
		} catch (Exception e) {
			log.error("[拆分车主数据时间校验]出现异常！", e);
		}

		return temp;
	}

	/**
	 *返回十五天前至九天前的数据
	 */
	private void get15Date() {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c15 = Calendar.getInstance();
		c15.add(Calendar.DATE, -15);
		Calendar c9 = Calendar.getInstance();
		c9.add(Calendar.DATE, -9);
		Date startDate = c15.getTime();
		Date endDate = c9.getTime();
		log.info("[新车主拆分逻辑]当前时间：" + format.format(new Date()) + "  15天前："
				+ format.format(startDate) + "   9天前： "
				+ format.format(endDate));
	}

	/**
	 * 返回前一天的日期
	 */
	private String get1Date(String importDate) throws Exception {
		Date dc = formata.parse(importDate);
		Calendar c = Calendar.getInstance();
		c.set(dc.getYear() + 1900, dc.getMonth(), dc.getDate());
		c.add(Calendar.DATE, -1);
		Date oneDate = c.getTime();
		return formatb.format(oneDate);
	}

	/**
	 * 校验sync
	 * 新福克斯要用 SERISE 字段跟MODEL字段来判断
	 * 其它的用 SERISE 字段来判断
	 * @param dmsToBean
	 */
	public void processSync(DmsToDataBean dmsToBean,
			Map<String, String> ma9992, Map<String, String> mb9993) {
		try {
			if (ma9992.size() > 0) {
				if (dmsToBean.getSerise() != null) {
					if (ma9992.containsKey(dmsToBean.getSerise())) {
						String xf = ma9992.get(dmsToBean.getSerise());
						boolean isb=false;
						for (int i = 0; i < syncSTR.length; i++) {
							if(xf.indexOf(syncSTR[i])>-1){
								isb=true;
								break;
							}
						}
						if(isb){
							if(xf.indexOf(syncSTR[0])>-1){
								if(mb9993.containsKey(dmsToBean.getModel())){
									String mod = mb9993.get(dmsToBean.getModel());
									if(!(mod.indexOf("舒适")>-1)){
										dmsToBean.setCarSync("1");
									}
								}else{
									dmsToBean.setCarSync("1");
								}
							}else{
								dmsToBean.setCarSync("1");
							}
						}
					}
				}
			}
		} catch (Exception e) {
			log.error("校验EA出现异常", e);
		}
	}
        /**
         * ping.qi add 校验sync 2014-08-25
         * */
	public void processSyncByList(DmsToDataBean dmsToBean,List<String> listSyncs){
	    try {
		if (listSyncs != null && listSyncs.size() > 0) {
			if (dmsToBean.getCat_code() != null) {
					if (listSyncs.contains(dmsToBean.getCat_code())) {
						dmsToBean.setCarSync("1");
					}
			}
		}
	} catch (Exception e) {
		log.error("校验SYNC出现异常processSyncByList", e);
	}
	}
	/**
	 * 校验EA
	 * 
	 * @param dmsToBean
	 */
	public void processEA(DmsToDataBean dmsToBean, List<String> syncList) {
		try {
			if (syncList != null && syncList.size() > 0) {
				if (dmsToBean.getCat_code() != null) {
						if (syncList.contains(dmsToBean.getCat_code())) {
							dmsToBean.setCarEa("1");
						}
				}
			}
		} catch (Exception e) {
			log.error("校验SYNC出现异常", e);
		}
	}

	public String replaceBlank(String str) {
		String dest = "";
		if (str != null) {
			Pattern p = Pattern.compile("\\s*|\t|\r|\n");
			Matcher m = p.matcher(str);
			dest = m.replaceAll("");
		}
		return dest;
	}

	/**
	 * 代购数据处理 若姓名中包含代购，则将联系人信息处理到车主信息中
	 */
	public void ownerShopping(DmsToDataBean dmsToData) {
		if (replaceBlank(dmsToData.getOwner_name()).indexOf("代购") > -1) {
			dmsToData.setOwner_name(dmsToData.getContactor_name());// 车主姓名替换为联系人姓名
			dmsToData.setGender(dmsToData.getContactor_gender());// 车主性别替换成联系人性别
			dmsToData.setMobile(dmsToData.getContactor_mobile());// 车主手机替换成联系人手机
			dmsToData.setE_mail(dmsToData.getContactor_email());// 车主Email替换成联系人Email
			dmsToData.setPhone(dmsToData.getContactor_phone()); // 车主电话替换成联系人电话
			dmsToData.setAddress(dmsToData.getContactor_address());// 车主地址替换成联系人地址。
		}
	}

	/**
	 * 电话校验
	 * 
	 * @param dmsToData
	 */
	private boolean phoneChecked(String phone) {
		boolean isPhone = true;
		if (phone != null && !"".equals(phone)) {
			if (!isPhoneNO(phone) ) {
				isPhone = false;
			}
		} else {
			isPhone = false;
		}
		return isPhone;
	}

	/**
	 * 设置数据不能拨打的原因
	 * 
	 * @param dmsToData
	 * @param str
	 */
	public static void setIsObStr(DmsToDataBean dmsToData, String str) {
		if (dmsToData.getIsObStr() != null) {
			dmsToData.setIsObStr(dmsToData.getIsObStr() + "," + str);
		} else {
			dmsToData.setIsObStr(str);
		}
	}

	/**
	 * 校验是否为空，校验规则，车主手机跟电话都为空，则标识为空
	 */
	private boolean checkedNULL(String mobile, String phone) {
		boolean temp = false;
		if ((mobile == null || "".equals(replaceBlank(mobile)))
				&& (phone == null || "".equals(replaceBlank(phone)))) {
			temp = true;
		}
		return temp;
	}

	/**
	 * 校验是否为错号，校验规则，手机跟电话为错错或空错标识为错。
	 * 
	 * @param dmsToData
	 * @return
	 */
	private boolean checkedERROR(String mobile, String phone) {
		boolean temp = false;
		if (mobile == null || "".equals(replaceBlank(mobile))) {
			if (!phoneChecked(phone)) {
				temp = true;
			}
		}
		if (phone == null || "".equals(replaceBlank(phone))) {
			if (!mobileChecked(mobile)) {
				temp = true;
			}
		}
		if ((!mobileChecked(mobile)) && (!phoneChecked(phone))) {
			temp = true;
		}
		return temp;
	}

	/**
	 * 校验手机或电话
	 * 
	 * @return
	 */
	public void checkedTel(DmsToDataBean dmsToData,
			List<DmsToErorBean> dmsToEror,String type) {
		// 车主
		if (checkedNULL(dmsToData.getMobile(), dmsToData.getPhone())) {
			dmsToData.setOdp1Code("0");
		} else {
			if (checkedERROR(dmsToData.getMobile(), dmsToData.getPhone())) {
				dmsToData.setOdp1Code("1");
			}
		}
		// 联系人
		if (checkedNULL(dmsToData.getContactor_mobile(), dmsToData
				.getContactor_phone())) {
			dmsToData.setOdp2Code("0");
		} else {
			if (checkedERROR(dmsToData.getContactor_mobile(), dmsToData
					.getContactor_phone())) {
				dmsToData.setOdp2Code("1");
			}
		}
		if ("owner".equals(dmsToData.getObType())) {
			if ((!mobileChecked(dmsToData.getMobile()))
					&& (!phoneChecked(dmsToData.getPhone()))) {
				dmsToData.setIsOwnerError("Y");
				setIsObStr(dmsToData, Constant.K_02);
				dmsToEror.add(createDmsToErorBean(dmsToData,type));
			}
		} else {
			boolean cz = (!mobileChecked(dmsToData.getMobile()))
					&& (!phoneChecked(dmsToData.getPhone()));
			boolean lxr = (!mobileChecked(dmsToData.getContactor_mobile()))
					&& (!phoneChecked(dmsToData.getContactor_phone()));
			if (cz && lxr) {
				dmsToData.setIsOwnerError("Y");
				setIsObStr(dmsToData, Constant.K_02);
				dmsToEror.add(createDmsToErorBean(dmsToData,type));
			}
		}
	}

	/**
	 * 清理电话手机特殊字符
	 */
	public void clearPhoneMobile(DmsToDataBean dmsToData) {
		if (dmsToData.getMobile() != null) {

			String mobile = dmsToData.getMobile().replaceAll(
					"[^a-zA-Z0-9_\u4e00-\u9fa5]", "");

			dmsToData.setMobile(mobile);
		}
		if (dmsToData.getPhone() != null) {
			String phone = dmsToData.getPhone().replaceAll(
					"[^a-zA-Z0-9_\u4e00-\u9fa5]", "");
			dmsToData.setPhone(phone);
		}
		if (dmsToData.getContactor_phone() != null) {
			String contactorPhone = dmsToData.getContactor_phone().replaceAll(
					"[^a-zA-Z0-9_\u4e00-\u9fa5]", "");
			dmsToData.setContactor_phone(contactorPhone);
		}
		if (dmsToData.getContactor_mobile() != null) {
			String contactorMobile = dmsToData.getContactor_mobile()
					.replaceAll("[^a-zA-Z0-9_\u4e00-\u9fa5]", "");
			dmsToData.setContactor_mobile(contactorMobile);
		}

	}

	/**
	 * 手机校验
	 * 
	 * @param dmsToData
	 */
	private boolean mobileChecked(String toMobile) {
		boolean mobile = true;
		// 验证手机是否为空
		if (replaceBlank(toMobile) == null || "".equals(replaceBlank(toMobile))) {
			mobile = false;
			return mobile;
		}
		if (!isMobileNO(replaceBlank(toMobile))) {
			mobile = false;
		}
		return mobile;
	}

	/**
	 * 校验车主联系人相同的手机
	 */
	public void mobileSame(DmsToDataBean dmsToData) {
		boolean isMobileSame = false;
		boolean isPhoneSame = false;
		boolean isMobileNULL = false;
		boolean isPhoneNULL = false;
		if ((dmsToData.getContactor_mobile() != null && !"".equals(dmsToData
				.getContactor_mobile()))
				&& (dmsToData.getMobile() != null && !"".equals(dmsToData
						.getMobile()))) {
			if (dmsToData.getContactor_mobile().equals(dmsToData.getMobile())) {
				isMobileSame = true;
			}
		} else {
			if ((dmsToData.getContactor_mobile() == null || "".equals(dmsToData
					.getContactor_mobile()))
					&& (dmsToData.getMobile() == null || "".equals(dmsToData
							.getMobile()))) {
				isMobileNULL = true;
			}
		}
		if ((dmsToData.getContactor_phone() != null && !"".equals(dmsToData
				.getContactor_phone()))
				&& (dmsToData.getPhone() != null && !"".equals(dmsToData
						.getPhone()))) {
			if (dmsToData.getContactor_phone().equals(dmsToData.getPhone())) {
				isPhoneSame = true;
			}
		} else {
			if ((dmsToData.getContactor_phone() == null || "".equals(dmsToData
					.getContactor_phone()))
					&& (dmsToData.getPhone() == null || "".equals(dmsToData
							.getPhone()))) {
				isPhoneNULL = true;
			}
		}
		if(replaceBlank(dmsToData.getOwner_name()).indexOf("代购") > -1){
		    dmsToData.setObType("contacter");
		}else{
        		if (isMobileSame && isPhoneSame) {
        			dmsToData.setObType("owner");
        		} else {
        			if (isMobileSame && isPhoneNULL) {
        				dmsToData.setObType("owner");
        			} else if (isMobileNULL && isPhoneSame) {
        				dmsToData.setObType("owner");
        			} else if (isPhoneNULL && isMobileNULL) {
        				dmsToData.setObType("owner");
        			} else {
        				dmsToData.setObType("owner&contacter");
        			}
        		}
		}
	}

	/**
	 * 地址校验
	 * 
	 * @param dmsToData
	 */
	public void addressChecked(DmsToDataBean dmsToData) {
		if (dmsToData.getAddress() != null) {
			dmsToData.setAddress(dmsToData.getAddress().replaceAll(
					"[^a-zA-Z0-9-_\u4e00-\u9fa5]", ""));
		}
		if (dmsToData.getContactor_address() != null) {
			dmsToData.setContactor_address(dmsToData.getContactor_address()
					.replaceAll("[^a-zA-Z0-9-_\u4e00-\u9fa5]", ""));
		}
	}

	/**
	 * 是否可以OB校验
	 */
	public boolean isObChecked(DmsToDataBean dmsToData) {
		boolean isOb = true;
		if ((replaceBlank(dmsToData.getOwner_name())).indexOf("4s") > -1
				|| (replaceBlank(dmsToData.getOwner_name())).indexOf("4S") > -1) {
			setIsObStr(dmsToData, Constant.K_03);
			isOb = false;
		}
		if ((replaceBlank(dmsToData.getOwner_name())).indexOf("试驾") > -1) {
			setIsObStr(dmsToData, Constant.K_04);
			isOb = false;
		}
		//ping.qi update 2014-07-28 车主姓名是否包含“直销车”
		if((replaceBlank(dmsToData.getOwner_name())).indexOf("直销车") > -1 ){
		    setIsObStr(dmsToData,Constant.K_21);
		    isOb = false;
		}
		if((replaceBlank(dmsToData.getOwner_name())).indexOf("邹志祥") > -1 ){
		    setIsObStr(dmsToData,Constant.K_22);
		    isOb = false;
		}
		if((replaceBlank(dmsToData.getMobile())).indexOf("13510944747") > -1 ){
		    setIsObStr(dmsToData,Constant.K_23);
		    isOb = false;
		}
		if (dmsToData.getDealer_sale_code() == null
				|| "".equals(replaceBlank(dmsToData.getDealer_sale_code()))) {
			setIsObStr(dmsToData, Constant.K_05);
			isOb = false;
		} else {
			if ("B99999".equals(replaceBlank(dmsToData.getDealer_sale_code()))) {
				setIsObStr(dmsToData, Constant.K_06);
				isOb = false;
			}
			if ("A11800".equals(replaceBlank(dmsToData.getDealer_sale_code()))) {
				setIsObStr(dmsToData, Constant.K_07);
				isOb = false;
			}
		}
		return isOb;
	}

	// 创建空错号信息表日志
	private DmsToErorBean createDmsToErorBean(DmsToDataBean dmsToData,String type) {
		DmsToErorBean dmsToEr = new DmsToErorBean();
		dmsToEr.setOwnerId(dmsToData.getOwner_id());
		dmsToEr.setVin(dmsToData.getVin());
		dmsToEr.setDealer_sale_code(dmsToData.getDealer_sale_code());
		dmsToEr.setOwner_name(dmsToData.getOwner_name());
		dmsToEr.setGender(dmsToData.getGender());
		dmsToEr.setAddress(dmsToData.getAddress());
		dmsToEr.setEmail(dmsToData.getE_mail());
		dmsToEr.setMobile(dmsToData.getMobile());
		dmsToEr.setPhone(dmsToData.getPhone());
		dmsToEr.setReback_date(null);
		dmsToEr.setContactor_name(dmsToData.getContactor_name());
		dmsToEr.setContactor_gender(dmsToData.getContactor_gender());
		dmsToEr.setContactor_phone(dmsToData.getContactor_phone());
		dmsToEr.setContactor_mobile(dmsToData.getContactor_mobile());
		dmsToEr.setContactor_province(dmsToData.getProvince());
		dmsToEr.setContactor_city(dmsToData.getCity());
		dmsToEr.setContactor_address(dmsToData.getContactor_address());
		dmsToEr.setContactor_email(dmsToData.getContactor_email());
		dmsToEr.setCreateDate(dmsToData.getCreateOwnerDate());
		dmsToEr.setRemark(dmsToData.getIsObStr());
		dmsToEr.setOdp1_code(dmsToData.getOdp1Code());
		dmsToEr.setOdp2_code(dmsToData.getOdp2Code());
		dmsToEr.setCertificate_type(dmsToData.getCertificate_type());
		dmsToEr.setCertificate_code(dmsToData.getCertificate_code());
		dmsToEr.setDataType(type);//反馈数据
		return dmsToEr;
	}

	/**
	 * 验证手机号码
	 * 
	 * @param mobiles
	 * @return
	 */
	public static boolean isMobileNO(String mobiles) {
		boolean flag = false;
		try {
			if (mobiles.length() < 11) {
				return flag;
			}
			if (mobiles.length() > 11) {
				int sIndex1 = mobiles.indexOf("13");
				int sIndex2 = mobiles.indexOf("15");
				int sIndex3 = mobiles.indexOf("18");
				int sIndex4 = mobiles.indexOf("14");
				//ping.qi update 20140829
				int sIndex5 = mobiles.indexOf("17");
				int sIndex6 = mobiles.indexOf("16");
				int sIndex7 = mobiles.indexOf("19");
				if ((mobiles.length() - sIndex1) >= 8 && sIndex1 > -1) {
					mobiles = mobiles.substring(sIndex1, sIndex1 + 11);
				} else if ((mobiles.length() - sIndex2) >= 8 && sIndex2 > -1) {
					mobiles = mobiles.substring(sIndex2, sIndex2 + 11);
				} else if ((mobiles.length() - sIndex3) >= 8 && sIndex3 > -1) {
					mobiles = mobiles.substring(sIndex3, sIndex3 + 11);
				} else if ((mobiles.length() - sIndex4) >= 8 && sIndex4 > -1) {
					mobiles = mobiles.substring(sIndex4, sIndex4 + 11);
				} else if ((mobiles.length() - sIndex5) >= 8 && sIndex5 > -1) {
					mobiles = mobiles.substring(sIndex5, sIndex5 + 11);
				}/*else if ((mobiles.length() - sIndex6) >= 8 && sIndex6 > -1) {
					mobiles = mobiles.substring(sIndex6, sIndex6 + 11);
				}else if ((mobiles.length() - sIndex7) >= 8 && sIndex7 > -1) {
					mobiles = mobiles.substring(sIndex7, sIndex7 + 11);
				}*/
			}
			//Pattern p = Pattern
				//	.compile("^(17951)?((\\+86)|(86))?((13[0-9])|(14[0-9])|(15[0-9])|(18[0-9])|(17[0-9]))\\d{8}$");
			Pattern p = Pattern
					.compile("^(17951)?((\\+86)|(86))?(1[3-9][0-9])\\d{8}$");
			Matcher m = p.matcher(mobiles);
			flag = m.matches();
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	/**
	 * 验证座机号码
	 * 
	 * @param mobiles
	 * @return
	 */
	public static boolean isPhoneNO(String Phone) {
		boolean flag = false;
		try {
			if (Phone.indexOf("-") > -1) {
				Phone = Phone.split("-")[1];
			}
			if (Phone.length() >= 7) {
				Pattern p = Pattern.compile("^[0-9]*$");
				Matcher m = p.matcher(Phone);
				flag = m.matches();
			}
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}
}

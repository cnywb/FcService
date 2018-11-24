package com.fc.bean;

import java.io.Serializable;
import java.util.List;

public class DmsRepairOrderBean
  implements Serializable
{
  private static final long serialVersionUID = 8476011621199052247L;
  private String order_id = "";
  private String ro_no = "";
  private Long ro_id = Long.valueOf(0L);
  private String dealer_service_code = "";
  private String dealer_name = "";
  private String brand = "";
  private String serise = "";
  private String model = "";
  private String color = "";
  private String vin = "";
  private String engine_no = "";
  private String lisence_no = "";
  private String ro_create_date = "";
  private String delivery_date = "";
  private String for_balance_time = "";
  private String ro_status = "";
  private String ro_type = "";
  private String labour_amount;
  private String repair_part_amount;
  private String sales_part_amount;
  private String add_item_amount;
  private String over_item_amount;
  private String repair_amount;
  private String balance_amount;
  private String sales_date = "";
  private String in_mileage;
  private String remark = "";
  private String create_date = "";
  private String update_date = "";
  private String importDate = "";
  private String time_stamp = "";
  private String sequnce = "";
  private String owner_id = "";
  private Long is_valid = Long.valueOf(0L);
  private String service_advisor_code;
  private String service_advisor_name;
  private String big_org_name;
  private String out_mileage = "";
  private String deliverer;
  private String deliverer_gender;
  private String deliverer_mobile;
  private String deliverer_phone;
  private String iscsi;
  private List<DmsAddItemBean> add_item_list;
  private List<DmsLabourPartBean> repair_labour_list;
  private List<DmsRepairPartBean> repair_part_list;
  private List<DmsSalesPartBean> sales_part_list;
  private String repair_type_code;
  
  public String getOrder_id()
  {
    return this.order_id;
  }
  
  public void setOrder_id(String orderId)
  {
    this.order_id = orderId;
  }
  
  public String getRo_no()
  {
    return this.ro_no;
  }
  
  public void setRo_no(String roNo)
  {
    this.ro_no = roNo;
  }
  
  public Long getRo_id()
  {
    return this.ro_id;
  }
  
  public void setRo_id(Long roId)
  {
    this.ro_id = roId;
  }
  
  public String getDealer_service_code()
  {
    return this.dealer_service_code;
  }
  
  public void setDealer_service_code(String dealerServiceCode)
  {
    this.dealer_service_code = dealerServiceCode;
  }
  
  public String getDealer_name()
  {
    return this.dealer_name;
  }
  
  public void setDealer_name(String dealerName)
  {
    this.dealer_name = dealerName;
  }
  
  public String getBrand()
  {
    return this.brand;
  }
  
  public void setBrand(String brand)
  {
    this.brand = brand;
  }
  
  public String getSerise()
  {
    return this.serise;
  }
  
  public void setSerise(String serise)
  {
    this.serise = serise;
  }
  
  public String getModel()
  {
    return this.model;
  }
  
  public void setModel(String model)
  {
    this.model = model;
  }
  
  public String getColor()
  {
    return this.color;
  }
  
  public void setColor(String color)
  {
    this.color = color;
  }
  
  public String getVin()
  {
    return this.vin;
  }
  
  public void setVin(String vin)
  {
    this.vin = vin;
  }
  
  public String getEngine_no()
  {
    return this.engine_no;
  }
  
  public void setEngine_no(String engineNo)
  {
    this.engine_no = engineNo;
  }
  
  public String getLisence_no()
  {
    return this.lisence_no;
  }
  
  public void setLisence_no(String lisenceNo)
  {
    this.lisence_no = lisenceNo;
  }
  
  public String getRo_create_date()
  {
    return this.ro_create_date;
  }
  
  public void setRo_create_date(String roCreateDate)
  {
    this.ro_create_date = roCreateDate;
  }
  
  public String getDelivery_date()
  {
    return this.delivery_date;
  }
  
  public void setDelivery_date(String deliveryDate)
  {
    this.delivery_date = deliveryDate;
  }
  
  public String getFor_balance_time()
  {
    return this.for_balance_time;
  }
  
  public void setFor_balance_time(String forBalanceTime)
  {
    this.for_balance_time = forBalanceTime;
  }
  
  public String getRo_status()
  {
    return this.ro_status;
  }
  
  public void setRo_status(String roStatus)
  {
    this.ro_status = roStatus;
  }
  
  public String getRo_type()
  {
    return this.ro_type;
  }
  
  public void setRo_type(String roType)
  {
    this.ro_type = roType;
  }
  
  public String getLabour_amount()
  {
    return this.labour_amount;
  }
  
  public void setLabour_amount(String labourAmount)
  {
    this.labour_amount = labourAmount;
  }
  
  public String getRepair_part_amount()
  {
    return this.repair_part_amount;
  }
  
  public void setRepair_part_amount(String repairPartAmount)
  {
    this.repair_part_amount = repairPartAmount;
  }
  
  public String getSales_part_amount()
  {
    return this.sales_part_amount;
  }
  
  public void setSales_part_amount(String salesPartAmount)
  {
    this.sales_part_amount = salesPartAmount;
  }
  
  public String getAdd_item_amount()
  {
    return this.add_item_amount;
  }
  
  public void setAdd_item_amount(String addItemAmount)
  {
    this.add_item_amount = addItemAmount;
  }
  
  public String getOver_item_amount()
  {
    return this.over_item_amount;
  }
  
  public void setOver_item_amount(String overItemAmount)
  {
    this.over_item_amount = overItemAmount;
  }
  
  public String getRepair_amount()
  {
    return this.repair_amount;
  }
  
  public void setRepair_amount(String repairAmount)
  {
    this.repair_amount = repairAmount;
  }
  
  public String getBalance_amount()
  {
    return this.balance_amount;
  }
  
  public void setBalance_amount(String balanceAmount)
  {
    this.balance_amount = balanceAmount;
  }
  
  public String getSales_date()
  {
    return this.sales_date;
  }
  
  public void setSales_date(String salesDate)
  {
    this.sales_date = salesDate;
  }
  
  public String getIn_mileage()
  {
    return this.in_mileage;
  }
  
  public void setIn_mileage(String inMileage)
  {
    this.in_mileage = inMileage;
  }
  
  public String getRemark()
  {
    return this.remark;
  }
  
  public void setRemark(String remark)
  {
    this.remark = remark;
  }
  
  public String getCreate_date()
  {
    return this.create_date;
  }
  
  public void setCreate_date(String createDate)
  {
    this.create_date = createDate;
  }
  
  public String getUpdate_date()
  {
    return this.update_date;
  }
  
  public void setUpdate_date(String updateDate)
  {
    this.update_date = updateDate;
  }
  
  public String getImportDate()
  {
    return this.importDate;
  }
  
  public void setImportDate(String importDate)
  {
    this.importDate = importDate;
  }
  
  public String getTime_stamp()
  {
    return this.time_stamp;
  }
  
  public void setTime_stamp(String timeStamp)
  {
    this.time_stamp = timeStamp;
  }
  
  public String getSequnce()
  {
    return this.sequnce;
  }
  
  public void setSequnce(String sequnce)
  {
    this.sequnce = sequnce;
  }
  
  public String getOwner_id()
  {
    return this.owner_id;
  }
  
  public void setOwner_id(String ownerId)
  {
    this.owner_id = ownerId;
  }
  
  public Long getIs_valid()
  {
    return this.is_valid;
  }
  
  public void setIs_valid(Long isValid)
  {
    this.is_valid = isValid;
  }
  
  public String getService_advisor_code()
  {
    return this.service_advisor_code;
  }
  
  public void setService_advisor_code(String serviceAdvisorCode)
  {
    this.service_advisor_code = serviceAdvisorCode;
  }
  
  public String getService_advisor_name()
  {
    return this.service_advisor_name;
  }
  
  public void setService_advisor_name(String serviceAdvisorName)
  {
    this.service_advisor_name = serviceAdvisorName;
  }
  
  public String getBig_org_name()
  {
    return this.big_org_name;
  }
  
  public void setBig_org_name(String bigOrgName)
  {
    this.big_org_name = bigOrgName;
  }
  
  public String getOut_mileage()
  {
    return this.out_mileage;
  }
  
  public void setOut_mileage(String outMileage)
  {
    this.out_mileage = outMileage;
  }
  
  public List<DmsAddItemBean> getAdd_item_list()
  {
    return this.add_item_list;
  }
  
  public void setAdd_item_list(List<DmsAddItemBean> addItemList)
  {
    this.add_item_list = addItemList;
  }
  
  public List<DmsLabourPartBean> getRepair_labour_list()
  {
    return this.repair_labour_list;
  }
  
  public void setRepair_labour_list(List<DmsLabourPartBean> repairLabourList)
  {
    this.repair_labour_list = repairLabourList;
  }
  
  public List<DmsRepairPartBean> getRepair_part_list()
  {
    return this.repair_part_list;
  }
  
  public void setRepair_part_list(List<DmsRepairPartBean> repairPartList)
  {
    this.repair_part_list = repairPartList;
  }
  
  public List<DmsSalesPartBean> getSales_part_list()
  {
    return this.sales_part_list;
  }
  
  public void setSales_part_list(List<DmsSalesPartBean> salesPartList)
  {
    this.sales_part_list = salesPartList;
  }
  
  public String getDeliverer()
  {
    return this.deliverer;
  }
  
  public void setDeliverer(String deliverer)
  {
    this.deliverer = deliverer;
  }
  
  public String getDeliverer_gender()
  {
    return this.deliverer_gender;
  }
  
  public void setDeliverer_gender(String deliverer_gender)
  {
    this.deliverer_gender = deliverer_gender;
  }
  
  public String getDeliverer_mobile()
  {
    return this.deliverer_mobile;
  }
  
  public void setDeliverer_mobile(String deliverer_mobile)
  {
    this.deliverer_mobile = deliverer_mobile;
  }
  
  public String getDeliverer_phone()
  {
    return this.deliverer_phone;
  }
  
  public void setDeliverer_phone(String deliverer_phone)
  {
    this.deliverer_phone = deliverer_phone;
  }
  
  public String getIscsi()
  {
    return this.iscsi;
  }
  
  public void setIscsi(String iscsi)
  {
    this.iscsi = iscsi;
  }

public String getRepair_type_code() {
	return repair_type_code;
}

public void setRepair_type_code(String repair_type_code) {
	this.repair_type_code = repair_type_code;
}
  
  
}

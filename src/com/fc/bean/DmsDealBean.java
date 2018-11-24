package com.fc.bean;

public class DmsDealBean  implements java.io.Serializable{


		private static final long serialVersionUID = 6199508978264375406L;
		
		private String time_stamp;
		private String sequnce;
		private Double id;
		private String name="";
		private String vin="";
		private String campaign_code="";
		private Double amount=0.0;
		private Double use_amount=0.0;
		private String valid_begin_date="";
		private String valid_end_date="";
		private String dealer_sale_code="";
		private String dealer_service_code="";
		private String status="";
		private String create_date="";
		private String user_name="";
		private Double lowest_amount=0.0;
		private Double limit_deduct=0.0;
		public Double getId() {
			return id;
		}
		public void setId(Double id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getVin() {
			return vin;
		}
		public void setVin(String vin) {
			this.vin = vin;
		}
		public String getCampaign_code() {
			return campaign_code;
		}
		public void setCampaign_code(String campaign_code) {
			this.campaign_code = campaign_code;
		}
		public Double getAmount() {
			return amount;
		}
		public void setAmount(Double amount) {
			this.amount = amount;
		}

		public String getValid_begin_date() {
			return valid_begin_date;
		}
		public void setValid_begin_date(String valid_begin_date) {
			this.valid_begin_date = valid_begin_date;
		}
		public String getValid_end_date() {
			return valid_end_date;
		}
		public void setValid_end_date(String valid_end_date) {
			this.valid_end_date = valid_end_date;
		}


		public String getCreate_date() {
			return create_date;
		}
		public void setCreate_date(String create_date) {
			this.create_date = create_date;
		}
		public String getUser_name() {
			return user_name;
		}
		public void setUser_name(String user_name) {
			this.user_name = user_name;
		}
		public Double getLowest_amount() {
			return lowest_amount;
		}
		public void setLowest_amount(Double lowest_amount) {
			this.lowest_amount = lowest_amount;
		}
		public Double getLimit_deduct() {
			return limit_deduct;
		}
		public void setLimit_deduct(Double limit_deduct) {
			this.limit_deduct = limit_deduct;
		}
		public String getDealer_sale_code() {
			return dealer_sale_code;
		}
		public void setDealer_sale_code(String dealer_sale_code) {
			this.dealer_sale_code = dealer_sale_code;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public Double getUse_amount() {
			return use_amount;
		}
		public void setUse_amount(Double use_amount) {
			this.use_amount = use_amount;
		}
		public String getDealer_service_code() {
			return dealer_service_code;
		}
		public void setDealer_service_code(String dealer_service_code) {
			this.dealer_service_code = dealer_service_code;
		}
		public String getTime_stamp() {
			return time_stamp;
		}
		public void setTime_stamp(String timeStamp) {
			time_stamp = timeStamp;
		}
		public String getSequnce() {
			return sequnce;
		}
		public void setSequnce(String sequnce) {
			this.sequnce = sequnce;
		}
	}
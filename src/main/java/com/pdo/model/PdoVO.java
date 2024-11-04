package com.pdo.model;

import java.sql.Date;

public class PdoVO implements java.io.Serializable {
	
	private Integer pdoId;
	private Integer mebId;
	private Date pdoDate;
	private Integer pdTotalPrice;
	private String pdoStatus;
	private String paymentStatus;
	private String shippingAddr;
	private String shippingMethod;
	private Integer pdoReviewRate;
	private String pdoReviewComm;
	private Date createdTime;
	
	public Integer getPdoId() {
		return pdoId;
	}
	public void setPdoId(Integer pdoId) {
		this.pdoId = pdoId;
	}
	public Integer getMebId() {
		return mebId;
	}
	public void setMebId(Integer mebId) {
		this.mebId = mebId;
	}
	public Date getPdoDate() {
		return pdoDate;
	}
	public void setPdoDate(Date pdoDate) {
		this.pdoDate = pdoDate;
	}
	public Integer getPdTotalPrice() {
		return pdTotalPrice;
	}
	public void setPdTotalPrice(Integer pdTotalPrice) {
		this.pdTotalPrice = pdTotalPrice;
	}
	public String getPdoStatus() {
		return pdoStatus;
	}
	public void setPdoStatus(String pdoStatus) {
		this.pdoStatus = pdoStatus;
	}
	public String getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public String getShippingAddr() {
		return shippingAddr;
	}
	public void setShippingAddr(String shippingAddr) {
		this.shippingAddr = shippingAddr;
	}
	public String getShippingMethod() {
		return shippingMethod;
	}
	public void setShippingMethod(String shippingMethod) {
		this.shippingMethod = shippingMethod;
	}
	public Integer getPdoReviewRate() {
		return pdoReviewRate;
	}
	public void setPdoReviewRate(Integer pdoReviewRate) {
		this.pdoReviewRate = pdoReviewRate;
	}
	public String getPdoReviewComm() {
		return pdoReviewComm;
	}
	public void setPdoReviewComm(String pdoReviewComm) {
		this.pdoReviewComm = pdoReviewComm;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	
}

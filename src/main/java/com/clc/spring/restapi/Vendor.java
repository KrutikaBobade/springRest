package com.clc.spring.restapi;

import java.util.List;

public class Vendor {
	private int vendorId;
	private String vendorName;
	
	
	
	
	private List<Product> listOfProduct;
	public int getVendorId() {
		return vendorId;
	}
	public void setVendorId(int vendorId) {
		this.vendorId = vendorId;
	}
	public String getVendorName() {
		return vendorName;
	}
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	public List<Product> getListOfProduct() {
		return listOfProduct;
	}
	public void setListOfProduct(List<Product> listOfProduct) {
		this.listOfProduct = listOfProduct;
	}
	@Override
	public String toString() {
		return "\n [vendorId=" + vendorId + ", vendorName=" + vendorName + ", listOfProduct=" + listOfProduct + "]";
	}
	public Vendor(int vendorId, String vendorName, List<Product> listOfProduct) {
		super();
		this.vendorId = vendorId;
		this.vendorName = vendorName;
		this.listOfProduct = listOfProduct;
	}
	public Vendor() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}

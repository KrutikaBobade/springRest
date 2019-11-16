	package com.clc.spring.restapi;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/vendor")
public class VendorController {
public List<Vendor> listOfvendor = new ArrayList<Vendor>();
{
	List<Product> listOfProduct = new ArrayList<Product>();
	listOfProduct.add(new Product(545, "headphone"));
	listOfProduct.add(new Product(7899, "Book"));
	listOfProduct.add(new Product(890, "pen"));
	listOfvendor.add(new Vendor(675, "Amazon", listOfProduct));
	
	
}
	
	@RequestMapping(value = "/",method = RequestMethod.POST)
	public String addvendor(@RequestBody Vendor vendor) {
		System.out.println("Inside add vendor" + vendor);
		listOfvendor.add(vendor);
		return "vendor Added Successfully";
	}
	
	@RequestMapping(value = "/{vid}",method = RequestMethod.GET)
	public Vendor getVendor(@PathVariable("vid") int vendorId) {
		System.out.println("Inside get vendor");
		for (Vendor ven : listOfvendor) {
			if(ven.getVendorId()== vendorId) {
				return ven;
			}
		}
		System.out.println("vendor not found...!");
		return null;
	}
	
	@RequestMapping(value = "/",method = RequestMethod.GET)
	public List<Vendor> getAllVendor() {
		return listOfvendor;
	}
	
	@RequestMapping(value = "/{vid}",method = RequestMethod.DELETE)
	public String deleteEmployee(@PathVariable("vid") int vendorId) {
		System.out.println("Inside delete vendor");
		for (Vendor ven : listOfvendor) {
			if(ven.getVendorId() == vendorId) {
				listOfvendor.remove(ven);
				return "vendor deleted Successfully";
			}
		}
		return "Emp not found...so cannot delete!";
	}
	
	@RequestMapping(value = "/{vid}",method = RequestMethod.PUT)
	public Vendor updateEmployee(@RequestBody Vendor vendor) {
		System.out.println("Inside update employee");
		for (Vendor vendo : listOfvendor) {
			if(vendo.getVendorId()== vendor.getVendorId()) {
				vendo.setVendorName(vendor.getVendorName());
				vendo.setListOfProduct(vendor.getListOfProduct());
				System.out.println("vendor details are updated...!");
				return getVendor(vendor.getVendorId());
			}
		}
		return null;
	}
		
	
}

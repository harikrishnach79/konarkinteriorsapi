package in.konarkinteriors.app.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ki_tbl_servicerequests")
public class ServiceRequest {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int reqId;
	private String customerRequestType;
	private String customerEmail;
	private String customerName;
	private String customerAddressLine1;
	private String customerAddressLine2;
	private String customerCity;
	private String customerPincode;
	private String date;
	private String slot;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getSlot() {
		return slot;
	}

	public void setSlot(String slot) {
		this.slot = slot;
	}

	public ServiceRequest() {
		super();
	}

	public ServiceRequest(int reqId, String customerRequestType, String customerEmail, String customerName,
			String customerAddressLine1, String customerAddressLine2, String customerCity, String customerPincode,
			String date, String slot) {
		super();
		this.reqId = reqId;
		this.customerRequestType = customerRequestType;
		this.customerEmail = customerEmail;
		this.customerName = customerName;
		this.customerAddressLine1 = customerAddressLine1;
		this.customerAddressLine2 = customerAddressLine2;
		this.customerCity = customerCity;
		this.customerPincode = customerPincode;
		this.date = date;
		this.slot = slot;
	}

	public int getReqId() {
		return reqId;
	}

	public void setReqId(int reqId) {
		this.reqId = reqId;
	}

	public String getCustomerRequestType() {
		return customerRequestType;
	}

	public void setCustomerRequestType(String customerRequestType) {
		this.customerRequestType = customerRequestType;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerAddressLine1() {
		return customerAddressLine1;
	}

	public void setCustomerAddressLine1(String customerAddressLine1) {
		this.customerAddressLine1 = customerAddressLine1;
	}

	public String getCustomerAddressLine2() {
		return customerAddressLine2;
	}

	public void setCustomerAddressLine2(String customerAddressLine2) {
		this.customerAddressLine2 = customerAddressLine2;
	}

	public String getCustomerCity() {
		return customerCity;
	}

	public void setCustomerCity(String customerCity) {
		this.customerCity = customerCity;
	}

	public String getCustomerPincode() {
		return customerPincode;
	}

	public void setCustomerPincode(String customerPincode) {
		this.customerPincode = customerPincode;
	}

}

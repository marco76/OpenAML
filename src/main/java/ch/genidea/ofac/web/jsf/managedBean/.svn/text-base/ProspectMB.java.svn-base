package ch.genidea.ofac.web.jsf.managedBean;

import ch.genidea.checknames.model.Prospect;
import ch.genidea.checknames.service.ProspectService;

public class ProspectMB {
	
	String name;
	String familyName;
	String address;
	String company;
	String email;
	String ofacPackage;
	String notes;
	String city;
	String country;
	String phoneNumber;
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	private ProspectService service;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFamilyName() {
		return familyName;
	}
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getOfacPackage() {
		return ofacPackage;
	}
	public void setOfacPackage(String ofacPackage) {
		this.ofacPackage = ofacPackage;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	

	
	public String saveProspect(){
		
		Prospect entry = new Prospect();
		entry.setFirstName(name);
		entry.setFamilyName(familyName);
		entry.setAddress(address);
		entry.setEmail(email);
		entry.setCity(city);
		entry.setCompany(company);
		entry.setCountry(country);
		entry.setPhoneNumber(phoneNumber);
		service.save(entry);
		
		return null;
	}
	public void setService(ProspectService service) {
		this.service = service;
	}

}

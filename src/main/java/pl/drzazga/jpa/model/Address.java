package pl.drzazga.jpa.model;

import javax.persistence.Embeddable;

import org.hibernate.validator.constraints.NotBlank;

@Embeddable
public class Address {

	@NotBlank
	private String zipCode;
	
	@NotBlank
	private String city;
	
	@NotBlank
	private String street;
	
	@NotBlank
	private String country;
	
	public Address(String zipCode, String city, String street, String country) {
		this.zipCode = zipCode;
		this.city = city;
		this.street = street;
		this.country = country;
	}

	public Address() {}
	
	public String getZipCode() {
		return zipCode;
	}

	public String getCity() {
		return city;
	}

	public String getStreet() {
		return street;
	}
}

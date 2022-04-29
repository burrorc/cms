package com.yourautospa.cms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="vehicle")
public class Vehicle {
	
	@Id
	@Column(name="plate")
	private String plate;
	
	@Column(name="make")
	private String make;
	
	@Column(name="model")
	private String model;
	
	@Column(name="year")
	private int year;
	
	@Column(name="customer_id")
	private int customerId;
	
	//@Column(name="subscription")
	private int subscription;
	
	public Vehicle() {
		
	}
	
	public Vehicle(String plate) {
		this.plate = plate;
	}

	public Vehicle(String plate, String make, String model, int year, int customerId, int subscription) {
		this.plate = plate;
		this.make = make;
		this.model = model;
		this.year = year;
		this.customerId = customerId;
		this.subscription = subscription;
	}

	public String getPlate() {
		return plate;
	}

	public void setPlate(String plate) {
		this.plate = plate;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getSubscription() {
		return subscription;
	}

	public void setSubscription(int subscription) {
		this.subscription = subscription;
	}

	@Override
	public String toString() {
		return "Vehicle [plate=" + plate + ", make=" + make + ", model=" + model + ", year=" + year + ", customerId="
				+ customerId + ", subscription=" + subscription + "]";
	}
	
}

package com.yourautospa.cms.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="subscription")
public class Subscription {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="vehicle_plate")
	private String vehiclePlate;
	
	@Column(name="wash_name")
	private String washName;
	
	
	
	
	public Subscription() {
		
	}

	public Subscription(String vehiclePlate, String washName) {
		this.vehiclePlate = vehiclePlate;
		this.washName = washName;
	}

	public Subscription(int id, String vehiclePlate, String washName) {
		this.id = id;
		this.vehiclePlate = vehiclePlate;
		this.washName = washName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getVehiclePlate() {
		return vehiclePlate;
	}

	public void setVehiclePlate(String vehiclePlate) {
		this.vehiclePlate = vehiclePlate;
	}

	public String getWashName() {
		return washName;
	}

	public void setWashName(String washName) {
		this.washName = washName;
	}

	

	@Override
	public String toString() {
		return "Subscription [id=" + id + ", vehiclePlate=" + vehiclePlate + ", washName=" + washName + "]";
	}
	
	

}

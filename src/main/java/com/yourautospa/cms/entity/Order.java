package com.yourautospa.cms.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="order_table")
public class Order {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="name")
	private String name;
	
	@Column(name = "created_on")
	private LocalDateTime createdOn;
	
	@Column(name = "completed_on")
	private LocalDateTime completedOn;
	
	@Column(name="plate")
	private String plate;
	
	@Column(name="payment")
	private String payment;
	
	public Order() {
		this.createdOn = LocalDateTime.now();
	}

	public Order(String name) {
		this.name = name;
	}

	public Order(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDateTime getCompletedOn() {
		return completedOn;
	}

	public void setCompletedOn(LocalDateTime completedOn) {
		this.completedOn = completedOn;
	}

	public String getPlate() {
		return plate;
	}

	public void setPlate(String plate) {
		this.plate = plate;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public LocalDateTime getCreatedOn() {
		return createdOn;
	}

	
//	public LocalDateTime getCreatedOn() {
//		return createdOn;
//	}
//
//	public void setCreatedOn(LocalDateTime createdOn) {
//		this.createdOn = createdOn;
//	}
//
//	@Override
//	public String toString() {
//		return "Order [id=" + id + ", name=" + name + ", createdOn=" + createdOn + "]";
//	}
	
	
	

	
}

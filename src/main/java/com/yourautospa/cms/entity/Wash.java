package com.yourautospa.cms.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="wash")
public class Wash {
	
	@Id
	@Column(name="id")
	private int id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="price")
	private BigDecimal price;
	
	@OneToMany(fetch=FetchType.LAZY, 
			mappedBy="subscription",
			cascade= {CascadeType.DETACH,
			CascadeType.MERGE,
			CascadeType.PERSIST,
			CascadeType.REFRESH})
	//@JoinColumn(name="subscription")
	private List<Vehicle> vehicles;
	
	public Wash() {
		
	}
	
	public Wash(int id) {
		this.id = id;
	}

	public Wash(int id, String name, BigDecimal price) {
		this.id = id;
		this.name = name;
		this.price = price;
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

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public List<Vehicle> getVehicles() {
		return vehicles;
	}

	public void setVehicles(List<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}

	//convenience method for vehicles
	public void addVehicle(Vehicle theVehicle) {
		if(vehicles == null) {
			vehicles = new ArrayList<>();
		}
		
		vehicles.add(theVehicle);
	}
	
	@Override
	public String toString() {
		return "Wash [id=" + id + ", name=" + name + ", price=" + price + "]";
	}

	
	
}

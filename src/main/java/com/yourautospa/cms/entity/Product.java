package com.yourautospa.cms.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="product")
public class Product {
	
	@Id
	@Column(name="id")
	private int id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="price")
	private BigDecimal price;
	
	@Column(name="subscription")
	private boolean subscription;
	
	@Column(name="wash")
	private boolean wash;
	
	@Column(name="extra")
	private boolean extra;
	
	@Column(name="lobby")
	private boolean lobby;
	
	@OneToMany(fetch=FetchType.LAZY, 
			mappedBy="subscription",
			cascade= {CascadeType.DETACH,
			CascadeType.MERGE,
			CascadeType.PERSIST,
			CascadeType.REFRESH})
	private List<Vehicle> vehicles;
	
	@ManyToMany(mappedBy = "products")
    private List<Order> orders;
	
	public Product() {
		if (orders == null)
            orders = new ArrayList<>();
		
	}
	
	public Product(int id) {
		this.id = id;
		if (orders == null)
            orders = new ArrayList<>();
	}

	public Product(int id, String name, BigDecimal price) {
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
	
	public boolean isSubscription() {
		return subscription;
	}

	public void setSubscription(boolean subscription) {
		this.subscription = subscription;
	}

	public boolean isWash() {
		return wash;
	}

	public void setWash(boolean wash) {
		this.wash = wash;
	}

	public boolean isExtra() {
		return extra;
	}

	public void setExtra(boolean extra) {
		this.extra = extra;
	}

	public boolean isLobby() {
		return lobby;
	}

	public void setLobby(boolean lobby) {
		this.lobby = lobby;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", price=" + price + "]";
	}

	
	
}

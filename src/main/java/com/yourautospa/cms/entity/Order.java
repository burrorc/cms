package com.yourautospa.cms.entity;

import java.math.BigDecimal;
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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="order_table2")
public class Order {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name = "created_on")
	private LocalDateTime createdOn;
	
	@Column(name = "completed_on")
	private LocalDateTime completedOn;
	
	@ManyToOne(cascade= {CascadeType.DETACH,
			CascadeType.REFRESH})
	@JoinColumn(name="plate")
	private Vehicle vehicle;
	
	@Column(name="total")
	private BigDecimal total;
	
	@Column(name="payment")
	private String payment;
	
	@Column(name="amount_paid")
	private BigDecimal amountPaid;
	
	@Column(name="comments")
	private String comments;
	
	@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "order_items2",
            joinColumns = {@JoinColumn(name = "order_id")},
            inverseJoinColumns = {@JoinColumn(name = "product_id")})
    private List<Product> products;
	
	
	public Order() {
		this.createdOn = LocalDateTime.now();
		if (products == null)
            products = new ArrayList<>();
	}

	public Order(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getCompletedOn() {
		return completedOn;
	}

	public void setCompletedOn() {
		this.completedOn = LocalDateTime.now();
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public BigDecimal getAmountPaid() {
		return amountPaid;
	}

	public void setAmountPaid(BigDecimal amountPaid) {
		this.amountPaid = amountPaid;
	}

	public LocalDateTime getCreatedOn() {
		return createdOn;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	
}

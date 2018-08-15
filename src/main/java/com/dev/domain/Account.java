package com.dev.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Account")
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private long amount;
	@NotNull
	private String name;
	private String address;
	private Date createdDate;
	
	public Account() {
	}

	
	
	public Account(long amount, String name, String address) {
		super();
		this.amount=amount;
		this.name = name;
		this.address = address;
		this.createdDate = new Date();
	}



	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public void credit(long amount)
	{
		this.amount = this.amount+amount;
		System.out.println("Amount credit >>"+ this.amount);
	}
	
	public void debit(long amount)
	{
		this.amount = this.amount-amount;
		System.out.println("Amount debit >>"+ this.amount);
	}
	
}

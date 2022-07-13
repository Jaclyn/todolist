package com.test.todolist.db.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="task")
public class Task {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String description;
	private Date creationDate;
	private Date lastUpdatedDate;
	private String status;
	private String owner;
	
	public Task() {};
	
	public Task(int id) {
		this.id = id;
	}
	
	public Task(String description) {
		this.description = description;
	}

	public Task(int id, String description, java.sql.Date creationDate, java.sql.Date lastUpdatedDate, String status, String owner) {
		this.id = id;
		this.description = description;
		this.creationDate = creationDate;
		this.lastUpdatedDate = lastUpdatedDate;
		this.status = status;
		this.owner = owner;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	public void setLastUpdatedDate(Date lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}
	
}

package com.marlabs.rmbs.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "reference_documents")
public class ReferenceDocuments {
	
	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private int id;
	  private String path;
	  private String name;
	  private Boolean deleted;
	  public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}



	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Boolean getDeleted() {
		return deleted;
	}
	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
	
	
	

}

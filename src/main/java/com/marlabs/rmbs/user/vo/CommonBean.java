package com.marlabs.rmbs.user.vo;

public class CommonBean {
	
	private Integer id;
	private String  name;

	private String manager;
	
	
	public CommonBean(){}
	
	public CommonBean(Integer id,String name){
		this.id=id;
		this.name=name; 
	}	
	
	public CommonBean(Integer id,String name,String manager){
		this.id=id;		
		this.manager=manager; 
		this.name=name; 
	}	
		
	public CommonBean(Integer id){
		this.id=id;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}
	
	
	
	
}

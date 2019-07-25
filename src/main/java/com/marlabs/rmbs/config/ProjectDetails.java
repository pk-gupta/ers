package com.marlabs.rmbs.config;

public class ProjectDetails {

	
	  private int id;
	  
	  
	  private String projectCode;
	  private String projectDesc;
	  

	  
	 
	  
	  public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProjectCode() {
		return projectCode;
	}
	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}
	public String getProjectDesc() {
		return projectDesc;
	}
	public void setProjectDesc(String projectDesc) {
		this.projectDesc = projectDesc;
	}
	public String getProjectManager() {
		return projectManager;
	}
	public void setProjectManager(String projectManager) {
		this.projectManager = projectManager;
	}
	public int getProjectManagerId() {
		return projectManagerId;
	}
	public void setProjectManagerId(int i) {
		this.projectManagerId = i;
	}
	private String projectManager;
	 
	  private int projectManagerId;
	  
	private int projectId;
	public int getProjectId() {
		return projectId;
	}
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public ProjectDetails(Integer id, int projectId){
		this.id=id;
		this.projectId=projectId;
	}
	
	public ProjectDetails(){
		
	}
	
}

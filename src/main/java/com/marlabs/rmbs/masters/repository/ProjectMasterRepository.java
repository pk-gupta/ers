package com.marlabs.rmbs.masters.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.marlabs.rmbs.config.ProjectDetails;
import com.marlabs.rmbs.entities.ProjectMaster;
import com.marlabs.rmbs.user.vo.CommonBean;

@Repository
public interface ProjectMasterRepository extends JpaRepository<ProjectMaster,Integer>{

	@Query("select new com.marlabs.rmbs.user.vo.CommonBean(id,projectCode,projectManager)"
			+ " FROM ProjectMaster")
	public List<CommonBean> findProjects();
	
	
	@Query("FROM ProjectMaster where projectManagerId.id=:userId")
	List<ProjectMaster> isEmployeeProjectManager( @Param("userId") Integer userId);
	
	 
	/* @Query("SELECT new com.marlabs.rmbs.config.smtp.email.ProjectDetails(id,projectId)"
	 		+ " FROM ProjectMaster")
	public List<ProjectDetails> getProjectsFullList();*/

	 @Query("FROM ProjectMaster where projectCode=:employeeProjectId")
	public ProjectMaster findProjectManager(@Param("employeeProjectId") String employeeProjectId);
	 
	 
	 @Query("FROM ProjectMaster where projectId=:projectId")
	 public ProjectMaster findProject(@Param("projectId") int projectId);
	 

}

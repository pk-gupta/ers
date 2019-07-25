package com.marlabs.rmbs.masters.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.marlabs.rmbs.entities.EmployeeProject;
import com.marlabs.rmbs.user.vo.EmployeeProjectVo;
@Repository
public interface EmployeeProjectDetailsRepository extends JpaRepository<EmployeeProject, Integer> {
	@Query("select new com.marlabs.rmbs.user.vo.EmployeeProjectVo(id,employeeProjectCode,employeeProject)"
			+ " FROM EmployeeProject where employeeId =:userId or employeeProjectCode =:employeeProjectId")
	public List<EmployeeProjectVo> getProjectDetails(@Param("userId") String userId,@Param("employeeProjectId") String employeeProjectId);
	}

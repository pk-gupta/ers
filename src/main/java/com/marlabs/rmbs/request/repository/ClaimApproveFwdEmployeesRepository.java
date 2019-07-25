package com.marlabs.rmbs.request.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.marlabs.rmbs.entities.ClaimApproveFwdEmployees;
import com.marlabs.rmbs.user.vo.CommonBean;



@Repository
public interface ClaimApproveFwdEmployeesRepository extends JpaRepository<ClaimApproveFwdEmployees, Integer>{
	
	
	
	@Query("from ClaimApproveFwdEmployees where approveFwdFromEmployeeId.id = :userId")
	 public List<ClaimApproveFwdEmployees> listApproveByUser(@Param("userId") Integer userId);
	
	
	@Query("select new java.lang.Integer(approveFwdFromEmployeeId.id) from "
			+ "ClaimApproveFwdEmployees where approveFwdToEmployeeId.id = :userId group by approveFwdFromEmployeeId.id")
	public List<Integer> listApproveFromoUser(@Param("userId") Integer userId);
	
	
	@Query("select new com.marlabs.rmbs.user.vo.CommonBean(approveFwdToEmployeeId.id,approveFwdToEmployeeId.cn)"
			+ " from ClaimApproveFwdEmployees where approveFwdFromEmployeeId.id = :userId")
	public List<CommonBean> listApproveByUserCommon(@Param("userId") Integer userId);
	
}

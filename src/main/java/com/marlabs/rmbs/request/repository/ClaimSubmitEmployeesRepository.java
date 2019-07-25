package com.marlabs.rmbs.request.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.marlabs.rmbs.entities.ClaimSubmitEmployees;
import com.marlabs.rmbs.user.vo.CommonBean;


@Repository
public interface ClaimSubmitEmployeesRepository extends JpaRepository<ClaimSubmitEmployees, Integer>{
	
	
	@Query("from ClaimSubmitEmployees where submitFromEmployeeId.id = :userId")
	public List<ClaimSubmitEmployees> listSubmitByUser(@Param("userId") Integer userId);
	
	
	@Query("select new java.lang.Integer(submitFromEmployeeId.id) "
			+ "from ClaimSubmitEmployees where submitToEmployeeId.id = :userId group by submitFromEmployeeId.id")
	public List<Integer> listSubmitFromUser(@Param("userId") Integer userId);
	
	
	
	
	@Query("select new com.marlabs.rmbs.user.vo.CommonBean(submitToEmployeeId.id,submitToEmployeeId.cn)"
			+ " from ClaimSubmitEmployees where submitFromEmployeeId.id = :userId")
	public List<CommonBean> listSubmitByUserCommon(@Param("userId") Integer userId);
	
	
	
}

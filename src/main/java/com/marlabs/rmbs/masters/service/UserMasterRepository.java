package com.marlabs.rmbs.masters.service;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.marlabs.rmbs.config.ReimbursPerson;
import com.marlabs.rmbs.entities.UserMaster;
import com.marlabs.rmbs.user.vo.CommonBean;

public interface UserMasterRepository extends JpaRepository<UserMaster,Integer>{

	
	@Query("SELECT u FROM UserMaster u WHERE u.cn LIKE CONCAT(:username,'%') or LOWER(u.cn) LIKE LOWER(CONCAT(:username,'%')) or"
			+ "  u.mail LIKE CONCAT(:username,'%') or LOWER(u.mail) LIKE LOWER(CONCAT(:username,'%')) and userManagerId is not null")
	List<UserMaster> findUsersWithPartOfName(@Param("username") String username);
	
	
	
	
	 @Query("SELECT new com.marlabs.rmbs.user.vo.CommonBean(id,cn) "
	 		+ "FROM UserMaster where departmentId."
		 		+ "id = :financeId")
		List<CommonBean> getByDepartment(@Param("financeId") Integer financeId);

	 
//	 @Query("SELECT new com.marlabs.rmbs.config.smtp.email.ReimbursPerson(id,uid,cn,mail,givenName,"
//	 		+ "telephoneNumber,department,loc,manager,userPrincipalName,employeeId) FROM UserMaster")
//	public List<ReimbursPerson> getUsersFullList();
	 
	 
	 @Query("FROM UserMaster where userManagerId.id=:userId")
		List<UserMaster> isEmployeeManager( @Param("userId") Integer userId);
	 
	 public UserMaster findByMailIgnoreCase(String mail);

	 @Query("FROM UserMaster where employeeId=:employeeId")
	 public UserMaster findByEmployeeId(@Param("employeeId") String employeeId);


     @Query("FROM UserMaster where id=:userId") 
	 public UserMaster getDesignationLevel(@Param("userId") Integer userId);
     
     
     @Query("SELECT employeeId FROM UserMaster where id=:userId") 
	 public String getEmployeeId(@Param("userId") Integer userId);

	 public UserMaster findById(UserMaster requestingUserId);
	 
	 @Query("SELECT id FROM UserMaster where employeeId =:employeeManagerId") 
     public Integer getEmployeeManagerId(@Param("employeeManagerId") String employeeManagerId);

	@Query("FROM UserMaster where employeeId=:deptManagerId")
	public UserMaster findByEmployeeManagerId(@Param("deptManagerId") String deptManagerId);



	@Query("Select isManager FROM UserMaster where id=:employeeId")
	public Boolean isManager(@Param("employeeId") Integer employeeId);





}

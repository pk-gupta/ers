package com.marlabs.rmbs.user.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marlabs.rmbs.entities.ClaimApproveFwdEmployees;
import com.marlabs.rmbs.entities.ClaimSubmitEmployees;
import com.marlabs.rmbs.entities.DepartmentMaster;
import com.marlabs.rmbs.entities.RoleMaster;
import com.marlabs.rmbs.entities.UserMaster;
import com.marlabs.rmbs.masters.constant.RoleConstant;
import com.marlabs.rmbs.masters.repository.DepartmentRepository;
import com.marlabs.rmbs.masters.repository.RoleRepository;
import com.marlabs.rmbs.masters.service.UserMasterRepository;
import com.marlabs.rmbs.request.repository.ClaimApproveFwdEmployeesRepository;
import com.marlabs.rmbs.request.repository.ClaimSubmitEmployeesRepository;
import com.marlabs.rmbs.user.vo.AdminRequest;
import com.marlabs.rmbs.user.vo.CommonBean;
import com.marlabs.rmbs.user.vo.UserDetails;

@Service 
public class UserServiceImpl implements UserService{

	@Autowired
	private UserMasterRepository userMasterRepository;
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private ClaimSubmitEmployeesRepository claimSubmitEmployeesRepository;
	
	@Autowired
	private ClaimApproveFwdEmployeesRepository claimApproveFwdEmployeesRepository;

	@Override
	public List<UserDetails> userSearch(String userDetails) {
		List<UserMaster> userDetailList = userMasterRepository.findUsersWithPartOfName(userDetails);
		List<UserDetails> userShowList = new ArrayList<>();

		for (UserMaster userMaster : userDetailList) {
			UserDetails userDetailsObj = new UserDetails();
			userDetailsObj.setUserName(userMaster.getCn());
			userDetailsObj.setUserDepartment(userMaster.getDepartment());
			userDetailsObj.setUserMail(userMaster.getMail());
			userDetailsObj.setUserId(userMaster.getId());
			userShowList.add(userDetailsObj);
		}

		return userShowList;
	}
	
	
/*
	@Override
	
	public AdminRequest updateUser(AdminRequest adminRequest) {
	
			
		System.out.println(adminRequest.getUserId()+"------------------");
			UserMaster userMaster = userMasterRepository.getOne(adminRequest.getUserId());
			Department department = departmentRepository.getOne(adminRequest.getDepartment());

			if (adminRequest.getSubmitToEmployees() != null) {
			List<ClaimSubmitEmployees> submitEmpList=claimSubmitEmployeesRepository.listSubmitByUser(adminRequest.getUserId());
			for (ClaimSubmitEmployees claimSubmitEmployees : submitEmpList) {
				claimSubmitEmployeesRepository.delete(claimSubmitEmployees.getId());
			}
				
			
				if (RoleConstant.MANAGER.equalsRoles(adminRequest.getSubmissionTo())) {
					ClaimSubmitEmployees claimSubmitEmployees = new ClaimSubmitEmployees();
					claimSubmitEmployees.setSubmitFromEmployeeId(userMaster);
					claimSubmitEmployees.setSubmitToEmployeeId(userMaster.getUserManagerId());
					claimSubmitEmployeesRepository.save(claimSubmitEmployees);

				} else {

					for (CommonBean submitCommon : adminRequest.getSubmitToEmployees()) {
						ClaimSubmitEmployees claimSubmitEmployees = new ClaimSubmitEmployees();
						UserMaster submitFinanceId = userMasterRepository.getOne(submitCommon.getId());
						claimSubmitEmployees.setSubmitFromEmployeeId(userMaster);
						claimSubmitEmployees.setSubmitToEmployeeId(submitFinanceId);
						claimSubmitEmployeesRepository.save(claimSubmitEmployees);

					}
				}
				
			}

			if (adminRequest.getApproveToEmployees() != null) {
				List<ClaimApproveFwdEmployees> approveFwdEmpList = claimApproveFwdEmployeesRepository
						.listApproveByUser(adminRequest.getUserId());
				for (ClaimApproveFwdEmployees claimSubmitEmployees : approveFwdEmpList) {
					claimApproveFwdEmployeesRepository.delete(claimSubmitEmployees.getId());
				}
				
				if (RoleConstant.MANAGER.equalsRoles(adminRequest.getApproveFwdTo())) {
					ClaimApproveFwdEmployees claimApproveFwdEmployees = new ClaimApproveFwdEmployees();
					claimApproveFwdEmployees.setApproveFwdFromEmployeeId(userMaster);
					claimApproveFwdEmployees.setApproveFwdToEmployeeId(userMaster.getUserManagerId());
					claimApproveFwdEmployeesRepository.save(claimApproveFwdEmployees);
				} else {

					for (CommonBean approveFwdCommon : adminRequest.getApproveToEmployees()) {
						ClaimApproveFwdEmployees claimApproveFwdEmployees = new ClaimApproveFwdEmployees();
						UserMaster approveFwdfinanceId = userMasterRepository.getOne(approveFwdCommon.getId());
						claimApproveFwdEmployees.setApproveFwdFromEmployeeId(userMaster);
						claimApproveFwdEmployees.setApproveFwdToEmployeeId(approveFwdfinanceId);
						claimApproveFwdEmployeesRepository.save(claimApproveFwdEmployees);
					}
				}
			}

			RoleMaster roleMasterId = roleRepository.getOne(adminRequest.getRoleId());

			userMaster.setDepartmentId(department);
			userMaster.setApproveFwdTo(adminRequest.getApproveFwdTo());
			userMaster.setDepartment(department.getDepartmentCode());
			userMaster.setApprovalLimit(adminRequest.getApproveLimit());
			userMaster.setRoleId(roleMasterId);
			userMaster.setSubmissionTo(adminRequest.getSubmissionTo());
			userMasterRepository.saveAndFlush(userMaster);

			return adminRequest;
			
		
	}
	
	
	
	
	
	@Override
	public AdminRequest getAdminForm() {
		AdminRequest adminRequest=new AdminRequest();
		adminRequest.setDepartmentList(departmentRepository.findAll());
		adminRequest.setRoleList(roleRepository.findAll());
		List<Department> department= new ArrayList<Department>();
		int finance_code=0;
		 department=departmentRepository.findAll();
		 for(Department dpt:department){
			 if(dpt.getDepartmentCode().equals("India Finance ")){
				 finance_code=dpt.getId();
			 }
		 }
		adminRequest.setFinancePersonList((userMasterRepository.getByDepartment(finance_code)));
		return adminRequest;
	}
	
	
	
	@Override
	public AdminRequest getEmployeeConfigDetails(Integer userId) {
		
		UserMaster userMaster = userMasterRepository.getOne(userId);
		AdminRequest adminRequest=new AdminRequest();
		adminRequest.setDepartmentList(departmentRepository.findAll());
		adminRequest.setRoleList(roleRepository.findAll());
		List<Department> department= new ArrayList<Department>();
		int finance_code=0;
		 department=departmentRepository.findAll();
		 for(Department dpt:department){
			 if(dpt.getDepartmentCode().equals("India Finance ")){
				 finance_code=dpt.getId();
			 }
		 }
		adminRequest.setFinancePersonList((userMasterRepository.getByDepartment(finance_code)));
		adminRequest.setRealEmp(userMaster.getEmployeeId());
		adminRequest.setRoleId(userMaster.getRoleId() != null ? userMaster.getRoleId().getId() : 0);
		adminRequest.setSubmissionTo(userMaster.getSubmissionTo());
		adminRequest.setDepartment(userMaster.getDepartmentId() != null ? userMaster.getDepartmentId().getId() : 0);
		adminRequest.setSubmitToEmployees(claimSubmitEmployeesRepository.listSubmitByUserCommon(userId));
		adminRequest.setApproveLimit(userMaster.getApprovalLimit());
		adminRequest.setApproveFwdTo(userMaster.getApproveFwdTo());
		adminRequest.setApproveToEmployees(claimApproveFwdEmployeesRepository.listApproveByUserCommon(userId));
		return adminRequest;
	}

*/

}

package com.marlabs.rmbs.request.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import com.marlabs.rmbs.approve.service.DepartmentManagerMissingException;
import com.marlabs.rmbs.config.EmailService;
import com.marlabs.rmbs.entities.ClaimTypeMaster;
import com.marlabs.rmbs.entities.CurrencyMaster;
import com.marlabs.rmbs.entities.ProjectMaster;
import com.marlabs.rmbs.entities.UserMaster;
import com.marlabs.rmbs.exception.FileNotSupportedException;
import com.marlabs.rmbs.exception.ProjectMissingException;
import com.marlabs.rmbs.masters.repository.ClaimTypeRepository;
import com.marlabs.rmbs.masters.repository.CurrencyMasterRepository;
import com.marlabs.rmbs.masters.repository.DepartmentRepository;
import com.marlabs.rmbs.masters.repository.ProjectMasterRepository;
import com.marlabs.rmbs.masters.service.UserMasterRepository;
import com.marlabs.rmbs.request.vo.ClaimRequestHeaderVo;
import com.marlabs.rmbs.request.vo.ClaimRequestVo;

@Service
public class ClaimRequestUpdateService {

	@Autowired(required = false)
	private ClaimServiceFactory claimServiceFactory;
	@Autowired
	private EmailService emailService;
	@Autowired
	private UserMasterRepository userMasterRepository;
	@Autowired
	private DepartmentRepository departmentRepository;
	@Autowired
	private ClaimTypeRepository claimTypeRepository;
	@Autowired
	private CurrencyMasterRepository currencyMasterRepository;
	@Autowired
	private ProjectMasterRepository projectMasterRepository;
	@Autowired
	private Environment env;

	public ClaimRequestHeaderVo createRequest(ClaimRequestVo claimRequestVo, Integer claimId)
			throws DepartmentManagerMissingException, ManagerIdInActiveException, FileNotSupportedException, Exception {

		UserMaster userObj = userMasterRepository.findOne(claimRequestVo.getClaimRequestHeaderVo().getEmployeeId());
		ClaimTypeMaster claimTypeobj = claimTypeRepository
				.getOne(claimRequestVo.getClaimRequestHeaderVo().getClaimType());
		CurrencyMaster currencyobj = currencyMasterRepository
				.findOne(claimRequestVo.getClaimRequestHeaderVo().getClaimCurrency());

		if (claimRequestVo.getClaimRequestHeaderVo().getEmployeeProjectId() == null) {
			throw new ProjectMissingException("Please enter valid project...");
		}

		ProjectMaster projectMaster = projectMasterRepository
				.findProjectManager(claimRequestVo.getClaimRequestHeaderVo().getEmployeeProjectId());

		Integer department = (userMasterRepository.getOne(claimRequestVo.getClaimRequestHeaderVo().getEmployeeId()))
				.getEmployeeDepartmentId();
		String deptManagerId = departmentRepository.findByDepatmentId(department).getDepartmentManagerId();
		if (null == deptManagerId || deptManagerId.contains("[null]") || deptManagerId.contains("NULL")
				|| deptManagerId.isEmpty()
				|| userMasterRepository.findByEmployeeId(deptManagerId).getIsActive() == false)
			throw new DepartmentManagerMissingException("Please check : Department Manager id is missing");

		UserMaster approveObj = null;

		Boolean managerCheck = userMasterRepository.isManager(claimRequestVo.getClaimRequestHeaderVo().getEmployeeId());
		List<ProjectMaster> projectManagerList = projectMasterRepository
				.isEmployeeProjectManager(claimRequestVo.getClaimRequestHeaderVo().getEmployeeId());
		Boolean pmCheck = (projectManagerList.isEmpty() ? false : true);

		if (projectMaster.getProjectId() == 1 || managerCheck || pmCheck) {
			approveObj = userMasterRepository.findByEmployeeId(userObj.getEmployeeManagerId());
		} else {
			approveObj = userMasterRepository.findByEmployeeId(projectMaster.getProjectManagerId());
		}
		if (!approveObj.getIsActive()) {
			throw new ManagerIdInActiveException("Please Update ManagerId :");
		}
		ClaimService claimService = claimServiceFactory.getService(claimTypeobj.getClaimCode());
		ClaimRequestHeaderVo claimRequestHeaderVo = claimService.update(claimRequestVo, claimId, userObj, claimTypeobj,
				currencyobj, projectMaster, approveObj);
		if ("submit".equalsIgnoreCase(claimRequestVo.getClaimRequestHeaderVo().getSubmitType())) {
			emailService.send(userObj, approveObj, claimTypeobj.getClaimCode(), claimRequestHeaderVo.getClaimNo(),
					claimRequestVo.getClaimRequestHeaderVo().getSpecialApprove());
		}
		return claimRequestHeaderVo;
	}
}

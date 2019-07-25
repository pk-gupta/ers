package com.marlabs.rmbs.config;
/*package com.marlabs.rmbs.config.smtp.email;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.mail.MessagingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.marlabs.rmbs.entities.Department;
import com.marlabs.rmbs.entities.EmployeeProject;
import com.marlabs.rmbs.entities.ProjectMaster;
import com.marlabs.rmbs.entities.RoleMaster;
import com.marlabs.rmbs.entities.UserMaster;
import com.marlabs.rmbs.masters.repository.RoleRepository;
import com.marlabs.rmbs.masters.service.UserMasterRepository;
import com.marlabs.rmbs.request.repository.EmployeeProjectRepo;
import org.marlabs.department.DepartmentRepository;
import org.marlabs.department.DepartmentVo;
import org.marlabs.employee.EmployeeRepository;
import org.marlabs.employee.EmployeeVo;
import org.marlabs.employee.project.EmployeeProjectRepository;
import org.marlabs.employee.project.EmployeeProjectVo;
import org.marlabs.project.ProjectRepository;
import org.marlabs.project.ProjectVo;

@Component
public class ScheduledTasks {
	@Autowired
	private UserMasterRepository userMasterRepository;
	@Autowired
	private EmployeeProjectRepo employeeProjectRepo;
	@Autowired 
	private EmployeeProjectRepository employeeProjectrepository;
	@Autowired
	private ProjectRepository projectrepository;
	@Autowired
    private EmployeeRepository employeerepository;
	
	@Autowired
    private DepartmentRepository departmentrepository;
	@Autowired
	private com.marlabs.rmbs.masters.repository.ProjectMasterRepository projectMasterRepository;
	@Autowired
	private com.marlabs.rmbs.masters.repository.DepartmentRepository departmentRepo;
	@Autowired
    private RoleRepository roleRepository;
	
	@Autowired
	private SmtpMailSender smtpMailSender;
	
	private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);
	private static final String MAIL_ID="pappu.kumar@marlabs.com";
	private static final String MAIL_SUBJECT = "HRIS Data Schedular Notification !!!";
	private final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	
	@Scheduled(cron = "${scheduling.job.cron}")
	public void schedularHrisData() {
		
		try{
			log.info("In schedularHrisData method : The time is now {}", dateFormat.format(new Date()));
			
			saveDepartment();
			//saveUser();
			//saveEmployeeProject();
			
		   	//saveProjects();
		   	log.info("Out schedularHrisData method : The time is now {}", dateFormat.format(new Date()));
		}catch(Exception e){
			log.debug("Exception in schedularHrisData() method ------- ",e);
		}
		
    }
	
	
   	public void saveDepartment() throws MessagingException {
   		try{
			List<DepartmentVo> department=departmentrepository.listDepartments();	
			List<DepartmentDetail> departs=new ArrayList<>();
			
			for(DepartmentVo dpt:department){
				DepartmentDetail dp=new DepartmentDetail();
				dp.setDepartmentCode(dpt.getDepartmentName());
				dp.setDepartmentDesc(dpt.getDepartmentName());
				dp.setDepartmentId(dpt.getDepartmentId());
				dp.setDepartmentManagerId(dpt.getDepartmentManagerId());
				dp.setDepartmentManager(dpt.getDepartmentManager());
				departs.add(dp);
			}
			
			List<Department> departmntDetails=departmentRepo.findAll();
			if(null!=departmntDetails && (!departmntDetails.isEmpty())){
				for(DepartmentDetail dpt:departs){
					for( Department dptDetails:departmntDetails){
						if(dpt.getDepartmentCode().equals(dptDetails.getDepartmentCode())){
							dpt.setId(dptDetails.getId());
							}
						}
					Department depo=new Department();			
					depo.setId(dpt.getId());
					depo.setDepatmentId(dpt.getDepartmentId());
					depo.setDepartmentCode(dpt.getDepartmentCode());
					depo.setDepartmentDesc(dpt.getDepartmentDesc());
					depo.setDepartmentManagerId(dpt.getDepartmentManagerId());
					depo.setDepartmentManager(dpt.getDepartmentManager());
					departmentRepo.save(depo);
					}
				}
			log.info("Updated successfully Department Table on time {}", dateFormat.format(new Date()));
			smtpMailSender.send(MAIL_ID,MAIL_ID,MAIL_SUBJECT,"Updated successfully Department Table");	
   		}catch(Exception e){
   			log.debug("Exception while saving department details record ---- ",e);
   			String body = "Exception in saveEmployeeProject() method" + e;
   			smtpMailSender.send(MAIL_ID,MAIL_ID,MAIL_SUBJECT,body);
   		}
		
	}


	public void saveEmployeeProject() throws MessagingException{
	try{
		
	   	List<EmployeeProjectVo> empProjects=employeeProjectrepository.listEmployeeProjects();
		List<EmployeeProject> empProjectsPostgres = employeeProjectRepo.findAll();
		
		EmployeeProjectVo empprojdt = new EmployeeProjectVo();
		empprojdt.setId(1);
		empprojdt.setProjectId(1);
		empprojdt.setEmployeeProjectId("NA");
		empprojdt.setEmployeeProject("Not Applicable");
		empprojdt.setEmployeeId(null);
		empprojdt.setEmployeeName(null);
		empprojdt.setLocation(null);
		empprojdt.setManager(null);
		empprojdt.setManagerId(null);
		empProjects.add(0,empprojdt);
		
		 if(null!=empProjectsPostgres && !empProjectsPostgres.isEmpty() ){
	     	for(EmployeeProjectVo empView:empProjects){
	     		for(EmployeeProject empPost:empProjectsPostgres){
	     			if(empView.getEmployeeProjectId().equals(empPost.getEmployeeProjectId())){
	     				empView.setId(empPost.getId());
	     				
	     			}
	     		}
	     	}
	     }
		 List<ProjectVo> projects=projectrepository.listProjects();
			for(EmployeeProjectVo emp:empProjects){
			     for(ProjectVo prj:projects){

					if(emp.getEmployeeProjectId().equalsIgnoreCase(prj.getProjectCode())){
						emp.setProjectId(prj.getProjectId());
						emp.setManagerId(prj.getProjectManagerId());
						emp.setManager(prj.getProjectManager());
						
					}
				}
			}
		 
		 
		for(EmployeeProjectVo emp:empProjects){
			EmployeeProject pro=new EmployeeProject();
			pro.setEmployeeId(emp.getEmployeeId());
	        pro.setEmployeeName(emp.getEmployeeName());
	        pro.setEmployeeProject(emp.getEmployeeProject());
	        pro.setEmployeeProjectId(emp.getEmployeeProjectId());
	        pro.setId(emp.getId());
	        pro.setProjectId(emp.getProjectId());
	        pro.setManager(emp.getManager());
	        pro.setManagerId(emp.getManagerId());
	        employeeProjectRepo.save(pro);
	      
		}

		 log.info("Updated successfully EmployeeProject Table on time {}", dateFormat.format(new Date()));		
		 smtpMailSender.send(MAIL_ID,MAIL_ID,MAIL_SUBJECT,"Updated successfully EmployeeProject Table");	
		
	}
	catch(Exception e){
		log.debug("Exception in saveEmployeeProject() method", e);
		String body = "Exception in saveEmployeeProject() method" + e;
		smtpMailSender.send(MAIL_ID,MAIL_ID,MAIL_SUBJECT,body);
	}
    
   	}
	
	
	
	
	private void saveUser() throws MessagingException {
		try{
		List<EmployeeVo> empVoHrisData = employeerepository.listEmployees();   
		List<ReimbursPerson> newObjList = new ArrayList<>();
		List<Department> departmntDetails=departmentRepo.findAll();
		
		for (EmployeeVo person : empVoHrisData) {
			ReimbursPerson dbObj = new ReimbursPerson();
			dbObj.setUid(person.getEmployeeName());
			dbObj.setCn(person.getEmployeeName());
			dbObj.setManager(person.getEmployeeManager());
			dbObj.setTelephoneNumber(person.getPhoneNumber());
			dbObj.setLoc(person.getLocation());
			dbObj.setMail(person.getEmail());
			dbObj.setDepartment(person.getEmployeeDepartment());
			dbObj.setEmployeeDepartmentId(person.getEmployeeDepartmentId());
			dbObj.setUserPrincipalName(person.getEmail());
			dbObj.setEmployeeId(person.getEmployeeId());
			dbObj.setEmployeeManagerId(person.getEmployeeManagerId());
			dbObj.setIsBillable(person.getIsBillable());
			dbObj.setIsManager(person.getIsManager());
			dbObj.setIsActive(person.getIsActive());
			dbObj.setDesignationId(person.getDesignationId());
			dbObj.setDesignation(person.getDesignation());
			dbObj.setDesignationLevel(person.getDesignationLevel());
			
		    for(Department dpt:departmntDetails){
		    	
		    	if(person.getEmployeeDepartmentId().equals(dpt.getDepatmentId())){
		    		Department dp =new Department();
		    		dp.setId(dpt.getId());
		    		dp.setDepatmentId(dpt.getDepatmentId());
		    		dp.setDepartmentCode(dpt.getDepartmentCode());
		    		dp.setDepartmentDesc(dpt.getDepartmentDesc());
		    		dp.setDepartmentManagerId(dpt.getDepartmentManagerId());
		    		dp.setDepartmentManager(dpt.getDepartmentManager());
		    		dbObj.setDepartmentId(dp);
		    	}
		    }
			newObjList.add(dbObj);
		}
		
			List<ReimbursPerson> reimbursPersonLocalData = userMasterRepository.getUsersFullList();
			RoleMaster roleNormal= roleRepository.findOne(3);
		   	RoleMaster roleApprover= roleRepository.findOne(2);
		for(ReimbursPerson emp:newObjList){
			emp.setRoleId(roleNormal);
		}
		
		
	
		List<String> managerList = new ArrayList<>();
		for(ReimbursPerson emp:newObjList){	
			managerList.add(emp.getEmployeeManagerId());
		}
		
		List<ProjectVo> projects=projectrepository.listProjects();
		for(ProjectVo pr:projects){
			managerList.add(pr.getProjectManagerId());
		}
		
		for(ReimbursPerson emp:newObjList){
			for(String mId:managerList){
				if(emp.getEmployeeId().equals(mId)){
					emp.setIsManager(true);
					emp.setRoleId(roleApprover);
				}
			}
		}
		
		
		if(null!=reimbursPersonLocalData && !reimbursPersonLocalData.isEmpty()){
			
				for(ReimbursPerson emp:newObjList){
					for(ReimbursPerson rmdb:reimbursPersonLocalData){
						if(emp.getEmployeeId().equals(rmdb.getEmployeeId())){
							emp.setId(rmdb.getId());
						    UserMaster usermaster=  userMasterRepository.findOne(rmdb.getId());
						    if(usermaster.getRoleId().getId()==4){
						    emp.setRoleId(usermaster.getRoleId());
				        break;
				           }
						}
					}
						
				}
				
				for(ReimbursPerson emp:newObjList){
				      for(ReimbursPerson rmdb:reimbursPersonLocalData){
					    if(emp.getEmployeeManagerId().equals(rmdb.getEmployeeId())){					     
			             emp.setUserManagerId(rmdb.getId());
						}
					}
					
				}
		
		}		

		for (ReimbursPerson reimbursPerson : newObjList) {
			UserMaster userMaster = new UserMaster();
			userMaster.setCn(reimbursPerson.getCn());
		    userMaster.setRoleId(reimbursPerson.getRoleId());
			userMaster.setIsManager(reimbursPerson.getIsManager());
			userMaster.setIsBillable(reimbursPerson.getIsBillable());
			userMaster.setIsActive(reimbursPerson.getIsActive());
			userMaster.setDepartment(reimbursPerson.getDepartment());
			userMaster.setGivenName(reimbursPerson.getGivenName());
			userMaster.setMail(reimbursPerson.getMail()); 
			userMaster.setSubmissionTo("manager");
			userMaster.setEmployeeType("hris");
			userMaster.setManager(reimbursPerson.getManager());
			userMaster.setEmployeeManagerId(reimbursPerson.getEmployeeManagerId());
			userMaster.setLoc(reimbursPerson.getLoc());
			userMaster.setUid(reimbursPerson.getUid());
			userMaster.setEmployeeId(reimbursPerson.getEmployeeId());
			userMaster.setEmployeeDepartmentId(reimbursPerson.getEmployeeDepartmentId());
			userMaster.setDesignation(reimbursPerson.getDesignation());
			userMaster.setDesignationId(reimbursPerson.getDesignationId());
			userMaster.setDesignationLevel(reimbursPerson.getDesignationLevel());

			if(null!=reimbursPerson.getId()){
			userMaster.setId(reimbursPerson.getId());
			}
			UserMaster user=new UserMaster();
			user.setId(reimbursPerson.getUserManagerId());
			user.setCn(reimbursPerson.getManager());
			userMaster.setUserManagerId(user);
			
			userMasterRepository.save(userMaster);
			
		}
		log.info("Updated successfully UserMaster Table on time {}", dateFormat.format(new Date()));
		smtpMailSender.send(MAIL_ID,MAIL_ID,MAIL_SUBJECT,"Updated successfully UserMaster Table");	
		}
catch(Exception e){
	log.debug("Exception in saveUser() method ---  reimbursPersonLocalData is either empty or null :",e);
	String body = "Exception in saveUser() method" + e;
	smtpMailSender.send(MAIL_ID,MAIL_ID,MAIL_SUBJECT,body);
}	
}
	
	private void saveProjects() throws MessagingException {
		try{
			List<ProjectVo> projects=projectrepository.listProjects();
			List<ProjectDetails> details=new ArrayList<>();
			ProjectVo projdt=new ProjectVo();
			projdt.setProjectCode("NA");
			projdt.setProjectName("Not Applicable");
			projdt.setProjectId(1);
			projdt.setProjectManager("");
			projects.add(0, projdt);
			
			
			for(ProjectVo project:projects){
				ProjectDetails pdt=new ProjectDetails();				
				pdt.setProjectCode(project.getProjectCode());
				pdt.setProjectDesc(project.getProjectName());
				pdt.setProjectManager(project.getProjectManager());
				pdt.setProjectId(project.getProjectId());
			UserMaster userDetail = userMasterRepository.findByEmployeeId(project.getProjectManagerId());
			if(null!=userDetail){
			pdt.setProjectManagerId(userDetail.getId());
			details.add(pdt);
			}
			else if(null==userDetail && pdt.getProjectId()==1 ){
				details.add(pdt);
			}
			}
			
			List<ProjectDetails> projectDetailsFromLocal=projectMasterRepository.getProjectsFullList();
			
			   
				   if(null!=projectDetailsFromLocal && !projectDetailsFromLocal.isEmpty()){
						for(ProjectDetails projectsList:details){
							for(ProjectDetails projectDetails:projectDetailsFromLocal){
								if(projectsList.getProjectId()==projectDetails.getProjectId()){
									projectsList.setId(projectDetails.getId());
								}
							}
						}
						}
			   

			for(ProjectDetails prjcts:details){
				ProjectMaster prjMaster=new ProjectMaster();
				
				prjMaster.setProjectCode(prjcts.getProjectCode());
				prjMaster.setProjectDesc(prjcts.getProjectDesc());
				prjMaster.setProjectManager(prjcts.getProjectManager());
				prjMaster.setProjectId(prjcts.getProjectId());
				prjMaster.setActive(true);
				if(prjcts.getProjectId()!=1){
				UserMaster user=new UserMaster();
				user.setId(prjcts.getProjectManagerId());
				prjMaster.setProjectManagerId(user);
				}
				if(0!=prjcts.getId()){
			     prjMaster.setId(prjcts.getId());
				}
				projectMasterRepository.save(prjMaster);
				
				
			}
			log.info("Updated successfully ProjectMaster Table on time {}", dateFormat.format(new Date()));
			smtpMailSender.send(MAIL_ID,MAIL_ID,MAIL_SUBJECT,"Updated successfully ProjectMaster Table");
		}catch(Exception e){
			log.debug("Exception in saveProjects() method :",e);
			String body = "Exception in saveProjects() method" + e;
			smtpMailSender.send(MAIL_ID,MAIL_ID,MAIL_SUBJECT,body);
			
		}
}
	
}
*/
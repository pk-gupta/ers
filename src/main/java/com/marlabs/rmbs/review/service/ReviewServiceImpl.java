package com.marlabs.rmbs.review.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marlabs.rmbs.approve.service.ClaimApproveServiceImpl;
import com.marlabs.rmbs.entities.ClaimSubmitHeader;
import com.marlabs.rmbs.entities.FrequentAskedQuestions;
import com.marlabs.rmbs.entities.ProjectMaster;
import com.marlabs.rmbs.masters.constant.StatusConstant;
import com.marlabs.rmbs.masters.repository.ProjectMasterRepository;
import com.marlabs.rmbs.request.repository.ClaimRequestHeaderRepository;
import com.marlabs.rmbs.request.repository.FrequentlyAskedRepository;
import com.marlabs.rmbs.request.vo.ClaimRequestHeaderVo;


@Service 
public class ReviewServiceImpl implements ReviewService{
	
	@Autowired
	private ClaimRequestHeaderRepository claimRequestHeaderRepository;
	
	@Autowired
	private FrequentlyAskedRepository frequentlyAskedRepository;
	
	@Autowired
	private ProjectMasterRepository projectMaster;
	@Autowired
	private ClaimApproveServiceImpl claimApproveServiceImpl;

	@Override
	public List<ClaimRequestHeaderVo> getClaimsByProject(Integer projectId,Integer status,Date fromDate,Date toDate) {
		List<ClaimSubmitHeader> claimProjectList = new ArrayList<ClaimSubmitHeader>();
		if(toDate == null){
			toDate = new Date();
		}
		
		if(projectId==1){
			
			ProjectMaster project = projectMaster.findProject(1);
			projectId=project.getId();
			
		}
		//all projects
		if(status.equals(0)){
			if(fromDate != null){
				//all projects in a specific date range
				if (projectId.equals(0) ) {
					claimProjectList = claimRequestHeaderRepository.findClaimsByDateRange(fromDate,toDate,
							StatusConstant.DRAFT.value,StatusConstant.REVIEW.value,StatusConstant.REJECT.value);
				}else{
					//projects in a specific date range
					claimProjectList = claimRequestHeaderRepository.findClaimsByProject(projectId,fromDate,toDate
							,StatusConstant.DRAFT.value,StatusConstant.REVIEW.value,StatusConstant.REJECT.value);
				}
			}
			else{
				//all projects from beginning
				if (projectId.equals(0) ) {
					claimProjectList = claimRequestHeaderRepository.findAllClaimsFromBeginning(toDate,
							StatusConstant.DRAFT.value,StatusConstant.REVIEW.value,StatusConstant.REJECT.value);
				}else{
					//projects from beginning
					claimProjectList = claimRequestHeaderRepository.findClaimsByProjectFromBeginning(projectId,toDate
							,StatusConstant.DRAFT.value,StatusConstant.REVIEW.value,StatusConstant.REJECT.value);
				}
			}
		}else{
			if(fromDate != null){
				
				//all projects in a specific date range
				if (projectId.equals(0) ) {
					System.out.println("status="+status);
					claimProjectList = claimRequestHeaderRepository.findClaimsByDateRangeWithStatus(status,fromDate,toDate,
							StatusConstant.DRAFT.value,StatusConstant.REVIEW.value,StatusConstant.REJECT.value);
				}else{
					
					//projects in a specific date range
					claimProjectList = claimRequestHeaderRepository.findClaimsByProjectWithStatus(projectId,status,fromDate,toDate
							,StatusConstant.DRAFT.value,StatusConstant.REVIEW.value,StatusConstant.REJECT.value);
				}
			}
			else{
				
				//all projects from beginning
				if (projectId.equals(0) ) {
					
					claimProjectList = claimRequestHeaderRepository.findAllClaimsFromBeginningWithStatus(status,toDate,
							StatusConstant.DRAFT.value,StatusConstant.REVIEW.value,StatusConstant.REJECT.value);
				}else{
					
					//projects from beginning
					claimProjectList = claimRequestHeaderRepository.findClaimsByProjectFromBeginningWithStatus(status,projectId,toDate
							,StatusConstant.DRAFT.value,StatusConstant.REVIEW.value,StatusConstant.REJECT.value);
				}
			}
		}
		

		List<ClaimRequestHeaderVo> claimReviewList = new ArrayList<ClaimRequestHeaderVo>();

		for (ClaimSubmitHeader claimApproveDetails : claimProjectList) {
			ClaimRequestHeaderVo claimRequestHeaderVo = new ClaimRequestHeaderVo();
			claimRequestHeaderVo.setId(claimApproveDetails.getId());
			claimRequestHeaderVo
					.setClaimDate(new SimpleDateFormat("dd-MM-yyyy").format(claimApproveDetails.getClaimDate()));
			claimRequestHeaderVo.setClaimCurrency(claimApproveDetails.getCurrencyId().getId());
			claimRequestHeaderVo.setCurrencyCode(claimApproveDetails.getCurrencyId().getCurrencyCode());
			claimRequestHeaderVo.setClaimNo(claimApproveDetails.getClaimNo());
			claimRequestHeaderVo.setClaimedAmount(claimApproveDetails.getClaimAmount());
			claimRequestHeaderVo.setClaimTypeName(claimApproveDetails.getClaimTypeId().getClaimCode());
			claimRequestHeaderVo.setRemarks(claimApproveDetails.getRemarks());
			claimRequestHeaderVo.setSpecialApprove(claimApproveDetails.getSpecialApprove());
			claimRequestHeaderVo.setProject(claimApproveDetails.getProject());
			claimRequestHeaderVo.setEmpId(claimApproveDetails.getRequestingUserId().getEmployeeId());
			claimRequestHeaderVo.setEmployeeName(claimApproveDetails.getRequestingUserId().getCn());
			claimRequestHeaderVo.setStatus(claimApproveDetails.getFinalStatus().getClaimStatusCode());
			claimRequestHeaderVo.setExchangeRate(claimApproveDetails.getExchangeRate());
			claimReviewList.add(claimRequestHeaderVo);
		}
		return claimReviewList;
	}


	@Override
	public List<FrequentAskedQuestions> getFreqAskedQuestion() {
		List <FrequentAskedQuestions> faqList=new ArrayList<FrequentAskedQuestions>();
		faqList=frequentlyAskedRepository.findAll();
		return faqList;
	}
	
	
	@Override
	public void deleteFreqAskedQuestion(int id) {
		frequentlyAskedRepository.delete(id);
	
	}


	public List<FrequentAskedQuestions> saveData(FaqDTO faqDTO) {
		System.out.println(faqDTO.getFaqDetailsList());
		List <FrequentAskedQuestions> faqList=new ArrayList<FrequentAskedQuestions>();
		for(FaqListVo faqDetailsVo : faqDTO.getFaqDetailsList()){
			FrequentAskedQuestions faq = new FrequentAskedQuestions();
			faq.setQuestions(faqDetailsVo.getQuestions());
			faq.setAnswer(faqDetailsVo.getAnswer());
			frequentlyAskedRepository.save(faq);
			faqList.add(faq);
		}
		
		return faqList;
		
	}
   ///////25-09-2017-Monday/////search Claims with user id ////////

	public List<ClaimRequestHeaderVo> getClaimsByUserId(Integer userId) {
		List<ClaimSubmitHeader> userClaimsList = new ArrayList<>();
		userClaimsList = claimRequestHeaderRepository.findClaimsByUserId(userId,StatusConstant.DRAFT.value,StatusConstant.REVIEW.value,StatusConstant.REJECT.value);
		List<ClaimRequestHeaderVo> claimReviewList = new ArrayList<>();

		for (ClaimSubmitHeader claimApproveDetails : userClaimsList) {
			ClaimRequestHeaderVo claimRequestHeaderVo = new ClaimRequestHeaderVo();
			claimRequestHeaderVo.setId(claimApproveDetails.getId());
			claimRequestHeaderVo
					.setClaimDate(new SimpleDateFormat("dd-MM-yyyy").format(claimApproveDetails.getClaimDate()));
			claimRequestHeaderVo.setClaimCurrency(claimApproveDetails.getCurrencyId().getId());
			claimRequestHeaderVo.setCurrencyCode(claimApproveDetails.getCurrencyId().getCurrencyCode());
			claimRequestHeaderVo.setClaimNo(claimApproveDetails.getClaimNo());
			claimRequestHeaderVo.setClaimedAmount(claimApproveDetails.getClaimAmount());
			claimRequestHeaderVo.setClaimTypeName(claimApproveDetails.getClaimTypeId().getClaimCode());
			claimRequestHeaderVo.setRemarks(claimApproveDetails.getRemarks());
			claimRequestHeaderVo.setSpecialApprove(claimApproveDetails.getSpecialApprove());
			claimRequestHeaderVo.setProject(claimApproveDetails.getProject());
			claimRequestHeaderVo.setEmpId(claimApproveDetails.getRequestingUserId().getEmployeeId());
			claimRequestHeaderVo.setEmployeeName(claimApproveDetails.getRequestingUserId().getCn());
			claimRequestHeaderVo.setStatus(claimApproveDetails.getFinalStatus().getClaimStatusCode());
			claimRequestHeaderVo.setExchangeRate(claimApproveDetails.getExchangeRate());
			claimReviewList.add(claimRequestHeaderVo);
		}
		return claimReviewList;
	}

    ///////25-09-2017-Monday/////////////



}

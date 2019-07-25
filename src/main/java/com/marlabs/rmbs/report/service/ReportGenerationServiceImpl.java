package com.marlabs.rmbs.report.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.marlabs.rmbs.entities.ClaimSubmitHeader;
import com.marlabs.rmbs.report.vo.ClaimSubmitDetailsProjection;
import com.marlabs.rmbs.report.vo.PerDiemReportVo;
import com.marlabs.rmbs.request.repository.ClaimApproveDetailsRepository;
import com.marlabs.rmbs.request.repository.ClaimRequestDetailRepository;
import com.marlabs.rmbs.request.repository.ClaimRequestHeaderRepository;
@Service
public class ReportGenerationServiceImpl implements ReportGenerationService {
    @Autowired
    private ClaimRequestHeaderRepository claimsubmitHeaderRepo;
    @Autowired
    private ClaimRequestDetailRepository claimBillDetailsRepo;
    @Autowired
    private ClaimApproveDetailsRepository claimApproveDetailsRepo;
    private static final String PERDIEM = "Per Diem"; 
    private static final String PERDIEMOWN = "Per diem own arrangement"; 
    // 17-10-2017 Creating report for per diem
	@Override
	public List<PerDiemReportVo> getPerDiemReport(Integer claimType, String claimCategory, Date fromPerdiemDate, Date toPerdiemDate) {
		List<PerDiemReportVo> perDiemReportList = new ArrayList<>();
        List<Integer> claimIdList = claimBillDetailsRepo.getClaimIdList(claimType,claimCategory,fromPerdiemDate,toPerdiemDate);
        if (claimIdList != null && !claimIdList.isEmpty()) {
        	
       
        for(Integer claimId : claimIdList){
        	
        	// Header part
        	PerDiemReportVo perDeimReportVo= new PerDiemReportVo();
        	ClaimSubmitHeader claimSubmitHeader = claimsubmitHeaderRepo.getOne(claimId);
        	perDeimReportVo.setClaimNo(claimSubmitHeader.getClaimNo());
        	perDeimReportVo.setEmployeeId(claimSubmitHeader.getRequestingUserId().getEmployeeId());
        	perDeimReportVo.setEmployeeName(claimSubmitHeader.getRequestingUserId().getCn());
        	perDeimReportVo.setClaimDate(claimSubmitHeader.getClaimDate());
        	perDeimReportVo.setSummary(claimSubmitHeader.getRemarks());
        	perDeimReportVo.setClaimCategory(claimCategory);
        	// Header part
        	
        	// Bill Details part
        	List<ClaimSubmitDetailsProjection> claimBillDetailsList = claimBillDetailsRepo.getClaimDetailsData(claimId);
			Double totalNonTaxableAmount=0.0;
			Double perDiemAmount =0.0;
			Map<Integer, Double> otherAmount = new HashMap<>(); 
			if(claimCategory.equals(PERDIEM)){
				for(ClaimSubmitDetailsProjection csd : claimBillDetailsList){
					
					if(csd.getClaimCategory().equals("Meals") || csd.getClaimCategory().equals("Local Travel") || csd.getClaimCategory().equals("Local Travel - Weekdays") ){
						totalNonTaxableAmount = totalNonTaxableAmount+csd.getClaimAmount();
					}else if(csd.getClaimCategory().equals(PERDIEM)){
						perDiemAmount = csd.getClaimAmount();
						perDeimReportVo.setPerDiemAmount(csd.getClaimAmount());
						perDeimReportVo.setPerDiemAmountCurrency(csd.getBillCurrency());
					}else{
						if(otherAmount.containsKey(csd.getBillCurrency())){
							otherAmount.put(csd.getBillCurrency(), csd.getClaimAmount()+otherAmount.get(csd.getBillCurrency()) );
						}else {
				            	otherAmount.put(csd.getBillCurrency(), csd.getClaimAmount());
				            	}
					}
				}
			}else if(claimCategory.equals(PERDIEMOWN)){
				for(ClaimSubmitDetailsProjection csd : claimBillDetailsList){
					
					if(csd.getClaimCategory().equals("Meals") || csd.getClaimCategory().equals("Lodging") || csd.getClaimCategory().equals("Local Travel") || csd.getClaimCategory().equals("Local Travel - Weekdays") ){
						totalNonTaxableAmount = totalNonTaxableAmount+csd.getClaimAmount();
					}else if(csd.getClaimCategory().equals(PERDIEMOWN)){
						perDiemAmount = csd.getClaimAmount();
						perDeimReportVo.setPerDiemAmount(csd.getClaimAmount());
						perDeimReportVo.setPerDiemAmountCurrency(csd.getBillCurrency());
					}else{
						if(otherAmount.containsKey(csd.getBillCurrency())){
							otherAmount.put(csd.getBillCurrency(), csd.getClaimAmount()+otherAmount.get(csd.getBillCurrency()) );
						}else {
				            	otherAmount.put(csd.getBillCurrency(), csd.getClaimAmount());
				            	}
					}
				}
			}
			            
			perDeimReportVo.setOtherAmount(otherAmount);
			perDeimReportVo.setNonTaxableAmount(totalNonTaxableAmount);
			perDeimReportVo.setTaxableAmount(perDiemAmount-totalNonTaxableAmount);
			
			// Bill Details Part
			
        	// Approve Details part
			//        	List<ClaimApproveDetails> claimApproveList = new ArrayList<>();
			//			claimApproveList = claimApproveDetailsRepo.getApprovelClaims(claimId);
			//			for(ClaimApproveDetails cad : claimApproveList){
			//				cad.getModifiedDate();
			//			}
            // Approve Details part
			
        	
        	perDiemReportList.add(perDeimReportVo);
        	
        }
        }
	return perDiemReportList;
	}
	

}
		


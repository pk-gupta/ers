package com.marlabs.rmbs.request.repository;


import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.marlabs.rmbs.entities.ClaimSubmitDetails;
import com.marlabs.rmbs.report.vo.ClaimSubmitDetailsProjection;


public interface ClaimRequestDetailRepository extends JpaRepository<ClaimSubmitDetails, Integer>{
	
	
	 @Query("SELECT details FROM ClaimSubmitDetails details where claimHeaderId."
		 		+ "id = :claimId")
		    public List<ClaimSubmitDetails> getClaimDetails(@Param("claimId") Integer claimId);
	 
	 @Query("SELECT new com.marlabs.rmbs.entities.ClaimSubmitDetails(id,claimCategory,mode,billDate,visitedLocation,sourceAddress,destinationAddress,"
	 		+ "vendor,billCurrency,rate,distance,unit,giftQuantity,issuedTo,fromDate,toDate,"
	 		+ "totalExpenseAmount,claimAmount,clientBillable,fileName,remarks,payableAmount,"
	 		+ "financeRemarks,verified) FROM ClaimSubmitDetails where claimHeaderId."
		 		+ "id = :claimId")
		    public List<ClaimSubmitDetails> getBillDetailsWithoutFile(@Param("claimId") Integer claimId);
	 
	 @Query("select new com.marlabs.rmbs.report.vo.ClaimSubmitDetailsProjection(details.claimCategory,details.billCurrency,details.claimAmount)"
				+ "FROM ClaimSubmitDetails details where claimHeaderId.id = :claimId")
		    public List<ClaimSubmitDetailsProjection> getClaimDetailsData(@Param("claimId") Integer claimId);


	@Query("Select distinct claimHeaderId.id from ClaimSubmitDetails where claimCategory = :claimCategory AND claimHeaderId.claimTypeId.id = :claimType "
			+ "AND claimHeaderId.claimDate >= :fromDate AND claimHeaderId.claimDate <= :toDate AND (claimHeaderId.finalStatus.id=3 OR claimHeaderId.finalStatus.id=7)")
	public List<Integer> getClaimIdList(@Param("claimType") Integer claimType, @Param("claimCategory") String claimCategory, @Param("fromDate") Date fromDate,@Param("toDate") Date toDate);
	
	 

}

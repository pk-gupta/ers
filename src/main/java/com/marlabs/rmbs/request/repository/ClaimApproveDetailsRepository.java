package com.marlabs.rmbs.request.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.marlabs.rmbs.entities.ClaimApproveDetails;

@Repository
@Transactional
public interface ClaimApproveDetailsRepository  extends JpaRepository<ClaimApproveDetails, Integer> {

	
	
	
	 @Query("SELECT approve FROM ClaimApproveDetails approve where claimHeaderId."
		 		+ "id = :claimId order by id asc")
		    public List<ClaimApproveDetails> getApprovelClaims(@Param("claimId") Integer claimId);
	 
		
	 @Query("SELECT approve FROM ClaimApproveDetails approve where claimHeaderId."
		 		+ "id = :claimId order by id asc")
		    public List<ClaimApproveDetails> getApprovelClaimsByPage(@Param("claimId") Integer claimId,Pageable pageable);
	 
	 
	 @Query("SELECT approve FROM ClaimApproveDetails approve where approverId.id = :employeeId "
	 		+ " and claimStatus.id !=:draftStatusId and claimStatus.id !=:rejectStatusId and claimStatus.id !=:approveStatusId order by id desc")
		    public List<ClaimApproveDetails> getApprovelClaimsByManager(@Param("employeeId") Integer employeeId,
		    		@Param("draftStatusId") Integer draftStatusId, @Param("rejectStatusId") Integer rejectStatusId,@Param("approveStatusId") Integer approveStatusId);
}

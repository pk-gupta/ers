package com.marlabs.rmbs.request.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;




import com.marlabs.rmbs.entities.ClaimSubmitHeader;




@Repository

public interface ClaimRequestHeaderRepository extends JpaRepository<ClaimSubmitHeader, Integer> {

	@Query("SELECT header FROM ClaimSubmitHeader header where requestingUserId." + "id = :userId order by id desc")
	public List<ClaimSubmitHeader> getClaims(@Param("userId") Integer userId);

	@Query("SELECT header FROM ClaimSubmitHeader header where requestingUserId." + "id = :userId order by id desc")
	public List<ClaimSubmitHeader> getPageClaims(@Param("userId") Integer userId, Pageable pageable);
	
	@Query("SELECT header FROM ClaimSubmitHeader header where requestingUserId." + "id != :userId AND ((finalStatus.id =:forwardStatusId AND forwardPersonId = :userId) OR (finalStatus.id =:pendingStatusId AND approvedPersonId.id =:userId) OR (finalStatus.id =:validateStatusId AND validatePersonId=:userId)) order by id desc")
	public List<ClaimSubmitHeader> getPageClaimsForApprover(@Param("userId") Integer userId,@Param("forwardStatusId") Integer forwardStatusId, @Param("pendingStatusId") Integer pendingStatusId,@Param("validateStatusId") Integer validateStatusId, Pageable page);
	
	@Query("SELECT header FROM ClaimSubmitHeader header where requestingUserId." + "id != :userId AND (finalStatus.id =:approveStatusId AND financeType=1) order by id desc")
	public List<ClaimSubmitHeader> getPageClaimsForClearer(@Param("userId") Integer userId, @Param("approveStatusId") Integer approveStatusId,Pageable page);
	
	@Query("SELECT Count(*) FROM ClaimSubmitHeader where requestingUserId." + "id = :userId")
	public Integer  getCountForNormalUser(@Param("userId") Integer userId);
	
	@Query("SELECT Count(*) FROM ClaimSubmitHeader where requestingUserId." + "id = :userId AND finalStatus.id=2")
	public Integer  getPendingCount(@Param("userId") Integer userId);
	
	@Query("SELECT Count(*) FROM ClaimSubmitHeader where requestingUserId." + "id != :userId AND ((finalStatus.id =:forwardStatusId AND forwardPersonId = :userId) OR (finalStatus.id =:pendingStatusId AND approvedPersonId.id =:userId) OR (finalStatus.id =:validateStatusId AND validatePersonId=:userId))")
	public Integer  getCountForApprover(@Param("userId") Integer userId,@Param("forwardStatusId") Integer forwardStatusId, @Param("pendingStatusId") Integer pendingStatusId,@Param("validateStatusId") Integer validateStatusId);
	
	@Query("SELECT Count(*) FROM ClaimSubmitHeader where requestingUserId." + "id != :userId AND (finalStatus.id =:approveStatusId AND financeType=1)")
	public Integer  getCountForClearer(@Param("userId") Integer userId, @Param("approveStatusId") Integer approveStatusId);
	
	
	ClaimSubmitHeader findTop1ByOrderByIdDesc();

	
	@Query("FROM ClaimSubmitHeader where approvedPersonId.id IN (:ids) "
			+ " and finalStatus.id =:forwardStatusId and requestingUserId.id!=:userId order by id desc")
	public List<ClaimSubmitHeader> getClaimsByManager(@Param("ids") List<Integer> users,
			@Param("forwardStatusId") Integer forwardStatusId,@Param("userId") Integer userId);


	@Query("FROM ClaimSubmitHeader where  "
			+ "finalStatus.id =:forwardStatusId and forwardPersonId=:userId order by id desc")
	public List<ClaimSubmitHeader> getForwardClaimsByManager(@Param("forwardStatusId") Integer forwardStatusId,@Param("userId") Integer userId);
	
	@Query("FROM ClaimSubmitHeader where finalStatus.id =:validateStatusId and validatePersonId=:userId order by id desc")
	public List<ClaimSubmitHeader> getValidateClaimsByApprover(@Param("userId") Integer userId, @Param("validateStatusId") Integer validateStatusId);
	
	@Query("FROM ClaimSubmitHeader where approvedPersonId.id =:ids "
			+ " and finalStatus.id =:pendingStatusId  order by id desc")
	public List<ClaimSubmitHeader> getClaimsByApprover(@Param("ids") Integer users,
			@Param("pendingStatusId") Integer pendingStatusId);

	
//	@Query("SELECT count(*) from ClaimSubmitHeader where requestingUserId." + "id = :userId")
//	public int getclaimsCount(@Param("userId") Integer userId);
	
	@Query("SELECT count(*) from ClaimSubmitHeader where requestingUserId." + "id = :userId AND finalStatus." + "id = 2")
	public int getclaimsCountWithPendingStatus(@Param("userId") Integer userId);
	
	@Query("FROM ClaimSubmitHeader where finalStatus.id =:approveStatusId and requestingUserId.id!=:userId and financeType=1  order by id desc")
	List<ClaimSubmitHeader> getApprovedClaimList(@Param("approveStatusId") Integer approveStatusId,
			@Param("userId") Integer userId);
	
	
	@Query("FROM ClaimSubmitHeader where finalStatus.id =:approveStatusId and requestingUserId.id!=:userId and financeType=2  order by id desc")
	List<ClaimSubmitHeader> getApprovedClaimListIIS(@Param("approveStatusId") Integer approveStatusId,
			@Param("userId") Integer userId);
	
	
	@Query("FROM ClaimSubmitHeader where projectMasterId.id =:projectId and"
			+ " (claimDate >= :fromDate and  claimDate <= :toDate) and finalStatus.id!=:draftStatus and finalStatus.id!=:reviewstatus and finalStatus.id!=:rejectstatus order by claimDate desc")
	List<ClaimSubmitHeader> findClaimsByProject(@Param("projectId") Integer projectId,@Param("fromDate") Date fromDate,
			@Param("toDate") Date toDate,@Param("draftStatus") Integer draft,@Param("reviewstatus") Integer review,@Param("rejectstatus") Integer reject);

	
	@Query("FROM ClaimSubmitHeader where projectMasterId.id =:projectId and"
			+ " (claimDate >= :fromDate and  claimDate <= :toDate) and finalStatus.id=:status and finalStatus.id!=:draftStatus and finalStatus.id!=:reviewstatus and finalStatus.id!=:rejectstatus order by claimDate desc")
	List<ClaimSubmitHeader> findClaimsByProjectWithStatus(@Param("projectId") Integer projectId,@Param("status") Integer status,@Param("fromDate") Date fromDate,
			@Param("toDate") Date toDate,@Param("draftStatus") Integer draft,@Param("reviewstatus") Integer review,@Param("rejectstatus") Integer reject);

	
	
	@Query("FROM ClaimSubmitHeader where (claimDate >= :fromDate and  claimDate <= :toDate) and finalStatus.id!=:draftStatus and finalStatus.id!=:reviewstatus and finalStatus.id!=:rejectstatus order by claimDate desc")
	List<ClaimSubmitHeader> findClaimsByDateRange(@Param("fromDate") Date fromDate,
			@Param("toDate") Date toDate,@Param("draftStatus") Integer draft,@Param("reviewstatus") Integer review,@Param("rejectstatus") Integer reject);

	@Query("FROM ClaimSubmitHeader where (claimDate >= :fromDate and  claimDate <= :toDate) and finalStatus.id=:status and finalStatus.id!=:draftStatus and finalStatus.id!=:reviewstatus and finalStatus.id!=:rejectstatus order by claimDate desc")
	List<ClaimSubmitHeader> findClaimsByDateRangeWithStatus(@Param("status") Integer status,@Param("fromDate") Date fromDate,
			@Param("toDate") Date toDate,@Param("draftStatus") Integer draft,@Param("reviewstatus") Integer review,@Param("rejectstatus") Integer reject);

	
	
	//fetch all projects from beginning 
	@Query("FROM ClaimSubmitHeader where (claimDate <= :toDate) and finalStatus.id!=:draftStatus and finalStatus.id!=:reviewstatus and finalStatus.id!=:rejectstatus order by claimDate desc")
	public List<ClaimSubmitHeader> findAllClaimsFromBeginning(
			@Param("toDate") Date toDate,@Param("draftStatus") Integer draft,@Param("reviewstatus") Integer review,@Param("rejectstatus") Integer reject);
	
	@Query("FROM ClaimSubmitHeader where (claimDate <= :toDate) and finalStatus.id=:status and finalStatus.id!=:draftStatus and finalStatus.id!=:reviewstatus and finalStatus.id!=:rejectstatus order by claimDate desc")
	public List<ClaimSubmitHeader> findAllClaimsFromBeginningWithStatus(
			@Param("status") Integer status,@Param("toDate") Date toDate,@Param("draftStatus") Integer draft,@Param("reviewstatus") Integer review,@Param("rejectstatus") Integer reject);
	
	//fetch projects from beginning 
	@Query("FROM ClaimSubmitHeader where projectMasterId.id =:projectId and"
			+ " (claimDate <= :toDate) and finalStatus.id!=:draftStatus and finalStatus.id!=:reviewstatus and finalStatus.id!=:rejectstatus order by claimDate desc")
	public List<ClaimSubmitHeader> findClaimsByProjectFromBeginning(
			@Param("projectId") Integer projectId,
			@Param("toDate") Date toDate,@Param("draftStatus") Integer draft,@Param("reviewstatus") Integer review,@Param("rejectstatus") Integer reject);

	@Query("FROM ClaimSubmitHeader where projectMasterId.id =:projectId and"
			+ " (claimDate <= :toDate) and finalStatus.id=:status and finalStatus.id!=:draftStatus and finalStatus.id!=:reviewstatus and finalStatus.id!=:rejectstatus order by claimDate desc")
	public List<ClaimSubmitHeader> findClaimsByProjectFromBeginningWithStatus(
			@Param("status") Integer status,@Param("projectId") Integer projectId,
			@Param("toDate") Date toDate,@Param("draftStatus") Integer draft,@Param("reviewstatus") Integer review,@Param("rejectstatus") Integer reject);
	
	@Query("FROM ClaimSubmitHeader where requestingUserId.id =:userId and finalStatus.id!=:draftStatus and finalStatus.id!=:reviewstatus and finalStatus.id!=:rejectstatus order by claimDate desc")
	public List<ClaimSubmitHeader> findClaimsByUserId(@Param("userId") Integer userId, @Param("draftStatus") Integer draft,@Param("reviewstatus") Integer review,@Param("rejectstatus") Integer reject);
	

	

	

	

	
}

package com.marlabs.rmbs.masters.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.marlabs.rmbs.entities.PerDiemMaster;


@Repository
public interface PerDiemMasterRepository extends JpaRepository<PerDiemMaster,Integer>{
	@Query("SELECT distinct location FROM PerDiemMaster")
	public List<String> distinctByLocation();
	
	@Query("SELECT perDiemAmnt FROM PerDiemMaster where claimTypeId= :claimType and "
			+ "category= :claimCategory AND grade= :designationLevel AND location= :location AND effectiveDate = :beforeFirstjuly")
	public Double getperDiemAmntBeforeJuly(@Param("claimType") Integer claimType,
			@Param("claimCategory") String claimCategory,
			@Param("designationLevel") Integer designationLevel, 
			@Param("location") String location,
			@Param("beforeFirstjuly") Date beforeFirstjuly);
	
	@Query("SELECT perDiemAmnt FROM PerDiemMaster where claimTypeId= :claimType and "
			+ "category= :claimCategory AND grade= :designationLevel AND location= :location AND effectiveDate = :afterFirstjuly")
	public Double getperDiemAmntAfterJuly(@Param("claimType") Integer claimType,
			@Param("claimCategory") String claimCategory,
			@Param("designationLevel") Integer designationLevel, 
			@Param("location") String location,
			@Param("afterFirstjuly") Date afterFirstjuly);
	
	
	
	@Query("SELECT Distinct currency  FROM PerDiemMaster where location= :location")
	public String getCurrency(@Param("location") String location);
	@Query("SELECT distinct effectiveDate FROM PerDiemMaster")
	public List<Date> distinctByDate();
}

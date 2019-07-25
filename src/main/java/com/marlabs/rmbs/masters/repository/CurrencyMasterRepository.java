package com.marlabs.rmbs.masters.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.marlabs.rmbs.entities.CurrencyMaster;

@Repository
public interface CurrencyMasterRepository extends JpaRepository<CurrencyMaster,Integer>{
    @Query("Select id from CurrencyMaster where currencyCode = :currency")
    int findCurrencyId(@Param("currency") String currency);
  
}

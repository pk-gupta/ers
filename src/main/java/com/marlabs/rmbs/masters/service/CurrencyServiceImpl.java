/**
 * 
 */
package com.marlabs.rmbs.masters.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marlabs.rmbs.entities.CurrencyMaster;
import com.marlabs.rmbs.masters.repository.CurrencyMasterRepository;

/**
 * CurrencyServiceImpl.java
 * @author Pappu.kumar
 * Dec 31, 2015
 */

@Service
public class CurrencyServiceImpl implements CurrencyService{
	
		@Autowired
		private CurrencyMasterRepository currencyMasterRepository;

		public List<CurrencyMaster> loadCurrency(){
			return (List<CurrencyMaster>) currencyMasterRepository.findAll();
		  }
			
		}



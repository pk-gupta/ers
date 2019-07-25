/**
 * 
 */
package com.marlabs.rmbs.masters.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.marlabs.rmbs.entities.CurrencyMaster;

/**
 * CurrencyService.java
 * @author Pappu.kumar
 * Dec 31, 2015
 */

@Service
public interface CurrencyService {
	public List<CurrencyMaster> loadCurrency();

}

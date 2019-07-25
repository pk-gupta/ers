/**
 * 
 */
package com.marlabs.rmbs.masters.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.marlabs.rmbs.entities.ClaimStatus;
import com.marlabs.rmbs.entities.CurrencyMaster;

import com.marlabs.rmbs.entities.UserMaster;
import com.marlabs.rmbs.masters.repository.ClaimStatusRepository;
import com.marlabs.rmbs.masters.repository.CurrencyMasterRepository;

import com.marlabs.rmbs.masters.service.CurrencyService;
import com.marlabs.rmbs.masters.service.UserMasterRepository;
import io.jsonwebtoken.Claims;

@RestController
public class CurrencyController {

	@Autowired
	private CurrencyService currencyService;

	@Autowired
	private CurrencyMasterRepository currencyMasterRepository;

	@Autowired
	private ClaimStatusRepository claimStatusRepository;

	@Autowired
	private UserMasterRepository userMasterRepository;

	@RequestMapping(value = "/currency", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<CurrencyMaster> loadCurrency() {
		try {
			return currencyService.loadCurrency();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@RequestMapping(value = "/currencyUpdate", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> updateCurrency(@RequestBody CurrencyMaster currencyData, HttpServletRequest http) {
		try {

			Claims claim = (Claims) http.getAttribute("claims");
			String email = (String) claim.get("contactId");

			UserMaster user = userMasterRepository.findByMailIgnoreCase(email);
			if (user.getRoleId() == 3 || user.getRoleId()== 2) {
				return new ResponseEntity<>(HttpStatus.FORBIDDEN);

			}
			CurrencyMaster currency = new CurrencyMaster();
			currency.setId(currencyData.getId());
			currency.setCurrencyCode(currencyData.getCurrencyCode());
			currency.setCurrencyDesc(currencyData.getCurrencyDesc());
			currencyMasterRepository.save(currency);
		} catch (Exception e) {
			e.printStackTrace();

		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "/claimstatus", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ClaimStatus> loadClaimStatus() {
		try {
			return claimStatusRepository.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	

}

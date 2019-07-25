package com.marlabs.rmbs.review.service;

import java.util.Date;
import java.util.List;

import com.marlabs.rmbs.entities.FrequentAskedQuestions;
import com.marlabs.rmbs.request.vo.ClaimRequestHeaderVo;


public interface ReviewService {
	
	public List<ClaimRequestHeaderVo> getClaimsByProject(Integer projectId,Integer status,Date fromDate,Date toDate);
	
	public List<FrequentAskedQuestions> getFreqAskedQuestion();


	public List<ClaimRequestHeaderVo> getClaimsByUserId(Integer userId);
	void deleteFreqAskedQuestion(int id); 

}

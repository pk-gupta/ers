package com.marlabs.rmbs.review.service;

import java.util.ArrayList;
import java.util.List;

public class FaqDTO {
	private List<FaqListVo> faqDetailsList=new ArrayList<>();

	public List<FaqListVo> getFaqDetailsList() {
		return faqDetailsList;
	}

	public void setFaqDetailsList(List<FaqListVo> faqDetailsList) {
		this.faqDetailsList = faqDetailsList;
	}
}

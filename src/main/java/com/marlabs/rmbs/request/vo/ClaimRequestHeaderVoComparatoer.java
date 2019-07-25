package com.marlabs.rmbs.request.vo;

import java.util.Comparator;

public class ClaimRequestHeaderVoComparatoer implements Comparator<ClaimRequestHeaderVo>{
		@Override
		public int compare(ClaimRequestHeaderVo v1, ClaimRequestHeaderVo v2) {
			return v2.getModifiedDate().compareTo(v1.getModifiedDate());
		}
		
	}

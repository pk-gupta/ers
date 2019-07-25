package com.marlabs.rmbs.report.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.marlabs.rmbs.report.vo.PerDiemReportVo;
@Service
public interface ReportGenerationService {

	public List<PerDiemReportVo> getPerDiemReport(Integer claimType, String claimCategory, Date fromPerdiemDate, Date toPerdiemDate);

}

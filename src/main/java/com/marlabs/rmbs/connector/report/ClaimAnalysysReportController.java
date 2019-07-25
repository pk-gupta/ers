package com.marlabs.rmbs.connector.report;

import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.marlabs.rmbs.entities.UserMaster;
import com.marlabs.rmbs.masters.constant.DepartmentConstant;
import com.marlabs.rmbs.masters.service.UserMasterRepository;
import io.jsonwebtoken.Claims;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRCsvExporter;


@SuppressWarnings("deprecation")
@RestController
public class ClaimAnalysysReportController {
	
	
	@Autowired
	private UserMasterRepository userMasterRepository;
    @Autowired
    private Environment env;      

	
	SimpleDateFormat sdfr = new SimpleDateFormat("dd/MM/yyyy");
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	
	@RequestMapping(value = "/claims/analysis/report", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<byte[]> loadClaimType(@RequestParam("projectId") final Integer projectId,@RequestParam("fromDate") final String fromDateString,
			@RequestParam("toDate") final String toDateString, @RequestParam("format") final String format,HttpServletRequest http) throws Exception {
		
		
		Claims claim=(Claims) http.getAttribute("claims");
		String email=(String) claim.get("contactId");
		

		UserMaster user=userMasterRepository.findByMailIgnoreCase(email);
		if(!DepartmentConstant.isClearer(user.getEmployeeName())){
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
			
		}
		else{
			
		
		
		
		Connection con = null ;
		
		try {
			log.info("##########Entered into ClaimAnalysysReportController.loadClaimType()##########");
			Properties prop = new Properties();
			InputStream input = null;
			input = getClass().getClassLoader().getResourceAsStream("application.properties");
			prop.load(input);

			String url=env.getProperty("spring.datasource.url");
            String username=env.getProperty("spring.datasource.username");
            String password=env.getProperty("spring.datasource.password");
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection(url,username,password);
			
			String reportName="";
			if("csv".equalsIgnoreCase(format)){
				reportName="reports/claim_analysis_csv.jrxml";
			}else{
				reportName="reports/claim_analysis_pdf.jrxml";
			}

			ClassLoader classLoader = getClass().getClassLoader();

			URL reportUrl = classLoader.getResource(reportName);
			URL imageUrl = classLoader.getResource("images/marlabs-top-logo.png");

			Date fromDate = null;
			Date toDate = new Date();
			String expenseReportQuery = null;
			if(!("null").equals(fromDateString)){
				fromDate= sdfr.parse(fromDateString);
			}
			if(!("null").equals(toDateString)){
				toDate= sdfr.parse(toDateString);
				toDate=DateUtils.addDays(toDate, 1);
			}
			//all projects
			if(fromDate != null){
				//all projects in a specific date range
				if (projectId.equals(0) ) {
					expenseReportQuery = ExpenseReportQueries.findClaimsByDateRange;
				}else{
					//projects in a specific date range
					expenseReportQuery = ExpenseReportQueries.findClaimsByProjectAndDateRange;
				}
			}
			else{
				//all projects from beginning
				if (projectId.equals(0) ) {
					expenseReportQuery = ExpenseReportQueries.findAllClaimsFromBeginning;
				}else{
					//projects from beginning
					expenseReportQuery = ExpenseReportQueries.findClaimsByProjectFromBeginning;
				}
			}
			Map<String, Object> parameters = new HashMap<>();
			
			parameters.put("fromDate", fromDate);
			parameters.put("projectId",projectId);
			parameters.put("toDate", toDate);
			parameters.put("realPath", imageUrl.getPath());
			parameters.put("expenseReportQuery", expenseReportQuery);

			JasperReport jasperReport = JasperCompileManager.compileReport(reportUrl.getPath());
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, con);

			String contentType = null;
			String filename = null;
			if ("pdf".equalsIgnoreCase(format)) {

				filename = "claim_analysis_report.pdf";
				JasperExportManager.exportReportToPdfFile(jasperPrint, filename);
				contentType = "application/pdf";
			} 
			else if("csv".equalsIgnoreCase(format)) {
				filename = "claim_analysis_report.csv";
				JRCsvExporter exporter = new JRCsvExporter();
				exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
				exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, filename);
				exporter.exportReport();
				contentType = "application/vnd.ms-excel";
			}

			Path path = Paths.get(filename);
			byte[] data = Files.readAllBytes(path);

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.parseMediaType(contentType));
			headers.setContentDispositionFormData(filename, filename);
			headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
			ResponseEntity<byte[]> response = new ResponseEntity<>(data, headers, HttpStatus.OK);
			log.info("##########Exit from ClaimAnalysysReportController.loadClaimType()##########");
			return response;
			
		} catch (Exception e) {
			log.debug("------Exception in ClaimAnalysisController------",e);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} finally {
			if(con!=null)
			con.close();
		}
		
	}
	
	}
	
}

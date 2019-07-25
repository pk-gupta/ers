package com.marlabs.rmbs.connector.report;

import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.marlabs.rmbs.masters.service.UserMasterRepository;
import com.marlabs.rmbs.request.repository.ClaimRequestHeaderRepository;
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
public class ReportController {
	
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ClaimRequestHeaderRepository claimRequestHeaderRepository;
	
	@Autowired
	private UserMasterRepository userMasterRepository;
	
	@RequestMapping(value = "/claims/report", method = RequestMethod.GET)
	

	@ResponseBody
	public ResponseEntity<byte[]> loadClaimType(@RequestParam("claimId") final Integer claimId,
			@RequestParam("format") final String format,HttpServletRequest http) throws Exception {
		Connection con = null;
		log.info("##########Entered into ReportController.loadClaimType()##########");
		
		 UserMaster user=claimRequestHeaderRepository.getOne(claimId).getRequestingUserId();
		 Claims claim=(Claims) http.getAttribute("claims");
		 String email=(String) claim.get("contactId");
			

			UserMaster userId=userMasterRepository.findByMailIgnoreCase(email);
			
			if(user.getId()!=userId.getId()){
			
				return new ResponseEntity<>(HttpStatus.FORBIDDEN);
			}
		try {
		
			log.debug("------------------------------------->");
			Properties prop = new Properties();
			InputStream input = null;
			input = getClass().getClassLoader().getResourceAsStream("application.properties");

			prop.load(input);
			
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection(
					prop.getProperty("spring.datasource.url"),
					prop.getProperty("spring.datasource.username"),
					prop.getProperty("spring.datasource.password"));

			String reportName="";
			if("csv".equalsIgnoreCase(format)){
				reportName="reports/claim_report_csv.jrxml";
			}else{
				reportName="reports/claim_report_pdf.jrxml";
			}
			
				
			ClassLoader classLoader = getClass().getClassLoader();
			URL reportUrl = classLoader.getResource(reportName);
			URL imageUrl = classLoader.getResource("images/marlabs-top-logo.png");
			URL pendingImageUrl = classLoader.getResource("images/pending_image.png");
			URL approveImageUrl = classLoader.getResource("images/approved_images.png");
			
			
			//QR Code Image Generation

				
			Map<String, Object> parameters = new HashMap<>();
			parameters.put("claimId", claimId);
			parameters.put("realPath", imageUrl.getPath());
			parameters.put("pendingPath", pendingImageUrl.getPath());
			parameters.put("approvedPath", approveImageUrl.getPath());
		
		 
		
		
			
			
			
			JasperReport jasperReport = JasperCompileManager.compileReport(reportUrl.getPath());
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, con);
			
			String contentType=null;
			String filename=null;
			
			
			if ("pdf".equalsIgnoreCase(format)) {

				filename = "claim_report.pdf";
				JasperExportManager.exportReportToPdfFile(jasperPrint, filename);
				contentType = "application/pdf";
			} else if ("csv".equalsIgnoreCase(format)) {
				filename = "claim_report.csv";
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
			ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(data, headers, HttpStatus.OK);
			log.info("##########Exit from ReportController.loadClaimType()##########");
			return response;
		} catch (Exception e) {
			throw new Exception("Exception in ReportController",e);
		
		}
		finally {
			if(con!=null)
			con.close();
			
	}

	}

}

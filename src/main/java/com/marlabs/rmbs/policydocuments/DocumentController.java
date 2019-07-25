package com.marlabs.rmbs.policydocuments;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.marlabs.rmbs.entities.ReferenceDocuments;
import com.marlabs.rmbs.entities.UserMaster;
import com.marlabs.rmbs.masters.service.UserMasterRepository;
import com.marlabs.rmbs.request.repository.ReferenceDocumentsRepository;
import io.jsonwebtoken.Claims;
import sun.misc.BASE64Decoder;

@RestController
public class DocumentController {
	
	@Value("${file.location}")
	private String location;

	SimpleDateFormat sdfr = new SimpleDateFormat("dd/MM/yyyy");

	ReferenceDocuments referenceValues = new ReferenceDocuments();

	@Autowired
	ReferenceDocumentsRepository referenceDocumentPath;
	@Autowired
	private DocumentServiceImpl reviewServiceImpl;
@Autowired
private UserMasterRepository userMasterRepository;


	@ResponseBody
	@RequestMapping(value = "/load", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public ResponseEntity<List<ReferenceDocuments>> getDocs() {
		try {
			List<ReferenceDocuments> faqList = reviewServiceImpl.getReferenceDocumentsList();
			Iterator<ReferenceDocuments> it = faqList.iterator();
			while (it.hasNext()) {

				ReferenceDocuments name = it.next();

				if (name.getDeleted()) {
					it.remove();
				}

			}
			return new ResponseEntity<>(faqList, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	@RequestMapping(value = "/downloadPDF", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<byte[]> downloadPDF(@RequestParam("Id") int Id, @RequestParam("name") String name) {

		String docPath = referenceDocumentPath.getdocName(Id);
		Path path = Paths.get(docPath);
		byte[] data;
		try {
			data = Files.readAllBytes(path);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType("application/pdf"));
		headers.setContentDispositionFormData(name + ".pdf", name + ".pdf");
		headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(data, headers, HttpStatus.OK);

		return response;
        } catch (Exception e) {
			
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<byte[]> removeDoc(@RequestParam("Id") int Id,HttpServletRequest http) throws Exception {
		

		Claims claim=(Claims) http.getAttribute("claims");
		String mail=(String) claim.get("contactId");
		
		UserMaster user=userMasterRepository.findByMailIgnoreCase(mail);
		if((user.getRoleId()==3 || user.getRoleId()==2)){
			return new ResponseEntity<byte[]>(HttpStatus.FORBIDDEN);
			
		}
		referenceDocumentPath.deletedFlag(Id);

		ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(HttpStatus.OK);
		return response;
	}

	@RequestMapping(value = "/upload", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public void uploadClaim(@RequestBody DocumentDTO data,HttpServletRequest http) throws Exception {
		
		
		Claims claim=(Claims) http.getAttribute("claims");
		String mail=(String) claim.get("contactId");
		UserMaster user=userMasterRepository.findByMailIgnoreCase(mail);
		if((user.getRoleId()==3 || user.getRoleId()==2)){
			throw new ServletException("invalid user");
			
		}
		ReferenceDocuments referenceValues = new ReferenceDocuments();
		
		String path = location;
	
		String fileName =  data.getFileName();
		String name = data.getDocumentName();
		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		fileName=timeStamp+"_"+fileName;
		String savePath = path + fileName;
		
		String pdfFile = data.getFile(); 
		BASE64Decoder decoder = new BASE64Decoder();
		byte[] decodedBytes = decoder.decodeBuffer(pdfFile);

		File file = new File(savePath);
		
		FileOutputStream fop = new FileOutputStream(file);

		fop.write(decodedBytes);
		fop.flush();
		fop.close();
		Date currentDate = new Date();
		int id = (int) (currentDate.getTime() / 1000);
		referenceValues.setId(id);
		referenceValues.setName(name);
		referenceValues.setDeleted(false);
		referenceValues.setPath(savePath);
		referenceDocumentPath.save(referenceValues);

	}

}

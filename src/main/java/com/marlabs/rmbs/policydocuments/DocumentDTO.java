package com.marlabs.rmbs.policydocuments;


import org.springframework.hateoas.ResourceSupport;

public class DocumentDTO extends ResourceSupport{
	
	private String fileName;
	private String documentName;
	private String file;
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getDocumentName() {
		return documentName;
	}
	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	
}

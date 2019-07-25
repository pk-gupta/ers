package com.marlabs.rmbs.policydocuments;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marlabs.rmbs.entities.ReferenceDocuments;
import com.marlabs.rmbs.request.repository.ReferenceDocumentsRepository;


@Service 
public class DocumentServiceImpl implements DocumentService{
	

	@Autowired
	private ReferenceDocumentsRepository referenceDocumentsRepository;
	


	
	
	@Override
	public List<ReferenceDocuments> getReferenceDocumentsList() {
		List <ReferenceDocuments> docList=new ArrayList<ReferenceDocuments>();
		docList=referenceDocumentsRepository.findAll();
		return docList;
	}

}

package com.marlabs.rmbs.request.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.marlabs.rmbs.entities.ReferenceDocuments;

@Repository
public interface ReferenceDocumentsRepository extends JpaRepository<ReferenceDocuments, Integer>{

	 @Query("SELECT path FROM ReferenceDocuments where "
		 		+ "id = :id")
		    public String getdocName(@Param("id") int id);
	 
	 @Transactional
	 @Modifying
	 @Query("UPDATE ReferenceDocuments r SET r.deleted = true where "
		 		+ "id = :id")
		    public int deletedFlag(@Param("id") int id);

}

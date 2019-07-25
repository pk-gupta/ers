package com.marlabs.rmbs.request.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.marlabs.rmbs.entities.FrequentAskedQuestions;

@Repository
public interface FrequentlyAskedRepository extends JpaRepository<FrequentAskedQuestions, Integer>{



}

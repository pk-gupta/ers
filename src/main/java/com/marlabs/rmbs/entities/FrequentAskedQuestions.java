package com.marlabs.rmbs.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "freq_ask_quest")
public class FrequentAskedQuestions {
	
	  @Id
	  @GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	  @Column(name="answer",length=1000)
	private String answer;
	  @Column(name="questions",length=1000)
	private String questions;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getQuestions() {
		return questions;
	}
	public void setQuestions(String questions) {
		this.questions = questions;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	
	

}

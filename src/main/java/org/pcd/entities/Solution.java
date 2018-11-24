package org.pcd.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Solution implements Serializable{
	@Id @GeneratedValue
	private Long id;
	@Column(columnDefinition = "TEXT")
	private String code;
	@ManyToOne
	private Probleme probleme;
	@ManyToOne
	private Etudiant etudiant;
	private Date date;
	private String result;
	private Boolean plagiat;
	
	public Boolean getPlagiat() {
		return plagiat;
	}
	public void setPlagiat(Boolean plagiat) {
		this.plagiat = plagiat;
	}
	public Solution(String code, Probleme probleme, Etudiant etudiant, Date date, String result) {
		super();
		this.code = code;
		this.probleme = probleme;
		this.etudiant = etudiant;
		this.date = date;
		this.result = result;
	}
	public Solution(Probleme probleme, Etudiant etudiant, Date date, String result) {
		super();
		this.probleme = probleme;
		this.etudiant = etudiant;
		this.date = date;
		this.result = result;
	}
	
	public Solution(String code, Probleme probleme, Etudiant etudiant, Date date, String result, Boolean plagiat) {
		super();
		this.code = code;
		this.probleme = probleme;
		this.etudiant = etudiant;
		this.date = date;
		this.result = result;
		this.plagiat = plagiat;
	}
	public Solution() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Probleme getProbleme() {
		return probleme;
	}
	public void setProbleme(Probleme probleme) {
		this.probleme = probleme;
	}
	public Etudiant getEtudiant() {
		return etudiant;
	}
	public void setEtudiant(Etudiant etudiant) {
		this.etudiant = etudiant;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
}

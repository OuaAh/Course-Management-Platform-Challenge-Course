package org.pcd.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity

public class Etudiant implements Serializable{
	@Id
	private String username;
	private String password;
	private String nom;
	private String prenom;
	private String email;
	private Date date;
	@ManyToOne
	private Groupe groupe;
	@OneToMany(mappedBy="etudiant",fetch=FetchType.LAZY)
	private Collection<Solution> solutions;
	
	public Etudiant() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Etudiant(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	public Etudiant(String username, String password, String nom, String prenom, Date date, Groupe groupe) {
		super();
		this.username = username;
		this.password = password;
		this.nom = nom;
		this.prenom = prenom;
		this.date = date;
		this.groupe = groupe;
	}

	
	public Etudiant(String username, String password, String nom, String prenom, String email, Date date
			, Groupe groupe) {
		super();
		this.username = username;
		this.password = password;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.date = date;
		this.groupe = groupe;
	}
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Groupe getGroupe() {
		return groupe;
	}

	public void setGroupe(Groupe groupe) {
		this.groupe = groupe;
	}

	public Collection<Solution> getSolutions() {
		return solutions;
	}

	public void setSolutions(Collection<Solution> solutions) {
		this.solutions = solutions;
	}
	
}

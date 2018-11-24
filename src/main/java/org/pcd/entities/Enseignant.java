package org.pcd.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Enseignant implements Serializable {
	
	@Id
	private String username;
	private String password;
	private String Nom;
	private String Prenom;
	private String email;
	private Date date;
	private boolean privilege;
	@OneToMany(mappedBy="enseignant",fetch=FetchType.LAZY)
	private Collection<Cours> cours;
	@OneToMany(mappedBy="enseignant",fetch=FetchType.LAZY)
	private Collection<Groupe> groupes;
	@OneToMany(mappedBy="enseignant",fetch=FetchType.LAZY)
	private Collection<Probleme> problemes;
	public Enseignant() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Enseignant(String username, String password, String nom, String prenom, Date date, boolean privilege) {
		super();
		this.username = username;
		this.password = password;
		Nom = nom;
		Prenom = prenom;
		this.date = date;
		this.privilege = privilege;
	}
	
	public Enseignant(String username, String password, String nom, String prenom, String email, Date date,
			boolean privilege) {
		super();
		this.username = username;
		this.password = password;
		Nom = nom;
		Prenom = prenom;
		this.email = email;
		this.date = date;
		this.privilege = privilege;
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
		return Nom;
	}
	public void setNom(String nom) {
		Nom = nom;
	}
	public String getPrenom() {
		return Prenom;
	}
	public void setPrenom(String prenom) {
		Prenom = prenom;
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
	public boolean isPrivilege() {
		return privilege;
	}
	public void setPrivilege(boolean privilege) {
		this.privilege = privilege;
	}
	public Collection<Cours> getCours() {
		return cours;
	}
	public void setCours(Collection<Cours> cours) {
		this.cours = cours;
	}
	public Collection<Groupe> getGroupes() {
		return groupes;
	}
	public void setGroupes(Collection<Groupe> groupes) {
		this.groupes = groupes;
	}
	public Collection<Probleme> getProblemes() {
		return problemes;
	}
	public void setProblemes(Collection<Probleme> problemes) {
		this.problemes = problemes;
	}
	
	
}

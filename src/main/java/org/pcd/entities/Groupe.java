package org.pcd.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Groupe implements Serializable {
	@Id
	private String nom;
	private Date date;
	@ManyToOne
	private Enseignant enseignant;
	@OneToMany(mappedBy="groupe",fetch=FetchType.LAZY)
	private Collection<Etudiant> etudiants;
	
	public Groupe() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Groupe(String nom, Date date, Enseignant enseignant) {
		super();
		this.nom = nom;
		this.date = date;
		this.enseignant = enseignant;
	}



	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public Enseignant getEnseignant() {
		return enseignant;
	}
	public void setEnseignant(Enseignant enseignant) {
		this.enseignant = enseignant;
	}
	public Collection<Etudiant> getEtudiants() {
		return etudiants;
	}
	public void setEtudiants(Collection<Etudiant> etudiants) {
		this.etudiants = etudiants;
	}
}

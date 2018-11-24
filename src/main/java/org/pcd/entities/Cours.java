package org.pcd.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Cours implements Serializable{
	
	@Id @GeneratedValue
	private Long id;
	private String titre;
	@Column(columnDefinition = "TEXT")
	private String contenu;
	private Date date;
	@ManyToOne
	private Enseignant enseignant;
	public Cours(String titre, String contenu, Date date, Enseignant enseignant) {
		super();
		this.titre = titre;
		this.contenu = contenu;
		this.date = date;
		this.enseignant = enseignant;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Cours() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public String getContenu() {
		return contenu;
	}
	public void setContenu(String contenu) {
		this.contenu = contenu;
	}
	public Enseignant getEnseignant() {
		return enseignant;
	}
	public void setEnseignant(Enseignant enseignant) {
		this.enseignant = enseignant;
	}
}

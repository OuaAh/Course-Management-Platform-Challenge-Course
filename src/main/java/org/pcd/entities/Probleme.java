package org.pcd.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Probleme implements Serializable{
	@Id @GeneratedValue
	private Long id;
	private String titre;
	@Column(columnDefinition = "TEXT")
	private String ennonce;
	private Date date;
	private String type;
	private char difficultie;
	@Column(columnDefinition = "TEXT")
	private String input;
	@Column(columnDefinition = "TEXT")
	private String output;
	@ManyToOne
	private Enseignant enseignant;
	@OneToMany(mappedBy="probleme",fetch=FetchType.LAZY)
	private Collection<Solution> solutions;
	

	public Probleme(String titre, String ennonce, Date date, String type, char difficultie, String input, String output,
			Enseignant enseignant) {
		super();
		this.titre = titre;
		this.ennonce = ennonce;
		this.date = date;
		this.type = type;
		this.difficultie = difficultie;
		this.input = input;
		this.output = output;
		this.enseignant = enseignant;
	}
	public Probleme() {
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
	public String getEnnonce() {
		return ennonce;
	}
	public void setEnnonce(String ennonce) {
		this.ennonce = ennonce;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public char getDifficultie() {
		return difficultie;
	}
	public void setDifficultie(char difficultie) {
		this.difficultie = difficultie;
	}
	public String getInput() {
		return input;
	}
	public void setInput(String input) {
		this.input = input;
	}
	public String getOutput() {
		return output;
	}
	public void setOutput(String output) {
		this.output = output;
	}
	public Enseignant getEnseignant() {
		return enseignant;
	}
	public void setEnseignant(Enseignant enseignant) {
		this.enseignant = enseignant;
	}
	public Collection<Solution> getSolutions() {
		return solutions;
	}
	public void setSolutions(Collection<Solution> solutions) {
		this.solutions = solutions;
	}
}

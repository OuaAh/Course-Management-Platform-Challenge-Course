package org.pcd.service;

import java.util.Date;

import org.pcd.entities.Cours;
import org.pcd.entities.Enseignant;
import org.pcd.entities.Etudiant;
import org.pcd.entities.Groupe;
import org.pcd.entities.Probleme;
import org.pcd.entities.Solution;
import org.springframework.data.domain.Page;

public interface IService {
	
	public Enseignant consulterEnseignant(String username);
	public Probleme consulterProbleme(Long id);
	public Etudiant consulterEtudiant(String username);
	public Cours consulterCours(Long id);
	public Solution consulterSolution(Long id);
	
	
	public Page<Groupe> listGroupe(String username,int page,int size);
	public Page<Enseignant> listEnseignant(int page,int size);
	public Page<Solution> listSolution(int page,int size);
	public Page<Cours> listCours(int page,int size);
	public Page<Probleme> listProbleme(int page,int size);
	public Page<Etudiant> listEtudiant(int page,int size); 
	
	public Page<Solution> historyProbleme(Long id,int page,int size); 
	public Page<Solution> historyEtudiant(String username,int page,int size); 
	public Page<Etudiant> listEtudiantParGroup(Long id,String username,int page,int size); 
	
	public Page<Solution> listProblemeACorriger(String username, int page,int size);
	
	public Page<Probleme> listProblemeAjouter(String username,int page,int size);
	public Page<Probleme> listProblemeQuasiDupliques(String titre,String ennonce,String type,char difficultie,String input,String output, int page,int size);
	public Page<Cours> listCoursAjoutes(String username,int page,int size);
	
	public void ajouterEnseignant(String username, String password, String nom, String prenom, boolean privilege);
	public void supprimerEnseignant(String username);
	public void ajouterCours(String titre, String contenu, Enseignant enseignant);
	public void ajouterProbleme(String titre, String ennonce, String type, char difficultie, String input, String output,Enseignant enseignant);
	public void ajouterEtudiant(String username, String password, String nom, String prenom, Groupe groupe);
	public void supprimerEtudiant(String username);
	public void ajouterGroupe(String nom, Enseignant enseignant);
	public void ajouterSolution(String code, Probleme probleme, Etudiant etudiant);

	public void modifierSolution(Long id, String result);
	public void modfierProbleme(Long id,String titre, String ennonce, String type, char difficultie, String input, String output);
	public void modifierProfilEnseignant(String username,String password, String nom, String prenom);
	public void modifierProfilEtudiant(String username, String password, String nom, String prenom);
	
	//public Solution soumettreProbleme();
	
	public boolean DetectiondePlagiat(String code, Long id,String username);
	public int nombreProblemeAccepted(String username);
	public int nombrePersonneAccepted(Long id);
	public int nombreProblemeAjoute(String username);
	public int nombreProblemesAccepter(String username);
	public int nombreProblemeSoumis(String username);
	public int nombreProblemeRefuse(String username);
	public int nombreProblemeattends(String username);
	
	public int nombreProblemesAcceptes();
	public int nombreProblemeSoumis();
	public int nombreProblemeRefuse();
	public int nombreProblemeattends();
}

package org.pcd.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.management.RuntimeErrorException;

import org.pcd.dao.CoursRepository;
import org.pcd.dao.EnseignantRepository;
import org.pcd.dao.EtudiantRepository;
import org.pcd.dao.GroupeRepository;
import org.pcd.dao.ProblemeRepository;
import org.pcd.dao.SolutionRepository;
import org.pcd.entities.Cours;
import org.pcd.entities.Enseignant;
import org.pcd.entities.Etudiant;
import org.pcd.entities.Groupe;
import org.pcd.entities.Probleme;
import org.pcd.entities.Solution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.lang3.StringUtils;

@Service
@Transactional
public class ServiceImpl implements IService{
	@Autowired
	private CoursRepository coursRepository;
	
	@Autowired
	private EnseignantRepository enseignantRepository;
	
	@Autowired
	private EtudiantRepository etudiantRepository;
	
	@Autowired
	private GroupeRepository groupeRepository;
	
	@Autowired
	private ProblemeRepository problemeRepository;
	
	@Autowired
	private SolutionRepository solutionRepository;

	@Override
	public Enseignant consulterEnseignant(String username) {
		Enseignant e = enseignantRepository.findOne(username);
		if (e==null) throw new RuntimeException("Enseignant introuvable");
		return e;
	}

	@Override
	public Probleme consulterProbleme(Long id) {
		Probleme p = problemeRepository.findOne(id);
		if (p==null) throw new RuntimeException("Probleme introuvable");
		return p;
	}

	@Override
	public Etudiant consulterEtudiant(String username) {
		Etudiant e = etudiantRepository.findOne(username);
		if(e==null) throw new RuntimeException("Etudiant introuvable");
		return e;
	}

	@Override
	public Cours consulterCours(Long id) {
		Cours c = coursRepository.findOne(id);
		if(c==null) throw new RuntimeException("Cours introuvable");
		return c;
	}

	@Override
	public Solution consulterSolution(Long id) {
		Solution s = solutionRepository.findOne(id);
		if(s==null) throw new RuntimeException("Cours introuvable");
		return s;
	}

	@Override
	public Page<Groupe> listGroupe(String username, int page, int size) {
		return groupeRepository.listGroupe(username, new PageRequest(page, size));
	}

	@Override
	public Page<Enseignant> listEnseignant(int page, int size) {
		return enseignantRepository.findAll(new PageRequest(page,size));
	}

	@Override
	public Page<Solution> listSolution(int page, int size) {
		return solutionRepository.findAll(new PageRequest(page, size));
	}

	@Override
	public Page<Cours> listCours(int page, int size) {
		return coursRepository.findAll(new PageRequest(page, size));
	}

	@Override
	public Page<Probleme> listProbleme(int page, int size) {
		return problemeRepository.findAll(new PageRequest(page,size));
	}

	@Override
	public Page<Etudiant> listEtudiant(int page, int size) {
		return etudiantRepository.findAll(new PageRequest(page, size));
	}

	@Override
	public Page<Solution> historyProbleme(Long id, int page, int size) {
		return solutionRepository.historyProbleme(id, new PageRequest(page, size));
	}

	@Override
	public Page<Solution> historyEtudiant(String username, int page, int size) {
		return solutionRepository.historyEtudiant(username, new PageRequest(page, size));
	}

	@Override
	public Page<Etudiant> listEtudiantParGroup(Long id, String username, int page, int size) {
		return etudiantRepository.listEtudiantParGroup(id, new PageRequest(page, size));
	}

	@Override
	public Page<Probleme> listProblemeAjouter(String username, int page, int size) {
		return problemeRepository.listProblemeAjouter(username, new PageRequest(page,size));
	}

	@Override
	public Page<Probleme> listProblemeQuasiDupliques(String titre,String ennonce,String type,char difficultie,String input,String output, int page, int size) {
		List<Probleme> listProbleme = problemeRepository.findAll();
		String refen = ennonce ;
		String refin = input;
		String refout = output;
		String refti = titre;
		Iterator<Probleme> it = listProbleme.iterator();
		List<Probleme> lisP = new ArrayList<>();
		while(it.hasNext()) {
			Probleme p=it.next();
			String desen = p.getEnnonce() ;
			String desin = p.getInput();
			String desout = p.getInput();
			String desti = p.getTitre();
            double distance;
            distance=(double)(StringUtils.getLevenshteinDistance(refen, desen))/Math.min(refen.length(),desen.length());
            if (distance < 0.6) {
            	lisP.add(p);
            }
		}
		Page<Probleme> PageProblemDep = new PageImpl<Probleme>(lisP);
		return PageProblemDep;
	}

	@Override
	public Page<Cours> listCoursAjoutes(String username, int page, int size) {
		return coursRepository.listCoursAjoutes(username, new PageRequest(page, size));
	}

	@Override
	public void ajouterEnseignant(String username, String password, String nom, String prenom,
			boolean privilege) {
		if(enseignantRepository.exists(username)||etudiantRepository.exists(username)) throw new RuntimeException("username déja utilisé");	
		else enseignantRepository.save(new Enseignant(username, password, nom,  prenom, new Date(),
					privilege));
	}

	@Override
	public void supprimerEnseignant(String username) {
		if(!enseignantRepository.exists(username)) throw new RuntimeException("username invalide");	
		else enseignantRepository.delete(consulterEnseignant(username));
	}

	@Override
	public void ajouterCours(String titre, String contenu, Enseignant enseignant) {
		coursRepository.save(new Cours(titre, contenu, new Date(), enseignant));
	}

	@Override
	public void ajouterProbleme(String titre, String ennonce, String type, char difficultie, String input,
			String output, Enseignant enseignant) {
		problemeRepository.save(new Probleme(titre, ennonce, new Date(),  type,  difficultie, input,
				 output, enseignant));
		
	}

	@Override
	public void ajouterEtudiant(String username, String password, String nom, String prenom, Groupe groupe) {
		if(enseignantRepository.exists(username)||etudiantRepository.exists(username)) throw new RuntimeException("username déja utilisé");	
		else etudiantRepository.save(new Etudiant(username, password, nom,  prenom, new Date(),
					groupe));
		
	}

	@Override
	public int nombreProblemeAccepted(String username) {
		int Res=0;
		Collection<Solution> C = consulterEtudiant(username).getSolutions();
		Iterator<Solution> i = C.iterator();
		while(i.hasNext()){
			if(i.next().getResult().equals("YES")) Res++;
		}
		return Res;
	}

	@Override
	public void ajouterGroupe(String nom, Enseignant enseignant) {
		if(groupeRepository.exists(nom)) throw new RuntimeException("Nom Groupe déja exsiste");
		else groupeRepository.save(new Groupe(nom,new Date(),enseignant));
		
	}

	@Override
	public int nombrePersonneAccepted(Long id) {
		int Res=0;
		Collection<Solution> C = consulterProbleme(id).getSolutions();
		Iterator<Solution> i = C.iterator();
		while(i.hasNext()){
			if(i.next().getResult().equals("YES")) Res++;
		}
		return Res;
	}

	@Override
	public void modfierProbleme(Long id, String titre, String ennonce, String type, char difficultie, String input,
			String output) {
		// TODO Auto-generated method stub
		Probleme p = consulterProbleme(id);
		p.setTitre(titre);
		p.setEnnonce(ennonce);
		p.setType(type);
		p.setDifficultie(difficultie);
		p.setInput(input);
		p.setOutput(output);
		problemeRepository.save(p);
	}

	@Override
	public void modifierProfilEnseignant(String username, String password, String nom, String prenom) {
		// TODO Auto-generated method stub
		Enseignant e = consulterEnseignant(username);
		e.setPassword(password);
		e.setNom(nom);
		e.setPrenom(prenom);
		enseignantRepository.save(e);
	}

	@Override
	public void modifierProfilEtudiant(String username, String password, String nom, String prenom) {
		// TODO Auto-generated method stub
		Etudiant e = consulterEtudiant(username);
		e.setPassword(password);
		e.setNom(nom);
		e.setPrenom(prenom);
		etudiantRepository.save(e);
	}

	@Override
	public void supprimerEtudiant(String username) {
		// TODO Auto-generated method stub
		if(!etudiantRepository.exists(username)) throw new RuntimeException("username invalide");	
		else etudiantRepository.delete(username);
	}

	@Override
	public void ajouterSolution(String code, Probleme probleme, Etudiant etudiant) {
		// TODO Auto-generated method stub
		Solution s = new Solution(code, probleme, etudiant, new Date(), "WAIT");
		solutionRepository.save(s);
	}

	@Override
	public void modifierSolution(Long id, String result) {
		// TODO Auto-generated method stub
		Solution s = consulterSolution(id);
		s.setResult(result);
		solutionRepository.save(s);
	}

	@Override
	public Page<Solution> listProblemeACorriger(String username, int page, int size) {
		return solutionRepository.listProblemeACorriger(username, new PageRequest(page, size));
	}

	@Override
	public boolean DetectiondePlagiat(String code, Long id,String username) {
		List<Solution> listSolutions = solutionRepository.findAll();
		String refen = code ;
		Iterator<Solution> it = listSolutions.iterator();
		List<Solution> lisP = new ArrayList<>();
		while(it.hasNext()) {
			Solution p=it.next();
			if(p.getProbleme().getId()==id&&(!p.getEtudiant().getUsername().equals(username))) {
				String desen = p.getCode() ;
	            double distance;
	            distance=(double)StringUtils.getLevenshteinDistance(refen, desen)/Math.min(refen.length(),desen.length());
	            if (distance < 0.4) {
	            	lisP.add(p);
	            	return true;
	            }
			}
		}
		return false;
	}
	
	@Override
	public int nombreProblemeAjoute(String username) {
		int Res=0;
		List<Probleme> C = problemeRepository.findAll();
		Iterator<Probleme> i = C.iterator();
		while(i.hasNext()){
			if(i.next().getEnseignant().getUsername()==username) Res++;
		}
		return Res;
	}

	@Override
	public int nombreProblemesAccepter(String username) {
		int Res=0;
		List<Solution> C = solutionRepository.findAll();
		Iterator<Solution> i = C.iterator();
		while(i.hasNext()){
			Solution e =i.next();
			if(e.getEtudiant().getUsername()==username && e.getResult().equals("YES")) Res++;
		}
		return Res;
	}

	@Override
	public int nombreProblemeSoumis(String username) {
		int Res=0;
		List<Solution> C = solutionRepository.findAll();
		Iterator<Solution> i = C.iterator();
		while(i.hasNext()){
			Solution e =i.next();
			if(e.getEtudiant().getUsername()==username) Res++;
		}
		return Res;
	}

	@Override
	public int nombreProblemeRefuse(String username) {
		int Res=0;
		List<Solution> C = solutionRepository.findAll();
		Iterator<Solution> i = C.iterator();
		while(i.hasNext()){
			Solution e =i.next();
			if(e.getEtudiant().getUsername()==username && e.getResult().equals("NO")) Res++;
		}
		return Res;
	}

	@Override
	public int nombreProblemeattends(String username) {
		int Res=0;
		List<Solution> C = solutionRepository.findAll();
		Iterator<Solution> i = C.iterator();
		while(i.hasNext()){
			Solution e =i.next();
			if(e.getEtudiant().getUsername()==username && e.getResult().equals("WAIT")) Res++;
		}
		return Res;
	}

	@Override
	public int nombreProblemesAcceptes() {
		int Res=0;
		List<Solution> C = solutionRepository.findAll();
		Iterator<Solution> i = C.iterator();
		while(i.hasNext()){
			Solution e =i.next();
			if( e.getResult().equals("YES")) Res++;
		}
		return Res;
	}

	@Override
	public int nombreProblemeSoumis() {
		int Res=0;
		List<Solution> C = solutionRepository.findAll();
		Iterator<Solution> i = C.iterator();
		while(i.hasNext()){
			Solution e =i.next();
			Res++;
		}
		return Res;
	}

	@Override
	public int nombreProblemeRefuse() {
		int Res=0;
		List<Solution> C = solutionRepository.findAll();
		Iterator<Solution> i = C.iterator();
		while(i.hasNext()){
			Solution e =i.next();
			if( e.getResult().equals("NO")) Res++;
		}
		return Res;
	}

	@Override
	public int nombreProblemeattends() {
		int Res=0;
		List<Solution> C = solutionRepository.findAll();
		Iterator<Solution> i = C.iterator();
		while(i.hasNext()){
			Solution e =i.next();
			if( e.getResult().equals("WAIT")) Res++;
		}
		return Res;
	}
	
}

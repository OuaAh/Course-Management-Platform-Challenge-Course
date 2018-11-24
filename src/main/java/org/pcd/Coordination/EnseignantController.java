package org.pcd.Coordination;

import java.util.Date;
import java.util.List;

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
import org.pcd.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EnseignantController {
	@Autowired
	private IService service;
	
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
	
	@RequestMapping("/enseignants")
	public String acceuil(Model model,String username) {
		Enseignant ens = enseignantRepository.findOne(username);
		model.addAttribute("enseignant",ens);
		return "enseignants";
	}
	
	
	@RequestMapping("/listeSoumissions")
	public String soumissions(Model model,
			@RequestParam(name="page",defaultValue="0")int p,String username) {
		Enseignant ens = enseignantRepository.findOne(username);
		model.addAttribute("enseignant",ens);
		Page<Solution> etds = solutionRepository.findAll(
				new PageRequest(p,10));
		int pageCount= etds.getTotalPages();
		int[]pages = new int[pageCount];
		for(int i=0;i<pageCount;i++) pages[i]=i;
		model.addAttribute("pages",pages);
		model.addAttribute("pagesolutions", etds);
		model.addAttribute("pageCourante",p);
		int problemesacceptes = service.nombreProblemesAcceptes();
		int problemessoumis = service.nombreProblemeSoumis();
		int problemeattends = service.nombreProblemeattends();
		int problemesrefuses = service.nombreProblemeRefuse();
		model.addAttribute("problemesacceptes",problemesacceptes);
		model.addAttribute("problemessoumis",problemessoumis);
		model.addAttribute("problemeattends",problemeattends);
		model.addAttribute("problemesrefuses",problemesrefuses);
		return "soumissionsenseignant";
	}
	
	@RequestMapping("/listeEtudiants")
	public String etudiants(Model model,
			@RequestParam(name="page",defaultValue="0")int p,String username,
			@RequestParam(name="motCle",defaultValue="")String mc) {
		Enseignant ens = enseignantRepository.findOne(username);
		model.addAttribute("enseignant",ens);
		Page<Etudiant> etds = etudiantRepository.chercherEtudiant("%"+mc+"%", new PageRequest(p, 8));
		int pageCount= etds.getTotalPages();
		int[]pages = new int[pageCount];
		for(int i=0;i<pageCount;i++) pages[i]=i;
		model.addAttribute("pages",pages);
		model.addAttribute("pagesolutions", etds);
		model.addAttribute("pageCourante",p);
		List<Groupe> grp = groupeRepository.findAll();
		model.addAttribute("groupes",grp);
		return "etudiantsenseignant";
	}
	
	@RequestMapping("/listeGroupes")
	public String groups(Model model,
			@RequestParam(name="page",defaultValue="0")int p,String username,
			@RequestParam(name="motCle",defaultValue="")String mc) {
		Enseignant ens = enseignantRepository.findOne(username);
		model.addAttribute("enseignant",ens);
		Page<Groupe> etds = groupeRepository.chercherGroupe("%"+mc+"%", new PageRequest(p, 8));
		int pageCount= etds.getTotalPages();
		int[]pages = new int[pageCount];
		for(int i=0;i<pageCount;i++) pages[i]=i;
		model.addAttribute("pages",pages);
		model.addAttribute("pagesolutions", etds);
		model.addAttribute("pageCourante",p);
		return "groupsenseignant";
	}
	
	@RequestMapping("/listeCours")
	public String cours(Model model,
			@RequestParam(name="page",defaultValue="0")int p,String username,
			@RequestParam(name="motCle",defaultValue="")String mc) {
		Enseignant ens = enseignantRepository.findOne(username);
		model.addAttribute("enseignant",ens);
		Page<Cours> etds = coursRepository.chercherCours("%"+mc+"%", 
				new PageRequest(p,10));
		int pageCount= etds.getTotalPages();
		int[]pages = new int[pageCount];
		for(int i=0;i<pageCount;i++) pages[i]=i;
		model.addAttribute("pages",pages);
		model.addAttribute("pagesolutions", etds);
		model.addAttribute("pageCourante",p);
		model.addAttribute("motCle",mc);
		return "coursenseignant";
	}
	
	@RequestMapping("/listeProblemes")
	public String problemes(Model model,
			@RequestParam(name="page",defaultValue="0")int p,String username,
			@RequestParam(name="motCle",defaultValue="")String mc) {
		Enseignant ens = enseignantRepository.findOne(username);
		model.addAttribute("enseignant",ens);
		Page<Probleme> etds = problemeRepository.chercherEtudiant("%"+mc+"%", 
				new PageRequest(p,10));
		int pageCount= etds.getTotalPages();
		int[]pages = new int[pageCount];
		for(int i=0;i<pageCount;i++) pages[i]=i;
		model.addAttribute("pages",pages);
		model.addAttribute("pagesolutions", etds);
		model.addAttribute("pageCourante",p);
		model.addAttribute("motCle",mc);
		return "problemesenseignant";
	}
	
	@RequestMapping("/listeenseignants")
	public String enseignant(Model model,
			@RequestParam(name="page",defaultValue="0")int p,String username) {
		Enseignant ens = enseignantRepository.findOne(username);
		model.addAttribute("enseignant",ens);
		Page<Enseignant> etds = enseignantRepository.findAll(
				new PageRequest(p,10));
		int pageCount= etds.getTotalPages();
		int[]pages = new int[pageCount];
		for(int i=0;i<pageCount;i++) pages[i]=i;
		model.addAttribute("pages",pages);
		model.addAttribute("pagesolutions", etds);
		model.addAttribute("pageCourante",p);
		return "enseignantlis";
	}
	
	@RequestMapping("/profil")
	public String profil(Model model,
			String username, String alertmodifier) {
		Enseignant ens = enseignantRepository.findOne(username);
		model.addAttribute("enseignant",ens);
		return "profil";
	}
	
	@RequestMapping("/UpdateEnseigant")
	public String update(Model model,
			String username, String password, String nom, String prenom, String email) {
		Enseignant ens = enseignantRepository.findOne(username);
		ens.setEmail(email);ens.setPassword(password);ens.setNom(nom);ens.setPrenom(prenom);
		enseignantRepository.save(ens);
		model.addAttribute("alertmodifier", "YES");
		model.addAttribute("enseignant",ens);
		return "profil";
	}
	
	@RequestMapping("/saveprobleme")
	public String SaveProbleme(Model model,String username, String titre, char difficultie,String ennonce, String type, String input,String output
			,@RequestParam(name="page",defaultValue="0")int p) {
		Enseignant ens = enseignantRepository.findOne(username);
		model.addAttribute("enseignant",ens);
		
		Page<Probleme> etds = service.listProblemeQuasiDupliques(titre, ennonce, type, difficultie, input, output, p, 10);
		int nbr=etds.getNumberOfElements();
		int pageCount= etds.getTotalPages();
		int[]pages = new int[pageCount];
		for(int i=0;i<pageCount;i++) pages[i]=i;
		model.addAttribute("pages",pages);
		model.addAttribute("pagesolutions", etds);
		model.addAttribute("pageCourante",p);
		if(nbr!=0)
		model.addAttribute("nbr",nbr);
		service.ajouterProbleme(titre, ennonce, type, difficultie, input, output, ens);
		return "problemequasiduplique";
	}
	
	@RequestMapping("/supprimerproblemeacc")
	public String SupprimerProblemeajouter(Model model, String username) {
		Enseignant ens = enseignantRepository.findOne(username);
		model.addAttribute("enseignant",ens);
		Probleme p = problemeRepository.acc();
		problemeRepository.delete(p.getId());
		return "redirect:listeProblemes?username="+username;
	}
	@RequestMapping("/modifierproblemepage")
	public String modifierproblemepage(Model model,Long id, String username) {
		Enseignant ens = enseignantRepository.findOne(username);
		model.addAttribute("enseignant",ens);
		Probleme pro = problemeRepository.findOne(id);
		model.addAttribute("probleme",pro);
		return "problemeedit";
	}
	
	@RequestMapping("/modifierprobleme")
	public String ModifierProblemel(Model model,Long id, String username,
			String titre, char difficultie,String ennonce, String type, String input,String output) {
		Enseignant ens = enseignantRepository.findOne(username);
		model.addAttribute("enseignant",ens);
		Probleme pro = problemeRepository.findOne(id);
		model.addAttribute("probleme",pro);
		pro.setDifficultie(difficultie);
		pro.setTitre(titre);pro.setType(type);pro.setEnnonce(ennonce);pro.setInput(input);pro.setOutput(output);
		problemeRepository.save(pro);
		model.addAttribute("modifier","YES");
		return "problemeedit";
	}

	@RequestMapping("/savecours")
	public String Savecours(Model model,String username, String titre, String enonce) {
		Enseignant ens = enseignantRepository.findOne(username);
		model.addAttribute("enseignant",ens);
		service.ajouterCours(titre, enonce, ens);
		return "redirect:listeCours?username="+username;
	}
	
	@RequestMapping("/supprimercours")
	public String Supprimercours(Model model,Long id, String username) {
		Enseignant ens = enseignantRepository.findOne(username);
		model.addAttribute("enseignant",ens);
		coursRepository.delete(id);
		return "redirect:listeCours?username="+username;
	}
	
	@RequestMapping("/modifiercourspage")
	public String modifiercourspage(Model model,Long id, String username) {
		Enseignant ens = enseignantRepository.findOne(username);
		model.addAttribute("enseignant",ens);
		Cours cours = coursRepository.findOne(id);
		model.addAttribute("cours",cours);
		return "coursedit";
	}
	
	@RequestMapping("/modifiercours")
	public String ModifierCoursl(Model model,
			String titre, String contenu, String username,Long id) {
		Enseignant ens = enseignantRepository.findOne(username);
		model.addAttribute("enseignant",ens);
		Cours cours = coursRepository.findOne(id);
		cours.setContenu(contenu);
		cours.setTitre(titre);
		coursRepository.save(cours);
		model.addAttribute("cours",cours);
		model.addAttribute("modifier", "YES");
		return "coursedit";
	}
	
	@RequestMapping("/saveetudiant")
	public String Saveetudiant(Model model,String username, String username1,String password, String nom,String prenom,String email,String groupe) {
		Enseignant ens = enseignantRepository.findOne(username);
		model.addAttribute("enseignant",ens);
		Groupe gro = groupeRepository.findOne(groupe);
		try {
			Etudiant e =new Etudiant(username1, password, nom, prenom, email, new Date(), gro);
			if(etudiantRepository.findOne(username1)==null) {
			etudiantRepository.save(e);}
			else {return "redirect:listeEtudiants?username="+username+"&error=ll";}
		}
		catch(Exception exp) {
			return "redirect:listeEtudiants?username="+username+"&error="+exp.getMessage();
		}
		return "redirect:listeEtudiants?username="+username;
	}
	
	@RequestMapping("/supprimeretudiant")
	public String supprimeretudiant(Model model,String id, String username) {
		Enseignant ens = enseignantRepository.findOne(username);
		model.addAttribute("enseignant",ens);
		etudiantRepository.delete(id);
		return "redirect:listeEtudiants?username="+username;
	}
	
	@RequestMapping("/consulterhistorique")
	public String historique(Model model,
			@RequestParam(name="page",defaultValue="0")int p,String username,String id) {
		Enseignant ens = enseignantRepository.findOne(username);
		model.addAttribute("enseignant",ens);
		Etudiant etu = etudiantRepository.findOne(id);
		model.addAttribute("etudiant",etu);
		Page<Solution> etds = service.historyEtudiant(id, p, 10);
		int pageCount= etds.getTotalPages();
		int[]pages = new int[pageCount];
		for(int i=0;i<pageCount;i++) pages[i]=i;
		model.addAttribute("pages",pages);
		model.addAttribute("pagesolutions", etds);
		model.addAttribute("pageCourante",p);
		int problemesacceptes = service.nombreProblemesAccepter(id);
		int problemessoumis = service.nombreProblemeSoumis(id);
		int problemeattends = service.nombreProblemeattends(id);
		int problemesrefuses = service.nombreProblemeRefuse(id);
		model.addAttribute("problemesacceptes",problemesacceptes);
		model.addAttribute("problemessoumis",problemessoumis);
		model.addAttribute("problemeattends",problemeattends);
		model.addAttribute("problemesrefuses",problemesrefuses);
		return "historiqueetuenseignant";
	}
	
	@RequestMapping("/editSoumission")
	public String editSoumission(Model model,Long id,String username) {
		Enseignant ens = enseignantRepository.findOne(username);
		model.addAttribute("enseignant",ens);
		Solution solution = solutionRepository.findOne(id);
		model.addAttribute("solution",solution);
		return "editSoumission";
	}
	
	@RequestMapping("/editersoumission")
	public String editerSoumission(Model model,Long id,String username,String result) {
		Enseignant ens = enseignantRepository.findOne(username);
		model.addAttribute("enseignant",ens);
		Solution solution = solutionRepository.findOne(id);
		solution.setResult(result);
		solutionRepository.save(solution);
		model.addAttribute("solution",solution);
		String res="Changement effectué";
		model.addAttribute("alert",res);
		return "editSoumission";
	}
	
	@RequestMapping("/saveenseignant")
	public String Saveenseignant(Model model,String username, String username1,String password, String nom,String prenom,String email) {
		Enseignant ens = enseignantRepository.findOne(username);
		model.addAttribute("enseignant",ens);
		try {
			Enseignant e = new Enseignant(username1, password, nom, prenom,email, new Date(),false);
			if(enseignantRepository.findOne(username1)==null) {
			enseignantRepository.save(e);}
			else {
				return "redirect:listeenseignants?username="+username+"&error=ee";
			}
		//service.ajouterEnseignant(username1, password, nom, prenom, false);
		}
		catch(Exception exp) {
			return "redirect:listeenseignants?username="+username+"&error="+exp.getMessage();
		}
		return "redirect:listeenseignants?username="+username;
	}
	
	@RequestMapping("/supprimerenseignant")
	public String supprimerenseignant(Model model,String id, String username) {
		Enseignant ens = enseignantRepository.findOne(username);
		model.addAttribute("enseignant",ens);
		enseignantRepository.delete(id);
		return "redirect:listeenseignants?username="+username;
	}
	
	@RequestMapping("/savegroupe")
	public String Savegroupe(Model model,String username, String nom) {
		Enseignant ens = enseignantRepository.findOne(username);
		model.addAttribute("enseignant",ens);
		try {
		service.ajouterGroupe(nom, ens);
		}
		catch(Exception exp) {
			return "redirect:listeGroupes?username="+username+"&error1="+exp.getMessage();
		}
		return "redirect:listeGroupes?username="+username;
	}
	
	@RequestMapping("/consultergroupe")
	public String groupe(Model model,
			@RequestParam(name="page",defaultValue="0")int p,String username,String id) {
		Enseignant ens = enseignantRepository.findOne(username);
		model.addAttribute("enseignant",ens);
		Page<Etudiant> etds = etudiantRepository.chercherEtudiantgroupe(id, new PageRequest(p, 8));
		int pageCount= etds.getTotalPages();
		int[]pages = new int[pageCount];
		for(int i=0;i<pageCount;i++) pages[i]=i;
		model.addAttribute("pages",pages);
		model.addAttribute("pagesolutions", etds);
		model.addAttribute("pageCourante",p);
		return "etudiantgroupe";
	}
}

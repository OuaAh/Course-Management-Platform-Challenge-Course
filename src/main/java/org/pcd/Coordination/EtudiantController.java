package org.pcd.Coordination;

import java.util.Date;

import org.pcd.dao.CoursRepository;
import org.pcd.dao.EnseignantRepository;
import org.pcd.dao.EtudiantRepository;
import org.pcd.dao.GroupeRepository;
import org.pcd.dao.ProblemeRepository;
import org.pcd.dao.SolutionRepository;
import org.pcd.entities.Cours;
import org.pcd.entities.Enseignant;
import org.pcd.entities.Etudiant;
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
public class EtudiantController {
	
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
	
	@RequestMapping("/etudiants")
	public String acceuil(Model model,String username) {
		Etudiant etu = etudiantRepository.findOne(username);
		model.addAttribute("etudiant",etu);
		return "etudiants";
	}
	
	@RequestMapping("/profiletu")
	public String profil(Model model,String username) {
		Etudiant etu = etudiantRepository.findOne(username);
		model.addAttribute("etudiant",etu);
		return "profiletu";
	}
	
	@RequestMapping("/UpdateEtudiant")
	public String update(Model model,
			String username, String password, String nom, String prenom, String email) {
		Etudiant ens = etudiantRepository.findOne(username);
		ens.setEmail(email);ens.setPassword(password);ens.setNom(nom);ens.setPrenom(prenom);
		etudiantRepository.save(ens);
		model.addAttribute("alertmodifier", "YES");
		model.addAttribute("etudiant",ens);
		return "profiletu";
	}
	
	@RequestMapping("/listeProblemesetu")
	public String problemes(Model model,
			@RequestParam(name="page",defaultValue="0")int p,String username,
			@RequestParam(name="motCle",defaultValue="")String mc) {
		Etudiant ens = etudiantRepository.findOne(username);
		model.addAttribute("etudiant",ens);
		Page<Probleme> etds = problemeRepository.chercherEtudiant("%"+mc+"%", 
				new PageRequest(p,10));
		int pageCount= etds.getTotalPages();
		int[]pages = new int[pageCount];
		for(int i=0;i<pageCount;i++) pages[i]=i;
		model.addAttribute("pages",pages);
		model.addAttribute("pagesolutions", etds);
		model.addAttribute("pageCourante",p);
		model.addAttribute("motCle",mc);
		return "problemesetudiant";
	}
	
	@RequestMapping("/consulterProblemeetu")
	public String problemeenonce(Model model, Long id, String username) {
		Etudiant ens = etudiantRepository.findOne(username);
		model.addAttribute("etudiant",ens);
		Probleme prob = problemeRepository.findOne(id);
		model.addAttribute("probleme",prob);
		int stat = service.nombrePersonneAccepted(id);
		model.addAttribute("stat",stat);
		return "problemeetud";
	}
	@RequestMapping("/SaveSubmit")
	public String Submit(Model model, String code, String username, long id){
		Etudiant ens = etudiantRepository.findOne(username);
		model.addAttribute("etudiant",ens);
		Probleme prob = problemeRepository.findOne(id);
		model.addAttribute("probleme",prob);
		boolean b = service.DetectiondePlagiat(code,prob.getId(),username);
		Solution s = new Solution(code, prob, ens, new Date(), "WAIT", b);
		solutionRepository.save(s);
		return "redirect:consulterProblemeetu?id="+id+"&username="+username;
	}
	
	@RequestMapping("/listeCoursetu")
	public String cours(Model model,
			@RequestParam(name="page",defaultValue="0")int p,String username,
			@RequestParam(name="motCle",defaultValue="")String mc) {
		Etudiant ens = etudiantRepository.findOne(username);
		model.addAttribute("etudiant",ens);
		Page<Cours> etds = coursRepository.chercherCours("%"+mc+"%",
				new PageRequest(p,10));
		int pageCount= etds.getTotalPages();
		int[]pages = new int[pageCount];
		for(int i=0;i<pageCount;i++) pages[i]=i;
		model.addAttribute("pages",pages);
		model.addAttribute("pagesolutions", etds);
		model.addAttribute("pageCourante",p);
		model.addAttribute("motCle",mc);
		return "coursetudiant";
	}
	
	@RequestMapping("/consulterCoursetu")
	public String cours(Model model,Long id,String username) {
		Etudiant ens = etudiantRepository.findOne(username);
		model.addAttribute("etudiant",ens);
		Cours cours = coursRepository.findOne(id);
		model.addAttribute("cours",cours);
		return "coursetu";
	}
	@RequestMapping("/historique")
	public String historique(Model model,
			@RequestParam(name="page",defaultValue="0")int p,String username) {
		Etudiant ens = etudiantRepository.findOne(username);
		model.addAttribute("etudiant",ens);
		Page<Solution> etds = service.historyEtudiant(username, p, 10);
		int pageCount= etds.getTotalPages();
		int[]pages = new int[pageCount];
		for(int i=0;i<pageCount;i++) pages[i]=i;
		model.addAttribute("pages",pages);
		model.addAttribute("pagesolutions", etds);
		model.addAttribute("pageCourante",p);
		int problemesacceptes = service.nombreProblemesAccepter(username);
		int problemessoumis = service.nombreProblemeSoumis(username);
		int problemeattends = service.nombreProblemeattends(username);
		int problemesrefuses = service.nombreProblemeRefuse(username);
		model.addAttribute("problemesacceptes",problemesacceptes);
		model.addAttribute("problemessoumis",problemessoumis);
		model.addAttribute("problemeattends",problemeattends);
		model.addAttribute("problemesrefuses",problemesrefuses);
		return "historiqueetudiant";
	}
	@RequestMapping("/consulterHistoriqueProbEtu")
	public String historiquePro(Model model,
			@RequestParam(name="page",defaultValue="0")int p,String username,long id) {
		Etudiant ens = etudiantRepository.findOne(username);
		model.addAttribute("etudiant",ens);
		Probleme pro = problemeRepository.findOne(id);
		model.addAttribute("probleme",pro);
		Page<Solution> etds = service.historyProbleme(id, p, 10);
		int pageCount= etds.getTotalPages();
		int[]pages = new int[pageCount];
		for(int i=0;i<pageCount;i++) pages[i]=i;
		model.addAttribute("pages",pages);
		model.addAttribute("pagesolutions", etds);
		model.addAttribute("pageCourante",p);
		return "historiqueProblemeetu";
	}
	
	@RequestMapping("/consulterSoumission")
	public String consultersoumission(Model model,Long id,String username) {
		Etudiant ens = etudiantRepository.findOne(username);
		model.addAttribute("etudiant",ens);
		Solution s=solutionRepository.findOne(id);
		model.addAttribute("solution",s);
		return "consluterSoumission";
	}
}

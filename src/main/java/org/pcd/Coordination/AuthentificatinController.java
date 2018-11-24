package org.pcd.Coordination;


import java.util.List;

import org.pcd.dao.EnseignantRepository;
import org.pcd.dao.EtudiantRepository;
import org.pcd.dao.ProblemeRepository;
import org.pcd.dao.SolutionRepository;
import org.pcd.entities.Enseignant;
import org.pcd.entities.Etudiant;
import org.pcd.entities.Solution;
import org.pcd.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthentificatinController {
	
	@Autowired
	private IService service;
	
	@Autowired
	private EnseignantRepository enseignantRepository;
	
	@Autowired
	private EtudiantRepository etudiantRepository;
	
	@Autowired
	private ProblemeRepository problemeRepository;
	
	@Autowired
	private SolutionRepository solutionRepository;
	
	@RequestMapping("/")
	public String index() {
		return "authentification";
	}

	@RequestMapping("/consulterCompte")
	public String consulter(Model model,String username,String password) {
		Exception ex = new Exception();
		try {
		Enseignant ens = enseignantRepository.findOne(username);
		if (ens==null) {
			Etudiant et = etudiantRepository.findOne(username);
			if (et.getPassword().equals(password)) {
				model.addAttribute("etudiant", et);
				return "etudiants";
			}
			model.addAttribute("erreur",ex);
			return "authentification";
		}
		else if (ens.getPassword().equals(password)) {
			List<Solution> etds = solutionRepository.findAll();
			model.addAttribute("solutions", etds);
			model.addAttribute("enseignant", ens);
			return "enseignants";
		}
		}
		catch(Exception exp) {
			model.addAttribute("erreur",exp);
			return "authentification";
		}
		model.addAttribute("erreur",ex);
		return "authentification";
		
	}
}

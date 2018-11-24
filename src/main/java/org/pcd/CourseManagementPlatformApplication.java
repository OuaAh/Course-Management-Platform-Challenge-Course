package org.pcd;

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
import org.pcd.entities.Groupe;
import org.pcd.entities.Probleme;
import org.pcd.entities.Solution;
import org.pcd.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CourseManagementPlatformApplication implements CommandLineRunner {
	
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
	@Autowired
	private CoursRepository coursRepository;
	@Autowired
	private IService iservice;

	public static void main(String[] args) {
		SpringApplication.run(CourseManagementPlatformApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		
		
		Enseignant ens1 = enseignantRepository.save(new Enseignant("0XENS01","0XENS01","0XENS01","0XENS01","0XENS01@ensi-uma.tn",new Date(),true));
		Enseignant ens2 = enseignantRepository.save(new Enseignant("0XENS02","0XENS02","0XENS02","0XENS02","0XENS02@ensi-uma.tn",new Date(),false));
		Groupe grpA = groupeRepository.save(new Groupe("II1A",new Date(),ens1));
		Groupe grpB = groupeRepository.save(new Groupe("II1B",new Date(),ens2));
		Groupe grpC = groupeRepository.save(new Groupe("II1C",new Date(),ens2));
		Etudiant etu1 = etudiantRepository.save(new Etudiant("0XETU01","0XETU01","0XETU01","0XETU01","0XETU01@ensi-uma.tn",new Date(),grpA));
		for(int i=3;i<10;i++) {
			etudiantRepository.save(new Etudiant("0XETU0"+i,"0XETU0"+i,"0XETU0"+i,"0XETU0"+i,"0XETU0"+i+"@ensi-uma.tn",new Date(),grpA));
		}
		Etudiant etu2 = etudiantRepository.save(new Etudiant("0XETU02","0XETU02","0XETU02","0XETU02","0XETU02@ensi-uma.tn",new Date(),grpA));
		Etudiant etu3 = etudiantRepository.save(new Etudiant("0XETU03","0XETU03","0XETU03","0XETU03","0XETU03@ensi-uma.tn",new Date(),grpB));
		for(int i=10;i<17;i++) {
			etudiantRepository.save(new Etudiant("0XETU"+i,"0XETU"+i,"0XETU"+i,"0XETU"+i,"0XETU"+i+"@ensi-uma.tn",new Date(),grpB));
		}
		for(int i=17;i<25;i++) {
			etudiantRepository.save(new Etudiant("0XETU"+i,"0XETU"+i,"0XETU"+i,"0XETU"+i,"0XETU"+i+"@ensi-uma.tn",new Date(),grpB));
		}
		Probleme pro1 = problemeRepository.save(new Probleme("Theatre Square",
				"Theatre Square in the capital city of Berland has a rectangular shape with the size n × m meters. On the occasion of the city's anniversary, a decision was taken to pave the Square with square granite flagstones. Each flagstone is of the size a × a.\n" + 
				"\n" + 
				"What is the least number of flagstones needed to pave the Square? It's allowed to cover the surface larger than the Theatre Square, but the Square has to be covered. It's not allowed to break the flagstones. The sides of flagstones should be parallel to the sides of the Square.",
				new Date(),"MATH",'A',"The input contains three positive integer numbers in the first line: n,  m and a (1 ≤  n, m, a ≤ 109).",
				"Write the needed number of flagstones.",ens1));
		iservice.ajouterCours("THEORIE DE GRAPHE", "La théorie des graphes est la discipline mathématique et informatique qui étudie les graphes, lesquels sont des modèles abstraits de dessins de réseaux reliant des objets1. Ces modèles sont constitués par la donnée de « points », appelés nœuds ou sommets (en référence aux polyèdres), et de « liens » entre ces points ; ces liens sont souvent symétriques (les graphes sont alors dits non orientés) et sont appelés des arêtes.\n" + 
				"\n" + 
				"Les algorithmes élaborés pour résoudre des problèmes concernant les objets de cette théorie ont de nombreuses applications dans tous les domaines liés à la notion de réseau (réseau social, réseau informatique, télécommunications, etc.) et dans bien d'autres domaines (par exemple génétique) tant le concept de graphe, à peu près équivalent à celui de relation binaire (à ne pas confondre donc avec graphe d'une fonction), est général. De grands théorèmes difficiles, comme le théorème des quatre couleurs, le théorème des graphes parfaits, ou encore le théorème de Robertson-Seymour, ont contribué à asseoir cette matière auprès des mathématiciens, et les questions qu'elle laisse ouvertes, comme la conjecture de Hadwiger, en font une branche vivace des mathématiques discrètes."
				, ens1);
		/*
		Probleme pro2 = problemeRepository.save(new Probleme("Cactus to Tree",
				"You are given a special connected undirected graph where each vertex belongs to at most one simple cycle.\n" + 
				"\n" + 
				"Your task is to remove as many edges as needed to convert this graph into a tree (connected graph with no cycles).\n" + 
				"\n" + 
				"For each node, independently, output the maximum distance between it and a leaf in the resulting tree, assuming you were to remove the edges in a way that minimizes this distance.",
				new Date(),"GRAPH",'F'," ",
				"Print n" + 
				" space-separated integers, the " + 
				"i" + 
				"-th integer represents the maximum distance between node " + 
				"i" + 
				" and a leaf if the removed edges were chosen in a way that minimizes this distance.",ens1));
		
		Probleme pro3 = problemeRepository.save(new Probleme("Theatre Square",
				"Theatre Square in the capital city of Berland has a rectangular shape with the size n × m meters. On the occasion of the city's anniversary, a decision was taken to pave the Square with square granite flagstones. Each flagstone is of the size a × a.\n" + 
				"\n" + 
				"What is the least number of flagstones needed to pave the Square? It's allowed to cover the surface larger than the Theatre Square, but the Square has to be covered. It's not allowed to break the flagstones. The sides of flagstones should be parallel to the sides of the Square.",
				new Date(),"MATH",'A',"The input contains three positive integer numbers in the first line: n,  m and a (1 ≤  n, m, a ≤ 109).",
				"Write the needed number of flagstones.",ens1));
		
		
		Solution sol1 = solutionRepository.save(new Solution(" #include <bits/stdc++.h> ",pro1,etu1,new Date(),"YES"));
		for(int i=1;i<100;i++)
			problemeRepository.save(new Probleme("Theatre Square",
					"Theatre Square in the capital city of Berland has a rectangular shape with the size n × m meters. On the occasion of the city's anniversary, a decision was taken to pave the Square with square granite flagstones. Each flagstone is of the size a × a.\n" + 
					"\n" + 
					"What is the least number of flagstones needed to pave the Square? It's allowed to cover the surface larger than the Theatre Square, but the Square has to be covered. It's not allowed to break the flagstones. The sides of flagstones should be parallel to the sides of the Square.",
					new Date(),"MATH",'A',"The input contains three positive integer numbers in the first line: n,  m and a (1 ≤  n, m, a ≤ 109).",
					"Write the needed number of flagstones.",ens1));
			
		
		for(int i=1;i<100;i++)
			iservice.ajouterSolution(" #include ",pro1, etu3);
	*/
	}
}

package org.pcd.dao;

import org.pcd.entities.Probleme;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProblemeRepository 
extends JpaRepository<Probleme, Long>{
	
	@Query("select o from Probleme o where o.enseignant.username=:x order by o.date desc")
	public Page<Probleme> listProblemeAjouter(@Param("x") String username, Pageable pageable);

	@Query("select e from Probleme e where e.titre like :x or e.type like :x order by e.difficultie")
	public Page<Probleme> chercherEtudiant(@Param("x")String mc, Pageable pageable);
	
	@Query("select p from Probleme p where p.id = (select max(id) from Probleme)")
	public Probleme acc();
}

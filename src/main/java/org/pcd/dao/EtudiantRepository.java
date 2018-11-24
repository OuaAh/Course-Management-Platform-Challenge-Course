package org.pcd.dao;

import org.pcd.entities.Etudiant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EtudiantRepository 
extends JpaRepository<Etudiant, String>{
	
	@Query("select o from Etudiant o where o.groupe.nom=:x order by o.date desc")
	public Page<Etudiant> listEtudiantParGroup(@Param("x")Long id, Pageable pageable);
	
	@Query("select e from Etudiant e where e.username like :x or e.nom like :x or e.prenom like :x")
	public Page<Etudiant> chercherEtudiant(@Param("x")String mc, Pageable pageable);
	
	@Query("select e from Etudiant e where e.groupe.nom = :x")
	public Page<Etudiant> chercherEtudiantgroupe(@Param("x")String mc, Pageable pageable);
}

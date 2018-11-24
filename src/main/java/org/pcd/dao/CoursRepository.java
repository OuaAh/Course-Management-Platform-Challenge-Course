package org.pcd.dao;

import org.pcd.entities.Cours;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CoursRepository
extends JpaRepository<Cours, Long>{
	@Query("select o from Cours o where o.enseignant.username=:x order by o.date desc")
	public Page<Cours> listCoursAjoutes(@Param("x")String username, Pageable pageable);
	
	@Query("select e from Cours e where e.titre like :x or e.enseignant.username=:x")
	public Page<Cours> chercherCours(@Param("x")String mc, Pageable pageable);
}

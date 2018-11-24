package org.pcd.dao;

import org.pcd.entities.Solution;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SolutionRepository 
extends JpaRepository<Solution, Long>{
	
	@Query("select o from Solution o where o.probleme.id=:x order by o.date desc")
	public Page<Solution> historyProbleme(@Param("x") Long id, Pageable pageable);
	
	@Query("select o from Solution o where o.etudiant.username=:x order by o.date desc")
	public Page<Solution> historyEtudiant(@Param("x")String username, Pageable pageable); 
	
	@Query("select o from Solution o where o.probleme.enseignant.username=:x and o.result='WAIT' order by o.date desc")
	public Page<Solution> listProblemeACorriger(@Param("x") String username, Pageable pageable);
	
}

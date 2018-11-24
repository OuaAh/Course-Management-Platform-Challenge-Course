package org.pcd.dao;


import org.pcd.entities.Groupe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface GroupeRepository 
extends JpaRepository<Groupe, String>{
	@Query("select o from Groupe o where o.enseignant.username=:x order by o.date desc")
	public Page<Groupe> listGroupe(@Param("x")String username, Pageable pageable);
	
	@Query("select o from Groupe o where o.nom like :x order by o.nom")
	public Page<Groupe> chercherGroupe(@Param("x")String username, Pageable pageable);
}

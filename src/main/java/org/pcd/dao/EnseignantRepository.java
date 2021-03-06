package org.pcd.dao;

import org.pcd.entities.Enseignant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EnseignantRepository
extends JpaRepository<Enseignant, String>{
	
}

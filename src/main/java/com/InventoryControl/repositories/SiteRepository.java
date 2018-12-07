package com.InventoryControl.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.InventoryControl.domain.Sites;

@Repository
public interface SiteRepository extends JpaRepository<Sites, Integer>{

}

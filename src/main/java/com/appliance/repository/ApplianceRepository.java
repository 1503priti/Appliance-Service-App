package com.appliance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.appliance.entity.Appliance;

@Repository
public interface ApplianceRepository extends JpaRepository<Appliance, Integer> {
	
	

}
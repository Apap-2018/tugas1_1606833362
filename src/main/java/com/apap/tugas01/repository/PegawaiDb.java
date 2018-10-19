package com.apap.tugas01.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apap.tugas01.model.PegawaiModel;

@Repository
public interface PegawaiDb extends JpaRepository<PegawaiModel, Long> {
	PegawaiModel findByNip(String nip);
}
	

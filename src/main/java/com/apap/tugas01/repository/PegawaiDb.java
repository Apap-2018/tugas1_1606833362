package com.apap.tugas01.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import com.apap.tugas01.model.InstansiModel;
import com.apap.tugas01.model.JabatanModel;
import com.apap.tugas01.model.PegawaiModel;

@Repository
public interface PegawaiDb extends JpaRepository<PegawaiModel, Long> {
	PegawaiModel findByNip(String nip);
	List<PegawaiModel> findByInstansi(InstansiModel instansi);
}
	

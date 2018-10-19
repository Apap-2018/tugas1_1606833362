package com.apap.tugas01.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apap.tugas01.model.InstansiModel;
import com.apap.tugas01.model.ProvinsiModel;

@Repository
public interface InstansiDb extends JpaRepository<InstansiModel,Long> {
	InstansiModel findById(long id);
	List<InstansiModel> findByProvinsi(ProvinsiModel provinsi);
	List<InstansiModel> findByNama(String nama);
}


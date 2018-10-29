package com.apap.tugas01.repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apap.tugas01.model.InstansiModel;
import com.apap.tugas01.model.ProvinsiModel;

@Repository
public interface InstansiDb extends JpaRepository<InstansiModel, BigInteger> {
	Optional<InstansiModel> findById(BigInteger id);
	List<InstansiModel> findByProvinsi(ProvinsiModel provinsi);
	List<InstansiModel> findByNama(String nama);
}


package com.apap.tugas01.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import com.apap.tugas01.model.InstansiModel;
import com.apap.tugas01.model.ProvinsiModel;

public interface InstansiService {
	Optional<InstansiModel> findInstansiById(BigInteger id);
	List<InstansiModel> viewAll();
	List<InstansiModel> getInstansiByProvinsi(ProvinsiModel provinsi);
	List<InstansiModel> getByNama(String nama);

}
package com.apap.tugas01.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import com.apap.tugas01.model.ProvinsiModel;

public interface ProvinsiService {
	Optional<ProvinsiModel> findProvinsiById(BigInteger id);
	List<ProvinsiModel> viewAllProvinsi();
}


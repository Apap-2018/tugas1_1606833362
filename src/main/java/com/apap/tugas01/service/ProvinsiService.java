package com.apap.tugas01.service;

import java.util.List;
import java.util.Optional;

import com.apap.tugas01.model.ProvinsiModel;

public interface ProvinsiService {
	Optional<ProvinsiModel> findProvinsiDetailById(long id);
	List<ProvinsiModel> viewAllProvinsi();
}


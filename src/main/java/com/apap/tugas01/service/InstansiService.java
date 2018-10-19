package com.apap.tugas01.service;

import java.util.List;

import com.apap.tugas01.model.InstansiModel;
import com.apap.tugas01.model.ProvinsiModel;

public interface InstansiService {
	InstansiModel findInstansiById(Long id);
	List<InstansiModel> viewAll();
	List<InstansiModel> getInstansiByProvinsi(ProvinsiModel provinsi);

}
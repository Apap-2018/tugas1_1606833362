package com.apap.tugas01.service;

import java.util.List;
import java.util.Optional;

import com.apap.tugas01.model.InstansiModel;
public interface InstansiService {
	InstansiModel findInstansiById(Long id);
	List<InstansiModel> viewAll();	
	
}

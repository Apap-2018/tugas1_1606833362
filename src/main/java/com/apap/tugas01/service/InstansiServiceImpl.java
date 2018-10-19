package com.apap.tugas01.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apap.tugas01.model.InstansiModel;
import com.apap.tugas01.model.ProvinsiModel;
import com.apap.tugas01.repository.InstansiDb;
@Service
@Transactional
public class InstansiServiceImpl implements InstansiService{
	@Autowired
	private InstansiDb instansiDb;

	@Override
	public InstansiModel findInstansiById(Long id) {
		return instansiDb.findById(id).get();
	}

	@Override
	public List<InstansiModel> viewAll() {
		return instansiDb.findAll();
	}
	
	@Override
	public List<InstansiModel> getInstansiByProvinsi(ProvinsiModel provinsi) {
		return instansiDb.findByProvinsi(provinsi);
	}
}
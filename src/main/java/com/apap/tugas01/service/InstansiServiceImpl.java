package com.apap.tugas01.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

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
	public List<InstansiModel> viewAll() {
		return instansiDb.findAll();
	}
	
	@Override
	public List<InstansiModel> getInstansiByProvinsi(ProvinsiModel provinsi) {
		return instansiDb.findByProvinsi(provinsi);
	}

	@Override
	public Optional<InstansiModel> findInstansiById(BigInteger id) {
		return instansiDb.findById(id);
	}

	@Override
	public List<InstansiModel> getByNama(String nama) {
		return instansiDb.findByNama(nama);
	}
}
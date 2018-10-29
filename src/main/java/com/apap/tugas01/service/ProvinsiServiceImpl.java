package com.apap.tugas01.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apap.tugas01.model.ProvinsiModel;
import com.apap.tugas01.repository.ProvinsiDb;

@Service
@Transactional
public class ProvinsiServiceImpl implements ProvinsiService {
	@Autowired
	private ProvinsiDb provinsiDb;

	@Override
	public Optional<ProvinsiModel> findProvinsiById(BigInteger id) {
		return provinsiDb.findById(id);
	}

	@Override
	public List<ProvinsiModel> viewAllProvinsi() {
		return provinsiDb.findAll();
	}
}

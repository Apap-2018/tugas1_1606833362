package com.apap.tugas01.service;


import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apap.tugas01.model.JabatanModel;
import com.apap.tugas01.model.JabatanPegawaiModel;
import com.apap.tugas01.repository.JabatanPegawaiDb;
@Service
@Transactional

public class JabatanPegawaiServiceImpl implements JabatanPegawaiService{
	@Autowired
	private JabatanPegawaiDb jabatanPegawaiDb;
	
	@Override
	public Optional<List<JabatanPegawaiModel>> findJabatanByPegawaiId(String nip) {
		return jabatanPegawaiDb.findAllByPegawaiNip(nip);
	}
	
	@Override
	public List<JabatanPegawaiModel> findAllByJabatanId(BigInteger id){
		return jabatanPegawaiDb.findAllByJabatanId(id);
	}
	
	@Override
	public int countPegawaiByJabatan(JabatanModel jabatan) {
		return 0;
	}

}

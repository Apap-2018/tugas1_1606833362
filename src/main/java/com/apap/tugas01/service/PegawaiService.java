package com.apap.tugas01.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import com.apap.tugas01.model.InstansiModel;
import com.apap.tugas01.model.PegawaiModel;

public interface PegawaiService {
	void addPegawai (PegawaiModel pegawai);
	void updatePegawai(PegawaiModel pegawai);
	PegawaiModel getPegawaiByNip(String nip);
	Optional<PegawaiModel> getPegawaiById(BigInteger id);
	String generateNIP(PegawaiModel pegawai);
	List<PegawaiModel> getAllPegawai();
	List<PegawaiModel> findByInstansi(InstansiModel instansi);
	double getGajiLengkapByNip(String nip);
}

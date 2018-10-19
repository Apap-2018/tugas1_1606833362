package com.apap.tugas01.service;

import java.util.Date;
import java.util.List;

import com.apap.tugas01.model.InstansiModel;
import com.apap.tugas01.model.JabatanModel;
import com.apap.tugas01.model.PegawaiModel;

public interface PegawaiService {
	void addPegawai (PegawaiModel pegawai);
	PegawaiModel getPegawaiByNip(String nip);
	List<PegawaiModel> getAllPegawai();
	List<PegawaiModel> findByInstansi(InstansiModel instansi);
	double getGajiLengkapByNip(String nip);
}

package com.apap.tugas01.service;

import java.util.List;

import com.apap.tugas01.model.PegawaiModel;

public interface PegawaiService {
	void addPegawai (PegawaiModel pegawai);
	PegawaiModel getPegawaiByNip(String nip);
	List<PegawaiModel> getAllPegawai();
	double getGajiLengkapByNip(String nip);
}

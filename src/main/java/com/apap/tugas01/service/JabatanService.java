package com.apap.tugas01.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import com.apap.tugas01.model.JabatanModel;

public interface JabatanService {
	void addJabatan(JabatanModel jabatan);
	void deleteJabatan(JabatanModel jabatan);
	void updateJabatan(BigInteger id, JabatanModel jabatan);
	Optional<JabatanModel> findJabatanById(BigInteger id);
	List<JabatanModel> viewAll();

}
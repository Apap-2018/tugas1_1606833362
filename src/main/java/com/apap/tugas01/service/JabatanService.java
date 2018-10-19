package com.apap.tugas01.service;

import java.util.List;
import java.util.Optional;

import com.apap.tugas01.model.JabatanModel;

public interface JabatanService {
	void addJabatan(JabatanModel jabatan);
	void deleteJabatan(JabatanModel jabatan);
	void updateJabatan(Long id, JabatanModel jabatan);
	Optional<JabatanModel> findJabatanById(Long id);
	List<JabatanModel> viewAll();

}
package com.apap.tugas01.service;

import java.util.List;
import java.util.Optional;

import com.apap.tugas01.model.JabatanPegawaiModel;

public interface JabatanPegawaiService {
	Optional<List<JabatanPegawaiModel>> findJabatanByPegawaiId(String nip);
	List<JabatanPegawaiModel> findAllByJabatanId(Long id);
}


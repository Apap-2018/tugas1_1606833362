package com.apap.tugas01.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apap.tugas01.model.JabatanPegawaiModel;

@Repository
public interface JabatanPegawaiDb extends JpaRepository<JabatanPegawaiModel,Long> {
	Optional<List<JabatanPegawaiModel>> findAllByPegawaiNip(String nip);
	List<JabatanPegawaiModel> findAllByJabatanId(Long id);
}

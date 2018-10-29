package com.apap.tugas01.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apap.tugas01.model.JabatanModel;
import com.apap.tugas01.repository.JabatanDb;

@Service
@Transactional
public class JabatanServiceImpl implements JabatanService{
    @Autowired
    private JabatanDb jabatanDb;

    public void addJabatan(JabatanModel jabatan){
        jabatanDb.save(jabatan);
    }

    public void deleteJabatan(JabatanModel jabatan){
        jabatanDb.deleteById(jabatan.getId());
    }

    public List<JabatanModel> viewAll(){
        return jabatanDb.findAll();
    }

	@Override
	public void updateJabatan(BigInteger id, JabatanModel jabatan) {
        JabatanModel jabatanUpdated = jabatanDb.getOne(id);
        jabatanUpdated.setDeskripsi(jabatan.getDeskripsi());
        jabatanUpdated.setGajiPokok(jabatan.getGajiPokok());
        jabatanUpdated.setId(jabatan.getId());
        jabatanUpdated.setNama(jabatan.getNama());
        jabatanDb.save(jabatanUpdated);
    }

	@Override
	public Optional<JabatanModel> findJabatanById(BigInteger id) {
		return jabatanDb.findById(id);
	}
}

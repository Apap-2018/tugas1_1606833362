package com.apap.tugas01.service;

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
    private JabatanDb JabatanDb;

    public Optional<JabatanModel> findJabatanById(Long id){
        return JabatanDb.findById(id);
    }

    public void updateJabatan(Long id, JabatanModel jabatan){
        JabatanModel jabatanUpdated = JabatanDb.getOne(id);
        jabatanUpdated.setDeskripsi(jabatan.getDeskripsi());
        jabatanUpdated.setGajiPokok(jabatan.getGajiPokok());
        jabatanUpdated.setId(jabatan.getId());
        jabatanUpdated.setNama(jabatan.getNama());
        JabatanDb.save(jabatanUpdated);
    }

    public void addJabatan(JabatanModel jabatan){
        JabatanDb.save(jabatan);
    }

    public void deleteJabatan(JabatanModel jabatan){
        JabatanDb.deleteById(jabatan.getId());
    }

    public List<JabatanModel> viewAll(){
        return JabatanDb.findAll();
    }
}

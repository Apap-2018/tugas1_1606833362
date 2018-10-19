package com.apap.tugas01.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apap.tugas01.model.InstansiModel;
import com.apap.tugas01.model.JabatanModel;
import com.apap.tugas01.model.PegawaiModel;
import com.apap.tugas01.repository.PegawaiDb;

@Service
@Transactional
public class PegawaiServiceImpl implements PegawaiService{
    @Autowired
    private PegawaiDb PegawaiDb;

    @Override
    public PegawaiModel getPegawaiByNip(String nip) {
        return PegawaiDb.findByNip(nip);
    }


    @Override
    public void addPegawai(PegawaiModel pegawai){
        PegawaiDb.save(pegawai);
    }

    @Override
    public List<PegawaiModel> getAllPegawai(){
        return PegawaiDb.findAll();
    }

    @Override
    public double getGajiLengkapByNip(String nip) {
        double gajiLengkap = 0;
        PegawaiModel pegawai = this.getPegawaiByNip(nip);
        double gajiTerbesar = 0;
        for (JabatanModel jabatan:pegawai.getJabatanList()) {
            if (jabatan.getGajiPokok() > gajiTerbesar) {
                gajiTerbesar = jabatan.getGajiPokok();
            }
        }
        gajiLengkap += gajiTerbesar;
        double presentaseTunjangan = pegawai.getInstansi().getProvinsi().getPresentaseTunjangan();
        gajiLengkap += (gajiLengkap * presentaseTunjangan/100);
        return gajiLengkap;
    }
    
	@Override
	public List<PegawaiModel> findByInstansi(InstansiModel instansi) {
		return PegawaiDb.findByInstansi(instansi);
	}	

}

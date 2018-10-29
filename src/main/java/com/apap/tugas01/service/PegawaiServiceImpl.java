package com.apap.tugas01.service;

import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

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
    private PegawaiDb pegawaiDb;

    @Override
    public PegawaiModel getPegawaiByNip(String nip) {
        return pegawaiDb.findByNip(nip);
    }


    @Override
    public void addPegawai(PegawaiModel pegawai){
        pegawaiDb.save(pegawai);
    }

    @Override
    public List<PegawaiModel> getAllPegawai(){
        return pegawaiDb.findAll();
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
		return pegawaiDb.findByInstansi(instansi);
	}
	
	@Override
	public Optional<PegawaiModel> getPegawaiById(BigInteger id) {
		return pegawaiDb.findById(id);
	}

	@Override
	public void updatePegawai(PegawaiModel pegawai) {
		PegawaiModel oldPegawai = pegawaiDb.findByNip(pegawai.getNip());
		
		if(!oldPegawai.getTanggalLahir().equals(pegawai.getTanggalLahir())||
			!oldPegawai.getTahunMasuk().equals(pegawai.getTahunMasuk())||
			!oldPegawai.getInstansi().equals(pegawai.getInstansi())) {
			oldPegawai.setNip(this.generateNIP(pegawai));
			pegawai.setNip(oldPegawai.getNip());
		}
		oldPegawai.setNama(pegawai.getNama());
		oldPegawai.setTahunMasuk(pegawai.getTahunMasuk());
		oldPegawai.setInstansi(pegawai.getInstansi());
		oldPegawai.setTanggalLahir(pegawai.getTanggalLahir());
		oldPegawai.setTempatLahir(pegawai.getTempatLahir());
		oldPegawai.setJabatanList(pegawai.getJabatanList());
		
		pegawaiDb.save(oldPegawai);
	}

	@Override
	public String generateNIP(PegawaiModel pegawai) {
		String nip = "";
		nip+=pegawai.getInstansi().getId();
        
		DateFormat dateFormat = new SimpleDateFormat("ddMMyy");
        String strDate = dateFormat.format(pegawai.getTanggalLahir());
        nip+=strDate;
        
        nip+=pegawai.getTahunMasuk();
        
        List<PegawaiModel> pegawaiSama = pegawaiDb.findByInstansiAndTanggalLahirAndTahunMasuk(pegawai.getInstansi(), pegawai.getTanggalLahir(), pegawai.getTahunMasuk());
        int urutanPegawai = 1;
        if(pegawaiSama.size()>0) {
        	urutanPegawai = Integer.parseInt(pegawaiSama.get(pegawaiSama.size()-1).getNip().substring(14,16))+1;
        }
        
        if(urutanPegawai<10) {
        	nip+="0";
        }
        nip+=urutanPegawai;
        
        return nip;
	}

}

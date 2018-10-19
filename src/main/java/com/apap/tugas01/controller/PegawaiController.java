package com.apap.tugas01.controller;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.apap.tugas01.model.InstansiModel;
import com.apap.tugas01.model.JabatanModel;
import com.apap.tugas01.model.JabatanPegawaiModel;
import com.apap.tugas01.model.PegawaiModel;
import com.apap.tugas01.service.InstansiService;
import com.apap.tugas01.service.JabatanPegawaiService;
import com.apap.tugas01.service.JabatanService;
import com.apap.tugas01.service.PegawaiService;
import com.apap.tugas01.service.ProvinsiService;

@Controller
public class PegawaiController {
    @Autowired
    private PegawaiService pegawaiService;
    
    @Autowired
    private JabatanPegawaiService jabatanPegawaiService;
    
    @Autowired
    private JabatanService jabatanService;
    
    @Autowired
    private InstansiService instansiService;
    
    @Autowired
    private ProvinsiService provinsiService;

    @RequestMapping("/")
    private String home(Model model) {
        model.addAttribute("listJabatan", jabatanService.viewAll());
        model.addAttribute("listInstansi", instansiService.viewAll());
        model.addAttribute("title", "Home");
        return "home";
    }

    @RequestMapping (value = "/pegawai", method = RequestMethod.GET)
    private String lihatPegawai(@RequestParam(value = "nip") String nip, Model model){
        PegawaiModel pegawai = pegawaiService.getPegawaiByNip(nip);
        List<JabatanPegawaiModel> jabatanPegawai = jabatanPegawaiService.findJabatanByPegawaiId(nip).get();
        double gajiPegawai = pegawaiService.getGajiLengkapByNip(nip);
        long format = (new Double(gajiPegawai)).longValue();

        model.addAttribute("pegawai", pegawai);
        model.addAttribute("gajiPegawai", format);
        model.addAttribute("jabatanPegawai", jabatanPegawai);
        model.addAttribute("title", "Data Pegawai");
        return "view-pegawai";
    }
    
	@RequestMapping(value = "/pegawai/termuda-tertua", method = RequestMethod.GET)
	private String lihatPegawaiTermudaTertua(@RequestParam("idInstansi") long idInstansi, Model model) {
		InstansiModel instansi = instansiService.findInstansiById(idInstansi);
		List<PegawaiModel> daftarPegawai = instansi.getPegawaiList();
		
		if(daftarPegawai.isEmpty()) {
			model.addAttribute("message", "Tidak ada pegawai yang terdaftar dalam instansi ini");
			model.addAttribute("pegawaiTermuda", new PegawaiModel());
			model.addAttribute("pegawaiTertua",  new PegawaiModel());
			return "view-pegawai-termuda-tertua";
		}
		
		PegawaiModel pegawaiTermuda = daftarPegawai.get(0);
		PegawaiModel pegawaiTertua = daftarPegawai.get(0);
		
        
		if(daftarPegawai.size()==1) {
			model.addAttribute("message", "Hanya ada satu pegawai dalam intansi ini");
		}
		else {
			for(int i=0;i<daftarPegawai.size();i++) {
				if(daftarPegawai.get(i).getUmur()<pegawaiTermuda.getUmur()) {
					pegawaiTermuda = daftarPegawai.get(i);
				}
				else if (daftarPegawai.get(i).getUmur()>pegawaiTertua.getUmur()){
					pegawaiTertua = daftarPegawai.get(i);
				}
			}
			model.addAttribute("message", "");
		}
		
		String PegawaiMudaNip = pegawaiTermuda.getNip();
		double gajiPegawaiMuda = pegawaiService.getGajiLengkapByNip(PegawaiMudaNip);
        long formatMuda = (new Double(gajiPegawaiMuda)).longValue();
        
        String PegawaiTuaNip = pegawaiTertua.getNip();
		double gajiPegawaiTua = pegawaiService.getGajiLengkapByNip(PegawaiTuaNip);
        long formatTua = (new Double(gajiPegawaiTua)).longValue();
		
		model.addAttribute("pegawaiTermuda", pegawaiTermuda);
		model.addAttribute("gajiPegawaiTermuda", formatMuda);
		model.addAttribute("jabatanTermuda", pegawaiTermuda.getJabatanList());
		
		model.addAttribute("pegawaiTertua", pegawaiTertua);
		model.addAttribute("gajiPegawaiTertua", formatTua);
		model.addAttribute("jabatanTertua", pegawaiTertua.getJabatanList());
		
		return "view-pegawai-termuda-tertua";
	}

}

package com.apap.tugas01.controller;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.apap.tugas01.model.JabatanModel;
import com.apap.tugas01.model.JabatanPegawaiModel;
import com.apap.tugas01.model.PegawaiModel;
import com.apap.tugas01.service.JabatanPegawaiService;
import com.apap.tugas01.service.JabatanService;
import com.apap.tugas01.service.PegawaiService;

@Controller
public class JabatanController {
    @Autowired
    private JabatanService JabatanService;
    
    @Autowired
    private JabatanPegawaiService JabatanPegawaiService;
    
    @Autowired
    private PegawaiService PegawaiService;

    @RequestMapping(value = "/jabatan/tambah", method = RequestMethod.GET)
    private String tambahJabatan(Model model){
        model.addAttribute("jabatan", new JabatanModel());
        return "tambahJabatan";
    }

    @RequestMapping(value = "/jabatan/tambah", method = RequestMethod.POST)
    private String tambahJabatanSubmit(@ModelAttribute JabatanModel jabatan, Model model){
        JabatanService.addJabatan(jabatan);
        model.addAttribute("jabatan", jabatan);
        return "tambahJabatanSuccess";
    }

    @RequestMapping(value = "/jabatan/view", method = RequestMethod.GET)
    private String lihatJabatan(@RequestParam(value="idJabatan") BigInteger id, Model model) {
		JabatanModel jabatan = JabatanService.findJabatanById(id).get();
		List<PegawaiModel> allPegawai = PegawaiService.getAllPegawai();
		int counter = 0;
		for(int i=0;i<allPegawai.size();i++) {
			for(int j=0;j<allPegawai.get(i).getJabatanList().size();j++) {
				if(allPegawai.get(i).getJabatanList().get(j)==jabatan)
					counter++;
			}
		}
		model.addAttribute("jabatan", jabatan);
		model.addAttribute("jumlahPegawai", counter);
		model.addAttribute("message", "");
        return "view-jabatan";
    }

    @RequestMapping(value = "/jabatan/ubah", method = RequestMethod.GET)
    private String ubahJabatan(@RequestParam(value = "idJabatan") BigInteger id, Model model){
        model.addAttribute("jabatan", JabatanService.findJabatanById(id).get());
        model.addAttribute("message", "");
        return "ubahJabatan";
    }

    @RequestMapping(value = "/jabatan/ubah", method = RequestMethod.POST)
    private String ubahJabatanSubmit(@ModelAttribute JabatanModel jabatan, Model model){
    	BigInteger id = jabatan.getId();
    	JabatanService.updateJabatan(id, jabatan);
        model.addAttribute("jabatan", jabatan);
		model.addAttribute("message", "Data Jabatan Berhasil Diubah");
		return "ubahJabatan";
    }

    @RequestMapping (value = "/jabatan/hapus", method = RequestMethod.POST)
    private String hapusJabatan(@ModelAttribute JabatanModel jabatan, Model model){
        List<JabatanPegawaiModel> list = JabatanPegawaiService.findAllByJabatanId(jabatan.getId());
        if (list.isEmpty()) {
            JabatanService.deleteJabatan(jabatan);
            model.addAttribute("jabatan", jabatan);
            return "hapusJabatanSuccess";
        }
        else {
            model.addAttribute("message", "Maaf, jabatan tidak dapat dihapus");
            model.addAttribute("jabatan", JabatanService.findJabatanById(jabatan.getId()).get());
            model.addAttribute("jumlahPegawai", list.size());
            return "view-jabatan";
        }
    }

    @RequestMapping(value = "/jabatan/viewall", method = RequestMethod.GET)
    private String lihatSeluruhJabatan(Model model){
        model.addAttribute("allJabatan", JabatanService.viewAll());
        return "view-all-jabatan";
    }
}

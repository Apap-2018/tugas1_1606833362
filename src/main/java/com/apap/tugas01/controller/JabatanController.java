package com.apap.tugas01.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.annotation.ManagedOperationParameter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    private String viewJabatan(@RequestParam(value="idJabatan") Long id, Model model) {
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
        return "view-jabatan";
    }

    @RequestMapping(value = "/jabatan/ubah", method = RequestMethod.GET)
    private String ubahJabatan(@RequestParam(value = "idJabatan") String id_jabatan, Model model){
        Long id = Long.parseLong(id_jabatan);
        model.addAttribute("jabatan", JabatanService.findJabatanById(id).get());
        return "ubahJabatan";
    }

    @RequestMapping(value = "/jabatan/ubah", method = RequestMethod.POST)
    private String ubahJabatanSubmit(@ModelAttribute JabatanModel jabatan, Model model){
        Long id = jabatan.getId();
        JabatanService.updateJabatan(id, jabatan);
        model.addAttribute("jabatan", jabatan);
        return "ubahJabatanSuccess";
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
            model.addAttribute("jabatan",jabatan);
            return "hapusJabatanWarning";
        }
    }

    @RequestMapping(value = "/jabatan/viewall", method = RequestMethod.GET)
    private String lihatSeluruhJabatan(Model model){
        model.addAttribute("allJabatan", JabatanService.viewAll());
        return "view-all-jabatan";
    }
}

package com.apap.tugas01.controller;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.apap.tugas01.model.InstansiModel;
import com.apap.tugas01.model.JabatanModel;
import com.apap.tugas01.model.JabatanPegawaiModel;
import com.apap.tugas01.model.PegawaiModel;
import com.apap.tugas01.model.ProvinsiModel;
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
	private String lihatPegawaiTermudaTertua(@RequestParam("idInstansi") BigInteger idInstansi, Model model) {
		InstansiModel instansi = instansiService.findInstansiById(idInstansi).get();
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
	
	@RequestMapping(value = "/pegawai/tambah", method = RequestMethod.GET)
	private String addPegawai(Model model) {
		PegawaiModel pegawai = new PegawaiModel();
		pegawai.setJabatanList(new ArrayList<JabatanModel>());
		pegawai.getJabatanList().add(new JabatanModel());

		model.addAttribute("pegawai", pegawai);
		model.addAttribute("allProvinsi", provinsiService.viewAllProvinsi());
		model.addAttribute("allInstansi", instansiService.viewAll());
		model.addAttribute("allJabatan", jabatanService.viewAll());

		return "tambahPegawai";
	}
	
	@RequestMapping(value = "/pegawai/tambah", method = RequestMethod.POST)
	private String tambahPegawaiSubmit(@ModelAttribute PegawaiModel pegawai, Model model) {
		pegawaiService.addPegawai(pegawai);
		model.addAttribute("pegawai", pegawai);

		return "tambahPegawaiSuccess";
	}
	
	
	@RequestMapping(value="/pegawai/tambah", params={"addRow"}, method = RequestMethod.POST)
	public String addRow(@ModelAttribute PegawaiModel pegawai, BindingResult bindingResult, Model model) {
		pegawai.getJabatanList().add(new JabatanModel());
	    model.addAttribute("pegawai", pegawai);
	    
	    model.addAttribute("allProvinsi", provinsiService.viewAllProvinsi());
		model.addAttribute("allInstansi", instansiService.getInstansiByProvinsi(pegawai.getInstansi().getProvinsi()));
		model.addAttribute("allJabatan", jabatanService.viewAll());
	    return "tambahPegawai";
	}
	
	
	@RequestMapping(value = "/pegawai/tambah", method = RequestMethod.POST, params={"deleteRow"})
	private String deleteRow(@ModelAttribute PegawaiModel pegawai, Model model, final BindingResult bindingResult, final HttpServletRequest req) {
		Integer rowId =  Integer.valueOf(req.getParameter("deleteRow"));
		pegawai.getJabatanList().remove(rowId.intValue());
		model.addAttribute("pegawai", pegawai); 
		
	    model.addAttribute("allProvinsi", provinsiService.viewAllProvinsi());
		model.addAttribute("allInstansi", instansiService.getInstansiByProvinsi(pegawai.getInstansi().getProvinsi()));
		model.addAttribute("allJabatan", jabatanService.viewAll());
		
		return "tambahPegawai";
	}
	
	@RequestMapping(value = "/pegawai/ubah", method = RequestMethod.GET)
	private String ubahPegawai(@RequestParam("nip") String nip, Model model) {
		PegawaiModel pegawai = pegawaiService.getPegawaiByNip(nip);
		model.addAttribute("pegawai", pegawai);
		model.addAttribute("allProvinsi", provinsiService.viewAllProvinsi());
		model.addAttribute("allInstansi", instansiService.viewAll());
		model.addAttribute("allJabatan", jabatanService.viewAll());

		return "ubahPegawai";
	}
	
	@RequestMapping(value = "/pegawai/ubah", method = RequestMethod.POST)
	private String ubahPegawaiSubmit(@ModelAttribute PegawaiModel pegawai, Model model) {
		model.addAttribute("oldNip", pegawai.getNip());
		pegawaiService.updatePegawai(pegawai);
		model.addAttribute("newNip", pegawai.getNip());

		return "ubahPegawaiSuccess";
	}
	
	@RequestMapping(value="/pegawai/ubah", params={"addRow"}, method = RequestMethod.POST)
	public String addRowUbah(@ModelAttribute PegawaiModel pegawai, BindingResult bindingResult, Model model) {
		pegawai.getJabatanList().add(new JabatanModel());
	    model.addAttribute("pegawai", pegawai);
	    
	    model.addAttribute("allProvinsi", provinsiService.viewAllProvinsi());
		model.addAttribute("allInstansi", instansiService.getInstansiByProvinsi((pegawai.getInstansi().getProvinsi())));
		model.addAttribute("allJabatan", jabatanService.viewAll());
		
	    return "ubahPegawai";
	}
	
	
	@RequestMapping(value = "/pegawai/ubah", method = RequestMethod.POST, params={"deleteRow"})
	private String deleteRowUbah(@ModelAttribute PegawaiModel pegawai, Model model,final BindingResult bindingResult, final HttpServletRequest req) {
		Integer rowId =  Integer.valueOf(req.getParameter("deleteRow"));
		pegawai.getJabatanList().remove(rowId.intValue());
		model.addAttribute("pegawai", pegawai); 
		
	    model.addAttribute("allProvinsi", provinsiService.viewAllProvinsi());
		model.addAttribute("allInstansi", instansiService.getInstansiByProvinsi((pegawai.getInstansi().getProvinsi())));
		model.addAttribute("allJabatan", jabatanService.viewAll());
		
		return "ubahPegawai";
	}
	
}
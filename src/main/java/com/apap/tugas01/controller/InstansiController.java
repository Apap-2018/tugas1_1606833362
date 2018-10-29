package com.apap.tugas01.controller;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.apap.tugas01.model.InstansiModel;
import com.apap.tugas01.model.ProvinsiModel;
import com.apap.tugas01.service.InstansiService;
import com.apap.tugas01.service.ProvinsiService;

@Controller
public class InstansiController {
    @Autowired
    private InstansiService instansiService;
    
    @Autowired
    private ProvinsiService provinsiService;
    
	@RequestMapping(value = "/pegawai/instansi", method = RequestMethod.GET)
	@ResponseBody
	public List<InstansiModel> getInstansi(@RequestParam (value = "provinsiId", required = true) BigInteger provinsiId) {
	    ProvinsiModel provinsi = provinsiService.findProvinsiById(provinsiId).get();
		return instansiService.getInstansiByProvinsi(provinsi);
	}

}

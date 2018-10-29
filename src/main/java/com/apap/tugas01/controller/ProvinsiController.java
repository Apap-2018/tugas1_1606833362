package com.apap.tugas01.controller;

import java.math.BigInteger;
import java.util.ArrayList;
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
public class ProvinsiController {
    @Autowired
    private InstansiService instansiService;
    
    @Autowired
    private ProvinsiService provinsiService;
    
	@RequestMapping(value = "/pegawai/provinsi", method = RequestMethod.GET)
	@ResponseBody
	public List<ProvinsiModel> getProvinsi(@RequestParam (value = "instansiId", required = true) BigInteger instansiId) {
	    String instansiName = instansiService.findInstansiById(instansiId).get().getNama();
	    List<InstansiModel> instansiList = instansiService.getByNama(instansiName);
	    List<ProvinsiModel> provinsi = new ArrayList<ProvinsiModel>();
	    for(int i=0;i<instansiList.size();i++) {
	    	provinsi.add(instansiList.get(i).getProvinsi());
	    }
		return provinsi;
	}
}

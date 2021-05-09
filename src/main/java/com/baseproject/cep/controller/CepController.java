package com.baseproject.cep.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baseproject.shared.constants.Pages;
import com.baseproject.shared.dto.CepDataDto;
import com.baseproject.util.BuscaCepUtil;

@Controller
public class CepController {

	@Autowired
	BuscaCepUtil cepUtil;

	@GetMapping("/buscacep")
	public String forgot(Model model) {
		return Pages.CEP;
	}
	
	@ResponseBody
	@GetMapping("/cep/{cep}")
	public CepDataDto buscaCep(@PathVariable String cep) {
		return cepUtil.buscaCep(cep);
	}


}

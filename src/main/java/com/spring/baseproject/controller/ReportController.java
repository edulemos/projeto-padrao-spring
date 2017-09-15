package com.spring.baseproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.spring.baseproject.service.UsuariosSistemaService;

@Controller
public class ReportController {
	
	@Autowired
	private UsuariosSistemaService userService;

	@RequestMapping(value = "/relatorio/usuarios", method = RequestMethod.GET)
	public ModelAndView gerarRelatorioUsuarios(ModelMap modelMap, ModelAndView modelAndView) {
	    modelMap.put("datasource", userService.findAll());
		modelMap.put("format", "pdf");
		modelAndView = new ModelAndView("report_usuarios", modelMap);
		return modelAndView;
	}
	
}

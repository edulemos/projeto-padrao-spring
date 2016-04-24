package com.spring.baseproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProdutoController {
	
	@RequestMapping("/cadastro/produto")
	public String list() {
		return "produto/list";
	}

}

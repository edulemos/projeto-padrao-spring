package com.spring.baseproject.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.baseproject.form.CadastroForm;
import com.spring.baseproject.service.CadastroService;

@Controller
public class CadastroController {

	@Autowired
	private CadastroService service;

	@Autowired
	private MessageSource messages;

	@RequestMapping("/cadastro")
	public String cadastro(Model model) {
		return "home/cadastro";
	}

	@RequestMapping(value = "/cadastro/save", method = RequestMethod.POST)
	public String save(@Valid CadastroForm form, BindingResult bindingResult, Model model) {
		try {

			if (bindingResult.hasErrors()) {
				model.addAttribute("form", form);
				return cadastro(model);
			}

			service.salvar(form);

			model.addAttribute("msg", messages.getMessage("cadastro.msg.sucesso", null, null));

		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			model.addAttribute("form", form);
		}

		return cadastro(model);
	}

}

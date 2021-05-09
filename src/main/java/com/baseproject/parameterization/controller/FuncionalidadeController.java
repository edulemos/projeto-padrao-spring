package com.baseproject.parameterization.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.baseproject.parameterization.model.Funcionalidade;
import com.baseproject.parameterization.service.FuncionalidadeService;
import com.baseproject.parameterization.validator.FuncionalidadeDeleteValidator;
import com.baseproject.shared.constants.Pages;
import com.baseproject.util.MessagesUtil;

@Controller
public class FuncionalidadeController {

	@Autowired
	FuncionalidadeService service;

	@Autowired
	FuncionalidadeDeleteValidator deleteValidator;

	@Autowired
	MessagesUtil msg;

	@GetMapping("/funcionalidades")
	public String list(Model model, @RequestParam Optional<String> fieldName, @RequestParam Optional<String> fieldValue) {
		model.addAttribute("list", service.list(fieldName, fieldValue));
		return Pages.FUNCIONALIDADES_LIST;
	}
	
	@GetMapping("/funcionalidades-form")
	public String form(@RequestParam Optional<String> u, Model model) {
		Funcionalidade entity = u.isPresent() ? service.find(u.get()) : new Funcionalidade();
		model.addAttribute("f", entity);
		return Pages.FUNCIONALIDADES_FORM;
	}
	
	@PostMapping("/funcionalidades-save")
	public String save(Model model, RedirectAttributes ra, @Valid @ModelAttribute("f") Funcionalidade entity, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			model.addAttribute("f", entity);
			return Pages.FUNCIONALIDADES_FORM;
		} else {
			service.save(entity);
			ra.addFlashAttribute("msg", msg.get("funcionalidade.save"));
			return "redirect:funcionalidades";
		}

	}

	@PostMapping("/funcionalidades-delete")
	public String delete(Model model, @ModelAttribute("f") Funcionalidade entity, RedirectAttributes ra, BindingResult bindingResult) {
		deleteValidator.validate(entity.getUuid(), bindingResult);

		if (bindingResult.hasErrors()) {
			model.addAttribute("f", entity);
			return Pages.FUNCIONALIDADES_DELETE;
		} else {
			service.delete(entity.getUuid());
			ra.addFlashAttribute("msg", msg.get("funcionalidade.delete"));
			return "redirect:funcionalidades";
		}

	}

	@GetMapping("/funcionalidades-delete-form")
	public String deleteForm(@RequestParam String u, Model model) {
		Funcionalidade entity = service.find(u);
		model.addAttribute("f", entity);
		return Pages.FUNCIONALIDADES_DELETE;
	}

	

}

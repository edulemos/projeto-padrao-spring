package com.baseproject.parameterization.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.baseproject.parameterization.model.Parametro;
import com.baseproject.parameterization.service.ParametroService;
import com.baseproject.shared.constants.Pages;
import com.baseproject.util.MessagesUtil;

@Controller
public class ParametroController {

	@Autowired
	ParametroService service;

	@Autowired
	MessagesUtil msg;

	@GetMapping("/parametros")
	public String list(Model model, @RequestParam Optional<String> fieldName,
			@RequestParam Optional<String> fieldValue) {
		model.addAttribute("funcionalidades", service.funcionalidades());
		model.addAttribute("list", service.list(fieldName, fieldValue));
		model.addAttribute("idFunc", fieldValue.isPresent() && fieldName.get().equals("funcionalidade") ?  fieldValue.get().toString() : "" );
		return Pages.PARAMETROS_LIST;
	}

	@GetMapping("/parametros-form")
	public String form(@RequestParam Optional<String> u, Model model) {
		Parametro entity = u.isPresent() ? service.find(u.get()) : new Parametro();
		model.addAttribute("p", entity);
		model.addAttribute("funcionalidades", service.funcionalidades());

		return Pages.PARAMETROS_FORM;
	}

	@PostMapping("/parametros-save")
	public String save(Model model, RedirectAttributes ra, @Valid @ModelAttribute("p") Parametro entity,
			BindingResult bindingResult) {
		
		if(null == entity.getFuncionalidade()) {
			ObjectError error = new ObjectError("funcionalidade",msg.get("parametro.erro.funcionalidade"));
			bindingResult.addError(error);
		}
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("funcionalidades", service.funcionalidades());
			model.addAttribute("p", entity);
			return Pages.PARAMETROS_FORM;
		} else {
			service.save(entity);
			ra.addFlashAttribute("msg", msg.get("parametro.save"));
			return "redirect:parametros";
		}

	}

	@PostMapping("/parametros-delete")
	public String delete(RedirectAttributes ra, @ModelAttribute("p") Parametro entity) {
		service.delete(entity.getUuid());
		ra.addFlashAttribute("msg", msg.get("parametro.delete"));
		return "redirect:parametros";
	}

	@GetMapping("/parametros-delete-form")
	public String deleteForm(@RequestParam String u, Model model) {
		Parametro entity = service.find(u);
		model.addAttribute("p", entity);
		return Pages.PARAMETROS_DELETE;
	}

}

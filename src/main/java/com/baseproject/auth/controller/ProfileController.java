package com.baseproject.auth.controller;

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

import com.baseproject.auth.model.Profile;
import com.baseproject.auth.service.ProfileService;
import com.baseproject.auth.validator.ProfileDeleteValidator;
import com.baseproject.shared.constants.Pages;
import com.baseproject.util.MessagesUtil;

@Controller
public class ProfileController {

	@Autowired
	ProfileService service;

	@Autowired
	MessagesUtil msg;

	@Autowired
	ProfileDeleteValidator deleteValidator;

	@GetMapping("/profiles")
	public String list(Model model, @RequestParam Optional<String> fieldName, @RequestParam Optional<String> fieldValue) {
		model.addAttribute("list", service.list(fieldName, fieldValue));
		return Pages.PROFILES_LIST;
	}

	@GetMapping("/profiles-form")
	public String form(@RequestParam Optional<String> u, Model model) {
		Profile entity = service.find(u.isPresent() ? u.get() : "");
		model.addAttribute("p", entity);
		return Pages.PROFILES_FORM;
	}

	@PostMapping("/profiles-save")
	public String save(Model model, RedirectAttributes ra, @Valid @ModelAttribute("p") Profile entity, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			model.addAttribute("p", entity);
			return Pages.PROFILES_FORM;
		} else {
			service.save(entity);
			ra.addFlashAttribute("msg", msg.get("profile.save"));
			return "redirect:profiles";
		}

	}

	@PostMapping("/profiles-delete")
	public String delete(Model model, @ModelAttribute("p") Profile entity, RedirectAttributes ra, BindingResult bindingResult) {
		deleteValidator.validate(entity.getUuid(), bindingResult);

		if (bindingResult.hasErrors()) {
			model.addAttribute("p", entity);
			return Pages.PROFILES_DELETE;
		} else {
			service.delete(entity.getUuid());
			ra.addFlashAttribute("msg", msg.get("profile.delete"));
			return "redirect:profiles";
		}
	}

	@GetMapping("/profiles-delete-form")
	public String deleteForm(@RequestParam String u, Model model) {
		Profile entity = service.find(u);
		model.addAttribute("p", entity);
		return Pages.PROFILES_DELETE;
	}

}

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.baseproject.auth.dto.CheckEmailDto;
import com.baseproject.auth.model.User;
import com.baseproject.auth.service.UsersService;
import com.baseproject.shared.constants.Pages;
import com.baseproject.util.MessagesUtil;

@Controller
public class UsersController {

	@Autowired
	UsersService service;

	@Autowired
	MessagesUtil msg;

	@GetMapping("/users")
	public String list(Model model, @RequestParam Optional<String> fieldName, @RequestParam Optional<String> fieldValue) {
		model.addAttribute("list", service.list(fieldName, fieldValue));
		return Pages.USERS_LIST;
	}

	@GetMapping("/users-form")
	public String form(@RequestParam Optional<String> u, Model model) {
		User user = service.find(u.isPresent() ? u.get() : "");
		model.addAttribute("user", user);
		return Pages.USERS_FORM;
	}

	@PostMapping("/users-save")
	public String save(Model model, RedirectAttributes ra, @Valid @ModelAttribute("user") User user, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("user", user);
			return Pages.USERS_FORM;
		} else {
			service.save(user);
			ra.addFlashAttribute("msg", msg.get("user.save"));
			return "redirect:users";
		}
	}

	@GetMapping("/users-delete")
	public String delete(RedirectAttributes ra, @RequestParam String u) {
		service.delete(u);
		ra.addFlashAttribute("msg", msg.get("user.delete"));
		return "redirect:users";
	}

	@GetMapping("/users-delete-form")
	public String deleteForm(@RequestParam String u, Model model) {
		User user = service.find(u);
		model.addAttribute("user", user);
		return Pages.USERS_DELETE;
	}
	
	@ResponseBody
	@GetMapping("/check/email")
	public CheckEmailDto buscaCep(@RequestParam Optional<String> uuid, @RequestParam Optional<String> email) {
		CheckEmailDto dto = new CheckEmailDto();
		dto.setInUse(service.checkEmailDuplicate(uuid, email));
		return dto;
	}

}

package com.spring.baseproject.test.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.spring.baseproject.config.SpringDataConfig;
import com.spring.baseproject.config.SpringMvcConfig;
import com.spring.baseproject.config.SpringSecurityConfig;
import com.spring.baseproject.service.CadastroService;
import com.spring.baseproject.test.util.TestUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { SpringSecurityConfig.class, SpringMvcConfig.class, SpringDataConfig.class })
public class CadastroControllerTest {

	@Autowired
	private CadastroService service;

	@Autowired
	private MessageSource messages;

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@Test
	public void cadastroPage() throws Exception {
		mockMvc.perform(get("/cadastro")).andExpect(status().isOk()).andExpect(view().name("home/cadastro"));
	}

	@Test
	public void cadastroOk() throws Exception {
		
		String dataHoraString = TestUtil.dataHoraString();
		String nome = "Usuario " + dataHoraString;
		String email = dataHoraString + "@email.com";
		
		this.mockMvc.perform(post("/cadastro/save")				
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("nome", nome)
				.param("email", email)
				.param("confirmaEmail", email)
				.param("senha", "123456")
				.param("confirmaSenha", "123456"))
				.andExpect(view().name("home/cadastro"))
				.andExpect(model().attribute("msg", messages.getMessage("cadastro.msg.sucesso", null, null)));
	}
	
	@Test
	public void cadastroEmailInvalido() throws Exception {
		
		String dataHoraString = TestUtil.dataHoraString();
		String nome = "Usuario " + dataHoraString;
		
		this.mockMvc.perform(post("/cadastro/save")	
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("nome", nome)
				.param("email", "<error>")
				.param("confirmaEmail", "<error>")
				.param("senha", "123456")
				.param("confirmaSenha", "123456"))
                .andDo(print())
				.andExpect(view().name("home/cadastro"))
		        .andExpect(model().attributeHasFieldErrors("cadastroForm", "email", "confirmaEmail"));		
	}

}

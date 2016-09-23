package com.spring.baseproject.test.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
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
import com.spring.baseproject.entity.Usuario;
import com.spring.baseproject.repository.UserRepository;
import com.spring.baseproject.test.util.TestBuild;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { SpringSecurityConfig.class, SpringMvcConfig.class, SpringDataConfig.class })
public class CadastroControllerTest extends TestBuild{
	
	@Autowired
	private MessageSource messages;
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;
	
	List<Usuario> testUsers = new ArrayList<Usuario>();

	
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	/** TESTE UMA UM CADASTRO VALIDO - STATUS OK*/
	@Test
	public void testCadastroOk() throws Exception {
		
		Usuario testUser = getTestUser();
		testUsers.add(testUser);

		this.mockMvc.perform(post("/cadastro/save")				
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("nome", testUser.getName())
				.param("email", testUser.getEmail())
				.param("confirmaEmail", testUser.getEmail())
				.param("senha", testUser.getPassword())
				.param("confirmaSenha", testUser.getPassword()))
				.andExpect(view().name("home/cadastro"))
				.andExpect(model().attribute("msg", messages.getMessage("cadastro.msg.sucesso", null, null)));
		
	}
	
	/** TESTE UMA UM CADASTRO COM EMAIL NÃO INFORMADO - STATUS NOK*/
	@Test
	public void testCadastroEmailNaoInformado() throws Exception {
		
		this.mockMvc.perform(post("/cadastro/save")	
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("nome", "teste")
				//.param("email", "email@email.com")
				.param("confirmaEmail", "email@email.com")
				.param("senha", "123456")
				.param("confirmaSenha", "123456"))
				.andExpect(view().name("home/cadastro"))
				.andExpect(model().attributeHasFieldErrors("cadastroForm", "email"));		
	}
	
	/** TESTE UMA UM CADASTRO COM A CONFIRMACAO EMAIL NÃO INFORMADO - STATUS NOK*/
	@Test
	public void testCadastroConfirmaEmailNaoInformado() throws Exception {
		
		this.mockMvc.perform(post("/cadastro/save")	
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("nome", "teste")
				.param("email", "email@email.com")
				//.param("confirmaEmail", "email@email.com")
				.param("senha", "123456")
				.param("confirmaSenha", "123456"))
				.andExpect(view().name("home/cadastro"))
				.andExpect(model().attributeHasFieldErrors("cadastroForm", "confirmaEmail"));		
	}	
	
	/** TESTE UMA UM CADASTRO COM O EMAIL INVALIDO - STATUS NOK*/
	@Test
	public void testCadastroEmailInvalido() throws Exception {
		
		String dataHoraString = dataHoraString();
		String nome = "Usuario " + dataHoraString;
		
		this.mockMvc.perform(post("/cadastro/save")	
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("nome", nome)
				.param("email", "<error>")
				.param("confirmaEmail", "<error>")
				.param("senha", "123456")
				.param("confirmaSenha", "123456"))
				.andExpect(view().name("home/cadastro"))
		        .andExpect(model().attributeHasFieldErrors("cadastroForm", "email", "confirmaEmail"));		
		
	}
	
	/** TESTE DE UM CADASTRO COM O EMAIL JA CADASTRADO - STATUS NOK*/
	@Test
	public void testCadastroEmailDuplicado() throws Exception {
		
		Usuario testUser = getTestUser();
		repository.save(testUser);
		testUsers.add(testUser);
		
		this.mockMvc.perform(post("/cadastro/save")	
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("nome", testUser.getName())
				.param("email", testUser.getEmail())
				.param("confirmaEmail", testUser.getEmail())
				.param("senha", "123456")
				.param("confirmaSenha", "123456"))
				.andExpect(view().name("home/cadastro"))
				.andExpect(model().attribute("error", messages.getMessage("cadastro.msg.emailemuso", null, null)));	
	
	}
	
	/** TESTE DE UM CADASTRO COM O NOME NAO INFORMADO - STATUS NOK*/
	@Test
	public void testCadastroNomeNaoInformado() throws Exception {
		
		this.mockMvc.perform(post("/cadastro/save")	
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				//.param("nome", nome)
				.param("email", "email@email.com")
				.param("confirmaEmail", "email@email.com")
				.param("senha", "123456")
				.param("confirmaSenha", "123456"))
				.andExpect(view().name("home/cadastro"))
				.andExpect(model().attributeHasFieldErrors("cadastroForm", "nome"));		
	}
	
	
	/** TESTE DE UM CADASTRO COM A SENHA NAO INFORMADA - STATUS NOK*/
	@Test
	public void testCadastroSenhaNaoInformada() throws Exception {
		
		this.mockMvc.perform(post("/cadastro/save")	
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("nome", "nome")
				.param("email", "email@email.com")
				.param("confirmaEmail", "email@email.com")
			//	.param("senha", "123456")
				.param("confirmaSenha", "123456"))
				.andExpect(view().name("home/cadastro"))
				.andExpect(model().attributeHasFieldErrors("cadastroForm", "senha"));		
	}
	
	/** TESTE DE UM CADASTRO COM A CONFIRMACAO DA SENHA NA INFORMADA - STATUS NOK*/
	@Test
	public void testCadastroConfirmaSenhaNaoInformada() throws Exception {
		
		this.mockMvc.perform(post("/cadastro/save")	
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("nome", "nome")
				.param("email", "email@email.com")
				.param("confirmaEmail", "email@email.com")
				.param("senha", "123456"))
//				.param("confirmaSenha", "123456"))
				.andExpect(view().name("home/cadastro"))
				.andExpect(model().attributeHasFieldErrors("cadastroForm", "confirmaSenha"));		
	}
	
	@After
	public void deletarDadosDeTeste() throws ClassNotFoundException, SQLException{
		for (Usuario usuario : testUsers) {
			deleteUsuarioDeTeste(usuario.getEmail());
		}
	}

}

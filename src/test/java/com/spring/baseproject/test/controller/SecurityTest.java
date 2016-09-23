package com.spring.baseproject.test.controller;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
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
import com.spring.baseproject.test.util.TestBuild;


@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { SpringSecurityConfig.class, SpringMvcConfig.class, SpringDataConfig.class })
public class SecurityTest extends TestBuild{
	
	@Autowired
	private WebApplicationContext context;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
	}

	/** TESTE DE UM LOGIN VALIDO - STATUS OK */
	@Test
	public void testLoginOK() throws Exception {
		
		String senha = "123456";
		Usuario testUser = getTestUser(senha);
		insertUsuarioDeTeste(testUser);
		testUsers.add(testUser);
		
		System.out.println(testUser.getEmail());
		System.out.println(testUser.getPassword());
		
		mockMvc.perform(formLogin()
				.user(testUser.getEmail())
				.password(senha))
				.andExpect(authenticated());
		
	}
	
	/** TESTE DE UM LOGIN INVALIDO - STATUS NOK */
	@Test
	public void testLoginNOK() throws Exception {
		
		String senha = "123456";
		String outraSenha = "111111";
		Usuario testUser = getTestUser(senha);
		insertUsuarioDeTeste(testUser);
		testUsers.add(testUser);
		
		System.out.println(testUser.getEmail());
		System.out.println(testUser.getPassword());
		
		mockMvc.perform(formLogin()
				.user(testUser.getEmail())
				.password(outraSenha))
				.andExpect(unauthenticated());
		
	}
	
	@Test
	@WithMockUser(username="admin@email.com", roles="{MANTER_PERFIS, ROLE_MANTER_USUARIOS, ROLE_MANTER_PRODUTOS}")
	public void testRoleUrl() throws Exception {
		
		mockMvc.perform(
				get("/perfis/list"))			
				.andExpect(status().isForbidden());
	}
	
	
	@After
	public void deletarDadosDeTeste() throws ClassNotFoundException, SQLException{
		for (Usuario usuario : testUsers) {
			deleteUsuarioDeTeste(usuario.getEmail());
		}
	}
	

}

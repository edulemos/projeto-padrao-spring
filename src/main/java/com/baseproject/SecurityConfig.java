package com.baseproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.baseproject.auth.enumerator.Roles;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Qualifier("userDetailsServiceImpl")
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/resources/**", "/recover**", "/register**", "/h2-console**").permitAll()
                
                .antMatchers(Roles.ROLE_PROFILES_LIST.getUrl()).access(hasRole(Roles.ROLE_PROFILES_LIST))		    
                .antMatchers(Roles.ROLE_PROFILES_SAVE.getUrl()).access(hasRole(Roles.ROLE_PROFILES_SAVE))		    
                .antMatchers(Roles.ROLE_PROFILES_DELETE.getUrl()).access(hasRole(Roles.ROLE_PROFILES_DELETE))		
                
    		    .antMatchers(Roles.ROLE_USERS_LIST.getUrl()).access(hasRole(Roles.ROLE_USERS_LIST))		    
    		    .antMatchers(Roles.ROLE_USERS_SAVE.getUrl()).access(hasRole(Roles.ROLE_USERS_SAVE))		    
    		    .antMatchers(Roles.ROLE_USERS_DELETE.getUrl()).access(hasRole(Roles.ROLE_USERS_DELETE))		
    		    
    		    .antMatchers(Roles.ROLE_PARAMETER_LIST.getUrl()).access(hasRole(Roles.ROLE_PARAMETER_LIST))		    
    		    .antMatchers(Roles.ROLE_PARAMETER_SAVE.getUrl()).access(hasRole(Roles.ROLE_PARAMETER_SAVE))		    
    		    .antMatchers(Roles.ROLE_PARAMETER_DELETE.getUrl()).access(hasRole(Roles.ROLE_PARAMETER_DELETE))
    		    
    		    .antMatchers(Roles.ROLE_FUNCIONALIDADE_LIST.getUrl()).access(hasRole(Roles.ROLE_FUNCIONALIDADE_LIST))		    
    		    .antMatchers(Roles.ROLE_FUNCIONALIDADE_SAVE.getUrl()).access(hasRole(Roles.ROLE_FUNCIONALIDADE_SAVE))		    
    		    .antMatchers(Roles.ROLE_FUNCIONALIDADE_DELETE.getUrl()).access(hasRole(Roles.ROLE_FUNCIONALIDADE_DELETE))
    		    
    		
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
            .logout()
                .permitAll();
        
        http.csrf().disable();
        http.headers().frameOptions().disable();


    }

    @Bean
    public AuthenticationManager customAuthenticationManager() throws Exception {
        return authenticationManager();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }
    
	private String hasRole(Roles roleName) {
		return "hasRole('" + roleName.toString() + "')";
	}
}
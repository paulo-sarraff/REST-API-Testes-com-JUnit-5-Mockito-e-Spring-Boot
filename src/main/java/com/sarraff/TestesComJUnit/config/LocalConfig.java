package com.sarraff.TestesComJUnit.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.sarraff.TestesComJUnit.domain.Usuario;
import com.sarraff.TestesComJUnit.repositories.UsuarioRepository;

@Configuration
@Profile("local")
public class LocalConfig {
	
	@Autowired
	private UsuarioRepository repository;

	@Bean
	public void startDB() {
		Usuario u1 = new Usuario(null, "Paulo", "paulo@ig.com.br", "123");
		Usuario u2 = new Usuario(null, "Luis", "luis@ig.com.br", "1234");
		
		repository.saveAll(List.of(u1, u2));
	}
}

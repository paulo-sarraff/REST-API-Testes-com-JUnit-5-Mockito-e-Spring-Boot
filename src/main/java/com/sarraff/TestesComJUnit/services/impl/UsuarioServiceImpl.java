package com.sarraff.TestesComJUnit.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sarraff.TestesComJUnit.domain.Usuario;
import com.sarraff.TestesComJUnit.repositories.UsuarioRepository;
import com.sarraff.TestesComJUnit.services.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService{
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public Usuario findById(Integer id) {
		
		Optional<Usuario> obj = usuarioRepository.findById(id);
		return obj.orElse(null);
	}
	
}

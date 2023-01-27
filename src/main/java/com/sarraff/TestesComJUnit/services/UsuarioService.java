package com.sarraff.TestesComJUnit.services;

import java.util.List;

import com.sarraff.TestesComJUnit.domain.Usuario;

public interface UsuarioService {
	
	Usuario findById(Integer id);
	
	List<Usuario> findAll();
}

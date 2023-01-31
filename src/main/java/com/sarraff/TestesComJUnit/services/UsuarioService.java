package com.sarraff.TestesComJUnit.services;

import java.util.List;

import com.sarraff.TestesComJUnit.domain.Usuario;
import com.sarraff.TestesComJUnit.domain.dto.UsuarioDTO;

public interface UsuarioService {
	
	Usuario findById(Integer id);
	List<Usuario> findAll();
	Usuario create(UsuarioDTO obj);
	Usuario update (UsuarioDTO obj);
}

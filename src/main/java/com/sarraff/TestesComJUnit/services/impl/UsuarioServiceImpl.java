package com.sarraff.TestesComJUnit.services.impl;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sarraff.TestesComJUnit.domain.Usuario;
import com.sarraff.TestesComJUnit.domain.dto.UsuarioDTO;
import com.sarraff.TestesComJUnit.repositories.UsuarioRepository;
import com.sarraff.TestesComJUnit.services.UsuarioService;
import com.sarraff.TestesComJUnit.services.exceptions.DataIntegratyViolationException;
import com.sarraff.TestesComJUnit.services.exceptions.ObjectNotFoundException;

@Service
public class UsuarioServiceImpl implements UsuarioService{
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public Usuario findById(Integer id) {
		
		Optional<Usuario> obj = usuarioRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	public List<Usuario> findAll(){
		return usuarioRepository.findAll();
	}

	@Override
	public Usuario create(UsuarioDTO obj) {
		findByEmail(obj);
		return usuarioRepository.save(mapper.map(obj, Usuario.class));
	}
	
	private void findByEmail(UsuarioDTO obj) {
		Optional<Usuario> usuario = usuarioRepository.findByEmail(obj.getEmail());
		
		if(usuario.isPresent() && !usuario.get().getId().equals(obj.getId())) {
			throw new DataIntegratyViolationException("Email já cadastrado no sistema");
		}
	}

	@Override
	public Usuario update(UsuarioDTO obj) {
		findByEmail(obj);
		return usuarioRepository.save(mapper.map(obj, Usuario.class));
	}
	
}

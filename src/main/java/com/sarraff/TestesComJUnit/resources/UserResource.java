package com.sarraff.TestesComJUnit.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sarraff.TestesComJUnit.domain.dto.UsuarioDTO;
import com.sarraff.TestesComJUnit.services.UsuarioService;

@RestController
@RequestMapping(value = "/usuario")
public class UserResource {
	
	@Autowired
	private UsuarioService service;
	
	@Autowired
	private ModelMapper mapper;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<UsuarioDTO> findById(@PathVariable Integer id){
		return ResponseEntity.ok().body(mapper.map(service.findById(id), UsuarioDTO.class));
	}
	
	@GetMapping
	public ResponseEntity<List<UsuarioDTO>> findAll(){
		return ResponseEntity.ok()
				.body(service.findAll()
						.stream().map(x -> mapper.map(x, UsuarioDTO.class)).collect(Collectors.toList()));
	}
	
	@PostMapping
	public ResponseEntity<UsuarioDTO> create(@RequestBody UsuarioDTO obj){
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}").buildAndExpand(service.create(obj)).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<UsuarioDTO> update(@PathVariable Integer id, @RequestBody UsuarioDTO obj){
		obj.setId(id);
		return ResponseEntity.ok().body(mapper.map(service.update(obj), UsuarioDTO.class));
	}
}

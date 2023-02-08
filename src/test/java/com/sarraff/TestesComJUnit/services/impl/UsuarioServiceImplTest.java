package com.sarraff.TestesComJUnit.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import com.sarraff.TestesComJUnit.domain.Usuario;
import com.sarraff.TestesComJUnit.domain.dto.UsuarioDTO;
import com.sarraff.TestesComJUnit.repositories.UsuarioRepository;
import com.sarraff.TestesComJUnit.services.exceptions.ObjectNotFoundException;

@SpringBootTest
class UsuarioServiceImplTest {
	
	private static final int INDEX = 0;

	private static final String PASSWORD = "123";

	private static final String EMAIL = "paulo@uea.edu.br";

	private static final String NOME = "Paulo";

	private static final Integer ID = 1;

	@InjectMocks
	private UsuarioServiceImpl service;
	
	@Mock
	private UsuarioRepository usuarioRepository;
	
	@Mock
	private ModelMapper mapper;
	
	private Usuario usuario;
	
	private UsuarioDTO usuarioDTO;
	
	private Optional<Usuario> optionalUsuario;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		startUsuario();
	}

	@Test
	void whenFindByIdEntaoRetorneUmaInstanciaUsuario() {
		when(usuarioRepository.findById(anyInt())).thenReturn(optionalUsuario);
		Usuario response = service.findById(ID);
		
		assertNotNull(response);
		assertEquals(Usuario.class, response.getClass());
		assertEquals(ID, response.getId());
		assertEquals(NOME, response.getName());
		assertEquals(EMAIL, response.getEmail());
	}
	
	@Test
	void whenFindByIdRetorneObjectNotFoundException() {
		when(usuarioRepository.findById(anyInt())).thenThrow(new ObjectNotFoundException("Objeto não encontrado"));
		
		try {
			service.findById(ID);
		} catch (Exception e) {
			assertEquals(ObjectNotFoundException.class, e.getClass());
			assertEquals("Objeto não encontrado", e.getMessage());
		}
	}

	@Test
	void whenFindAllThenReturnAnListOfUsers() {
		when(usuarioRepository.findAll()).thenReturn(List.of(usuario));
		List<Usuario> response = service.findAll();
		
		assertNotNull(response);
		assertEquals(1, response.size());
		assertEquals(Usuario.class, response.get(INDEX).getClass());
		
		assertEquals(ID, response.get(INDEX).getId());
		assertEquals(NOME, response.get(INDEX).getName());
		assertEquals(EMAIL, response.get(INDEX).getEmail());
		assertEquals(PASSWORD, response.get(INDEX).getPassword());
	}

	@Test
	void testCreate() {
	}

	@Test
	void testUpdate() {
	}

	@Test
	void testDelete() {
	}
	
	private void startUsuario() {
		usuario = new Usuario(ID, NOME, EMAIL, PASSWORD);
		usuarioDTO = new UsuarioDTO(ID, NOME, EMAIL, PASSWORD);
		optionalUsuario = Optional.of(new Usuario(ID, NOME, EMAIL, PASSWORD));
	}

}

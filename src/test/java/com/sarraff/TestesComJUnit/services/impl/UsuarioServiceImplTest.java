package com.sarraff.TestesComJUnit.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

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

@SpringBootTest
class UsuarioServiceImplTest {
	
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
	void testFindAll() {
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

package com.sarraff.TestesComJUnit.resources;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.sarraff.TestesComJUnit.domain.Usuario;
import com.sarraff.TestesComJUnit.domain.dto.UsuarioDTO;
import com.sarraff.TestesComJUnit.services.UsuarioService;

@SpringBootTest
class UserResourceTest {
	
	private static final int INDEX = 0;

	private static final String PASSWORD = "123";

	private static final String EMAIL = "paulo@uea.edu.br";

	private static final String NOME = "Paulo";

	private static final Integer ID = 1;
	
	private Usuario usuario;
	
	private UsuarioDTO usuarioDTO;
	
	
	@InjectMocks
	private UserResource resource;
	
	@Mock
	private UsuarioService service;
	
	@Mock
	private ModelMapper mapper;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		startUsuario();
	}

	@Test
	void whenFindByIdThenReturnSuccess() {
		when(service.findById(anyInt())).thenReturn(usuario);
		when(mapper.map(any(), any())).thenReturn(usuarioDTO);
		
		ResponseEntity<UsuarioDTO> response = resource.findById(ID);
		
		assertNotNull(response);
		assertNotNull(response.getBody());
		assertEquals(ResponseEntity.class, response.getClass());
		assertEquals(UsuarioDTO.class, response.getBody().getClass());
		
		assertEquals(ID, response.getBody().getId());
		assertEquals(NOME, response.getBody().getName());
		assertEquals(EMAIL, response.getBody().getEmail());
		assertEquals(PASSWORD, response.getBody().getPassword());
	}
	
	@Test
	void test2() {
		fail("Not yet implemented");
	}
	
	@Test
	void test3() {
		fail("Not yet implemented");
	}
	
	@Test
	void test4() {
		fail("Not yet implemented");
	}
	
	@Test
	void test5() {
		fail("Not yet implemented");
	}
	
	private void startUsuario() {
		usuario = new Usuario(ID, NOME, EMAIL, PASSWORD);
		usuarioDTO = new UsuarioDTO(ID, NOME, EMAIL, PASSWORD);
	}

}

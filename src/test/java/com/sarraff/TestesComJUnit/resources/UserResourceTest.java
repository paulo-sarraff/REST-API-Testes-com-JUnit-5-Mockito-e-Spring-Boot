package com.sarraff.TestesComJUnit.resources;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
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
	
	private Usuario usuario = new Usuario();
	
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
	void whenFindAllThenReturnAListOfUserDTO() {
		when(service.findAll()).thenReturn(List.of(usuario));
		when(mapper.map(any(), any())).thenReturn(usuarioDTO);
		
		ResponseEntity<List<UsuarioDTO>> response = resource.findAll();
		
		assertNotNull(response);
		assertNotNull(response.getBody());
		assertEquals(ResponseEntity.class, response.getClass());
		assertEquals(ArrayList.class, response.getBody().getClass());
		assertEquals(UsuarioDTO.class, response.getBody().get(INDEX).getClass());
		assertEquals(HttpStatus.OK, response.getStatusCode());
		
		assertEquals(ID, response.getBody().get(INDEX).getId());
		assertEquals(NOME, response.getBody().get(INDEX).getName());
		assertEquals(EMAIL, response.getBody().get(INDEX).getEmail());
		assertEquals(PASSWORD, response.getBody().get(INDEX).getPassword());
	}
	
	@Test
	void whenCreateThenReturnCreated() {
		when(service.create(any())).thenReturn(usuario);
		
		ResponseEntity<UsuarioDTO> response = resource.create(usuarioDTO);
		
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals(ResponseEntity.class, response.getClass());
		assertNotNull(response.getHeaders().getLocation());
	}
	
	@Test
	void whenUpdateThenReturnSuccess() {
		when(service.update(usuarioDTO)).thenReturn(usuario);
		when(mapper.map(any(), any())).thenReturn(usuarioDTO);
		
		ResponseEntity<UsuarioDTO> response = resource.update(ID, usuarioDTO);
		
		assertNotNull(response);
		assertNotNull(response.getBody());
		assertEquals(ResponseEntity.class, response.getClass());
		assertEquals(UsuarioDTO.class, response.getBody().getClass());
		assertEquals(HttpStatus.OK, response.getStatusCode());
		
		assertEquals(ID, response.getBody().getId());
		assertEquals(NOME, response.getBody().getName());
		assertEquals(EMAIL, response.getBody().getEmail());
		
	}
	
	@Test
	void whenDeleteThenReturnSuccess() {
		doNothing().when(service).delete(anyInt());
		
		ResponseEntity<UsuarioDTO> response = resource.delete(ID);
		
		assertNotNull(response);
		assertEquals(ResponseEntity.class, response.getClass());
		verify(service, times(1)).delete(anyInt());
		assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
	}
	
	private void startUsuario() {
		usuario = new Usuario(ID, NOME, EMAIL, PASSWORD);
		usuarioDTO = new UsuarioDTO(ID, NOME, EMAIL, PASSWORD);
	}

}

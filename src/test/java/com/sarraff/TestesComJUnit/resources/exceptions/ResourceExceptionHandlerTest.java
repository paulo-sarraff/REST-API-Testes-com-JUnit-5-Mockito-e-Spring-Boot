package com.sarraff.TestesComJUnit.resources.exceptions;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import com.sarraff.TestesComJUnit.services.exceptions.DataIntegratyViolationException;
import com.sarraff.TestesComJUnit.services.exceptions.ObjectNotFoundException;

@SpringBootTest
class ResourceExceptionHandlerTest {
	
	private static final String EMAIL_JA_CADASTRADO = "Email já cadastrado";
	private static final String OBJETO_NAO_ENCONTRADO = "Objeto não encontrado";
	@InjectMocks
	private ResourceExceptionHandler exceptionHandler;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testObjectNotFoundExceptionThenReturnAResponseEntity() {
		ResponseEntity<StandardError> response = exceptionHandler
				.objectNotFound(
						new ObjectNotFoundException(OBJETO_NAO_ENCONTRADO),
						new MockHttpServletRequest());	
		assertNotNull(response);
		assertNotNull(response.getBody());
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertEquals(ResponseEntity.class, response.getClass());
		assertEquals(StandardError.class, response.getBody().getClass());
		assertEquals(OBJETO_NAO_ENCONTRADO, response.getBody().getError());
		assertEquals(404, response.getBody().getStatus());
	}

	@Test
	void testDataIntegrityViolationException() {
		ResponseEntity<StandardError> response = exceptionHandler
				.dataIntegrityViolationException(
						new DataIntegratyViolationException(EMAIL_JA_CADASTRADO),
						new MockHttpServletRequest());	
		assertNotNull(response);
		assertNotNull(response.getBody());
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		assertEquals(ResponseEntity.class, response.getClass());
		assertEquals(StandardError.class, response.getBody().getClass());
		assertEquals(EMAIL_JA_CADASTRADO, response.getBody().getError());
		assertEquals(400, response.getBody().getStatus());
	}

}

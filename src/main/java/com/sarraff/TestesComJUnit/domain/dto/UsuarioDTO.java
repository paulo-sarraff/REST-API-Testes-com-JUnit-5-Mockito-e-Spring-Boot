package com.sarraff.TestesComJUnit.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {
		
	private Integer id;
	private String name;
	private String email;
	@JsonIgnore
	private String password;
}

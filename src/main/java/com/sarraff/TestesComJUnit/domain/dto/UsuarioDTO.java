package com.sarraff.TestesComJUnit.domain.dto;

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
	private String password;
}

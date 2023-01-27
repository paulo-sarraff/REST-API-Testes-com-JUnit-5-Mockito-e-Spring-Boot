package com.sarraff.TestesComJUnit.resources.exceptions;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StandardError {

	private LocalDate timestamp;
	private Integer status;
	private String error;
	private String path;
}

package com.sarraff.TestesComJUnit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sarraff.TestesComJUnit.domain.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

}

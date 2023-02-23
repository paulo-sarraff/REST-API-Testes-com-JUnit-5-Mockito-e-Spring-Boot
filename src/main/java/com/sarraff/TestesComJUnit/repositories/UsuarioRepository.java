package com.sarraff.TestesComJUnit.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sarraff.TestesComJUnit.domain.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

	Optional<Usuario> findByEmail(String email);
}

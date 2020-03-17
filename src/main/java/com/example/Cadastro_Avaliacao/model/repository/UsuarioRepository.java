package com.example.Cadastro_Avaliacao.model.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Cadastro_Avaliacao.model.entity.Usuario;

public interface UsuarioRepository extends JpaRepository <Usuario, Long> {

	boolean existsByEmail(String email);
	Optional<Usuario> findByEmail(String email);
}

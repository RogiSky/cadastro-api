package com.example.Cadastro_Avaliacao.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Cadastro_Avaliacao.model.entity.Formulario;

public interface FormularioRepository extends JpaRepository <Formulario, String>{

	boolean existsByCpf(String cpf);
	Optional<Formulario> findByCpf(String cpf);
	Optional<Formulario> findByNome(String nome);
	Optional<Formulario> findByUF(String uf);

}

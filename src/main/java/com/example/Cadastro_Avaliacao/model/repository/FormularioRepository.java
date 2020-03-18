package com.example.Cadastro_Avaliacao.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.Cadastro_Avaliacao.model.entity.Formulario;

public interface FormularioRepository extends JpaRepository <Formulario, String>{

	boolean existsByCpf(String cpf);
	Optional<Formulario> findByCpf(String cpf);
	Optional<Formulario> findByNome(String nome);
	Optional<Formulario> findByUf(String uf);
	Formulario save(Formulario formulario);
	@Query(value = " select count(*) from Formulario f where f.uf = :uf")
	Integer obterTotalPorEstado(@Param("uf")String uf);
}

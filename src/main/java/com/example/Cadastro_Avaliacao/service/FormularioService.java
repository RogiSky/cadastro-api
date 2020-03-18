package com.example.Cadastro_Avaliacao.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.query.Param;

import com.example.Cadastro_Avaliacao.model.entity.Formulario;

public interface FormularioService {

	Formulario salvar(Formulario formulario);
	Formulario atualizar(Formulario formulario);
	Optional<Formulario> selecionaPorCpf(String cpf);
	void deletar(Formulario formulario);
	void validarCpf(String cpf);
	void validar(Formulario formulario);
	List <Formulario> buscar(Formulario formularioFiltro);
	Integer obterTotalPorEstado(String uf);

}

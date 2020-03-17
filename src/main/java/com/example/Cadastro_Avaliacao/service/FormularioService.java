package com.example.Cadastro_Avaliacao.service;

import java.util.List;

import com.example.Cadastro_Avaliacao.model.entity.Formulario;

public interface FormularioService {

	Formulario salvar(Formulario formulario);
	Formulario atualizar(Formulario formulario);
	void deletar(Formulario formulario);
	void validarCpf(String cpf);
	void validar(Formulario formulario);
	List <Formulario> buscar(Formulario formularioFiltro);
}

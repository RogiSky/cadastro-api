package com.example.Cadastro_Avaliacao.service;

import com.example.Cadastro_Avaliacao.model.entity.Usuario;

public interface UsuarioService {

	Usuario autenticar(String email, String senha);
	Usuario salvarUsuario(Usuario usario);
	
	void validarEmail(String email);
}


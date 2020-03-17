package com.example.Cadastro_Avaliacao.service.implementation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Cadastro_Avaliacao.exception.ErroAutenticacao;
import com.example.Cadastro_Avaliacao.exception.RegraNegocioException;
import com.example.Cadastro_Avaliacao.model.entity.Usuario;
import com.example.Cadastro_Avaliacao.model.repository.UsuarioRepository;
import com.example.Cadastro_Avaliacao.service.UsuarioService;
@Service
public class UsuarioServiceImp implements UsuarioService {

	private UsuarioRepository repository;

	@Autowired
	public UsuarioServiceImp(UsuarioRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public Usuario autenticar(String email, String senha) {

		Optional<Usuario> usuario = repository.findByEmail(email);
		
		if(!usuario.isPresent()) {
			throw new ErroAutenticacao("Email não encontrado.");
		}
		
		if(!usuario.get().getSenha().contentEquals(senha)) {
			throw new ErroAutenticacao("Senha inválida.");
		}
		
		return usuario.get();
	}


	@Override
	@Transactional
	public Usuario salvarUsuario(Usuario usuario) {
		validarEmail(usuario.getEmail());
		return repository.save(usuario);
	}

	@Override
	public void validarEmail(String email) {
		boolean exists = repository.existsByEmail(email);
		if(exists) {
			throw new RegraNegocioException("O email fornecido já está cadastrado.");
			
		}
	}

	
}

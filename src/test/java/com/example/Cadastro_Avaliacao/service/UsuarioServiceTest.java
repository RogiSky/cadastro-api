package com.example.Cadastro_Avaliacao.service;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.Cadastro_Avaliacao.exception.ErroAutenticacao;
import com.example.Cadastro_Avaliacao.exception.RegraNegocioException;
import com.example.Cadastro_Avaliacao.model.entity.Usuario;
import com.example.Cadastro_Avaliacao.model.repository.UsuarioRepository;
import com.example.Cadastro_Avaliacao.service.implementation.UsuarioServiceImp;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class UsuarioServiceTest {

	@SpyBean
	UsuarioServiceImp service;
	
	@MockBean
	UsuarioRepository repository;
	
	private static Usuario criaUsuario() {
		return Usuario.builder()
				.id(1l)
				.nome("teste")
				.email("teste@teste.com")
				.senha("1234")
				.build();
	}
	
	@Test(expected = Test.None.class)
	public void deveAutenticarUsuarioComSucesso() {
		String email = "teste@teste.com";
		String senha = "1234";
		
		Usuario usuario = Usuario.builder().email(email).senha(senha).id(1l).build();
		Mockito.when(repository.findByEmail(email)).thenReturn(Optional.of(usuario));
		
		Usuario result = service.autenticar(email, senha);
		
		Assertions.assertThat(result).isNotNull();
	}
	
	@Test
	public void deveLancarErroEmailNaoEncontrado() {
		Mockito.when(repository.findByEmail(Mockito.anyString())).thenReturn(Optional.empty());		
		Throwable exception = Assertions.catchThrowable(()->service.autenticar("teste@teste.com", "1234"));
		Assertions.assertThat(exception)
			.isInstanceOf(ErroAutenticacao.class)
			.hasMessage("Email não encontrado.");
	}

	@Test
	public void deveLancarErroEmailNaoCoincideComSenha() {
		Usuario usuario = criaUsuario();
		Mockito.when(repository.findByEmail(Mockito.anyString())).thenReturn(Optional.of(usuario));		
		Throwable exception = Assertions.catchThrowable(()->service.autenticar("teste@teste.com", "2345"));		
		Assertions.assertThat(exception)
			.isInstanceOf(ErroAutenticacao.class)
			.hasMessage("Senha inválida.");
	}

	@Test (expected = Test.None.class)
	public void deveValidarEmail() {
		Mockito.when(repository.existsByEmail(Mockito.anyString())).thenReturn(false);
		service.validarEmail("email@email.com");
	}
	
	@Test (expected = RegraNegocioException.class)
	public void shouldThrownErrorInEmailValidation() {
		Mockito.when(repository.existsByEmail(Mockito.anyString())).thenReturn(true);		
		service.validarEmail("email@email.com");
	}
	
	@Test(expected = Test.None.class)
	public void deveSalvarUmUsuario() {
		
		Mockito.doNothing().when(service).validarEmail(Mockito.anyString());
		Usuario usuario = criaUsuario();
		Mockito.when(repository.save(Mockito.any(Usuario.class))).thenReturn(usuario);
		Usuario usuarioSalvo = service.salvarUsuario(new Usuario());
		
		Assertions.assertThat(usuarioSalvo).isNotNull();
		Assertions.assertThat(usuarioSalvo.getId()).isEqualTo(1l);
		Assertions.assertThat(usuarioSalvo.getNome()).isEqualTo("teste");
		Assertions.assertThat(usuarioSalvo.getEmail()).isEqualTo("teste@teste.com");
		Assertions.assertThat(usuarioSalvo.getSenha()).isEqualTo("1234");
	}
	
	@Test(expected = RegraNegocioException.class)
	public void naoDeveSalvarUsuarioComEmailJaCadastrado() {
		Usuario usuario = 	criaUsuario();
		Mockito.doThrow(RegraNegocioException.class).when(service).validarEmail("teste@teste.com");
		service.salvarUsuario(usuario);
		
		//test if method save wasn't used
		Mockito.verify(repository, Mockito.never()).save(usuario);
	}
}

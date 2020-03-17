package com.example.Cadastro_Avaliacao.model.repository;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.Cadastro_Avaliacao.model.entity.Usuario;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class UsuarioRepositoryTest {

	@Autowired
	UsuarioRepository repository;

	@Autowired
	TestEntityManager entityManager;
	
	@Test
	public void verifyReturnTrueForMethodExistsByEmail() {
		//create an object usuario
		Usuario usuario = criaUsuario();
		//save the object
		entityManager.persist(usuario);			
		//test if exists
		boolean result = repository.existsByEmail("teste@teste.com");
		Assertions.assertThat(result).isTrue();
	}
	

	@Test
	public void verifyReturnFalseForMethodExistsByEmai() {
		// the BD is empty, than the existsByEmail should return false
		boolean result = repository.existsByEmail("teste@teste.com");
		Assertions.assertThat(result).isFalse();
	}
	
	@Test
	public void persistirUmUsuarioNoBancoDeDados() {
		Usuario usuario = criaUsuario();
		Usuario usuarioSalvo = repository.save(usuario);
		Assertions.assertThat(usuarioSalvo.getId()).isNotNull();
	}
	
	@Test
	public void deveBuscarUmUsuarioPorEmail() {
		Usuario usuario = criaUsuario();
		entityManager.persist(usuario);
		Optional<Usuario> result = repository.findByEmail("teste@teste.com");
		Assertions.assertThat(result.isPresent()).isTrue();
	}
	
	@Test
	public void deveRetornarVazioQuandoBuscarUmUsuarioNaoCadastrado() {
		Optional<Usuario> result = repository.findByEmail("teste@teste.com");
		Assertions.assertThat(result.isPresent()).isFalse();
	}
	public static Usuario criaUsuario() {
		return Usuario 
				.builder()
				.nome("teste")
				.email("teste@teste.com")
				.senha("1234")
				.build();
	}
}

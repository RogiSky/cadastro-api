package com.example.Cadastro_Avaliacao.model.repository;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.Cadastro_Avaliacao.model.entity.Formulario;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles("test")
public class FormularioRepositoryTest {

	@Autowired
	FormularioRepository repository;
	
	@Autowired
	TestEntityManager entityManager;

	private Formulario criaFormulario() {
		Float pes = (float) 90;
		return Formulario.builder()
				.cpf("12345678999")
				.nome("teste")
				.uf("SP")
				.peso(pes)
				.build();
		
	}
	
	@Test
	public void deveSalvarUmFormulario() {
		Formulario formulario = criaFormulario();
		formulario = repository.save(formulario);
		Assertions.assertThat(formulario.getCpf()).isNotNull();

	}
	
	@Test
	public void deveDeletarUmLancamento() {
		Formulario formulario = criaFormulario();
		entityManager.persist(formulario);
		formulario = entityManager.find(Formulario.class, formulario.getCpf());
		repository.delete(formulario);
		Formulario formApagado = entityManager.find(Formulario.class, formulario.getCpf());
		Assertions.assertThat(formApagado).isNull();
	}
}

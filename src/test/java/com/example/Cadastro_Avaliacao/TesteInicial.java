package com.example.Cadastro_Avaliacao;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
//import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.Cadastro_Avaliacao.model.repository.FormularioRepository;


@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
//@DataJpaTest

public class TesteInicial {

	//@Autowired
	FormularioRepository repository;
	
	@Test
	public void runTest() {
		Assertions.assertThat(true).isNotNull();
	}
}

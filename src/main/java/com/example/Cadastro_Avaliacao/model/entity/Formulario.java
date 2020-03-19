package com.example.Cadastro_Avaliacao.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "cadastropessoa", schema ="formulario" )
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Formulario {

	
	@Id
	@Column(name ="cpf")
	private String cpf;
	@Column(name="nome")
	private String nome;
	@Column(name="uf")
	private String uf;
	@Column(name="peso")
	private Float peso;
//Data de nascimento
	@Column(name ="data_nascimento")
	private Date dataNascimento;
	
}

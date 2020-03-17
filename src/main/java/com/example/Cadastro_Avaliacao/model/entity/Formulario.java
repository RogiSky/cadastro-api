package com.example.Cadastro_Avaliacao.model.entity;

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
	//Data de nascimento
	@Column(name="diaNascimento")
	private Integer diaNascimento;
	@Column(name="mesNascimento")
	private Integer mesNascimento;
	@Column(name="anoNascimento")
	private Integer anoNascimento;
	@Column(name="peso")
	private float peso;
	@Column(name="UF")
	private String UF;

}

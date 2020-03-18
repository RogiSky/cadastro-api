package com.example.Cadastro_Avaliacao.api.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
@Setter
@Getter
public class FormularioDTO {
	private String cpf;
	private String nome;
	private String Uf;
	private Float peso;
	private Integer diaNascimento;
	private Integer mesNascimento;
	private Integer anoNascimento;

}

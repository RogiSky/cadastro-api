package com.example.Cadastro_Avaliacao.api.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FormularioDTO {
	private String cpf;
	private String nome; 
	private String Uf;
	private Float peso;
	private Date dataNascimento;

}

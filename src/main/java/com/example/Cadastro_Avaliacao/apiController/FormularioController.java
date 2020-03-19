package com.example.Cadastro_Avaliacao.apiController;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Cadastro_Avaliacao.api.dto.FormularioDTO;
import com.example.Cadastro_Avaliacao.exception.RegraNegocioException;
import com.example.Cadastro_Avaliacao.model.entity.Formulario;
import com.example.Cadastro_Avaliacao.service.FormularioService;
import com.example.Cadastro_Avaliacao.service.UsuarioService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/formularios")
@RequiredArgsConstructor
public class FormularioController {
	
	private FormularioService service;
		
	@Autowired
	public FormularioController(FormularioService service) {
		this.service = service;
	}

	@GetMapping
	public ResponseEntity buscar(
			@RequestParam(value = "cpf", required = false) String cpf,
			@RequestParam(value = "nome", required = false) String nome,
			@RequestParam(value = "uf", required = false) String uf,
			@RequestParam(value = "peso", required = false) Float peso,
			@RequestParam(value = "dataNascimento", required = false) Date dataNascimento
			) {
		Formulario formularioFiltro = new Formulario();
		formularioFiltro.setCpf(cpf);
		formularioFiltro.setNome(nome);
		formularioFiltro.setUf(uf);
		formularioFiltro.setPeso(peso);
		formularioFiltro.setDataNascimento(dataNascimento);
		
		List<Formulario> formularios = service.buscar(formularioFiltro);
		return ResponseEntity.ok(formularios);
	}
	
	@PostMapping("/salvar")
	public ResponseEntity salvar(@RequestBody FormularioDTO dto) {
		
		try {
			Formulario entidade = converter(dto);
			entidade = service.salvar(entidade);
			return new ResponseEntity(entidade,HttpStatus.CREATED);			
		}catch(RegraNegocioException e) {
			//
			return ResponseEntity.badRequest().body(e.getMessage());
			}
	}

	@PutMapping("{cpf}")
	public ResponseEntity atualizar(@PathVariable("cpf") String cpf, @RequestBody FormularioDTO dto) {
		return service.selecionaPorCpf(cpf)
				.map(entity -> {
				try {
					Formulario formulario = converter(dto);
					formulario.setCpf(entity.getCpf());
					service.atualizar(formulario);
					return new ResponseEntity(formulario,HttpStatus.OK);
					}catch(RegraNegocioException e) {
						return ResponseEntity.badRequest().body(e.getMessage());
						}
			}).orElseGet( () -> 
				new ResponseEntity("CPF não encontrado na base de daods.", HttpStatus.BAD_REQUEST));
	}
	
	@DeleteMapping("{cpf}")
	public ResponseEntity deletar(@PathVariable("cpf") String cpf) {
		
		return service.selecionaPorCpf(cpf)
				.map(entity -> {
					service.deletar(entity);
					return new ResponseEntity(HttpStatus.NO_CONTENT);
				}).orElseGet( () ->
						new ResponseEntity("CPF não encontrado na base de dados.", HttpStatus.BAD_REQUEST));
		}
	
	private Formulario converter(FormularioDTO dto) {
		Formulario formulario = new Formulario();
		formulario.setCpf(dto.getCpf());
		formulario.setNome(dto.getNome());
		formulario.setUf(dto.getUf());
		formulario.setPeso(dto.getPeso());			
		formulario.setDataNascimento(dto.getDataNascimento());
		return formulario;
	}
	
	@GetMapping("{uf}/total_por_estado")
	public ResponseEntity totalCadastroPorEstado(@PathVariable("uf") String uf) {
		Integer totalCadastrado = service.obterTotalPorEstado(uf);
		return ResponseEntity.ok(totalCadastrado);
	}
	
	
}

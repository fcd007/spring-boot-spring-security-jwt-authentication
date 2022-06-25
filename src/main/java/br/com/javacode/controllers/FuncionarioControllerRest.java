package br.com.javacode.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.javacode.dto.FuncionarioDTOListV1;
import br.com.javacode.entity.Funcionario;
import br.com.javacode.exception.ResourceNotFoundException;
import br.com.javacode.repository.FuncionarioRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1")
public class FuncionarioControllerRest {

	@Autowired
	private FuncionarioRepository funcionarioRepository;

	// listar funcionarios
	@GetMapping("/funcionarios/listar")
	public List<Funcionario> listarFuncionarios() {
		return funcionarioRepository.findAll();
	}

	// criando um novo funcionario
	@PostMapping("/funcionarios/criar")
	public Funcionario criarFuncionario(@RequestBody Funcionario funcionario) {
		Date dataCriacaoEntidade = new Date();
		return funcionarioRepository.save(funcionario);
	}

	// criando uma lista de funcionarios
	@PostMapping("/funcionarios/criar-lista")
	public void salvarListaAll(@RequestBody FuncionarioDTOListV1<Funcionario> funcionarios) {
		 List<Funcionario> listaFuncionarios = new ArrayList<>();
		 Date dataCriacaoEntidade = new Date();
		 listaFuncionarios = funcionarios.getFuncionarios();
		 
		 for (Funcionario funcionario : listaFuncionarios) {
			
		}
		 
		funcionarioRepository.saveAll(listaFuncionarios);
	}

	// busca por id do funcionario
	@GetMapping("/funcionarios/{id}")
	public ResponseEntity<Funcionario> buscarFuncionarioById(@PathVariable Long id) {
		Funcionario funcionario = funcionarioRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Funcionario não encontrado pelo ID"));

		return ResponseEntity.ok(funcionario);
	}

	// update funcionario
	@PutMapping("/funcionarios/{id}")
	public ResponseEntity<Funcionario> updateFuncionario(@PathVariable Long id, @RequestBody Funcionario funcionarioDetalhes) {
		Date dataUpateEntidade = new Date();
		Funcionario funcionario = funcionarioRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Funcionario não pode ser atualizado, funcionário não existe"));

		funcionario.setNome(funcionarioDetalhes.getNome());
		funcionario.setSobrenome(funcionarioDetalhes.getSobrenome());
		funcionario.setEmailId(funcionarioDetalhes.getEmailId());

		Funcionario updateFuncionario = funcionarioRepository.save(funcionario);

		return ResponseEntity.ok(updateFuncionario);
	}

	// deletar funcionario
	@DeleteMapping("funcionarios/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteFuncionario(@PathVariable Long id) {
		Funcionario funcionario = funcionarioRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Funcionario não pode ser deletado, funcionario não encontrado pelo ID"));
		
		Date dataUpateEntidade = new Date();
		//para deletar o funcionário - apenas deixamos ele como inativo - I - status
		funcionarioRepository.delete(funcionario);
		Map<String, Boolean> response = new HashMap<>();
		response.put("Deletado com sucesso!", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}

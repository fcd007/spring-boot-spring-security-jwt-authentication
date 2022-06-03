package br.com.javacode.dto;

import java.util.ArrayList;
import java.util.List;

public class FuncionarioDTOListV1<T> {

	private List<T> funcionarios;

	public FuncionarioDTOListV1(List<T> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public FuncionarioDTOListV1() {
		funcionarios = new ArrayList<>();
	}

	public List<T> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<T> funcionarios) {
		this.funcionarios = funcionarios;
	}

}

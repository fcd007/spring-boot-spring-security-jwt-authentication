package br.com.javacode.enuns;

public enum FuncionarioStatusEnum {

	A("Ativo"), I("Inativo"), P("Pendente");

	private final String nome;

	private FuncionarioStatusEnum(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

}

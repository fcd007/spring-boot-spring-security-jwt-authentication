package br.com.javacode.enuns;

public enum GeneroEnum {

	H("Homem"), M("Mulher"), O("Outro"), N("Não deseja responder");

	private final String nome;

	private GeneroEnum(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}
}

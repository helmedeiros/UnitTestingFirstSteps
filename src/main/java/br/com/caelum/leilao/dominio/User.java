package br.com.caelum.leilao.dominio;

/**
 * Represents the User.
 */
public class User {

	private int id;
	private String nome;
	
	public User(String nome) {
		this(0, nome);
	}

	public User(int id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public int getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}
	
	
	
}

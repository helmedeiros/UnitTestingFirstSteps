package br.com.caelum.auction.dominio;

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

	public int getId() { return id; }

	public String getNome() { return nome; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!nome.equals(user.nome)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return nome.hashCode();
    }
}

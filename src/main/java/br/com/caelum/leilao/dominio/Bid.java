package br.com.caelum.leilao.dominio;

/**
 * Represents a Auction bid.
 */
public class Bid {

	private User user;
	private double amount;
	
	public Bid(User user, double amount) {
		this.user = user;
		this.amount = amount;
	}

	public User getUser() {
		return user;
	}

	public double getAmount() {
		return amount;
	}
	
	
	
}

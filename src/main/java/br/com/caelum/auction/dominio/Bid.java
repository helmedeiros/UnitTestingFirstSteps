package br.com.caelum.auction.dominio;

/**
 * Represents a Auction bid.
 */
public class Bid {

	private User user;
	private double amount;
	
	public Bid(User user, double amount) {
        if(amount <= 0) throw new IllegalArgumentException("Zero isn't an acceptable amount.");

		this.user = user;
		this.amount = amount;
	}

	public User getUser() { return user; }

	public double getAmount() { return amount; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bid bid = (Bid) o;

        if (Double.compare(bid.amount, amount) != 0) return false;
        if (!user.equals(bid.user)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = user.hashCode();
        temp = Double.doubleToLongBits(amount);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}

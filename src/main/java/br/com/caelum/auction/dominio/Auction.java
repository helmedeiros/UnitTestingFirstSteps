package br.com.caelum.auction.dominio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

/**
 * Represents the Auction.
 */
public class Auction {

	private String description;
	private List<Bid> bids;

   public Auction(String description) {
		this.description = description;
		this.bids = new ArrayList<Bid>();
	}

    /**
     * Records the bid following business rules.
     * @param bid - The {@link Bid} to be evaluated and recorded following the rules.
     */
    public void take(Bid bid) {
        if(bids.isEmpty() || (!lastBidWasMadeBy(bid.getUser()) && exceedsMaximumBids(bid)))
            bids.add(bid);
	}

    private boolean exceedsMaximumBids(Bid bid) {
        return getTotalBidsOf(bid.getUser()) < 5;
    }

    private boolean lastBidWasMadeBy(User user) {
        return getLastBid().getUser().equals(user);
    }

    private int getTotalBidsOf(final User user) {
        int totalOfBidsOfUser = 0;
        for (Bid actualBid : bids) {
            if(actualBid.getUser().equals(user)) totalOfBidsOfUser++;
        }
        return totalOfBidsOfUser;
    }

    private Bid getLastBid() {
        return bids.get(bids.size() - 1);
    }

    public String getDescription() {
		return description;
	}

	public List<Bid> getBids() {
		return Collections.unmodifiableList(bids);
	}

    public void doubleBid(final User user) {
        final Bid lastUserBid = takeAnyBid(user);

        if(lastUserBid != null) take(doubleThis(lastUserBid));
    }

    private Bid doubleThis(final Bid lastUserBid) {
        return new Bid(lastUserBid.getUser(), lastUserBid.getAmount()*2);
    }

    /**
     * Search the last Bid take by the given {@link User} and return it.
     * @param user - The {@link User} for whom is wanted to search bids.
     * @return The {@link Bid} for the given {@link User} when it was found. Elsewhere null.
     */
    private Bid takeAnyBid(final User user) {
        ListIterator listIterator = bids.listIterator(bids.size());

        while (listIterator.hasPrevious()) {
            final Bid lastUserBid = (Bid) listIterator.previous();

            if(lastUserBid.getUser().equals(user)) return lastUserBid;
        }

        return null;
    }
}

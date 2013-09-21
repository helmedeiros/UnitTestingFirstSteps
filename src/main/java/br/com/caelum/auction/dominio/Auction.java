package br.com.caelum.auction.dominio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

	
	
}

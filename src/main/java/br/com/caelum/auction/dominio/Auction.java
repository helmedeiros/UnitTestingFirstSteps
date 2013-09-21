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
	
	public void take(Bid bid) {
        if(bids.isEmpty() || (!getLastBid().getUser().equals(bid.getUser())))
            bids.add(bid);
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

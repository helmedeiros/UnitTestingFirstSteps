package br.com.caelum.auction.fixture;

import br.com.caelum.auction.dominio.Auction;
import br.com.caelum.auction.dominio.Bid;
import br.com.caelum.auction.dominio.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Builder for {@link Auction} fixture.
 * User: helmedeiros
 * Date: 9/23/13
 * Time: 9:21 PM
 */
public class AuctionBuilder {
    private static Auction auction;

    public static class Builder {
        public final String description;
        public List<Bid> bids;

        public Builder(String description) {
            this.description = description;
            this.bids = new ArrayList<Bid>();
        }

        public Builder bid(final User user, final double amount){
            bids.add(new Bid(user, amount)); return this;
        }

        public Auction build(){
            return create(this);
        }
    }

    private static Auction create(Builder builder) {
        auction = new Auction(builder.description);
        for(Bid bid : builder.bids){ auction.take(bid); }
        return auction;
    }
}

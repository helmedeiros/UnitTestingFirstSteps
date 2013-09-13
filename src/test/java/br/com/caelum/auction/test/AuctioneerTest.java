package br.com.caelum.auction.test;

import br.com.caelum.auction.dominio.Auction;
import br.com.caelum.auction.dominio.Bid;
import br.com.caelum.auction.dominio.User;
import br.com.caelum.auction.servico.Auctioneer;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Unit test of {@link Auctioneer}
 */
public class AuctioneerTest {

    private User john;
    private User harry;
    private User bill;

    @Test public void testGreaterBidIncreasingSequence() throws Exception {
        createValidUsers();

        final double initialBidAmount = 300.0;
        final double greaterBid = initialBidAmount + 200.0;

        final Auction auction = createAuctionWith("New Playstation 3",
                new Bid(john, initialBidAmount),
                new Bid(harry, initialBidAmount + 100.0),
                new Bid(bill, greaterBid));

        Auctioneer auctioneer = new Auctioneer();
        auctioneer.evaluate(auction);

        assertEquals("the last bid is the greater in this case.", greaterBid, auctioneer.getGreaterBid(), Double.MIN_VALUE);
    }

    @Test public void testLowerBidInSequence() throws Exception {
        createValidUsers();

        final double lowerBid = 100.0;

        Auction auction = createAuctionWith("Old Playstation 3",
                new Bid(john, lowerBid),
                new Bid(harry, lowerBid + 10),
                new Bid(bill, lowerBid + 11));

        Auctioneer auctioneer = new Auctioneer();
        auctioneer.evaluate(auction);

        assertEquals("the first bid is the lower in this case", lowerBid, auctioneer.getLowerBid(), Double.MIN_VALUE);
    }

    /**
     * Create a set of valid {@link User}.
     */
    private void createValidUsers() {
        john = new User("John");
        harry = new User("Harry");
        bill = new User("Bill");
    }

    /**
     * Creates an {@link Auction} based on the given {@link Bid}.
     * @param description - The {@link String} that describes a new {@link Auction}.
     * @param bids - The {@link Bid}s to be set into {@link Auction}.
     * @return The new {@link Auction} to the given {@link Bid}s.
     */
    private Auction createAuctionWith(final String description, final Bid... bids) {
        Auction auction = new Auction(description);

        for (Bid bid : bids) { auction.take(bid); }

        return auction;
    }
}

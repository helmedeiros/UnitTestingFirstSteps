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

    public static final String NEW_PLAYSTATION_3 = "New Playstation 3";
    public static final double DELTA = 0.00001;
    public static final double LOWER_EXPECTED_AMOUNT = 100.0;
    public static final double GREATER_EXPECTED_AMOUNT = 600.0;
    private User john;
    private User harry;
    private User bill;


    @Test public void testEvaluateBidIncreasingSequence() throws Exception {
        createValidUsers();

        final Auction auction = createAuctionWith(NEW_PLAYSTATION_3,
                new Bid(john, LOWER_EXPECTED_AMOUNT),
                new Bid(harry, 200.0),
                new Bid(bill, GREATER_EXPECTED_AMOUNT));

        Auctioneer auctioneer = new Auctioneer();
        auctioneer.evaluate(auction);

        assertEquals(GREATER_EXPECTED_AMOUNT, auctioneer.getGreaterBid(), DELTA);
        assertEquals(LOWER_EXPECTED_AMOUNT, auctioneer.getLowerBid(), DELTA);
    }

    @Test public void testEvaluateBidDecreasingSequence() throws Exception {
        createValidUsers();

        final  Auction auction = createAuctionWith(NEW_PLAYSTATION_3,
                new Bid(john, GREATER_EXPECTED_AMOUNT),
                new Bid(harry, 200.0),
                new Bid(bill, LOWER_EXPECTED_AMOUNT));

        Auctioneer auctioneer = new Auctioneer();
        auctioneer.evaluate(auction);

        assertEquals(GREATER_EXPECTED_AMOUNT, auctioneer.getGreaterBid(), DELTA);
        assertEquals(LOWER_EXPECTED_AMOUNT, auctioneer.getLowerBid(), DELTA);
    }

    @Test public void testEvaluateBidPyramidSequence() throws Exception {
        createValidUsers();

        final  Auction auction = createAuctionWith(NEW_PLAYSTATION_3,
                new Bid(john, GREATER_EXPECTED_AMOUNT),
                new Bid(harry, 200.0),
                new Bid(bill, LOWER_EXPECTED_AMOUNT),
                new Bid(harry, 400.0),
                new Bid(john, 500.0));

        Auctioneer auctioneer = new Auctioneer();
        auctioneer.evaluate(auction);

        assertEquals(GREATER_EXPECTED_AMOUNT, auctioneer.getGreaterBid(), DELTA);
        assertEquals(LOWER_EXPECTED_AMOUNT, auctioneer.getLowerBid(), DELTA);
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

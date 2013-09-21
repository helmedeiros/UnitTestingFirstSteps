package br.com.caelum.auction.dominio;

import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Unit test of {@link Auction}
 * User: helmedeiros
 * Date: 9/21/13
 * Time: 4:12 PM
 */
public class AuctionTest {

    public static final User VALID_USER = new User("John");
    public static final Bid VALID_BID = new Bid(VALID_USER, 1000.0);
    public static final String ANY_VALID_AUCTION_NAME = "OLD PS2";
    public static final double DELTA = 0.0001;

    @Test public void shouldAcceptOneBidAuction() throws Exception {
        final Auction auction = new Auction(ANY_VALID_AUCTION_NAME);
        auction.take(VALID_BID);

        assertEquals(1, auction.getBids().size());
        assertEquals(VALID_BID, auction.getBids().get(0));
    }

    @Test public void shouldAcceptMultipleBids() throws Exception {
        Bid firstBid = new Bid(new User("Bill"), 2000.0);
        Bid secondBid = new Bid(new User("Mike"), 2000.0);

        List<Bid> allBids = new ArrayList<Bid>();
        allBids.add(VALID_BID);
        allBids.add(firstBid);
        allBids.add(secondBid);

        final Auction auction = createAuctionWithBids(ANY_VALID_AUCTION_NAME, allBids);

        assertThatAuctionHas(auction, allBids);
    }

    /** should not accept two bids from the same user in sequence. */
    @Test public void shouldNotAcceptTwoBidsFromTheSameUserInSequence() throws Exception {

        double firstBidAmount = 1000.0;
        final Bid firstBid = new Bid(VALID_USER, firstBidAmount);

        final Auction auction = new Auction(ANY_VALID_AUCTION_NAME);
        auction.take(firstBid);
        auction.take(new Bid(VALID_USER, 2000.0));
        auction.take(new Bid(VALID_USER, 3000.0));

        assertEquals(1, auction.getBids().size());
        assertEquals(firstBid, auction.getBids().get(0));
        assertEquals(firstBidAmount, auction.getBids().get(0).getAmount(), DELTA);
    }

    private void assertThatAuctionHas(Auction auction, List<Bid> allBids) {
        assertEquals(allBids.size(), auction.getBids().size());
        int index = 0;

        for (Bid bid : allBids) {
            assertEquals(bid, auction.getBids().get(index));
            index++;
        }
    }

    private Auction createAuctionWithBids(String auctionName, List<Bid> bidsList) {
        final Auction auction = new Auction(auctionName);
        for (Bid bid : bidsList) {
            auction.take(bid);
        }
        return auction;
    }
}

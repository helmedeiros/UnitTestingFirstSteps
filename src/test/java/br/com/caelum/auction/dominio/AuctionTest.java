package br.com.caelum.auction.dominio;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static br.com.caelum.auction.matcher.AuctionMatcher.takeTheBid;
import static org.hamcrest.MatcherAssert.assertThat;
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
    public static final User ANOTHER_DIFFERENT_BUT_VALID_USER = new User("Bill");

    @Test public void shouldAcceptOneBidAuction() throws Exception {
        final Auction auction = new Auction(ANY_VALID_AUCTION_NAME);
        auction.take(VALID_BID);

        assertThat(auction, takeTheBid(VALID_BID));

        assertEquals(1, auction.getBids().size());
        assertEquals(VALID_BID, auction.getBids().get(0));
    }

    @Test public void shouldAcceptMultipleBids() throws Exception {
        Bid firstBid = new Bid(ANOTHER_DIFFERENT_BUT_VALID_USER, 2000.0);
        Bid secondBid = new Bid(new User("Mike"), 2000.0);

        List<Bid> allBids = new ArrayList<Bid>();
        allBids.add(VALID_BID);
        allBids.add(firstBid);
        allBids.add(secondBid);

        final Auction auction = createAuctionWithBids(ANY_VALID_AUCTION_NAME, allBids);

        assertThatAuctionHas(auction, allBids);
    }

    /** should not accept two or more bids from the same user in sequence. */
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

    /** a same user can't do more than 5 bits in a same auction */
    @Test public void shouldNotAcceptMoreThanFiveBidsFromASameUser() throws Exception {
        final List<Bid> validBids = new ArrayList<Bid>();
        validBids.add(new Bid(VALID_USER, 10.0));
        validBids.add(new Bid(ANOTHER_DIFFERENT_BUT_VALID_USER, 11.0));
        validBids.add(new Bid(VALID_USER, 12.0));
        validBids.add(new Bid(ANOTHER_DIFFERENT_BUT_VALID_USER, 13.0));
        validBids.add(new Bid(VALID_USER, 14.0));
        validBids.add(new Bid(ANOTHER_DIFFERENT_BUT_VALID_USER, 15.0));
        validBids.add(new Bid(VALID_USER, 16.0));
        validBids.add(new Bid(ANOTHER_DIFFERENT_BUT_VALID_USER, 17.0));
        validBids.add(new Bid(VALID_USER, 18.0));
        validBids.add(new Bid(ANOTHER_DIFFERENT_BUT_VALID_USER, 19.0));

        final List<Bid> allBids = new ArrayList<Bid>();
        allBids.addAll(validBids);
        allBids.add(new Bid(VALID_USER, 20.0));
        allBids.add(new Bid(ANOTHER_DIFFERENT_BUT_VALID_USER, 21.0));
        allBids.add(new Bid(VALID_USER, 22.0));
        allBids.add(new Bid(ANOTHER_DIFFERENT_BUT_VALID_USER, 23.0));

        final Auction auction = createAuctionWithBids(ANY_VALID_AUCTION_NAME, allBids);
        assertThatAuctionHas(auction, validBids);
    }

    @Test public void shouldDoubleTheLastBidAmountOfTheGivenUser() throws Exception {
        final double amount = 1000.0;
        final double doubledAmount = amount * 2;

        final Auction auction = new Auction(ANY_VALID_AUCTION_NAME);

        auction.take(new Bid(VALID_USER, amount));
        auction.take(new Bid(ANOTHER_DIFFERENT_BUT_VALID_USER, amount + 1));
        auction.doubleBid(VALID_USER);


        assertEquals(amount, auction.getBids().get(0).getAmount(), DELTA);
        assertEquals(amount + 1, auction.getBids().get(1).getAmount(), DELTA);
        assertEquals(doubledAmount, auction.getBids().get(2).getAmount(), DELTA);
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

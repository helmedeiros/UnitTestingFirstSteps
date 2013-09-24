package br.com.caelum.auction.test;

import br.com.caelum.auction.dominio.Auction;
import br.com.caelum.auction.dominio.User;
import br.com.caelum.auction.fixture.AuctionBuilder;
import br.com.caelum.auction.servico.Auctioneer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

/**
 * Unit test of {@link Auctioneer}
 */
public class AuctioneerTest {

    private Auctioneer auctioneer;
    public static final String NEW_PLAYSTATION_3 = "New Playstation 3";
    public static final double DELTA = 0.00001;
    public static final double LOWER_EXPECTED_AMOUNT = 100.0;
    public static final double GREATER_EXPECTED_AMOUNT = 600.0;
    private User john;
    private User harry;
    private User bill;


    @Before
    public void setUp() throws Exception {
        auctioneer = new Auctioneer();
        System.out.println("initialize test case!");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotBePossibleToEvaluateAnNullAuction() throws Exception {
        auctioneer.evaluate(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotBePossibleToEvaluateAnActionInAbsenceOfBids() throws Exception {
        auctioneer.evaluate(new AuctionBuilder.Builder(NEW_PLAYSTATION_3).build());
    }

    @Test public void testEvaluateBidIncreasingSequence() throws Exception {
        createValidUsers();

        final Auction auction =
                new AuctionBuilder.Builder(NEW_PLAYSTATION_3)
                        .bid(john, LOWER_EXPECTED_AMOUNT)
                        .bid(harry, 200.0)
                        .bid(bill, GREATER_EXPECTED_AMOUNT)
                        .build();

        auctioneer.evaluate(auction);

        final double expectedMedian = (LOWER_EXPECTED_AMOUNT +  200.0 + GREATER_EXPECTED_AMOUNT) / 3;

        assertThat(auctioneer.getGreaterBid(), equalTo(GREATER_EXPECTED_AMOUNT));
        assertThat(auctioneer.getLowerBid(), equalTo(LOWER_EXPECTED_AMOUNT));
        assertThat(auctioneer.getMedianBid(), equalTo(expectedMedian));
    }

    @Test public void testEvaluateBidDecreasingSequence() throws Exception {
        createValidUsers();

        final Auction auction =
                new AuctionBuilder.Builder(NEW_PLAYSTATION_3)
                        .bid(john, GREATER_EXPECTED_AMOUNT)
                        .bid(harry, 200.0)
                        .bid(bill, LOWER_EXPECTED_AMOUNT).build();

        auctioneer.evaluate(auction);

        final double expectedMedian = (LOWER_EXPECTED_AMOUNT +  200.0 + GREATER_EXPECTED_AMOUNT) / 3;

        assertEquals(GREATER_EXPECTED_AMOUNT, auctioneer.getGreaterBid(), DELTA);
        assertEquals(LOWER_EXPECTED_AMOUNT, auctioneer.getLowerBid(), DELTA);
        assertEquals(expectedMedian, auctioneer.getMedianBid(), DELTA);
    }

    @Test public void testEvaluateBidPyramidSequence() throws Exception {
        createValidUsers();

        final Auction auction =
                new AuctionBuilder.Builder(NEW_PLAYSTATION_3)
                        .bid(john, GREATER_EXPECTED_AMOUNT)
                        .bid(harry, 200.0)
                        .bid(bill, LOWER_EXPECTED_AMOUNT)
                        .bid(harry, 400.0)
                        .bid(john, 500.0).build();

        auctioneer.evaluate(auction);

        assertEquals(GREATER_EXPECTED_AMOUNT, auctioneer.getGreaterBid(), DELTA);
        assertEquals(LOWER_EXPECTED_AMOUNT, auctioneer.getLowerBid(), DELTA);
    }

    @Test public void testEvaluateOneBidAuction() throws Exception {
        john = new User("John");

        final Auction auction =
                new AuctionBuilder.Builder(NEW_PLAYSTATION_3)
                        .bid(john, GREATER_EXPECTED_AMOUNT).build();

        auctioneer.evaluate(auction);

        assertEquals(GREATER_EXPECTED_AMOUNT, auctioneer.getGreaterBid(), DELTA);
        assertEquals(GREATER_EXPECTED_AMOUNT, auctioneer.getLowerBid(), DELTA);
    }

    @Test public void testEvaluateBidRandomSequence() throws Exception {
        createValidUsers();

        final Auction auction =
                new AuctionBuilder.Builder(NEW_PLAYSTATION_3)
                        .bid(john, 200)
                        .bid(harry, 450)
                        .bid(john, 120)
                        .bid(bill, 700)
                        .bid(harry, 630)
                        .bid(bill, 230).build();

        auctioneer.evaluate(auction);

        assertEquals(700, auctioneer.getGreaterBid(), DELTA);
        assertEquals(120, auctioneer.getLowerBid(), DELTA);
        assertEquals(388.33, auctioneer.getMedianBid(), 0.01);
    }

    /** An auction with no bid, returns empty list. */
    @Test public void testGetTopThreeBidsFromAuctionWithoutBids() throws Exception {
        final Auction auction = new AuctionBuilder.Builder(NEW_PLAYSTATION_3).build();

        try{
            auctioneer.evaluate(auction);
        }catch (IllegalArgumentException e){}

        final List topThreeBids = auctioneer.getTopThreeBids();

        assertEquals(0, topThreeBids.size());
    }

    /** An auction with 2 bids, should return only the two bids that met. */
    @Test public void testGetTopThreeBidsFromAuctionWithTwoBids() throws Exception {
        createValidUsers();

        final Auction auction =
                new AuctionBuilder.Builder(NEW_PLAYSTATION_3)
                        .bid(john, GREATER_EXPECTED_AMOUNT)
                        .bid(bill, LOWER_EXPECTED_AMOUNT).build();

        auctioneer.evaluate(auction);

        assertEquals(2, auctioneer.getTopThreeBids().size());
        assertEquals(GREATER_EXPECTED_AMOUNT, auctioneer.getTopThreeBids().get(0).getAmount(), DELTA);
        assertEquals(LOWER_EXPECTED_AMOUNT, auctioneer.getTopThreeBids().get(1).getAmount(), DELTA);
    }

    /** An auction with 5 bids must find the three largest. */
    @Test public void testGetTopThreeBidsFromAuctionWithFiveBids() throws Exception {
        createValidUsers();

        final Auction auction =
                new AuctionBuilder.Builder(NEW_PLAYSTATION_3)
                        .bid(john, GREATER_EXPECTED_AMOUNT)
                        .bid(bill, 100)
                        .bid(harry, 120)
                        .bid(john, 130)
                        .bid(bill, LOWER_EXPECTED_AMOUNT).build();

        auctioneer.evaluate(auction);

        assertEquals(3, auctioneer.getTopThreeBids().size());
        assertEquals(GREATER_EXPECTED_AMOUNT, auctioneer.getTopThreeBids().get(0).getAmount(), DELTA);
        assertEquals(130, auctioneer.getTopThreeBids().get(1).getAmount(), DELTA);
        assertEquals(120, auctioneer.getTopThreeBids().get(2).getAmount(), DELTA);
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("end of test case!");
    }

    /**
     * Create a set of valid {@link User}.
     */
    private void createValidUsers() {
        john = new User("John");
        harry = new User("Harry");
        bill = new User("Bill");
    }

}

package br.com.caelum.auction.dominio;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Unit test of {@link Bid}
 * User: helmedeiros
 * Date: 9/20/13
 * Time: 10:51 PM
 */
public class BidTest {
    final User john = new User("John");

    @Test(expected = IllegalArgumentException.class)
    public void bidsWithZeroAmountWereNotAcceptable() throws Exception {
        new Bid(john, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void bidsWithNegativeAmountWereNotAcceptable() throws Exception {
        new Bid(john, -10);
    }

    @Test public void bidsWithSameUserAndBidAmountShouldBeEqual() throws Exception {

        final Bid firstBid = new Bid(john, 1000.0);
        final Bid secondBid = new Bid(john, 1000.0);

        assertEquals(firstBid, secondBid);
    }

}

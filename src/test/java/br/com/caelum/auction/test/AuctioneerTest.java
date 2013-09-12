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

    private User jonh;
    private User harry;
    private User bill;

    @Test public void testGreaterBidInSequence() throws Exception {
        createValidUsers();

        Auction auction = new Auction("New Playstation 3");

        final double initialBidamount = 300.0;
        final double greaterBid = initialBidamount + 200.0;

        auction.take(new Bid(jonh, initialBidamount));
        auction.take(new Bid(harry, initialBidamount + 100.0));
        auction.take(new Bid(bill, greaterBid));

        Auctioneer auctioneer = new Auctioneer();
        auctioneer.evaluate(auction);

        assertEquals("the last bid is the greater in this case.", greaterBid, auctioneer.getGreaterBid(), Double.MIN_VALUE);
    }


    private void createValidUsers() {
        jonh = new User("Jonh");
        harry = new User("Harry");
        bill = new User("Bill");
    }
}

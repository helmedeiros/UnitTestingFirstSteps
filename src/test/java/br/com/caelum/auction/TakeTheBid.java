package br.com.caelum.auction;

import br.com.caelum.auction.dominio.Auction;
import br.com.caelum.auction.dominio.Bid;
import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

/**
 * Matcher for Auction ensuring that there is a bid in there
 * User: helmedeiros
 * Date: 9/24/13
 * Time: 8:13 PM
 */
public class TakeTheBid extends TypeSafeMatcher<Auction> {

    private final Bid bid;

    public TakeTheBid(Bid bid) {
        this.bid = bid;
    }

    @Override
    protected boolean matchesSafely(final Auction auction) {
        if(auction != null && auction.getBids() != null && auction.getBids().size() > 0 && auction.getBids().contains(this.bid))
            return true;

        return false;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("it doesn't have take this bid yet.");
    }

    @Factory
    public static <T> Matcher<Auction> takeTheBid(Bid bid) {
        return new TakeTheBid(bid);
    }
}

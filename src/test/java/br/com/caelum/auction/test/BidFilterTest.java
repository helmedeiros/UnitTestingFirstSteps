package br.com.caelum.auction.test;

import br.com.caelum.auction.servico.BidFilter;
import br.com.caelum.auction.dominio.Bid;
import br.com.caelum.auction.dominio.User;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Unit test of {@link BidFilter}.
 * User: helmedeiros
 * Date: 9/20/13
 * Time: 9:46 PM
 */
public class BidFilterTest {
    final User john = new User("John");
    final BidFilter bidFilter = new BidFilter();

    /** should keep bids between 1000 and 3000 */
    @Test public void shouldKeepBidsBetween1000And3000() throws Exception {
        final int initialBid = 1001;
        final int finalBid = 3000;

        final List<Bid> allBids = createBidsBetween(initialBid, finalBid);

        final List<Bid> filteredBids = bidFilter.filter(allBids);

        assertBidsWereKept(allBids, filteredBids);
    }

    /**
     * Verify if the given {@link List} of {@link Bid}s were kept in the filtered {@link List} of {@link Bid}s.
     * @param allBids - The {@link Bid}s to be checked in the given filter {@link List}.
     * @param filteredBids - The filter {@link List} to be checked.
     */
    private void assertBidsWereKept(final List<Bid> allBids, final List<Bid> filteredBids) {
        assertEquals(allBids.size(), filteredBids.size());

        for (Bid bid : allBids) {
            assertTrue(filteredBids.contains(bid));
        }
    }

    /**
     * Create {@link Bid}s between the initial and final interval.
     * @param initialBid - The initial {@link Bid} of the interval.
     * @param finalBid - The final {@link Bid} of the interval.
     * @return A {@link List} of bids in the interval,
     */
    private List<Bid> createBidsBetween(final int initialBid, final int finalBid) {
        List<Bid> allBids = new ArrayList<Bid>();

        for (int bidAmount = initialBid; bidAmount < finalBid; bidAmount++) {
            allBids.add(new Bid(john, bidAmount));
        }
        return allBids;
    }

}

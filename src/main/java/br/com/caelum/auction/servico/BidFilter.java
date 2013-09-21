package br.com.caelum.auction.servico;

import br.com.caelum.auction.dominio.Bid;

import java.util.ArrayList;
import java.util.List;

/**
 * Helps filtering {@link Bid}s following given rules.
 */
public class BidFilter {

    public List<Bid> filter (final List<Bid> bidsList){
        List<Bid> filteredBidsList = new ArrayList<Bid>();

        for (Bid bid : bidsList) {
            if(bid.getAmount() > 1000 && bid.getAmount() < 3000) filteredBidsList.add(bid);
            else if(bid.getAmount() > 500 && bid.getAmount() < 700) filteredBidsList.add(bid);
            else if(bid.getAmount() > 5000) filteredBidsList.add(bid);
        }

        return filteredBidsList;
    }

}

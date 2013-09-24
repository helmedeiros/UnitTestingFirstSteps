package br.com.caelum.auction.servico;

import br.com.caelum.auction.dominio.Auction;
import br.com.caelum.auction.dominio.Bid;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Represents the Auctioneer of the Auction.
 */
public class Auctioneer {
    private double greaterBid = Double.NEGATIVE_INFINITY;
    private double lowerBid = Double.POSITIVE_INFINITY;
    private double medianBid;
    private List<Bid> topThreeBids = new ArrayList<Bid>();

    /**
     * Given an {@link Auction} all its bids will be evaluated, and the greater will be returned.
     * If the given {@link Auction} doesn't have bids an {@link IllegalArgumentException} will be throw.
     * @param auction - The {@link Auction} to be evaluated.
     */
    public void evaluate(Auction auction) throws IllegalArgumentException{
        if(auction == null || auction.getBids() == null || auction.getBids().size() == 0)
            throw new IllegalArgumentException("It is impossible to evaluate one auction without bids.");

        for (Bid bid : auction.getBids()) {
            if(bid.getAmount() > greaterBid) greaterBid = bid.getAmount();
            if(bid.getAmount() < lowerBid) lowerBid = bid.getAmount();
            medianBid += bid.getAmount();
        }

        medianBid /= auction.getBids().size();

        topThreeBids = evaluateTheTop(3, auction.getBids());
    }

    private List<Bid> evaluateTheTop(int topSize, List<Bid> bids) {
        List<Bid> topBids = new ArrayList<Bid>(bids);
        Collections.sort(topBids, new Comparator<Bid>() {
            public int compare(Bid bid1, Bid bid2) {
                if (bid1.getAmount() < bid2.getAmount()) return 1;
                if (bid1.getAmount() > bid2.getAmount()) return -1;
                return 0;
            }
        });

        topBids = topBids.subList(0, topBids.size() > (topSize-1) ? topSize : topBids.size());

        return topBids;
    }

    public double getGreaterBid() { return greaterBid; }

    public double getLowerBid() { return lowerBid; }

    public double getMedianBid() { return medianBid; }

    public List<Bid> getTopThreeBids() { return topThreeBids; }

}

package br.com.caelum.auction.servico;

import br.com.caelum.auction.dominio.Auction;
import br.com.caelum.auction.dominio.Bid;

/**
 * Represents the Auctioneer of the Auction.
 */
public class Auctioneer {
    private double greaterBid = Double.NEGATIVE_INFINITY;
    private double lowerBid = Double.POSITIVE_INFINITY;
    private double medianBid;

    /**
     * Given an {@link Auction} all its bids will be evaluated, and the greater will be returned.
     * @param auction - The {@link Auction} to be evaluated.
     */
    public void evaluate(Auction auction){

        for (Bid bid : auction.getBids()) {
            if(bid.getAmount() > greaterBid) greaterBid = bid.getAmount();
            if(bid.getAmount() < lowerBid) lowerBid = bid.getAmount();
            medianBid += bid.getAmount();
        }

        medianBid /= auction.getBids().size();
    }

    public double getGreaterBid() { return greaterBid; }

    public double getLowerBid() { return lowerBid; }

    public double getMedianBid() { return medianBid; }

}

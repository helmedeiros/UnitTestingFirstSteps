UnitTestingFirstSteps
=====================

First steps for TDD learners

These project is part of the PM-71 course by Caelum.

Can you see any logical problem? The tests are covering all possible scenarios? FORK IT! AND SOLVE THE PROBLEMS :)

# Problems to solve

Below are all tags, and what is being expected to be done.

### V1.0 - Starting with unit tests
1. Implement the Auctioneer class. This class must have a method evaluates (Auction auction), which holds the highest and lowest value of bids placed in that auction. Then write at least one unit test for this class.

2. Implement in the Auctioneer class a method that returns the average value of bids. Also write test for this.

### V2.0 - Problems to solve

1. Write the test to ensure that the class Evaluator works if the auction has only one Lance. To do so, create an auction bid only (with a value of, for example, 600), and invoking the Auctioneer. To validate, verify that both the highest and the lowest being equal to 600.

2. Test if the Auctioneer understands an Auction whose bids were given in random order. For this, propose and create an auction bid with random values​​, such as 200, 450, 120, 700, 630, 230. Finally, check that the lowest is 120 and the highest is 700.

3. Finally, test the Auctioneer works for bids placed in descending order. Make Bids with values ​​of 400, 300, 200, 100, for example, and ensures that the output tap with input values​​.

4. Implement in the Auctioneer class a method that returns, after the evaluation, the top three bids.  Follow the given rules: An auction with 5 bids must find the three largest; An auction with 2 bids, should return only the two bids that met; An auction with no bid, returns empty list.

5. Implement the BidFilter class. This class must have a method filter (List<Bid> bids), which applies filters among the given list, accepting only bids between 1000 and 3000, or between 500 and 700, or higher than 5000. Than write down necessary tests for it.

### V3.0 - Practicing Test-Driven Development

 1. Using the concepts of TDD, add into the Auction the following rules: An auction shall not accept 2 bids in sequence given by the same user; An auction shall not accept that a single user gives more than 5 bids. In all cases, the Bid should just being ignore.


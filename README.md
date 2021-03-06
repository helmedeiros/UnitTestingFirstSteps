UnitTestingFirstSteps
=====================

First steps for TDD learners

These project is part of the [PM-71](http://www.alura.com.br/course/PM-71) course by [Caelum](http://www.caelum.com.br/)

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

2. Implement the method doubleBid (User user) in Auction class. This method should find the last bid given by this user and create a new bid to double the previous bid. If he has not given any bid yet, not bid should be created. Remember that all existent business rules should still working.

### V4.0 - Taking care of your tests

##### There are many forms to keep your tests clear to be understood and easy to be changed, one of them is keep all duplicated code in one single place. It will save your time later, when a new Object like the Auctioneer begins to have new mandatory fields to be instantiated.

1. Do a refactor in the AuctioneerTest, extracting a method for an Auctioneer creation. Note that the class in focus for unit testing will commonly be instantiated for the execution of each test case. Use the @Before annotation with the previous extracted method to made this default creation before each test case start processing.

2. Test cases were expected to be idempotent, ie when executed once or several times the results should be the same. Use the @After to understand how it works, and how it could work in pair with the @Before consolidating idempotence.

3. Sometimes create a fixture(fixed state objects used as a baseline for running tests) could be repetitive and tough. In many of this cases a Build will be exactly what we need. In test we call that Test Data Builders. Implement the AuctionTestDataBuilder, with description as mandatory and bid as an increment method build.

### V5.0 - Testing Exceptions

1. Any business logic has its own percentage of restrictions, so lets start working out our exceptions. Make it clear, that is impossible an auctioneer evaluates Auctions that doesn't have Bids. Write the tests first to solve this problem.

2. Having the null contracts theory in mind, have the class Bid drop an IllegalArgumentException if the amount of the proposed bid is less than or equal to zero. Write the test for this functionality, using @ Test (expected = ...).

### V6.0 - Using Hamcrest

##### Hamcrest is a framework for writing matcher objects allowing 'match' rules to be defined declaratively. There are a number of situations where matchers are invaluable, let's practice some of them.

1. Start by rewriting all simple test we did in the AuctioneerTest, changing the used JUnit's assertEquals methods, for Hamcrest's assertThat construct and the standard set of matchers.

2. Hamcrest allow us to create and manage our own matchers. Create a matcher for Auction ensuring that a given Bid is present. So we can test like: Bid bid = new Bid(new User("John"), 2000.0); assertThat(auction, takeThisBid(bid));

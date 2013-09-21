package br.com.caelum.auction.dominio;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Unit test of {@link User}
 * User: helmedeiros
 * Date: 9/20/13
 * Time: 11:01 PM
 */
public class UserTest {

    @Test public void userWithSameNameShouldBeTheSame() throws Exception {
        final User john = new User("John");
        final User john1 = new User("John");

        assertEquals(john, john1);
    }
}

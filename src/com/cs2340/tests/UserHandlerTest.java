package com.cs2340.tests;

import com.cs2340.api.UserHandler;
import com.cs2340.model.Profile;
import static org.junit.Assert.*;

/**
 * Created by Jacob Wartofsky on 11/15/16.
 */
public class UserHandlerTest {



    //Test to see if two profiles are equal
    @org.junit.Test
    public void testGetProfile() throws Exception {
        UserHandler use = new UserHandler();
        //Create a profile
        Profile p = new Profile("Jacob", "1337 lala land", "foo@notreal.com");
        String pcookie = "cookie";
        use.postProfile(p, pcookie);
        //Get the profile
        assertEquals("Jacob", use.getProfile(pcookie).getData().getName());
    }


    //Test to see if two profiles are not equal
    @org.junit.Test
    public void testGetProfileNotEqual() throws Exception {
        UserHandler use = new UserHandler();
        //Create a profile
        Profile p = new Profile("Jacob", "1337 lala land", "foo@notreal.com");
        String pcookie = "cookie";
        use.postProfile(p, pcookie);
        //Get the profile
        assertNotEquals("Fred", use.getProfile(pcookie).getData().getName());
    }

}

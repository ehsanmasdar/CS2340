package com.cs2340.tests;

import com.cs2340.api.UserHandler;
import com.cs2340.model.Profile;
import static org.junit.Assert.*;

/**
 * Created by Jacob Wartofsky on 11/15/16.
 */
public class UserHandlerTest {

    Profile p = new Profile("Jacob", "1337 lala land", "foo@notreal.com");
    String pcookie = "cookie";

    //Test to see if two profiles are equal
    @org.junit.Test
    public void testGetProfile() throws Exception {
        //Create a profile
        UserHandler.postProfile(p, pcookie);
        //Get the profile
        assertEquals("Jacob", UserHandler.getProfile(pcookie).getData().getName());
    }


    //Test to see if two profiles are not equal
    @org.junit.Test
    public void testGetProfileNotEqual() throws Exception {
        //Create a profile
        UserHandler.postProfile(p, pcookie);
        //Get the profile
        assertNotEquals("Fred", UserHandler.getProfile(pcookie).getData().getName());
    }

}

package com.cs2340.tests;

import static org.junit.Assert.*;

import com.cs2340.api.UserHandler;
import com.cs2340.model.User;
import com.cs2340.model.Response;
import org.junit.Test;

/**
 * Created by justinduan on 11/15/16.
 */
public class LoginTests {
    @Test
    public void UsernameFail() {
        User jd = new User("JD", "GATECH");
        User jayd = new User("jaydee", "GATECH");
        UserHandler use = new UserHandler();
        Response<String> expected = new Response<>(0, "failed", null);
        use.postRegister(jd);
        assertEquals(expected.getSuccess(), use.postLogin(jayd).getSuccess());
    }

    @Test
    public void PasswordFail() {
        User jd = new User("JD", "GATECH");
        User jayd = new User("JD", "UGA");
        UserHandler use = new UserHandler();
        Response<String> expected = new Response<>(0, "failed", null);
        UserHandler.postRegister(jd);
        assertEquals(expected.getSuccess(), use.postLogin(jayd).getSuccess());
    }

    @Test
    public void UserPassFail() {
        User jd = new User("JD", "GATECH");
        User jayd = new User("jaydee", "UGA");
        UserHandler use = new UserHandler();
        Response<String> expected = new Response<>(0, "failed", null);
        UserHandler.postRegister(jd);
        assertEquals(expected.getSuccess(), use.postLogin(jayd).getSuccess());
    }

    @Test
    public void Passed() {
        User jd = new User("JD", "GATECH");
        UserHandler use = new UserHandler();
        Response<String> expected = new Response<>(1, "success", null);
        UserHandler.postRegister(jd);
        assertEquals(expected.getSuccess(), use.postLogin(jd).getSuccess());
    }

}

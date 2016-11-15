package com.cs2340.controller;

import static org.junit.Assert.*;

/**
 * Register Controller Test
 */
public class RegisterControllerTest {
    @org.junit.Test
    public void validatePasswordCorrect() throws Exception {
        assertEquals(null, RegisterController.validatePassword("test","test"));

    }
    @org.junit.Test
    public void validatePasswordShort() throws Exception {
        assertEquals("Password must be 4 characters or longer", RegisterController.validatePassword("t","t"));
    }
    @org.junit.Test
    public void validatePasswordNoMatchShort() throws Exception {
        assertEquals("Passwords entered must match", RegisterController.validatePassword("a", "b"));

    }
    @org.junit.Test
    public void validatePasswordNoMatch() throws Exception {
        assertEquals("Passwords entered must match", RegisterController.validatePassword("hello", "world"));
    }

}

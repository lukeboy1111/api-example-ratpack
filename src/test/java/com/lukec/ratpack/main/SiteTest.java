package com.lukec.ratpack.main;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.lukec.ratpack.Application;

import ratpack.http.client.ReceivedResponse;
import ratpack.test.MainClassApplicationUnderTest;

@RunWith(JUnit4.class)
public class SiteTest {

    String lineSeparator = System.getProperty("line.separator");

    MainClassApplicationUnderTest aut = new MainClassApplicationUnderTest(Application.class);

    @After
    public void tearDown() {
	aut.close();
    }

    @Test
    public void staticHandler() {
	// assertEquals("text asset\n", get("static/test.txt"));
	final ReceivedResponse response = aut.getHttpClient().get();
	assertEquals(404, response.getStatusCode());
	assertThat(response.getBody().getText(), CoreMatchers.containsString("{ message: \"Unauthorised\" }"));
    }

    @Test
    public void staticHandlerTest() {
	final ReceivedResponse response = aut.getHttpClient().get("static/test.txt");
	assertEquals(200, response.getStatusCode());
	assertThat(response.getBody().getText(), CoreMatchers.containsString("text asset"));
    }

    @Test
    public void checkNoTokenForBalance() {
	final ReceivedResponse response = aut.getHttpClient().get("balance");
	assertEquals(500, response.getStatusCode());
	assertThat(response.getBody().getText(), CoreMatchers.containsString("Token Not Present"));
    }

    @Test
    public void checkNoTokenForTransactions() {
	final ReceivedResponse response = aut.getHttpClient().get("transactions");
	assertEquals(500, response.getStatusCode());
	assertThat(response.getBody().getText(), CoreMatchers.containsString("Token Not Present"));
    }

}

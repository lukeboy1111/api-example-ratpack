package com.lukec.ratpack.main;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.lukec.ratpack.Application;
import com.lukec.ratpack.bo.Transaction;

import ratpack.http.client.ReceivedResponse;
import ratpack.jackson.Jackson;
import ratpack.test.MainClassApplicationUnderTest;
import ratpack.test.embed.EmbeddedApp;

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

    @Test
    public void checkNoTokenForSpend() throws Exception {
	Date theDate =new SimpleDateFormat("yyyy-MM-dd").parse("2020-01-01 00:00:00");
	EmbeddedApp
		.of(s -> s.handlers(chain -> chain.get(
			ctx -> ctx.render(Jackson.json(new Transaction(new Date(), "test", BigDecimal.TEN, "GBP"))))))
		.test(httpClient -> {
		    ReceivedResponse response = httpClient.get();
		    assertThat(response.getBody().getText(), CoreMatchers.containsString("\"description\":\"test\""));
		    assertThat(response.getBody().getText(), CoreMatchers.containsString("\"amount\":10"));
		    assertThat(response.getBody().getText(), CoreMatchers.containsString("\"currency\":\"GBP\""));
		    assertEquals("application/json", response.getBody().getContentType().getType());
		});

    }

}

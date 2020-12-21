package com.lukec.ratpack.main;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.lukec.ratpack.Application;

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
    assertEquals("text asset\n", get("static/test.txt"));
  }


  private String get(String path) {
    return aut.getHttpClient().getText(path);
  }

}

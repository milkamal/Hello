package com.twc.hnav.WookieTest;


import java.util.concurrent.TimeUnit;

import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.junit.*;

import static org.junit.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;


public class AdminLoginInvalidUserTest extends TestCase {
  private WebDriver driver;
  private String baseUrl;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://localhost:8080";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testAdminLoginInvalidUser() throws Exception {
    driver.get(baseUrl + "/wookie/demo/");
    assertTrue(isElementPresent(By.id("login2")));
    try {
      assertEquals("Admin Login |", driver.findElement(By.id("login2")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    driver.findElement(By.id("login2")).click();
    assertEquals("Webapps Admin Login", driver.findElement(By.id("heading2")).getText());
    assertTrue(isElementPresent(By.id("username")));
    try {
      assertTrue(isElementPresent(By.id("password")));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    driver.findElement(By.id("username")).sendKeys("anonymous");
    driver.findElement(By.id("password")).sendKeys("nouser");
    driver.findElement(By.id("submit")).click();
    // Check Invalid Admin user error message
    assertEquals("Invalid Login please try again", driver.findElement(By.id("heading3")).getText());
    // Check if the username and password input fields got cleared
    assertEquals("", driver.findElement(By.id("username")).getText());
    assertEquals("", driver.findElement(By.id("password")).getText());
    // Click on the Cancel button to return to Home page
    driver.findElement(By.cssSelector("input[type=\"button\"][value=\"Cancel\"]")).click();
    assertEquals("User Login |", driver.findElement(By.id("login1")).getText());
  }
/*
  public static TestSuite suite() {
	  TestSuite suite = new TestSuite();
	  suite.addTestSuite(AdminLoginInvalidUserTest.class);
	  return suite;
  }
  */
  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }


}

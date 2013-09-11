package com.twc.hnav.WookieTest;


import java.util.concurrent.TimeUnit;

import junit.framework.TestCase;

import org.junit.*;

import static org.junit.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;


public class AdminLoginPrivilegeTest extends TestCase {
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
  public void testAdminPrivilege() throws Exception {
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
    driver.findElement(By.id("username")).sendKeys("milkamal");
    driver.findElement(By.id("password")).sendKeys("twc");
    driver.findElement(By.id("submit")).click();
    
    if (isElementPresent(By.cssSelector("body"))) {
    	String errMsg = driver.findElement(By.cssSelector("body")).getText();
        assertTrue(errMsg.contains("ACCESS DENIED"));
    } else
    	fail("Error");
    
    //assertEquals("ACCESS DENIED..!! \n \n HOME", driver.findElement(By.cssSelector("body")).getText());
    assertEquals("HOME", driver.findElement(By.linkText("HOME")).getText());
    driver.findElement(By.linkText("HOME")).click();
    assertEquals("User Login |", driver.findElement(By.id("login1")).getText());
  }

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

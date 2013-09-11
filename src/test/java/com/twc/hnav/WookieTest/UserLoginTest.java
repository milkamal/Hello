package com.twc.hnav.WookieTest;

import java.util.List;
import java.util.concurrent.TimeUnit;

import junit.framework.TestCase;

import org.junit.*;

import static org.junit.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;


public class UserLoginTest extends TestCase {
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
  public void testUserLogin() throws Exception {
    driver.get(baseUrl + "/wookie/demo/");
    assertEquals("Webapp Appstore", driver.findElement(By.id("heading1")).getText());
    try {
      assertTrue(isElementPresent(By.id("login1")));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals("User Login |", driver.findElement(By.id("login1")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertTrue(isElementPresent(By.id("login2")));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals("Admin Login |", driver.findElement(By.id("login2")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertTrue(isElementPresent(By.id("login3")));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals("Publisher Login", driver.findElement(By.id("login3")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    driver.findElement(By.id("login1")).click();
    try {
      assertTrue(isElementPresent(By.id("username")));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    driver.findElement(By.id("username")).sendKeys("milkamal");
    try {
      assertTrue(isElementPresent(By.id("password")));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    driver.findElement(By.id("password")).sendKeys("twc");
    driver.findElement(By.id("submit")).click();
    //driver.findElement(By.id("widget_header")).click();
    List<WebElement> el = driver.findElements(By.id("heading1"));
    assertFalse(el.isEmpty());
    el = driver.findElements(By.id("heading3"));
    assertTrue(el.isEmpty());
    

    try {
      assertEquals("Logout", driver.findElement(By.id("login1")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    driver.findElement(By.id("login1")).click();
    try {
      assertEquals("User Login |", driver.findElement(By.id("login1")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
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

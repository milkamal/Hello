package com.twc.hnav.WookieTest;

import java.util.concurrent.TimeUnit;

import junit.framework.TestCase;

import org.junit.*;

import static org.junit.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class HomeScreenTest extends TestCase {
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
  public void testHomeScreen() throws Exception {
    driver.get(baseUrl + "/wookie/demo/");
    String currentUrl = driver.getCurrentUrl();
    
    // Check if logo is displayed , logo image is correct and has correct width and height
    assertTrue(driver.findElement(By.id("logo")).isDisplayed());
    WebElement imgEl = driver.findElement(By.id("logo")).findElement(By.cssSelector("img"));
    String logoSrc =    imgEl.getAttribute("src");
    assertEquals(currentUrl+"twc-logo.png", logoSrc);
    Thread.sleep(2000);
    assertEquals(135,Integer.parseInt(imgEl.getAttribute("width")));
    assertEquals(50,Integer.parseInt(imgEl.getAttribute("height")));
    
    // Check if "All" button is highlighted with background image
    WebElement allBtn = driver.findElement(By.className("topbox")).findElement(By.xpath("/html/body/div/div[3]/table/tbody/tr/td[@id='all']"));
    String allBtnStyle = allBtn.getAttribute("style");
    assertTrue(allBtnStyle.indexOf("background-image") != -1 );
    // Check if "Free" button is not highlighted with background image
    WebElement freeBtn = driver.findElement(By.className("topbox")).findElement(By.xpath("/html/body/div/div[3]/table/tbody/tr/td[@id='free']"));
    String freeBtnStyle = freeBtn.getAttribute("style");
    assertTrue(freeBtnStyle.indexOf("background-image") == -1 );
    
    // Check Widget Button for 'demogame'
    assertTrue(isElementPresent(By.xpath("/html/body/div/div[2]/div/table/tbody/tr/td/a")));
    
    WebElement demoGameBtn = driver.findElement(By.xpath("/html/body/div/div[2]/div/table/tbody/tr/td/a"));
    assertEquals(currentUrl+"widgetinfo.htm?key=http://wookie.apache.org/widgets/demogame",demoGameBtn.getAttribute("href"));
    demoGameBtn = driver.findElement(By.xpath("/html/body/div/div[2]/div/table/tbody/tr/td/a/img"));
    assertTrue(demoGameBtn.getAttribute("src").indexOf("demogame/images/icon.png") != -1);
    demoGameBtn = driver.findElement(By.xpath("/html/body/div/div[2]/div/table/tbody/tr/td/a[2]/font"));
    assertEquals("demogame",demoGameBtn.getText());
    
    
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

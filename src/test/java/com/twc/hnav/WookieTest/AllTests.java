package com.twc.hnav.WookieTest;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AllTests 
    
{
  

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
    	TestSuite allTests = new TestSuite();
    	
    	allTests.addTestSuite(AdminLoginInvalidUserTest.class);
    	allTests.addTestSuite(AdminLoginPrivilegeTest.class);
    	allTests.addTestSuite(HomeScreenTest.class);
    	allTests.addTestSuite(UserLoginTest.class);
        return allTests;
    }

    
}

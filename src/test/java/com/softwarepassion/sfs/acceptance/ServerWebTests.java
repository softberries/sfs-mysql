package com.softwarepassion.sfs.acceptance;

import com.softwarepassion.sfs.SfsMysqlApplication;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.TimeUnit;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(SfsMysqlApplication.class)
@WebIntegrationTest(randomPort = true)
public class ServerWebTests {

    private static FirefoxDriver browser;

    @Value("${server.port}")
    private int port;

    @BeforeClass
    public static void openBrowser() {
        browser = new FirefoxDriver();
        browser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterClass
    public static void closeBrowser() {
        browser.quit();
    }

    @Test
    public void simpleTest() {
        System.out.println("asdf");
    }
}

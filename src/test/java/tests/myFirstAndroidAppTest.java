package tests;


import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;

import static helpers.utils.getProperty;


public class myFirstAndroidAppTest {

    private static ThreadLocal<AndroidDriver> androidDriver = new ThreadLocal<AndroidDriver>();
    private  ThreadLocal<String> sessionId = new ThreadLocal<String>();

    String num1 = "editNum1";
    String num2 = "editNum2";
    String sumButton = "sumButton";
    String result = "resultTextView";


    @BeforeMethod
    public void setup(Method method) throws MalformedURLException {

        System.out.println("Sauce - BeforeMethod hook");

        String region = getProperty("region", "eu");

        String username = System.getenv("SAUCE_USERNAME");
        String accesskey = System.getenv("SAUCE_ACCESS_KEY");
        String methodName = method.getName();

        String sauceUrl;

        if (region.equalsIgnoreCase("eu")) {
            sauceUrl = "@ondemand.eu-central-1.saucelabs.com:443";
        } else {
            sauceUrl = "@ondemand.us-west-1.saucelabs.com:443";
        }

        String SAUCE_REMOTE_URL = "https://" + username + ":" + accesskey + sauceUrl +"/wd/hub";
        URL url = new URL(SAUCE_REMOTE_URL);

        String appName ="myFirstApp_version2.0_debug.apk";

        MutableCapabilities capabilities = new MutableCapabilities();
//        capabilities.setCapability("deviceName", "Android");
//        capabilities.setCapability("platformVersion", "8.0");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("automationName", "UiAutomator2");
        capabilities.setCapability("name", methodName);
        capabilities.setCapability("app", "storage:filename=" + appName);
        capabilities.setCapability("phoneOnly", true);

        // Launch remote browser and set it as the current thread
     //   androidDriver.set(new AndroidDriver(new URL(SAUCE_REMOTE_URL),capabilities));
        androidDriver.set( new AndroidDriver(url, capabilities));
        String id = ((RemoteWebDriver) getAndroidDriver()).getSessionId().toString();
        sessionId.set(id);
    }

    @AfterMethod
    public void teardown(ITestResult result) {
        System.out.println("Sauce - AfterMethod hook");
       ((JavascriptExecutor)getAndroidDriver()).executeScript("sauce:job-result=" + (result.isSuccess() ? "passed" : "failed"));

        getAndroidDriver().quit();
    }

    public  AndroidDriver getAndroidDriver() {
        return androidDriver.get();
    }

    @Test
    public void calcAddNum() {
        String strNum1 = "5";
        String strNum2 = "3";
        String expectedResult = "8";

        System.out.println("Sauce - Start calcAddNum test");
        WebElement num1Edit = (WebElement) androidDriver.get().findElementById(num1);
        num1Edit.sendKeys(strNum1);

        WebElement num2Edit = (WebElement) androidDriver.get().findElementById(num2);
        num2Edit.sendKeys(strNum2);

        WebElement sumButtonElement = (WebElement) androidDriver.get().findElementById(sumButton);
        sumButtonElement.click();

        WebElement resultEdit = (WebElement) androidDriver.get().findElementById(result);
        String actualRes = resultEdit.getText();

        Assert.assertEquals(expectedResult,actualRes );
    }

    @Test
    public void calcAddNumNegative() {
        String strNum1 = "2";
        String strNum2 = "7";
        String expectedResult = "9";

        System.out.println("Sauce - Start calcAddNumNegative test");
        WebElement num1Edit = (WebElement) androidDriver.get().findElementById(num1);
        num1Edit.sendKeys(strNum1);

        WebElement num2Edit = (WebElement) androidDriver.get().findElementById(num2);
        num2Edit.sendKeys(strNum2);

        WebElement sumButtonElement = (WebElement) androidDriver.get().findElementById(sumButton);
        sumButtonElement.click();

        WebElement resultEdit = (WebElement) androidDriver.get().findElementById(result);
        String actualRes = resultEdit.getText();

        Assert.assertEquals(expectedResult,actualRes );
    }
}
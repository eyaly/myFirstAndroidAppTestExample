# Example of using Azure DevOps pipeline for running Appium Tests on Sauce Labs Platform

The framework uses testNG xml file for parallel executions. All the tests in the same class will run in parallel on different devices
## Important information
### Environment variables for Sauce Labs
The examples in this repository that can run on Sauce Labs use environment variables, make sure you've added the following

    # For Sauce Labs Real devices in the New UI
    export SAUCE_USERNAME=********
    export SAUCE_ACCESS_KEY=*******
    
### The app
The Native app that has been used for the tests can be found [here](https://github.com/eyaly/myFirstAndroidApp/tree/master/demoApp).
The app can be build using the simple Android project tests can be found [here](https://github.com/eyaly/myFirstAndroidApp).


### Upload apps to Sauce Storage
* If you want to use iOS real devices and Android real devices in the New Sauce Labs UI you need to upload the apps to the Sauce Storage.
For more information on this step please visit: [Application Storage](https://wiki.saucelabs.com/display/DOCS/Application+Storage).
* In the app capability you must use `storage:<app-id>` or `storage:filename=<file-name>`. For more information on this step please visit: [Application Storage](https://wiki.saucelabs.com/display/DOCS/Application+Storage).
* Change the value of appName in the native apps tests for Android and iOS according to your app name.
### Useful Links 
* How to upload the apps to the Sauce Storage and the app capability: [Application Storage](https://wiki.saucelabs.com/display/DOCS/Application+Storage).
* Appium Capabilities for Real Device Testing: [Appium Capabilities](https://wiki.saucelabs.com/display/DOCS/Appium+Capabilities+for+Real+Device+Testing).
* Sauce Labs Data Center Endpoints: [Data Center EndPoints](https://wiki.saucelabs.com/display/DOCS/Data+Center+Endpoints).
* How to set the pass/fail status of a test: [Annotating Tests with Selenium's JavaScript Executor](https://wiki.saucelabs.com/display/DOCS/Annotating+Tests+with+Selenium%27s+JavaScript+Executor).
## Run Native App tests on Sauce Labs Android real devices in the Sauce Labs Platform
If you want to run the Native Android App tests on Sauce Labs real Android devices then you can run the Android tests with

    // If using the US DC
    mvn clean install -DtestngXmlFile=testng_android.xml -Dregion=us
    
    // If using the EU DC
    mvn clean install -DtestngXmlFile=testng_android.xml -Dregion=eu
    
The tests will be executed on an any available Samsung device.

> NOTE: Make sure you are in the folder `myFirstAndroidAppTestExample` when you execute this command

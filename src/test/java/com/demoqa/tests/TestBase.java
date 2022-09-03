package com.demoqa.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.junit5.BrowserPerTestStrategyExtension;
import com.codeborne.selenide.junit5.BrowserStrategyExtension;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.demoqa.config.CredentialsConfig;
import com.demoqa.helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.remote.DesiredCapabilities;

import static java.lang.String.format;

@ExtendWith({BrowserPerTestStrategyExtension.class})
public class TestBase   {

   static String remoteUrl;
    @BeforeAll
    static void configure() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());


        remoteUrl = System.getProperty("remote_url");
        String browser = System.getProperty("browser", "chrome");
        String browserSize = System.getProperty("browser_size", "1920x1080" );
        String browserVersion = System.getProperty("browser_version");

        if (remoteUrl != null){
            CredentialsConfig config = ConfigFactory.create(CredentialsConfig.class);
            String remoteLogin = config.login();
            String remotePassword = config.password();

            Configuration.remote = format("https://%s:%s@%s/wd/hub", remoteLogin, remotePassword, remoteUrl);
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("enableVNC", true);
            capabilities.setCapability("enableVideo", true);
            Configuration.browserCapabilities = capabilities;
        }

        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = browserSize;
        Configuration.browser = browser;
        Configuration.browserVersion = browserVersion;
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        if (remoteUrl != null) {
            Attach.addVideo(remoteUrl);
        }
    }
}

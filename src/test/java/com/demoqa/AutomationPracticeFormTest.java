package com.demoqa;
import org.junit.jupiter.api.BeforeAll;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import java.io.File;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.text;


public class AutomationPracticeFormTest {

    @BeforeAll
    static void setParameters() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
    }

    @Test
    void testAutomationForm() {
        open("/automation-practice-form");

        executeJavaScript("$('footer').remove()");
        executeJavaScript("$('#fixedban').remove()");


        $("#firstName").setValue("Alex");
        $("#lastName").setValue("Testov");
        $("#userEmail").setValue("alex@test.com");
        $(byText("Male")).click();
        $("#userNumber").setValue("01234656789");

        $("#dateOfBirthInput").click();
        $(".react-datepicker__year-select").selectOptionContainingText("1988");
        $(".react-datepicker__month-select").selectOptionContainingText("May");
        $(".react-datepicker__day--020").click();

        $("#subjectsInput").setValue("Economics").pressEnter();
        $(byText("Sports")).click();

        $("#uploadPicture").uploadFile(new File("src/test/resources/cat.jpg"));

        $("#currentAddress").setValue("Saint-Petersburg\nNevsky, 1");

        $("#state").click();
        $(byText("NCR")).click();

        $("#city").click();
        $(byText("Delhi")).click();


        $("#submit").click();

        $(".table-responsive").shouldHave(
                text("Alex Testov"),
                text("alex@test.com"),
                text("Male"),
                text("0123465678"),
                text("20 May,1988"),
                text("Economics"),
                text("Sports"),
                text("cat.jpg"),
                text("Saint-Petersburg Nevsky, 1"),
                text("NCR Delhi")
        );


    }
}

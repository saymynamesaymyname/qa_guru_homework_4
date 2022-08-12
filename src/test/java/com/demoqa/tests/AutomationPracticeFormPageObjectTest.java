package com.demoqa.tests;

import com.codeborne.selenide.Configuration;
import com.demoqa.pages.RegistrationFormPage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;


public class AutomationPracticeFormPageObjectTest {

    @BeforeAll
    static void setParameters() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
    }

    RegistrationFormPage registrationFormPage = new RegistrationFormPage();


    @Test
    void testFullRegistrationForm() {
        registrationFormPage
                .openPage()
                .setFirstName("Alex")
                .setLastName("Testov")
                .setEmail("alex@test.com")
                .setGender("Male")
                .setUserNumber("01234656789")
                .setDateOfBirth("1988", "May", "20")
                .setSubject("Economics")
                .setHobby("Sports")
                .uploadFile("src/test/resources/cat.jpg")
                .setAddress("Spb, Nevsky, 11")
                .setState("NCR")
                .setCity("Delhi")
                .clickSubmitButton()
                .checkResultsTableVisible()
                .checkResult("Student Name", "Alex Testov")
                .checkResult("Student Email", "alex@test.com")
                .checkResult("Gender", "Male")
                .checkResult("Mobile", "0123465678")
                .checkResult("Date of Birth", "20 May,1988")
                .checkResult("Subjects", "Economics")
                .checkResult("Hobbies", "Sports")
                .checkResult("Picture", "cat.jpg")
                .checkResult("Address", "Spb, Nevsky, 11")
                .checkResult("State and City", "NCR Delhi");
    }

    @Test
    void TestRegistrationFormWithMinimumData() {
        registrationFormPage
                .openPage()
                .setFirstName("Boris")
                .setLastName("Godunov")
                .setUserNumber("0123456789")
                .setGender("Male")
                .clickSubmitButton()
                .checkResult("Student Name", "Boris Godunov")
                .checkResult("Gender", "Male")
                .checkResult("Mobile", "0123456789");


    }
}

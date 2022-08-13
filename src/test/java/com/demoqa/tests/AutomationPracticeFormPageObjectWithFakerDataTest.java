package com.demoqa.tests;

import com.demoqa.pages.RegistrationFormPage;
import com.demoqa.utils.RandomDataGenerator;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.demoqa.utils.RandomDataGenerator.generateRandomMonth;
import static java.lang.String.format;
import static java.lang.String.valueOf;


public class AutomationPracticeFormPageObjectWithFakerDataTest extends TestBase {
    RegistrationFormPage registrationFormPage = new RegistrationFormPage();
    Faker faker = new Faker();

    String firstName,
            lastName,
            email,
            phoneNumber,
            day,
            month,
            year,
            fullExpectedDate,
            address;

    @BeforeEach
    void generateTestData() {
        firstName = faker.name().firstName();
        lastName = faker.name().lastName();
        email = faker.internet().emailAddress();
        phoneNumber = faker.phoneNumber().subscriberNumber(10);
        day = String.format("%02d", faker.number().numberBetween(1,28));
        year = valueOf(faker.number().numberBetween(1980, 2000));
        month = generateRandomMonth();
        fullExpectedDate = format("%s %s,%s", day, month, year);
        address = faker.address().fullAddress();
    }


    @Test
    void testFullRegistrationForm() {
        registrationFormPage
                .openPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setGender("Male")
                .setUserNumber(phoneNumber)
                .setDateOfBirth(year, month, day)
                .setSubject("Economics")
                .setHobby("Sports")
                .uploadFile("src/test/resources/cat.jpg")
                .setAddress(address)
                .setState("NCR")
                .setCity("Delhi")
                .clickSubmitButton()
                .checkResultsTableVisible()
                .checkResult("Student Name", firstName + " " + lastName)
                .checkResult("Student Email", email)
                .checkResult("Gender", "Male")
                .checkResult("Mobile", phoneNumber)
                .checkResult("Date of Birth", fullExpectedDate)
                .checkResult("Subjects", "Economics")
                .checkResult("Hobbies", "Sports")
                .checkResult("Picture", "cat.jpg")
                .checkResult("Address", address)
                .checkResult("State and City", "NCR Delhi");
    }

    @Test
    void TestRegistrationFormWithMinimumData() {
        registrationFormPage
                .openPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setUserNumber(phoneNumber)
                .setGender("Male")
                .clickSubmitButton()
                .checkResult("Student Name", firstName + " " + lastName)
                .checkResult("Gender", "Male")
                .checkResult("Mobile", phoneNumber);


    }
}

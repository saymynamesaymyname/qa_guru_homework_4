package com.demoqa.tests;

import com.demoqa.pages.RegistrationFormPage;
import com.demoqa.utils.RandomDataGenerator;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.demoqa.utils.RandomDataGenerator.generateRandomMonth;
import static java.lang.String.format;
import static java.lang.String.valueOf;

@DisplayName("Tests for automation practice form")
public class AutomationPracticeFormPageObjectWithFakerDataTest extends TestBase {
    RegistrationFormPage registrationFormPage = new RegistrationFormPage();
    ClassLoader classLoader = AutomationPracticeFormPageObjectWithFakerDataTest.class.getClassLoader();

    Faker faker = new Faker();

    String firstName = faker.name().firstName(),
            lastName = faker.name().lastName(),
            email = faker.internet().emailAddress(),
            phoneNumber = faker.phoneNumber().subscriberNumber(10),
            day = String.format("%02d", faker.number().numberBetween(1,28)),
            month = generateRandomMonth(),
            year = valueOf(faker.number().numberBetween(1980, 2000)),
            fullExpectedDate = format("%s %s,%s", day, month, year),
            address = faker.address().fullAddress(),
            gender = "Male",
            subject = "Economics",
            hobby = "Sports",
            pathToFile = classLoader.getResource("cat.jpg").getPath(),
            state = "NCR",
            city = "Delhi";



    @Test
    @Tag("fullTest")
    @DisplayName("Full registration form test")
    void testFullRegistrationForm() {
        registrationFormPage
                .openPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setGender(gender)
                .setUserNumber(phoneNumber)
                .setDateOfBirth(year, month, day)
                .setSubject(subject)
                .setHobby(hobby)
                .uploadFile(pathToFile)
                .setAddress(address)
                .setState(state)
                .setCity(city)
                .clickSubmitButton()
                .checkResultsTableVisible()
                .checkResult("Student Name", firstName + " " + lastName)
                .checkResult("Student Email", email)
                .checkResult("Gender", "Male")
                .checkResult("Mobile", phoneNumber)
                .checkResult("Date of Birth", fullExpectedDate)
                .checkResult("Subjects", subject)
                .checkResult("Hobbies", hobby)
                .checkResult("Picture", pathToFile.substring(pathToFile.lastIndexOf("/")+1))
                .checkResult("Address", address)
                .checkResult("State and City", state + " " + city);
    }

    @Test
    @Tag("smokeTest")
    @DisplayName("Registration with minimum ")
    void testRegistrationFormWithMinimumData() {
        registrationFormPage
                .openPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setUserNumber(phoneNumber)
                .setGender(gender)
                .clickSubmitButton()
                .checkResult("Student Name", firstName + " " + lastName)
                .checkResult("Gender", gender)
                .checkResult("Mobile", phoneNumber);


    }
}

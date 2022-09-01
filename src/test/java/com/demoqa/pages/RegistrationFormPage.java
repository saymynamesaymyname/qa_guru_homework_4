package com.demoqa.pages;

import com.codeborne.selenide.SelenideElement;
import com.demoqa.pages.components.DatePickerComponent;
import com.demoqa.pages.components.ResultsModal;
import io.qameta.allure.Step;

import java.io.File;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public class RegistrationFormPage {

    DatePickerComponent datePickerComponent = new DatePickerComponent();
    ResultsModal resultsModal = new ResultsModal();
    private SelenideElement
            firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            userEmailInput = $("#userEmail"),
            genderWrapper = $("#genterWrapper"),
            userNumberInput = $("#userNumber"),
            dateOfBirthInput = $("#dateOfBirthInput"),
            subjectsInput = $("#subjectsInput"),
            hobbiesWrapper = $("#hobbiesWrapper"),
            uploadPictureInput = $("#uploadPicture"),
            adressInput = $("#currentAddress"),
            stateDropdown = $("#state"),
            cityDropdown = $("#city"),
            submitButton = $("#submit");


   @Step("Open main page")
    public RegistrationFormPage openPage() {
        open("/automation-practice-form");
        executeJavaScript("$('footer').remove()");
        executeJavaScript("$('#fixedban').remove()");
        return this;
    }

    @Step("Set first name")
    public RegistrationFormPage setFirstName(String value) {
        firstNameInput.setValue(value);
        return this;
    }

    @Step("Set last name")
    public RegistrationFormPage setLastName(String value) {
        lastNameInput.setValue(value);
        return this;
    }

    @Step("Set email")
    public RegistrationFormPage setEmail(String value) {
        userEmailInput.setValue(value);
        return this;
    }

    @Step("Set gender")
    public RegistrationFormPage setGender(String value) {
        genderWrapper.$(byText(value)).click();
        return this;
    }
    @Step("Set phone number")
    public RegistrationFormPage setUserNumber(String value) {
        userNumberInput.setValue(value);
        return this;
    }
    @Step("Set date of birth")
    public RegistrationFormPage setDateOfBirth(String year, String month, String day) {
        dateOfBirthInput.click();
        datePickerComponent.setDate(year, month, day);
        return this;
    }
    @Step("Set subject")
    public RegistrationFormPage setSubject(String value) {
        subjectsInput.setValue(value).pressEnter();
        return this;
    }
    @Step("Set hobby")
    public RegistrationFormPage setHobby(String value) {
        hobbiesWrapper.$(byText(value)).click();
        return this;
    }
    @Step("Upload file")
    public RegistrationFormPage uploadFile(String path) {
        uploadPictureInput.uploadFile(new File(path));
        return this;
    }
    @Step("Set adress")
    public RegistrationFormPage setAddress(String value) {
        adressInput.setValue(value);
        return this;
    }
    @Step("Set state")
    public RegistrationFormPage setState(String value) {
        stateDropdown.click();
        $(byText(value)).click();
        return this;
    }
    @Step("Set city")
    public RegistrationFormPage setCity(String value) {
        cityDropdown.click();
        $(byText(value)).click();
        return this;
    }

    @Step("Click submit button")
    public RegistrationFormPage clickSubmitButton() {
        submitButton.click();
        return this;
    }

    @Step("Check results table is visible")
    public RegistrationFormPage checkResultsTableVisible() {
        resultsModal.checkVisible();
        return this;
    }

    @Step("Check that {key} field has {value} value")
    public RegistrationFormPage checkResult(String key, String value) {
        resultsModal.checkResult(key, value);
        return this;
    }

}

package com.demoqa.pages;

import com.codeborne.selenide.SelenideElement;
import com.demoqa.pages.components.DatePickerComponent;
import com.demoqa.pages.components.ResultsModal;

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


    public RegistrationFormPage openPage() {
        open("/automation-practice-form");
        executeJavaScript("$('footer').remove()");
        executeJavaScript("$('#fixedban').remove()");
        return this;
    }


    public RegistrationFormPage setFirstName(String value) {
        firstNameInput.setValue(value);
        return this;
    }

    public RegistrationFormPage setLastName(String value) {
        lastNameInput.setValue(value);
        return this;
    }

    public RegistrationFormPage setEmail(String value) {
        userEmailInput.setValue(value);
        return this;
    }

    public RegistrationFormPage setGender(String value) {
        genderWrapper.$(byText(value)).click();
        return this;
    }

    public RegistrationFormPage setUserNumber(String value) {
        userNumberInput.setValue(value);
        return this;
    }

    public RegistrationFormPage setDateOfBirth(String year, String month, String day) {
        dateOfBirthInput.click();
        datePickerComponent.setDate(year, month, day);
        return this;
    }

    public RegistrationFormPage setSubject(String value) {
        subjectsInput.setValue(value).pressEnter();
        return this;
    }

    public RegistrationFormPage setHobby(String value) {
        hobbiesWrapper.$(byText(value)).click();
        return this;
    }

    public RegistrationFormPage uploadFile(String path) {
        uploadPictureInput.uploadFile(new File(path));
        return this;
    }

    public RegistrationFormPage setAddress(String value) {
        adressInput.setValue(value);
        return this;
    }

    public RegistrationFormPage setState(String value) {
        stateDropdown.click();
        $(byText(value)).click();
        return this;
    }

    public RegistrationFormPage setCity(String value) {
        cityDropdown.click();
        $(byText(value)).click();
        return this;
    }

    public RegistrationFormPage clickSubmitButton() {
        submitButton.click();
        return this;
    }

    public RegistrationFormPage checkResultsTableVisible() {
        resultsModal.checkVisible();
        return this;
    }

    public RegistrationFormPage checkResult(String key, String value) {
        resultsModal.checkResult(key, value);
        return this;
    }

}

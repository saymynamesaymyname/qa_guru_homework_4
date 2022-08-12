package com.demoqa.pages.components;

import static com.codeborne.selenide.Selenide.$;

public class DatePickerComponent {

    public DatePickerComponent setDate (String year, String month, String day){
        $(".react-datepicker__year-select").selectOptionContainingText(year);
        $(".react-datepicker__month-select").selectOptionContainingText(month);
        $(".react-datepicker__day--0" + day).click();
        return this;
    }
}

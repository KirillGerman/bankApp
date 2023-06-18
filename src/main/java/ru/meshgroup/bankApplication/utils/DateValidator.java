package ru.meshgroup.bankApplication.utils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateValidator implements ConstraintValidator<DatePattern, String> {

    static DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public void initialize(DatePattern constraintAnnotation) {

    }

    @Override
    public boolean isValid(String text, ConstraintValidatorContext context) {
        if (text == null)
            return true;
        try {
            if (!text.matches("\\d{4}-\\d{2}-\\d{2}"))
                return false;
            LocalDate.parse(text, dateTimeFormat);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

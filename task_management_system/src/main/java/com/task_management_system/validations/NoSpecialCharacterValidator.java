package com.task_management_system.validations;

import com.task_management_system.validations.annotations.NoSpecialCharacter;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class NoSpecialCharacterValidator implements ConstraintValidator<NoSpecialCharacter, Object> {

    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        return !containsSpecialCharacters(value.toString());

    }
    private boolean containsSpecialCharacters(String name) {

        Pattern pattern = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(name);
        return matcher.find();

    }
}

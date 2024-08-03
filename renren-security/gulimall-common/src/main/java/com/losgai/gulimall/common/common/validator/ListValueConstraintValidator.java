package com.losgai.gulimall.common.common.validator;

import com.losgai.gulimall.common.common.validator.annotation.ListValue;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.HashSet;

public class ListValueConstraintValidator implements ConstraintValidator<ListValue, Integer> {
    private HashSet<Integer> vals=new HashSet<>();

    @Override
    public void initialize(ListValue constraintAnnotation) {
        int[] vals =constraintAnnotation.vals();
        for(int val:vals){
            this.vals.add(val);
        }
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        return vals.contains(value);
    }
}

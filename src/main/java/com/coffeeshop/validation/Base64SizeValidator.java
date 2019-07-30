package com.coffeeshop.validation;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class Base64SizeValidator implements ConstraintValidator<Base64Size, String> {

    private int maxKbSize;

    @Override
    public void initialize(Base64Size base64Size) {
        this.maxKbSize = base64Size.maxSizeKb();
    }

    public boolean isValid(String base64, ConstraintValidatorContext context) {

        if (base64 == null) {
            return true;
        }

        if (base64.isEmpty()) {
            return true;
        }
        if (calculateBase64StringSize(base64, false) <= maxKbSize) {
            return true;
        } else return false;
    }

    public Integer calculateBase64StringSize(String base64StringSize, boolean sizeInBytes) {
        Integer res = 0;
        Integer padding = 0;
        if (base64StringSize.endsWith("==")) {
            padding = 2;
        } else {
            if (base64StringSize.endsWith("=")) {
                padding = 1;
            }
            }
            res = (int)(Math.ceil(base64StringSize.length() / 4) * 3) - padding;
            if (sizeInBytes) {
                return res;
            } else
                return (res / 1024);
        }

}



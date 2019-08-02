package com.coffeeshop.validation.validator;

import com.coffeeshop.validation.ImageTypeConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Base64;

public class ImageJpgTypeValidator implements ConstraintValidator<ImageTypeConstraint, String> {

    private final byte[] JPEG_START_WITH_BYTES = {-1, -40};

    private final byte[] JPEG_END_WITH_BYTES = {-1, -39};

    public ImageJpgTypeValidator() {
    }

    public void initialize(ImageTypeConstraint constraint) {
   }

    public boolean isValid(String base64Img, ConstraintValidatorContext context) {
        if (base64Img == null) {
            return true;
        }
        if (base64Img.isEmpty() && base64Img != null) {
            return false;
        }

        byte[] imageContent = Base64.getDecoder().decode(base64Img);

        return imageContent[0] == JPEG_START_WITH_BYTES[0] &&
               imageContent[1] == JPEG_START_WITH_BYTES[1] &&
               imageContent[imageContent.length - 2] == JPEG_END_WITH_BYTES[0] &&
               imageContent[imageContent.length - 1] == JPEG_END_WITH_BYTES[1];
   }
}

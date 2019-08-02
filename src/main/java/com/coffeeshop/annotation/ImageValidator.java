package com.coffeeshop.annotation;

import org.apache.commons.io.FileUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.io.File;
import java.io.IOException;
import java.net.URLConnection;
import java.util.Base64;

public class ImageValidator implements ConstraintValidator<ImageConstraint, String> {
   public void initialize(ImageConstraint constraint) {
   }

   public boolean isValid(String img, ConstraintValidatorContext context) {

      byte[] imageContent = Base64.getDecoder().decode(img);
      File file = new File("image");
       try {
           FileUtils.writeByteArrayToFile(file, imageContent);
       } catch (IOException e) {
           e.printStackTrace();
       }
       URLConnection connection = null;
       try {
           connection = file.toURL().openConnection();
       } catch (IOException e) {
           e.printStackTrace();
       }
       String mimeType = connection.getContentType();


       return false;
   }
}

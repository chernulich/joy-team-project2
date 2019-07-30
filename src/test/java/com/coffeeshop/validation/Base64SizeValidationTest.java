package com.coffeeshop.validation;

import io.leangen.geantyref.TypeFactory;
import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class Base64SizeValidationTest {

    private final int BYTES_IN_MEGA_BYTE = 1048576;
    private final String ALPHANUMERIC_CHARSET = "[^A-Za-z0-9]";
    
    private Base64Size base64SizeAnnotation;
    private Base64SizeValidator validator;
    private final String COFFEE_IMAGE_TEST = "src/test/resources/coffee-2.jpg";

    @SneakyThrows
    private Base64Size getInstanceOfAnnotation(int size) {
            Map<String,Object> annotationParams = new HashMap<>();
            annotationParams.put("maxSizeKb",size);
            base64SizeAnnotation = TypeFactory.annotation(Base64Size.class,annotationParams);
            getValidator(base64SizeAnnotation);
            return base64SizeAnnotation;
    }

    private Base64SizeValidator getValidator(Base64Size base64SizeAnnotation){
        validator = new Base64SizeValidator();
        validator.initialize(base64SizeAnnotation);
        return validator;
    }

    @Test
    public void testEmptyBase64ImageSize() {
        validator = getValidator(getInstanceOfAnnotation(9));

        assertTrue(validator.isValid("", null));
    }

    @Test
    public void testValidBase64ImageSize() {
        validator = getValidator(getInstanceOfAnnotation(9));
        String image = encoder(COFFEE_IMAGE_TEST);
        assertTrue(validator.isValid(image,null));
    }

    @Test
    public void testNotValidBase64ImageSize() {
        validator = getValidator(getInstanceOfAnnotation(7));
        String image = encoder(COFFEE_IMAGE_TEST);
        assertFalse(validator.isValid(image, null));
    }

    @Test
    public void testNullBase64ImageSize(){
        validator = getValidator(getInstanceOfAnnotation(9));
        assertTrue(validator.isValid(null, null));
    }


    @Test
    public void testGeneratorAlphaNumericInBytes(){
        String stringEncoded = GeneratorBase64Symbols();
        int expectedSize = getValidator(getInstanceOfAnnotation(BYTES_IN_MEGA_BYTE))
                .calculateBase64StringSize(stringEncoded,true);
        assertEquals(BYTES_IN_MEGA_BYTE,expectedSize);
    }

    @Test
    public void testGeneratorAlphaNumericInKB(){
        String stringEncoded = GeneratorBase64Symbols();
        int expectedSize = getValidator(getInstanceOfAnnotation(BYTES_IN_MEGA_BYTE))
                .calculateBase64StringSize(stringEncoded,false);
        assertEquals(BYTES_IN_MEGA_BYTE/1024,expectedSize);
    }

    @SneakyThrows({FileNotFoundException.class, IOException.class})
    private String encoder(String imageSrc){
        String base64 = "" ;
        InputStream inputStream = new FileInputStream(imageSrc);
            byte[] imageToBytes = IOUtils.toByteArray(inputStream);
            base64 = Base64.getEncoder().encodeToString(imageToBytes);
            return base64;
    }

    private String GeneratorBase64Symbols(){
        String randomGenerated = RandomStringUtils.random(BYTES_IN_MEGA_BYTE,ALPHANUMERIC_CHARSET);
        String stringEncoded = Base64.getEncoder().encodeToString(randomGenerated.getBytes());
        return stringEncoded;
    }

}

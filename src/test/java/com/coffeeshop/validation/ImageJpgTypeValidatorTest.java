package com.coffeeshop.validation;

import com.coffeeshop.validation.validator.ImageJpgTypeValidator;
import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.util.Base64;

import static org.junit.Assert.*;

public class ImageJpgTypeValidatorTest {

    private final String PATH_TO_FIRST_IMAGE_JPG = "src/test/resources/image/coffee.jpg";
    private final String PATH_TO_SECOND_IMAGE_JPG = "src/test/resources/image/art-blur-cappuccino-302899.jpg";
    private final String PATH_TO_THIRD_IMAGE_JPG = "src/test/resources/image/beans-brown-coffee-34085.jpg";
    private final String PATH_TO_FOUR_IMAGE_JPG = "src/test/resources/image/beverage-brewed-coffee-caffeine-374885.jpg";
    private final String PATH_TO_IMAGE_PNG = "src/test/resources/image/Image_Product Listing.png";
    private final String PATH_TO_IMAGE_PDF = "src/test/resources/image/Хотелки.pdf";
    private final String PATH_TO_IMAGE_ZIP = "src/test/resources/image/BloomFilter.zip";

    private ImageJpgTypeValidator validator = new ImageJpgTypeValidator();

    @Test
    public void testImageTypeIsJpgSuccess() {
        String image1Base64 = imageToArray(PATH_TO_FIRST_IMAGE_JPG);
        String image2Base64 = imageToArray(PATH_TO_SECOND_IMAGE_JPG);
        String image3Base64 = imageToArray(PATH_TO_THIRD_IMAGE_JPG);
        String image4Base64 = imageToArray(PATH_TO_FOUR_IMAGE_JPG);
        assertTrue(validator.isValid(image1Base64, null));
        assertTrue(validator.isValid(image2Base64, null));
        assertTrue(validator.isValid(image3Base64, null));
        assertTrue(validator.isValid(image4Base64, null));
    }

    @Test
    public void testImageTypeJpgFail() {
        String image1Base64 = imageToArray(PATH_TO_IMAGE_PNG);
        String image2Base64 = imageToArray(PATH_TO_IMAGE_PDF);
        String image3Base64 = imageToArray(PATH_TO_IMAGE_ZIP);
        assertFalse(validator.isValid(image1Base64, null));
        assertFalse(validator.isValid(image2Base64, null));
        assertFalse(validator.isValid(image3Base64, null));
    }

    @Test
    public void testImageIsEmpty() {
        String imageBase64 = "";
        assertFalse(validator.isValid(imageBase64, null));
    }

    @Test
    public void testImageIsNull() {
        assertTrue(validator.isValid(null, null));
    }

    @SneakyThrows
    private String imageToArray(String pathToImage) {
        File file = new File(pathToImage);
        FileInputStream fis = new FileInputStream(file);

        return Base64.getEncoder().encodeToString(IOUtils.toByteArray(fis));
    }


}

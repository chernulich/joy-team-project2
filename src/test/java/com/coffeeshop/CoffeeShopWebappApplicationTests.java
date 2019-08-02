package com.coffeeshop;

import static org.junit.Assert.assertFalse;

import com.coffeeshop.model.web.image.ImageRequest;

import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CoffeeShopWebappApplicationTests {

    private Validator validator;

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void contextLoads() throws IOException {
        InputStream is = getClass().getResourceAsStream("image/coffee.jpg");
        byte[] imageContent = IOUtils.toByteArray(is);
        String img = Base64.getEncoder().encodeToString(imageContent);

        ImageRequest imageRequest = new ImageRequest();
        imageRequest.setImage(img);

        Set<ConstraintViolation<ImageRequest>> violations = validator.validate(imageRequest);
        assertFalse(violations.isEmpty());
    }

}

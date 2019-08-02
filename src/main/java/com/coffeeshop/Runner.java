package com.coffeeshop;

import org.apache.commons.io.IOUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Base64;

@Component
public class Runner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        File file = new File("src/main/resources/image/coffee.jpg");
        byte[] imageContent = new byte[(int)file.length()];
        FileInputStream fis = new FileInputStream(file);
        imageContent = IOUtils.toByteArray(fis);
        fis.close();
        System.out.println("ImageContent" + Arrays.toString(imageContent));
        System.out.println("------------------------------------------------------");
        System.out.println("Base64" + Arrays.toString(Base64.getEncoder().encode(imageContent)));
    }
}

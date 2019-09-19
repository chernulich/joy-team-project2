package com.coffeeshop.configuration.swagger;


import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import io.swagger.annotations.ApiModelProperty;
import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.schema.ModelPropertyBuilderPlugin;
import springfox.documentation.spi.schema.contexts.ModelPropertyContext;
import springfox.documentation.swagger.common.SwaggerPluginSupport;
import springfox.documentation.swagger.schema.ApiModelPropertyPropertyBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

@Component
@Order(SwaggerPluginSupport.SWAGGER_PLUGIN_ORDER + 1)
public class SwaggerApiModelPropertyImageFileDataType implements ModelPropertyBuilderPlugin {

    private static final String FILE_ARRAY_PREFIX = "[";
    private static final String FILE_ARRAY_SUFFIX = "]";
    private static final String FILE_ARRAY_DELIMITER = ",";

    private final ApiModelPropertyPropertyBuilder apiModelPropertyPropertyBuilder;

    @Autowired
    public SwaggerApiModelPropertyImageFileDataType(ApiModelPropertyPropertyBuilder apiModelPropertyPropertyBuilder) {
        this.apiModelPropertyPropertyBuilder = apiModelPropertyPropertyBuilder;
    }

    private static final Map<String, UnaryOperator<String>> FILE_TRANSFORMATIONS_PATTERN = new HashMap<String, UnaryOperator<String>>() {{
        put("file", SwaggerApiModelPropertyImageFileDataType::getBase64FromSingleFile);
        put("List<file>", SwaggerApiModelPropertyImageFileDataType::getBase64FromListFile);
    }};


    private static String getBase64FromSingleFile(String exampleField) {
        if (exampleField == null || exampleField.trim().isEmpty()) {
            return exampleField;
        }
        exampleField = exampleField.trim();

        try {
            InputStream fileStream = SwaggerApiModelPropertyImageFileDataType.class.getResourceAsStream("/" + exampleField);
            if (fileStream == null) {
                return exampleField;
            }

            byte[] fileBytes = IOUtils.toByteArray(fileStream);
            return Base64.getEncoder().encodeToString(fileBytes);
        } catch (IOException e) {
            return exampleField;
        }

    }

    private static String getBase64FromListFile(String exampleField) {

        if (!StringUtils.startsWith(exampleField, FILE_ARRAY_PREFIX) && !StringUtils.endsWith(exampleField, FILE_ARRAY_SUFFIX)) {
            return exampleField;
        }

        exampleField = StringUtils.removeEnd(exampleField, FILE_ARRAY_SUFFIX);
        exampleField = StringUtils.removeStart(exampleField, FILE_ARRAY_PREFIX);

        return Arrays
                .stream(exampleField.split(FILE_ARRAY_DELIMITER))
                .map(SwaggerApiModelPropertyImageFileDataType::getBase64FromSingleFile)
                .collect(Collectors.joining(FILE_ARRAY_DELIMITER, FILE_ARRAY_PREFIX, FILE_ARRAY_SUFFIX));
    }


    @Override
    @SneakyThrows
    public void apply(ModelPropertyContext context) {
        BeanPropertyDefinition beanPropertyDefinition = context.getBeanPropertyDefinition().orNull();

        ApiModelProperty annotation = Optional.ofNullable(beanPropertyDefinition)
                .map(BeanPropertyDefinition::getField)
                .map(x -> x.getAnnotation(ApiModelProperty.class))
                .orElse(null);

        boolean hasAnnotationOrFile = annotation != null &&
                FILE_TRANSFORMATIONS_PATTERN.containsKey(annotation.dataType());

        if (!hasAnnotationOrFile) {
            apiModelPropertyPropertyBuilder.apply(context);
            return;
        }


        context
                .getBuilder()
                .example(FILE_TRANSFORMATIONS_PATTERN.get(annotation.dataType()).apply(annotation.example()));
    }




    @Override
    public boolean supports(DocumentationType delimiter) {
        return apiModelPropertyPropertyBuilder.supports(delimiter);
    }
}

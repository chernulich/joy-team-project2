package com.coffeeshop.deserializer;

import com.coffeeshop.model.admin.ProductCoffeeDto;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.io.IOException;

@Component
@Validated
public class ProductCoffeeDtoDeserializer extends JsonDeserializer<ProductCoffeeDto> {

    @Override
    public ProductCoffeeDto deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        ObjectCodec oc = jp.getCodec();
        JsonNode node = oc.readTree(jp);

        final Integer sour = node.get("sour").asInt();
        final Integer bitter = node.get("bitter").asInt();
        final Integer strong = node.get("strong").asInt();
        final Boolean ground = node.get("ground").asBoolean();
        final Boolean decaf = node.get("decaf").asBoolean();

        @Valid
        ProductCoffeeDto productCoffeeDto = ProductCoffeeDto.builder()
                .sour(sour)
                .bitter(bitter)
                .strong(strong)
                .ground(ground)
                .decaf(decaf)
                .build();

        return productCoffeeDto;
    }//{\"id\":1,\"name\":\"Program @# 1\",\"ownerId\":1,\"contents\":\"Some contents\"}
}

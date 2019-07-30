package com.coffeeshop.model.admin.deserializer;

import com.coffeeshop.model.admin.ProductCoffeeDto;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.IntNode;

import java.io.IOException;

public class CoffeeDeserializer extends StdDeserializer<ProductCoffeeDto> {

    public CoffeeDeserializer() {
        this(null);
    }

    public CoffeeDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public ProductCoffeeDto deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonNode node = jp.getCodec().readTree(jp);
        Integer sour = (Integer) (node.get("sour")).numberValue();
        Integer bitter = (Integer) (node.get("bitter")).numberValue();
        Integer strong = (Integer) (node.get("strong")).numberValue();
        Boolean ground =  node.get("ground").asBoolean();
        Boolean decaf =  node.get("decaf").asBoolean();
        return new ProductCoffeeDto(sour, bitter, strong, ground, decaf);
    }
}

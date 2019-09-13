package com.coffeeshop.model.web;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@Getter
@PropertySource(value = "classpath:defaultDtoValues.properties")
public class DefaultDtoValues {
    @Value(value = "${default.page.size}")
    public Integer defaultPageSize;

    @Value(value = "${default.result.size}")
    public Integer defaultResultSize;

    @Value(value = "${default.max.result.size}")
    public Integer defaultMaxResultSize;
}

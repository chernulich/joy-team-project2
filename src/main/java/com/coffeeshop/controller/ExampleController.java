package com.coffeeshop.controller;

import com.coffeeshop.model.entity.Example;
import com.coffeeshop.model.web.ExampleDto;
import com.coffeeshop.repository.ExampleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ExampleController {

    @Autowired
    private ExampleRepository exampleRepository;

    @GetMapping("/examples")
    public List<Example> getExamples() {

        return exampleRepository.findAll();
    }

    @PostMapping("/examples")
    public Example save(@RequestBody ExampleDto exampleDto) {
        return exampleRepository.save(
                Example.builder()
                        .name(exampleDto.getName())
                        .build());
    }

    @GetMapping("/examples/{id}")
    public Example getById(@PathVariable("id") Long id) {
        return exampleRepository.findById(id).orElse(null);
    }

}

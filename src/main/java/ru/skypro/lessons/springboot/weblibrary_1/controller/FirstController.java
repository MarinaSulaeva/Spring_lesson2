package ru.skypro.lessons.springboot.weblibrary_1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class FirstController {

    @GetMapping
    public String showHelloWorld() {
        return "Hello World!";
    }
}

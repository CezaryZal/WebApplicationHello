package io.github.mat3e;

import java.util.Optional;

public class HelloService {
    static final String FALLBACK_NAME = "word";

    String prepareGreeting (String name){
        return "hello " + Optional.ofNullable(name).orElse(FALLBACK_NAME) + "?!";
        // tworzymy optional z wartosci, który może byc nullem
    }
}

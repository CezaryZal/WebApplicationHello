package io.github.mat3e.lang;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

class LangService {
    private LangRepository repository;
    private LangDTO langDTO;

    LangService() {
        this(new LangRepository());
    }

    LangService(LangRepository repository) {
        this.repository = repository;
    }

    List<LangDTO> findAll() {
        return repository
                .findAll()
                .stream()
                .map(LangDTO::new)
                .collect(toList());
    }
}

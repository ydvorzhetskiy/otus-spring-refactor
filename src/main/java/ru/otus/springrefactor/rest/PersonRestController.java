package ru.otus.springrefactor.rest;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.springrefactor.domain.Person;
import ru.otus.springrefactor.service.PersonService;

@RequiredArgsConstructor
@RestController
public class PersonRestController {

    private final PersonService service;

    @GetMapping("/api/person/{id}")
    public Person getById(@PathVariable("id") long id) {
        return service.findById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/api/person")
    public Person save(@RequestBody Person person) {
        return service.save(person);
    }
}

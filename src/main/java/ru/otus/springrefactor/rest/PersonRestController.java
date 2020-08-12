package ru.otus.springrefactor.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.springrefactor.domain.Person;
import ru.otus.springrefactor.service.PersonService;

@RestController
public class PersonRestController {

    @Autowired
    private PersonService service;

    @RequestMapping(value = "/api/person/{id}", method = RequestMethod.GET)
    public Person getById(@PathVariable("id") long id) {
        return service.findById(id);
    }

    @RequestMapping(value = "/api/person", method = RequestMethod.POST)
    public ResponseEntity<Person> save(@RequestBody Person person) {
        Person saved = service.save(person);
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(saved);
    }
}

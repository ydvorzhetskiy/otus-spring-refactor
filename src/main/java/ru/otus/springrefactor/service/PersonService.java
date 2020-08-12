package ru.otus.springrefactor.service;

import ru.otus.springrefactor.domain.Person;

public interface PersonService {

    Person save(Person person);

    Person findById(long id);
}

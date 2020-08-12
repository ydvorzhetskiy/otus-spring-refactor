package ru.otus.springrefactor.repository;

import ru.otus.springrefactor.domain.Person;

public interface PersonRepository {

    Person save(Person person);

    Person findById(long id);
}

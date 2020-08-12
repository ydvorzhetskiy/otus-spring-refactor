package ru.otus.springrefactor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.springrefactor.domain.Person;
import ru.otus.springrefactor.repository.PersonRepository;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository repository;

    @Override
    public Person save(Person person) {
        return repository.save(person);
    }

    @Override
    public Person findById(long id) {
        return repository.findById(id);
    }
}

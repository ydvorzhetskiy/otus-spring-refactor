package ru.otus.springrefactor.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.springrefactor.domain.Person;
import ru.otus.springrefactor.repository.PersonRepository;

@RequiredArgsConstructor
@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository repository;

    @Override
    public Person save(Person person) {
        return repository.save(person);
    }

    @Override
    public Person findById(long id) {
        return repository.findById(id);
    }
}

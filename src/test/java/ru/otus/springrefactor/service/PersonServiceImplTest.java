package ru.otus.springrefactor.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.springrefactor.domain.Person;
import ru.otus.springrefactor.repository.PersonRepository;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;

@DisplayName("Сервис PersonServiceImpl")
@ExtendWith(MockitoExtension.class)
class PersonServiceImplTest {

    @Mock
    private PersonRepository repository;

    private PersonService personService;

    @BeforeEach
    void setUp() {
        this.personService = new PersonServiceImpl(repository);
    }

    @DisplayName("должен вызвать метод save репозитория")
    @Test
    void save() {
        personService.save(person());

        verify(repository).save(any());
    }

    @DisplayName("должен вызвать метод findById репозитория")
    @Test
    void findById() {
        personService.findById(11);

        verify(repository).findById(anyLong());
    }

    private Person person() {
        return new Person("Ivan");
    }
}

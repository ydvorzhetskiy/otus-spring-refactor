package ru.otus.springrefactor.repository;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.springrefactor.domain.Person;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Репозиторий для Person")
@SpringBootTest
class PersonRepositoryTest {

    @Autowired
    private PersonRepository repository;

    @DisplayName("корректно сохраняет сущность")
    @Test
    void save() {
        val newPerson = repository.save(person("Ivan"));
        assertThat(newPerson.getId()).isNotZero();
    }

    @DisplayName("должен сохранять сущность")
    @Test
    void findById() {
        val newPerson = repository.save(person("Ivan"));
        val foundById = repository.findById(newPerson.getId());
        assertThat(foundById)
            .isNotNull()
            .isEqualToComparingFieldByField(newPerson);
    }

    private Person person(String name) {
        return new Person(name);
    }
}

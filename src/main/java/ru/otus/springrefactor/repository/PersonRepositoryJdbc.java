package ru.otus.springrefactor.repository;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.springrefactor.domain.Person;

import static java.util.Collections.singletonMap;

@RequiredArgsConstructor
@Repository
public class PersonRepositoryJdbc implements PersonRepository {

    private static final RowMapper<Person> ROW_MAPPER = (resultSet, i) -> {
        long id = resultSet.getLong("id");
        String name = resultSet.getString("name");
        return new Person(id, name);
    };

    private final NamedParameterJdbcOperations jdbc;

    @Override
    public Person save(Person person) {
        val params = singletonMap("name", person.getName());
        jdbc.update("insert into persons (`name`) values (:name)", params);
        long newId = jdbc.getJdbcOperations()
                .queryForObject("select count(*) from persons", Integer.class);
        person.setId(newId);
        return person;
    }

    @Override
    public Person findById(long id) {
        val params = singletonMap("id", id);
        return jdbc.queryForObject(
                "select * from persons where id = :id", params, ROW_MAPPER
        );
    }
}

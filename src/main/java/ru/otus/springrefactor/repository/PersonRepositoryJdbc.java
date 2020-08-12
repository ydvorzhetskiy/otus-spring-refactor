package ru.otus.springrefactor.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.springrefactor.domain.Person;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Map;

@Repository
public class PersonRepositoryJdbc implements PersonRepository {

    private JdbcTemplate jdbc;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public PersonRepositoryJdbc(JdbcTemplate jdbc, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbc = jdbc;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        jdbc.execute("drop table if exists persons");
        jdbc.execute("create table persons(id bigint auto_increment primary key, name varchar(255))");
    }

    @Override
    public Person save(Person person) {
        jdbc.update("insert into persons (`name`) values (?)", person.getName());
        long newId = jdbc.queryForObject("select count(*) from persons", Integer.class);
        person.setId(newId);
        return person;
    }

    @Override
    public Person findById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        return namedParameterJdbcTemplate.queryForObject(
            "select * from persons where id = :id", params, new PersonMapper()
        );
    }

    private static class PersonMapper implements RowMapper<Person> {

        @Override
        public Person mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            return new Person(id, name);
        }
    }
}

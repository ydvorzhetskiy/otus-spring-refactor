package ru.otus.springrefactor.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    private long id;
    private String name;

    public Person(String name) {
        this.name = name;
    }
}

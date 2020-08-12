package ru.otus.springrefactor.rest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.springrefactor.domain.Person;
import ru.otus.springrefactor.service.PersonService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PersonRestController.class)
class PersonRestControllerTest {

    @MockBean
    private PersonService service;

    @Autowired
    private MockMvc mvc;

    @DisplayName("должен возвращать GET /api/person/{id}")
    @Test
    void getById() throws Exception {
        when(service.findById(anyLong()))
            .thenReturn(new Person(42, "Ivan"));

        mvc.perform(get("/api/person/42"))
            .andExpect(status().isOk())
            .andExpect(content().json("{\"id\": 42,\"name\":\"Ivan\"}"));
    }

    @DisplayName("должен выполнять POST /api/person")
    @Test
    void save() throws Exception {
        when(service.save(any()))
            .thenReturn(new Person(42, "Ivan"));

        mvc.perform(
            post("/api/person")
                .content("{\"name\":\"Ivan\"}")
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isCreated())
            .andExpect(content().json("{\"id\": 42,\"name\":\"Ivan\"}"));
    }
}

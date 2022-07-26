package com.bootcamp.client.service;

import com.bootcamp.client.model.Person;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PersonService {
    public Flux<Person> findAll();

    public Mono<Person> getPerson(String idPerson);

    public Mono<Person> save(Person person);

    public Mono<Person> update(Person person);

    public Mono<Person> logicDelete(String idPerson);
}

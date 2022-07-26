package com.bootcamp.client.service;

import com.bootcamp.client.model.Client;
import com.bootcamp.client.model.Person;
import com.bootcamp.client.repository.PersonRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PersonServiceImpl implements PersonService{

    private PersonRepository personRepository;

    @Override
    public Flux<Person> findAll() {
        return personRepository.findAll();
    }

    @Override
    public Mono<Person> getPerson(String idPerson) {
        Mono<Person> personMono = personRepository.findAll().filter(x -> x.getIdPerson().equals(idPerson)).next();
        return personMono;
    }

    @Override
    public Mono<Person> save(Person person) {
        return personRepository.save(person);
    }

    @Override
    public Mono<Person> update(Person person) {
        Mono<Person> personMono = personRepository.findAll().filter(x -> x.getIdPerson().equals(person.getIdPerson())).next();

        Person personbd = personMono.block();

        personbd.setFirstname(person.getFirstname());
        personbd.setLastname(person.getLastname());
        personbd.setDni(person.getDni());
        personbd.setClient(person.getClient());
        personbd.setState(person.getState());

        return personRepository.save(personbd);
    }

    @Override
    public Mono<Person> logicDelete(String idPerson) {
        Mono<Person> personMono = personRepository.findAll().filter(x -> x.getIdPerson().equals(idPerson)).next();
        Person person = personMono.block();
        person.setState(0);

        return personRepository.save(person);
    }
}

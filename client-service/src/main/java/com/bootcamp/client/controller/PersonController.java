package com.bootcamp.client.controller;

import com.bootcamp.client.model.Person;
import com.bootcamp.client.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.lang.invoke.MethodHandles;

@RestController
@RequestMapping("/person")
public class PersonController {
    @Autowired
    PersonService personService;

    private static final Logger LOGGER= LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @GetMapping(value = "/List")
    public Flux<Person> AllPeople() {
        //List all people
        LOGGER.info("List person");
        return personService.findAll();
    }

    @GetMapping(value = "/GetPerson/{id}")
    public Mono<Person> Inquiry(@PathVariable("id") String cod) {
        //List person by id
        LOGGER.info("List person by id");
        return personService.getPerson(cod);
    }

    @PostMapping(value = "/New")
    public Mono<Person> save(@RequestBody Person person){
        //Save Person
        LOGGER.info("Save");
        return personService.save(person);
    }

    @PutMapping(value = "/Update")
    public Mono<Person> update(@RequestBody Person person){
        //Update Person
        LOGGER.info("Update Person");
        return personService.update(person);
    }

    @PutMapping(value = "/Delete/{id}")
    public Mono<Person> logicDelete(@PathVariable("id")String cod){
        //Delete Person
        LOGGER.info("Delete Person");
        return personService.logicDelete(cod);
    }
}

package com.bootcamp.client.repository;

import com.bootcamp.client.model.Person;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface PersonRepository extends ReactiveCrudRepository<Person,String> {
}

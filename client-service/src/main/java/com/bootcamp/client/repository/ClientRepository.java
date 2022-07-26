package com.bootcamp.client.repository;

import com.bootcamp.client.model.Client;
import org.bson.types.ObjectId;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

public interface ClientRepository extends ReactiveCrudRepository<Client, String> {
}

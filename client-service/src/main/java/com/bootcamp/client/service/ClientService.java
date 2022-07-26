package com.bootcamp.client.service;

import com.bootcamp.client.model.Client;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ClientService {
    public Flux<Client> findAll();

    public Mono<Client> getClient(String idCliente);

    public Mono<Client> save(Client client);

    public Mono<Client> update(Client client);

    public Mono<Client> logicDelete(String idCliente);

}

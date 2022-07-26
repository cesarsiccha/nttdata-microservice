package com.bootcamp.client.service;

import com.bootcamp.client.model.Client;
import com.bootcamp.client.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;


    @Override
    public Flux<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public Mono<Client> getClient(String idCliente) {
        Mono<Client> client = clientRepository.findAll().filter(x -> x.getIdClient().equals(idCliente)).next();

        return client;
    }

    @Override
    public Mono<Client> save(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Mono<Client> update(Client client) {
        Mono<Client> clientMono = clientRepository.findAll().filter(x -> x.getIdClient().equals(client.getIdClient())).next();

        Client clientbd = clientMono.block();

        clientbd.setName(client.getName());
        clientbd.setCategoryClient(client.getCategoryClient());
        clientbd.setState(client.getState());

        return clientRepository.save(clientbd);
    }

    @Override
    public Mono<Client> logicDelete(String idCliente) {

        Mono<Client> clientMono = clientRepository.findAll().filter(x -> x.getIdClient().equals(idCliente)).next();
        Client client = clientMono.block();
        client.setState(0);

        return clientRepository.save(client);
    }
}

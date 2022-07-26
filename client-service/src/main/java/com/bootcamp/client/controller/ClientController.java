package com.bootcamp.client.controller;

import com.bootcamp.client.model.Client;
import com.bootcamp.client.service.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.lang.invoke.MethodHandles;

@Slf4j
@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    ClientService clientService;

    private static final Logger LOGGER= LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @GetMapping(value = "/List")
    public Flux<Client> AllClients() {
        //List all clients
        LOGGER.info("List Client");
        return clientService.findAll();
    }

    @GetMapping(value = "/GetClient/{id}")
    public Mono<Client> Inquiry(@PathVariable("id") String cod) {
        //List customers by code
        LOGGER.info("List Client by id");
        return clientService.getClient(cod);
    }

    @PostMapping(value = "/New")
    public Mono<Client> save(@RequestBody Client client){
        //Save Client
        LOGGER.info("Save Client");
        return clientService.save(client);
    }

    @PutMapping(value = "/Update")
    public Mono<Client> update(@RequestBody Client client){
        //Update Client
        LOGGER.info("Update Client");
        return clientService.update(client);
    }

    @PutMapping(value = "/Delete/{id}")
    public Mono<Client> logicDelete(@PathVariable("id")String cod){
        //Logic Delete
        LOGGER.info("Delete Client");
        return clientService.logicDelete(cod);
    }

}

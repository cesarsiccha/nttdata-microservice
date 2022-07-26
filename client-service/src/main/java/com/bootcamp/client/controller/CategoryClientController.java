package com.bootcamp.client.controller;

import com.bootcamp.client.model.CategoryClient;
import com.bootcamp.client.model.Client;
import com.bootcamp.client.service.CategoryClientService;
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
@RequestMapping("/categoryclient")
public class CategoryClientController {
    @Autowired
    CategoryClientService categoryClientService;

    private static final Logger LOGGER= LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @GetMapping(value = "/List")
    public Flux<CategoryClient> AllClients() {
        //List all clients
        LOGGER.info("List CatClient");
        return categoryClientService.findAll();
    }

    @PostMapping(value = "/New")
    public Mono<CategoryClient> save(@RequestBody CategoryClient categoryClient){
        //Save Client
        LOGGER.info("Save CatClient");
        return categoryClientService.save(categoryClient);
    }

    @PutMapping(value = "/Update")
    public Mono<CategoryClient> update(@RequestBody CategoryClient categoryClient){
        //Update Client
        LOGGER.info("Update CatClient");
        return categoryClientService.update(categoryClient);
    }

    @PutMapping(value = "/Delete/{id}")
    public Mono<CategoryClient> logicDelete(@PathVariable("id")String cod){
        //Delete Client
        LOGGER.info("Delete CatClient");
        return categoryClientService.logicDelete(cod);
    }
}

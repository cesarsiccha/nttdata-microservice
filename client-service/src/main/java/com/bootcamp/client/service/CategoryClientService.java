package com.bootcamp.client.service;

import com.bootcamp.client.model.CategoryClient;
import com.bootcamp.client.model.Client;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CategoryClientService {
    public Flux<CategoryClient> findAll();

    public Mono<CategoryClient> save(CategoryClient categoryClient);

    public Mono<CategoryClient> update(CategoryClient categoryClient);

    public Mono<CategoryClient> logicDelete(String idCategoryClient);
}

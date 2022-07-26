package com.bootcamp.client.service;

import com.bootcamp.client.model.CategoryClient;
import com.bootcamp.client.model.Client;
import com.bootcamp.client.repository.CategoryClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CategoryClientServiceImpl implements CategoryClientService{
    @Autowired
    private CategoryClientRepository categoryClientRepository;
    @Override
    public Flux<CategoryClient> findAll() {
        return categoryClientRepository.findAll();
    }

    @Override
    public Mono<CategoryClient> save(CategoryClient categoryClient) {
        return categoryClientRepository.save(categoryClient);
    }

    @Override
    public Mono<CategoryClient> update(CategoryClient categoryClient) {
        Mono<CategoryClient> categoryClientMono = categoryClientRepository.findAll().filter(x -> x.getIdCategoryClient().equals(categoryClient.getIdCategoryClient())).next();

        CategoryClient clientbd = categoryClientMono.block();

        clientbd.setDescription(categoryClient.getDescription());
        clientbd.setState(categoryClient.getState());

        return categoryClientRepository.save(clientbd);
    }

    @Override
    public Mono<CategoryClient> logicDelete(String idCategoryClient) {
        Mono<CategoryClient> categoryClientMono = categoryClientRepository.findAll().filter(x -> x.getIdCategoryClient().equals(idCategoryClient)).next();
        CategoryClient categoryClient = categoryClientMono.block();
        categoryClient.setState(0);

        return categoryClientRepository.save(categoryClient);
    }
}

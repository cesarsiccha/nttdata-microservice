package com.bootcamp.product.service;

import com.bootcamp.product.model.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {

    public Flux<Product> findAll();

    public Mono<Product> getProduct(String idProduct);

    public Mono<Product> save(Product product);

    public Mono<Product> update(Product product);

    public Mono<Product> logicDelete(String idProduct);

}

package com.bootcamp.product.service;

import com.bootcamp.product.model.CategoryProduct;
import com.bootcamp.product.model.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CategoryProductService {
    public Flux<CategoryProduct> findAll();

    public Mono<CategoryProduct> save(CategoryProduct categoryProduct);

    public Mono<CategoryProduct> update(CategoryProduct categoryProduct);

    public Mono<CategoryProduct> DeleteCatProduct(String id);
}

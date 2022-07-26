package com.bootcamp.product.service;

import com.bootcamp.product.model.DetailProduct;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface DetailProductService {
    public Flux<DetailProduct> findAll();

    public Mono<DetailProduct> save(DetailProduct detailProduct);

    public Mono<DetailProduct> update(DetailProduct detailProduct);

    public Mono<DetailProduct> DeleteDetProduct(String id);
}

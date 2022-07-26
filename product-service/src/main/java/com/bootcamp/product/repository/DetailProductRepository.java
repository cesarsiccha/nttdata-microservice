package com.bootcamp.product.repository;

import com.bootcamp.product.model.DetailProduct;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface DetailProductRepository extends ReactiveCrudRepository<DetailProduct,String> {
}

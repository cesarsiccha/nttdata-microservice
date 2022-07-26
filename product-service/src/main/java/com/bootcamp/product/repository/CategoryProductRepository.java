package com.bootcamp.product.repository;

import com.bootcamp.product.model.CategoryProduct;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface CategoryProductRepository extends ReactiveCrudRepository<CategoryProduct,String> {
}

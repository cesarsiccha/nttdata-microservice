package com.bootcamp.client.repository;

import com.bootcamp.client.model.CategoryClient;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface CategoryClientRepository extends ReactiveCrudRepository<CategoryClient,String> {
}

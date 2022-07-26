package com.bootcamp.credit.repository;

import com.bootcamp.credit.model.Credit;
import org.bson.types.ObjectId;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditRepository extends ReactiveCrudRepository<Credit, ObjectId> {
}

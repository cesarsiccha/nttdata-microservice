package com.bootcamp.account.repository;

import org.bson.types.ObjectId;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.bootcamp.account.model.AccountsRelation;

@Repository
public interface AccountRelationRepository extends ReactiveCrudRepository<AccountsRelation, ObjectId>{

}

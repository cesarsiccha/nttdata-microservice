package com.bootcamp.account.repository;

import org.bson.types.ObjectId;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.bootcamp.account.model.Account;

@Repository
public interface AccountRepository extends ReactiveCrudRepository<Account, ObjectId>{
	
	

}

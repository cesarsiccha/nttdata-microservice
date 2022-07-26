package com.bootcamp.bootcoin.repository;

import org.bson.types.ObjectId;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.bootcamp.bootcoin.model.BootCoin;


@Repository
public interface BootcoinRepository extends ReactiveCrudRepository<BootCoin, String> {

}

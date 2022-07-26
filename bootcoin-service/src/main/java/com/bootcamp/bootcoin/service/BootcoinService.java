package com.bootcamp.bootcoin.service;

import com.bootcamp.bootcoin.model.BootCoin;

import com.bootcamp.bootcoin.model.Client;
import com.bootcamp.bootcoin.model.Transaction;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BootcoinService {

	Mono<BootCoin> NewChange(double typechange);

	Mono<BootCoin> ListById();

	Flux<BootCoin> ListAll();

	String RegisterUser(Client client);

	String BuyBootCoin(Transaction transaction);

	String SellBootCoin(Transaction transaction);

}

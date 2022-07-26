package com.bootcamp.credit.service;

import com.bootcamp.credit.model.Credit;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CreditService {

    public Flux<Credit> findAll();

    public Mono<Credit> save(Credit credit);

    public Mono<Double> Inquiry(String prod,String number);

    public Mono<Credit> getCreditbyCodCli(String cod);

    public Mono<Credit> getReport();

    public Mono<Credit> getDebit(String cod);

    public Mono<Void> delete(String prod,String number);

}

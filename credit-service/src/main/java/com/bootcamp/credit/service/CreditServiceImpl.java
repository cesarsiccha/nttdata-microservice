package com.bootcamp.credit.service;

import com.bootcamp.credit.model.Credit;
import com.bootcamp.credit.repository.CreditRepository;
import com.mongodb.MongoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.apache.log4j.Logger;

@Service
public class CreditServiceImpl implements CreditService {

    @Autowired
    private CreditRepository creditRepository;

    private static  Logger LogJava = Logger.getLogger(CreditServiceImpl.class);

    @Override
    public Flux<Credit> findAll() {
        LogJava.info("List all Credits");
        return creditRepository.findAll();
    }

    @Override
    public Mono<Credit> save(Credit credit) {
        try{
            LogJava.info("Save");
            return creditRepository.save(credit);
        }catch (MongoException e){
            LogJava.error("Error in Save - Mongo - "+e.getMessage());
            return null;
        }
    }

    @Override
    public Mono<Double> Inquiry(String pro, String number) {
        LogJava.info("Inquiry");

        Mono<Credit> creditMono = creditRepository.findAll().filter(x -> x.getDetailProduct().equals(pro)
                && x.getNumber().equals(number)
        ).next();

        return creditMono.map( r -> r.getAmount());
    }

    @Override
    public Mono<Credit> getCreditbyCodCli(String cod) {
        LogJava.info("Get Credits by CodCli");

        Mono<Credit> creditMono = creditRepository.findAll().filter(x -> x.getIdClient().equals(cod)
        ).next();

        return creditMono;
    }

    @Override
    public Mono<Credit> getReport() {

        LogJava.info("Get Report");

        Mono<Credit> creditMono = creditRepository.findAll().next();

        Credit Cred = creditMono.block();

        Cred.getAmount();

        return creditMono;
    }

    @Override
    public Mono<Credit> getDebit(String cod) {
        LogJava.info("Generate Debt");

        Mono<Credit> creditMono = creditRepository.findAll().filter(x -> x.getIdClient().equals(cod)
                && x.getFlag() == 0
        ).next();

        return creditMono;

    }

    @Override
    public Mono<Void> delete(String prod, String number) {
        LogJava.info("Delete Credit");

        Mono<Credit> creditMono = creditRepository.findAll().filter(x -> x.getDetailProduct().equals(prod)
                && x.getNumber().equals(number)
        ).next();

        Credit Credit = creditMono.block();

        return creditRepository.delete(Credit);
    }
}

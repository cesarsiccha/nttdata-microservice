package com.bootcamp.bootcoin.controller;

import com.bootcamp.bootcoin.model.Client;
import com.bootcamp.bootcoin.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.bootcamp.bootcoin.model.BootCoin;
import com.bootcamp.bootcoin.service.BootcoinService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping(value="/bootcoin")
@CrossOrigin("*")
public class BootcoinController {

	@Autowired
    BootcoinService bootcoinService;
	
	@PostMapping(value = "/NewChange/{typechange}")
    public Mono<BootCoin> NewChange(@PathVariable("typechange") double typechange) {
        return bootcoinService.NewChange(typechange);
    }
	
	@GetMapping(value = "/ListById")
    public Mono<BootCoin> ListById() {
        return bootcoinService.ListById();
    }
	
	@GetMapping(value = "/listAll")
    public Flux<BootCoin> ListAll() {
        return bootcoinService.ListAll();
    }
	
	@PostMapping(value = "/RegistryUser")
    public String RegisterUser(@RequestBody Client client) {
        return bootcoinService.RegisterUser(client);
    }

    @PostMapping(value = "/BuyBootCoin")
    public String BuyBootCoin(@RequestBody Transaction transaction) {
        return bootcoinService.BuyBootCoin(transaction);
    }

    @PostMapping(value = "/SellBootCoin")
    public String Sold_BootCoin(@RequestBody Transaction transaction) {
        return bootcoinService.SellBootCoin(transaction);
    }
	
}

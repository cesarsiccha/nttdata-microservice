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
	
	@PostMapping(value = "/CurrencyChange/{typechange}")
    public Mono<BootCoin> Currency_Change(@PathVariable("typechange") double typechange) {
        //List all Commissions
        return bootcoinService.NewChange(typechange);
    }
	
	@GetMapping(value = "/Inquiry")
    public Mono<BootCoin> ListById() {
        //List all Commissions
        return bootcoinService.ListById();
    }
	
	@GetMapping(value = "/InquiryAll")
    public Flux<BootCoin> InquiryAll() {
        //List all Commissions
        return bootcoinService.ListAll();
    }
	
	@PostMapping(value = "/RegistryUser")
    public String Registry_User(@RequestBody Client client) {
        //List all Commissions
        return bootcoinService.RegisterUser(client);
    }

    @PostMapping(value = "/BuyBootCoin")
    public String Buy_BootCoin(@RequestBody Transaction transaction) {
        //List all Commissions
        return bootcoinService.BuyBootCoin(transaction);
    }

    @PostMapping(value = "/SellBootCoin")
    public String Sold_BootCoin(@RequestBody Transaction transaction) {
        //List all Commissions
        return bootcoinService.SellBootCoin(transaction);
    }
	
}

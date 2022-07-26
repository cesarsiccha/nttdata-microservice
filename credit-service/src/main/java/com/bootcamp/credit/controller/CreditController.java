package com.bootcamp.credit.controller;

import com.bootcamp.credit.model.Credit;
import com.bootcamp.credit.service.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value="/credit")
public class CreditController {

    @Autowired
    CreditService creditService;

    @GetMapping(value = "/list")
    public Flux<Credit> AllCredits() {
        //List all credits
        return creditService.findAll();
    }

    @PostMapping(value = "/new")
    public Mono<Credit> save(@RequestBody Credit credit){
        //Register new credit
        return creditService.save(credit);
    }

    @GetMapping(value = "/inquiry/{prod}/{num}")
    public Mono<Double> Inquiry(@PathVariable("pro") String pro, @PathVariable("num") String num) {
        //Balance inquiry
        return creditService.Inquiry(pro, num);
    }

    @GetMapping(value = "/GetCreditsbyCodCli/{cod}")
    public Mono<Credit> GetCreditsbyCodCli(@PathVariable("cod") String cod) {
        //Get Credit by CodCli
        return creditService.getCreditbyCodCli(cod);
    }

    @GetMapping(value = "/GetReport")
    public Mono<Credit> GetReport() {
        //Get Report Credit
        return creditService.getReport();
    }

    @GetMapping(value = "/Getdebit/{cod}")
    public Mono<Credit> Getdebit(@PathVariable("cod") String cod) {
        //Get Report Debt
        return creditService.getDebit(cod);
    }

    @DeleteMapping(value="/Delete/{pro}/{num}")
    public Mono<Void> Delete(@PathVariable("pro") String pro, @PathVariable("num") String num){
        //Delete Credit
        return creditService.delete(pro, num);
    }

}

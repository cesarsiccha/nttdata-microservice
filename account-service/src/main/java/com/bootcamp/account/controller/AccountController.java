package com.bootcamp.account.controller;

import java.util.Map;

import com.bootcamp.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import com.bootcamp.account.Entity.AccountObj;
import com.bootcamp.account.model.Account;


@RestController
@RequestMapping(value="/account")
public class AccountController {

	@Autowired
	AccountService accountService;
	
	public static final String SAV_SERVICE = "savingService";

	@GetMapping(value = "/AccountsbyCodcli/{codcli}")
	public Flux<Account> Accounts_byCodcli(@PathVariable("codcli") String codcli) {
		//List of savings by Codcli
		return accountService.AccountsbyCodcli(codcli);
	}
	
	@GetMapping(value = "/list")
	public Flux<Account> AllAccounts() {
		//list all savings accounts
		return accountService.AllAccounts();
	}
	
	@GetMapping(value = "/Inquiry/{pro}/{num}")
	public Mono<Double> Inquiry(@PathVariable("prod") String prod,@PathVariable("num") String num) {
		//Balance inquiry
		return accountService.Inquiry(prod,num);
	}

	@PutMapping(value = "/AmountUpdate/{prod}/{num}/{newamou}")
	@CircuitBreaker(name = SAV_SERVICE,fallbackMethod = "getInquiryZero")
	public Mono<Account> AmountUpdate(@PathVariable("prod")String prod,
                                      @PathVariable("num") String Number,
                                      @PathVariable("newamou") double NewAmou) throws Exception{
		//Amount update of a Saving Account 
		return accountService.AmountUpdate(prod, Number, NewAmou);
	}

	
	@PostMapping(value="/Open")
    public Mono<Account> Open(@RequestBody AccountObj Sav){
		//Open a Saving Account 
        return accountService.OpenAccount(Sav);
    }
	
	@DeleteMapping(value="/Close/{pro}/{num}")
    public Mono<Account> Close(@PathVariable("pro") String pro, @PathVariable("num") String num){
		//Close a Saving Account 
        return accountService.CloseAccount(pro,num);
    }
	
	@DeleteMapping(value="/Delete/{pro}/{num}")
    public Mono<Void> Delete(@PathVariable("pro") String pro,@PathVariable("num") String num){
		//Delete a Saving Account 
        return accountService.DeleteAccount(pro, num);
    }
}

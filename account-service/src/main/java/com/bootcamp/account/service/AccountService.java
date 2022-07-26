package com.bootcamp.account.service;


import java.util.Map;

import com.bootcamp.account.Entity.AccountObj;
import com.bootcamp.account.model.Account;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AccountService {

	//Methods Declaration

	public Flux<Account> AllAccounts();
	public Flux<Account> AccountsbyCodcli(String codcli);

	public Mono<Account> OpenAccount (AccountObj account);
	
	public Mono<Account> CloseAccount (String prod,String number);
	
	public Mono<Void> DeleteAccount (String prod,String number);
	
	public Mono<Double> Inquiry(String prod, String number);

	public Mono<Integer> GetTypeAccount(String prod, String number);
	
	public Mono<Account> AmountUpdate(String prod, String Number, double amount) throws Exception;
	
}
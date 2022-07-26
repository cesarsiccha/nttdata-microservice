package com.bootcamp.account.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootcamp.account.Entity.AccountObj;
import com.bootcamp.account.model.AccountsRelation;
import com.bootcamp.account.model.Account;
import com.bootcamp.account.repository.AccountRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private AccountRelationService accountRelationService;
	
	
	private static Logger LogJava = Logger.getLogger(AccountServiceImpl.class);

	@Override
	public Flux<Account> AccountsbyCodcli(String codcli) {
		
		List<AccountsRelation> accountsRelations = accountRelationService.GetAccountSavList(codcli);
		
		List<Account> accounts = new ArrayList<Account>();

		accountsRelations.forEach(x -> accounts.add(GetSavingByProCurNum(x.getProduct(),x.getNumber())));
		
		Mono<List<Account>> MLSav = Mono.just(accounts);
		
		return MLSav.flatMapMany(Flux::fromIterable);
				
		
	}
	
	public Account GetSavingByProCurNum (String Product, String Number){

		return accountRepository.findAll().filter(x -> x.getDetailProduct().equals(Product)
				&& x.getNumber().equals(Number)
				).next().block();
	}
	@Override
	public Flux<Account> AllAccounts() {
		LogJava.info("List all Savings Account");
		return accountRepository.findAll();
	}

	@Override
	public Mono<Double> Inquiry(String prod, String number) {
		LogJava.info("Inquiry");
		
		Mono<Account> accountMono = accountRepository.findAll().filter(x -> x.getDetailProduct().equals(prod)
				&& x.getNumber().equals(number)
				).next();
		
		return accountMono.map( r -> r.getAmount());
		
	}

	@Override
	public Mono<Integer> GetTypeAccount(String pro, String Number) {
		LogJava.info("Inquiry");

		Mono<Account> accountMono = accountRepository.findAll().filter(x -> x.getDetailProduct().equals(pro)
				&& x.getNumber().equals(Number)
				).next();

		return accountMono.map( r -> r.getType());

	}

	@Override
	public Mono<Account> AmountUpdate(String pro, String Number, double amount) throws Exception {
		LogJava.info("AmountUpdate");
		
		Mono<Account> accountMono = accountRepository.findAll().filter(x -> x.getDetailProduct().equals(pro)
				&& x.getNumber().equals(Number)
				).next();
	
		Account account = new Account();
		
		if (accountMono != null) {
			account = accountMono.block();

			account.setAmount(amount);
		}
		
		//throw new  Exception("asd");
		
		return accountRepository.save(account);
	}


	@Override
	public Mono<Account> OpenAccount(AccountObj account) {
		LogJava.info("Open Account");
		
		boolean flag;
		
		//business condition
		
		if (account.getTypeCLi()==1) {

			flag = PersonType(account.getCodTit());
		}else {
			flag = BusinessType(account.getCodTit());
		}
		
		Account account1 = account.GetSaving();
		
		
		if (flag) {
			Flux.fromIterable(account.GetRelation()).flatMap(R ->
			Flux.concat(accountRelationService.save(R))).subscribe();
			
			return accountRepository.save(account1);
		}else {
			return null;
		}
	}

	private boolean BusinessType(List<String> codTit) {
		
		for(String cc: codTit) {
			//Accounts List
			Flux<AccountsRelation> accountsRelationFlux = accountRelationService.GetAccountSav(cc);
						
			boolean bool = accountsRelationFlux.map(f -> GetTypeAccount(f.getProduct(),f.getNumber()))
				.filter(x -> x.block()==1 || x.block()==2).collect(Collectors.toSet()).block().size()==2;
			
			if (bool) {
				return false;
			}
		}
		
		return true;
		
		
	}
	
	private boolean PersonType(List<String> codTit) {

		return accountRelationService.PerCond(codTit);
	}

	@Override
	public Mono<Account> CloseAccount(String pro, String Number) {
		LogJava.info("Close Saving");
		
		Mono<Account> accountMono = accountRepository.findAll().filter(x -> x.getDetailProduct().equals(pro)
				&& x.getNumber().equals(Number)
				).next();
		
		
		Account account = accountMono.block();

		account.setState(0);
		
		return accountRepository.save(account);
	}


	@Override
	public Mono<Void> DeleteAccount(String pro,String Number) {
		LogJava.info("Delete Saving");
		
		Mono<Account> accountMono = accountRepository.findAll().filter(x -> x.getDetailProduct().equals(pro)
				&& x.getNumber().equals(Number)
				).next();
		
		
		Account account = accountMono.block();
		
		return accountRepository.delete(account);
	}

			
}

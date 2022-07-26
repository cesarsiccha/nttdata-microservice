package com.bootcamp.account.service;

import java.util.List;

import com.bootcamp.account.model.AccountsRelation;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AccountRelationService {

	public boolean PerCond(List<String> codTit);
	
	public Flux<AccountsRelation> GetAccountSav(String codcli);
	
	public List<AccountsRelation> GetAccountSavList(String codcli);
	
	public Mono<AccountsRelation> save(AccountsRelation AR);
	
}

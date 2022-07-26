package com.bootcamp.account.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import com.bootcamp.account.model.AccountsRelation;
import com.bootcamp.account.repository.AccountRelationRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AccountRelationServiceImpl implements AccountRelationService {

	@Autowired
	private AccountRelationRepository accountRelationRepository;
	
	@Autowired
	MongoTemplate mongoTemplate;
		
	@Override
	public boolean PerCond(List<String> codTit) {
		for (String codcli: codTit) {
			
			Mono<AccountsRelation> Obj1 = accountRelationRepository.findAll().filter(x -> x.getCodClient().equals(codcli)
					).next();
			
			if (Obj1 != null) {
				return  true;
			}
		}
		
		return false;
	}
	
	@Override
	public Mono<AccountsRelation> save(AccountsRelation AR) {
		return accountRelationRepository.save(AR);
		
	}

	@Override
	public Flux<AccountsRelation> GetAccountSav(String codcli) {
		Flux<AccountsRelation> Obj1 = accountRelationRepository.findAll().filter(x -> x.getCodClient().equals(codcli)
				);
		
		return Obj1;
	}

	@Override
	public List<AccountsRelation> GetAccountSavList(String codcli) {
		MatchOperation comparisonOperators = Aggregation.match(Criteria.where("CodClient").is(codcli));
		Aggregation aggregationAll = Aggregation.newAggregation(comparisonOperators);
		
		AggregationResults<AccountsRelation> resultsAll =
				mongoTemplate.aggregate(aggregationAll,"Accounts_Relationship", AccountsRelation.class);
		
		return resultsAll.getMappedResults();
		
	}

}

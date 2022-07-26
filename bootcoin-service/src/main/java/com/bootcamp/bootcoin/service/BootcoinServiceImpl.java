package com.bootcamp.bootcoin.service;

import java.util.UUID;

import com.bootcamp.bootcoin.model.Client;
import com.bootcamp.bootcoin.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.bootcamp.bootcoin.model.BootCoin;
import com.bootcamp.bootcoin.repository.BootcoinRepository;
import com.bootcamp.bootcoin.util.BootCoinCreatedEvent;
import com.bootcamp.bootcoin.util.Event;
import com.bootcamp.bootcoin.util.EventType;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class BootcoinServiceImpl implements BootcoinService {

	@Autowired
	private BootcoinRepository bootcoinRepository;
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	@Autowired
	private ReactiveStringRedisTemplate redisTemplate;
	
	@Autowired
	private KafkaTemplate<String, Event<?>> producer;
	
	@Value("${topic.customer.name:bootcointopic}")
	private String bootcoinTopic;
	
	@Override
	public Mono<BootCoin> NewChange(double typechange) {
		BootCoin bootCoin = new BootCoin();

		bootCoin.setChangeType(typechange);
		bootCoin.setState(1);
		
		return bootcoinRepository.save(bootCoin);
		
		
	}

	@Override
	public Mono<BootCoin> ListById() {
		AggregationResults<BootCoin> resultsAll = ResultAll();
		
		Mono<BootCoin> Mbc;
		
		try {
			Mbc = Mono.just(resultsAll.getMappedResults().get(0));
		}catch(Exception e){
			Mbc=null;
		}
		
		return Mbc;
		
	}
	
	private AggregationResults<BootCoin> ResultAll(){
		SortOperation sortOperation = Aggregation.sort(Sort.by(Sort.Direction.DESC,"DateCreate"));
		Aggregation aggregationAll = Aggregation.newAggregation(sortOperation);
		AggregationResults<BootCoin> resultsAll =
				mongoTemplate.aggregate(aggregationAll,"BootCoin", BootCoin.class);
		
		return resultsAll;
	}

	@Override
	public Flux<BootCoin> ListAll() {
		return bootcoinRepository.findAll().map(x -> {
			log.info("Previous value {}",redisTemplate.opsForHash().get("listAll","name"));
			redisTemplate.opsForHash().put("listAll","name", x);
			return x;
		});	
	}

	@Override
	public String RegisterUser(Client client) {
		client.setType(1);
		client.setState(1);

		Event event = new BootCoinCreatedEvent();

		event.setData(client);
		event.setId(UUID.randomUUID().toString());
		event.setType(EventType.CREATED);

		Message<Event> message = MessageBuilder
				.withPayload(event)
				.setHeader(KafkaHeaders.TOPIC, bootcoinTopic)
				.build();

		this.producer.send(message);
		
		return "User registry";
	}

	@Override
	public String BuyBootCoin(Transaction transaction) {
		transaction.setType(1);

		Event event = new BootCoinCreatedEvent();

		event.setData(transaction);
		event.setId(UUID.randomUUID().toString());
		event.setType(EventType.CREATED);

		Message<Event> message = MessageBuilder
				.withPayload(event)
				.setHeader(KafkaHeaders.TOPIC, bootcoinTopic)
				.build();

		this.producer.send(message);

		return "Purchase successful";
	}

	@Override
	public String SellBootCoin(Transaction transaction) {
		transaction.setType(2);

		Event event = new BootCoinCreatedEvent();

		event.setData(transaction);
		event.setId(UUID.randomUUID().toString());
		event.setType(EventType.CREATED);

		Message<Event> message = MessageBuilder
				.withPayload(event)
				.setHeader(KafkaHeaders.TOPIC, bootcoinTopic)
				.build();

		this.producer.send(message);

		return "Sell successful";
	}


}

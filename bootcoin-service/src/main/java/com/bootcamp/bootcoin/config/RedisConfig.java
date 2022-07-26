package com.bootcamp.bootcoin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

@Configuration
public class RedisConfig {

	@Bean
	public ReactiveRedisConnectionFactory reactiveRedisConnectionFactory() {
	    return new LettuceConnectionFactory("localhost", 6379);
	}
	
}

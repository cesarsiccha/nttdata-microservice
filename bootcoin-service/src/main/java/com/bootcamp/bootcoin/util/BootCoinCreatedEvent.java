package com.bootcamp.bootcoin.util;

import com.bootcamp.bootcoin.model.BootCoin;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class BootCoinCreatedEvent extends Event<BootCoin>{

}

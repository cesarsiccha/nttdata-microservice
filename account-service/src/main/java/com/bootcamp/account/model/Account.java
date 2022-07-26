package com.bootcamp.account.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data @AllArgsConstructor @NoArgsConstructor
@Document(collection = "account")
public class Account implements Serializable  {

	@Id
	private String idAccount;
	private String detailProduct;
	private String number;
	private double amount;
	private Date dateCreate;
	private int type;
	private int state;
	
	
}

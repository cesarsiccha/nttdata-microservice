package com.bootcamp.account.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
@Document(collection = "Accounts_Relationship")
public class AccountsRelation implements Serializable  {

	@Id
	private String idAccountRel;
	private String Product;
	private String Number;
	private String CodClient;
	private int RelCod;
	
}

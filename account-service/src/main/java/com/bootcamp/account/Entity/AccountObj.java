package com.bootcamp.account.Entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.bootcamp.account.model.AccountsRelation;
import com.bootcamp.account.model.Account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class AccountObj {

	private String detailProduct;
	private String number;
	private double amount;
	private int state;
	private Date dateCreate;
	private int type;
	
	List<String> CodTit;
	List<String> CodSig;
	
	private int typeCLi;
	
	public Account GetSaving() {
		Account account = new Account();

		account.setDetailProduct(this.detailProduct);
		account.setNumber(this.number);
		account.setAmount(this.amount);
		account.setState(this.state);
		account.setDateCreate(this.dateCreate);
		account.setType(this.type);
		
		return account;
	}
	
	public List<AccountsRelation> GetRelation() {
		
		ArrayList<AccountsRelation> ARS = new ArrayList<AccountsRelation>();
		
		for (String CodTitT : CodTit) {
			AccountsRelation AR = new AccountsRelation();
			
			AR.setCodClient(CodTitT);
			AR.setNumber(this.number);
			AR.setProduct(this.detailProduct);
			AR.setRelCod(1);
			
			ARS.add(AR);
		}
		
		for (String CodSigT : CodSig) {
			AccountsRelation AR = new AccountsRelation();
			
			AR.setCodClient(CodSigT);
			AR.setNumber(this.number);
			AR.setProduct(this.detailProduct);
			AR.setRelCod(2);
			
			ARS.add(AR);
		}
		
		
		return ARS;
	}
	
}

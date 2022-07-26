package com.bootcamp.product.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@AllArgsConstructor @NoArgsConstructor
@Document("DetailProduct")
public class DetailProduct implements Serializable {
    @Id
    private String idDetailProduct;
    private String account;
    private String amount;
    private String client;
    private String product;

    private int state;
}

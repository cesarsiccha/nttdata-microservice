package com.bootcamp.product.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@AllArgsConstructor @NoArgsConstructor
@Document("Product")
public class Product implements Serializable {

    @Id
    private String idProduct;
    private String name;
    private String description;
    private String categoryProduct;
    private int state;

}

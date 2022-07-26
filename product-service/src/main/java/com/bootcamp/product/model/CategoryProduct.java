package com.bootcamp.product.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@AllArgsConstructor @NoArgsConstructor
@Document("CategoryProduct")
public class CategoryProduct implements Serializable {

    @Id
    private String idCategoryProduct;
    private String description;
    private int state;
}

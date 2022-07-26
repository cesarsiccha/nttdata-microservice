package com.bootcamp.credit.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

@Data @AllArgsConstructor @NoArgsConstructor
@Document(collection = "Credit")
public class Credit implements Serializable {

    @Id
    private String idCredit;
    private String detailProduct;
    private String number;
    private String idClient;
    private Double Amount;
    private Date date;
    private int flag;
    private int state;

}

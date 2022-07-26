package com.bootcamp.client.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@AllArgsConstructor @NoArgsConstructor
@Document(collection = "Clients")
public class Client implements Serializable {

    @Id
    private String idClient;
    private String name;
    private String categoryClient;
    private int state;

}

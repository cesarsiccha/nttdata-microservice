package com.bootcamp.bootcoin.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Client")
public class Client {
    @Id
    private String idClient;
    private String name;
    private String lasName;
    private String dni;
    private String email;
    private String telephone;
    private int state;
    private int Type;

}

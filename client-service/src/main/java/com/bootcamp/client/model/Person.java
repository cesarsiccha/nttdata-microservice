package com.bootcamp.client.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@AllArgsConstructor @NoArgsConstructor
@Document("person")
public class Person implements Serializable {
    @Id
    private String idPerson;
    private String firstname;
    private String lastname;
    private String dni;
    private String client;
    private int state;
}

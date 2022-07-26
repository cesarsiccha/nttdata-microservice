package com.bootcamp.client.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@AllArgsConstructor @NoArgsConstructor
@Document("CategoryClient")
public class CategoryClient implements Serializable {

    @Id
    private String idCategoryClient;
    private String description;
    private int state;
}

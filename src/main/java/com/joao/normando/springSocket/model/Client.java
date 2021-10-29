package com.joao.normando.springSocket.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document("cliente")
public class Client implements Serializable {

    @Id
    private String id;

    private Data data;

    private String name;

    private String state;

    private Integer bravery;

    private String low;


}

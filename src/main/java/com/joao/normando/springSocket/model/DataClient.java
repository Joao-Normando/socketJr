package com.joao.normando.springSocket.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DataClient {

    private String data;
    private String id;
    private String name;
    private String state;
    private String bravery;
    private String low;

    public DataClient() {
    }
}

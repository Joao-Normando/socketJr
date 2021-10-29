package com.joao.normando.springSocket.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Document("client")
@Getter
@Setter
public class Client implements Serializable {

    @Id
    private String id;

    private LocalDateTime data;

    private String name;

    private String state;

    private Integer bravery;

    private String low;

    public Client() {
    }

    public Client( LocalDateTime data, String name, String state, Integer bravery, String low) {
        this.data = data;
        this.name = name;
        this.state = state;
        this.bravery = bravery;
        this.low = low;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client)) return false;
        Client client = (Client) o;
        return Objects.equals(id, client.id) && Objects.equals(name, client.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}

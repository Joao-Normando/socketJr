package com.joao.normando.springSocket.repository;

import com.joao.normando.springSocket.model.Client;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface ClientRepository extends MongoRepository <Client, String> {


}

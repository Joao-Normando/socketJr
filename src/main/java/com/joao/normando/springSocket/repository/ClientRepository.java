package com.joao.normando.springSocket.repository;

import ch.qos.logback.core.net.server.Client;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface ClientRepository extends MongoRepository <Client, String> {


}

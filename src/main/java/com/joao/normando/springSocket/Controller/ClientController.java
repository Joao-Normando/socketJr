package com.joao.normando.springSocket.Controller;


import com.joao.normando.springSocket.model.Client;
import com.joao.normando.springSocket.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
public class ClientController {


    @Autowired
    private ClientRepository clientRepository;
    @GetMapping("/client")
    public List<Client> index() {
        List<Client> Client = (List<Client>)clientRepository.findAll();
        return Client;
    }

    @PostMapping ("/client/save")
    public void Save (@RequestBody Client client) {
        clientRepository.save(client);
    }

    @GetMapping("/client/{id}")
    public Client id(@PathVariable String id) {
       Optional<Client> client = clientRepository.findById(id);
       if (client.isEmpty()) {
           System.out.println("Não existe cliente com este id !");
       }
        return client.get();
    }

}

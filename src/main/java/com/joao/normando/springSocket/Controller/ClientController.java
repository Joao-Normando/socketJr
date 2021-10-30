package com.joao.normando.springSocket.Controller;


import com.joao.normando.springSocket.model.Client;
import com.joao.normando.springSocket.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequestMapping(name="api/clients")
@RestController
public class ClientController {


    @Autowired
    private ClientRepository clientRepository;
    @GetMapping("/client")
    public List<String> index() {
        List<Client> clients = (List<Client>)clientRepository.findAll();
        List<String> returning = new ArrayList<>();
        clients.forEach(client -> {
            returning.add(client.toString());
        });
        return returning;
    }

    @PostMapping ("/client/save")
    public String save (@RequestBody Client clientRequest) {
        Client client = clientRepository.save(clientRequest);
        System.out.println(client);
        return client.toString();
    }

    @GetMapping("/client/{id}")
    public String id(@PathVariable String id) {
       Optional<Client> client = clientRepository.findById(id);
       if (client.isEmpty()) {
           System.out.println("Id no exist !");
       }
        return client.get().toString();
    }
}

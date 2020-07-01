package com.bot.dasi.service;

import com.bot.dasi.domain.Client;
import com.bot.dasi.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ClientService {

    @Autowired
    ClientRepository clientRepository;

    public void init(){
        if (findAll().isEmpty()){
            Client client = new Client();
            client.setUserDiscordId(550159107613786133L);
            Client clientNew = create(client);

            subtractCoin(clientNew, 30);
        }
    }

    public List<Client> findAll(){
        return clientRepository.findAll();
    }

    public Client findByUserDiscordId(Long userDiscordId){
        return clientRepository.findByUserDiscordId(userDiscordId);
    }

    public Client create(Client client){
        return clientRepository.save(client);
    }

    public Client addCoin(Client client, Integer coin){
        Client updateClient = clientRepository.findById(client.getId()).orElse(null);
        updateClient.setCoin(client.getCoin() + coin);
        return clientRepository.save(updateClient);
    }

    public Client subtractCoin(Client client, Integer coin){
        Client updateClient = clientRepository.findById(client.getId()).orElse(null);
        updateClient.setCoin(client.getCoin() - coin);
        return clientRepository.save(updateClient);
    }
}

package com.bot.dasi.repository;

import com.bot.dasi.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    Client findByUserDiscordId(Long userDiscordId);
}

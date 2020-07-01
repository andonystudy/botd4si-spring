package com.bot.dasi.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userDiscordId;
    private Integer coin;

    @PrePersist
    public void prePersist(){
        coin = 200;
    }
}

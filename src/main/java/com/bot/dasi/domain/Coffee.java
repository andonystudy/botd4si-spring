package com.bot.dasi.domain;

import lombok.Data;

@Data
public class Coffee {

    private Long id;
    private String name;
    private String description;
    private String uriImage;
    private Integer price;

}

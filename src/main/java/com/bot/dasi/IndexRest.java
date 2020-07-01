package com.bot.dasi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("")
@RestController
public class IndexRest {

    @GetMapping("")
    public String init(){
        return "Coffee Bot on ..-";
    }
}

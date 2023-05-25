package com.pokemon.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pokemon {
    private String name;
    private String url;

    public Pokemon(String name, String url) {
        this.name = name;
        this.url = url;
    }
}

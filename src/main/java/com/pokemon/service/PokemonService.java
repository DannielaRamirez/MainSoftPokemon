package com.pokemon.service;

import com.pokemon.model.Pokemon;
import com.pokemon.repository.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PokemonService {
    @Autowired
    PokemonRepository pokemonRepository;

   public List<Pokemon> getPokemonList(int limit, int offset) {
       return pokemonRepository.getPokemonList(limit, offset);
   }
}

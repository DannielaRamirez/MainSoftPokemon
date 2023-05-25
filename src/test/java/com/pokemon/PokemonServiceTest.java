package com.pokemon;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import com.pokemon.model.Pokemon;
import com.pokemon.repository.PokemonRepository;
import com.pokemon.service.PokemonService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PokemonServiceTest {
     @Autowired
     private PokemonService pokemonService;

     @MockBean
     private PokemonRepository pokemonRepository;

     @Test
     public void testGetPokemonList() {
          List<Pokemon> expectedPokemonList = Arrays.asList(
                  new Pokemon("Pikachu", "https://pokeapi.co/api/v2/pokemon/25"),
                  new Pokemon("Charizard", "https://pokeapi.co/api/v2/pokemon/6")
          );

          int limit = 10;
          int offset = 0;
          when(pokemonRepository.getPokemonList(limit, offset)).thenReturn(expectedPokemonList);

          List<Pokemon> actualPokemonList = pokemonService.getPokemonList(limit, offset);

          assertEquals(expectedPokemonList.size(), actualPokemonList.size());
          assertEquals(expectedPokemonList.get(0).getName(), actualPokemonList.get(0).getName());
          assertEquals(expectedPokemonList.get(0).getUrl(), actualPokemonList.get(0).getUrl());
          assertEquals(expectedPokemonList.get(1).getName(), actualPokemonList.get(1).getName());
          assertEquals(expectedPokemonList.get(1).getUrl(), actualPokemonList.get(1).getUrl());
     }
}

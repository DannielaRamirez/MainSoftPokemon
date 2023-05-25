package com.pokemon.endpoints;


import com.pokemon.model.Pokemon;
import com.pokemon.service.PokemonService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import com.pokemon.gs_ws.*;

import java.util.ArrayList;
import java.util.List;

@Endpoint
public class PokemonEndpoint {
    private static final String NAMESPACE_URI = "http://pokemon.com";
    @Autowired
    PokemonService pokemonService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetPokemonRequest")
    @ResponsePayload
    public GetPokemonResponse getAllPokemon(@RequestPayload GetPokemonRequest request) {
        int limit = request.getLimit();
        int offset = request.getOffset();

        GetPokemonResponse response = new GetPokemonResponse();
        List<PokemonInfo> pokemonInfoList = new ArrayList<>();

        List<Pokemon> pokemonList = pokemonService.getPokemonList(limit, offset);

        for (int i = 0; i < pokemonList.size(); i++) {
            PokemonInfo ob = new PokemonInfo();
            BeanUtils.copyProperties(pokemonList.get(i), ob);
            pokemonInfoList.add(ob);
        }
        response.getPokemonInfo().addAll(pokemonInfoList);
        return response;
    }
}

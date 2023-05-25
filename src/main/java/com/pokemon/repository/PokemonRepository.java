package com.pokemon.repository;


import com.pokemon.model.Pokemon;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PokemonRepository {
    @Value("${pokeapi.url}")
    private String apiUrl;
    public List<Pokemon> getPokemonList(int limit, int offset) {
        List<Pokemon> pokemonList = new ArrayList<>();

        try {
            HttpClient httpClient = HttpClient.newHttpClient();
            String url = apiUrl + "?limit=" + limit + "&offset=" + offset;
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            int responseCode = response.statusCode();

            if (responseCode == 200) {
                String responseBody = response.body();
                System.out.println("Respuesta de la URL: " + responseBody);

                JSONArray resultsArray = new JSONObject(responseBody).getJSONArray("results");

                for (int i = 0; i < resultsArray.length(); i++) {
                    JSONObject pokemonObject = resultsArray.getJSONObject(i);
                    String name = pokemonObject.getString("name");
                    String url1 = pokemonObject.getString("url");

                    Pokemon pokemon = new Pokemon(name, url);
                    pokemonList.add(pokemon);
                }
            } else {
                System.out.println("La llamada no fue exitosa. Código de respuesta: " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pokemonList;
    }
}

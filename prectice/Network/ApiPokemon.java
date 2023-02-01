package com.example.prectice.Network;

import com.example.prectice.model.Pokemon;
import com.example.prectice.model.PokemonRespuesta;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiPokemon {
    @GET("pokemon")
    Call<PokemonRespuesta> getPokemon();
}

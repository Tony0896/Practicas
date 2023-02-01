package com.example.prectice;

import androidx.annotation.ArrayRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.prectice.Adapter.MovieAdapter;
import com.example.prectice.Network.ApiClient;
import com.example.prectice.Network.ApiDatas;
import com.example.prectice.Network.ApiMovie;
import com.example.prectice.Network.ApiPokemon;
import com.example.prectice.model.Datas;
import com.example.prectice.model.Movie;
import com.example.prectice.model.Pokemon;
import com.example.prectice.model.PokemonRespuesta;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private List<Movie> movies;
    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;
    private static final String TAG  = "POK";

    private RecyclerView recyclerView2;
    private MovieAdapter movieAdapter2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.rv_movies);
        recyclerView2 = findViewById(R.id.rv_movies);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        //showMovies();
        getPokemon();
        //getData();
        recyclerView2.setLayoutManager((new GridLayoutManager(getApplicationContext(),1)));
    }

    public void showMovies(){
        Call<List<Movie>> call = ApiClient.getClient().create(ApiMovie.class).getMovies();
        call.enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                if(response.isSuccessful()){
                    movies = response.body();
                    movieAdapter= new MovieAdapter(movies, getApplicationContext());
                    recyclerView.setAdapter(movieAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Movie>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getPokemon(){
        Log.i(TAG, "enteer");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiPokemon apiPokemon = retrofit.create(ApiPokemon.class);
        Call<PokemonRespuesta> pokemonRespuestaCall = apiPokemon.getPokemon();
        pokemonRespuestaCall.enqueue(new Callback<PokemonRespuesta>() {
            @Override
            public void onResponse(Call<PokemonRespuesta> call, Response<PokemonRespuesta> response) {
                if(response.isSuccessful()){
                    PokemonRespuesta pokemonRespuesta = response.body();
                    ArrayList<Pokemon> lista = pokemonRespuesta.getResults();
                    for(Pokemon pokemon: lista){
                        Log.d(TAG, pokemon.getUrl());
                    }
                }
            }

            @Override
            public void onFailure(Call<PokemonRespuesta> call, Throwable t) {

            }
        });
    }

    public void getData(){
        Log.i(TAG, "enteer");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://operacionqa.ci-sa.com.mx/www.CISAAPP.com/Archivos/JSON_desin/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiDatas apiDatas = retrofit.create(ApiDatas.class);
        Call<List<Datas>> call = apiDatas.getData();
        call.enqueue(new Callback<List<Datas>>() {
            @Override
            public void onResponse(Call<List<Datas>> call, Response<List<Datas>> response) {
                if(response.isSuccessful()){
                    List<Datas> datag = response.body();
                    for(Datas datas: datag){
                        Log.d(TAG, datas.getBuscador());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Datas>> call, Throwable t) {
                Log.e(TAG, "error2:"+t.getMessage());
            }
        });
    }
}
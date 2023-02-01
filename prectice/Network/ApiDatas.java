package com.example.prectice.Network;

import com.example.prectice.model.Datas;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiDatas {
    @GET("Unidades_14.json")
    Call<List<Datas>> getData();
}

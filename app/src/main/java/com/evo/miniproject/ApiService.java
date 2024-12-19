package com.evo.miniproject;

import com.evo.miniproject.model.ResponseModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("/posts")
    Call<List<ResponseModel>> getResponse();
}

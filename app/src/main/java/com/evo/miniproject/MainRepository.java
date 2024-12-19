package com.evo.miniproject;

import com.evo.miniproject.model.ResponseModel;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainRepository {
    private ApiService apiService;

    public MainRepository() {
        apiService = RetrofitHttpCall.getInstance("https://jsonplaceholder.typicode.com").create(ApiService.class);
    }

    public void getPost(CallbackRequest callbackRequest) {
        apiService.getResponse().enqueue(new Callback<List<ResponseModel>>() {
            @Override
            public void onResponse(Call<List<ResponseModel>> call, Response<List<ResponseModel>> response) {
                if (!response.isSuccessful()) {
                    try (ResponseBody responseBody = response.errorBody()) {
                        callbackRequest.onError(new String(responseBody.bytes()));
                    } catch (IOException e) {
                        callbackRequest.onError(new String("ERROR"));
                    }
                    return;
                }
                callbackRequest.onEntityPosted(response.body());
            }

            @Override
            public void onFailure(Call<List<ResponseModel>> call, Throwable throwable) {
                callbackRequest.onError(throwable.toString());
            }
        });
    }
}

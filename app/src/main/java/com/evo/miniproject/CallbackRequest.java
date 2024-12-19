package com.evo.miniproject;

public interface CallbackRequest {
    void onEntityPosted(Object object);
    void onError(String errorMessage);

}

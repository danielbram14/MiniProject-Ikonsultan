package com.evo.miniproject.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.evo.miniproject.model.ResponseModel;

import java.util.List;

public class MainViewModel extends ViewModel {

    private final MutableLiveData<List<ResponseModel>> uiState =
            new MutableLiveData(new ResponseModel());

    public LiveData<List<ResponseModel>> getUiState() {
        return uiState;
    }

    public void rollDice() {

    }
}

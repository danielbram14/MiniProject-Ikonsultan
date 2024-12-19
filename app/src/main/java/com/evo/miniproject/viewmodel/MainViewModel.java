package com.evo.miniproject.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.evo.miniproject.CallbackRequest;
import com.evo.miniproject.MainRepository;
import com.evo.miniproject.model.ResponseModel;

import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends ViewModel {
    private final MainRepository mainRepository;
    private MutableLiveData<List<ResponseModel>> responseModel;
    private MutableLiveData<String> errorLiveData;

    public MainViewModel() {
        mainRepository = new MainRepository();
        responseModel = new MutableLiveData<>();
        errorLiveData = new MutableLiveData<>();
    }

    public LiveData<List<ResponseModel>> getUser() {
        return responseModel;
    }

    public LiveData<String> getError() {
        return errorLiveData;
    }

    public void getPost(){
        mainRepository.getPost(new CallbackRequest() {
            @Override
            public void onEntityPosted(Object object) {
                responseModel.setValue((List<ResponseModel>) object);
            }

            @Override
            public void onError(String errorMessage) {
                errorLiveData.setValue(errorMessage);
            }
        });
    }

    public void search(String query) {
        try {
            List<ResponseModel> responseModels = responseModel.getValue();
            List<ResponseModel> temp = new ArrayList<>();
            assert responseModels != null;
            for (ResponseModel response : responseModels) {
                if (response.getTitle() != null && response.getTitle().contains(query)) {
                    temp.add(response);
                }
            }
            responseModel.setValue(temp);
        } catch (Exception e) {
            errorLiveData.setValue(e.getMessage());
        }
    }
}
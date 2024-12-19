package com.evo.miniproject;

import android.os.Bundle;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.evo.miniproject.model.ResponseModel;
import com.evo.miniproject.viewmodel.MainViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MainViewModel mViewModel;
    private SearchView mSearchView;
    private RecyclerView mRecyclerView;
    private MainAdapter mainAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initVariable();
        initAction();
    }

    private void initAction() {
        mViewModel.getUser().observe(this, new Observer<List<ResponseModel>>() {
            @Override
            public void onChanged(List<ResponseModel> responseModels) {
                mainAdapter.replaceData(responseModels);
            }
        });
        mViewModel.getError().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String response) {
                Toast.makeText(MainActivity.this, response, Toast.LENGTH_SHORT).show();
            }
        });
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (!query.isEmpty()) {
                    mViewModel.search(query);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.isEmpty()) {
                    mViewModel.getPost();
                }
                return false;
            }
        });
    }

    private void initVariable() {
        mViewModel = new MainViewModel();
        mSearchView = findViewById(R.id.svData);
        mRecyclerView = findViewById(R.id.rvData);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mainAdapter = new MainAdapter(this, new ArrayList<>());
        mRecyclerView.setAdapter(mainAdapter);
        mViewModel.getPost();
    }

}
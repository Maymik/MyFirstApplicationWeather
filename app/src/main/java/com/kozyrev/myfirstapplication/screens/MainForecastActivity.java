package com.kozyrev.myfirstapplication.screens;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kozyrev.myfirstapplication.R;
import com.kozyrev.myfirstapplication.models.WeatherForecastModel;

public class MainForecastActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    View progressBar;

    MyForecastRecyclerViewAdapter adapter;

    // activity lifecycle
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast_main);
        adapter = new MyForecastRecyclerViewAdapter(this);


        recyclerView = findViewById(R.id.main_activity_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(adapter);

        progressBar = findViewById(R.id.forecast_bar_progress);
        ForecastViewModel forecastViewModel = new ViewModelProvider(this).get(ForecastViewModel.class);
        forecastViewModel.setContext(this);
        forecastViewModel.getForecast().observe(this, new Observer<WeatherForecastModel>() {

            @Override
            public void onChanged(WeatherForecastModel weatherForecastModel) {
                updateUI(weatherForecastModel);
            }
        });
    }

    private void updateUI(WeatherForecastModel weatherForecastModel) {
        adapter.setData(weatherForecastModel.getList());
        adapter.notifyDataSetChanged();
        progressBar.setVisibility(View.GONE);
    }

}

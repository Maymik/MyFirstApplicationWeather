package com.kozyrev.myfirstapplication.screens;

import android.Manifest;
import android.annotation.SuppressLint;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.kozyrev.myfirstapplication.R;
import com.kozyrev.myfirstapplication.models.WeatherForecastModel;

import java.security.Permissions;
import java.util.Map;

public class MainForecastActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    View progressBar;
    ForecastViewModel forecastViewModel;

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
        forecastViewModel = new ViewModelProvider(this).get(ForecastViewModel.class);
        forecastViewModel.setContext(this);
        forecastViewModel.getForecast().observe(this, new Observer<WeatherForecastModel>() {

            @Override
            public void onChanged(WeatherForecastModel weatherForecastModel) {
                updateUI(weatherForecastModel);
            }
        });

        ActivityResultContracts.RequestMultiplePermissions contract = new ActivityResultContracts
                .RequestMultiplePermissions();
        ActivityResultCallback callback = new ActivityResultCallback<Map<String, Boolean>>() {
            @Override
            public void onActivityResult(Map<String, Boolean> result) {
                Boolean fineLocationGranted = result.getOrDefault(
                        Manifest.permission.ACCESS_FINE_LOCATION, false);
                Boolean coarseLocationGranted = result.getOrDefault(
                        Manifest.permission.ACCESS_COARSE_LOCATION, false);
                if (fineLocationGranted != null && fineLocationGranted) {
                    // Precise location access granted.
                    requestLocation();
                } else if (coarseLocationGranted != null && coarseLocationGranted) {
                    // Only approximate location access granted.
                    requestLocation();
                } else {
                    Toast.makeText(MainForecastActivity.this, "Without location we always use Kyiv location.", Toast.LENGTH_SHORT).show();
                }
            }
        };

        ActivityResultLauncher<String[]> locationPermissionRequest = registerForActivityResult(contract, callback);
        locationPermissionRequest.launch(new String[] {
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
        });
    }

    @SuppressLint("MissingPermission")
    private void requestLocation() {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            progressBar.setVisibility(View.VISIBLE);
                            Toast.makeText(MainForecastActivity.this, "Loading data for new location...", Toast.LENGTH_SHORT).show();
                            forecastViewModel.reloadWithLocation(location.getLatitude(), location.getLongitude());
                        }
                    }
                });
    }

    private FusedLocationProviderClient fusedLocationClient;

    private void updateUI(WeatherForecastModel weatherForecastModel) {
        adapter.setData(weatherForecastModel.getList());
        adapter.notifyDataSetChanged();
        progressBar.setVisibility(View.GONE);
    }

}

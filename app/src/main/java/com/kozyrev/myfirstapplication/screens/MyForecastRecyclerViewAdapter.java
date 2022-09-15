package com.kozyrev.myfirstapplication.screens;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.kozyrev.myfirstapplication.R;
import com.kozyrev.myfirstapplication.models.DayForecast;

import java.util.ArrayList;
import java.util.List;

class MyForecastRecyclerViewAdapter extends RecyclerView.Adapter<MyForecastRecyclerViewAdapter.ViewHolder> {

    private static final float KELVIN_TO_CELSIUS = 273.15f;
    private List<DayForecast> mData = new ArrayList<>();
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;


    MyForecastRecyclerViewAdapter(Context context) {
        this.mInflater = LayoutInflater.from(context);
        Log.i("123", "not working");

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.forecast_recyclerview_row, parent, false);
        return new  ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //Date
        DayForecast dayForecast = mData.get(position);
        holder.DateTextView.setText(dayForecast.getDt_txt());


        // WIND
        float windSpeed = dayForecast.getWindForecast().getSpeed();
        String windSpeedText = Float.toString(windSpeed);
        holder.WindTextView.setText(String.format("%s m/min ", windSpeedText));


       // Humidity
        int humidityForecast = dayForecast.getMain().getHumidity();
        String humidityText = Integer.toString(humidityForecast);
        holder.HumidityTextView.setText(String.format("%s %% ", humidityText));


        // TEMP
        float tempCelsius = dayForecast.getMain().getTemp() - KELVIN_TO_CELSIUS;
        int tempIntCelsius = Math.round(tempCelsius);
        String tempCelsiusText = Integer.toString(tempIntCelsius);
        holder.TemperatureTextView.setText(String.format("%s °C ", tempCelsiusText));


        String weatherWordTitle = dayForecast.getWeatherForecast().get(0).getMain(); // Cloud, Rain,
        String weatherWordDescription = dayForecast.getWeatherForecast().get(0).getDescription(); // light rain, moderate rain

        //image
        if (weatherWordTitle.equals("Rain")) {

            holder.weatherImage.setImageResource(R.drawable.rain_icon_);
        } else if (weatherWordTitle.equals("Clouds")){

            holder.weatherImage.setImageResource(R.drawable.cloud_icon);
        } else {
            holder.weatherImage.setImageResource(android.R.drawable.btn_minus);
        }
    }

    @Override
    public int getItemCount() {

        return mData.size();
    }

    public void setData(List<DayForecast> mData) {
        this.mData = mData;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView DateTextView;
        TextView TemperatureTextView;
        TextView WindTextView;
        TextView HumidityTextView;
        ImageView weatherImage;

        ViewHolder(View itemView) {
            super(itemView);
            weatherImage = itemView.findViewById(R.id.forecast_weather_image);
            DateTextView = itemView.findViewById(R.id.forecast_date_value);
            WindTextView = itemView.findViewById(R.id.forecast_wind_value);
            HumidityTextView = (itemView.findViewById(R.id.forecast_humidity_value));
            TemperatureTextView = itemView.findViewById(R.id.forecast_temperature_value);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }

        public TextView getHumidityTextView() {
            return HumidityTextView;
        }

        public void setHumidityTextView(TextView humidityTextView) {
            HumidityTextView = humidityTextView;
        }
    }

    DayForecast getItem(int id) {
        return mData.get(id);
    }

    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}


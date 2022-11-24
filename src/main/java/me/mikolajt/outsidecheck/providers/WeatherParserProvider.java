package me.mikolajt.outsidecheck.providers;

import com.google.gson.Gson;
import me.mikolajt.outsidecheck.ForecastType;

public interface WeatherParserProvider {
    <T> T parseWeather(String weatherData, Class<T> forecast);

    class GsonParserProvider implements WeatherParserProvider{
        private final Gson gson;

        public GsonParserProvider(){
            this.gson = new Gson();
        }

        public GsonParserProvider(Gson gson) {
            this.gson = gson;
        }

        @Override
        public <T> T parseWeather(String weatherData, Class<T> forecast) {
            return gson.fromJson(weatherData, forecast);
        }
    }
}

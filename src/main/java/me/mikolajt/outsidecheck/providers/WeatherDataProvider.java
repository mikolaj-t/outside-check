package me.mikolajt.outsidecheck.providers;

import me.mikolajt.outsidecheck.CityConfig;
import me.mikolajt.outsidecheck.ForecastType;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

public interface WeatherDataProvider {
    String getWeatherData(OptionsProvider.Options options, CityConfig config);

    class OpenWeatherProvider implements WeatherDataProvider {
        private final HttpClient client;

        public OpenWeatherProvider() {
            this.client = HttpClient.newBuilder().build();;
        }

        @Override
        public String getWeatherData(OptionsProvider.Options options, CityConfig config) {
            return fetchApi(options.apiKey(), config, options.forecastType());
        }

        private String fetchApi(String apiKey, CityConfig cityConfig, ForecastType forecastType) {
            String url = "https://api.openweathermap.org/data/2.5/onecall"
                    + "?lat=" + cityConfig.latitude() + "&lon=" + cityConfig.longitude()
                    + "&units=metric&exclude=current,minutely,alerts,"
                    + ForecastType.theOtherOne(forecastType).toString().toLowerCase()
                    + "&appid=" + apiKey;
            try {
                var request = HttpRequest.newBuilder().uri(new URI(url)).GET()
                        .timeout(Duration.of(10, ChronoUnit.SECONDS)).build();
                var response = client.send(request, HttpResponse.BodyHandlers.ofString());
                return response.body();
            } catch (URISyntaxException | IOException | InterruptedException e) {
                System.err.println(e.getMessage());
            }
            return "";
        }
    }
}

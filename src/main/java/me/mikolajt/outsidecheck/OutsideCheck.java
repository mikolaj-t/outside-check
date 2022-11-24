package me.mikolajt.outsidecheck;

import me.mikolajt.outsidecheck.providers.*;

public record OutsideCheck(OptionsProvider optionsProvider, ConfigProvider configProvider,
                           WeatherDataProvider weatherDataProvider, WeatherParserProvider weatherParserProvider,
                           WeatherPrintProvider weatherPrintProvider) {
    public void checkWeather(String[] args){
        var options = optionsProvider.getOptions();
        if(options == null) return;

        var config = configProvider.getConfig();
        if(config == null) return;

        var cityConfig = config.getCityConfig(options.city().toUpperCase());
        if(cityConfig == null){
            System.err.println("City is not defined in config: " + options.city());
            return;
        }

        var weatherData = weatherDataProvider.getWeatherData(options, cityConfig);
        if(weatherData.length() == 0) return;

        var forecastClass = switch (options.forecastType()) {
            case DAILY -> DailyForecast.class;
            case HOURLY -> HourlyForecast.class;
        };
        var parsedWeather = weatherParserProvider.parseWeather(weatherData, forecastClass);
        if(parsedWeather == null) return;

        weatherPrintProvider.printWeather(parsedWeather);
    }
}
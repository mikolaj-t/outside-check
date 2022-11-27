package me.mikolajt.outsidecheck;

import me.mikolajt.outsidecheck.providers.*;

public class Main {
    public static void main(String[] args) {
        var optionsProvider = new OptionsProvider.CliOptionsProvider(args);
        if(optionsProvider.getOptions() == null) return;
        var configProvider = new ConfigProvider.FileConfigProvider(optionsProvider.getOptions().configPath());
        var weatherProvider = new WeatherDataProvider.OpenWeatherProvider();
        var parseProvider = new WeatherParserProvider.GsonParserProvider();
        var printProvider = new WeatherPrintProvider.StreamPrintProvider(System.out);

        var outsideCheck = new OutsideCheck(optionsProvider, configProvider, weatherProvider,
                parseProvider, printProvider);
        outsideCheck.checkWeather(args);
    }
}
package me.mikolajt.outsidecheck;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Main {
    private static void printUsage(String contextText){
        if(contextText.length() > 0){
            System.err.println(contextText);
        }
        System.err.println("""
                    Usage: ./outside [api_key] [config_path] [forecast_type] [city]
                    \t[api_key] - OpenWeather One Call 2.5 API Key
                    \t[config_path] - path to a configuration CSV file of cities and their coordinates
                    \t[forecast_type] - daily/hourly forecast
                    \t[city] - city to show the weather of, from those defined in the config file""");
    }

    private static Map<String, CityConfig> readConfigFile(String filepath) throws IOException {
        Map<String, CityConfig> configMap = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            String line;
            while((line = br.readLine()) != null){
                String[] values = line.split(",");
                if(values.length != 3) throw new IOException("Incorrect number of variables in a line!");

                String cityName = values[0];
                String cityLong = values[1];
                String cityLat = values[2];
                configMap.put(cityName, new CityConfig(cityLong, cityLat));
            }
        }

        return configMap;
    }

    public static void main(String[] args) {
        if(args.length != 4){
            printUsage("Argument count mismatch: %d vs 4".formatted(args.length));
            return;
        }

        String apiKey = args[0];
        String configPath = args[1];

        ForecastType forecastType;
        try {
            forecastType = ForecastType.FromString(args[2]);
        } catch (IllegalArgumentException e) {
            printUsage("Incorrect forecast type: %s".formatted(args[2]));
            return;
        }

        String city = args[3];

        Map<String, CityConfig> config;
        try {
            config = readConfigFile(configPath);
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return;
        }
    }
}
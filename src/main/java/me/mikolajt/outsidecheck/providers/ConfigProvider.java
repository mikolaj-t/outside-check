package me.mikolajt.outsidecheck.providers;

import me.mikolajt.outsidecheck.CityConfig;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public interface ConfigProvider {
    Config getConfig();

    record Config(Map<String, CityConfig> configMap){
        public CityConfig getCityConfig(String city){
            return configMap.get(city);
        }
    }

    class FileConfigProvider implements ConfigProvider {
        private final String filepath;

        public FileConfigProvider(String filepath) {
            this.filepath = filepath;
        }

        @Override
        public Config getConfig() {
            try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
                var config = new Config(new HashMap<>());
                String line;
                while((line = br.readLine()) != null){
                    String[] values = line.split(",");
                    if(values.length != 3) throw new IOException("Incorrect number of variables in a line!");

                    String cityName = values[0];
                    String cityLat = values[1];
                    String cityLong = values[2];
                    config.configMap.put(cityName.toUpperCase(), new CityConfig(cityLong, cityLat));
                }
                return config;
            } catch (IOException e) {
                System.err.println(e);
                return null;
            }
        }
    }
}

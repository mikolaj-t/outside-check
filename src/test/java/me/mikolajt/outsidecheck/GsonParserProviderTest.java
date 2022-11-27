package me.mikolajt.outsidecheck;

import me.mikolajt.outsidecheck.providers.WeatherParserProvider;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class GsonParserProviderTest {
    @Test
    public void testDailyJson(){
        String json = "{\"daily\":[{\"dt\":1669561200,\"temp\":{\"day\":25.49,\"min\":21.6,\"max\":28.46,\"night\":23.37,\"eve\":25.55,\"morn\":22.32},\"pop\":0.345}," +
                "{\"dt\":1669647600,\"temp\":{\"day\":26.79,\"min\":21.81,\"max\":28.37,\"night\":23.87,\"eve\":26.55,\"morn\":21.81},\"pop\":0}]}";
        var gsonProvider = new WeatherParserProvider.GsonParserProvider();
        var dailyForecast = gsonProvider.parseWeather(json, DailyForecast.class);
        var expectedForecast = new DailyForecast(new DailyForecastData[]{
                new DailyForecastData(1669561200, new DailyTemperature(25.49, 21.6, 28.46, 23.37, 25.55, 22.32), 0.345),
                new DailyForecastData(1669647600, new DailyTemperature(26.79, 21.81, 28.37, 23.87, 26.55, 21.81), 0)
        });
        Assert.assertEquals(dailyForecast, expectedForecast);
    }

    @Test
    public void testHourlyJson(){
        String json = "{\"hourly\":[{\"dt\":1669550400,\"temp\":3.54},{\"dt\":1669554000,\"temp\":-3.65,\"snow\":{\"1h\":12},\"rain\":{\"1h\":33.0}}]}";
        var gsonProvider = new WeatherParserProvider.GsonParserProvider();
        var hourlyForecast = gsonProvider.parseWeather(json, HourlyForecast.class);
        var expectedForecast = new HourlyForecast(new HourlyForecastData[]{
                new HourlyForecastData(1669550400, 3.54, null ,null),
                new HourlyForecastData(1669554000, -3.65, Map.of("1h", 33.0), Map.of("1h", 12.0))
        });
        Assert.assertEquals(hourlyForecast, expectedForecast);
    }

}

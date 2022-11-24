package me.mikolajt.outsidecheck;

import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

public class ForecastsTest {
    @Test
    public void testDailyForecastOutput(){
        var forecastData = new DailyForecastData[] {
            new DailyForecastData(1643760000, new DailyTemperature(10, -10, 10, -10, -5, -5), 0.42),
            new DailyForecastData(1643846400, new DailyTemperature(100, 50, 200, 50, 30, 40), 0.33)
        };
        var forecast = new DailyForecast(forecastData);
        var expectedOutput = lineSeparateStrings("02.02.2022 -10.0 10.0 -2.5 42.0", "03.02.2022 50.0 200.0 55.0 33.0");
        Assert.assertEquals(forecast.toString(), expectedOutput);
    }

    @Test
    public void testHourlyForecastOutput(){
        var forecastData = new HourlyForecastData[]{
                new HourlyForecastData(1643760000, 40.4, Map.of("1h", 345.6789), null),
                new HourlyForecastData(1643846400, -33.3333, Map.of(), Map.of("1h", 123.0)),
                new HourlyForecastData(1643932800, 15.4, null, Map.of()),
                new HourlyForecastData(1644093964, 0, Map.of("1h", 12.3), Map.of("1h", 4.56))
        };
        var forecast = new HourlyForecast(forecastData);
        System.out.println(forecast);
        var expectedOutput = lineSeparateStrings("02.02.2022 00:00 40.4 345.6789", "03.02.2022 00:00 -33.3333 123.0snow",
                "04.02.2022 00:00 15.4 unknown", "05.02.2022 20:46 0.0 12.3 4.56snow");
        Assert.assertEquals(forecast.toString(), expectedOutput);
    }

    private String lineSeparateStrings(String... strings){
        StringBuilder sb = new StringBuilder();
        for (var string : strings) {
            sb.append(string).append(System.lineSeparator());
        }
        return sb.toString();
    }
}

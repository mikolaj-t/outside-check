package me.mikolajt.outsidecheck;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Map;

record DailyForecast(DailyForecastData[] daily) {
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        for (var dailyForecast:daily) {
            sb.append(dailyForecast).append(System.lineSeparator());
        }
        return sb.toString();
    }
}

record DailyForecastData(long dt, DailyTemperature temp, double pop) {
    @Override
    public String toString() {
        var date = LocalDateTime.ofEpochSecond(dt, 0, ZoneOffset.UTC);
        var dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return dateTimeFormatter.format(date) + " " + temp + " " + pop * 100;
    }
}

record DailyTemperature(double day, double min, double max, double night, double eve, double morn){
    @Override
    public String toString() {
        return min + " " + max + " " + (day+night+eve+morn)/4;
    }
}

record HourlyForecast(HourlyForecastData[] hourly){
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        for (var hourlyForecast:hourly) {
            sb.append(hourlyForecast).append(System.lineSeparator());
        }
        return sb.toString();
    }
}

record HourlyForecastData(long dt, double temp, Map<String, Double> rain, Map<String, Double> snow){
    @Override
    public String toString() {
        var date = LocalDateTime.ofEpochSecond(dt, 0, ZoneOffset.UTC);
        var dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

        StringBuilder sb = new StringBuilder();
        sb.append(dateTimeFormatter.format(date)).append(" ").append(temp);

        boolean isRaining = rain != null && !rain.isEmpty();
        boolean isSnowing = snow != null && !snow.isEmpty();
        if(isRaining){
            sb.append(" ").append(rain.get("1h"));
        }
        if (isSnowing) {
            sb.append(" ").append(snow.get("1h")).append("snow");
        }
        if(!isRaining && !isSnowing){
            sb.append(" ").append("unknown");
        }
        return sb.toString();
    }
}
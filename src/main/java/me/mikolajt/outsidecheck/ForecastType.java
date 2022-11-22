package me.mikolajt.outsidecheck;

public enum ForecastType {
    DAILY, HOURLY;

    public static ForecastType FromString(String s) throws IllegalArgumentException{
        return ForecastType.valueOf(s.toUpperCase());
    }
}

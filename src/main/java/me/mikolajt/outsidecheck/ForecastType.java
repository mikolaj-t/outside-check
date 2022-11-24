package me.mikolajt.outsidecheck;

public enum ForecastType {
    DAILY, HOURLY;
    public static ForecastType theOtherOne(ForecastType type){
        return ForecastType.values()[(type.ordinal() + 1) % 2];
    }
    public static ForecastType fromString(String s) throws IllegalArgumentException{
        return ForecastType.valueOf(s.toUpperCase());
    }
}

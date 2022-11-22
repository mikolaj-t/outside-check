package me.mikolajt.outsidecheck;

public enum ForecastType {
    DAILY, HOURLY;

    public static ForecastType FromString(String s) throws IllegalStateException{
        return switch (s.toLowerCase()) {
            case "daily" -> DAILY;
            case "hourly" -> HOURLY;
            default -> throw new IllegalStateException("Unexpected value: " + s.toLowerCase());
        };
    }
}

package me.mikolajt.outsidecheck;

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
        } catch (IllegalStateException e) {
            printUsage("Incorrect forecast type: %s".formatted(args[2]));
            return;
        }

        String city = args[3];

        for(String s : args){
            StringBuilder sb = new StringBuilder();
            sb.append("Hello ").append(s).append("!");
            System.out.println(sb);
        }
    }
}
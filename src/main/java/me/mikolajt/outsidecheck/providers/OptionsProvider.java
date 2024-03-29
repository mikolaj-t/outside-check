package me.mikolajt.outsidecheck.providers;

import me.mikolajt.outsidecheck.ForecastType;

public interface OptionsProvider {
    Options getOptions();

    record Options(String apiKey, String configPath, ForecastType forecastType, String city) { }

    class CliOptionsProvider implements OptionsProvider {
        public static final String USAGE_MESSAGE = """
                    Usage: ./outside <api_key> <config_path> <forecast_type> <city>
                    \tapi_key - OpenWeather One Call 2.5 API Key
                    \tconfig_path - path to a configuration CSV file of cities and their coordinates
                    \tforecast_type - (daily|hourly) forecast 
                    \tcity - city to show the weather of, from those defined in the config file""";
        private final String[] args;
        private final Options options;

        public CliOptionsProvider(String[] args){
            this.args = args;
            this.options = parseArgs();
        }
        @Override
        public Options getOptions() {
            return this.options;
        }
        private Options parseArgs(){
            if(args.length < 4){
                printUsage("Argument count mismatch: " + args.length + " vs 4");
                return null;
            }

            ForecastType forecastType;
            try {
                forecastType = ForecastType.fromString(args[2]);
            } catch (IllegalArgumentException e) {
                printUsage("Incorrect forecast type:" + args[2]);
                return null;
            }

            StringBuilder cityName = new StringBuilder();
            for(int i=3; i<args.length; i++){
                cityName.append(args[i]);
                if(i != args.length-1) cityName.append(" ");
            }
            return new Options(args[0], args[1], forecastType, cityName.toString());
        }
        private void printUsage(String contextText){
            if(contextText.length() > 0){
                System.err.println(contextText);
            }
            System.err.println(USAGE_MESSAGE);
        }

    }
}



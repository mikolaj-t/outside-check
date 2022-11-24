package me.mikolajt.outsidecheck.providers;

import java.io.PrintStream;

public interface WeatherPrintProvider {
    void printWeather(Object obj);

    class StreamPrintProvider implements  WeatherPrintProvider {
        private final PrintStream stream;

        public StreamPrintProvider(PrintStream stream) {
            this.stream = stream;
        }

        @Override
        public void printWeather(Object obj) {
            stream.println(obj.toString());
        }
    }
}

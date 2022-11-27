# outside-check
Quickly check what's the weather like outside!

## How to use

Requires Java 19 and Maven to be installed, and obtained [OpenWeather API](https://openweathermap.org/) key.

First, `git clone` the repository. To build it, use `mvn package` command.
You need to configure the config file containing comma-separated city names and their lat and long coordinates, ex:
```
Warsaw,52.2297,21.0122
Dublin,53.3498,-6.2603
Buenos Aires,-34.6037,-58.3816
```
To check what its like outside, run the commands:
1) on Unix-like systems
    - `./outside <api_key> <config_path> <(daily | hourly)> <city>`
2) on Windows
   - `./outside.bat <api_key> <config_path> <(daily | hourly)> <city>`


and that's it!
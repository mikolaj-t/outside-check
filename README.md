# outside-check
Quickly check what's the weather like outside!

## How to use

Requires Java 19 and Maven to be installed, and obtained [OpenWeather API](https://openweathermap.org/) key.

First, `git clone` the repository. Then, use `mvn package` command and after the build has finished, run the commands:
1) on Unix-like systems
    - `./outside <api_key> <config_path> <(daily | hourly)> <city>`
2) on Windows
   - `./outside.bat <api_key> <config_path> <(daily | hourly)> <city>`

and that's it!
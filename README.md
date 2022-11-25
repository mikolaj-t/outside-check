# outside-check
Quickly check what's the weather like outside!

## How to use

Requires Java 19 and Maven to be installed, and the [OpenWeather API](https://openweathermap.org/) key.

First, `git clone` the repository. Then, use `mvn package` command and after the build has finished:

1) On Unix-like systems
    - run the command `./outside [api_key] [config] [daily/hourly] city` and that's it!
2) On Windows
   - With Window Subsystem for Linux / WSL installed: type `bash` in the terminal and then
run the command just like in part 1)
   - Without WSL it's less convenient: `java -jar ./target/outside-check-1.0-SNAPSHOT.jar [api_key] [config] [daily/hourly] city`
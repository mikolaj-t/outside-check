package me.mikolajt.outsidecheck;

import me.mikolajt.outsidecheck.providers.OptionsProvider;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

public class CliOptionProviderTest {
    private final PrintStream stderr = System.err;
    @Test
    public void cliOptionsProviderTooFewArguments(){
        String[] args = {"apiKey", "config.txt", "daily", "aaa"};
        var byteStream = new ByteArrayOutputStream();
        System.setErr(new PrintStream(byteStream));
        for(int i=0; i<args.length; i++){
            var slice = Arrays.copyOfRange(args, 0, i);
            var cliProvider = new OptionsProvider.CliOptionsProvider(slice);
            var options = cliProvider.getOptions();
            Assert.assertNull(options);
            String errorMessage = "Argument count mismatch: " + i + " vs 4" + System.lineSeparator() +
                    OptionsProvider.CliOptionsProvider.USAGE_MESSAGE
                            .replace("\t", "").trim();
            var actualMessage = byteStream.toString().trim().replace("\t", "");
            Assert.assertEquals(actualMessage, errorMessage);
            byteStream.reset();
        }
        System.setErr(stderr);
    }

    @Test
    public void cliOptionsProviderIncorrectForecastType(){
        String[] args = {"apiKey", "config.txt", "gibberish", "aaa"};
        var byteStream = new ByteArrayOutputStream();
        System.setErr(new PrintStream(byteStream));

        var cliProvider = new OptionsProvider.CliOptionsProvider(args);
        var options = cliProvider.getOptions();

        Assert.assertNull(options);
        String errorMessage = "Incorrect forecast type:" + args[2] + System.lineSeparator() +
                OptionsProvider.CliOptionsProvider.USAGE_MESSAGE;
        Assert.assertEquals(byteStream.toString().trim(), errorMessage);
        System.setErr(stderr);
    }

    @Test
    public void cliOptionsProviderTwoWordCityName(){
        String[] args = {"apiKey", "config.txt", "daily", "aaa bbb"};
        var cliProvider = new OptionsProvider.CliOptionsProvider(args);
        var expectedOptions = new OptionsProvider.Options(args[0], args[1], ForecastType.DAILY, args[3]);
        Assert.assertEquals(cliProvider.getOptions(), expectedOptions);
    }

}

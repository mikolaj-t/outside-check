package me.mikolajt.outsidecheck;

import me.mikolajt.outsidecheck.providers.ConfigProvider;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.util.Map;

public class FileConfigProviderTest {
    private final ConfigProvider.Config expectedCofnig = new ConfigProvider.Config(
            Map.of("WARSAW", new CityConfig("52.2297", "21.0122"),
                    "DUBLIN", new CityConfig("53.3498","-6.2603"),
                    "BUENOS AIRES", new CityConfig("-34.6037","-58.3816")));
    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    @Test
    public void testFileConfigProvider() throws IOException {
        File testConfig = tempFolder.newFile("config.txt");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(testConfig))) {
            bw.write("Warsaw,52.2297,21.0122");
            bw.newLine();
            bw.write("dublin,53.3498,-6.2603");
            bw.newLine();
            bw.write("Buenos Aires,-34.6037,-58.3816");
        }

        var fileConfigProvider = new ConfigProvider.FileConfigProvider(testConfig.getPath());
        Assert.assertEquals(fileConfigProvider.getConfig(), expectedCofnig);
    }
}

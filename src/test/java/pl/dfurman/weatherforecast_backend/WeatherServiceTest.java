package pl.dfurman.weatherforecast_backend;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.dfurman.weatherforecast_backend.weather.WeatherService;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class WeatherServiceTest {

    @Autowired
    private WeatherService weatherService;

    @Test
    public void assertIfCoordinatesAreValid() {
        assertTrue(weatherService.isValidCoordinates(50.0, 19.0));
        assertFalse(weatherService.isValidCoordinates(-91.0, 19.0));
        assertFalse(weatherService.isValidCoordinates(50.0, -181.0));

    }

    @Test
    public void testWeatherDataWithInvalidCoordinates() {
        assertThrows(IllegalArgumentException.class, () -> {
            weatherService.getForecastFor7Days(-81.0, 19.0);
        });
    }
}

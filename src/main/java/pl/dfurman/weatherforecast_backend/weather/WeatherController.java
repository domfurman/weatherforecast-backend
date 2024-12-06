package pl.dfurman.weatherforecast_backend.weather;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherController {

    private final WeatherService weatherService;
    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/weather")
    public Weather getForecastFor7Days(@RequestParam double latitude, @RequestParam double longitude) {
        return weatherService.getForecastFor7Days(latitude, longitude);
    }
}

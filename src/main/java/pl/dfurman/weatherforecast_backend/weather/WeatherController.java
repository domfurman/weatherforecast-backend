package pl.dfurman.weatherforecast_backend.weather;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherController {

    @Autowired
    WeatherService weatherService;

    @GetMapping("/weather")
    public Weather weather() {
        return weatherService.weatherCall();
    }
}

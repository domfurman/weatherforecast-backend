package pl.dfurman.weatherforecast_backend.weather;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Locale;

@Service
public class WeatherService {

    public Weather getForecastFor7Days(double latitude, double longitude) {
        String endpoint = String.format(
                Locale.US,
                "https://api.open-meteo.com/v1/forecast?latitude=%.2f&longitude=%.2f&daily=weather_code&current=temperature_2m,wind_speed_10m&hourly=temperature_2m,relative_humidity_2m,wind_speed_10m&daily=daylight_duration",
                latitude, longitude
        );

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endpoint))
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();

        HttpResponse<String> response = null;
        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            ObjectMapper mapper = new ObjectMapper();
            WeatherResponse weatherResponse = mapper.readValue(response.body(), WeatherResponse.class);
            return new Weather(
                    weatherResponse.getDate(),
                    weatherResponse.getMinMaxTemperatures(),
                    weatherResponse.getWeather_code()
            );
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }
}

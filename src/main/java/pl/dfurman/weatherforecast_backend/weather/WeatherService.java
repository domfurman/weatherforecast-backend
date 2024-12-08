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
                "https://api.open-meteo.com/v1/forecast?latitude=%.2f&longitude=%.2f&daily=weather_code,temperature_2m_max,temperature_2m_min,sunshine_duration&current",
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
                    Utils.calculateDailyDetails(
                            weatherResponse.getDate(),
                            weatherResponse.getDaily().getTemperature_2m_min(),
                            weatherResponse.getDaily().getTemperature_2m_max(),
                            weatherResponse.getDaily().getSunshine_duration(),
                            weatherResponse.getDaily().getWeather_code()
                    )
            );
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Weather getWeekSummary(double latitude, double longitude) {
        String endpoint = String.format(
                Locale.US,
                "https://api.open-meteo.com/v1/forecast?latitude=%.2f&longitude=%.2f&daily=sunshine_duration,temperature_2m_max,temperature_2m_min,precipitation_sum&hourly=pressure_msl",
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
                    Utils.calculateWeekSummary(
                            weatherResponse.getDaily().getTemperature_2m_min(),
                            weatherResponse.getDaily().getTemperature_2m_max(),
                            weatherResponse.getHourly().getPressure_msl(),
                            weatherResponse.getDaily().getSunshine_duration(),
                            weatherResponse.getDaily().getPrecipitation_sum())
                    );
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }
}

package pl.dfurman.weatherforecast_backend.weather;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import pl.dfurman.weatherforecast_backend.weather.exceptionshandlers.WeatherApiException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Locale;
import java.util.Map;

@Service
public class WeatherService {

    public boolean isValidCoordinates(double latitude, double longitude) {
        return latitude >= -90 && latitude <= 90 && longitude >= -180 && longitude <= 180;
    }

    public Weather getForecastFor7Days(double latitude, double longitude) {
        if (!isValidCoordinates(latitude, longitude)) {
            throw new IllegalArgumentException("Invalid coordinates: Latitude must be between -90 and 90, and Longitude must be between -180 and 180");
        }

        String endpoint = String.format(
                Locale.US,
                "https://api.open-meteo.com/v1/forecast?latitude=%.2f&longitude=%.2f&daily=weather_code,temperature_2m_max,temperature_2m_min,sunshine_duration&current=temperature_2m",
                latitude, longitude
        );

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endpoint))
                .GET()
                .build();

        HttpResponse<String> response = null;
        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200) {
                throw new WeatherApiException("Failed to fetch weather data. HTTP Status: " + response.statusCode());
            }

            ObjectMapper mapper = new ObjectMapper();
            WeatherResponse weatherResponse = mapper.readValue(response.body(), WeatherResponse.class);

            return new Weather(
                    weatherResponse.getDate(),
                    weatherResponse.getCurrent().getTemperature_2m(),
                    Utils.calculateDailyDetails(
                            weatherResponse.getDate(),
                            weatherResponse.getDaily().getTemperature_2m_min(),
                            weatherResponse.getDaily().getTemperature_2m_max(),
                            weatherResponse.getDaily().getSunshine_duration(),
                            weatherResponse.getDaily().getWeather_code()
                    )
            );
        } catch (IOException e) {
            e.printStackTrace();
            throw new WeatherApiException("Error occurred while fetching the weather data: " + e.getMessage(), e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new WeatherApiException("Request was interrupted while fetching the weather data", e);
        }
    }

    public Map<String, Object> getWeekSummary(double latitude, double longitude) {
        if (!isValidCoordinates(latitude, longitude)) {
            throw new IllegalArgumentException("Invalid coordinates: Latitude must be between -90 and 90, and Longitude must be between -180 and 180");
        }
        String endpoint = String.format(
                Locale.US,
                "https://api.open-meteo.com/v1/forecast?latitude=%.2f&longitude=%.2f&daily=sunshine_duration,temperature_2m_max,temperature_2m_min,precipitation_sum&hourly=pressure_msl",
                latitude, longitude
        );

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endpoint))
                .GET()
                .build();

        HttpResponse<String> response = null;
        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200) {
                throw new WeatherApiException("Failed to fetch weather data. HTTP Status: " + response.statusCode());
            }

            ObjectMapper mapper = new ObjectMapper();
            WeatherResponse weatherResponse = mapper.readValue(response.body(), WeatherResponse.class);

            return Utils.calculateWeekSummary(
                    weatherResponse.getDaily().getTemperature_2m_min(),
                    weatherResponse.getDaily().getTemperature_2m_max(),
                    weatherResponse.getHourly().getPressure_msl(),
                    weatherResponse.getDaily().getSunshine_duration(),
                    weatherResponse.getDaily().getPrecipitation_sum()
            );

        } catch (IOException e) {
            e.printStackTrace();
            throw new WeatherApiException("Error occurred while fetching the weather data: " + e.getMessage(), e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new WeatherApiException("Request was interrupted while fetching the weather data", e);
        }
    }
}

package pl.dfurman.weatherforecast_backend.weather;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;

public class Weather {
    private LocalDate date;
    private Map<String, ArrayList<Map<String, Double>>> dailyWeatherDetails;
    private int[] weatherCode;


    public Weather(LocalDate date, Map<String, ArrayList<Map<String, Double>>> minMaxTemperatures, int[] weatherCode) {
        this.date = date;
        this.dailyWeatherDetails = minMaxTemperatures;
        this.weatherCode = weatherCode;

    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Map<String, ArrayList<Map<String, Double>>> getDailyWeatherDetails() {
        return dailyWeatherDetails;
    }

    public void setDailyWeatherDetails(Map<String, ArrayList<Map<String, Double>>> dailyWeatherDetails) {
        this.dailyWeatherDetails = dailyWeatherDetails;
    }

    public int[] getWeatherCode() {
        return weatherCode;
    }

    public void setWeatherCode(int[] weatherCode) {
        this.weatherCode = weatherCode;
    }
}

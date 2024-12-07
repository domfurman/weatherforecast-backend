package pl.dfurman.weatherforecast_backend.weather;

import com.fasterxml.jackson.annotation.JsonInclude;
import pl.dfurman.weatherforecast_backend.weather.models.DailyWeatherDetails;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Weather {
    private LocalDate date;
    private Map<String, DailyWeatherDetails> dailyWeatherDetails;
    private int[] weatherCode;
    private Map<String, Object> weekSummary;

    public Weather(LocalDate date, Map<String, DailyWeatherDetails> dailyWeatherDetails, int[] weatherCode) {
        this.date = date;
        this.dailyWeatherDetails = dailyWeatherDetails;
        this.weatherCode = weatherCode;
    }

    public Weather(Map<String, Object> weekSummary) {
        this.weekSummary = weekSummary;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Map<String, DailyWeatherDetails> getDailyWeatherDetails() {
        return dailyWeatherDetails;
    }

    public void setDailyWeatherDetails(Map<String, DailyWeatherDetails> dailyWeatherDetails) {
        this.dailyWeatherDetails = dailyWeatherDetails;
    }

    public int[] getWeatherCode() {
        return weatherCode;
    }

    public void setWeatherCode(int[] weatherCode) {
        this.weatherCode = weatherCode;
    }

    public Map<String, Object> getWeekSummary() {
        return weekSummary;
    }

    public void setWeekSummary(Map<String, Object> weekSummary) {
        this.weekSummary = weekSummary;
    }
}

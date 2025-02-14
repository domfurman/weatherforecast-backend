package pl.dfurman.weatherforecast_backend.weather;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import pl.dfurman.weatherforecast_backend.weather.models.DailyWeatherDetails;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Weather {
    private LocalDate date;
    private Map<String, DailyWeatherDetails> dailyWeatherDetails;
    @JsonIgnore
    private Map<String, Object> weekSummary;
    private int currentTemperature;

    public Weather(LocalDate date, int currentTemperature, Map<String, DailyWeatherDetails> dailyWeatherDetails) {
        this.date = date;
        this.currentTemperature = currentTemperature;
        this.dailyWeatherDetails = dailyWeatherDetails;
    }

    public Weather(Map<String, Object> weekSummary) {
        this.weekSummary = weekSummary;
    }

    public Weather() {
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

    @JsonAnyGetter
    public Map<String, Object> getWeekSummary() {
        return weekSummary;
    }

    public void setWeekSummary(Map<String, Object> weekSummary) {
        this.weekSummary = weekSummary;
    }

    public int getCurrentTemperature() {
        return currentTemperature;
    }

    public void setCurrentTemperature(int currentTemperature) {
        this.currentTemperature = currentTemperature;
    }
}

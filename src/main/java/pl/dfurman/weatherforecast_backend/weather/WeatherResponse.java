package pl.dfurman.weatherforecast_backend.weather;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import pl.dfurman.weatherforecast_backend.weather.models.Current;
import pl.dfurman.weatherforecast_backend.weather.models.Daily;
import pl.dfurman.weatherforecast_backend.weather.models.Hourly;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherResponse {
    private double latitude;
    private double longitude;
    private Current current;
    private LocalDate date;
    private Hourly hourly;
    private Map<String, ArrayList<Map<String, Double>>> dailyDetails;
    private Daily daily;


    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public Hourly getHourly() {
        return hourly;
    }

    public void setHourly(Hourly hourly) {
        this.hourly = hourly;
    }

    public Current getCurrent() {
        return current;
    }

    public void setCurrent(Current current) {
        this.current = current;
    }

    public LocalDate getDate() {
        return LocalDate.parse(current.getTime(), formatter);
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Daily getDaily() {
        return daily;
    }

    public void setDaily(Daily daily) {
        this.daily = daily;
    }

    public Map<String, ArrayList<Map<String, Double>>> getDailyDetails() {
        return dailyDetails;
    }

    public void setDailyDetails(Map<String, ArrayList<Map<String, Double>>> dailyDetails) {
        this.dailyDetails = dailyDetails;
    }
}

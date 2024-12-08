package pl.dfurman.weatherforecast_backend.weather.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.format.DateTimeFormatter;

@JsonIgnoreProperties(ignoreUnknown = true)
public  class Hourly {
    private String[] time;
    private double[] temperature_2m;
    private double[] pressure_msl;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

    public double[] getTemperature_2m() {
        return temperature_2m;
    }

    public void setTemperature_2m(double[] temperature_2m) {
        this.temperature_2m = temperature_2m;
    }

    public String[] getTime() {
        return time;
    }

    public void setTime(String[] time) {
        this.time = time;
    }

    public double[] getPressure_msl() {
        return pressure_msl;
    }

    public void setPressure_msl(double[] pressure_msl) {
        this.pressure_msl = pressure_msl;
    }
}
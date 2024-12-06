package pl.dfurman.weatherforecast_backend.weather.models;

import java.util.Map;

public class Daily {
    private String[] time;
    private double[] daylight_duration;
    private int[] weather_code;

    public String[] getTime() {
        return time;
    }

    public void setTime(String[] time) {
        this.time = time;
    }

    public double[] getDaylight_duration() {
        return daylight_duration;
    }

    public void setDaylight_duration(double[] daylight_duration) {
        this.daylight_duration = daylight_duration;
    }

    public int[] getWeather_code() {
        return weather_code;
    }

    public void setWeather_code(int[] weather_code) {
        this.weather_code = weather_code;
    }
}

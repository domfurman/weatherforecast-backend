package pl.dfurman.weatherforecast_backend.weather.models;

public class Daily {
    private String[] time;
    private double[] sunshine_duration;
    private int[] weather_code;
    private double[] temperature_2m_max;
    private double[] temperature_2m_min;

    public String[] getTime() {
        return time;
    }

    public void setTime(String[] time) {
        this.time = time;
    }

    public double[] getSunshine_duration() {
        return sunshine_duration;
    }

    public void setSunshine_duration(double[] sunshine_duration) {
        this.sunshine_duration = sunshine_duration;
    }

    public int[] getWeather_code() {
        return weather_code;
    }

    public void setWeather_code(int[] weather_code) {
        this.weather_code = weather_code;
    }

    public double[] getTemperature_2m_max() {
        return temperature_2m_max;
    }

    public void setTemperature_2m_max(double[] temperature_2m_max) {
        this.temperature_2m_max = temperature_2m_max;
    }

    public double[] getTemperature_2m_min() {
        return temperature_2m_min;
    }

    public void setTemperature_2m_min(double[] temperature_2m_min) {
        this.temperature_2m_min = temperature_2m_min;
    }
}

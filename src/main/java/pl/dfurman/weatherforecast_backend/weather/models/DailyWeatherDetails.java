package pl.dfurman.weatherforecast_backend.weather.models;

public class DailyWeatherDetails {
    private double minTemperature;
    private double maxTemperature;
    private double generatedEnergy;
    private int weatherCode;

    public DailyWeatherDetails(double minTemperature, double maxTemperature, double generatedEnergy, int weatherCode) {
        this.minTemperature = minTemperature;
        this.maxTemperature = maxTemperature;
        this.generatedEnergy = generatedEnergy;
        this.weatherCode = weatherCode;
    }

    public double getMinTemperature() {
        return minTemperature;
    }

    public void setMinTemperature(double minTemperature) {
        this.minTemperature = minTemperature;
    }

    public double getMaxTemperature() {
        return maxTemperature;
    }

    public void setMaxTemperature(double maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    public double getGeneratedEnergy() {
        return generatedEnergy;
    }

    public void setGeneratedEnergy(double generatedEnergy) {
        this.generatedEnergy = generatedEnergy;
    }

    public int getWeatherCode() {
        return weatherCode;
    }

    public void setWeatherCode(int weatherCode) {
        this.weatherCode = weatherCode;
    }
}

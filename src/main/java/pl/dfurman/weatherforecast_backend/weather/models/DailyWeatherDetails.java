package pl.dfurman.weatherforecast_backend.weather.models;

public class DailyWeatherDetails {
    private double minTemperature;
    private double maxTemperature;
    private double generatedEnergy;

    public DailyWeatherDetails(double minTemperature, double maxTemperature, double generatedEnergy) {
        this.minTemperature = minTemperature;
        this.maxTemperature = maxTemperature;
        this.generatedEnergy = generatedEnergy;
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
}

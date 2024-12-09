package pl.dfurman.weatherforecast_backend.weather.models;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Current {
    private String time;
    private int temperature_2m;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getTemperature_2m() {
        return temperature_2m;
    }

    public void setTemperature_2m(int temperature_2m) {
        this.temperature_2m = temperature_2m;
    }
}

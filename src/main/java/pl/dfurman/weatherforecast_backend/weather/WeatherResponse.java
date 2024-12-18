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

//    public Map<String, ArrayList<Map<String, Double>>> getMinMaxTemperatures() {
//        Map<String, ArrayList<Map<String, Double>>> temps = new TreeMap<>();
//        int days = this.hourly.getTemperature_2m().length / 24;
//        double[] daylightDuration = this.daily.getDaylight_duration();
//        for (int i = 0; i < days; i++) {
//            String date = LocalDate.parse(this.hourly.getTime()[i * 24], formatter).toString();
//            ArrayList<Double> allTemperaturesOfDay = new ArrayList<>();
//            for (int j = 0; j < 24; j++) {
//                allTemperaturesOfDay.add(this.hourly.getTemperature_2m()[i * 24 + j]);
//            }
//            int finalI = i;
//            Double daylightDurationForDay = daylightDuration[finalI] / 3600;
//            Double generatedEnergy = Utils.calculateGeneratedEnergy(daylightDurationForDay);
//            double generatedEnergyRounded = (double) Math.round(generatedEnergy * 10000.0) / 10000.0;
//            temps.put(date, new ArrayList<>(Arrays.asList(
//                    new HashMap<String, Double>() {{
//                        put("minTemperature", Collections.min(allTemperaturesOfDay));
//                        put("maxTemperature", Collections.max(allTemperaturesOfDay));
//                        put("generatedEnergy", generatedEnergyRounded);
//                    }}
//            )));
//
//        }
//        return temps;
//    }

//    public Map<String, ArrayList<Map<String, Double>>> getDailyDetails() {
//        Map<String, ArrayList<Map<String, Double>>> dailyDetails = new TreeMap<>();
//        int days = this.daily.getTemperature_2m_max().length;
//        double[] minTemperatures = this.daily.getTemperature_2m_min();
//        double[] maxTemperatures = this.daily.getTemperature_2m_max();
//        double[] sunshineDuration = this.daily.getSunshine_duration();
//
//        for (int i = 0; i < days; i++) {
//            int finalI = i;
//            Double daylightDurationForDay = sunshineDuration[finalI] / 3600;
//            Double generatedEnergy = Utils.calculateGeneratedEnergy(daylightDurationForDay);
//            double generatedEnergyRounded = (double) Math.round(generatedEnergy * 10000.0) / 10000.0;
//            ArrayList<Map<String, Double>> minMaxTemperaturesOfDay = new ArrayList<>(Arrays.asList(
//                    new HashMap<String, Double>() {{
//                        put("minTemperature", minTemperatures[finalI]);
//                        put("maxTemperature", maxTemperatures[finalI]);
//                        put("generatedEnergy", generatedEnergyRounded);
//                    }}
//            ));
//            LocalDate date = this.getDate().plusDays(finalI);
//            dailyDetails.put(date.toString(), minMaxTemperaturesOfDay);
//        }
//
//        return dailyDetails;
//    }


    public Map<String, ArrayList<Map<String, Double>>> getDailyDetails() {
        return dailyDetails;
    }

    public void setDailyDetails(Map<String, ArrayList<Map<String, Double>>> dailyDetails) {
        this.dailyDetails = dailyDetails;
    }
}

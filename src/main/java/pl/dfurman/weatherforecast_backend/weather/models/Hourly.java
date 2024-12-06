package pl.dfurman.weatherforecast_backend.weather.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public  class Hourly {
    private String[] time;
    private double[] temperature_2m;
    private Daily daily;
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

//    public Map<String, ArrayList<Double>> getMaxMinTemperaturesOfDays() {
//        Map<String, ArrayList<Double>> temps = new TreeMap<>();
//        int days = temperature_2m.length / 24;
//        for (int i = 0; i < days; i++) {
//            String date = LocalDate.parse(time[i*24], formatter).toString();
//            ArrayList<Double> allTemperaturesOfDay = new ArrayList<>();
//            for (int j = 0; j< 24; j++) {
//                allTemperaturesOfDay.add(temperature_2m[i*24 + j]);
//            }
//            temps.put(date, new ArrayList<>(Arrays.asList(
//                    Collections.min(allTemperaturesOfDay),
//                    Collections.max(allTemperaturesOfDay))));
//        }
//        return temps;
//    }

}
package pl.dfurman.weatherforecast_backend.weather;

import pl.dfurman.weatherforecast_backend.weather.models.DailyWeatherDetails;

import java.time.LocalDate;
import java.util.*;

public class Utils {
    private final static double systemPower = 2.5;
    private final static double panelEfficiency = 0.2;

    // Based on data and sources found on the internet, I decided to use the value of sunshine duration
    // instead of daylight duration for the calculations. Sunshine duration represents the actual
    // time the sun was shining and is more accurate for the purpose of this analysis,
    // as it excludes periods of cloud cover or other obstructions.
    public static double calculateGeneratedEnergy(double exposureTime) {
        return systemPower * exposureTime * panelEfficiency;
    }


    public static Map<String, DailyWeatherDetails> calculateDailyDetails(
            LocalDate startDate,
            double[] minTemperatures,
            double[] maxTemperatures,
            double[] sunshineDurations) {
        Map<String, DailyWeatherDetails> dailyDetails = new TreeMap<>();
        for (int i = 0; i < minTemperatures.length; i++) {
            Double daylightDurationForDay = sunshineDurations[i] / 3600;
            Double generatedEnergy = calculateGeneratedEnergy(daylightDurationForDay);
            double generatedEnergyRounded = Math.round(generatedEnergy * 10000.0) / 10000.0;

            LocalDate date = startDate.plusDays(i);
            dailyDetails.put(date.toString(), new DailyWeatherDetails(
                    minTemperatures[i],
                    maxTemperatures[i],
                    generatedEnergyRounded
            ));
        }
        return dailyDetails;
    }

    public static ArrayList<Double> calculateExtremeTemperatures(
            double[] minTemperatures,
            double[] maxTemperatures) {
        return new ArrayList<>(Arrays.asList(
                Arrays.stream(minTemperatures).min().orElse(Double.NaN),
                Arrays.stream(maxTemperatures).max().orElse(Double.NaN)
        ));
    }

    public static double calculateAveragePressure(double[] surfacePressure) {
        return Arrays.stream(surfacePressure).average().orElse(Double.NaN);
    }

    public static double calculateAverageSunshineDuration(double[] sunshineDuration) {
        return Arrays.stream(sunshineDuration).average().orElse(Double.NaN);
    }

    public static Map<String, Double> calculateWeekSummary(
            double[] minTemperatures,
            double[] maxTemperatures,
            double[] surfacePressure,
            double[] sunshineDuration) {
        Double averagePressure = calculateAveragePressure(surfacePressure);
        Double averageSunshineDuration = calculateAverageSunshineDuration(sunshineDuration);
        ArrayList<Double> extremeTemperatures = calculateExtremeTemperatures(minTemperatures, maxTemperatures);

        return new HashMap<>() {{
            put("averagePressure", averagePressure);
            put("averageSunshineDuration", averageSunshineDuration);
            put("lowestTemperature", extremeTemperatures.get(0));
            put("highestTemperature", extremeTemperatures.get(1));
        }};
    }
}

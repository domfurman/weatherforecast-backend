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
            double[] sunshineDurations,
            int[] weatherCodes) {
        Map<String, DailyWeatherDetails> dailyDetails = new TreeMap<>();
        for (int i = 0; i < minTemperatures.length; i++) {
            Double sunshineDurationForDay = sunshineDurations[i] / 3600;
            Double generatedEnergy = calculateGeneratedEnergy(sunshineDurationForDay);
            double generatedEnergyRounded = Math.round(generatedEnergy * 10000.0) / 10000.0;

            LocalDate date = startDate.plusDays(i);
            dailyDetails.put(date.toString(), new DailyWeatherDetails(
                    Math.round(minTemperatures[i]),
                    Math.round(maxTemperatures[i]),
                    generatedEnergyRounded,
                    weatherCodes[i]
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

    // Using sea-level pressure instead of surface pressure for calculating the average pressure.
    // Sea-level pressure is more consistent and independent of altitude, making it suitable
    // for standardizing pressure values across different locations.
    public static double calculateAveragePressure(double[] pressure) {
        return Math.round((Arrays.stream(pressure).average().orElse(Double.NaN) * 100.0)) / 100.0;
    }

    public static double calculateAverageSunshineDuration(double[] sunshineDuration) {
        return Math.round((Arrays.stream(sunshineDuration).average().orElse(Double.NaN) / 3600.0) * 100.0) / 100.0;
    }

    public static boolean isWeekRainy(double[] precipitationSum) {
        long rainyDays = Arrays.stream(precipitationSum).filter(p -> p > 0).count();
        return rainyDays >= 4;
    }

    public static Map<String, Object> calculateWeekSummary(
            double[] minTemperatures,
            double[] maxTemperatures,
            double[] pressure,
            double[] sunshineDuration,
            double[] precipitationSum) {
        Double averagePressure = calculateAveragePressure(pressure);
        Double averageSunshineDuration = calculateAverageSunshineDuration(sunshineDuration);
        ArrayList<Double> extremeTemperatures = calculateExtremeTemperatures(minTemperatures, maxTemperatures);
        boolean isWeekRainy = isWeekRainy(precipitationSum);

        return new HashMap<>() {{
            put("averagePressure", averagePressure);
            put("averageSunshineDuration", averageSunshineDuration);
            put("lowestTemperature", Math.round(extremeTemperatures.get(0)));
            put("highestTemperature", Math.round(extremeTemperatures.get(1)));
            put("isWeekRainy", isWeekRainy);
        }};
    }
}

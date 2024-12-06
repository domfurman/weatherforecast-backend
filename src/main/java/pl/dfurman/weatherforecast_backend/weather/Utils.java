package pl.dfurman.weatherforecast_backend.weather;

public class Utils {
    private final static double systemPower = 2.5;
    private final static double panelEfficiency = 0.2;

    public static double calculateGeneratedEnergy(double exposureTime) {
        return systemPower * exposureTime * panelEfficiency;
    }
}

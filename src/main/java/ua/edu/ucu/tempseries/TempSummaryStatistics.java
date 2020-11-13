package ua.edu.ucu.tempseries;

import lombok.Getter;

public class TempSummaryStatistics {
    @Getter
    private final double avgTemp, devTemp, minTemp, maxTemp;

    public TempSummaryStatistics(double avgTemp, double devTemp, double minTemp, double maxTemp){
        this.avgTemp = avgTemp;
        this.devTemp = devTemp;
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
    }
}

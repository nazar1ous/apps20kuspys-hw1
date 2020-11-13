package ua.edu.ucu.tempseries;

import lombok.Getter;

import java.util.InputMismatchException;
import java.lang.Math;

public class TemperatureSeriesAnalysis {
    final double absZero = - 273;
    @Getter
    private double[] temperatureSeries;
    @Getter
    private int numElements = 0;

    public TemperatureSeriesAnalysis(double[] temperatureSeries) {
        for (double t : temperatureSeries) {
            if (t < absZero) {
                throw new InputMismatchException("There is no" +
                        " stated temperature less than absolute zero." +
                        " Contact scientists");
            }
        }
        this.temperatureSeries = temperatureSeries;
        numElements = temperatureSeries.length;
    }


    public void checkValidSeries() throws IllegalArgumentException {
        if (numElements == 0) {
            throw new IllegalArgumentException("Series is empty");
        }
    }

    public double average() throws IllegalArgumentException {
        checkValidSeries();
        double summa = 0;
        for (int i = 0; i < numElements; ++ i) {
            summa += temperatureSeries[i];
        }
        return summa / numElements;
    }

    public double deviation() throws IllegalArgumentException {
        checkValidSeries();
        double expectation = this.average();
        double diff = 0;
        for (int i = 0; i < numElements; ++ i) {
            diff += Math.pow((temperatureSeries[i] - expectation), 2);
        }
        return Math.sqrt(diff / numElements);
    }

    public double min() throws IllegalArgumentException {
        checkValidSeries();
        double min = temperatureSeries[0];
        for (int i = 1; i < numElements; ++ i) {
            if (temperatureSeries[i] < min) {
                min = temperatureSeries[i];
            }
        }
        return min;
    }

    public double max() throws IllegalArgumentException {
        checkValidSeries();
        double max = temperatureSeries[0];
        for (int i = 1; i < numElements; ++ i) {
            if (temperatureSeries[i] > max) {
                max = temperatureSeries[i];
            }
        }
        return max;
    }

    public double findTempClosestToZero() throws IllegalArgumentException {
        return findTempClosestToValue(0);
    }

    public double findTempClosestToValue(double tempValue)
            throws IllegalArgumentException {
        checkValidSeries();
        double closestValue = temperatureSeries[0];
        double closestRange = Math.abs(closestValue - tempValue);
        double tempRange;
        for (int i = 1; i < numElements; ++ i) {
            tempRange = Math.abs(temperatureSeries[i] - tempValue);
            if (tempRange < closestRange) {
                closestRange = tempRange;
                closestValue = temperatureSeries[i];
            }
            if (tempRange == closestRange) {
                if (closestValue < temperatureSeries[i]) {
                    closestValue = temperatureSeries[i];
                }
            }
        }
        return closestValue;
    }

    public double[] findTempsLessThen(double tempValue)
            throws IllegalArgumentException {
        checkValidSeries();
        int len = 0;
        for (int i = 0; i < numElements; ++ i) {
            if (temperatureSeries[i] < tempValue) {
                len += 1;
            }
        }

        double[] tempLess = new double[len];
        int count = 0;
        for (int i = 0; i < numElements; ++ i) {
            if (temperatureSeries[i] < tempValue) {
                tempLess[count] = temperatureSeries[i];
                count++;
            }
        }
        return tempLess;
    }

    public double[] findTempsGreaterThen(double tempValue)
            throws IllegalArgumentException {
        checkValidSeries();
        int len = 0;
        for (int i = 0; i < numElements; ++ i) {
            if (temperatureSeries[i] > tempValue) {
                len += 1;
            }
        }
        double[] tempGreat = new double[len];
        int count = 0;
        for (int i = 0; i < numElements; ++ i) {
            if (temperatureSeries[i] > tempValue) {
                tempGreat[count] = temperatureSeries[i];
                count++;
            }
        }
        return tempGreat;
    }

    public TempSummaryStatistics summaryStatistics()
            throws IllegalArgumentException {
        checkValidSeries();
        return new TempSummaryStatistics(this.average(), this.deviation(),
                this.min(), this.max());
    }

    public int addTemps(double... temps) throws IllegalArgumentException {
        int prevLen = temperatureSeries.length;
        if (prevLen < numElements + temps.length) {
            temperatureSeries = copyArrayWithLen(this.temperatureSeries,
                    prevLen * 2 + temps.length, numElements);
            int counter = numElements;
            for (double t : temps) {
                temperatureSeries[counter] = t;
                counter++;
            }
            numElements = counter;
        }
        return 0;
    }

    static public double[] copyArrayWithLen(double[] arr,
                                            int newLen, int numElements) {
        double[] temp = new double[newLen];
        if (numElements >= 0) System.arraycopy(arr, 0,
                temp, 0, numElements);
        return temp;
    }
}

package edu.midlandstech.myapplication;

public class Currency {
    private int id;
    private String unit;
    private double factor;
    public Currency(int newId, String newUnit, double newFactor) {
        id = newId;
        unit = newUnit;
        factor = newFactor;
    }

    public String getUnit() {
        return unit;
    }
    public int getId() {
        return id;
    }


    public double toUSD(double amount) {
        return factor*amount;
    }


    public double fromUSD(double USD) {
        return USD/factor;
    }

    public String toString() {
        return id + " " + unit + " " + factor;
    }
}

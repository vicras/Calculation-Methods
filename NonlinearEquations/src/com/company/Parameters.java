package com.company;

public class Parameters {
    int iteration;
    double discrepancy;

    public Parameters(int iteration, double discrepancy) {
        this.iteration = iteration;
        this.discrepancy = discrepancy;
    }

    @Override
    public String toString() {
        return iteration + ";" + discrepancy;
    }
}

package com.company.task1;

import com.company.SolvingMethod;

import java.util.Arrays;

public class Task1 {

    class Interval {
        double a;
        double b;

        public Interval(double a, double b) {
            this.a = a;
            this.b = b;
        }
    }

    final double exactness = Math.pow(10, -12);

    double getFunction(double x) {
        return (x - 4) * Math.atan(x * x - 3);
    }

    Interval[] intervals = {
            new Interval(-1.8, -1.7),
            new Interval(1.7, 1.8),
            new Interval(3.95, 4.05)
    };


    public void solve() {
        SolvingMethod bisectionMethod = new BisectionMethod(this::getFunction, exactness);
        SolvingMethod chordMethod = new ChordMethod(this::getFunction, exactness);

        double[] ans_chord = new double[intervals.length];
        double[] ans_bis = new double[intervals.length];

        for (int i = 0; i < intervals.length; i++) {
            ans_bis[i] = bisectionMethod.solve(intervals[i].a, intervals[i].b);
            if(i==1)
                ans_chord[i] = chordMethod.solve(intervals[i].b, intervals[i].a);
            else
                ans_chord[i] = chordMethod.solve(intervals[i].a, intervals[i].b);
        }

        System.out.println("bisection answers");
        Arrays.stream(ans_bis).forEach(System.out::println);
        System.out.println("chord answers");
        Arrays.stream(ans_chord).forEach(System.out::println);

    }

}

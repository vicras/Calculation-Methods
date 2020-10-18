package com.company;


import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.stream.DoubleStream;


public class Main {

    private static final double[][] a = {
            {3, -4, 1},
            {1, -2, 3},
            {2, 1, -1}
    };

    private static final double[] b = {1, 1, 1};

    private static final double[][] testA = {
            {20.9, 1.2, 2.1, 0.9},
            {1.2, 21.2, 1.5, 2.5},
            {2.1, 1.5, 19.8, 1.3},
            {0.9, 2.5, 1.3, 32.1}
    };

    private static final double[] testB = {21.7, 27.46, 28.76, 49.72};

    public static void main(String[] args) {

        int size = 5;
        System.out.println("Matrix size " + size);

        TaskMatrix matrix = new TaskMatrix(size);
        var benchAns = fillAnswer(matrix.getLength(), 3);
        double[] partB = ApliedGouseSeidel.getPartB(matrix, benchAns);

        System.out.println(matrix);
        System.out.println("Part b:");
        System.out.println(Arrays.toString(partB));

        var ags = new ApliedGouseSeidel(2000000000, 0.01, matrix, partB);


        var beginDate = System.nanoTime();
        double[] ans = ags.countSymmetric(fillAnswer(matrix.getLength(), 0));
        var endDate = System.nanoTime();

        System.out.println("Begin time " + beginDate);
        System.out.println("End time " + endDate);

        System.out.println("Result time " + (endDate - beginDate));

        System.out.println("My program result " + Arrays.toString(ans));
        System.out.println("Correct answer " + Arrays.toString(benchAns));
    }

    private static double[] fillAnswer(int n, double val) {
        var answer = new double[n];
        for (int i = 0; i < n; i++) {
            answer[i] = val;
        }
        return answer;
    }
}

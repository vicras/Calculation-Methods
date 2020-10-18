package com.company;

public class TaskMatrix implements Matrixable {
    private int rank;

    public TaskMatrix(int rank) {
        this.rank = rank;
    }

    public int getLength() {
        return rank;
    }

    public double get(int i, int j) {
        if(i>=rank || j >= rank)
            throw new IndexOutOfBoundsException();

        if (j < i) {
            int t = i;
            i = j;
            j = t;
        }

        if (i == j) return i + 1;

        if(j -i ==2) return (j-1)/2.0;

        return 0.0;
    }


    @Override
    public String toString() {

        var ans = new StringBuilder();

        for (int i = 0; i < getLength(); i++) {
            for (int j = 0; j < getLength(); j++) {
                ans.append(get(i, j)).append("\t");
            }
            ans.append("\n");
        }

        return ans.toString();
    }
}

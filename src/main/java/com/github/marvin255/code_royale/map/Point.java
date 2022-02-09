package com.github.marvin255.code_royale.map;

public class Point {
    private final int x;
    private final int y;

    public Point(double x, double y) {
        this((int) Math.round(x), (int) Math.round(y));
    }

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    @Override
    public String toString()
    {
        return "(" + x + ", " + y + ")";
    }
}

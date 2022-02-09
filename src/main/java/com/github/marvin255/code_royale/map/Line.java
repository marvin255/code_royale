package com.github.marvin255.code_royale.map;

class Line
{
    private final double a;
    private final double b;
    private final double c;

    Line(double a, double b, double c)
    {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public double getA()
    {
        return a;
    }

    public double getB()
    {
        return b;
    }

    public double getC()
    {
        return c;
    }

    public double getAngleK()
    {
        return -(a / b);
    }

    public double getAngleB()
    {
        return -(c / b);
    }

    @Override
    public String toString() {
        return a + "x + " + b + "y + " + c;
    }
}
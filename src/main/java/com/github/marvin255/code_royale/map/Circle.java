package com.github.marvin255.code_royale.map;

public class Circle implements MapObject
{
    private final Point point;
    private final int radius;

    public Circle (Point point, int radius) {
        this.point = point;
        this.radius = radius;
    }

    @Override
    public Point getPoint() {
        return point;
    }

    @Override
    public int getRadius() {
        return radius;
    }
}

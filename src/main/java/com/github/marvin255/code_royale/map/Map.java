package com.github.marvin255.code_royale.map;

public class Map {
    private final int width;
    private final int height;

    public Map (int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Point createPoint(int x, int y)
    {
        return new Point(x, y);
    }

    public Point createPoint(double x, double y)
    {
        return new Point(x, y);
    }

    public boolean isPointValid(MapObject object)
    {
        return this.isPointValid(object.getPoint());
    }

    public boolean isPointValid(Point point)
    {
        return point.getX() >= 0
                && point.getX() <= width
                && point.getY() >= 0
                && point.getY() <= height;
    }

    public Point validatePoint(Point point)
    {
        if (isPointValid(point)) {
            return point;
        }

        int x = point.getX();
        if (x < 0) {
            x = 0;
        } else if (x > width) {
            x = width;
        }

        int y = point.getY();
        if (y < 0) {
            y = 0;
        } else if (y > height) {
            y = height;
        }

        return this.createPoint(x, y);
    }
}

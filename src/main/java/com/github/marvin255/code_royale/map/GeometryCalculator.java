package com.github.marvin255.code_royale.map;

import java.util.*;

public class GeometryCalculator {
    private static final double EPS = 0.0000001;

    private final Map map;

    public GeometryCalculator(Map map)
    {
        this.map = map;
    }

    public <T extends MapObject> List<T> findObjectsInRadius(MapObject basePoint, List<T> objects, int radius)
    {
        return this.findObjectsInRadius(basePoint.getPoint(), objects, radius);
    }

    public <T extends MapObject> List<T> findObjectsInRadius(Point basePoint, List<T> objects, int radius)
    {
        List<T> selectedObjects = new ArrayList<>();
        for (T currentObject : objects) {
            if (getDistanceBetween(basePoint, currentObject) <= radius) {
                selectedObjects.add(currentObject);
            }
        }
        return selectedObjects;
    }

    public <T extends MapObject> T findClosestObject(MapObject basePoint, List<T> allObjects)
    {
        return this.findClosestObject(basePoint.getPoint(), allObjects);
    }

    public <T extends MapObject> T findClosestObject(Point basePoint, List<T> allObjects)
    {
        T closest = null;
        int min = -1;
        for (T currentObject : allObjects) {
            int distance = getDistanceBetween(basePoint, currentObject);
            if (min == -1 || distance < min) {
                min = distance;
                closest = currentObject;
            }
        }
        return closest;
    }

    public <T extends MapObject> List<T> findObjectsOnStraight(MapObject first, MapObject second, List<T> allObjects)
    {
        return this.findObjectsOnStraight(first.getPoint(), second.getPoint(), allObjects);
    }

    public <T extends MapObject> List<T> findObjectsOnStraight(Point first, Point second, List<T> allObjects)
    {
        Line line = getLineByTwoPoints(first, second);
        List<T> itemsOnStraight = new ArrayList<>();
        for (T current : allObjects) {
            List<Point> crossPoints = getCrossPoints(line, current);
            if (crossPoints.size() == 2) {
                itemsOnStraight.add(current);
            }
        }
        return itemsOnStraight;
    }

    public Point getClosestTangentCoordinate(MapObject first, MapObject second)
    {
        List<Line> tangents = getTangentsByTwoCircles(first, second);
        Point closestTangentPoint = null;
        int minDistance = -1;
        for (Line tangent : tangents) {
            Point tangentPoint = getCrossPoints(tangent, second).get(0);
            int distance = getDistanceBetween(first.getPoint(), tangentPoint);
            if (minDistance == -1 || minDistance > distance) {
                minDistance = distance;
                closestTangentPoint = tangentPoint;
            }
        }
        return closestTangentPoint;
    }

    public int getDistanceBetween(MapObject first, MapObject second)
    {
        int distanceWithRadius = this.getDistanceBetween(first.getPoint(), second.getPoint());
        int distance = distanceWithRadius - first.getRadius() - second.getRadius();
        return Math.max(distance, 0);
    }

    public int getDistanceBetween(MapObject first, Point second)
    {
        int distanceWithRadius = this.getDistanceBetween(first.getPoint(), second);
        int distance = distanceWithRadius - first.getRadius();
        return Math.max(distance, 0);
    }

    public int getDistanceBetween(Point first, MapObject second)
    {
        int distanceWithRadius = this.getDistanceBetween(first, second.getPoint());
        int distance = distanceWithRadius - second.getRadius();
        return Math.max(distance, 0);
    }

    public int getDistanceBetween(Point first, Point second)
    {
        double xDiff = Math.pow(second.getX() - first.getX(), 2);
        double yDiff = Math.pow(second.getY() - first.getY(), 2);
        double distance = Math.sqrt(xDiff + yDiff);
        return (int) Math.round(distance);
    }

    private double getDistanceBetweenPointAndLine(Point point, Line line)
    {
        return Math.abs(line.getA() * point.getX() + line.getB() * point.getY() + line.getC())
                / Math.sqrt(line.getA() * line.getA() + line.getB() * line.getB());
    }

    private Line getLineByTwoPoints(Point first, Point second)
    {
        double k = (double) (first.getY() - second.getY()) / (first.getX() - second.getX());
        double b = second.getY() - k * second.getX();
        return new Line(k, -1, b);
    }

    private List<Line> getTangentsByTwoCircles(MapObject circle1, MapObject circle2)
    {
        List<Line> tangents = new ArrayList<>();
        for (int i = -1; i <= 1; i += 2) {
            for (int j = -1; j <= 1; j += 2) {
                double diffX = circle2.getPoint().getX() - circle1.getPoint().getX();
                double diffY = circle2.getPoint().getY() - circle1.getPoint().getY();
                double r1 = circle1.getRadius() * i;
                double r2 = circle2.getRadius() * j;
                double r = r2 - r1;
                double z = diffX * diffX + diffY * diffY;
                double d = z - r * r;
                if (d < -EPS) {
                    continue;
                }
                d = Math.sqrt(Math.abs(d));
                double a = (diffX * r + diffY * d) / z;
                double b = (diffY * r - diffX * d) / z;
                double c = r1 - a * circle1.getPoint().getX() - b * circle1.getPoint().getY();
                tangents.add(new Line(a, b, c));
            }
        }
        return tangents;
    }

    private List<Point> getCrossPoints(Line line, MapObject circle)
    {
        List<Point> points = new ArrayList<>();
        double x0 = circle.getPoint().getX();
        double y0 = circle.getPoint().getY();
        double r = circle.getRadius();
        double k = line.getAngleK();
        double lb = line.getAngleB();
        double a = k * k + 1;
        double b = 2 * (k * lb - k * y0 - x0);
        double c = lb * lb - 2 * lb * y0 + y0 * y0 + x0 * x0 - r * r;
        double d = b * b - 4 * a * c;
        if (d > -EPS && d < EPS) {
            double x = -b / (2 * a);
            double y = k * x + lb;
            if (x >= 0 && x <= map.getWidth() && y >= 0 && y <= map.getHeight()) {
                points.add(new Point(x, y));
            }
        } else if (d > EPS) {
            double x1 = (-b + Math.sqrt(d)) / (2 * a);
            double y1 = k * x1 + lb;
            if (x1 >= 0 && x1 <= map.getWidth() && y1 >= 0 && y1 <= map.getHeight()) {
                points.add(new Point(x1, y1));
            }
            double x2 = (-b - Math.sqrt(d)) / (2 * a);
            double y2 = k * x2 + lb;
            if (x2 >= 0 && x2 <= map.getWidth() && y2 >= 0 && y2 <= map.getHeight()) {
                points.add(new Point(x2, y2));
            }
        }
        return points;
    }
}

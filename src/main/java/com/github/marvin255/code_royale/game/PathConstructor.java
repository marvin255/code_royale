package com.github.marvin255.code_royale.game;

import com.github.marvin255.code_royale.game_object.Structure;
import com.github.marvin255.code_royale.game_object.StructureCollection;
import com.github.marvin255.code_royale.game_object.Unit;
import com.github.marvin255.code_royale.map.*;
import com.github.marvin255.code_royale.map.Map;

import java.util.*;

public class PathConstructor {
    private final Map map;
    private final GeometryCalculator geometryCalculator;

    public PathConstructor(Map map, GeometryCalculator geometryCalculator)
    {
        this.map = map;
        this.geometryCalculator = geometryCalculator;
    }

    public List<Point> createPath(Unit unit, MapObject target, StructureCollection structures)
    {
        List<Point> path = new ArrayList<>();
        List<Structure> objectsOnStraight = geometryCalculator.findObjectsOnStraight(unit, target, structures);

        return path;
    }

    public Point runaway(Unit prey, List<Unit> hunters)
    {
        Point huntersCenter = geometryCalculator.findAverageCenter(hunters);
        Circle runawayArea = new Circle(prey.getPoint(), prey.getRadius());
        List<Point> crossPoints = geometryCalculator.getCrossPointsLineCircle(huntersCenter, prey, runawayArea);

        Point runawayPoint = crossPoints.stream()
                .max(Comparator.comparingInt(p -> geometryCalculator.getDistanceBetween(huntersCenter, p)))
                .orElse(prey.getPoint());

        return this.map.validatePoint(runawayPoint);
    }
}

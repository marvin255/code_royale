package com.github.marvin255.code_royale.game;

import com.github.marvin255.code_royale.game_object.Structure;
import com.github.marvin255.code_royale.game_object.StructureCollection;
import com.github.marvin255.code_royale.game_object.Unit;
import com.github.marvin255.code_royale.map.GeometryCalculator;
import com.github.marvin255.code_royale.map.MapObject;
import com.github.marvin255.code_royale.map.Point;
import java.util.*;

public class PathConstructor {
    private final GeometryCalculator geometryCalculator;

    public PathConstructor(GeometryCalculator geometryCalculator)
    {
        this.geometryCalculator = geometryCalculator;
    }

    public List<Point> createPath(Unit unit, MapObject target, StructureCollection structures)
    {
        List<Point> path = new ArrayList<>();
        List<Structure> objectsOnStraight = geometryCalculator.findObjectsOnStraight(unit, target, structures);

        return path;
    }
}

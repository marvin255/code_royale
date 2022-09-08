package com.github.marvin255.code_royale.game.strategies_queen;

import com.github.marvin255.code_royale.game.*;
import com.github.marvin255.code_royale.game_object.*;
import com.github.marvin255.code_royale.map.GeometryCalculator;
import com.github.marvin255.code_royale.map.MapObject;
import com.github.marvin255.code_royale.map.Point;

import java.util.List;

public class RunawayIfDanger implements Strategy {
    private final GeometryCalculator geometryCalculator;
    private final PathConstructor pathConstructor;
    private final int dangerRadius;

    public RunawayIfDanger(GeometryCalculator geometryCalculator, PathConstructor pathConstructor, int dangerRadius)
    {
        this.geometryCalculator = geometryCalculator;
        this.pathConstructor = pathConstructor;
        this.dangerRadius = dangerRadius;
    }

    public RunawayIfDanger(GeometryCalculator geometryCalculator, PathConstructor pathConstructor)
    {
        this(geometryCalculator, pathConstructor, 100);
    }

    @Override
    public StrategyResult run(UnitCollection units, StructureCollection structures, Treasury treasury)
    {
        Unit queen = units.getFriendlyQueen();
        List<Unit> enemyKnights = units.getEnemyType(UnitType.KNIGHT);
        List<Unit> dangerousUnits = geometryCalculator.findObjectsInRadius(queen, enemyKnights, dangerRadius);

        if (dangerousUnits.size() == 0) {
            return StrategyResult.none();
        }

        return null;
    }

    private Point findRunawayTargetPoint(Unit queen, UnitCollection units, StructureCollection structures)
    {
        List<Structure> towers = structures.getFriendlyByType(StructureType.TOWER);
        GameObject tower = geometryCalculator.findClosestObject(queen, towers);
        if (tower != null) {
            return tower.getPoint();
        }

        List<Unit> alliedArchers = units.getFriendlyType(UnitType.ARCHER);
        Unit archer = geometryCalculator.findClosestObject(queen, alliedArchers);
        if (archer != null) {
            return archer.getPoint();
        }


    }
}

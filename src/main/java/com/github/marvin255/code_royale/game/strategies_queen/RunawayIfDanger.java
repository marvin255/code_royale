package com.github.marvin255.code_royale.game.strategies_queen;

import com.github.marvin255.code_royale.game.*;
import com.github.marvin255.code_royale.game_object.*;
import com.github.marvin255.code_royale.map.GeometryCalculator;

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
        this(geometryCalculator, pathConstructor, 400);
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

        return StrategyResult.move(
                StrategyCoefficient.HIGHEST,
                pathConstructor.runaway(queen, dangerousUnits)
        );
    }
}

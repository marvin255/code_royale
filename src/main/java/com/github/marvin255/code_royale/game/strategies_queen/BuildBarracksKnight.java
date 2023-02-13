package com.github.marvin255.code_royale.game.strategies_queen;

import com.github.marvin255.code_royale.game.Strategy;
import com.github.marvin255.code_royale.game.StrategyCoefficient;
import com.github.marvin255.code_royale.game.StrategyResult;
import com.github.marvin255.code_royale.game.Treasury;
import com.github.marvin255.code_royale.game_object.*;
import com.github.marvin255.code_royale.map.GeometryCalculator;

import java.util.List;

public class BuildBarracksKnight implements Strategy {
    private final GeometryCalculator geometryCalculator;

    public BuildBarracksKnight(GeometryCalculator geometryCalculator)
    {
        this.geometryCalculator = geometryCalculator;
    }

    public StrategyResult run(UnitCollection units, StructureCollection structures, Treasury treasury)
    {
        Unit queen = units.getFriendlyQueen();
        Structure closestSite = geometryCalculator.findClosestObject(queen, structures.getNeutral());

        if (closestSite == null) {
            return StrategyResult.none();
        } else {
            return StrategyResult.build(getCoefficient(structures), closestSite, StructureType.BARRACKS_KNIGHT);
        }
    }

    private StrategyCoefficient getCoefficient(StructureCollection structures)
    {
        int count = structures.getFriendlyByType(StructureType.BARRACKS_KNIGHT).size();

        if (count == 0) {
            return StrategyCoefficient.HIGHT;
        } else if (count == 1) {
            return StrategyCoefficient.MEDIUM;
        } else {
            return StrategyCoefficient.LOWEST;
        }
    }
}

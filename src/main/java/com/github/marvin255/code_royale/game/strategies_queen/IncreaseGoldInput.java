package com.github.marvin255.code_royale.game.strategies_queen;

import com.github.marvin255.code_royale.game.Strategy;
import com.github.marvin255.code_royale.game.StrategyCoefficient;
import com.github.marvin255.code_royale.game.StrategyResult;
import com.github.marvin255.code_royale.game.Treasury;
import com.github.marvin255.code_royale.game_object.*;
import com.github.marvin255.code_royale.map.GeometryCalculator;

import java.util.List;
import java.util.stream.Collectors;

public class IncreaseGoldInput implements Strategy {
    private final GeometryCalculator geometryCalculator;
    private final int thresholdValue;

    public IncreaseGoldInput(GeometryCalculator geometryCalculator, int thresholdValue)
    {
        this.geometryCalculator = geometryCalculator;
        this.thresholdValue = thresholdValue;
    }

    public IncreaseGoldInput(GeometryCalculator geometryCalculator)
    {
        this(geometryCalculator, 5);
    }

    public StrategyResult run(UnitCollection units, StructureCollection structures, Treasury treasury)
    {
        Unit queen = units.getFriendlyQueen();
        List<Structure> goldMines = getAllowedGoldMines(structures);
        Structure closestGoldMine = geometryCalculator.findClosestObject(queen, goldMines);

        if (closestGoldMine == null) {
            return StrategyResult.none();
        } else {
            return StrategyResult.build(getCoefficient(structures), closestGoldMine, StructureType.GOLDMINE);
        }
    }

    private StrategyCoefficient getCoefficient(StructureCollection structures)
    {
        int income = structures.getFriendlyByType(StructureType.GOLDMINE)
                .stream()
                .map(Structure::getGoldIncomeRating)
                .reduce(0, Integer::sum);

        return income >= thresholdValue ? StrategyCoefficient.LOW : StrategyCoefficient.MEDIUM;
    }

    private List<Structure> getAllowedGoldMines(StructureCollection structures)
    {
        List<Structure> mines = structures.getNeutral();
        List<Structure> friendlyGoldMines = structures.getFriendlyByType(StructureType.GOLDMINE)
                .stream()
                .filter(mine -> !mine.isMaxIncomeReached())
                .collect(Collectors.toList());
        mines.addAll(friendlyGoldMines);

        return mines;
    }
}

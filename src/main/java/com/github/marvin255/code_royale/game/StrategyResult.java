package com.github.marvin255.code_royale.game;

import com.github.marvin255.code_royale.game_object.Structure;
import com.github.marvin255.code_royale.game_object.StructureType;
import com.github.marvin255.code_royale.map.MapObject;
import com.github.marvin255.code_royale.map.Point;

import java.util.List;
import java.util.stream.Collectors;

public class StrategyResult {
    private final StrategyCoefficient coefficient;
    private final String move;

    public StrategyResult(StrategyCoefficient coefficient, String move)
    {
        this.coefficient = coefficient;
        this.move = move;
    }

    public static StrategyResult none()
    {
        return new StrategyResult(StrategyCoefficient.LOWEST, "WAIT");
    }

    public static StrategyResult noneTrain()
    {
        return new StrategyResult(StrategyCoefficient.LOWEST, "TRAIN");
    }

    public static StrategyResult move(StrategyCoefficient coefficient, MapObject object)
    {
        return StrategyResult.move(coefficient, object.getPoint());
    }

    public static StrategyResult move(StrategyCoefficient coefficient, Point p)
    {
        return new StrategyResult(coefficient, "MOVE " + p.getX() + " " + p.getY());
    }

    public static StrategyResult build(StrategyCoefficient coefficient, Structure structure, StructureType type)
    {
        return new StrategyResult(coefficient, "BUILD " + structure.getId() + " " + type.getBuildCommand());
    }

    public static StrategyResult train(StrategyCoefficient coefficient, List<Structure> structures)
    {
        String structuresList = structures.stream()
                .map(structure -> String.valueOf(structure.getId()))
                .collect(Collectors.joining(" "));

        return new StrategyResult(coefficient, "TRAIN " + structuresList);
    }

    public StrategyCoefficient getCoefficient()
    {
        return coefficient;
    }

    public String getMove()
    {
        return move;
    }
}

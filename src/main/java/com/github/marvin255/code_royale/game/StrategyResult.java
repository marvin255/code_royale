package com.github.marvin255.code_royale.game;

import com.github.marvin255.code_royale.map.MapObject;
import com.github.marvin255.code_royale.map.Point;

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

    public static StrategyResult move(StrategyCoefficient coefficient, MapObject object)
    {
        return StrategyResult.move(coefficient, object.getPoint());
    }

    public static StrategyResult move(StrategyCoefficient coefficient, Point p)
    {
        return new StrategyResult(coefficient, "MOVE " + p.getX() + " " + p.getY());
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

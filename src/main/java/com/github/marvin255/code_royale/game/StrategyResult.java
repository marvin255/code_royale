package com.github.marvin255.code_royale.game;

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

    public static StrategyResult move(StrategyCoefficient coefficient, int x, int y)
    {
        return new StrategyResult(coefficient, "MOVE " + x + " " + y);
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

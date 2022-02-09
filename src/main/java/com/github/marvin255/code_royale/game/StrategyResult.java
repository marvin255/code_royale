package com.github.marvin255.code_royale.game;

public class StrategyResult {
    private final StrategyCoefficient coefficient;
    private final String move;

    StrategyResult(StrategyCoefficient coefficient, String move)
    {
        this.coefficient = coefficient;
        this.move = move;
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

package com.github.marvin255.code_royale.game;

public enum StrategyCoefficient {
    CRITICAL(10000),
    HIGHEST(1000),
    HIGHT(800),
    MEDIUM(600),
    LOW(400),
    LOWEST(200);

    private final int weight;

    StrategyCoefficient(int weight)
    {
        this.weight = weight;
    }

    public int getWeight()
    {
        return weight;
    }
}

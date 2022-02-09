package com.github.marvin255.code_royale.game_object;

public enum UnitType {
    KNIGHT(80, 4, 100, 0, 25, 5, 20),
    ARCHER(100, 2, 75, 200, 45, 8, 25),
    GIANT(140, 1, 50, 0, 200, 10, 40),
    QUEEN(0, 1, 60, 0, 200, 0, 30);

    private final int cost;
    private final int number;
    private final int speed;
    private final int range;
    private final int hp;
    private final int trainingTime;
    private final int radius;

    public static UnitType convertInputToEnum(int input)
    {
        switch (input) {
            case -1:
                return QUEEN;
            case 0:
                return KNIGHT;
            case 1:
                return ARCHER;
            case 2:
                return GIANT;
            default:
                throw new RuntimeException();
        }
    }

    UnitType(int cost, int number, int speed, int range, int hp, int trainingTime, int radius)
    {
        this.cost = cost;
        this.number = number;
        this.speed = speed;
        this.range = range;
        this.hp = hp;
        this.trainingTime = trainingTime;
        this.radius = radius;
    }

    public int getCost()
    {
        return cost;
    }

    public int getNumber()
    {
        return number;
    }

    public int getSpeed()
    {
        return speed;
    }

    public int getRange()
    {
        return range;
    }

    public int getHp()
    {
        return hp;
    }

    public int getTrainingTime()
    {
        return trainingTime;
    }

    public int getRadius()
    {
        return radius;
    }
}
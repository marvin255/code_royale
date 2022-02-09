package com.github.marvin255.code_royale.game_object;

import com.github.marvin255.code_royale.map.Point;

public class Unit extends GameObject {
    private final UnitType type;
    private final int HP;

    protected Unit(Point point, int radius, Owner owner, UnitType type, int HP)
    {
        super(point, radius, owner);
        this.type = type;
        this.HP = HP;
    }

    public UnitType getType()
    {
        return type;
    }

    public int getHP()
    {
        return HP;
    }

    @Override
    public String toString()
    {
        return "Unit " + getPoint();
    }
}

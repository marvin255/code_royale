package com.github.marvin255.code_royale.game;

import com.github.marvin255.code_royale.game_object.UnitType;

public class Treasury {
    private int gold;

    public int getGold()
    {
        return gold;
    }

    public void setGold(int gold)
    {
        this.gold = gold;
    }

    public boolean canBuy(UnitType unit)
    {
        return this.canBuy(unit, 1);
    }

    public boolean canBuy(UnitType unit, int quantity)
    {
        return unit.getCost() * quantity <= gold;
    }

    public boolean buy(UnitType unit)
    {
        if (canBuy(unit)) {
            gold -= unit.getCost();
            return true;
        }
        return false;
    }
}

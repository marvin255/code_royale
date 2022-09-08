package com.github.marvin255.code_royale.game_object;

import com.github.marvin255.code_royale.map.Point;

public class UnitFactory {
    public static Unit create(Point point, int owner, int unitType, int health)
    {
        Owner ownerEnum = Owner.convertInputToEnum(owner);
        UnitType unitTypeEnum = UnitType.convertInputToEnum(unitType);
        return new Unit(point, unitTypeEnum.getRadius(), ownerEnum, unitTypeEnum, health);
    }
}

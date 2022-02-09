package com.github.marvin255.code_royale.game_object;

import com.github.marvin255.code_royale.map.Point;

public class Structure extends GameObject {
    private final int id;
    private StructureType type;
    private int param1;
    private int param2;

    protected Structure(Point point, int radius, int id)
    {
        super(point, radius, Owner.NO_OWNER);
        this.id = id;
        this.type = StructureType.NO_STRUCTURE;
    }

    public int getId()
    {
        return id;
    }

    public void setType(StructureType type)
    {
        this.type = type;
    }

    public StructureType getType()
    {
        return type;
    }

    public void setParam1(int param1)
    {
        this.param1 = param1;
    }

    public void setParam2(int param2)
    {
        this.param2 = param2;
    }

    public int getAttackRadius()
    {
        if (type == StructureType.TOWER) {
            return param2;
        }
        return -1;
    }

    public int getHP()
    {
        if (type == StructureType.TOWER) {
            return param1;
        }
        return -1;
    }

    public int getGoldIncomeRating()
    {
        if (type == StructureType.GOLDMINE) {
            return param1;
        }
        return -1;
    }

    public int getTimeToComplete()
    {
        if (type == StructureType.BARRACKS_ARCHER || type == StructureType.BARRACKS_GIANT || type == StructureType.BARRACKS_KNIGHT) {
            return param1;
        }
        return -1;
    }

    @Override
    public String toString()
    {
        return "Structure " + getPoint();
    }
}

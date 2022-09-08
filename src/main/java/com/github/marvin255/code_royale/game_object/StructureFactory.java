package com.github.marvin255.code_royale.game_object;

import com.github.marvin255.code_royale.map.Point;

public class StructureFactory {
    public static Structure create(int siteId, Point point, int radius)
    {
        return new Structure(point, radius, siteId);
    }
}

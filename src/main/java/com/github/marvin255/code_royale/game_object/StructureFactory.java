package com.github.marvin255.code_royale.game_object;

import com.github.marvin255.code_royale.map.Point;

public class StructureFactory {
    public static Structure create(int siteId, int x, int y, int radius)
    {
        Point point = new Point(x, y);
        return new Structure(point, radius, siteId);
    }
}

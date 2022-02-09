package com.github.marvin255.code_royale.game_object;

import com.github.marvin255.code_royale.map.Point;
import com.github.marvin255.code_royale.map.MapObject;

abstract public class GameObject implements MapObject {
    private final Point point;
    private final int radius;
    private Owner owner;

    GameObject (Point point, int radius, Owner owner) {
        this.point = point;
        this.radius = radius;
        this.owner = owner;
    }

    @Override
    public Point getPoint() {
        return point;
    }

    @Override
    public int getRadius() {
        return radius;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }
}

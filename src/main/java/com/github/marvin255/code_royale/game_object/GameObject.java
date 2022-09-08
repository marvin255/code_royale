package com.github.marvin255.code_royale.game_object;

import com.github.marvin255.code_royale.map.Circle;
import com.github.marvin255.code_royale.map.Point;

public abstract class GameObject extends Circle {
    private Owner owner;

    GameObject (Point point, int radius, Owner owner) {
        super(point, radius);
        this.owner = owner;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }
}

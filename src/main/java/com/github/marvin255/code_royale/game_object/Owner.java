package com.github.marvin255.code_royale.game_object;

public enum Owner {
    NO_OWNER,
    FRIENDLY,
    ENEMY;

    public static Owner convertInputToEnum(int input)
    {
        switch (input) {
            case 0:
                return FRIENDLY;
            case 1:
                return ENEMY;
            default:
                return NO_OWNER;
        }
    }
}

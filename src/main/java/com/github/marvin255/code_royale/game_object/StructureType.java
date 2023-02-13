package com.github.marvin255.code_royale.game_object;

public enum StructureType {
    NO_STRUCTURE(""),
    GOLDMINE("MINE"),
    TOWER("TOWER"),
    BARRACKS_KNIGHT("BARRACKS-KNIGHT"),
    BARRACKS_ARCHER("BARRACKS-ARCHER"),
    BARRACKS_GIANT("BARRACKS-GIANT");

    private final String buildCommand;

    StructureType(String buildCommand)
    {
        this.buildCommand = buildCommand;
    }

    public static StructureType convertInputToEnum(int structureType, int param2)
    {
        if (structureType == -1) {
            return NO_STRUCTURE;
        } else if (structureType == 0) {
            return GOLDMINE;
        } else if (structureType == 1) {
            return TOWER;
        } else if (structureType == 2 && param2 == 0) {
            return BARRACKS_KNIGHT;
        } else if (structureType == 2 && param2 == 1) {
            return BARRACKS_ARCHER;
        } else if (structureType == 2 && param2 == 2) {
            return BARRACKS_GIANT;
        } else {
            throw new RuntimeException();
        }
    }

    public String getBuildCommand()
    {
        return buildCommand;
    }
}
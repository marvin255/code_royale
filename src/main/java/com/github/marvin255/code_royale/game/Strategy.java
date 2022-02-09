package com.github.marvin255.code_royale.game;

import com.github.marvin255.code_royale.game_object.StructureCollection;
import com.github.marvin255.code_royale.game_object.UnitCollection;

public interface Strategy {
    StrategyResult run(UnitCollection units, StructureCollection structures, Treasury treasury);
}

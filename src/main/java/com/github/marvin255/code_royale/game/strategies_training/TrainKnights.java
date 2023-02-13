package com.github.marvin255.code_royale.game.strategies_training;

import com.github.marvin255.code_royale.game.Strategy;
import com.github.marvin255.code_royale.game.StrategyCoefficient;
import com.github.marvin255.code_royale.game.StrategyResult;
import com.github.marvin255.code_royale.game.Treasury;
import com.github.marvin255.code_royale.game_object.*;

import java.util.List;

public class TrainKnights implements Strategy {
    public StrategyResult run(UnitCollection units, StructureCollection structures, Treasury treasury)
    {
        List<Structure> barracks = structures.getFriendlyByType(StructureType.BARRACKS_KNIGHT);
        boolean canTrain = barracks.size() * UnitType.KNIGHT.getCost() <= treasury.getGold();

        if (canTrain) {
            return StrategyResult.train(StrategyCoefficient.HIGHT, barracks);
        } else {
            return StrategyResult.noneTrain();
        }
    }
}

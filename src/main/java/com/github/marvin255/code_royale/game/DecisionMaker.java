package com.github.marvin255.code_royale.game;

import com.github.marvin255.code_royale.game_object.StructureCollection;
import com.github.marvin255.code_royale.game_object.UnitCollection;
import java.util.*;

public class DecisionMaker {
    private final List<Strategy> strategies;

    public DecisionMaker(List<Strategy> strategies)
    {
        this.strategies = strategies;
    }

    public String makeDecision(UnitCollection units, StructureCollection structures, Treasury treasury)
    {
        Optional<StrategyResult> decision = strategies
                .stream()
                .map(strategy -> strategy.run(units, structures, treasury))
                .max(Comparator.comparing(result -> result.getCoefficient().getWeight()));

        return decision.isPresent() ? decision.get().getMove() : "";
    }
}

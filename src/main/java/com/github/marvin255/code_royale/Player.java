package com.github.marvin255.code_royale;

import com.github.marvin255.code_royale.game.DecisionMaker;
import com.github.marvin255.code_royale.game.PathConstructor;
import com.github.marvin255.code_royale.game.Treasury;
import com.github.marvin255.code_royale.game.strategies_queen.BuildBarracksKnight;
import com.github.marvin255.code_royale.game.strategies_queen.IncreaseGoldInput;
import com.github.marvin255.code_royale.game.strategies_queen.RunawayIfDanger;
import com.github.marvin255.code_royale.game.strategies_training.TrainKnights;
import com.github.marvin255.code_royale.game_object.*;
import com.github.marvin255.code_royale.map.GeometryCalculator;
import com.github.marvin255.code_royale.map.GameMap;
import com.github.marvin255.code_royale.map.Point;

import java.util.Scanner;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Player {
    public static void main(String[] args) {
        final GameMap gameMap = new GameMap(1920, 1000);
        final GeometryCalculator geometryCalculator = new GeometryCalculator();
        final PathConstructor pathConstructor = new PathConstructor(gameMap, geometryCalculator);

        final DecisionMaker queenDecisionMaker = new DecisionMaker(
                new RunawayIfDanger(geometryCalculator, pathConstructor),
                new BuildBarracksKnight(geometryCalculator),
                new IncreaseGoldInput(geometryCalculator)
        );

        final DecisionMaker trainingDecisionMaker = new DecisionMaker(
                new TrainKnights()
        );

        final Treasury treasury = new Treasury();
        final StructureCollection structures = new StructureCollection();
        final UnitCollection units = new UnitCollection();

        Scanner in = new Scanner(System.in);
        int numSites = in.nextInt();
        for (int i = 0; i < numSites; i++) {
            int siteId = in.nextInt();
            int x = in.nextInt();
            int y = in.nextInt();
            int radius = in.nextInt();
            Point point = gameMap.createPoint(x, y);
            structures.add(StructureFactory.create(siteId, point, radius));
        }

        // game loop
        while (true) {
            int gold = in.nextInt();
            treasury.setGold(gold);

            int touchedSite = in.nextInt(); // -1 if none

            structures.clearMapped();
            for (int i = 0; i < numSites; i++) {
                int structureId = in.nextInt();
                int ignore1 = in.nextInt(); // used in future leagues
                int ignore2 = in.nextInt(); // used in future leagues
                int structureType = in.nextInt(); // -1 = No structure, 2 = Barracks
                int owner = in.nextInt(); // -1 = No structure, 0 = Friendly, 1 = Enemy
                int param1 = in.nextInt();
                int param2 = in.nextInt();
                Structure structure = structures.getById(structureId);
                structure.setType(StructureType.convertInputToEnum(structureType, param2));
                structure.setOwner(Owner.convertInputToEnum(owner));
                structure.setParam1(param1);
                structure.setParam2(param2);
            }

            units.clear();
            int numUnits = in.nextInt();
            for (int i = 0; i < numUnits; i++) {
                int x = in.nextInt();
                int y = in.nextInt();
                int owner = in.nextInt();
                int unitType = in.nextInt(); // -1 = QUEEN, 0 = KNIGHT, 1 = ARCHER
                int health = in.nextInt();
                Point point = gameMap.createPoint(x, y);
                units.add(UnitFactory.create(point, owner, unitType, health));
            }

            // Write an action using System.out.println()
            // To debug: System.err.println("Debug messages...");
            // First line: A valid queen action
            // Second line: A set of training instructions
            System.out.println(queenDecisionMaker.makeDecision(units, structures, treasury));
            System.out.println(trainingDecisionMaker.makeDecision(units, structures, treasury));
        }
    }
}

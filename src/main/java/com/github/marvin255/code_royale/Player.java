package com.github.marvin255.code_royale;

import com.github.marvin255.code_royale.map.Map;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Player {
    public static void main(String[] args) {
        final Map map = new Map(1920, 1000);
        /*
        final Treasury treasury = new Treasury();
        final StructureList structures = new StructureList();
        final UnitList units = new UnitList();

        Scanner in = new Scanner(System.in);
        int numSites = in.nextInt();
        for (int i = 0; i < numSites; i++) {
            int siteId = in.nextInt();
            int x = in.nextInt();
            int y = in.nextInt();
            int radius = in.nextInt();
            structures.add(StructureFactory.create(siteId, x, y, radius));
        }*/

        // game loop
        while (true) {
            /*
            int gold = in.nextInt();
            treasury.setGold(gold);

            int touchedSite = in.nextInt(); // -1 if none

            structures.clearCalculations();
            for (int i = 0; i < numSites; i++) {
                int siteId = in.nextInt();
                int ignore1 = in.nextInt(); // used in future leagues
                int ignore2 = in.nextInt(); // used in future leagues
                int structureType = in.nextInt(); // -1 = No structure, 2 = Barracks
                int owner = in.nextInt(); // -1 = No structure, 0 = Friendly, 1 = Enemy
                int param1 = in.nextInt();
                int param2 = in.nextInt();
                Structure site = structures.byId(siteId);
                site.setType(StructureTypes.convertInput(structureType, param2));
                site.setOwner(Owners.convertInput(owner));
                site.setParam1(param1);
                site.setParam2(param2);
            }

            units.clear();
            int numUnits = in.nextInt();
            for (int i = 0; i < numUnits; i++) {
                int x = in.nextInt();
                int y = in.nextInt();
                int owner = in.nextInt();
                int unitType = in.nextInt(); // -1 = QUEEN, 0 = KNIGHT, 1 = ARCHER
                int health = in.nextInt();
                units.add(UnitFactory.create(x, y, owner, unitType, health));
            }*/

            // Write an action using System.out.println()
            // To debug: System.err.println("Debug messages...");
            String[] turn = new String[2];

            // First line: A valid queen action
            // Second line: A set of training instructions
            System.out.println(turn[0]);
            System.out.println(turn[1]);
        }
    }
}

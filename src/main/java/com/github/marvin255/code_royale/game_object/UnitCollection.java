package com.github.marvin255.code_royale.game_object;

import java.util.*;

public class UnitCollection extends ArrayList<Unit>  {
    private boolean isCollectionMapped = false;
    private final Map<Owner, Map<UnitType, List<Unit>>> mappedCollection = new HashMap<>();

    @Override
    public void clear()
    {
        super.clear();
        clearMapped();
    }

    public void clearMapped()
    {
        isCollectionMapped = false;
    }

    public Unit getFriendlyQueen()
    {
        return getListByOwnerAndType(Owner.FRIENDLY, UnitType.QUEEN).get(0);
    }

    public List<Unit> getFriendlyType(UnitType type)
    {
        return getListByOwnerAndType(Owner.FRIENDLY, type);
    }

    public List<Unit> getEnemyType(UnitType type)
    {
        return getListByOwnerAndType(Owner.ENEMY, type);
    }

    public List<Unit> getListByOwnerAndType(Owner owner, UnitType type)
    {
        mapCollection();

        Map<UnitType, List<Unit>> byOwner = mappedCollection.get(owner);
        if (byOwner != null) {
            List<Unit> byType = byOwner.get(type);
            if (byType != null) {
                return List.copyOf(byType);
            }
        }

        return List.of();
    }

    private void mapCollection()
    {
        if (isCollectionMapped) {
            return;
        }

        isCollectionMapped = true;
        mappedCollection.clear();

        for (Unit unit : this) {
            Map<UnitType, List<Unit>> ownersMap = mappedCollection.computeIfAbsent(
                    unit.getOwner(),
                    k -> new HashMap<>()
            );
            List<Unit> typeList = ownersMap.computeIfAbsent(
                    unit.getType(),
                    k -> new ArrayList<>()
            );
            typeList.add(unit);
        }
    }
}

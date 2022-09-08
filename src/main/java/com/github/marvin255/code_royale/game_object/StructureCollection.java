package com.github.marvin255.code_royale.game_object;

import java.util.*;

public class StructureCollection extends ArrayList<Structure> {
    private boolean isCollectionMapped = false;
    private final Map<Owner, Map<StructureType, List<Structure>>> mappedCollection = new HashMap<>();

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

    public Structure getById(int id)
    {
        Structure found = null;
        for (Structure item : this) {
            if (item.getId() == id) {
                found = item;
                break;
            }
        }
        return found;
    }

    public List<Structure> getNeutral()
    {
        return this.getListByOwnerAndType(Owner.NO_OWNER, StructureType.NO_STRUCTURE);
    }

    public List<Structure> getFriendlyByType(StructureType type)
    {
        return this.getListByOwnerAndType(Owner.FRIENDLY, type);
    }

    public List<Structure> getEnemyByType(StructureType type)
    {
        return this.getListByOwnerAndType(Owner.ENEMY, type);
    }

    private List<Structure> getListByOwnerAndType(Owner owner, StructureType type)
    {
        mapCollection();

        Map<StructureType, List<Structure>> byOwner = mappedCollection.get(owner);
        if (byOwner != null) {
            List<Structure> byType = byOwner.get(type);
            if (byType != null) {
                return byType;
            }
        }

        return new ArrayList<>();
    }

    private void mapCollection()
    {
        if (isCollectionMapped) {
            return;
        }

        isCollectionMapped = true;
        mappedCollection.clear();

        for (Structure structure : this) {
            Map<StructureType, List<Structure>> ownersMap = mappedCollection.computeIfAbsent(
                    structure.getOwner(),
                    k -> new HashMap<>()
            );
            List<Structure> typeList = ownersMap.computeIfAbsent(
                    structure.getType(),
                    k -> new ArrayList<>()
            );
            typeList.add(structure);
        }
    }
}

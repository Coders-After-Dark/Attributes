package codersafterdark.attributes.common.skills.magic.adaptation.keys;

import net.minecraft.entity.Entity;

public class EntityAdaptationLockKey extends AdaptationLockKey {
    protected EntityAdaptationLockKey(String entityID) {
        super(entityID);
    }

    public EntityAdaptationLockKey(Entity entity) {
        this(getEntityId(entity));
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj) && obj instanceof EntityAdaptationLockKey;
    }
}

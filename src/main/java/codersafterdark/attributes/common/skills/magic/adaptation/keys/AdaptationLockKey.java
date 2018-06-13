package codersafterdark.attributes.common.skills.magic.adaptation.keys;

import codersafterdark.reskillable.api.data.LockKey;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class AdaptationLockKey implements LockKey {
    private final String entityID;

    protected AdaptationLockKey(String entityID) {
        this.entityID = entityID == null ? "" : entityID;
    }

    protected static String getEntityId(Entity entity) {
        for (EntityEntry entry : ForgeRegistries.ENTITIES.getValuesCollection()) {
            if (entry.getEntityClass() == entity.getClass()) {
                return entry.getRegistryName() != null ? entry.getRegistryName().toString() : "no_registry_name";
            }
        }
        return "no_registry_name";
    }

    @Override
    public boolean equals(Object obj) {
        return obj == this || obj instanceof AdaptationLockKey && entityID.equals(((AdaptationLockKey) obj).entityID);
    }

    @Override
    public int hashCode() {
        return entityID.hashCode();
    }
}

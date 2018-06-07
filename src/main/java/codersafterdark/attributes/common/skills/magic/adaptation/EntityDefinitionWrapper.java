package codersafterdark.attributes.common.skills.magic.adaptation;

import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class EntityDefinitionWrapper {

    String EntityID;
    Entity entity;

    public EntityDefinitionWrapper(String entityId) {
        this.EntityID = entityId;
    }

    public EntityDefinitionWrapper(Entity entity) {
        this(getEntityID(entity));
    }

    private static String getEntityID(Entity entity) {
        for (EntityEntry entry : ForgeRegistries.ENTITIES.getValuesCollection()) {
            if (entry.getEntityClass() == entity.getClass()) {
                return entry.getRegistryName() != null ? entry.getRegistryName().toString() : "no_registry_name";
            }
        }
        return "no_registry_name";
    }

    private static Entity getEntity(String entityID, World world) {
        return ForgeRegistries.ENTITIES.getValue(new ResourceLocation(entityID)).newInstance(world);
    }
}

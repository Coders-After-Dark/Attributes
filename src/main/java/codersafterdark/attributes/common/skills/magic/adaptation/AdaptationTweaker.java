package codersafterdark.attributes.common.skills.magic.adaptation;

import crafttweaker.IAction;
import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.entity.IEntity;
import crafttweaker.api.entity.IEntityDefinition;
import crafttweaker.api.minecraft.CraftTweakerMC;
import crafttweaker.api.potions.IPotionEffect;
import net.minecraft.entity.Entity;
import net.minecraft.potion.PotionEffect;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenClass("mods.attributes.AdaptationTweaker")
@ZenRegister
public class AdaptationTweaker {

    @ZenMethod
    public static void addAdaptation(IEntityDefinition entityDefinition, IPotionEffect effect){

    }

    public static class addAdaptation implements IAction {
        IEntityDefinition definition;
        PotionEffect effect;

        addAdaptation(IEntityDefinition entityDefinition, IPotionEffect effect) {
            this.definition = entityDefinition;
            this.effect = CraftTweakerMC.getPotionEffect(effect);
        }

        @Override
        public void apply() {
            EntityDefinitionWrapper wrapper = new EntityDefinitionWrapper(definition.getId());

        }

        @Override
        public String describe() {
            return null;
        }
    }
}

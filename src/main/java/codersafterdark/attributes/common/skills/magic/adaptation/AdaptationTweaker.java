package codersafterdark.attributes.common.skills.magic.adaptation;

import codersafterdark.attributes.Attributes;
import codersafterdark.attributes.common.skills.magic.adaptation.keys.EntityAdaptationLockKey;
import crafttweaker.IAction;
import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.entity.IEntityDefinition;
import crafttweaker.api.minecraft.CraftTweakerMC;
import crafttweaker.api.potions.IPotionEffect;
import net.minecraft.potion.PotionEffect;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import java.util.ArrayList;
import java.util.List;

@ZenClass("mods.attributes.AdaptationTweaker")
@ZenRegister
public class AdaptationTweaker {
    @ZenMethod
    public static void addAdaptation(IEntityDefinition definition, IPotionEffect... effects){
        Attributes.LATE_ADDITIONS.add(new AddAdaptation(definition, effects));
    }

    public static class AddAdaptation implements IAction {
        private final IEntityDefinition definition;
        private final List<PotionEffect> effects = new ArrayList<>();

        private AddAdaptation(IEntityDefinition entityDefinition, IPotionEffect... effects) {
            this.definition = entityDefinition;
            for (IPotionEffect effect : effects) {
                this.effects.add(CraftTweakerMC.getPotionEffect(effect));
            }
        }

        @Override
        public void apply() {
            TraitAdaptation.addAdaption(new EntityAdaptationLockKey(definition.getId()), effects);
        }

        @Override
        public String describe() {
            return null;
        }
    }
}

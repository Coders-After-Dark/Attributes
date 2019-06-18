package codersafterdark.attributes.common.skills.magic.adaptation;

import codersafterdark.attributes.Attributes;
import codersafterdark.attributes.common.skills.magic.adaptation.keys.EntityAdaptationLockKey;
import crafttweaker.CraftTweakerAPI;
import crafttweaker.IAction;
import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.entity.IEntityDefinition;
import crafttweaker.api.minecraft.CraftTweakerMC;
import crafttweaker.api.potions.IPotionEffect;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@ZenClass("mods.attributes.AdaptationTweaker")
@ZenRegister
public class AdaptationTweaker {

    @ZenMethod
    public static void addAdaptation(IEntityDefinition definition, IPotionEffect... effects){
        Attributes.LATE_ADDITIONS.add(new AddAdaptation(definition, effects));
    }

    public static class AddAdaptation implements IAction {
        private final IEntityDefinition definition;
        private final IPotionEffect[] effects;

        private AddAdaptation(IEntityDefinition entityDefinition, IPotionEffect... effects) {
            this.definition = entityDefinition;
            this.effects = effects;
        }

        @Override
        public void apply() {
            final List<PotionEffect> trueEffects = new ArrayList<>();
            for (IPotionEffect effect : effects) {
                if (checkValidIPotionEffect(effect)) {
                    trueEffects.add(CraftTweakerMC.getPotionEffect(effect));
                }
            }
            if (checkValidIEntityDefinition(definition)) {
                TraitAdaptation.addAdaption(new EntityAdaptationLockKey(definition.getId()), trueEffects);
            }
        }

        @Override
        public String describe() {
            StringBuilder string = new StringBuilder(" With Effect(s): ");
            for (IPotionEffect effect : effects) {
                string.append(effect.getEffectName()).append(" With Duration: ").append(effect.getDuration()).append(" With Amplifier: ").append(effect.getAmplifier());
            }
            return "Added Adaptation for Entity: " + definition.getName() + string;
        }
    }

    private static boolean checkValidIEntityDefinition(IEntityDefinition entity) {
        if (entity == null) {
            CraftTweakerAPI.logError("IEntityDefinition was found to be null");
            return false;
        }
        return true;
    }

    private static boolean checkValidIPotionEffect(IPotionEffect effect) {
        Collection<Potion> potions = ForgeRegistries.POTIONS.getValuesCollection();
        if (effect == null) {
            CraftTweakerAPI.logError("IPotionEffect was found to be null");
        }

        for (Potion potion : potions) {
            if (potion != null) {
                if (Objects.equals(effect != null ? effect.getEffectName() : null, potion.getName())) {
                    return true;
                }
            }
        }

        CraftTweakerAPI.logError("No Valid Potion found for the provided Potion Effect");
        return false;
    }
}

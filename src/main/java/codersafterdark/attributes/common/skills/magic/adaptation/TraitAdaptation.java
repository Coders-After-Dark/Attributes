package codersafterdark.attributes.common.skills.magic.adaptation;

import codersafterdark.attributes.common.skills.magic.adaptation.keys.AdaptationLockKey;
import codersafterdark.attributes.common.skills.magic.adaptation.keys.EntityAdaptationLockKey;
import codersafterdark.attributes.utils.AttributesConstants;
import codersafterdark.reskillable.api.data.PlayerData;
import codersafterdark.reskillable.api.data.PlayerDataHandler;
import codersafterdark.reskillable.api.data.PlayerSkillInfo;
import codersafterdark.reskillable.api.unlockable.Trait;
import codersafterdark.reskillable.lib.LibMisc;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TraitAdaptation extends Trait {
    private static final Map<AdaptationLockKey, List<PotionEffect>> adaptation = new HashMap<>();

    public TraitAdaptation() {
        super(new ResourceLocation(AttributesConstants.MODID, "adaptation"), 0, 3, new ResourceLocation(LibMisc.MOD_ID, "magic"), 0, "");
    }

    @Override
    public void onAttackMob(LivingHurtEvent event) {
        if (event.isCanceled() || !(event.getSource().getTrueSource() instanceof EntityPlayer)) {
            return;
        }

        if (event.getAmount() > 0) {
            AdaptationLockKey adaptionKey = new EntityAdaptationLockKey(event.getEntityLiving());
            if (adaptation.containsKey(adaptionKey)) {
                EntityPlayer player = (EntityPlayer) event.getSource().getTrueSource();
                PlayerData data = PlayerDataHandler.get(player);
                PlayerSkillInfo info = data.getSkillInfo(getParentSkill());
                if (info.isUnlocked(this)) {
                    adaptation.get(adaptionKey).forEach(player::addPotionEffect);
                }
            }
        }
    }

    public static void addAdaption(AdaptationLockKey key, List<PotionEffect> effects) {
        adaptation.computeIfAbsent(key, k -> new ArrayList<>()).addAll(effects);
    }
}

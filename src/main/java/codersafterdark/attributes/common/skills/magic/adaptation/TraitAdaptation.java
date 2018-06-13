package codersafterdark.attributes.common.skills.magic.adaptation;

import codersafterdark.attributes.common.skills.magic.adaptation.keys.AdaptationLockKey;
import codersafterdark.attributes.common.skills.magic.adaptation.keys.EntityAdaptationLockKey;
import codersafterdark.attributes.utils.AttributesConstants;
import codersafterdark.reskillable.api.data.PlayerData;
import codersafterdark.reskillable.api.data.PlayerDataHandler;
import codersafterdark.reskillable.api.data.PlayerSkillInfo;
import codersafterdark.reskillable.api.unlockable.Trait;
import codersafterdark.reskillable.lib.LibMisc;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityShulker;
import net.minecraft.entity.monster.EntityWitherSkeleton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.HashMap;
import java.util.Map;

public class TraitAdaptation extends Trait {

    public static final Map<AdaptationLockKey, PotionEffect> adaptation = new HashMap<>();

    public TraitAdaptation() {
        super(new ResourceLocation(AttributesConstants.MODID, "adaptation"), 0, 3, new ResourceLocation(LibMisc.MOD_ID, "magic"), 0, "");
    }

    @SubscribeEvent
    public void onAdaptation(LivingHurtEvent event) {
        if (event.isCanceled() || !(event.getSource().getTrueSource() instanceof EntityPlayer)) {
            return;
        }

        if (event.getAmount() > 0) {
            if (adaptation.containsKey(event.getEntityLiving().getClass())){
                EntityPlayer player = (EntityPlayer) event.getSource().getTrueSource();
                EntityLivingBase livingBase = event.getEntityLiving();
                PlayerData data = PlayerDataHandler.get(player);
                PlayerSkillInfo info = data.getSkillInfo(getParentSkill());
                if (info.isUnlocked(this)) {
                    player.addPotionEffect(adaptation.get(livingBase.getClass()));
                }
            }
        }
    }


}

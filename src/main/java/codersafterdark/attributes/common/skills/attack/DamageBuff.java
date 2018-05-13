package codersafterdark.attributes.common.skills.attack;

import codersafterdark.attributes.utils.AttributesConfigHandler.AttributesConfigs;
import codersafterdark.attributes.utils.AttributesConstants;
import codersafterdark.reskillable.api.data.PlayerData;
import codersafterdark.reskillable.api.data.PlayerDataHandler;
import codersafterdark.reskillable.api.data.PlayerSkillInfo;
import codersafterdark.reskillable.api.unlockable.Trait;
import codersafterdark.reskillable.lib.LibMisc;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class DamageBuff extends Trait {
    public DamageBuff() {
        super(new ResourceLocation(AttributesConstants.MODID, "damagebuff"), 4, 2, new ResourceLocation(LibMisc.MOD_ID, "attack"), 0, "");
    }

    @SubscribeEvent
    public void addDamage(LivingHurtEvent event) {
        float baseDamage = event.getAmount();
        EntityPlayer player = (EntityPlayer) event.getSource().getTrueSource();
        PlayerData data = null;
        if (player != null) {
            data = PlayerDataHandler.get(player);
        }
        if (data != null) {
            PlayerSkillInfo info = data.getSkillInfo(getParentSkill());
            for (int i = 0; i < info.getLevel(); i++) {
                baseDamage += AttributesConfigs.Attack.dmgBuff;
            }
        }
        event.setAmount(baseDamage);
    }
}

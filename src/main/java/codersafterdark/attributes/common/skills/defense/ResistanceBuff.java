package codersafterdark.attributes.common.skills.defense;

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

public class ResistanceBuff extends Trait {
    public ResistanceBuff() {
        super(new ResourceLocation(AttributesConstants.MODID, "resistancebuff"), 4, 2, new ResourceLocation(LibMisc.MOD_ID, "defense"), 0, "");
    }

    @SubscribeEvent
    public void resistDamage(LivingHurtEvent event) {
        float baseDamage = event.getAmount();
        if (event.getEntityLiving() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) event.getEntityLiving();
            PlayerData data = PlayerDataHandler.get(player);
            PlayerSkillInfo info = data.getSkillInfo(getParentSkill());
            for (int i = 0; i < info.getLevel(); i++) {
                baseDamage -= AttributesConfigs.Defense.resBuff;
            }
            event.setAmount(baseDamage);
        }
    }
}
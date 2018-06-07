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

public class TraitResistance extends Trait {
    public TraitResistance() {
        super(new ResourceLocation(AttributesConstants.MODID, "resistancebuff"), 0, 3, new ResourceLocation(LibMisc.MOD_ID, "defense"), 0, "");
    }

    @SubscribeEvent
    public void resistDamage(LivingHurtEvent event) {
        if (event.isCanceled() || !(event.getEntityLiving() instanceof EntityPlayer)){
            return;
        }

        float baseDamage = event.getAmount();
        EntityPlayer player = (EntityPlayer) event.getEntityLiving();
        PlayerData data = PlayerDataHandler.get(player);
        PlayerSkillInfo info = data.getSkillInfo(getParentSkill());
        for (int i = 0; i < info.getLevel(); i++) {
            baseDamage -= AttributesConfigs.Defense.resBuff;
        }
        event.setAmount(baseDamage);
    }
}

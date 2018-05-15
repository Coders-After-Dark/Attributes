package codersafterdark.attributes.common.skills.mining;

import codersafterdark.attributes.utils.AttributesConfigHandler.AttributesConfigs;
import codersafterdark.attributes.utils.AttributesConstants;
import codersafterdark.reskillable.api.data.PlayerData;
import codersafterdark.reskillable.api.data.PlayerDataHandler;
import codersafterdark.reskillable.api.data.PlayerSkillInfo;
import codersafterdark.reskillable.api.unlockable.Trait;
import codersafterdark.reskillable.lib.LibMisc;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class MiningBuff extends Trait {
    public MiningBuff() {
        super(new ResourceLocation(AttributesConstants.MODID, "miningbuff"), 0, 3, new ResourceLocation(LibMisc.MOD_ID, "mining"), 0, "");
    }

    @SubscribeEvent
    public void onMining(PlayerEvent.BreakSpeed event) {
        float editedSpeed = event.getNewSpeed();
        EntityPlayer player = event.getEntityPlayer();
        PlayerData data = PlayerDataHandler.get(player);
        PlayerSkillInfo info = data.getSkillInfo(getParentSkill());
        for (int i = 0; i < info.getLevel(); i++) {
            editedSpeed += AttributesConfigs.Mining.speedBuff;
        }
        event.setNewSpeed(editedSpeed);
    }
}
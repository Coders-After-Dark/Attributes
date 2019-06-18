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
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.event.entity.player.PlayerEvent.BreakSpeed;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class TraitMining extends Trait {
    public int level = 1;

    public TraitMining() {
        super(new ResourceLocation(AttributesConstants.MODID, "miningbuff"), 0, 3, new ResourceLocation(LibMisc.MOD_ID, "mining"), 0, "");
    }

    @Override
    public void getBreakSpeed(BreakSpeed event) {
        float editedSpeed = event.getNewSpeed();
        EntityPlayer player = event.getEntityPlayer();
        PlayerData data = PlayerDataHandler.get(player);
        PlayerSkillInfo info = data.getSkillInfo(getParentSkill());
        editedSpeed += AttributesConfigs.Mining.speedBuff * info.getLevel();
        event.setNewSpeed(editedSpeed);
    }

    @Override
    public String getDescription() {
        StringBuilder builder = new StringBuilder(new TextComponentTranslation("reskillable.unlock." + getKey() + ".desc").getUnformattedComponentText());
        builder.append(" Mining Speed Gained: ").append(AttributesConfigs.Mining.speedBuff * level);
        return builder.toString();
    }
}
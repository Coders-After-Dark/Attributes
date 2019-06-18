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
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class TraitResistance extends Trait {
    public int level = 1;

    public TraitResistance() {
        super(new ResourceLocation(AttributesConstants.MODID, "resistancebuff"), 0, 3, new ResourceLocation(LibMisc.MOD_ID, "defense"), 0, "");
    }

    @Override
    public void onHurt(LivingHurtEvent event) {
        if (event.isCanceled() || !(event.getEntityLiving() instanceof EntityPlayer)){
            return;
        }

        float baseDamage = event.getAmount();
        EntityPlayer player = (EntityPlayer) event.getEntityLiving();
        PlayerData data = PlayerDataHandler.get(player);
        PlayerSkillInfo info = data.getSkillInfo(getParentSkill());
        baseDamage -= AttributesConfigs.Defense.resBuff * info.getLevel();
        event.setAmount(baseDamage);
    }

    @Override
    public String getDescription() {
        StringBuilder builder = new StringBuilder(new TextComponentTranslation("reskillable.unlock." + getKey() + ".desc").getUnformattedComponentText());
        builder.append(" Damage Reduction Gained: ").append(AttributesConfigs.Defense.resBuff * level);
        return builder.toString();
    }

}

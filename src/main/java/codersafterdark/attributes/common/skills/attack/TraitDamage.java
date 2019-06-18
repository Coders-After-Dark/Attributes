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
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class TraitDamage extends Trait {
    public int level = 1;

    public TraitDamage() {
        super(new ResourceLocation(AttributesConstants.MODID, "damagebuff"), 0, 3, new ResourceLocation(LibMisc.MOD_ID, "attack"), 0, "");
    }

    @Override
    public void onAttackMob(LivingHurtEvent event) {
        if (event.isCanceled() || !(event.getSource().getTrueSource() instanceof EntityPlayer)){
            return;
        }

        float baseDamage = event.getAmount();
        EntityPlayer player = (EntityPlayer) event.getSource().getTrueSource();
        PlayerData data = PlayerDataHandler.get(player);
        PlayerSkillInfo info = data.getSkillInfo(getParentSkill());
        baseDamage += AttributesConfigs.Attack.dmgBuff * info.getLevel();
        event.setAmount(baseDamage);
    }

    @Override
    public String getDescription() {
        StringBuilder builder = new StringBuilder(new TextComponentTranslation("reskillable.unlock." + getKey() + ".desc").getUnformattedComponentText());
        builder.append(" Damage Addition Gained: ").append(AttributesConfigs.Attack.dmgBuff * level);
        return builder.toString();
    }
}

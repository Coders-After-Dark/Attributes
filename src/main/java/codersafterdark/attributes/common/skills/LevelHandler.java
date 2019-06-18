package codersafterdark.attributes.common.skills;

import codersafterdark.attributes.common.skills.attack.TraitDamage;
import codersafterdark.attributes.common.skills.defense.TraitResistance;
import codersafterdark.attributes.common.skills.mining.TraitMining;
import codersafterdark.reskillable.api.event.LevelUpEvent;
import codersafterdark.reskillable.api.unlockable.Unlockable;
import codersafterdark.reskillable.skill.SkillAttack;
import codersafterdark.reskillable.skill.SkillDefense;
import codersafterdark.reskillable.skill.SkillMining;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.List;

public class LevelHandler {
    @SubscribeEvent
    public void levelChangeEvent(LevelUpEvent.Post event) {
        List<Unlockable> unlockables = event.getSkill().getUnlockables();
        for (Unlockable unlockable : unlockables) {
            if (event.getSkill() instanceof SkillAttack && unlockable instanceof TraitDamage) {
                TraitDamage damage = (TraitDamage) unlockable;
                damage.level = event.getLevel();
            } else if (event.getSkill() instanceof SkillDefense && unlockable instanceof TraitResistance) {
                TraitResistance resistance = (TraitResistance) unlockable;
                resistance.level = event.getLevel();
            } else if (event.getSkill() instanceof SkillMining && unlockable instanceof TraitMining){
                TraitMining mining = (TraitMining) unlockable;
                mining.level = event.getLevel();
            }
        }
    }
}

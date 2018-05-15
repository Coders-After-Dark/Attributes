package codersafterdark.attributes.common.skills.mining.fortune;

import codersafterdark.attributes.utils.AttributesConstants;
import codersafterdark.reskillable.api.data.PlayerData;
import codersafterdark.reskillable.api.data.PlayerDataHandler;
import codersafterdark.reskillable.api.data.PlayerSkillInfo;
import codersafterdark.reskillable.api.unlockable.Trait;
import codersafterdark.reskillable.lib.LibMisc;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.HashMap;
import java.util.Map;

import static codersafterdark.attributes.utils.AttributesUtilMethod.nextIntInclusive;

public class FortuneBuff extends Trait {
    static final Map<Block, ItemStack> map = new HashMap<>();
    private static int fortuneLevel;

    static {
        map.put(Blocks.EMERALD_ORE, new ItemStack(Items.EMERALD, nextIntInclusive(1, 1 + fortuneLevel)));
        map.put(Blocks.LAPIS_ORE, new ItemStack(Items.DYE, nextIntInclusive(1, 1 + fortuneLevel), 4));
        map.put(Blocks.REDSTONE_ORE, new ItemStack(Items.REDSTONE, nextIntInclusive(1, 1 + fortuneLevel)));
        map.put(Blocks.LIT_REDSTONE_ORE, new ItemStack(Items.REDSTONE, nextIntInclusive(1, 1 + fortuneLevel)));
        map.put(Blocks.QUARTZ_ORE, new ItemStack(Items.QUARTZ, nextIntInclusive(1, 1 + fortuneLevel)));
    }

    public FortuneBuff() {
        super(new ResourceLocation(AttributesConstants.MODID, "fortunebuff"), 4, 3, new ResourceLocation(LibMisc.MOD_ID, "mining"), 0, "");
    }

    @SubscribeEvent
    public void onBlockBreak(BlockEvent.HarvestDropsEvent event) {
        Block block = event.getState().getBlock();
        EntityPlayer player = event.getHarvester();
        PlayerData data = PlayerDataHandler.get(player);
        PlayerSkillInfo info = data.getSkillInfo(getParentSkill());
        int value = Math.round(getParentSkill().getCap() / 3);

        if (info.getLevel() == info.skill.getCap()) {
            fortuneLevel = 3;
        } else if (info.getLevel() >= (value * 2)) {
            fortuneLevel = 2;
        } else if (info.getLevel() >= value) {
            fortuneLevel = 1;
        }

        if (map.containsKey(block)) {
            ItemStack stack = map.get(block);
            if (stack != null && !stack.isEmpty()) {
                event.getDrops().add(stack.copy());
            }
        }
    }
}
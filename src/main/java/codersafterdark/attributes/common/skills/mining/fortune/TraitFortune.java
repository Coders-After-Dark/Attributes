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
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;

import static codersafterdark.attributes.utils.AttributesUtilMethod.nextIntInclusive;

public class TraitFortune extends Trait {

    static List<ItemStack> blockList = new ArrayList<>();

    static {
        blockList.add(new ItemStack(Blocks.STONE));
        blockList.add(new ItemStack(Blocks.BROWN_MUSHROOM_BLOCK));
        blockList.add(new ItemStack(Blocks.RED_MUSHROOM_BLOCK));
        blockList.add(new ItemStack(Blocks.CHEST));
        blockList.add(new ItemStack(Blocks.GRASS));
        blockList.add(new ItemStack(Blocks.GRASS_PATH));
    }

    public TraitFortune() {
        super(new ResourceLocation(AttributesConstants.MODID, "fortunebuff"), 1, 3, new ResourceLocation(LibMisc.MOD_ID, "mining"), 0, "");
    }

    @SubscribeEvent
    public void onBlockBreak(BlockEvent.HarvestDropsEvent event) {
        if (event.isCanceled()) {
            return;
        }

        Block block = event.getState().getBlock();
        ItemStack blockStack = new ItemStack(block, 1, block.getMetaFromState(event.getState()));
        EntityPlayer player = event.getHarvester();
        PlayerData data = PlayerDataHandler.get(player);
        PlayerSkillInfo info = data.getSkillInfo(getParentSkill());

        int fortune = 0;
        int value = Math.round(getParentSkill().getCap() / 3);

        if (info.getLevel() == info.skill.getCap()) {
            fortune = 3;
        } else if (info.getLevel() >= (value * 2)) {
            fortune = 2;
        } else if (info.getLevel() >= value) {
            fortune = 1;
        }

        if (!blockList.contains(blockStack)){
            for (ItemStack stack : event.getDrops()){
                if (stack != blockStack) {
                    event.getDrops().remove(stack);
                    event.getDrops().add(new ItemStack(stack.getItem(), nextIntInclusive(stack.getCount(), stack.getCount() + fortune), stack.getMetadata()));
                }
            }
        }
    }
}
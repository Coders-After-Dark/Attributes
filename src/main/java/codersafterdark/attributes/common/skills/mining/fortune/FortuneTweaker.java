package codersafterdark.attributes.common.skills.mining.fortune;

import codersafterdark.attributes.Attributes;
import crafttweaker.CraftTweakerAPI;
import crafttweaker.IAction;
import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenClass("mods.attributes.FortuneTweaker")
@ZenRegister
public class FortuneTweaker {
    @ZenMethod
    public static void addFortuneTarget(IItemStack block, IItemStack stack) {
        if (block == null || block.isEmpty()) {
            CraftTweakerAPI.logError("IItemstack Block-Input was found to be Null or Empty");
        } else if (!(CraftTweakerMC.getItemStack(block).getItem() instanceof ItemBlock)) {
            CraftTweakerAPI.logError("Input Itemstack for 'Block' param is not a valid block");
        } else if (stack == null || block.isEmpty()) {
            CraftTweakerAPI.logError("IItemstack Item-Input was found to be Null or Empty");
        } else {
            Attributes.LATE_ADDITIONS.add(new addFortuneTarget(CraftTweakerMC.getItemStack(block), CraftTweakerMC.getItemStack(stack)));
        }
    }

    public static class addFortuneTarget implements IAction {
        Block block;
        ItemStack stack;

        public addFortuneTarget (ItemStack block, ItemStack stack) {
            this.block = Block.getBlockFromItem(block.getItem());
            this.stack = stack;
        }

        @Override
        public void apply() {
            FortuneBuff.map.put(block, stack);
        }

        @Override
        public String describe() {
            return "Added Block > Item Link for: " + block.getLocalizedName() + " > " + stack.getDisplayName();
        }
    }
}

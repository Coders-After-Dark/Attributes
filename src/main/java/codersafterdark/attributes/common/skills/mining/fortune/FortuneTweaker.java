package codersafterdark.attributes.common.skills.mining.fortune;

import codersafterdark.attributes.Attributes;
import crafttweaker.CraftTweakerAPI;
import crafttweaker.IAction;
import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenClass("mods.attributes.FortuneBlacklist")
@ZenRegister
public class FortuneTweaker {
    @ZenMethod
    public static void addEntry(IItemStack stack) {
        if (!(CraftTweakerMC.getItemStack(stack).getItem() instanceof ItemBlock)) {
            CraftTweakerAPI.logError("Input Itemstack for 'Block' param is not a valid block");
        } else {
            ItemStack stack1 = CraftTweakerMC.getItemStack(stack);
            Attributes.LATE_ADDITIONS.add(new addBlackListEntry(stack1));
        }
    }

    public static class addBlackListEntry implements IAction {
        ItemStack stack;

        addBlackListEntry(ItemStack stack) {
            this.stack = stack;
        }

        @Override
        public void apply() {
            TraitFortune.blockList.add(stack);
        }

        @Override
        public String describe() {
            return "Added Block Blacklist for Block: " + stack.getDisplayName();
        }
    }
}

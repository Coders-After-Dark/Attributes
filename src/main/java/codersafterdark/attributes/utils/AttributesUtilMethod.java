package codersafterdark.attributes.utils;

import codersafterdark.reskillable.base.ConfigHandler;
import codersafterdark.reskillable.base.LevelLockHandler;
import net.minecraft.entity.player.EntityPlayer;

public class AttributesUtilMethod {
    public static int nextIntInclusive(int min, int max) {
        return AttributesConstants.RANDOM.nextInt(max - min + 1) + min;
    }

    public static boolean skipPlayer(EntityPlayer player) {
        return player == null || !ConfigHandler.enforceOnCreative && player.isCreative() || !ConfigHandler.enforceFakePlayers && LevelLockHandler.isFake(player);
    }
}

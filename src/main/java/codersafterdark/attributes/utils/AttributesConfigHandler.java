package codersafterdark.attributes.utils;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;

public class AttributesConfigHandler {
    public static void postInit(FMLPostInitializationEvent event) {

    }

    @Config(modid = AttributesConstants.MODID)
    public static class AttributesConfigs {

        @Config(modid = AttributesConstants.MODID, category = "Attack")
        public static class Attack {
            @Config.Comment("Float Value for DMG/Level")
            public static float dmgBuff = 0.1f;
        }

        @Config(modid = AttributesConstants.MODID, category = "Defense")
        public static class Defense {
            @Config.Comment("Float Value for RES/Level")
            public static float resBuff = 0.1f;
        }

        @Config(modid = AttributesConstants.MODID, category = "Mining")
        public static class Mining {
            @Config.Comment("Float Value for Mining Speed/Level")
            public static float speedBuff = 0.1f;
        }
    }
}

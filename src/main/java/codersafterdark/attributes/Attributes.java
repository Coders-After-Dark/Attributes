package codersafterdark.attributes;

import codersafterdark.attributes.common.CommonProxy;
import codersafterdark.attributes.common.skills.attack.DamageBuff;
import codersafterdark.attributes.common.skills.defense.ResistanceBuff;
import codersafterdark.attributes.common.skills.mining.MiningBuff;
import codersafterdark.attributes.utils.AttributesConstants;
import codersafterdark.reskillable.api.ReskillableRegistries;
import codersafterdark.reskillable.api.unlockable.Unlockable;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = AttributesConstants.MODID, name = AttributesConstants.NAME, version = AttributesConstants.VERSION, dependencies = AttributesConstants.DEPENDENCIES, acceptedMinecraftVersions = AttributesConstants.MCVER)
public class Attributes {
    @SidedProxy(serverSide = AttributesConstants.PROXY_COMMON, clientSide = AttributesConstants.PROXY_CLIENT, modId = AttributesConstants.MODID)
    public static CommonProxy proxy;

    public static Logger logger;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        ReskillableRegistries.UNLOCKABLES.registerAll(
                new DamageBuff(),
                new ResistanceBuff(),
                new MiningBuff()
        );
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {

    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {

    }
}

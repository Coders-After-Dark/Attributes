package codersafterdark.attributes;

import codersafterdark.attributes.common.CommonProxy;
import codersafterdark.attributes.common.skills.LevelHandler;
import codersafterdark.attributes.common.skills.attack.TraitDamage;
import codersafterdark.attributes.common.skills.defense.TraitResistance;
import codersafterdark.attributes.common.skills.magic.adaptation.TraitAdaptation;
import codersafterdark.attributes.common.skills.mining.TraitMining;
import codersafterdark.attributes.utils.AttributesConstants;
import codersafterdark.reskillable.api.ReskillableRegistries;
import crafttweaker.CraftTweakerAPI;
import crafttweaker.IAction;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.logging.log4j.Logger;

import java.util.LinkedList;
import java.util.List;

@Mod(modid = AttributesConstants.MODID, name = AttributesConstants.NAME, version = AttributesConstants.VERSION, dependencies = AttributesConstants.DEPENDENCIES, acceptedMinecraftVersions = AttributesConstants.MCVER)
public class Attributes {
    public static List<IAction> LATE_ADDITIONS = new LinkedList<>();

    @SidedProxy(serverSide = AttributesConstants.PROXY_COMMON, clientSide = AttributesConstants.PROXY_CLIENT, modId = AttributesConstants.MODID)
    public static CommonProxy proxy;
    public static Logger logger;


    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        MinecraftForge.EVENT_BUS.register(new LevelHandler());
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        ReskillableRegistries.UNLOCKABLES.registerAll(
                new TraitDamage(),
                new TraitResistance(),
                new TraitMining(),
                new TraitAdaptation()
        );
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        if (Loader.isModLoaded("crafttweaker")) {
            LATE_ADDITIONS.forEach(CraftTweakerAPI::apply);
        }
    }
}

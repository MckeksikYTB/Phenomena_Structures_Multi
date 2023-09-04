package net.PS.phenomena;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//TODO disable datapack validation for Phenomena
//TODO disable experimental setting for Phenomena
//TODO make it only server side(only if possible), dont forget mods.toml
//TODO better loot for structures

@Mod(Phenomena.MODID)
public class Phenomena {

    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MODID = "phenomena";

    public Phenomena() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
    }
}

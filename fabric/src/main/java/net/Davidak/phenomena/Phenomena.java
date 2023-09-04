package net.Davidak.phenomena;

//import net.Davidak.phenomena.world.village.VillageAdditions;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Phenomena implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("phenomena");

	@Override
	public void onInitialize() {
		//VillageAdditions.registerNewVillageStructures();
	}
}

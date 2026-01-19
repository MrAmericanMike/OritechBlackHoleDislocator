package top.mramericanmike.blackholedislocator;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.mramericanmike.blackholedislocator.item.ModItems;

public class BlackHoleDislocator implements ModInitializer {
	public static final String MOD_ID = "blackholedislocator";
	public static final String MOD_NAME = "BlackHoleDislocator";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);

	@Override
	public void onInitialize() {
		ModItems.initialize();
		ModCreativeTab.registerCreativeTab();
	}
}
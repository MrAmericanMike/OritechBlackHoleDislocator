package top.mramericanmike.blackholedislocator;

import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;
import top.mramericanmike.blackholedislocator.item.ModItems;

@Mod(BlackHoleDislocator.MOD_ID)
public class BlackHoleDislocator {
    public static final String MOD_ID = "blackholedislocator";
    public static final Logger LOGGER = LogUtils.getLogger();

    public BlackHoleDislocator(IEventBus modEventBus, ModContainer modContainer) {
        ModItems.register(modEventBus);
        ModCreativeTab.register(modEventBus);
    }
}

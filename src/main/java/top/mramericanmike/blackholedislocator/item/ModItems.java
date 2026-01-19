package top.mramericanmike.blackholedislocator.item;

import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import top.mramericanmike.blackholedislocator.BlackHoleDislocator;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(BlackHoleDislocator.MOD_ID);

    public static final DeferredItem<Item> DISLOCATOR = ITEMS.register("dislocator",
            () -> new DislocatorItem(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}

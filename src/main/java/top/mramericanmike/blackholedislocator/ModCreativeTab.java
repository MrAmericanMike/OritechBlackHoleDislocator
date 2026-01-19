package top.mramericanmike.blackholedislocator;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import top.mramericanmike.blackholedislocator.item.ModItems;

public class ModCreativeTab {

    public static final ItemGroup MOD_CREATIVE_TAB = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(BlackHoleDislocator.MOD_ID, "blackholedislocator"),
            FabricItemGroup.builder().icon(
                            () -> new ItemStack(ModItems.DISLOCATOR))
                    .displayName(Text.translatable("itemGroup.blackholedislocator"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModItems.DISLOCATOR);
                    })
                    .build()
    );


    public static void registerCreativeTab() {
        BlackHoleDislocator.LOGGER.info("Registering Mod Creative Tab");
    }
}

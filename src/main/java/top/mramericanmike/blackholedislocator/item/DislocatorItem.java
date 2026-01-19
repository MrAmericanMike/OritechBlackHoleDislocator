package top.mramericanmike.blackholedislocator.item;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;

public class DislocatorItem extends Item {
    public DislocatorItem(Properties properties) {
        super(properties.stacksTo(1));
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Player player = context.getPlayer();

        if (player == null) {
            return InteractionResult.PASS;
        }

        ItemStack offHandStack = player.getOffhandItem();

        if (!(offHandStack.getItem() instanceof DislocatorItem)) {
            return InteractionResult.PASS;
        }

        Level world = context.getLevel();
        BlockPos blockPos = context.getClickedPos();
        BlockState blockState = world.getBlockState(blockPos);
        Block block = blockState.getBlock();

        ResourceLocation blockId = BuiltInRegistries.BLOCK.getKey(blockState.getBlock());
        String blockRegistryName = blockId.toString();
        InteractionHand hand = context.getHand();

        if (!world.isClientSide()
                && hand == InteractionHand.MAIN_HAND
                && blockRegistryName.equals("oritech:black_hole_block")
        ) {
            ItemStack blockStack = new ItemStack(block.asItem());

            Block.popResource(world, blockPos, blockStack);
            world.destroyBlock(blockPos, false, player);
            world.playSound(
                    null,
                    blockPos.getX() + 0.5,
                    blockPos.getY() + 0.5,
                    blockPos.getZ() + 0.5,
                    SoundEvents.BEACON_DEACTIVATE,
                    SoundSource.BLOCKS,
                    2.0F,
                    1.0F
            );
            return InteractionResult.SUCCESS;
        }

        return InteractionResult.PASS;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.translatable("tooltip.blackholedislocator.dislocator.line1"));
        tooltipComponents.add(Component.translatable("tooltip.blackholedislocator.dislocator.line2"));
    }
}

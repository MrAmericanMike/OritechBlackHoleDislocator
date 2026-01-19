package top.mramericanmike.blackholedislocator.item;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.Registries;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

public class DislocatorItem extends Item {
    public DislocatorItem() {
        super(new Settings().maxCount(1));
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        PlayerEntity player = context.getPlayer();

        if (player == null) {
            return ActionResult.PASS;
        }
        ItemStack offHandStack = player.getOffHandStack();
        if (!(offHandStack.getItem() instanceof DislocatorItem)) {
            return ActionResult.PASS;
        }

        World world = context.getWorld();
        BlockPos blockPos = context.getBlockPos();
        BlockState blockState = world.getBlockState(blockPos);
        Block block = blockState.getBlock();

        Identifier blockId = Registries.BLOCK.getId(block);
        String registryName = blockId.toString();

        Hand hand = context.getHand();

        if (!world.isClient()
                && hand == Hand.MAIN_HAND
                && registryName.equals("oritech:black_hole_block")
        ) {
            ItemStack blockStack = new ItemStack(block.asItem());
            Block.dropStack(world, blockPos, blockStack);
            world.breakBlock(blockPos, false);
            world.playSound(
                    null,
                    blockPos.getX() + 0.5,
                    blockPos.getY() + 0.5,
                    blockPos.getZ() + 0.5,
                    SoundEvents.BLOCK_BEACON_DEACTIVATE,
                    SoundCategory.BLOCKS,
                    2.0F,
                    1.0F
            );
            return ActionResult.SUCCESS;
        }
        return ActionResult.PASS;
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable("tooltip.blackholedislocator.dislocator.line1"));
        tooltip.add(Text.translatable("tooltip.blackholedislocator.dislocator.line2"));
    }
}

package net.ayoub.tutorialmod.block.custom;

import net.ayoub.tutorialmod.util.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

import java.util.List;

public class MagicBlock extends Block {
    public MagicBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {

        level.playSound(player,pos, SoundEvents.ALLAY_AMBIENT_WITHOUT_ITEM, SoundSource.BLOCKS,1.0F,1.0F);

        return InteractionResult.SUCCESS;
    }

    @Override
    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
       if(!entity.isSteppingCarefully() && entity instanceof Player) {
           ((Player) entity).randomTeleport(5,10,3,true);
       }
        if(entity instanceof ItemEntity item) {
            if(isValidItem(item.getItem()))   {
                item.setItem(new ItemStack(Items.DIAMOND,item.getItem().getCount()));

            }

        }



        super.stepOn(level, pos, state, entity);
    }

    private boolean isValidItem(ItemStack item) {
        return item.is(ModTags.Items.TRANSFORMABLE);
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.translatable("tooltip.tutorialmod.magic_block.tooltip"));


        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);


    }
}

package net.ayoub.tutorialmod.datagen;

import net.ayoub.tutorialmod.TutorialMod;
import net.ayoub.tutorialmod.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, TutorialMod.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.MAGIC_BLOCK);
        blockWithItem(ModBlocks.MY_BLOCK);
        blockWithItem(ModBlocks.BISMUTH_BLOCK);
        blockWithItem(ModBlocks.BISMUTH_ORE);
    }

    private void blockWithItem(DeferredBlock<?> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(),cubeAll(deferredBlock.get()));
    }


}

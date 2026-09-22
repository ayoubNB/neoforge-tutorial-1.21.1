package net.ayoub.tutorialmod.datagen;

import net.ayoub.tutorialmod.block.ModBlocks;
import net.ayoub.tutorialmod.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        List<ItemLike> BISMUTH_SMELTS =List.of(ModItems.RAW_BISMUTH,
                ModBlocks.BISMUTH_ORE);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.BISMUTH_BLOCK.get())
                .pattern("BBB")
                .pattern("BBB")
                .pattern("BBB")
                .define('B', ModItems.BISMUTH.get())
                .unlockedBy("has_bismuth", has(ModItems.BISMUTH)).save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.BISMUTH.get(),9)
                .requires(ModBlocks.BISMUTH_BLOCK.get())
                .unlockedBy("has_bismuth_block", has(ModBlocks.BISMUTH_BLOCK)).save(recipeOutput);
        oreSmelting(recipeOutput,BISMUTH_SMELTS,RecipeCategory.MISC,ModItems.BISMUTH.get(),0.25f,200,"bismuth");
        oreBlasting(recipeOutput,BISMUTH_SMELTS,RecipeCategory.MISC,ModItems.BISMUTH.get(),0.25f,100,"bismuth");

    }
}

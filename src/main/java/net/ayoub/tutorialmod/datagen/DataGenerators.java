package net.ayoub.tutorialmod.datagen;

import net.ayoub.tutorialmod.TutorialMod;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.loading.moddiscovery.ModInfo;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = TutorialMod.MODID)

public class DataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator gen = event.getGenerator();
        PackOutput packOutput=gen.getPackOutput();
        ExistingFileHelper existingFileHelper=event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider= event.getLookupProvider();

        gen.addProvider(event.includeServer(), new LootTableProvider(packOutput, Collections.emptySet(),
                List.of(new LootTableProvider.SubProviderEntry(ModBlocksLootTableProvider::new, LootContextParamSets.BLOCK)),lookupProvider));

        gen.addProvider(event.includeServer(), new ModRecipeProvider(packOutput, lookupProvider));

        BlockTagsProvider blockTagsProvider=new ModBlockTagProvider(packOutput,lookupProvider, existingFileHelper);
        gen.addProvider(event.includeServer(), blockTagsProvider);
        gen.addProvider(event.includeServer(), new ModItemTagProvider(packOutput, lookupProvider,blockTagsProvider.contentsGetter(),existingFileHelper));

        gen.addProvider(event.includeClient(), new ModBlockStateProvider(packOutput,existingFileHelper));
        gen.addProvider(event.includeClient(),new ModItemModelProvider(packOutput,existingFileHelper));

    }
}

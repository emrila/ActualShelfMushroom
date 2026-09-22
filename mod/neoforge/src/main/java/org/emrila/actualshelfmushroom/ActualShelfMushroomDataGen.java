package org.emrila.actualshelfmushroom;

import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import org.emrila.actualshelfmushroom.datagen.ShelfMushroomMultiVariantGenerator;

import java.util.stream.Stream;

@EventBusSubscriber(modid = ModConstants.MOD_ID)
public class ActualShelfMushroomDataGen {

    @SubscribeEvent
    public static void gatherClientData(GatherDataEvent.Client event) {
        event.createProvider(ModModelProvider::new);
    }

    private static class ModModelProvider extends ModelProvider {
        public ModModelProvider(PackOutput output) {
            super(output, ModConstants.MOD_ID);
        }

        @Override
        protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
            ShelfMushroomMultiVariantGenerator.generate(blockModels.blockStateOutput, BlockModelGenerators::plainVariant);
        }

        @Override
        protected Stream<? extends Holder<Block>> getKnownBlocks() {
            return Stream.of(BuiltInRegistries.BLOCK.wrapAsHolder(Blocks.SHELF_MUSHROOM));
        }

        @Override
        protected Stream<? extends Holder<Item>> getKnownItems() {
            return Stream.empty();
        }
    }
}
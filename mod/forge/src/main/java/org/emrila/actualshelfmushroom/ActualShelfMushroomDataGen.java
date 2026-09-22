package org.emrila.actualshelfmushroom;

import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.data.event.GatherDataEvent;
import org.emrila.actualshelfmushroom.datagen.ShelfMushroomMultiVariantGenerator;
import org.jspecify.annotations.NonNull;

import java.util.stream.Stream;

public class ActualShelfMushroomDataGen {

    public static void gatherData(@NonNull GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        generator.addProvider(event.includeClient(), new ModModelProvider(generator.getPackOutput()));
    }

    private static class ModModelProvider extends ModelProvider {

        public ModModelProvider(PackOutput output) {
            super(output);
        }

        @Override
        protected Stream<Block> getKnownBlocks() {
            return Stream.of(Blocks.SHELF_MUSHROOM);
        }

        @Override
        protected Stream<Item> getKnownItems() {
            return Stream.empty();
        }

        @Override
        protected BlockModelGenerators getBlockModelGenerators(BlockStateGeneratorCollector blocks, ItemInfoCollector items, SimpleModelCollector models) {
            return new BlockModelGenerators(blocks, items, models) {
                @Override
                public void run() {
                    ShelfMushroomMultiVariantGenerator.generate(blockStateOutput, BlockModelGenerators::plainVariant);
                }
            };
        }

        @Override
        protected ItemModelGenerators getItemModelGenerators(ItemInfoCollector items, SimpleModelCollector models) {
            return new ItemModelGenerators(items, models) {
                @Override
                public void run() {
                }
            };
        }
    }
}

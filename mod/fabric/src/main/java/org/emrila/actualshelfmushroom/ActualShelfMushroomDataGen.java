package org.emrila.actualshelfmushroom;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import org.emrila.actualshelfmushroom.datagen.ShelfMushroomMultiVariantGenerator;

public class ActualShelfMushroomDataGen implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(ModModelProvider::new);
    }

    private static class ModModelProvider extends FabricModelProvider {
        public ModModelProvider(FabricPackOutput output) {
            super(output);
        }

        @Override
        public void generateBlockStateModels(BlockModelGenerators gen) {
            ShelfMushroomMultiVariantGenerator.generate(gen.blockStateOutput, BlockModelGenerators::plainVariant);

        }

        @Override
        public void generateItemModels(ItemModelGenerators gen) {
        }
    }
}

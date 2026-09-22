package org.emrila.actualshelfmushroom.datagen;

import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.blockstates.BlockModelDefinitionGenerator;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.data.models.model.ModelLocationUtils;
import net.minecraft.client.renderer.block.dispatch.VariantMutator;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import org.emrila.actualshelfmushroom.ModConstants;

import java.util.function.Consumer;
import java.util.function.Function;

public class ShelfMushroomMultiVariantGenerator {
    public static void generate(Consumer<BlockModelDefinitionGenerator> blockStateOutput, Function<Identifier, MultiVariant> createVariant) {
        // Same as ROTATION_HORIZONTAL_FACING in BlockModelGenerators. It's accessible on Fabric and NeoForge, but not Forge... So rather just keep it all in one place.
        PropertyDispatch.C1<VariantMutator, Direction> newStage = PropertyDispatch.modify(BlockStateProperties.HORIZONTAL_FACING)
                .select(Direction.EAST, BlockModelGenerators.Y_ROT_90)
                .select(Direction.SOUTH, BlockModelGenerators.Y_ROT_180)
                .select(Direction.WEST, BlockModelGenerators.Y_ROT_270)
                .select(Direction.NORTH, BlockModelGenerators.NOP);

        Block block = Blocks.SHELF_MUSHROOM;

        Identifier shelfStageZero = ModelLocationUtils.getModelLocation(block, "_stage0");
        Identifier shelfStageOne = ModelLocationUtils.getModelLocation(block, "_stage1");
        Identifier shelfStageTwo = ShelfMushroomMultiVariantGenerator.getModelLocation(block, "_stage2");
        Identifier shelfStageThree = ShelfMushroomMultiVariantGenerator.getModelLocation(block, "_stage3");

        blockStateOutput.accept(
                MultiVariantGenerator.dispatch(block)
                        .with(PropertyDispatch.initial(BlockStateProperties.AGE_3)
                                .select(0, createVariant.apply(shelfStageZero))
                                .select(1, createVariant.apply(shelfStageOne))
                                .select(2, createVariant.apply(shelfStageTwo))
                                .select(3, createVariant.apply(shelfStageThree)))
                        .with(newStage));
    }

    private static Identifier getModelLocation(Block block, String suffix) {
        String blockName = BuiltInRegistries.BLOCK.getKey(block).getPath();
        return ModConstants.id("block/" + blockName + suffix);
    }

}

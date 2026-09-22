package org.emrila.actualshelfmushroom.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealSource;
import net.minecraft.world.level.block.ShelfMushroomBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.Map;

@Mixin(ShelfMushroomBlock.class)
public class ShelfMushroomBlockMixin {
    @Shadow
    @Final
    @Mutable
    public static int MAX_AGE;

    @Shadow
    @Final
    @Mutable
    public static IntegerProperty AGE;

    @Shadow
    @Final
    @Mutable
    private static List<Map<Direction, VoxelShape>> SHAPES;

    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void actualshelfmushroom$extendAgeRange(CallbackInfo ci) {
        MAX_AGE = 3;
        AGE = BlockStateProperties.AGE_3;

        SHAPES = List.of(
                Shapes.rotateHorizontal(Shapes.or(
                        Block.box(3, 9, 9, 13, 11, 16),
                        Block.box(5, 8, 12, 11, 9, 16)
                )),
                Shapes.rotateHorizontal(Shapes.or(
                        Block.box(1, 8, 6, 15, 11, 16),
                        Block.box(4, 6, 10, 12, 8, 16)
                )),
                Shapes.rotateHorizontal(Shapes.or(
                        Block.box(1, 13, 2, 15, 16, 16),
                        Block.box(4, 11, 8, 12, 13, 16)
                )),
                Shapes.rotateHorizontal(Shapes.or(
                        Block.box(0, 13, 0, 16, 16, 16),
                        Block.box(3, 11, 6, 13, 13, 16)
                ))
        );
    }

    @Inject(method = "<init>", at = @At("TAIL"))
    private void actualshelfmushroom$configureMaturePushReaction(BlockBehaviour.Properties properties, CallbackInfo ci) {
        ShelfMushroomBlock block = (ShelfMushroomBlock) (Object) this;
        for (BlockState state : block.getStateDefinition().getPossibleStates()) {
            if (state.getValue(AGE) == MAX_AGE) {
                ((BlockStateBaseAccessor) state).actualshelfmushroom$setPushReaction(PushReaction.PUSH_PULL);
            }
        }
    }

    @Inject(method = "isValidBonemealTarget", at = @At("HEAD"), cancellable = true)
    private void actualshelfmushroom$isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state, BonemealSource source, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(state.getValue(AGE) < MAX_AGE);
    }

    @Inject(method = "isPathfindable", at = @At("HEAD"), cancellable = true)
    private void actualshelfmushroom$isPathfindable(BlockState state, PathComputationType type, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(state.getValue(AGE) == MAX_AGE);
    }

    @Inject(method = "canSurvive", at = @At("RETURN"), cancellable = true)
    private void actualshelfmushroom$canSurvive(final BlockState state, final LevelReader level, final BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        boolean result = cir.getReturnValue() || state.getValue(AGE) == MAX_AGE;
        cir.setReturnValue(result);
    }
    
}

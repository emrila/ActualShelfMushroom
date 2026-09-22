package org.emrila.actualshelfmushroom.mixin;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.PushReaction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(BlockBehaviour.BlockStateBase.class)
public interface BlockStateBaseAccessor {
    @Mutable
    @Accessor("pushReaction")
    void actualshelfmushroom$setPushReaction(PushReaction reaction);
}

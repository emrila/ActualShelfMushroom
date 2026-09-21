/**
 *
 */
package org.emrila.emcmultiloader;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.data.event.GatherDataEvent;
import org.jspecify.annotations.NonNull;

public class EmcMultiLoaderDataGen {

    public static void gatherData(@NonNull GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();

        //generator.addProvider(event.includeClient(), new ModModelProvider(packOutput));
        //generator.addProvider(event.includeServer(), ModRecipeProvider.create(packOutput));
    }
}

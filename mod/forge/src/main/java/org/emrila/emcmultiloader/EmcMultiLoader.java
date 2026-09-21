package org.emrila.emcmultiloader;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.bus.BusGroup;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(ModConstants.MOD_ID)
public class EmcMultiLoader {

    public EmcMultiLoader(FMLJavaModLoadingContext context) {
        BusGroup modBusGroup = context.getModBusGroup();

        FMLCommonSetupEvent.getBus(modBusGroup).addListener(EmcMultiLoader::commonSetup);
        BuildCreativeModeTabContentsEvent.BUS.addListener(EmcMultiLoader::addCreative);
        GatherDataEvent.getBus(modBusGroup).addListener(EmcMultiLoaderDataGen::gatherData);
    }

    private static void commonSetup(FMLCommonSetupEvent event) {
    }

    private static void addCreative(BuildCreativeModeTabContentsEvent event) {
        ResourceKey<CreativeModeTab> tabKey = event.getTabKey();

    }

}

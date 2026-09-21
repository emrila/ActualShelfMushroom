package org.emrila.emcmultiloader;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

@Mod(ModConstants.MOD_ID)
public class EmcMultiLoader {

    public EmcMultiLoader(IEventBus eventBus) {

        NeoForge.EVENT_BUS.register(this);
        eventBus.addListener(EmcMultiLoader::commonSetup);
        eventBus.addListener(EmcMultiLoader::addCreative);
    }

    private static void commonSetup(FMLCommonSetupEvent event) {
    }

    private static void addCreative(BuildCreativeModeTabContentsEvent event) {
        ResourceKey<CreativeModeTab> tabKey = event.getTabKey();

    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
    }
}

package org.emrila.actualshelfmushroom;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

@Mod(ModConstants.MOD_ID)
public class ActualShelfMushroom {

    public ActualShelfMushroom(IEventBus eventBus) {

        NeoForge.EVENT_BUS.register(this);
        eventBus.addListener(ActualShelfMushroom::commonSetup);
    }

    private static void commonSetup(FMLCommonSetupEvent event) {
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
    }
}

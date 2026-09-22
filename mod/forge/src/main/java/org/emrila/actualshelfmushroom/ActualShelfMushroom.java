package org.emrila.actualshelfmushroom;

import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.bus.BusGroup;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(ModConstants.MOD_ID)
public class ActualShelfMushroom {

    public ActualShelfMushroom(FMLJavaModLoadingContext context) {
        BusGroup modBusGroup = context.getModBusGroup();

        FMLCommonSetupEvent.getBus(modBusGroup).addListener(ActualShelfMushroom::commonSetup);
        GatherDataEvent.getBus(modBusGroup).addListener(ActualShelfMushroomDataGen::gatherData);
    }

    private static void commonSetup(FMLCommonSetupEvent event) {
    }

}

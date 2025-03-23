package com.judiraal.moresweatcompat.mods.ytech;

import com.judiraal.moresweatcompat.mods.ConditionalEventBusSubscriber;
import com.momosoftworks.coldsweat.api.event.core.registry.BlockTempRegisterEvent;
import net.neoforged.bus.api.SubscribeEvent;

@ConditionalEventBusSubscriber(dependencies = {"ytech"})
public class YTechCompat {
    @SubscribeEvent
    public static void registerBlockTemps(BlockTempRegisterEvent event) {
        event.register(new YTechFirePitTemp());
        event.register(new YTechSmelterTemp());
    }
}

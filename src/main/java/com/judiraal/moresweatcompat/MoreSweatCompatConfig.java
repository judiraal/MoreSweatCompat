package com.judiraal.moresweatcompat;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

@EventBusSubscriber(modid = MoreSweatCompat.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class MoreSweatCompatConfig {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    private static final ModConfigSpec.ConfigValue<Double> INSULATION_SAFE_FACTOR = BUILDER
            .comment("Increases the safe temperature range based on armor insulation, use 0.0 to disable")
            .define("insulationSafeFactor", 1.0d);

    static final ModConfigSpec SPEC = BUILDER.build();

    public static double insulationSafeFactor;

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event) {
        insulationSafeFactor = INSULATION_SAFE_FACTOR.get()/60;
    }
}

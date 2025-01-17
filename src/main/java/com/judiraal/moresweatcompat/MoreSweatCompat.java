package com.judiraal.moresweatcompat;

import com.judiraal.moresweatcompat.mods.ConditionalSubscribers;
import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import org.slf4j.Logger;

@Mod(MoreSweatCompat.MOD_ID)
public class MoreSweatCompat {
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final String MOD_ID = "moresweatcompat";

    public MoreSweatCompat(IEventBus modEventBus, ModContainer modContainer) {
        ConditionalSubscribers.inject(modContainer);
        modContainer.registerConfig(ModConfig.Type.COMMON, MoreSweatCompatConfig.SPEC);
    }
}

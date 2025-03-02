package com.judiraal.moresweatcompat.mods.cold_sweat;

import com.judiraal.moresweatcompat.MoreSweatCompatConfig;
import com.judiraal.moresweatcompat.MoreSweatCompat;
import com.judiraal.moresweatcompat.mixin.cold_sweat.WorldHelperAccessor;
import com.momosoftworks.coldsweat.api.event.common.insulation.InsulationTickEvent;
import com.momosoftworks.coldsweat.api.event.core.registry.TempModifierRegisterEvent;
import com.momosoftworks.coldsweat.api.util.Placement;
import com.momosoftworks.coldsweat.api.util.Temperature;
import com.momosoftworks.coldsweat.common.capability.handler.EntityTempManager;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.server.ServerStoppedEvent;
import net.neoforged.neoforge.event.tick.ServerTickEvent;

@EventBusSubscriber
public class ColdSweatCompat {
    @SubscribeEvent
    public static void registerTempModifiers(TempModifierRegisterEvent event) {
        event.register(ResourceLocation.fromNamespaceAndPath(MoreSweatCompat.MOD_ID, "armor_limit"), InsulationLimitTempModifier::new);
    }

    @SubscribeEvent
    public static void onInsulation(InsulationTickEvent event) {
        if (MoreSweatCompatConfig.insulationSafeFactor == 0) return;
        double cold = event.getProperty("cold");
        if (cold > 0) Temperature.addOrReplaceModifier(event.getPlayer(), new InsulationLimitTempModifier(-cold* MoreSweatCompatConfig.insulationSafeFactor).tickRate(20).expires(20), Temperature.Trait.FREEZING_POINT, Placement.Duplicates.BY_CLASS);
        double heat = event.getProperty("heat");
        if (heat > 0) Temperature.addOrReplaceModifier(event.getPlayer(), new InsulationLimitTempModifier(heat* MoreSweatCompatConfig.insulationSafeFactor).tickRate(20).expires(20), Temperature.Trait.BURNING_POINT, Placement.Duplicates.BY_CLASS);
    }

    @SubscribeEvent
    public static void onServerStop(ServerStoppedEvent event) {
        EntityTempManager.SERVER_CAP_CACHE.clear();
        EntityTempManager.CLIENT_CAP_CACHE.clear();
        WorldHelperAccessor.getTemperatureChecks().clear();
        WorldHelperAccessor.getDummies().clear();
    }
}

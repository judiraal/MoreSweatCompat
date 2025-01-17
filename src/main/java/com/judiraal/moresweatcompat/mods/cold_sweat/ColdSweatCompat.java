package com.judiraal.moresweatcompat.mods.cold_sweat;

import com.judiraal.moresweatcompat.MoreSweatCompatConfig;
import com.judiraal.moresweatcompat.MoreSweatCompat;
import com.momosoftworks.coldsweat.api.event.common.insulation.InsulationTickEvent;
import com.momosoftworks.coldsweat.api.event.core.registry.TempModifierRegisterEvent;
import com.momosoftworks.coldsweat.api.util.Placement;
import com.momosoftworks.coldsweat.api.util.Temperature;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;

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
}

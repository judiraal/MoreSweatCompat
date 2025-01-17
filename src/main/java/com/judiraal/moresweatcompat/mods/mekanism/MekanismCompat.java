package com.judiraal.moresweatcompat.mods.mekanism;

import com.judiraal.moresweatcompat.mods.ConditionalEventBusSubscriber;
import com.judiraal.moresweatcompat.mods.cold_sweat.InsulationLimitTempModifier;
import com.momosoftworks.coldsweat.api.event.core.registry.BlockTempRegisterEvent;
import com.momosoftworks.coldsweat.api.util.Placement;
import com.momosoftworks.coldsweat.api.util.Temperature;
import mekanism.api.radiation.capability.IRadiationShielding;
import mekanism.common.capabilities.Capabilities;
import mekanism.common.util.EnumUtils;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

@ConditionalEventBusSubscriber(dependencies = {"mekanism"})
public class MekanismCompat {
    @SubscribeEvent
    public static void registerBlockTemps(BlockTempRegisterEvent event) {
        event.register(new MekanismBlockTemp());
    }

    @SubscribeEvent(priority = EventPriority.LOW)
    public static void applyRadiationShielding(PlayerTickEvent.Post event) {
        Player player = event.getEntity();
        if (player instanceof ServerPlayer && player.tickCount % 20 == 0 && !player.level().isClientSide) {
            double resistance = 0;
            for (EquipmentSlot type : EnumUtils.ARMOR_SLOTS) {
                ItemStack stack = player.getItemBySlot(type);
                if (!stack.isEmpty()) {
                    IRadiationShielding shielding = stack.getCapability(Capabilities.RADIATION_SHIELDING);
                    if (shielding != null) {
                        resistance += shielding.getRadiationShielding();
                    }
                }
            }
            if (resistance > .99) {
                Temperature.addOrReplaceModifier(player, new InsulationLimitTempModifier(-2.5).tickRate(20).expires(20), Temperature.Trait.FREEZING_POINT, Placement.Duplicates.BY_CLASS);
                Temperature.addOrReplaceModifier(player, new InsulationLimitTempModifier(2.5).tickRate(20).expires(20), Temperature.Trait.BURNING_POINT, Placement.Duplicates.BY_CLASS);
            }
        }
    }
}

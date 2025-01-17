package com.judiraal.moresweatcompat.mixin.mekanism;

import com.judiraal.moresweatcompat.mods.mekanism.EnvironmentLossHolder;
import mekanism.api.heat.HeatAPI;
import mekanism.common.content.network.HeatNetwork;
import mekanism.common.content.network.transmitter.ThermodynamicConductor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(HeatNetwork.class)
public class HeatNetworkMixin {
    @Redirect(method = "onUpdate", at = @At(value = "INVOKE", target = "mekanism/common/content/network/transmitter/ThermodynamicConductor.simulate ()Lmekanism/api/heat/HeatAPI$HeatTransfer;"))
    private HeatAPI.HeatTransfer msc_storeHeatEnvironmentLoss(ThermodynamicConductor instance) {
        HeatAPI.HeatTransfer result = instance.simulate();
        ((EnvironmentLossHolder)instance).setLastEnvironmentLoss(result.environmentTransfer());
        return result;
    }
}

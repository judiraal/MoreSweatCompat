package com.judiraal.moresweatcompat.mixin.mekanism;

import com.judiraal.moresweatcompat.mods.mekanism.EnvironmentLossHolder;
import mekanism.common.content.boiler.BoilerMultiblockData;
import mekanism.common.content.evaporation.EvaporationMultiblockData;
import mekanism.generators.common.content.fission.FissionReactorMultiblockData;
import mekanism.generators.common.content.fusion.FusionReactorMultiblockData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin({BoilerMultiblockData.class, EvaporationMultiblockData.class, FissionReactorMultiblockData.class, FusionReactorMultiblockData.class})
public class EnvironmentMultiblockDataMixin implements EnvironmentLossHolder {
    @Shadow(remap = false)
    public double lastEnvironmentLoss;

    @Override
    public double getLastEnvironmentLoss() {
        return lastEnvironmentLoss;
    }
}

package com.judiraal.moresweatcompat.mixin.mekanism;

import com.judiraal.moresweatcompat.mods.mekanism.EnvironmentLossHolder;
import mekanism.common.content.network.transmitter.ThermodynamicConductor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(ThermodynamicConductor.class)
public class ThermodynamicConductorMixin implements EnvironmentLossHolder {
    @Unique
    private double moreSweatCompat$environmentLoss;

    @Override
    public double getLastEnvironmentLoss() {
        return moreSweatCompat$environmentLoss;
    }

    @Override
    public void setLastEnvironmentLoss(double environmentLoss) {
        this.moreSweatCompat$environmentLoss = environmentLoss;
    }
}

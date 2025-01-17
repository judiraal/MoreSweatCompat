package com.judiraal.moresweatcompat.mixin.mekanism;

import com.judiraal.moresweatcompat.mods.mekanism.EnvironmentLossHolder;
import mekanism.common.tile.TileEntityQuantumEntangloporter;
import mekanism.common.tile.machine.TileEntityFuelwoodHeater;
import mekanism.common.tile.machine.TileEntityResistiveHeater;
import mekanism.generators.common.tile.TileEntityHeatGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin({TileEntityFuelwoodHeater.class, TileEntityResistiveHeater.class, TileEntityQuantumEntangloporter.class, TileEntityHeatGenerator.class})
public class EnvironmentTileEntityMixin implements EnvironmentLossHolder {
    @Shadow(remap = false)
    public double getLastEnvironmentLoss() {return 0;}
}

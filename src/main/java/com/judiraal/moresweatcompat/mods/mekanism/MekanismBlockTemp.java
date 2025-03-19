package com.judiraal.moresweatcompat.mods.mekanism;

import com.momosoftworks.coldsweat.api.temperature.block_temp.BlockTemp;
import mekanism.common.registries.MekanismBlocks;
import mekanism.common.tile.prefab.TileEntityMultiblock;
import mekanism.common.tile.transmitter.TileEntityTransmitter;
import mekanism.generators.common.registries.GeneratorsBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class MekanismBlockTemp extends BlockTemp {
    public MekanismBlockTemp() {
        super(0.0F, 21.5F, -Double.MAX_VALUE, 21.5F, 7.0F, true,
                MekanismBlocks.BASIC_THERMODYNAMIC_CONDUCTOR.getDelegate().value(),
                MekanismBlocks.ADVANCED_THERMODYNAMIC_CONDUCTOR.getDelegate().value(),
                MekanismBlocks.ELITE_THERMODYNAMIC_CONDUCTOR.getDelegate().value(),
                MekanismBlocks.ULTIMATE_THERMODYNAMIC_CONDUCTOR.getDelegate().value(),
                MekanismBlocks.RESISTIVE_HEATER.getDelegate().value(),
                MekanismBlocks.FUELWOOD_HEATER.getDelegate().value(),
                MekanismBlocks.QUANTUM_ENTANGLOPORTER.getDelegate().value(),
                GeneratorsBlocks.HEAT_GENERATOR.getDelegate().value(),
                MekanismBlocks.THERMAL_EVAPORATION_CONTROLLER.getDelegate().value(),
                MekanismBlocks.THERMAL_EVAPORATION_BLOCK.getDelegate().value(),
                MekanismBlocks.THERMAL_EVAPORATION_VALVE.getDelegate().value(),
                MekanismBlocks.BOILER_CASING.getDelegate().value(),
                MekanismBlocks.BOILER_VALVE.getDelegate().value(),
                GeneratorsBlocks.FUSION_REACTOR_FRAME.getDelegate().value(),
                GeneratorsBlocks.FUSION_REACTOR_CONTROLLER.getDelegate().value(),
                GeneratorsBlocks.FUSION_REACTOR_PORT.getDelegate().value(),
                GeneratorsBlocks.FUSION_REACTOR_LOGIC_ADAPTER.getDelegate().value(),
                GeneratorsBlocks.FISSION_REACTOR_PORT.getDelegate().value(),
                GeneratorsBlocks.FISSION_FUEL_ASSEMBLY.getDelegate().value(),
                GeneratorsBlocks.FISSION_REACTOR_LOGIC_ADAPTER.getDelegate().value(),
                GeneratorsBlocks.FISSION_REACTOR_CASING.getDelegate().value());
    }

    @Override
    public double getTemperature(Level level, @Nullable LivingEntity livingEntity, BlockState blockState, BlockPos blockPos, double v) {
        Object blockEntity = level.getBlockEntity(blockPos);
        if (blockEntity == null) return 0;
        if (blockEntity instanceof EnvironmentLossHolder holder) return holder.getLastEnvironmentLoss();
        double airHeatFactor = .05;
        if (blockEntity instanceof TileEntityTransmitter transmitter) blockEntity = transmitter.getTransmitter();
        else if (blockEntity instanceof TileEntityMultiblock<?> multiblock) {
            blockEntity = multiblock.getMultiblock();
            airHeatFactor = .1;
        }
        else return 0;
        if (blockEntity instanceof EnvironmentLossHolder holder) return holder.getLastEnvironmentLoss() * airHeatFactor;
        return 0;
    }
}

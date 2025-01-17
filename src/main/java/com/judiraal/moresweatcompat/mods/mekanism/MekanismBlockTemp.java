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
                MekanismBlocks.BASIC_THERMODYNAMIC_CONDUCTOR.getBlock(),
                MekanismBlocks.ADVANCED_THERMODYNAMIC_CONDUCTOR.getBlock(),
                MekanismBlocks.ELITE_THERMODYNAMIC_CONDUCTOR.getBlock(),
                MekanismBlocks.ULTIMATE_THERMODYNAMIC_CONDUCTOR.getBlock(),
                MekanismBlocks.RESISTIVE_HEATER.getBlock(),
                MekanismBlocks.FUELWOOD_HEATER.getBlock(),
                MekanismBlocks.QUANTUM_ENTANGLOPORTER.getBlock(),
                GeneratorsBlocks.HEAT_GENERATOR.getBlock(),
                MekanismBlocks.THERMAL_EVAPORATION_CONTROLLER.getBlock(),
                MekanismBlocks.THERMAL_EVAPORATION_BLOCK.getBlock(),
                MekanismBlocks.THERMAL_EVAPORATION_VALVE.getBlock(),
                MekanismBlocks.BOILER_CASING.getBlock(),
                MekanismBlocks.BOILER_VALVE.getBlock(),
                GeneratorsBlocks.FUSION_REACTOR_FRAME.getBlock(),
                GeneratorsBlocks.FUSION_REACTOR_CONTROLLER.getBlock(),
                GeneratorsBlocks.FUSION_REACTOR_PORT.getBlock(),
                GeneratorsBlocks.FUSION_REACTOR_LOGIC_ADAPTER.getBlock(),
                GeneratorsBlocks.FISSION_REACTOR_PORT.getBlock(),
                GeneratorsBlocks.FISSION_FUEL_ASSEMBLY.getBlock(),
                GeneratorsBlocks.FISSION_REACTOR_LOGIC_ADAPTER.getBlock(),
                GeneratorsBlocks.FISSION_REACTOR_CASING.getBlock());
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

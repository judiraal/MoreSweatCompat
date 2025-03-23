package com.judiraal.moresweatcompat.mods.ytech;

import com.judiraal.moresweatcompat.mods.mekanism.EnvironmentLossHolder;
import com.momosoftworks.coldsweat.api.temperature.block_temp.BlockTemp;
import com.yanny.ytech.registration.YTechBlocks;
import mekanism.common.tile.prefab.TileEntityMultiblock;
import mekanism.common.tile.transmitter.TileEntityTransmitter;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractFurnaceBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import org.jetbrains.annotations.Nullable;

public class YTechFirePitTemp extends BlockTemp {
    public YTechFirePitTemp() {
        super(0.0, 0.88, Double.NEGATIVE_INFINITY, 2.0, 4, true,
                YTechBlocks.FIRE_PIT.get());
    }

    @Override
    public double getTemperature(Level level, @Nullable LivingEntity livingEntity, BlockState blockState, BlockPos blockPos, double v) {
        if (blockState.getValue(AbstractFurnaceBlock.LIT))
            return 0.37 * Mth.cos(((float)blockState.getValue(BlockStateProperties.LEVEL)-15)/30*Mth.PI);
        return 0;
    }
}

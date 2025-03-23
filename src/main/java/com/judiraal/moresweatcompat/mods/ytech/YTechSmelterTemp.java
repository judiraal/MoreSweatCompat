package com.judiraal.moresweatcompat.mods.ytech;

import com.momosoftworks.coldsweat.api.temperature.block_temp.BlockTemp;
import com.yanny.ytech.configuration.block_entity.AbstractPrimitiveMachineBlockEntity;
import com.yanny.ytech.registration.YTechBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractFurnaceBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import org.jetbrains.annotations.Nullable;

public class YTechSmelterTemp extends BlockTemp {
    public YTechSmelterTemp() {
        super(0.0, 2.0, Double.NEGATIVE_INFINITY, 3.5, 7, true,
                YTechBlocks.PRIMITIVE_SMELTER.get(),
                YTechBlocks.PRIMITIVE_ALLOY_SMELTER.get());
    }

    @Override
    public double getTemperature(Level level, @Nullable LivingEntity livingEntity, BlockState blockState, BlockPos blockPos, double v) {
        Object blockEntity = level.getBlockEntity(blockPos);
        if (blockEntity instanceof AbstractPrimitiveMachineBlockEntity entity)
            return 0.63 * Mth.cos(((float)entity.temperature()-1300)/2600*Mth.PI);
        return 0;
    }
}

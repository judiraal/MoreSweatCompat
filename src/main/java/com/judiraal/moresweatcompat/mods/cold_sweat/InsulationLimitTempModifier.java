package com.judiraal.moresweatcompat.mods.cold_sweat;

import com.momosoftworks.coldsweat.api.temperature.modifier.TempModifier;
import com.momosoftworks.coldsweat.api.util.Temperature;
import net.minecraft.world.entity.LivingEntity;

import java.util.function.Function;

public class InsulationLimitTempModifier extends TempModifier {
    public InsulationLimitTempModifier() {
        this(0);
    }

    public InsulationLimitTempModifier(double delta) {
        this.getNBT().putDouble("delta", delta);
    }

    @Override
    protected Function<Double, Double> calculate(LivingEntity livingEntity, Temperature.Trait trait) {
        double delta = this.getNBT().getDouble("delta");
        return temp -> temp + delta;
    }
}

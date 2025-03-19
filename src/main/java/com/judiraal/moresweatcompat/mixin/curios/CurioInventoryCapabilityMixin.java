package com.judiraal.moresweatcompat.mixin.curios;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.*;
import top.theillusivec4.curios.api.SlotResult;
import top.theillusivec4.curios.common.capability.CurioInventoryCapability;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Mixin(CurioInventoryCapability.class)
public class CurioInventoryCapabilityMixin {
    @Final
    @Shadow
    LivingEntity livingEntity;

    @Unique
    private Map<Item, Optional<SlotResult>> firstCurioMap = new HashMap<>();

    @Unique
    private long firstCurioGameTime;

    /**
     * @author Judiraal
     * @reason Best way to optimize this method for performance
     */
    @Overwrite
    public Optional<SlotResult> findFirstCurio(Item item) {
        long currentGameTime = this.livingEntity.level().getGameTime();
        if (currentGameTime != firstCurioGameTime) {
            firstCurioGameTime = currentGameTime;
            firstCurioMap.clear();
        }
        return firstCurioMap.computeIfAbsent(item,
                i -> ((CurioInventoryCapability)(Object)this).findFirstCurio(
                        s -> s.getItem() == i, ""));
    }
}

package com.judiraal.moresweatcompat.mods.mekanism;

public interface EnvironmentLossHolder {
    double getLastEnvironmentLoss();

    default void setLastEnvironmentLoss(double environmentLoss) {};
}

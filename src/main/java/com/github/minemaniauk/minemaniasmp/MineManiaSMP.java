package com.github.minemaniauk.minemaniasmp;

import com.github.cozyplugins.cozylibrary.CozyPlugin;
import org.jetbrains.annotations.NotNull;

/**
 * Represents the main class.
 */
public final class MineManiaSMP extends CozyPlugin {

    private static @NotNull MineManiaSMP instance;

    @Override
    public boolean enableCommandDirectory() {
        return true;
    }

    @Override
    public void onCozyEnable() {

        // Initialize this instance.
        MineManiaSMP.instance = this;
    }

    /**
     * Used to get the instance of this plugin.
     *
     * @return The instance of this plugin.
     */
    public static @NotNull MineManiaSMP getInstance() {
        return MineManiaSMP.instance;
    }
}

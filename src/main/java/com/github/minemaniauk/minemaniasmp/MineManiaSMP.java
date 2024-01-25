package com.github.minemaniauk.minemaniasmp;

import com.github.cozyplugins.cozylibrary.CozyPlugin;
import com.github.cozyplugins.cozylibrary.inventory.action.action.CloseAction;
import com.github.cozyplugins.cozylibrary.location.Region3D;
import com.github.cozyplugins.cozylibrary.user.PlayerUser;
import com.github.cozyplugins.cozylibrary.user.User;
import com.github.minemaniauk.minemaniasmp.command.TeleportCommand;
import com.github.minemaniauk.minemaniasmp.inventory.TeleportInventory;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

/**
 * Represents the main class.
 */
public final class MineManiaSMP extends CozyPlugin implements Listener {

    private static @NotNull MineManiaSMP instance;

    @Override
    public boolean enableCommandDirectory() {
        return true;
    }

    @Override
    public void onCozyEnable() {

        // Initialize this instance.
        MineManiaSMP.instance = this;

        // Add the commands.
        this.addCommandType(new TeleportCommand());

        // Register the events.
        this.getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onEndPortal(PlayerPortalEvent event) {
        Player player = event.getPlayer();

        Region3D region3D = new Region3D(player.getLocation(), player.getLocation());
        region3D.expand(20);

        Location portalLocation = new Location(Bukkit.getWorld("world"), 19, 114, 0);

        // Check if the player is near the portal.
        if (!region3D.contains(portalLocation)) return;

        event.setCancelled(true);

        if (player.getOpenInventory().getTopInventory().getType() == InventoryType.CHEST) return;

        player.teleport(new Location(Bukkit.getWorld("world"), 13, 113, 0));
        new TeleportInventory().open(player);
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

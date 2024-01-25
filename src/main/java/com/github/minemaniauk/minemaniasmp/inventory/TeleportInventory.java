/*
 * MineManiaSMP
 * Used for interacting with the database and message broker.
 * Copyright (C) 2023  MineManiaUK Staff
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.github.minemaniauk.minemaniasmp.inventory;

import com.github.cozyplugins.cozylibrary.inventory.InventoryInterface;
import com.github.cozyplugins.cozylibrary.inventory.InventoryItem;
import com.github.cozyplugins.cozylibrary.inventory.action.action.ClickAction;
import com.github.cozyplugins.cozylibrary.user.PlayerUser;
import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;

/**
 * Represents the teleport inventory.
 * Used to show all the teleporting options for the player.
 */
public class TeleportInventory extends InventoryInterface {

    /**
     * Used to create an instance of the inventory.
     */
    public TeleportInventory() {
        super(54, "&f₴₴₴₴₴₴₴₴▶\uD83C");
    }

    @Override
    protected void onGenerate(PlayerUser player) {

        // Back button.
        this.setItem(new InventoryItem()
                .setMaterial(Material.PINK_STAINED_GLASS_PANE)
                .setCustomModelData(1)
                .setName("&a&lBack")
                .setLore("&7Click to go to the &f/help &7menu.")
                .addAction((ClickAction) (user, type, inventory) -> {
                    user.runCommandsAsOp("help");
                })
                .addSlot(45)
        );

        // Warps button.
        this.setItem(new InventoryItem()
                .setMaterial(Material.PINK_STAINED_GLASS_PANE)
                .setCustomModelData(1)
                .setName("&6&lWarps")
                .setLore("&eLocations saved by other players.",
                        "&7",
                        "&7Click to open the &f/warps &7menu.")
                .addAction((ClickAction) (user, type, inventory) -> {
                    user.runCommandsAsOp("warps");
                })
                .addSlot(0, 1, 2,
                        9, 10, 11,
                        18, 19, 20)
        );

        // RTP button.
        this.setItem(new InventoryItem()
                .setMaterial(Material.PINK_STAINED_GLASS_PANE)
                .setCustomModelData(1)
                .setName("&2&lRandom Teleport")
                .setLore("&eTeleport to a random location",
                        "&ein a world.",
                        "&7",
                        "&7Click to open the &f/rtp &7menu.")
                .addAction((ClickAction) (user, type, inventory) -> {
                    user.runCommandsAsOp("warps");
                })
                .addSlot(3, 4, 5,
                        12, 13, 14,
                        21, 22, 23)
        );

        // Homes button.
        this.setItem(new InventoryItem()
                .setMaterial(Material.PINK_STAINED_GLASS_PANE)
                .setCustomModelData(1)
                .setName("&a&lHomes")
                .setLore("&eSave private locations to teleport to.",
                        "&7",
                        "&f/home <name> &7Teleports you to a home.",
                        "&f/sethome <name> &7Create a home at the location",
                        "&7you are standing at.",
                        "&f/delhome <name> &7Delete a home.")
                .addSlot(6, 7, 8,
                        15, 16, 17,
                        24, 25, 26)
        );

        // Spawn button.
        this.setItem(new InventoryItem()
                .setMaterial(Material.PINK_STAINED_GLASS_PANE)
                .setCustomModelData(1)
                .setName("&f&lSpawn")
                .setLore("&7Click to teleport to &f/spawn&7.")
                .addAction((ClickAction) (user, type, inventory) -> {
                    user.runCommandsAsOp("spawn");
                })
                .addSlot(18, 19, 20)
        );

        // Horses button.
        this.createTeleportButton(
                "&f&lHorses",
                "teleport horses",
                27, 28, 29
        );

        // Fishing button.
        this.createTeleportButton(
                "&f&lFishing",
                "teleport fishing",
                36, 37, 38
        );

        // PVP button.
        this.createTeleportButton(
                "&f&lPVP",
                "teleport pvp",
                30, 31, 32
        );

        // Leaderboard button.
        this.createTeleportButton(
                "&f&lLeaderboards",
                "teleport leaderboards",
                39, 40, 41
        );

        // Storage button.
        this.createTeleportButton(
                "&f&lCommunity Storage",
                "teleport storage",
                24, 25, 26
        );

        // Storage button.
        this.createTeleportButton(
                "&f&lCrates",
                "teleport crates",
                33, 34, 35
        );

        // Storage button.
        this.createTeleportButton(
                "&f&lResources",
                "teleport resources",
                42, 43, 44
        );

        // Storage button.
        this.createTeleportButton(
                "&f&lAuction House",
                "teleport auctionhouse",
                51
        );

        // Storage button.
        this.createTeleportButton(
                "&f&lJobs",
                "teleport jobs",
                52
        );

        // Storage button.
        this.createTeleportButton(
                "&f&lBattle Pass",
                "teleport battlepass",
                53
        );
    }

    /**
     * Used to create a simple teleport button.
     *
     * @param title The button's title.
     * @param command The button's command to execute.
     * @param slots The button's slots.
     */
    private void createTeleportButton(@NotNull String title, @NotNull String command, @NotNull Integer... slots) {
        // Fishing button.
        this.setItem(new InventoryItem()
                .setMaterial(Material.PINK_STAINED_GLASS_PANE)
                .setCustomModelData(1)
                .setName(title)
                .setLore("&7Click to execute &f/{command}&7."
                        .replace("{command}", command))
                .addAction((ClickAction) (user, type, inventory) -> {
                    user.runCommandsAsOp(command);
                })
                .addSlot(slots)
        );
    }
}

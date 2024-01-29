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

import com.github.cozyplugins.cozydeliveries.Delivery;
import com.github.cozyplugins.cozylibrary.inventory.InventoryInterface;
import com.github.cozyplugins.cozylibrary.inventory.InventoryItem;
import com.github.cozyplugins.cozylibrary.inventory.action.action.ClickAction;
import com.github.cozyplugins.cozylibrary.user.PlayerUser;
import com.github.minemaniauk.minemaniasmp.MineManiaSMP;
import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the smp main menu.
 */
public class InfoInventory extends InventoryInterface {

    public InfoInventory() {
        super(54, "&f₴₴₴₴₴₴₴₴✼");
    }

    @Override
    protected void onGenerate(PlayerUser player) {

        // Help button.
        this.setItem(new InventoryItem()
                .setMaterial(Material.PINK_STAINED_GLASS_PANE)
                .setCustomModelData(1)
                .setName("&b&lHelp")
                .setLore("&e1) &f/teleport &7Then click random to",
                        "&7find a place to set up base.",
                        "&7",
                        "&e2) &f/kit claim &7To claim your land.",
                        "&7",
                        "&e3) &f/jobs &7To join a job and earn money.",
                        "&7",
                        "&e4) Have fun! :D and &f/help &7to explore",
                        "&7more features.")
                .addSlot(45, 46)
        );

        // Jobs button.
        this.setItem(new InventoryItem()
                .setMaterial(Material.PINK_STAINED_GLASS_PANE)
                .setCustomModelData(1)
                .setName("&b&lJobs")
                .setLore("&7Click to open &f/jobs&7.",
                        "&7",
                        "&7Select and view jobs to earn money!",
                        "&7Then do &f/balance &7to see your money.")
                .addSlot(0, 1, 2,
                        9, 10, 11)
                .addAction((ClickAction) (user, type, inventory) -> {
                    user.runCommandsAsOp("dm open jobs_miner");
                })
        );

        // Teleport button.
        this.setItem(new InventoryItem()
                .setMaterial(Material.PINK_STAINED_GLASS_PANE)
                .setCustomModelData(1)
                .setName("&a&lTeleport")
                .setLore("&7Click to open the &f/teleport &7menu.")
                .addSlot(3, 4, 5,
                        12, 13, 14)
                .addAction((ClickAction) (user, type, inventory) -> {
                    user.runCommandsAsOp("teleport");
                })
        );

        // Auction House button.
        this.setItem(new InventoryItem()
                .setMaterial(Material.PINK_STAINED_GLASS_PANE)
                .setCustomModelData(1)
                .setName("&6&lAuction House")
                .setLore("&eComing soon...")
                .addSlot(6, 7, 8,
                        15, 16, 17)
        );

        // Deliveries button.
        this.setItem(new InventoryItem()
                .setMaterial(Material.PINK_STAINED_GLASS_PANE)
                .setCustomModelData(1)
                .setName("&f&lDeliveries")
                .setLore("&7Click to see your &f/deliveries&7.")
                .addAction((ClickAction) (user, type, inventory) -> {
                    user.runCommandsAsOp("deliveries");
                })
                .addSlot(18, 19, 20, 21,
                        27, 28, 30,
                        36, 37, 38, 39)
        );

        // Add the first delivery.
        this.setDelivery(player);

        // Profile.
        this.setItem(new InventoryItem()
                .setMaterial(Material.PINK_STAINED_GLASS_PANE)
                .setCustomModelData(1)
                .setName("&d&lProfile")
                .setLore("&eComing soon...")
                .addSlot(24, 25, 26,
                        33, 34, 35,
                        41, 42, 43)
        );
    }

    private void setDelivery(@NotNull PlayerUser user) {
        List<Delivery> deliveryList = MineManiaSMP.getInstance().getDeliveriesAPI()
                .getDeliveryList(user.getUuid());

        if (deliveryList.isEmpty()) {
            this.setItem(new InventoryItem()
                    .setMaterial(Material.PINK_STAINED_GLASS_PANE)
                    .setCustomModelData(1)
                    .setName("&f&lEmpty")
                    .setLore("&7You currently have no parcels waiting.")
                    .addSlot(29)
            );
            return;
        }

        final Delivery delivery = deliveryList.get(0);

        List<String> lore = new ArrayList<>();

        lore.add("&7Click to collect delivery.");
        lore.add("&7");
        lore.add("&e&lContent");
        lore.addAll(new ArrayList<>(List.of(delivery
                        .getContenceString()
                        .replace(", ", "\n&f")
                        .split("\n")
                ))
        );

        // One of the deliveries.
        this.setItem(new InventoryItem()
                .setMaterial(Material.BARREL)
                .setName("&6&lDelivery")
                .setLore(lore)
                .addAction((ClickAction) (user1, type, inventory) -> {

                    // Check if they have inventory space.
                    if (!delivery.hasInventorySpace(user1)) {
                        user1.sendMessage("&7You dont have enough inventory space to collect this delivery.");
                        return;
                    }

                    // Give the delivery to the player.
                    boolean success = delivery.give(user1);
                    if (success) {
                        user1.sendMessage("&7You have received a delivery.");
                        this.onGenerate(user1);
                        return;
                    }

                    user1.sendMessage("&7Failed to receive a delivery.");
                })
                .addSlot(29)
        );
    }
}

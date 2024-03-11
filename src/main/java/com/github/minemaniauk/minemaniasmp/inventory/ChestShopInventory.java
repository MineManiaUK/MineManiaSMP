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

import com.github.cozyplugins.cozylibrary.inventory.CozyInventory;
import com.github.cozyplugins.cozylibrary.inventory.InventoryItem;
import com.github.cozyplugins.cozylibrary.user.PlayerUser;
import org.bukkit.Material;

public class ChestShopInventory extends CozyInventory {

    public ChestShopInventory() {
        super(54, "&f₴₴₴₴₴₴₴₴⚡");
    }

    @Override
    protected void onGenerate(PlayerUser player) {

        this.setItem(new InventoryItem()
                .setMaterial(Material.PINK_STAINED_GLASS_PANE)
                .setCustomModelData(1)
                .setName("&a&lHow to Create a Chest Shop")
                .setLore("&7",
                        "&7- &fPlace a container&7. (Chest, Shulker...)",
                        "&7- &fHold the item &7you want to buy or sell.",
                        "&7- Look at the container.",
                        "&7- Type &f/chestshop create [price]",
                        "&7",
                        "&aThat's it, you have created a shop!")
                .addSlot(0, 1, 2, 3,
                        9, 10, 11, 12,
                        18, 19, 20, 21)
        );

        this.setItem(new InventoryItem()
                .setMaterial(Material.PINK_STAINED_GLASS_PANE)
                .setCustomModelData(1)
                .setName("&a&lHow to Create a Chest Shop")
                .setLore("&7",
                        "&eHow can i buy instead of sell?",
                        "&7- Look at the shop and run &f/chestshop buy",
                        "&7",
                        "&eHow can i change the price?",
                        "&7- Look at the shop and run &f/chestshop price [price]",
                        "&7",
                        "&eHow do i delete a shop?",
                        "&7- Look at the shop and run &f/chestshop remove")
                .addSlot(4, 5, 6, 7, 8,
                        13, 14, 15, 16, 16, 17,
                        22, 23, 24, 25, 26, 27)
        );

        this.setItem(new InventoryItem()
                .setMaterial(Material.PINK_STAINED_GLASS_PANE)
                .setCustomModelData(1)
                .setName("&b&lHow to Buy and Sell Items")
                .setLore("&7",
                        "&7- &fClick the container&7.",
                        "&7- &fType in chat the number of ",
                        "&f &f &fitems &7you want to buy or sell.")
                .addSlot(27, 28, 29, 30, 31, 32, 33, 34, 35,
                        36, 37, 38, 39, 40, 41, 42, 43, 44,
                        45, 46, 47, 48, 49, 50, 51, 52, 53)
        );
    }
}

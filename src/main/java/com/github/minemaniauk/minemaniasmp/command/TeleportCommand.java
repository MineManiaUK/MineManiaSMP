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

package com.github.minemaniauk.minemaniasmp.command;

import com.github.cozyplugins.cozylibrary.command.command.CommandType;
import com.github.cozyplugins.cozylibrary.command.datatype.CommandArguments;
import com.github.cozyplugins.cozylibrary.command.datatype.CommandStatus;
import com.github.cozyplugins.cozylibrary.command.datatype.CommandSuggestions;
import com.github.cozyplugins.cozylibrary.command.datatype.CommandTypePool;
import com.github.cozyplugins.cozylibrary.user.ConsoleUser;
import com.github.cozyplugins.cozylibrary.user.FakeUser;
import com.github.cozyplugins.cozylibrary.user.PlayerUser;
import com.github.cozyplugins.cozylibrary.user.User;
import com.github.minemaniauk.minemaniasmp.inventory.TeleportInventory;
import com.github.smuddgge.squishyconfiguration.interfaces.ConfigurationSection;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;

/**
 * Represents the teleport command.
 * Used to open the teleport inventory.
 */
public class TeleportCommand implements CommandType {

    /**
     * Represents the places the player
     * can use as an argument to teleport to.
     */
    public enum Place {
        HORSES("world", 133, 80, -84),
        FISHING("world", 200, 77, -28),
        PVP("world", 109, 84, 70),
        LEADERBOARDS("world", 40, 124, 70),
        STORAGE("world", 5, 114, 19),
        CRATES("world", 59, 113, -38),
        // RESOURCES("world", 21, 80, -107),
        MARKET("world", 14, 117, -18.5),
        JOBS("world", 5, 116, -18);
        // BATTLEPASS("world", 0, 118, 0);

        private final @NotNull Location location;

        Place(@NotNull String worldName, double x, double y, double z) {
            this.location = new Location(Bukkit.getWorld(worldName), x, y, z);
        }

        /**
         * Used to get the place's location.
         *
         * @return The location of the place.
         */
        public @NotNull Location getLocation() {
            return this.location;
        }
    }

    @Override
    public @NotNull String getIdentifier() {
        return "teleport";
    }

    @Override
    public @Nullable String getSyntax() {
        return "/[name]";
    }

    @Override
    public @Nullable String getDescription() {
        return "Opens the teleport GUI.";
    }

    @Override
    public @Nullable CommandTypePool getSubCommandTypes() {
        return null;
    }

    @Override
    public @Nullable CommandSuggestions getSuggestions(@NotNull User user, @NotNull ConfigurationSection section, @NotNull CommandArguments arguments) {
        return new CommandSuggestions().append(Arrays.stream(Place.values())
                .map(Place::toString)
                .map(String::toLowerCase)
                .toList()
        );
    }

    @Override
    public @Nullable CommandStatus onUser(@NotNull User user, @NotNull ConfigurationSection section, @NotNull CommandArguments arguments) {
        return null;
    }

    @Override
    public @Nullable CommandStatus onPlayer(@NotNull PlayerUser user, @NotNull ConfigurationSection section, @NotNull CommandArguments arguments) {

        if (!arguments.getArguments().isEmpty() && !arguments.getArguments().get(0).isEmpty()) {
            final String placeName = arguments.getArguments().get(0);

            // Check if the place doesn't exist.
            if (!Arrays.stream(Place.values())
                    .map(Place::name)
                    .map(String::toLowerCase)
                    .toList()
                    .contains(placeName)) {

                user.sendMessage("&7&l> &7That place does not exist.");
                return new CommandStatus();
            }

            Place place = Place.valueOf(placeName.toUpperCase());
            user.sendMessage("&7&l> &7Teleporting to &f" + placeName + "&7.");
            user.getPlayer().teleport(place.getLocation());
            return new CommandStatus();
        }

        new TeleportInventory().open(user.getPlayer());
        return new CommandStatus();
    }

    @Override
    public @Nullable CommandStatus onFakeUser(@NotNull FakeUser user, @NotNull ConfigurationSection section, @NotNull CommandArguments arguments) {
        return null;
    }

    @Override
    public @Nullable CommandStatus onConsole(@NotNull ConsoleUser user, @NotNull ConfigurationSection section, @NotNull CommandArguments arguments) {
        return null;
    }
}

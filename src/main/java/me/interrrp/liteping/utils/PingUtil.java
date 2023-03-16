package me.interrrp.liteping.utils;

import org.bukkit.entity.Player;

/**
 * Utility methods related to ping.
 */
public class PingUtil {

    /**
     * Get the ping of a player in milliseconds.
     * 
     * @param player The player to get the ping of.
     * @return The ping of the player in milliseconds.
     */
    public static int getPing(Player player) {
        return player.getPing();
    }

    /**
     * Get the ping of a player in milliseconds as a string.
     * 
     * @param player The player to get the ping of.
     * @return The ping of the player in milliseconds as a string.
     */
    public static String getPingStr(Player player) {
        return String.valueOf(getPing(player));
    }

}

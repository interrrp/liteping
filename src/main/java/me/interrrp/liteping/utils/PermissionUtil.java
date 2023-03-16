package me.interrrp.liteping.utils;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class PermissionUtil {

    /**
     * Check if a player has a permission.
     * 
     * This will always return true if the permission system is disabled.
     * 
     * @param player     The player to check the permission of.
     * @param permission The permission to check.
     * @return Whether the player has the permission.
     */
    public static boolean hasPermission(Player player, String permission) {
        if (!ConfigUtil.isPermissionSystemEnabled())
            return true;

        return player.hasPermission(permission);
    }

    /**
     * Send a player a message saying they don't have permission.
     * 
     * @param player The player to send the message to.
     */
    public static void sendNoPermission(Player player) {
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', ConfigUtil.getNoPermissionMessage()));
    }

}

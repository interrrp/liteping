package me.interrrp.liteping.utils;

import me.interrrp.liteping.LitePing;

/**
 * Utility methods related to configuration.
 */
public class ConfigUtil {

    /**
     * Check if the permission system is enabled.
     * 
     * @return A boolean indicating if the permission system is enabled.
     */
    public static boolean isPermissionSystemEnabled() {
        return LitePing.getInstance().getConfig().getBoolean("permission-system.enabled");
    }

    /**
     * Get the message to send when a player does not have a permission.
     * 
     * @return The message to send when a player does not have a permission.
     */
    public static String getNoPermissionMessage() {
        return LitePing.getInstance().getConfig().getString("permission-system.no-perm-message");
    }

    /**
     * Check if the tablist is enabled.
     * 
     * @return A boolean indicating if the tablist is enabled.
     */
    public static boolean isTablistEnabled() {
        return LitePing.getInstance().getConfig().getBoolean("tablist.enabled");
    }

    /**
     * Get the tablist update delay in seconds.
     * 
     * @return The tablist update delay in seconds.
     */
    public static long getTablistUpdateDelay() {
        return LitePing.getInstance().getConfig().getInt("tablist.update-delay");
    }

    /**
     * Check if the tablist should show the real name of the player.
     * 
     * @return A boolean indicating if the tablist should show the real name of the
     *         player.
     */
    public static boolean isTablistShowRealName() {
        return LitePing.getInstance().getConfig().getBoolean("tablist.show-real-name");
    }

    /**
     * Get the tablist prefix.
     * 
     * @return The tablist prefix.
     */
    public static String getTablistPrefix() {
        return LitePing.getInstance().getConfig().getString("tablist.prefix");
    }

    /**
     * Get the tablist suffix.
     * 
     * @return The tablist suffix.
     */
    public static String getTablistSuffix() {
        return LitePing.getInstance().getConfig().getString("tablist.suffix");
    }

}

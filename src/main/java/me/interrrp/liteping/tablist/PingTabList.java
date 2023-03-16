package me.interrrp.liteping.tablist;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import me.interrrp.liteping.LitePing;
import me.interrrp.liteping.utils.ConfigUtil;
import me.interrrp.liteping.utils.PingUtil;

/**
 * A task that updates the tab list with the ping of each player.
 */
public class PingTabList extends BukkitRunnable {

    /**
     * The instance of the plugin.
     */
    private LitePing plugin;

    /**
     * Create a new PingTabList task.
     * 
     * @param plugin The instance of the plugin.
     */
    public PingTabList(LitePing plugin) {
        this.plugin = plugin;
    }

    public void run() {
        for (Player player : this.plugin.getServer().getOnlinePlayers()) {
            updatePlayerTablist(player);
        }
    }

    /**
     * Update the tab list entry of a player.
     * 
     * @param player The player to update the tab list entry of.
     */
    private void updatePlayerTablist(Player player) {
        updatePlayerPrefix(player);
        updatePlayerSuffix(player);
    }

    /**
     * Update the prefix of a player in the tab list.
     * 
     * @param player The player to update the prefix of.
     */
    private void updatePlayerPrefix(Player player) {
        String prefix = ConfigUtil.getTablistPrefix();
        if (prefix.equals(""))
            return;

        String name = getName(player);
        String ping = PingUtil.getPingStr(player);

        player.setPlayerListName(ChatColor.translateAlternateColorCodes('&',
                prefix.replace("%ping%", ping)) + " " + name);
    }

    /**
     * Update the suffix of a player in the tab list.
     * 
     * @param player The player to update the suffix of.
     */
    private void updatePlayerSuffix(Player player) {
        String suffix = ConfigUtil.getTablistSuffix();
        if (suffix.equals(""))
            return;

        String name = getName(player);
        String ping = PingUtil.getPingStr(player);

        player.setPlayerListName(name + " " + ChatColor.translateAlternateColorCodes('&',
                suffix.replace("%ping%", ping)));
    }

    /**
     * Get the name of a player, either their real name or their display name.
     * 
     * This is based on the config option "tablist.show-real-name".
     * 
     * @param player The player to get the name of.
     * @return The name of the player.
     */
    private String getName(Player player) {
        if (ConfigUtil.isTablistShowRealName()) {
            return player.getName();
        } else {
            return player.getDisplayName();
        }
    }

}

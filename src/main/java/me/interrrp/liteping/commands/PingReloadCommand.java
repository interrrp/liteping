package me.interrrp.liteping.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.interrrp.liteping.LitePing;

/**
 * Command to reload the plugin.
 */
public class PingReloadCommand implements CommandExecutor {

    /**
     * The plugin instance.
     */
    private LitePing plugin;

    /**
     * Initialize the command.
     * 
     * @param plugin The plugin instance.
     */
    public PingReloadCommand(LitePing plugin) {
        this.plugin = plugin;
    }

    /**
     * Called when the command is invoked.
     * 
     * @param sender The sender of the command (player or console).
     * @param c      The command.
     * @param label  The label of the command.
     * @param args   The arguments of the command.
     * @return Whether the command was executed successfully.
     */
    @Override
    public boolean onCommand(CommandSender sender, Command c, String label, String[] args) {
        if (!sender.hasPermission("liteping.reload")) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                    plugin.getConfig().getString("permission-system.no-perm-message")));

            return true;
        }

        plugin.reload();

        if (sender instanceof Player) {
            sender.sendMessage(ChatColor.GREEN + "Plugin reloaded.");
        }

        return true;
    }

}

package me.interrrp.liteping.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.interrrp.liteping.LitePing;
import me.interrrp.liteping.utils.ConfigUtil;
import me.interrrp.liteping.utils.PermissionUtil;
import me.interrrp.liteping.utils.PingUtil;

/**
 * Command to get the ping of a player.
 */
public class PingCommand implements CommandExecutor {

    /**
     * The plugin instance.
     */
    private LitePing plugin;

    /**
     * Initialize the command.
     * 
     * @param plugin The plugin instance.
     */
    public PingCommand(LitePing plugin) {
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
        if (!(sender instanceof Player)) {
            plugin.getLogger().info(ChatColor.RED + "This command is only executable as a Player.");
            return true;
        }

        final Player player = (Player) sender;

        boolean selfPing = args.length == 0;

        if (selfPing) {
            if (!PermissionUtil.hasPermission(player, "liteping.ping")) {
                PermissionUtil.sendNoPermission(player);
                return true;
            }

            sendSelfPing(player);

            return true;
        }

        if (!selfPing) {
            if (!PermissionUtil.hasPermission(player, "liteping.ping.others")) {
                PermissionUtil.sendNoOtherPing(player);
                return true;
            }

            String targetName = args.length > 0 ? args[0] : null;
            Player target = Bukkit.getPlayer(targetName);

            if (target == null) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                        ConfigUtil.getPlayerNotFoundMessage()));

                return true;
            }

            sendTargetPing(player, target);

            return true;
        }

        return true;
    }

    /**
     * Send the ping of the player to themselves.
     * 
     * @param player The player to send the ping to.
     */
    private void sendSelfPing(Player player) {
        String ping = PingUtil.getPingStr(player);
        String message = plugin.getConfig().getString("ping-command.ping-message").replaceAll("%ping%", ping);

        player.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }

    /**
     * Send the ping of a player to a player.
     * 
     * @param player       The player to send the ping to.
     * @param targetPlayer The player to send the ping of.
     */
    private void sendTargetPing(Player player, Player targetPlayer) {
        String ping = PingUtil.getPingStr(targetPlayer);
        String message = plugin.getConfig().getString("ping-command.ping-target-message")
                .replaceAll("%ping%", ping).replaceAll("%target%", targetPlayer.getName());

        player.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }

}

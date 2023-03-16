package me.interrrp.liteping;

import org.bukkit.plugin.java.JavaPlugin;

import me.interrrp.liteping.commands.PingCommand;
import me.interrrp.liteping.commands.PingReloadCommand;
import me.interrrp.liteping.tablist.PingTabList;
import me.interrrp.liteping.utils.ConfigUtil;

/**
 * The main class of the plugin.
 */
public class LitePing extends JavaPlugin {

    /**
     * The instance of the plugin.
     * 
     * This is commonly used to reference the plugin from other classes.
     */
    private static LitePing instance;

    /**
     * Get the instance of the plugin.
     * 
     * @return The instance of the plugin.
     */
    public static LitePing getInstance() {
        return instance;
    }

    /**
     * Called when the plugin is enabled.
     */
    @Override
    public void onEnable() {
        instance = this;

        saveDefaultConfig();
        setCommandExecutors();
        startTasks();
    }

    /**
     * Called when the plugin is disabled.
     */
    @Override
    public void onDisable() {
        instance = null;

        getLogger().info("Cancelling tasks...");
        getServer().getScheduler().cancelTasks(this);
    }

    /**
     * Set the command executors.
     */
    private void setCommandExecutors() {
        getCommand("ping").setExecutor(new PingCommand(this));
        getCommand("pingreload").setExecutor(new PingReloadCommand(this));
    }

    /**
     * Start all tasks.
     */
    private void startTasks() {
        if (ConfigUtil.isPermissionSystemEnabled()) {
            getLogger().info("The permission system is enabled. Please check that users have proper permissions.");
        }

        if (!ConfigUtil.isTablistEnabled()) {
            getLogger().info(
                    "The tablist is disabled, the ping will not be shown as a prefix. You can change this option in the config.");
        } else {
            startTablistTask();
        }
    }

    /**
     * Start the tablist task.
     */
    private void startTablistTask() {
        long delay = ConfigUtil.getTablistUpdateDelay();
        long delayTicks = delay * 20;

        new PingTabList(this).runTaskTimerAsynchronously(this, delayTicks, delayTicks);

        getLogger().info("TabList is enabled, task added with a delay of " + delay + " second/s.");
    }

    /**
     * Reload the plugin.
     */
    public void reload() {
        getLogger().info("Reloading the plugin...");
        getServer().getScheduler().cancelTasks(this);

        reloadConfig();
        startTasks();

        getLogger().info("Plugin reloaded.");
    }

}

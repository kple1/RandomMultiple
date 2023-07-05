package com.server.randommultiple;

import com.server.randommultiple.Command.MainCommand;
import com.server.randommultiple.Listener.Citizen;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    public static Main plugin;

    public void listener() {
        Bukkit.getPluginManager().registerEvents(new Citizen(), this);
    }

    public void command() {
        Bukkit.getPluginCommand("RandomMultiple").setExecutor(new MainCommand());
    }

    @Override
    public void onEnable() {
        listener();
        command();
        plugin = this;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Main getPlugin() {
        return plugin;
    }
}

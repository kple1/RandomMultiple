package com.server.randommultiple;

import com.server.randommultiple.Command.MainCommand;
import com.server.randommultiple.Listener.Citizen;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

import static com.server.randommultiple.UserData.Datas.config;
import static com.server.randommultiple.UserData.Datas.playerFile;

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
        plugin.saveDefaultConfig();
    }

    public void createPlayerDefaults() {
        YamlConfiguration playerConfig = YamlConfiguration.loadConfiguration(playerFile);
        playerConfig.addDefault("배팅 금액", 0);
        playerConfig.options().copyDefaults(true);
        saveYamlConfiguration();
    }

    public void saveYamlConfiguration() {
        try {
            config.save(playerFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Main getPlugin() {
        return plugin;
    }
}

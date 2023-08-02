package com.server.randommultiple;

import com.server.randommultiple.Command.MainCommand;
import com.server.randommultiple.Listener.*;
import com.server.randommultiple.Utils.Color;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.io.IOException;

import static com.server.randommultiple.UserData.Datas.config;
import static com.server.randommultiple.UserData.Datas.playerFile;

public final class Main extends JavaPlugin {

    public static Main plugin;
    private File uuidFolder;

    public void listener() {
        Bukkit.getPluginManager().registerEvents(new Citizen(), this);
        Bukkit.getPluginManager().registerEvents(new CreateFile(this), this);
        Bukkit.getPluginManager().registerEvents(new InvClickCancel(), this);
        Bukkit.getPluginManager().registerEvents(new ClickShowMultiple(), this);
        Bukkit.getPluginManager().registerEvents(new InvCloseEvent(), this);
        Bukkit.getPluginManager().registerEvents(new OpenInv(), this);
    }

    public void command() {
        Bukkit.getPluginCommand("RandomMultiple").setExecutor(new MainCommand());
    }

    @Override
    public void onEnable() {
        listener();
        command();
        plugin = this;
        saveDefaultConfig();
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

    public File getUuidFolder() {
        return uuidFolder;
    }

    public static Main getPlugin() {
        return plugin;
    }
}

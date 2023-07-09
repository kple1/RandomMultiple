package com.server.randommultiple;

import com.server.randommultiple.Command.MainCommand;
import com.server.randommultiple.Listener.Citizen;
import com.server.randommultiple.Listener.CreateFile;
import com.server.randommultiple.Listener.InvClickCancel;
import com.server.randommultiple.Listener.ClickShowMultiple;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

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
        saveConfig();
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

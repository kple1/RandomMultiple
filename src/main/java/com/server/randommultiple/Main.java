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

    private int taskId;
    String title = (Color.chat("&f[ &a랜덤배율 &f] "));
    public void startTimer(Inventory inv, Player player) {
        taskId = new BukkitRunnable() {
            int time = 0;

            //중요 시스템 (로또진행 메인 시스템)
            @Override
            public void run() {
                switch (time) {
                    case 0 -> player.sendMessage(title + "5초뒤 게임이 종료됩니다.");
                    case 1 -> player.sendMessage(title + "4초뒤 게임이 종료됩니다.");
                    case 2 -> player.sendMessage(title + "3초뒤 게임이 종료됩니다.");
                    case 3 -> player.sendMessage(title + "2초뒤 게임이 종료됩니다.");
                    case 4 -> {
                        player.sendMessage(title + "1초뒤 게임이 종료됩니다.");
                        inv.close();
                    }
                }
                time++;
            }
        }.runTaskTimer(this, 0, 20).getTaskId(); // 1초마다 실행
    }

    public File getUuidFolder() {
        return uuidFolder;
    }

    public static Main getPlugin() {
        return plugin;
    }
}

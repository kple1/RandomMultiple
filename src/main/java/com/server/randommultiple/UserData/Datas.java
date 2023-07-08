package com.server.randommultiple.UserData;

import com.server.randommultiple.Main;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class Datas {

    public static YamlConfiguration config = new YamlConfiguration();
    //public static File playerFile = new File(Main.getPlugin().getUuidFolder(), "plugins/RandomMultiple/UUIDs/");

    public static YamlConfiguration getPlayerConfig(OfflinePlayer player) {
        //playerFile = new File(Main.getPlugin().getUuidFolder(), "plugins/Lotto/UUIDs/" + player.getUniqueId().toString() + ".yml");

       /* if (!playerFile.exists()) {
            Main.getPlugin().createPlayerDefaults();
        }*/

        //config = YamlConfiguration.loadConfiguration(playerFile);
        return config;
    }
}

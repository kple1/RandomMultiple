package com.server.randommultiple.Command;

import com.server.randommultiple.Utils.Color;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static com.server.randommultiple.Main.plugin;

public class SetNPC implements CommandExecutor {

    String title = (Color.chat("&f[ &a랜덤배율 &f] "));

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player player) {
            if (player.isOp()) {
                return true;
            }
            plugin.getConfig().set("NPC 설정", args[1]);
            plugin.saveConfig();
            player.sendMessage(title + "npc 설정이 되었습니다.");
        }
        return false;
    }
}


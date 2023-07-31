package com.server.randommultiple.Command;

import com.server.randommultiple.Utils.ItemData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CreateCoin implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player player) {
            if (!player.isOp()) {
                return true;
            }

            player.getInventory().setItem(player.getInventory().getHeldItemSlot(), ItemData.createCoin(args[1], player));
        }
        return false;
    }
}

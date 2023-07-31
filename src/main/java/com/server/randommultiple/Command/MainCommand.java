package com.server.randommultiple.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class MainCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            if (args.length >= 1) {
                switch (args[0]) {
                    case "설정" -> {
                        SetNPC setOpenNPC = new SetNPC();
                        setOpenNPC.onCommand(sender, command, label, args);
                    }

                    case "삭제" -> {
                        RemoveNPC removeNPC = new RemoveNPC();
                        removeNPC.onCommand(sender, command, label, args);
                    }

                    case "코인" -> {
                        CreateCoin createCoin = new CreateCoin();
                        createCoin.onCommand(sender, command, label, args);
                    }
                }
            }
        }
        return false;
    }
}

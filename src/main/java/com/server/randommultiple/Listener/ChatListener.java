package com.server.randommultiple.Listener;

import com.server.randommultiple.Main;
import com.server.randommultiple.UserData.Datas;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;

public class ChatListener implements Listener {

    private final Player player;
    private Inventory inv;

    public ChatListener(Player player) {
        this.player = player;
    }

    @EventHandler
    public void onChat(PlayerChatEvent event) {
        Player getMessagePlayer = event.getPlayer();
        String message = event.getMessage();
        if (!message.isEmpty()) {
            YamlConfiguration config = Datas.getPlayerConfig(getMessagePlayer);
            config.set("배팅금액", Integer.parseInt(message));
            Main.getPlugin().saveYamlConfiguration();

            /* 인벤오픈 */
            ItemStack tintedBlock = new ItemStack(Material.TINTED_GLASS);

            this.inv = Bukkit.createInventory(null, 54, "[ 랜덤배율 ]");
            for (int i = 0; i < 54; i++) {
                inv.setItem(i, tintedBlock);
            }
            player.openInventory(inv);
        }

        // 이벤트 핸들러를 제거합니다.
        HandlerList.unregisterAll(this);
        event.setCancelled(true);
    }
}

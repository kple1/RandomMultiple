package com.server.randommultiple.Listener;

import com.server.randommultiple.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

public class ChatListener implements Listener {

    private final Main plugin;
    private final Player player;

    public ChatListener(Main plugin, Player player) {
        this.plugin = plugin;
        this.player = player;
    }

    @EventHandler
    public void onChat(PlayerChatEvent event) {
        String message = event.getMessage();
        if (message != null && !message.isEmpty()) {

        }

        // 이벤트 핸들러를 제거합니다.
        HandlerList.unregisterAll(this);
        event.setCancelled(true);
    }
}

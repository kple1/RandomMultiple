package com.server.randommultiple.Listener;

import com.server.randommultiple.Utils.Color;
import net.citizensnpcs.api.event.NPCRightClickEvent;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import static com.server.randommultiple.Main.plugin;

public class Citizen implements Listener {
    String title = (Color.chat("&f[ &a랜덤배율 &f] "));
    private Economy economy;

    @EventHandler
    public void npcOpenEvent(NPCRightClickEvent event) {
        Player player = event.getClicker();
        NPC npc = event.getNPC();
        String getNPC = plugin.getConfig().getString("NPC 설정");

        if (!npc.getName().equals(getNPC)) {
            return;
        }

        /* 배율입력 && 인벤오픈 */
        player.sendMessage(title + "배팅할 금액을 입력 해주세요.");
        Bukkit.getPluginManager().registerEvents(new ChatListener(player), plugin);
    }
}

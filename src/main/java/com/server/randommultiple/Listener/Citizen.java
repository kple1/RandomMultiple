package com.server.randommultiple.Listener;

import net.citizensnpcs.api.event.NPCRightClickEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.NPC;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import static com.server.randommultiple.Main.plugin;

public class Citizen implements Listener {

    @EventHandler
    void npcOpenEvent(NPCRightClickEvent event) {
        Player player = event.getClicker();
        NPC npc = (NPC) event.getNPC();
        String getNPC = plugin.getConfig().getString("NPC 설정");

        if (npc.getName().equals(getNPC)) { return; }
        Bukkit.getPluginManager().registerEvents(new ChatListener(plugin, player), plugin); //fix
    }
}
